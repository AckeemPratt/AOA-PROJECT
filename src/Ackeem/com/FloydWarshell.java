package Ackeem.com;

class FloydWarshell
{
    // Recursive Function to print path of given
    // vertex u from source vertex v
    private static void printPath(int[][] path, int v, int u)
    {
        if (path[v][u] == v)
            return;

        printPath(path, v, path[v][u]);
        System.out.print(path[v][u] + " ");
    }

    // Function to print the shortest cost with path
    // information between all pairs of vertices
    private static void printSolution(int[][] cost, int[][] path, int N)
    {
        for (int v = 0; v < N; v++)
        {
            for (int u = 0; u < N; u++)
            {
                if (u != v && path[v][u] != -1)
                {
                    System.out.print("Shortest Path from vertex " + v +  " to vertex " + u + " is (" + v + " ");
                    printPath(path, v, u);
                    System.out.println(u + ")");
                }
            }
        }
    }

    // Function to run Floyd-Warshell algorithm
    public static void FloydWarshell(int[][] adjMatrix, int N)
    {
        // cost[] and parent[] stores shortest-path
        // (shortest-cost/shortest route) information
        int[][] cost = new int[N][N];
        int[][] path = new int[N][N];

        // initialize cost[] and parent[]
        for (int v = 0; v < N; v++)
        {
            for (int u = 0; u < N; u++)
            {
                // initally cost would be same as weight
                // of the edge
                cost[v][u] = adjMatrix[v][u];

                if (v == u)
                    path[v][u] = 0;
                else if (cost[v][u] != Integer.MAX_VALUE)
                    path[v][u] = v;
                else
                    path[v][u] = -1;
            }
        }

        // run Floyd-Warshell
        for (int k = 0; k < N; k++)
        {
            for (int v = 0; v < N; v++)
            {
                for (int u = 0; u < N; u++)
                {
                    // If vertex k is on the shortest path from v to u,
                    // then update the value of cost[v][u], path[v][u]

                    if (cost[v][k] != Integer.MAX_VALUE
                            && cost[k][u] != Integer.MAX_VALUE
                            && (cost[v][k] + cost[k][u] < cost[v][u]))
                    {
                        cost[v][u] = cost[v][k] + cost[k][u];
                        path[v][u] = path[k][u];
                    }
                }

                // if diagonal elements become negative, the
                // graph contains a negative weight cycle
                if (cost[v][v] < 0)
                {
                    System.out.println("Negative Weight Cycle Found!!");
                    return;
                }
            }
        }

        // Print the shortest path between all pairs of vertices
        printSolution(cost, path, N);
    }

    public static void main(String[] args)
    {
        // Number of vertices in the adjMatrix
        final int N = 14;
        int M = Integer.MAX_VALUE;

        // given adjacency representation of matrix
        int[][] adjMatrix = new int[][]
                {




                        /*    1			2			3				4 		  5				6			7			  8			9		  10		11		12			13			14	*/
                        /*Kingston  St. Andrew  St. Catherine  Clarendon  Manchester  St. Elizabeth  Westmoreland  Hanover  St. James  Trelawny  St. Ann  St. Mary  Portland    St. Thomas*/
/*01 *//*Kingston */    {   0,         M,             2,         M ,         M,             M,             M,         M,       M,          M,         M,      M,         M,          M,    },
/*02 *//*St. Andrew*/   {   0,         0,             2,         M ,         M,             M,             M,         M,       M,          M,         M,      M,         M,          M,    },
/*03 *//*St. Catherine*/{   0,         M,             0,         M ,         M,             M,             M,         M,       M,          M,         M,      M,         M,          M,    },
/*04 *//*Clarendon */   {   0,         M,             2,         0 ,         M,             M,             M,         M,       M,          M,         M,      M,         M,          M,    },
/*05 *//*Manchester */  {   0,         M,             2,         M ,         0,             M,             M,         M,       M,          M,         M,      M,         M,          M,    },
/*06 *//*St. Elizabeth*/{   0,         M,             2,         M ,         M,             0,             M,         M,       M,          M,         M,      M,         M,          M,    },
/*07 *//*Westmoreland */{   0,         M,             2,         M ,         M,             M,             0,         M,       M,          M,         M,      M,         M,          M,    },
/*08 *//*Hanover */     {   0,         M,             2,         M ,         M,             M,             M,         0,       M,          M,         M,      M,         M,          M,    },
/*09*/ /*St. James */   {   0,         M,             2,         M ,         M,             M,             M,         M,       0,          M,         M,      M,         M,          M,    },
/*10*/ /*Trelawny */    {   0,         M,             2,         M ,         M,             M,             M,         M,       M,          0,         M,      M,         M,          M,    },
/*11*/ /*St. Ann */     {   0,         M,             2,         M ,         M,             M,             M,         M,       M,          M,         0,      M,         M,          M,    },
/*12*/ /*St. Mary */    {   0,         M,             2,         M ,         M,             M,             M,         M,       M,          M,         M,      0,         M,          M,    },
/*13*/ /*Portland */    {   0,         M,             2,         M ,         M,             M,             M,         M,       M,          M,         M,      M,         0,          M,    },
/*14*/ /*St. Thomas */  {   0,         M,             2,         M ,         M,             M,             M,         M,       M,          M,         M,      M,         M,          0,    },
                };

        // Run Floyd Warshell algorithm
        FloydWarshell(adjMatrix, N);
    }
}
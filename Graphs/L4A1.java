package Graphs;

import java.util.Scanner;

/*
 *  Undirected graphs:
 *  Write a program based on DFS which can answer questions of the
 *  type: "Find the a path from X to Y" Which should result in a
 *  list of vertices traversed from X to Y if there is a path
 */

public class L4A1 {

    public static void main(String[] args) {

        String dataPath = "contiguous-usa.dat";

        //Declare BST and read the .dat file
        BST <String, Integer> BST = new BST <String, Integer>();
        In datDoc1 = new In(dataPath);
        int stateNumber = 0;

        //Fill the BST with the states and corresponding number
        while(!datDoc1.isEmpty()) {

            String statstring = datDoc1.readString();
            if(!BST.contains(statstring)) {

                BST.put(statstring , stateNumber);
                stateNumber++;
            }
        }

        //Create the graph
        Graph stateGraph = new Graph(49);

        In datDoc2 = new In(dataPath);

        int edgeCounter = 1;
        String oldVertex = "";

        //For every other word in the dat file, create edge between that state and previous state
        while(!datDoc2.isEmpty()) {

            String vertex = datDoc2.readString();

            if(edgeCounter % 2 == 0) {

                stateGraph.addEdge(BST.get(oldVertex), BST.get(vertex));
            }

            oldVertex = vertex;
            edgeCounter++;
        }

        //Input from user
        Scanner userInput = new Scanner(System.in);
        System.out.println("Start state: ");
        String startState = userInput.nextLine();
        System.out.println("End state: ");
        String endState = userInput.nextLine();

        //Create starting point in startState
        DepthFirstPaths thePath = new DepthFirstPaths(stateGraph, BST.get(startState));

        System.out.println("The path between " + startState + " and " + endState + ": ");

        //Every state on the way to endState
        Iterable<Integer> theFoundPath = thePath.pathTo(BST.get(endState));

        //For every vertex in the found path
        for(int foundVertex : theFoundPath) {
            //Get the key
            Iterable<String> list = BST.keys();
            //For every element
            for(String element : list) {

                int comparison = BST.get(element);
                //Compare with the actual vertex
                if(comparison == foundVertex) {
                    //Print out the path
                    System.out.print(element + " -> ");
                }
            }
        }

        System.out.print("Done");
    }
}

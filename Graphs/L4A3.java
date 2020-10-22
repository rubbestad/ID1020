package Graphs;

import java.util.Scanner;
/*
 *  Directed graphs:
 *  Write a program that can answer if
 *  there is a path between any to vertices
 */

public class L4A3 {

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
        Digraph stateGraph = new Digraph(49);

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

        //User input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Start state: ");
        String startStat = userInput.nextLine();
        System.out.println("End state: ");
        String slutStat = userInput.nextLine();

        //Create starting point
        DirectedDFS thePath = new DirectedDFS(stateGraph, BST.get(startStat));

        //.marked returns a boolean, true if there is a path
        if(thePath.marked(BST.get(slutStat))) {
            System.out.println("There is a directed path between " + startStat + " and " + slutStat);
        }
        else {

            System.out.println("There is not a directed path between " + startStat + " and " + slutStat);
        }
    }
}

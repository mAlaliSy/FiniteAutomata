package com.malalisy.finiteautomata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DFA {

    /*DFA vertex may contain a list of NFA states*/
    private List<Set<Integer>> vertices;
    /*Adjacency list for graph representation*/
    private List<List<Pair<String, Integer>>> adjacencyList;

    public DFA() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    public void addVertex(Set<Integer> state) {
        if (!vertices.contains(state)) {
            vertices.add(state);
            adjacencyList.add(new ArrayList<>());
        }
    }


    public void addEdge(int sourcStateIndex, int targetStateIndex, String move) {
        adjacencyList.get(sourcStateIndex).add(new Pair<String, Integer>(move, targetStateIndex));
    }

    public void addEdge(Set<Integer> sourceState, Set<Integer> targetState, String move) {

        int sIndex = vertices.indexOf(sourceState);
        int tIndex = vertices.indexOf(targetState);

        addEdge(sIndex, tIndex, move);
    }


    public void print() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("State " + i + " that contains: " + vertices.get(i).toString() + ": ");
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                int destinationStateNumber = adjacencyList.get(i).get(j).getSecond();
                System.out.println("\tTo state " + destinationStateNumber +
                        " that contains: " + vertices.get(destinationStateNumber).toString()
                        + " => Move: " + adjacencyList.get(i).get(j).getFirst());
            }

            System.out.println("\n============\n");
        }
    }

}

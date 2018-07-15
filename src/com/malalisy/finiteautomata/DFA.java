package com.malalisy.finiteautomata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFA {

    /*DFA vertex may contain a list of NFA states*/
    private List<Set<Integer>> vertices;
    /*Adjacency list for graph representation*/
    private List<List<Pair<String, Integer>>> adjacencyList;

    private Set<Integer> acceptedEnds;

    public DFA() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
        acceptedEnds = new HashSet<>();
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

    public List<Set<Integer>> getVertices() {
        return vertices;
    }

    public List<List<Pair<String, Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    public void markAcceptedEnd(Set<Integer> acceptedEnds) {

        int i = 0;
        for (Set<Integer> state : vertices) {

            for (int acceptedEnd : acceptedEnds) {
                if (state.contains(acceptedEnd))
                    this.acceptedEnds.add(i);
            }
            i++;
        }
    }

    public boolean isAcceptedEnd(int stateNumber) {
        return acceptedEnds.contains(stateNumber);
    }

}

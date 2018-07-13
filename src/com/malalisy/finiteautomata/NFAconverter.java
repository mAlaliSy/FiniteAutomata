package com.malalisy.finiteautomata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.malalisy.finiteautomata.Constants.EPSILON;

public class NFAconverter {

    private Set<String>[][] stateGraph;
    private Set<Set<Integer>> markedStates;
    private Set<Set<Integer>> unmarkedStates;
    private Set<Integer> acceptedEnds;

    public NFAconverter(Set<String>[][] stateGraph, Set<Integer> acceptedEnds) {
        this.stateGraph = stateGraph;
        markedStates = new HashSet<>();
        unmarkedStates = new HashSet<>();
        this.acceptedEnds = acceptedEnds;
    }

    public DFA convert() {
        DFA dfa = new DFA();

        Set<String> moves = Utils.getAllMoves(stateGraph);
        Set<Integer> s0 = findEpsilonClosure(0);
        dfa.addVertex(s0);
        unmarkedStates.add(s0);

        while (!unmarkedStates.isEmpty()) {
            List<Set<Integer>> itemsToBeRemoved = new ArrayList<>();
            List<Set<Integer>> itemsToBeAdded = new ArrayList<>();

            for (Set<Integer> state : unmarkedStates) {

                itemsToBeRemoved.add(state);
                markedStates.add(state);

                // Mark state
                for (String move : moves) {
                    Set<Integer> moveState = getMoveOfState(state, move);
                    Set<Integer> newState = new HashSet<>();
                    for (int s : moveState) {
                        newState.addAll(findEpsilonClosure(s));
                    }

                    if (!markedStates.contains(newState)) {
                        itemsToBeAdded.add(newState);
                        dfa.addVertex(newState);
                    }

                    // Add edge to graph: from state -> newState: move
                    dfa.addEdge(state, newState, move);

                }

            }

            unmarkedStates.addAll(itemsToBeAdded);
            unmarkedStates.removeAll(itemsToBeRemoved);

        }

        dfa.markAcceptedEnd(acceptedEnds);

        return dfa;
    }

    private Set<Integer> findEpsilonClosure(int state) {
        Set<Integer> eclosure = new HashSet<>();
        eclosure.add(state);
        findEpsilonClosure(state, eclosure);
        return eclosure;
    }

    private void findEpsilonClosure(int state, Set<Integer> eclosure) {
        for (int i = 0; i < stateGraph.length; i++) {
            if (stateGraph[state][i].contains(EPSILON)) {
                if (!eclosure.contains(i)) {
                    eclosure.add(i);
                    findEpsilonClosure(i, eclosure);
                }
            }
        }
    }

    private Set<Integer> getMoveOfState(Set<Integer> state, String move) {
        Set<Integer> moveSet = new HashSet<>();
        for (int s : state) {
            for (int i = 0; i < stateGraph.length; i++) {
                for (String m : stateGraph[s][i]) {
                    if (m.equals(move))
                        moveSet.add(i);
                }
            }
        }

        return moveSet;
    }


}

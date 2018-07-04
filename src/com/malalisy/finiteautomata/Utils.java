package com.malalisy.finiteautomata;

import java.util.HashSet;
import java.util.Set;

import static com.malalisy.finiteautomata.Constants.EPSILON;

public final class Utils {
    private Utils() {
    }

    public static Set<String> getAllMoves(Set<String>[][] graph) {
        Set<String> moves = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                moves.addAll(graph[i][j]);
            }
        }
        moves.remove(EPSILON);
        return moves;
    }

}

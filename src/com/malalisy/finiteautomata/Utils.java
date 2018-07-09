package com.malalisy.finiteautomata;

import java.awt.*;
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

    public static String toHexString(Color colour) throws NullPointerException {
        String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
        if (hexColour.length() < 6) {
            hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
        }
        return "#" + hexColour;
    }


}

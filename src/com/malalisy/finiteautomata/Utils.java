package com.malalisy.finiteautomata;

import com.malalisy.finiteautomata.ui.components.GraphView;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
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


    public static String intSetToString(Set<Integer> integers) {

        StringBuilder string = new StringBuilder();
        int index = 0;
        for (int i : integers) {
            string.append(i);
            if (index < integers.size() - 1)
                string.append(",");
            index++;
        }

        return string.toString();
    }


    public static Pair<Set<String>[][], Set<Integer>> getTheDefaultExample() {
        Set<String>[][] stateGraph = new HashSet[9][9];
        for (int i = 0; i < stateGraph.length; i++) {
            for (int j = 0; j < stateGraph.length; j++) {
                stateGraph[i][j] = new HashSet<>();
            }
        }

        stateGraph[0][1].add(EPSILON);
        stateGraph[0][7].add(EPSILON);
        stateGraph[1][2].add(EPSILON);
        stateGraph[1][4].add(EPSILON);
        stateGraph[2][3].add("a");
        stateGraph[4][5].add("b");
        stateGraph[5][6].add(EPSILON);
        stateGraph[3][6].add(EPSILON);
        stateGraph[6][1].add(EPSILON);
        stateGraph[6][7].add(EPSILON);
        stateGraph[7][8].add("a");

        Set<Integer> acceptedStates = new HashSet<>();
        acceptedStates.add(8);

        return new Pair<>(stateGraph, acceptedStates);
    }

    public static void drawGraphFromAdjMatrix(Set<String>[][] adjMatrix, GraphView graphView, mxGraphComponent graphComponent) {


        Object[] vertices = new Object[adjMatrix.length];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = graphView.insertVertex(graphView.getDefaultParent(),
                    null, "" + i, 0, 0,
                    Constants.WIDTH, Constants.HEIGHT);
        }

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j].size() > 0) {

                    StringBuilder edgeLabel = new StringBuilder();
                    int index = 0;
                    for (String s : adjMatrix[i][j]) {
                        if (!s.equals(EPSILON) || adjMatrix[i][j].size() > 1)
                            edgeLabel.append(s);
                        if (index < adjMatrix[i][j].size() - 1)
                            edgeLabel.append(",");
                        index++;
                    }
                    graphView.insertEdge(graphView.getDefaultParent(), null, edgeLabel.toString(), vertices[i], vertices[j]);
                }
            }
        }


        mxHierarchicalLayout hierarchicalLayout = new mxHierarchicalLayout(graphView);
        hierarchicalLayout.setOrientation(SwingConstants.WEST);
        hierarchicalLayout.execute(graphView.getDefaultParent());
        new mxParallelEdgeLayout(graphView).execute(graphView.getDefaultParent());
    }

}

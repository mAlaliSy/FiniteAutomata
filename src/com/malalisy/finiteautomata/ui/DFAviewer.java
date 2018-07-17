package com.malalisy.finiteautomata.ui;

import com.malalisy.finiteautomata.Constants;
import com.malalisy.finiteautomata.DFA;
import com.malalisy.finiteautomata.Pair;
import com.malalisy.finiteautomata.ui.components.FrameDragListener;
import com.malalisy.finiteautomata.ui.components.GraphView;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Set;

public class DFAviewer {
    private JPanel mainPanel;
    private JPanel graphPanel;
    private JLabel statesLabel;
    private JPanel frameBorder;
    private JLabel xLabel;

    DFA dfa;

    public DFAviewer(DFA dfa) {
        this.dfa = dfa;


        GraphView graphView = new GraphView(false, true);
        mxGraphComponent graphComponent = new mxGraphComponent(graphView);

        List<Set<Integer>> v = dfa.getVertices();
        Object[] vertices = new Object[v.size()];
        for (int i = 0; i < v.size(); i++) {
            if (dfa.isAcceptedEnd(i)) {
                vertices[i] = graphView.insertVertex(graphView.getDefaultParent(),
                        null, "" + i, 0, 0,
                        Constants.WIDTH, Constants.HEIGHT, "shape=doubleEllipse;");
            } else {
                vertices[i] = graphView.insertVertex(graphView.getDefaultParent(),
                        null, "" + i, 0, 0,
                        Constants.WIDTH, Constants.HEIGHT);
            }
        }

        List<List<Pair<String, Integer>>> adjacencyList = dfa.getAdjacencyList();
        for (int i = 0; i < adjacencyList.size(); i++) {
            List<Pair<String, Integer>> adjacentStates = adjacencyList.get(i);
            for (Pair<String, Integer> edge : adjacentStates) {
                graphView.insertEdge(graphView.getDefaultParent(), null, edge.getFirst(), vertices[i], vertices[edge.getSecond()]);
            }
        }


        mxCircleLayout circleLayout = new mxCircleLayout(graphView);
        circleLayout.execute(graphView.getDefaultParent());
        new mxParallelEdgeLayout(graphView).execute(graphView.getDefaultParent());


        StringBuilder states = new StringBuilder();
        states.append("<html>");
        for (int i = 0; i < v.size(); i++) {
            states.append("State ").append(i).append(" : ");
            states.append(v.get(i));
            states.append("<br />");
        }
        states.append("</html>");
        statesLabel.setText(states.toString());

        graphComponent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        graphPanel.add(graphComponent);
    }


    public void show() {
        JFrame jFrame = new JFrame("DFA Viewer");

        jFrame.setUndecorated(true);
        jFrame.setContentPane(mainPanel);

        /*
         * Exit the app when the close button is clicked.
         * */
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Fit the window to the content
         * */
        jFrame.pack();

        jFrame.setVisible(true);

        /*
         * Display the window in the center of the screen.
         * */
        jFrame.setLocationRelativeTo(null);


        FrameDragListener frameDragListener = new FrameDragListener(jFrame);
        jFrame.addMouseListener(frameDragListener);
        jFrame.addMouseMotionListener(frameDragListener);


        xLabel.setOpaque(true);
        xLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jFrame.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });


    }


}

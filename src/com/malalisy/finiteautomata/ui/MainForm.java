package com.malalisy.finiteautomata.ui;

import com.malalisy.finiteautomata.Constants;
import com.malalisy.finiteautomata.NFAconverter;
import com.malalisy.finiteautomata.ui.components.FrameDragListener;
import com.malalisy.finiteautomata.ui.components.GraphView;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class MainForm {


    private JButton addStateButton;
    private JPanel graphPanel;
    private JPanel mainPanel;
    private JPanel frameBorder;
    private JButton removeButton;
    private JLabel xLabel;
    private JButton convertButton;


    private int stateCounter;

    public MainForm() {

        stateCounter = 0;
        GraphView graphView = new GraphView();

        mxGraphComponent graphComponent = new mxGraphComponent(graphView);
        graphComponent.getViewport().setBackground(Constants.BACKGROUND_COLOR);

        graphPanel.add(graphComponent);

        xLabel.setOpaque(true);
        xLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.exit(0);
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

        addStateButton.addActionListener(actionEvent -> {
            graphView.getModel().beginUpdate();
            graphView.insertVertex(graphView.getDefaultParent(), null, stateCounter + "", 100, 100, Constants.WIDTH, Constants.HEIGHT);
            graphView.getModel().endUpdate();
            stateCounter++;
        });
        removeButton.addActionListener(actionEvent -> {
            graphView.getModel().beginUpdate();


            if (((mxCell) graphView.getSelectionCell()).isVertex()) {
                int stateNumber = Integer.parseInt(((mxCell) graphView.getSelectionCell()).getValue().toString());

                for (Object o : graphView.getChildVertices(graphView.getDefaultParent())) {
                    mxCell cell = (mxCell) o;
                    int s = Integer.parseInt(cell.getValue().toString());
                    if (s > stateNumber) {
                        graphView.getModel().setValue(cell, s - 1);
                    }
                }

                stateCounter--;
            }
            graphView.removeCells(graphView.getSelectionCells());
            graphView.getModel().endUpdate();
        });

        graphView.getSelectionModel().addListener(mxEvent.CHANGE, (o, mxEventObject) -> {
            if (graphView.getSelectionCell() != null)
                removeButton.setVisible(true);
            else
                removeButton.setVisible(false);
        });


        graphComponent.getConnectionHandler().addListener(mxEvent.CONNECT, (o, mxEventObject) ->
                new mxParallelEdgeLayout(graphView).execute(graphView.getDefaultParent())

        );

        convertButton.addActionListener(actionEvent -> {
            int vertices = graphView.getChildVertices(graphView.getDefaultParent()).length;
            Set<String>[][] adjacencyMatrix = new Set[vertices][vertices];
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    adjacencyMatrix[i][j] = new HashSet<>();
                }
            }

            for (Object o : graphView.getChildEdges(graphView.getDefaultParent())) {
                mxCell cell = (mxCell) o;

                int source = Integer.parseInt(cell.getSource().getValue().toString());
                int target = Integer.parseInt(cell.getTarget().getValue().toString());

                String m = cell.getValue().toString();
                if (m == null || m.equals("")) {
                    adjacencyMatrix[source][target].add(Constants.EPSILON);
                    continue;
                }

                String[] moves = m.split(",");
                for (String move : moves) {
                    adjacencyMatrix[source][target].add(move);
                }
            }

            NFAconverter nfAconverter = new NFAconverter(adjacencyMatrix);
            DFAviewer dfAviewer = new DFAviewer(nfAconverter.convert());
            dfAviewer.show();
        });
    }

    public static void main(String[] args) {

    }

    public void show() {
        JFrame jFrame = new JFrame("NDFA to DFA Converter");

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
        jFrame.setMinimumSize(new Dimension(750, 500));

        FrameDragListener frameDragListener = new FrameDragListener(jFrame);
        jFrame.addMouseListener(frameDragListener);
        jFrame.addMouseMotionListener(frameDragListener);

    }
}

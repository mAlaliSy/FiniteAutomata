package com.malalisy.finiteautomata;

import com.malalisy.finiteautomata.ui.MainForm;
import com.malalisy.finiteautomata.ui.components.FrameDragListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.malalisy.finiteautomata.Constants.EPSILON;

public class Main {

    private static Set<String>[][] stateGraph;
    private static Set<Set<Integer>> markedStates;
    private static Set<Set<Integer>> unmarkedStates;
    private static DFA dfa;


    public static void main(String[] args) {
//        stateGraph = new HashSet[9][9];
//        markedStates = new HashSet<>();
//        unmarkedStates = new HashSet<>();
//        dfa = new DFA();
//
//        for (int i = 0; i < stateGraph.length; i++) {
//            for (int j = 0; j < stateGraph.length; j++) {
//                stateGraph[i][j] = new HashSet<>();
//            }
//        }
//
//
//        stateGraph[0][1].add(EPSILON);
//        stateGraph[0][7].add(EPSILON);
//        stateGraph[1][2].add(EPSILON);
//        stateGraph[1][4].add(EPSILON);
//        stateGraph[2][3].add("a");
//        stateGraph[4][5].add("b");
//        stateGraph[5][6].add(EPSILON);
//        stateGraph[3][6].add(EPSILON);
//        stateGraph[6][1].add(EPSILON);
//        stateGraph[6][7].add(EPSILON);
//        stateGraph[7][8].add("a");
//
//
//        NFAconverter nfAconverter = new NFAconverter(stateGraph);
//        nfAconverter.convert().print();


        MainForm mainForm = new MainForm();
        mainForm.show();
    }

}

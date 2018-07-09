package com.malalisy.finiteautomata.ui.components;

import com.malalisy.finiteautomata.Constants;
import com.malalisy.finiteautomata.Utils;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphView extends mxGraph {

    public GraphView() {

        Map<String, Object> map = new HashMap<>();
        map.put(mxConstants.STYLE_FONTCOLOR, "white");
        map.put(mxConstants.STYLE_FILLCOLOR, Utils.toHexString(Constants.MAIN_COLOR));
        map.put(mxConstants.STYLE_STROKECOLOR, Utils.toHexString(Constants.MAIN_COLOR));
        map.put(mxConstants.STYLE_SHAPE, "ellipse");
        stylesheet.setDefaultVertexStyle(map);
        map = new HashMap<>();
        map.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        map.put(mxConstants.STYLE_STROKECOLOR, "#888");
        map.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        stylesheet.setDefaultEdgeStyle(map);

    }


    @Override
    public boolean isCellEditable(Object o) {
        return model.isEdge(o);
    }

    @Override
    public boolean isAllowDanglingEdges() {
        return false;
    }

    @Override
    public boolean isAllowLoops() {
        return true;
    }

    @Override
    public boolean isCellsDeletable() {
        return false;
    }

    @Override
    public boolean isCellDeletable(Object o) {
        return true;
    }
}

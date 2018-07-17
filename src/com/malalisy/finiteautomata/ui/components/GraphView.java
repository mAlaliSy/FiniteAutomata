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

    boolean isEdgeEditable;

    public GraphView(boolean isEdgeEditable, boolean roundEdges) {
        this.isEdgeEditable = isEdgeEditable;

        Map<String, Object> map = stylesheet.getDefaultVertexStyle();
        map.put(mxConstants.STYLE_FONTCOLOR, "white");
        map.put(mxConstants.STYLE_FILLCOLOR, Utils.toHexString(Constants.MAIN_COLOR));
        map.put(mxConstants.STYLE_STROKECOLOR, Utils.toHexString(Constants.STROKE_COLOR));
        map.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        stylesheet.setDefaultVertexStyle(map);

        map = stylesheet.getDefaultEdgeStyle();
        map.put(mxConstants.STYLE_STROKECOLOR, "#888");
        map.put(mxConstants.STYLE_FONTCOLOR, "#0DBE62");
        map.put(mxConstants.STYLE_FONTSIZE, 18);


        map.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_CENTER);
        map.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);


        // Make the edges curved
        if (roundEdges) {
            map.put(mxConstants.STYLE_ROUNDED, true);
            map.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);
        }

        stylesheet.setDefaultEdgeStyle(map);

    }


    @Override
    public boolean isCellEditable(Object o) {
        return isEdgeEditable && model.isEdge(o);
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

    public void setEdgesRounded(boolean isEdgeRounded) {

        Map<String, Object> map = stylesheet.getDefaultEdgeStyle();
        map.put(mxConstants.STYLE_ROUNDED, isEdgeRounded);
        if (isEdgeRounded) {
            map.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);
        } else {
            map.put(mxConstants.STYLE_EDGE, mxConstants.STYLE_NOEDGESTYLE);
        }
        stylesheet.setDefaultEdgeStyle(map);
    }
}

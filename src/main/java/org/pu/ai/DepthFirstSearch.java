package org.pu.ai;

import org.pu.ai.model.Node;

import java.util.List;

public class DepthFirstSearch implements Searchable {

    private Graph graph;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    @Override
    public boolean search(String nameFirst, String nameSecond) {

        Node nodeOne = graph.getNode(nameFirst);
        Node nodeTwo = graph.getNode(nameSecond);
        if(nodeOne == null || nodeTwo == null) {
            System.out.println("Wrong or missing node!");
            return false;
        }

        return false;
    }

    private void dfs(int start) {

    }

    private void dfsRecursive(int current, List<Boolean> isVisited) {
        isVisited.set(current, true);
    }

}

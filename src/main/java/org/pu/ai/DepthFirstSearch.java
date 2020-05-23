package org.pu.ai;

import org.pu.ai.model.Link;
import org.pu.ai.model.Node;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch implements Searchable {

    private Graph graph;
    List<List<Node>> possibleRoutes;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    @Override
    public boolean search(String source, String target) {

        graph.graphReset();
        Node sourceNode = graph.getNode(source);
        Node targetNode = graph.getNode(target);
        List<Node> pathList = new ArrayList<>();
        possibleRoutes = new ArrayList<>();

        if(sourceNode == null || targetNode == null) {
            System.out.println("Wrong or missing node!");
            return false;
        }

        pathList.add(sourceNode);
        sourceNode.setVisited(true);

        searchHelper(sourceNode, targetNode, pathList);

        return !possibleRoutes.isEmpty();
    }

    private void searchHelper(Node source, Node destination,
                              List<Node> localPathList) {

        source.setVisited(true);

        if (source == destination) {
            System.out.println(localPathList);
            //copying the array list with the nodes because of the way java handles reference types
            possibleRoutes.add(new ArrayList<>(localPathList));
            source.setVisited(false);
            return;
        }

        for (Link link : source.getLinks()) {
            Node currentNode = graph.getNode(link.getRelatedName());

            if (!currentNode.isVisited()) {
                localPathList.add(currentNode);

                searchHelper(currentNode, destination, localPathList);

                localPathList.remove(currentNode);
            }
        }
    }

}

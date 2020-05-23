package org.pu.ai;

import org.pu.ai.model.Link;
import org.pu.ai.model.Node;

import java.util.ArrayList;
import java.util.Comparator;
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

    public Integer calculateRouteLength(List<Node> routeNodes) {
        Integer length = 0;

        for (int i = 0; i < routeNodes.size(); i++) {
            Node currentNode = routeNodes.get(i);

            if (i+1 != routeNodes.size()) {
                String nextNodeName = routeNodes.get(i + 1).getName();
                length += currentNode.getLinks()
                                     .stream()
                                     .filter( link -> link.getRelatedName().equals(nextNodeName))
                                     .findFirst().map(Link::getLength).orElse(0);
            }
        }

        return length;
    }

    public void searchRoute(List<String> routePoints) {
        Boolean hasRoute = search(routePoints.get(0), routePoints.get(routePoints.size()-1));

        if (!hasRoute) {
            throw new IllegalStateException("no route found!");
        }

        List<String> midPointNames = routePoints.subList(1, routePoints.size()-1);
        List<List<Node>> routesWithMidPoints = new ArrayList<>();

        for (List<Node> route : possibleRoutes ) {
            Integer matchingMidpointsCount = 0;
            for (Node node : route) {
                if (midPointNames.contains(node.getName())) {
                    matchingMidpointsCount++;
                }
            }

            if (matchingMidpointsCount == midPointNames.size()) {
                routesWithMidPoints.add(route);
            }
        }

        List<Node> routeWithMidPoints = routesWithMidPoints.stream()
                                            .min(Comparator.comparingInt(List::size))
                                            .orElse(new ArrayList<>());

        if (hasRoute && !routesWithMidPoints.isEmpty()) {
            System.out.println("A route is present between " + routePoints.get(0) + " and " + routePoints.get(routePoints.size()-1) + "\n"
                                + "Here is the sequence of nodes and their weights from the first to the final one: \n" + routeWithMidPoints + "\n"
                                + "The total length of the route from the first node to the final node is : " + calculateRouteLength(routeWithMidPoints));
        }
    }

}

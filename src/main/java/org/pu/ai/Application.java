package org.pu.ai;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    static Graph graph = new Graph();

    public static void init(Graph g) throws IOException {
        g.readData("src/resources/links.grf", "src/resources/nodes.grf");
    }

    public static void main(String[] args) throws IOException{
        init(graph);
        DepthFirstSearch dfs = new DepthFirstSearch(graph);

        String[] routePoints1 = {"sofia", "plovdiv", "stara zagora", "varna"};
        dfs.searchRoute(Arrays.asList(routePoints1));
        System.out.println();

        String[] routePoints2 = {"sofia", "plovdiv", "stara zagora"};
        dfs.searchRoute(Arrays.asList(routePoints2));
        System.out.println();

        String[] routePoints3 = {"sofia", "plovdiv", "yambol", "varna"};
        dfs.searchRoute(Arrays.asList(routePoints3));
        System.out.println();

        String[] routePoints4 = {"sofia", "plovdiv"};
        dfs.searchRoute(Arrays.asList(routePoints4));
        System.out.println();

        String[] routePoints5 = {"sofia", "plovdiv", "pleven"};
        dfs.searchRoute(Arrays.asList(routePoints5));
        System.out.println();
    }

}

package org.pu.ai;

import java.io.IOException;

public class Application {

    static Graph graph = new Graph();

    public static void init(Graph g) throws IOException {
        g.readData("src/resources/links.grf", "src/resources/nodes.grf");
    }

    public static void main(String[] args) throws IOException{
        init(graph);
        DepthFirstSearch dfs = new DepthFirstSearch(graph);
        dfs.search("sofia", "veliko tyrnovo");
        dfs.search("plovdiv", "pleven");
        dfs.search("sofia", "varna");
    }

}

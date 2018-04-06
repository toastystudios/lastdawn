/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 *
 * @author ruial
 */
public class JGraphTDemo {

    public static void main(String[] args) {
        //shortest path demo
        Graph g = new SimpleWeightedGraph<>(DefaultEdge.class);
        g.addVertex("v1");
        g.addVertex("v2");
        g.addVertex("v3");
        g.addVertex("v4");
        g.addEdge("v2", "v3");
        g.addEdge("v2", "v4");
        g.addEdge("v3", "v4");
        g.addEdge("v4", "v1");
        DijkstraShortestPath sp = new DijkstraShortestPath(g);
        List<String> shortestPath = sp.getPath("v1", "v2").getVertexList();
        for (String s : shortestPath) {
            System.out.println("Path: " + s);
        }
    }
}

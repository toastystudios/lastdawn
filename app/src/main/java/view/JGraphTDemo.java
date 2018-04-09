/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.util.List;
import java.util.Set;
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
       
        g.addVertex("Manobro City");
        g.addVertex("Sasuke");
        g.addVertex("Naruto");
        g.addVertex("SASUKEEEEEEEEEEE");
        g.addEdge("Sasuke", "Manobro City", 50);
        g.addEdge("Naruto", "Sasuke", 200);
        g.addEdge("Sasuke", "SASUKEEEEEEEEEEE", 150);
        
        Set<Integer> edges = g.edgeSet();
        
        for (Integer e : edges) {
            System.out.println(e);
        }
        DijkstraShortestPath sp = new DijkstraShortestPath(g);
        
        List<String> shortestPath = sp.getPath("Naruto", "SASUKEEEEEEEEEEE").getVertexList();
        
        for (String s : shortestPath) {
            System.out.println("Path: " + s );
        }
    }
}

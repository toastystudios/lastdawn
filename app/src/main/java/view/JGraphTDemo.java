/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package view;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.map.Coordinates;
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
        Coordinates a = new Coordinates(1, 1);
        Coordinates b = new Coordinates(1, 2);
        Coordinates c = new Coordinates(1, 3);
        Coordinates d = new Coordinates(1, 4);
       
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);
        g.addEdge(a, c, 1);
        g.addEdge(c, d, 2);
        g.addEdge(d, a, 3);
        g.addEdge(a, d, 4);
        
        Set<Integer> edges = g.edgeSet();
        
        for (Integer e : edges) {
            System.out.println(e);
        }
        DijkstraShortestPath sp = new DijkstraShortestPath(g);
        
        List<Coordinates> shortestPath = sp.getPath(a, c).getVertexList();
        
        for (Coordinates coord : shortestPath) {
            System.out.println("Path: \n" + coord);
        }
    }
}

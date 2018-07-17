/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 *
 * @author Toasty Studios
 */
public class GameMap {

    private static String name;
    private static Graph<Local, Road> graph;

    public GameMap(String name) {
        this.name = name;
        this.graph = new SimpleWeightedGraph<>(Road.class);
    }

    public String getName() {
        return name;
    }

    public boolean addLocal(Local local) {
        if (local == null || graph.containsVertex(local)) {
            return false;
        } else {
            graph.addVertex(local);
            return true;
        }
    }

    public boolean addRoad(Local v1, Local v2,Road road) {
        if (road == null || v1 == null || v2 == null || graph.containsEdge(v1, v2)) {
            return false;
        } else {
            graph.addEdge(v1, v2, road);
            return true;
        }
    }
    
    public Graph<Local, Road> getGraph(){
        return graph;
    }

    public String toString() {
        return name;
    }
    
}

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
public class Map {

    private String name;
    private Graph<Local, Road> graph;

    public Map(String name) {
        this.name = name;
        this.graph = new SimpleWeightedGraph<>(Road.class);
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return name;
    }
}

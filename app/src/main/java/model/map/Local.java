/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.map;

import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 *
 * @author Toasty Studios
 */
public class Local {

    private String name;
    private Kingdom kingdom;
    private Graph<District,Road> ditrictGraph;
    
    public Local(String name,Kingdom kingdom){
        this.name = name;
        this.ditrictGraph = new SimpleWeightedGraph<>(Road.class);
        this.kingdom = kingdom;
    }
    
    public String getLocalName(){
        return name;
    }
    
    public boolean newKingdom(Kingdom kingdom){
        if(kingdom != null) {
            this.kingdom = kingdom;
            return true;
        }
        return false;
    }
    
    public String getCurrentKingdom(){
        return this.kingdom.toString();
    }
    
}

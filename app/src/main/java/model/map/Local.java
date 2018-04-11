/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.map;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.kingdom);
        hash = 67 * hash + Objects.hashCode(this.ditrictGraph);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local other = (Local) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.kingdom != other.kingdom) {
            return false;
        }
        if (!Objects.equals(this.ditrictGraph, other.ditrictGraph)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Local{" + "name=" + name + ", kingdom=" + kingdom + ", ditrictGraph=" + ditrictGraph + '}';
    }
    
    
    
}

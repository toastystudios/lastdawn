/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.map;

import java.util.HashSet;
import java.util.Set;
import model.npc.NPC;

/**
 *
 * @author jpfr8
 */
public class District {
    
    private String name;
    private Set<NPC> npcs;
    
    public District(String name){
        this.name = name;
        this.npcs = new HashSet<>();
    }
    
    public String getDistrictName(){
        return name;
    }
    
    public Set<NPC> getNPCS(){
        return npcs;
    }
    
    public boolean addNPC(NPC npc){
        if(npcs.contains(npc)) return false;
        npcs.add(npc);
        return true;
    }
}

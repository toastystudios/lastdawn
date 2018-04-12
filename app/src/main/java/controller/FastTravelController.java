/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package controller;

import java.util.HashSet;
import java.util.Set;
import model.map.GameMap;
import model.map.Local;
import model.player.PlayerChar;

/**
 *
 * @author jpfr8
 */
public class FastTravelController {

    private GameMap map;
    private PlayerChar player;
    private Local local;

    public FastTravelController(GameMap map, PlayerChar player) {
        this.map = map;
        this.player = player;
    }

    public Set<Local> getLocalList() {
        Set<Local> locals = new HashSet<>();
        for (Local local : map.getGraph().vertexSet()) {
            locals.add(local);
        }
        return locals;
    }

    public boolean fastTravelDestiny(Local local) {
        if (!getLocalList().contains(local)) {
            player.changeLocation(local);
            return true;
        }
        return false;
    }
}

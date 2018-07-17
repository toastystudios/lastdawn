/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author Toasty Studios
 */
public enum ClassTypes {
    KNIGHT {
        @Override
        public String toString() {
            return "Knight";
        }
    },
    PALADIN {
        @Override
        public String toString() {
            return "Paladin";
        }
    },
    RANGER {
        @Override
        public String toString() {
            return "Ranger";
        }
    },
    SCIENTIST {
        @Override
        public String toString() {
            return "Scientist";
        }
    };
    
    /**
     * Get the list of available classes in game
     * 
     * @return list of classes in game
     */
    public static List<Enum> getListClass(){
        List<Enum> enumValues = new ArrayList<Enum>(EnumSet.allOf(Enum.class));
        return enumValues;                    
    }
}

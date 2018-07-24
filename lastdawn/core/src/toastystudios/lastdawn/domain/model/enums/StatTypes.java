/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */

package toastystudios.lastdawn.domain.model.enums;

/**
 *
 * @author Toasty Studios
 */
public enum StatTypes {
    STRENGTH {
        @Override
        public String toString() {
            return "Strength";
        }
    },
    CONSTITUTION {
        @Override
        public String toString() {
            return "Constitution";
        }
    },
    INTELLIGENCE {
        @Override
        public String toString() {
            return "Intelligence";
        }
    },
    DEXTERITY {
        @Override
        public String toString() {
            return "Dexterity";
        }
    },
    DEFENSE {
        @Override
        public String toString() {
            return "Defense";
        }
    }
    
}

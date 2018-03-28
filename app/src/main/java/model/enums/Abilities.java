/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.enums;

/**
 *
 * @author ruial
 */
public enum Abilities {
    BASIC {
        @Override
        public String toString() {
            return "Basic";
        }
    },
    ABILITY {
        @Override
        public String toString() {
            return "Ability";
        }
    },
    ULTIMATE {
        @Override
        public String toString() {
            return "Ultimate";
        }
    }
}

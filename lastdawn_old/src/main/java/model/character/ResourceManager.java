/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
 
 package model.character;

/**
 *
 * @author Toasty Studios
 */
public class ResourceManager {
    
    private static final int MAX_RESOURCE = 100;
    private static final int GENERATED_PER_ATTACK = 15;
    private static final int ABILITY = 50;
    private static final int ULTIMATE = 100;
    private int current;
 
    /**
     * init resource
     */
    public ResourceManager() {
        this.current = 0;
    }
    
    /**
     * Generates character resources
     * @return 
     */
    public int generateResource() {
        current += GENERATED_PER_ATTACK;

        if (current > MAX_RESOURCE) {
            this.current = MAX_RESOURCE;
        }

        return current;
    }
    
    /**
     * Spends ability resources
     * @return 
     */
    public int spendAbilityResource() {
        if (this.current < ABILITY) {
            return -1;
        }
        
        return this.current -= ABILITY;
    }
    
    /**
     * Spends ultimate ability resources
     * @return 
     */
    public int spendUltimateResources() {
        if (this.current < ULTIMATE) {
            return -1;
        }

        return this.current -= ULTIMATE;
    }
    
    /**
     * Returns current amount of resources
     * @return 
     */
    public int getResources() {
        return this.current;
    }
    
    
    

}

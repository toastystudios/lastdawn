/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.map;

/**
 *
 * @author jpfr8
 */
public class Road {
    
    private double chanceOfEnemy;
    
    public Road(double chanceOfEnemy){
        this.chanceOfEnemy = chanceOfEnemy;
    }
    
    public double getChanceOfEnemy(){
        return this.chanceOfEnemy;
    }  
    
    
}

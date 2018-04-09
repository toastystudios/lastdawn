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
    
    private String name;
    private double chanceOfEnemy;
    
    public Road(String name,double chanceOfEnemy){
        this.name = name;
        this.chanceOfEnemy = chanceOfEnemy;
    }
    
    public Road(String name){
        this.name = name;
        this.chanceOfEnemy = 0;
    }
    
    public String getRoadName(){
        return this.name;
    }
    
    public double getChanceOfEnemy(){
        return this.chanceOfEnemy;
    }  
    
    
}

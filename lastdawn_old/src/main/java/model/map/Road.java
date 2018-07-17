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
    
    public Road(){
        
    }
    
    public double getChanceOfEnemy(){
        return this.chanceOfEnemy;
    }  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.chanceOfEnemy) ^ (Double.doubleToLongBits(this.chanceOfEnemy) >>> 32));
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
        final Road other = (Road) obj;
        if (Double.doubleToLongBits(this.chanceOfEnemy) != Double.doubleToLongBits(other.chanceOfEnemy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Road{" + "chanceOfEnemy=" + chanceOfEnemy + '}';
    }
    
    
    
    
}

/*
 */
package LR_algorithmutils;

import grammar.Production;

/**
 *
 * @author leonardoho
 */
public class ReductionStateNode {
    
    private Production acceptedProduction;
    private int state;
    
    public ReductionStateNode() {}
    
    public ReductionStateNode(Production prod, int state){
        this.acceptedProduction = prod;
        this.state = state;
    }
    
    public void setProduction(Production prod){
        this.acceptedProduction = prod;
    }
    
    public Production getProduction(){
        return this.acceptedProduction;
    }
    
    public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return this.state;
    }
}

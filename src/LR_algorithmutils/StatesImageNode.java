/*
 */
package LR_algorithmutils;

import grammar.Production;

/**
 *
 * @author leonardoho
 */
public class StatesImageNode {
    
    private int state;
    private Production production;
    private int pointerPosition;
    private int goTo;
    private int goToType;
    
    public StatesImageNode(){}
    
    public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return this.state;
    }
    
    public void setProduction(Production prod){
        this.production = prod;
    }
    
    public Production getProduction(){
        return this.production;
    }
    
    public void setPointerPosition(int pointerPos){
        this.pointerPosition = pointerPos;
    }
    
    public int getPointerPosition(){
        return this.pointerPosition;
    }
    
    public void setGoTo(int goTo){
        this.goTo = goTo;
    }
    
    public int getGoTo(){
        return this.goTo;
    }
    
    public void setGoToType(int goToType){
        this.goToType = goToType;
    }
    
    public int getGoToType(){
        return this.goToType;
    }
}

/*
 */
package LR_algorithmutils;

import grammar.Production;

/**
 *
 * @author leonardoho
 */
public class TransitionsQueueNode {

    private Production production;
    private int state;
    private int pointerPosition;

    public TransitionsQueueNode() {
    }

    public TransitionsQueueNode(Production prod, int state, int pointerPosition) {
        this.production = prod;
        this.state = state;
        this.pointerPosition = pointerPosition;
    }

    public void setProduction(Production prod) {
        this.production = prod;
    }

    public Production getProduction() {
        return this.production;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
    
    public void setPointerPosition(int pointerPosition){
        this.pointerPosition = pointerPosition;
    }
    
    public int getPointerPosition(){
        return this.pointerPosition;
    }
    
}

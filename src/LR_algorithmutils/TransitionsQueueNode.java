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

    public TransitionsQueueNode(Production prod, int state, int pointerPos) {
        this.production = prod;
        this.state = state;
        this.pointerPosition = pointerPos;
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

    public int getstate() {
        return this.state;
    }

    public void setPointerPosition(int pointerPos) {
        this.pointerPosition = pointerPos;
    }

    public int getPointerPosition() {
        return this.pointerPosition;
    }
}

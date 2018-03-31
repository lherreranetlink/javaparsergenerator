/*
 */
package LR_algorithmutils;

import grammar.Production;

/**
 *
 * @author leonardoho
 */
public class TransitionsQueueNode {

    private ProductionAtTime production;
    private int state;

    public TransitionsQueueNode() {
    }

    public TransitionsQueueNode(ProductionAtTime prod, int state) {
        this.production = prod;
        this.state = state;
    }

    public void setProduction(ProductionAtTime prod) {
        this.production = prod;
    }

    public ProductionAtTime getProduction() {
        return this.production;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getstate() {
        return this.state;
    }
    
}

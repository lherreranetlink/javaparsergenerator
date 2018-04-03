/*
 */
package LR_algorithmutils;

import grammar.Production;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class MadeTransitionNode {

    private int state;
    private final ProductionAtTimeList productionsAtTime;
    private String symbol;

    public MadeTransitionNode() {
        productionsAtTime = new ProductionAtTimeList();
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    /*public void appendProduction(ProductionAtTimeNode node) {
        productionsAtTime.add(node);
    }*/

    public void appendProduction(Production production, int pointerPos) {
        /*ProductionAtTimeNode node = new ProductionAtTimeNode(production, pointerPos);
        productionsAtTime.add(node);*/
    }

    public Iterator getProductionsAtTimeIterator() {
        return this.productionsAtTime.getIterator();
    }

    public boolean isIdentical(MadeTransitionNode node) {
        /*if (this.symbol.equals(node.getSymbol())) {
            if (this.productionsAtTime.size() == node.productionsAtTime.size()) {
                Iterator iteratorThis = this.getProductionsAtTimeIterator();
                Iterator iteratorAux = node.getProductionsAtTimeIterator();
                while (iteratorThis.hasNext()) {
                    ProductionAtTimeNode thisProductionAtTime = (ProductionAtTimeNode) iteratorThis.next();
                    ProductionAtTimeNode auxProductionAtTime = (ProductionAtTimeNode) iteratorAux.next();
                    if (thisProductionAtTime.getProduction() != auxProductionAtTime.getProduction()
                            || thisProductionAtTime.getPointerPosition() != auxProductionAtTime.getPointerPosition()) {
                        return false;
                    }
                }
                return true;
            }
        }*/
        return false;
    }

    public void printProductionPositions() {
        productionsAtTime.print();
    }

}

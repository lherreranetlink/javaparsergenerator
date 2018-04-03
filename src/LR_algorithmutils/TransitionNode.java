/*
 */
package LR_algorithmutils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class TransitionNode {

    private final ArrayList<Integer> previousStateList;
    private int goTo;
    private final ProductionPointerPositionList productionsPointerPosition;
    private String symbol;

    public TransitionNode() {
        productionsPointerPosition = new ProductionPointerPositionList();
        previousStateList = new ArrayList<>();
    }

    public void setGoTo(int goTo) {
        this.goTo = goTo;
    }

    public int getGoTo() {
        return this.goTo;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void appendPreviousState(int previousState) {
        this.previousStateList.add(previousState);
    }

    public void appendProductionPointerPosition(int productionIndex, int pointerPosition) {
        ProductionPointerPositionNode node = new ProductionPointerPositionNode(productionIndex,
                pointerPosition);
        productionsPointerPosition.add(node);
    }

    public Iterator getProductionListIterator() {
        return this.productionsPointerPosition.getIterator();
    }

    public boolean haveSameData(TransitionNode node) {
        if (this.symbol.equals(node.symbol)) {
            if (this.productionsPointerPosition.size() == node.productionsPointerPosition.size()) {
                Iterator iteratorThis = this.getProductionListIterator();
                Iterator iteratorAux = node.getProductionListIterator();
                while (iteratorThis.hasNext()) {
                    ProductionPointerPositionNode current = (ProductionPointerPositionNode) iteratorThis.next();
                    ProductionPointerPositionNode compare = (ProductionPointerPositionNode) iteratorAux.next();
                    if (current.getProductionIndex() != compare.getProductionIndex()
                            || current.getPointerPosition() != compare.getPointerPosition()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void printPreviousStates() {
        this.previousStateList.forEach((state) -> {
            System.out.println("Prev state: " + state);
        });
    }

    public void printProductionPositions() {
        productionsPointerPosition.print();
    }

}

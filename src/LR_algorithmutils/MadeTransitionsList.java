/*
 */
package LR_algorithmutils;

import genericdatastructures.GenericList;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class MadeTransitionsList extends GenericList {

    public MadeTransitionsList() {
    }

    public MadeTransitionNode getNodeBySymbol(String symbol) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            MadeTransitionNode node = (MadeTransitionNode) iterator.next();
            if (node.getSymbol().equals(symbol)) {
                return node;
            }
        }
        return null;
    }
    
    public void print() {
        this.list.forEach((node) -> {
            MadeTransitionNode aux = (MadeTransitionNode) node;
            System.out.println("Symbol: " + aux.getSymbol());
            System.out.println("State: " + aux.getState());
            aux.printProductionPositions();
            System.out.print("\n");
        });
    }

}

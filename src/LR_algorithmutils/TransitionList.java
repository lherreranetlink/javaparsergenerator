/*
 */
package LR_algorithmutils;

import genericdatastructures.GenericList;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class TransitionList extends GenericList {

    public TransitionList() {
    }

    public TransitionNode getNodeBySymbol(String symbol) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            TransitionNode node = (TransitionNode) iterator.next();
            if (node.getSymbol().equals(symbol)) {
                return node;
            }
        }
        return null;
    }
    
    public void remove(int index){
        list.remove(index);
    }
    
    public void remove(TransitionNode node){
        list.remove(node);
    }
    
    public void print() {
        this.list.forEach((node) -> {
            TransitionNode aux = (TransitionNode) node;
            System.out.println("Symbol: " + aux.getSymbol());
            aux.printPreviousStates();
            System.out.println("Got To: " + aux.getGoTo());
            aux.printProductionPositions();
            System.out.print("\n");
        });
    }

}

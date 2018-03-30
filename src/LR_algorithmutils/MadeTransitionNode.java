/*
 */
package LR_algorithmutils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leonardoho
 */
public class MadeTransitionNode {

    private int state;
    private final Map<Integer, Integer> productionsPointerPositions;
    private String symbol;

    public MadeTransitionNode() {
        productionsPointerPositions = new HashMap<>();
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

    public void appendProduction(int productionIndex, int pointerPos) {
        productionsPointerPositions.put(productionIndex, pointerPos);
    }

    public void printProductionPositions() {
        productionsPointerPositions.entrySet().forEach((entry) -> {
            System.out.println("Production: " + entry.getKey() + " Pointer pos: " + entry.getValue());
        });
    }

}

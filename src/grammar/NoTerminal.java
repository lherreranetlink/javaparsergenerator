/*
 */
package grammar;

import java.util.ArrayList;

/**
 *
 * @author leonardoho
 */
public class NoTerminal extends ProductionComponent{
    
    private final ArrayList<Production> productionsToPoint;
    
    public NoTerminal(String symbol){
        this.symbol = symbol;
        this.type = ProductionComponent.NO_TERMINAL;
        productionsToPoint = new ArrayList<>();
    }
    
    public void appendProductionToPoint(Production prod){
        productionsToPoint.add(prod);
    }
    
    public Production getProductionToPoint(int index){
        return (productionsToPoint.size() > index)? productionsToPoint.get(index): null;
    }
    
    public void printProductionsToPoint(){
        this.productionsToPoint.forEach((production) -> {
            System.out.println("Points to: " + production.getSymbol() + " Address: " + production);
        });
    }
    
    
}

/*
 */
package grammar;

import exceptions.InvalidComponentTypeException;
import java.util.ArrayList;

/**
 *
 * @author leonardoho
 */
public class Production {

    protected int index;
    protected String symbol;

    protected final ArrayList<ProductionComponent> components;

    public Production() {
        components = new ArrayList<>();
    }

    public Production(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
        components = new ArrayList<>();
    }
    
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
    
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void addComponent(String symbol, int type) throws InvalidComponentTypeException {
        switch (type) {
            case ProductionComponent.NO_TERMINAL:
                NoTerminal noTerm = new NoTerminal(symbol);
                components.add(noTerm);
                break;
            case ProductionComponent.TERMINAL:
                Token token = new Token(symbol);
                components.add(token);
                break;
            default:
                throw new InvalidComponentTypeException("Invalid type of rule component");
        }
    }

    public void addComponent(ProductionComponent component) {
        components.add(component);
    }
    
    public ProductionComponent getFirstComponent(){
        return this.components.get(0);
    }
    
    public ProductionComponent getComponent(int index){
        return this.components.get(index);
    }
    
    public void printComponents(){
        components.forEach((component) -> {
            System.out.println("Component: " + component.getSymbol());
            /*if (component.getType() == ProductionComponent.NO_TERMINAL){
                NoTerminal noTerm = (NoTerminal) component;
                noTerm.printProductionsToPoint();
            } */
        });
    }

}

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

    public static final int INITIAL_POINTER_POSITION = 0;
    protected int index;
    protected String symbol;
    protected int pointerPosition;

    protected ArrayList<ProductionComponent> components;

    public Production() {
        components = new ArrayList<>();
        this.index = 0;
        this.pointerPosition = INITIAL_POINTER_POSITION;
    }

    public Production(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
        this.pointerPosition = INITIAL_POINTER_POSITION;
        components = new ArrayList<>();
    }

    public Production(String symbol, int index, int pointerPos) {
        this.symbol = symbol;
        this.index = index;
        this.pointerPosition = pointerPos;
        components = new ArrayList<>();
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setPointerPosition(int pointerPosition) {
        this.pointerPosition = pointerPosition;
    }

    public int getPointerPosition() {
        return this.pointerPosition;
    }

    public void incrementPointerPosition() {
        this.pointerPosition++;
    }

    public ProductionComponent getPointedComponent() {
        return (this.pointerPosition >= this.components.size()) ? null : this.components.get(this.pointerPosition);
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

    public ProductionComponent getFirstComponent() {
        return this.components.get(0);
    }

    public ProductionComponent getComponent(int index) {
        return this.components.get(index);
    }
    
    public void setComponents(ArrayList<ProductionComponent> components){
        this.components = components;
    }
    
    public ArrayList getComponents(){
        return this.components;
    }
    
    public Production getCopy(){
        Production newProduction = new Production();
        
        newProduction.setIndex(this.getIndex());
        newProduction.setPointerPosition(this.getPointerPosition());
        newProduction.setComponents(this.getComponents());
        newProduction.setSymbol(this.getSymbol());
        
        return newProduction;
    }

    public void printComponents() {
        components.forEach((component) -> {
            System.out.println("Component: " + component.getSymbol());
            /*if (component.getType() == ProductionComponent.NO_TERMINAL){
                NoTerminal noTerm = (NoTerminal) component;
                noTerm.printProductionsToPoint();
            } */
        });
    }

}

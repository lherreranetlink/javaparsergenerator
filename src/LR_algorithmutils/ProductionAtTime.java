/*
 */
package LR_algorithmutils;

import grammar.Production;
import grammar.ProductionComponent;

/**
 *
 * @author leonardoho
 */
public class ProductionAtTime extends Production {
   
    public static final int INITIAL_POINTER_POSITION = 0;
    
    protected int pointerPosition;
    
    public ProductionAtTime(){
        this.pointerPosition = INITIAL_POINTER_POSITION;
    }
    
    public ProductionAtTime(String symbol, int index, int pointerPosition){
        this.symbol = symbol;
        this.index = index;
        this.pointerPosition = pointerPosition;
    }
    
    public void setPointerPosition(int pointerPosition){
        this.pointerPosition = pointerPosition;
    }
    
    public int getPointerPosition(){
        return this.pointerPosition;
    }
    
    public void incrementPointerPosition(){
        this.pointerPosition++;
    }
    
    public ProductionComponent getPointedComponent(){
        return this.components.get(this.pointerPosition);
    }
    
}

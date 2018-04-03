/*
 */
package LR_algorithmutils;

/**
 *
 * @author leonardoho
 */
public class ProductionPointerPositionNode {

    private int productionIndex;
    private int pointerPosition;
    
    public ProductionPointerPositionNode(){}
    
    public ProductionPointerPositionNode(int productionIndex, int pointerPos){
        this.productionIndex = productionIndex;
        this.pointerPosition = pointerPos;
    }
    
    public void setProductionIndex(int productionIndex){
        this.productionIndex = productionIndex;
    }
    
    public int getProductionIndex(){
        return this.productionIndex;
    }
    
    public void setPointerPosition(int pointerPos){
        this.pointerPosition = pointerPos;
    }
    
    public int getPointerPosition(){
        return this.pointerPosition;
    }
}

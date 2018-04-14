/*
 */
package grammarutils;

import grammar.ProductionComponent;

/**
 *
 * @author leonardoho
 */
public class FirstSetNode {
    
    private ProductionComponent component;
    
    public FirstSetNode(){}
    
    public FirstSetNode(ProductionComponent component) {
        this.component = component;
    }
    
    public void setComponent(ProductionComponent component) {
        this.component = component;
    }
    
    public ProductionComponent getComponent(){
        return this.component;
    }
    
}

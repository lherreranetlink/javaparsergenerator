/*
 */
package grammar;

/**
 *
 * @author leonardoho
 */
public class ProductionComponent {

    public static final int TERMINAL = 0;
    public static final int NO_TERMINAL = 1;
    public static final int EPSILON = 2;
    
    protected String symbol;
    protected int type;
    
    public ProductionComponent(){}
    
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    
    public String getSymbol(){
        return this.symbol;
    }
    
    public int getType(){
        return this.type;
    }
    
    
}

/*
 */
package grammar;

/**
 *
 * @author leonardoho
 */
public class Token extends ProductionComponent{
    
    public Token(String symbol){
        this.symbol = symbol;
        this.type = ProductionComponent.TERMINAL;
    }
}

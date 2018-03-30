/*
 */
package grammarutils;

import genericdatastructures.GenericList;
import grammar.NoTerminal;

/**
 *
 * @author leonardoho
 */
public class AuxiliarNoTerminalList extends GenericList{
    
    public AuxiliarNoTerminalList(){}
    
    @Override
    public NoTerminal get(int index){
        return (NoTerminal)super.get(index);
    }
    
    public void print(){
        this.list.forEach((component) -> {
            NoTerminal comp = (NoTerminal) component;
            String symbol = comp.getSymbol();
            System.out.println("Component: " + symbol);
        });
    }
    
}

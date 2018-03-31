/*
 */
package grammar;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class Grammar {

    protected ArrayList<Production> productions;

    public Grammar() {
        productions = new ArrayList<>();
    }

    public void addProduction(Production prod) {
        productions.add(prod);
    }

    public void addProduction(int index, Production prod) {
        productions.add(index, prod);
    }

    public Production getProduction(int index) {
        return productions.get(index);
    }

    public Production getInitialProduction() {
        return productions.get(0);
    }

    public int getGrammarSize() {
        return productions.size();
    }
    
    public Iterator getProductionsIterator(){
        return this.productions.iterator();
    }

    public void print() {
        this.productions.forEach((production) -> {
            System.out.println("Production: " + production.getSymbol() + " index: " + production.getIndex());
            production.printComponents();
            System.out.print("\n");
        });
    }
}

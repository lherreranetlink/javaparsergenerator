/*
 */
package grammar;

import java.util.ArrayList;

/**
 *
 * @author leonardoho
 */
public class Grammar {

    public ArrayList<Production> rules;

    public Grammar() {
        rules = new ArrayList<>();
    }

    public void addProduction(Production prod) {
        rules.add(prod);
    }

    public void addProduction(int index, Production prod) {
        rules.add(index, prod);
    }

    public Production getProduction(int index) {
        return rules.get(index);
    }

    public Production getInitialProduction() {
        return rules.get(0);
    }

    public int getGrammarSize() {
        return rules.size();
    }

    public void print() {
        this.rules.forEach((production) -> {
            System.out.println("Rule: " + production.getSymbol() + " index: " + production.getIndex());
            production.printComponents();
            System.out.print("\n");
        });
    }
}

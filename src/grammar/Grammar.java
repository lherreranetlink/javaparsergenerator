/*
 */
package grammar;

import grammarutils.FirstSet;
import grammarutils.TaggedEpsilon;
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

    public Iterator getProductionsIterator() {
        return this.productions.iterator();
    }

    public FirstSet getFirstSet(String symbol) {
        FirstSet firstSet = new FirstSet();
        Iterator productionsIterator = this.getProductionsIterator();

        while (productionsIterator.hasNext()) {

            Production currProduction = (Production) productionsIterator.next();
            if (currProduction.getSymbol().equals(symbol)) {

                ProductionComponent component = currProduction.getFirstComponent();
                switch (component.getType()) {
                    case ProductionComponent.NO_TERMINAL:
                        firstSet.append(this.calculateFirstSetByDepthTravel(component));
                        break;
                    case ProductionComponent.TERMINAL:
                        firstSet.add(component);
                        firstSet.deleteDuplicates(component);
                        break;
                    case ProductionComponent.EPSILON:
                        TaggedEpsilon epsilonInstance = new TaggedEpsilon(currProduction.getSymbol());
                        firstSet.add(epsilonInstance);
                        firstSet.deleteDuplicates(component);
                }
            }
        }

        return firstSet;
    }

    private FirstSet calculateFirstSetByDepthTravel(ProductionComponent start) {
        return null;
    }
    
    private void doDepthTravel(){
        
    }

    public void print() {
        this.productions.forEach((production) -> {
            System.out.println("Production: " + production.getSymbol() + " index: " + production.getIndex());
            production.printComponents();
            System.out.print("\n");
        });
    }
}

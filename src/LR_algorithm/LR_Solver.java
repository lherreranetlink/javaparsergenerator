/*
 */
package LR_algorithm;

import LR_algorithmutils.MadeTransitionNode;
import LR_algorithmutils.MadeTransitionsList;
import LR_algorithmutils.ProductionAtTime;
import LR_algorithmutils.StatesImageList;
import LR_algorithmutils.TransitionsQueue;
import LR_algorithmutils.TransitionsQueueNode;
import grammar.Grammar;
import grammar.NoTerminal;
import grammar.Production;
import grammar.ProductionComponent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class LR_Solver {

    private final Grammar grammar;
    private final StatesImageList statesImageList;
    private final MadeTransitionsList madeTransitionsList;
    private final TransitionsQueue transitionsQueue;
            
    int statesCounter;

    public LR_Solver() {
        grammar = new Grammar();
        statesImageList = new StatesImageList();
        madeTransitionsList = new MadeTransitionsList();
        transitionsQueue = new TransitionsQueue();
        statesCounter = 0;
    }

    public LR_Solver(Grammar grammar) {
        this.grammar = grammar;
        statesImageList = new StatesImageList();
        madeTransitionsList = new MadeTransitionsList();
        transitionsQueue = new TransitionsQueue();
        statesCounter = 0;
    }

    public void do_LR1_Algorithm() {

        this.createArtificialInitialProduction();
        this.initializeTransitionsQueue();

        while (transitionsQueue.size() > 0) {

            TransitionsQueueNode currTransition = transitionsQueue.dequeue();
            ProductionAtTime currProduction = currTransition.getProduction();

            int pointerPosition = currProduction.getPointerPosition();
            ProductionComponent pointedComponent = currProduction.getPointedComponent();

            switch (pointedComponent.getType()) {
                case ProductionComponent.NO_TERMINAL:
                    Grammar auxGrammar = new Grammar();
                    auxGrammar.addProduction(currProduction);
                    this.fillAuxiliarGrammarByDepthTravel(auxGrammar, (NoTerminal) pointedComponent);
                    MadeTransitionsList auxTransitionsList =  this.createAuxiliarTransitionsList(auxGrammar);
                    this.mergeAuxiliarAndFinalTransitionsLists(auxTransitionsList);
                    break;
                /*case ProductionComponent.TERMINAL:
                    break;
                case ProductionComponent.EPSILON:*/
            }
        }
    }

    private void createArtificialInitialProduction() {
        /*Create an Atificial Initial Production like E' -> E*/
        Production initialProduction = grammar.getInitialProduction();
        Production prod = new Production("Initial", 0);
        grammar.addProduction(0, prod);
        String componentSymbol = initialProduction.getComponent(0).getSymbol();
        NoTerminal noTerm = new NoTerminal(componentSymbol);
        prod.addComponent(noTerm);
        
        Iterator productionsIterator = grammar.getProductionsIterator();
        
        while (productionsIterator.hasNext()){
            Production production = (Production) productionsIterator.next();
            if (noTerm.getSymbol().equals(production.getSymbol())){
                noTerm.appendProductionToPoint(production);
            }
        }
    }

    private void initializeTransitionsQueue() {
        /*Put first production in the transitions Queue*/
        ProductionAtTime initialProduction = (ProductionAtTime) grammar.getInitialProduction();
        initialProduction.setPointerPosition(ProductionAtTime.INITIAL_POINTER_POSITION);
        TransitionsQueueNode node = new TransitionsQueueNode(initialProduction, 0);
        transitionsQueue.queue(node);
    }

    private void fillAuxiliarGrammarByDepthTravel(Grammar grammar, NoTerminal initialComponent) {
        ArrayList<Production> visitedProductions = new ArrayList<>();
        this.doDepthTravel(grammar, initialComponent, visitedProductions);
    }

    private void doDepthTravel(Grammar grammar, NoTerminal component,
            ArrayList<Production> visitedProductions) {
        /*Do a depth travel thruogh the grammar graph to obtain Epsilon Lock*/

        int productionsCount = 0;
        ProductionAtTime nextProduction;
        while ((nextProduction = (ProductionAtTime) component.getProductionToPoint(productionsCount++)) != null) {
            
            if (!visitedProductions.contains(nextProduction)) {
                visitedProductions.add(nextProduction);
                grammar.addProduction(nextProduction);
                
                ProductionComponent firstComponent = nextProduction.getFirstComponent();
                
                if (firstComponent.getType() == ProductionComponent.NO_TERMINAL) {
                    this.doDepthTravel(grammar, (NoTerminal) firstComponent, visitedProductions);
                }
            }
            
        }
    }
    
    private MadeTransitionsList createAuxiliarTransitionsList(Grammar auxGrammar){
        
        MadeTransitionsList auxList = new MadeTransitionsList();
        
        Iterator productionsIterator = auxGrammar.getProductionsIterator();
        while (productionsIterator.hasNext()){
            
            ProductionAtTime currProduction = (ProductionAtTime) productionsIterator.next();
            ProductionComponent pointedComponent = currProduction.getPointedComponent();
            
            MadeTransitionNode node;
            if ((node = auxList.getNodeBySymbol(pointedComponent.getSymbol())) == null){
                node = new MadeTransitionNode();
                node.setSymbol(pointedComponent.getSymbol());
                node.setState(this.statesCounter);
                auxList.add(node);
            }
            node.appendProduction(currProduction, 0);
        }
        
        return auxList;
    }
    
    private void mergeAuxiliarAndFinalTransitionsLists(MadeTransitionsList auxList){
        
        /*if (this.madeTransitionsList.size() == 0){
            Iterator iterator = auxList.getIterator();
            while (iterator.hasNext()){
                MadeTransitionNode transition = (MadeTransitionNode) iterator.next();
                transition.setState(++statesCounter);
                this.madeTransitionsList.add(transition);
            }
        } else{
            boolean identicalTransition = false;
            Iterator auxListIterator = auxList.getIterator();
            while(auxListIterator.hasNext()){
                MadeTransitionNode auxListNode = (MadeTransitionNode) auxListIterator.next();
                
                Iterator transitionListIterator = this.madeTransitionsList.getIterator();
                while (transitionListIterator.hasNext()){
                    MadeTransitionNode transitionsListNode = (MadeTransitionNode) transitionListIterator.next();
                    
                    if (transitionsListNode.isIdentical(auxListNode)){
                        identicalTransition = true;
                        break;
                    } else {
                        
                    }
                }
                if (identicalTransition){
                    
                }
            }
        }*/
    }
}

/*
 */
package LR_algorithm;

import LR_algorithmutils.MadeTransitionNode;
import LR_algorithmutils.MadeTransitionsList;
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
            Production currProduction = currTransition.getProduction();

            int pointerPosition = currTransition.getPointerPosition();
            ProductionComponent pointedComponent = currProduction.getComponent(pointerPosition);

            switch (pointedComponent.getType()) {
                case ProductionComponent.NO_TERMINAL:
                    Grammar auxGrammar = new Grammar();
                    auxGrammar.addProduction(currProduction);
                    this.fillAuxiliarGrammarByDepthTravel(auxGrammar, (NoTerminal) pointedComponent);
                    MadeTransitionsList auxTransitionsList =  this.createAuxiliarTransitionsList(auxGrammar, pointerPosition);
                    this.mergeAuxiliarAndFinalTransitionsLists(auxTransitionsList);
                    break;
                case ProductionComponent.TERMINAL:
                    break;
                case ProductionComponent.EPSILON:
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
        grammar.rules.forEach((production) -> {
            if (production.getSymbol().equals(componentSymbol)) {
                noTerm.appendProductionToPoint(production);
            }
        });
    }

    private void initializeTransitionsQueue() {
        /*Put first production in the transitions Queue*/
        Production initialProduction = grammar.getInitialProduction();
        TransitionsQueueNode node = new TransitionsQueueNode(initialProduction, 0, 0);
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
        Production nextProduction;
        while ((nextProduction = component.getProductionToPoint(productionsCount++)) != null) {
            
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
    
    private MadeTransitionsList createAuxiliarTransitionsList(Grammar auxGrammar, int pointerPosition){
        
        MadeTransitionsList auxList = new MadeTransitionsList();
        MadeTransitionNode firstNode = new MadeTransitionNode();
        
        Production firstProduction = auxGrammar.getInitialProduction();
        ProductionComponent firstPointedComponent = firstProduction.getComponent(pointerPosition);
        
        firstNode.setSymbol(firstPointedComponent.getSymbol());
        firstNode.setState(999);
        firstNode.appendProduction(firstProduction.getIndex(), pointerPosition);
        auxList.add(firstNode);
        
        for (int i = 1; i < auxGrammar.getGrammarSize(); i++){
            
            Production currProduction = auxGrammar.getProduction(i);
            ProductionComponent currFirstComponent = currProduction.getFirstComponent();
            
            MadeTransitionNode node;
            if ((node = auxList.getNodeBySymbol(currFirstComponent.getSymbol())) == null){
                node = new MadeTransitionNode();
                node.setSymbol(currFirstComponent.getSymbol());
                node.setState(999);
                auxList.add(node);
            }
            node.appendProduction(currProduction.getIndex(), 0);
        }
        return auxList;
    }
    
    private void mergeAuxiliarAndFinalTransitionsLists(MadeTransitionsList auxList){
        
        if (this.madeTransitionsList.size() == 0){
            Iterator iterator = auxList.getIterator();
            while (iterator.hasNext()){
                MadeTransitionNode transition = (MadeTransitionNode) iterator.next();
                transition.setState(statesCounter++);
                this.madeTransitionsList.add(transition);
            }
        } else{
            Iterator auxListIterator = auxList.getIterator();
            while(auxListIterator.hasNext()){
                MadeTransitionNode auxListNode = (MadeTransitionNode) auxListIterator.next();
                
                Iterator transitionListIterator = this.madeTransitionsList.getIterator();
                while (transitionListIterator.hasNext()){
                    MadeTransitionNode transitionsListNode = (MadeTransitionNode) transitionListIterator.next();
                }
            }
        }
    }
}

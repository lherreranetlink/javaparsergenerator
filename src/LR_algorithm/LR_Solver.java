/*
 */
package LR_algorithm;

import LR_algorithmutils.ProductionPointerPositionNode;
import LR_algorithmutils.ReductionStateNode;
import LR_algorithmutils.TransitionNode;
import LR_algorithmutils.TransitionList;
import LR_algorithmutils.ReductionStatesList;
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

    public static final int INITIAL_STATE = 0;
    private final Grammar grammar;
    private final TransitionList madeTransitionsList;
    private final TransitionsQueue transitionsQueue;
    private final ReductionStatesList reductionStates;

    int currentState;
    int statesCounter;

    public LR_Solver() {
        grammar = new Grammar();
        madeTransitionsList = new TransitionList();
        transitionsQueue = new TransitionsQueue();
        reductionStates = new ReductionStatesList();
        currentState = 0;
        statesCounter = 0;
    }

    public LR_Solver(Grammar grammar) {
        this.grammar = grammar;
        madeTransitionsList = new TransitionList();
        transitionsQueue = new TransitionsQueue();
        reductionStates = new ReductionStatesList();
        currentState = 0;
        statesCounter = 0;
    }

    public void do_LR1_Algorithm() {

        this.createArtificialInitialProduction();
        this.initializeTransitionsQueue();

        while (transitionsQueue.size() > 0) {

            TransitionsQueueNode currTransition = transitionsQueue.dequeue();
            this.currentState = currTransition.getState();
            Production currProduction = currTransition.getProduction();
            currProduction.setPointerPosition(currTransition.getPointerPosition());
            ProductionComponent pointedComponent = currProduction.getPointedComponent();
            TransitionList auxTransitionsList;

            if (pointedComponent != null && pointedComponent.getType() != ProductionComponent.EPSILON) {
                switch (pointedComponent.getType()) {
                    case ProductionComponent.NO_TERMINAL:
                        Grammar auxGrammar = new Grammar();
                        auxGrammar.addProduction(currProduction.getCopy());
                        this.fillAuxiliarGrammarByDepthTravel(auxGrammar, (NoTerminal) pointedComponent);
                        auxTransitionsList = this.createAuxiliarTransitionsList(auxGrammar);
                        this.mergeAuxiliarAndFinalTransitionsLists(auxTransitionsList);
                        this.createNewStates(auxTransitionsList);
                        break;
                    case ProductionComponent.TERMINAL:
                        auxTransitionsList = new TransitionList();
                        TransitionNode node = new TransitionNode();
                        
                        node.setSymbol(pointedComponent.getSymbol());
                        node.appendPreviousState(this.currentState);
                        node.appendProductionPointerPosition(currProduction.getIndex(), currProduction.getPointerPosition());
                        auxTransitionsList.add(node);
                        
                        this.mergeAuxiliarAndFinalTransitionsLists(auxTransitionsList);
                        this.createNewStates(auxTransitionsList);
                }
            } else {
                ReductionStateNode reductionNode = new ReductionStateNode(currProduction, this.currentState);
                reductionStates.add(reductionNode);
            }
        }
    }

    private void createArtificialInitialProduction() {
        /*Create an Atificial Initial Production like E' -> E*/
        Production initialProduction = grammar.getInitialProduction();
        Production prod = new Production("Initial", 0);
        grammar.addProduction(0, prod);
        String componentSymbol = initialProduction.getSymbol();
        NoTerminal noTerm = new NoTerminal(componentSymbol);
        prod.addComponent(noTerm);

        Iterator productionsIterator = grammar.getProductionsIterator();

        while (productionsIterator.hasNext()) {
            Production production = (Production) productionsIterator.next();
            if (noTerm.getSymbol().equals(production.getSymbol())) {
                noTerm.appendProductionToPoint(production);
            }
        }
    }

    private void initializeTransitionsQueue() {
        /*Put first production in the transitions Queue*/
        Production initialProduction = (Production) grammar.getInitialProduction();
        TransitionsQueueNode node = new TransitionsQueueNode(initialProduction, INITIAL_STATE,
                Production.INITIAL_POINTER_POSITION);
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
                nextProduction.setPointerPosition(Production.INITIAL_POINTER_POSITION);
                visitedProductions.add(nextProduction);
                grammar.addProduction(nextProduction.getCopy());

                ProductionComponent firstComponent = nextProduction.getFirstComponent();

                if (firstComponent.getType() == ProductionComponent.NO_TERMINAL) {
                    this.doDepthTravel(grammar, (NoTerminal) firstComponent, visitedProductions);
                }
            }

        }
    }

    private TransitionList createAuxiliarTransitionsList(Grammar auxGrammar) {

        TransitionList auxList = new TransitionList();

        Iterator productionsIterator = auxGrammar.getProductionsIterator();
        while (productionsIterator.hasNext()) {

            Production currProduction = (Production) productionsIterator.next();
            ProductionComponent pointedComponent = currProduction.getPointedComponent();

            TransitionNode node;
            if ((node = auxList.getNodeBySymbol(pointedComponent.getSymbol())) == null) {
                node = new TransitionNode();
                node.setSymbol(pointedComponent.getSymbol());
                node.appendPreviousState(this.currentState);
                auxList.add(node);
            }
            node.appendProductionPointerPosition(currProduction.getIndex(), currProduction.getPointerPosition());
        }

        return auxList;
    }

    private void mergeAuxiliarAndFinalTransitionsLists(TransitionList auxList) {

        boolean identicalTransition = false;

        Iterator auxListIterator = auxList.getIterator();
        while (auxListIterator.hasNext()) {
            TransitionNode auxListNode = (TransitionNode) auxListIterator.next();

            Iterator transitionListIterator = this.madeTransitionsList.getIterator();
            while (transitionListIterator.hasNext()) {
                TransitionNode transitionsListNode = (TransitionNode) transitionListIterator.next();

                if (transitionsListNode.haveSameData(auxListNode)) {
                    transitionsListNode.appendPreviousState(currentState);
                    auxListIterator.remove(/*auxListNode*/);
                    identicalTransition = true;
                    break;
                }
            }
            if (!identicalTransition) {
                auxListNode.setGoTo(++this.statesCounter);
                this.madeTransitionsList.add(auxListNode);
            }
        }
    }

    private void createNewStates(TransitionList auxTransitionsList) {

        Iterator auxListIterator = auxTransitionsList.getIterator();
        while (auxListIterator.hasNext()) {
            TransitionNode currentTransition = (TransitionNode) auxListIterator.next();

            Iterator productionsIterator = currentTransition.getProductionListIterator();
            while (productionsIterator.hasNext()) {
                ProductionPointerPositionNode prod = (ProductionPointerPositionNode) productionsIterator.next();
                Production productionTransition = this.grammar.getProduction(prod.getProductionIndex());
                TransitionsQueueNode newTransition = new TransitionsQueueNode(productionTransition,
                        currentTransition.getGoTo(), prod.getPointerPosition() + 1);
                transitionsQueue.add(newTransition);
            }
        }
    }

}

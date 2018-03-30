/*
 */
package LR_algorithmutils;

import genericdatastructures.GenericList;
import grammar.Production;

/**
 *
 * @author leonardoho
 */
public class TransitionsQueue extends GenericList{
    
    public TransitionsQueue(){}
    
    public void queue(TransitionsQueueNode node){
        super.add(node);
    }
    
    public void queue(Production prod, int state, int pointerPos){
        TransitionsQueueNode newNode = new TransitionsQueueNode(prod, state, pointerPos);
        super.add(newNode);
    }
    
    public TransitionsQueueNode dequeue(){
        TransitionsQueueNode node = (TransitionsQueueNode) super.get(0);
        list.remove(0);
        return node;
    }
    
}

/*
 */
package LR_algorithmutils;

import genericdatastructures.GenericList;

/**
 *
 * @author leonardoho
 */
public class TransitionsQueue extends GenericList{
    
    public TransitionsQueue(){}
    
    public void queue(TransitionsQueueNode node){
        super.add(node);
    }
    
    public void queue(ProductionAtTime prod, int state){
        TransitionsQueueNode newNode = new TransitionsQueueNode(prod, state);
        super.add(newNode);
    }
    
    public TransitionsQueueNode dequeue(){
        TransitionsQueueNode node = (TransitionsQueueNode) super.get(0);
        list.remove(0);
        return node;
    }
    
}

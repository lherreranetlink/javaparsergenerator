/*
 */
package LR_algorithmutils;

import genericdatastructures.GenericList;

/**
 *
 * @author leonardoho
 */
public class ReductionStatesList extends GenericList{
    
    public ReductionStatesList(){}
    
    public void print(){
        this.list.forEach((node) -> {
            ReductionStateNode state = (ReductionStateNode) node;
            System.out.println("Production Index: " + state.getProduction().getIndex());
            System.out.println("Acceptation State: " + state.getState());
            System.out.print("\n");
        });
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LR_algorithmutils;

import genericdatastructures.GenericList;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class ProductionPointerPositionList extends GenericList{
    
    public ProductionPointerPositionList(){}
    
    public void print(){
        Iterator iterator = this.getIterator();
        while(iterator.hasNext()){
            ProductionPointerPositionNode node = (ProductionPointerPositionNode) iterator.next();
            System.out.println("Production: " + node.getProductionIndex());
            System.out.println("Pointer position: " + node.getPointerPosition());
        }
    }
    
}

/*
 */
package genericdatastructures;

import java.util.ArrayList;

/**
 *
 * @author leonardoho
 */
public class GenericList {
    
    protected ArrayList<Object> list;
    
    public GenericList(){
        list = new ArrayList<>();
    }
    
    public Object get(int index){
        return list.get(index);
    }
    
    public void add(Object obj){
        list.add(obj);
    }
    
    public int size(){
        return list.size();
    }
    
}

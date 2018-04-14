/*
 */
package grammarutils;

import genericdatastructures.GenericList;
import grammar.ProductionComponent;
import java.util.Iterator;

/**
 *
 * @author leonardoho
 */
public class FirstSet extends GenericList {

    public FirstSet() {
    }

    public void deleteDuplicates(ProductionComponent toSearch) {
        Iterator iterator = this.getIterator();
        
        while (iterator.hasNext()){
            ProductionComponent component = (ProductionComponent) iterator.next();
            if (component == toSearch){
                iterator.remove();
                break;
            }
        }
    }

    public void append(FirstSet set) {
        Iterator iterator = this.getIterator();

        while (iterator.hasNext()) {
            ProductionComponent component = (ProductionComponent) iterator.next();
            this.add(component);
        }
    }

}

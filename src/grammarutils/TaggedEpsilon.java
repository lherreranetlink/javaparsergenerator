/*
 */
package grammarutils;

import grammar.Epsilon;

/**
 *
 * @author leonardoho
 */
public class TaggedEpsilon extends Epsilon{
    
    private String tag;
    
    public TaggedEpsilon(){
        super();
    }
    
    public TaggedEpsilon(String tag){
        super();
        this.tag = tag;
    }
    
    public void setTag(String tag){
        this.tag = tag;
    }
    
    public String getTag(){
        return this.tag;
    }
        
}

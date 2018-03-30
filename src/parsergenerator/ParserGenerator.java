/*
 */
package parsergenerator;

import LR_algorithm.LR_Solver;
import grammar.Grammar;
import grammar.GrammarBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author leonardoho
 */
public class ParserGenerator {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GrammarBuilder builder = new GrammarBuilder("example");
            Grammar grammar = builder.buildGrammar();
            LR_Solver lr_solver = new LR_Solver (grammar);
            lr_solver.do_LR1_Algorithm();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParserGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParserGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

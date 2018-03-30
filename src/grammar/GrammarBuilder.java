/*
 */
package grammar;

import exceptions.InvalidComponentTypeException;
import fileutils.FileUtils;
import grammarutils.AuxiliarNoTerminalList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author leonardoho
 */
public class GrammarBuilder {

    private static final int INITIAL = 0;
    private static final int CAPTURE_RULE = 1;
    private static final int WAITING_COMPONENT = 2;
    private static final int BEGIN_CAPTURE_COMPONENT = 3;
    private static final int CAPTURE_TERMINAL = 4;
    private static final int CAPTURE_NO_TERMINAL = 5;
    private static final int END_CAPTURE_COMPONENT = 6;
    private static final int END_CAPTURE_GRAMMAR = 7;
    
    private final Grammar grammar;
    private final AuxiliarNoTerminalList auxList;
    private final FileInputStream inputStream;

    public GrammarBuilder(String filename) throws FileNotFoundException {
        inputStream = new FileInputStream(filename);
        grammar = new Grammar();
        auxList = new AuxiliarNoTerminalList();
    }

    @SuppressWarnings("empty-statement")
    public Grammar buildGrammar() throws IOException {
        /*Make an state machine as a simple parser for read the rules from a file*/
        /*Make a Graph With all no terminals pointed to productions that have same name*/
        String symbol = "";
        Production currentRule = null;
        int state = INITIAL, rulesCount = 0;
        char c;
        boolean continueRead = true;
        while (continueRead) {

            if ((state != CAPTURE_TERMINAL) && (state != END_CAPTURE_COMPONENT)) {
                while (FileUtils.isSpace(c = FileUtils.getNextChar(inputStream)));
            } else {
                c = FileUtils.getNextChar(inputStream);
            }

            switch (state) {
                case INITIAL:
                    if (c == '<') {
                        state = CAPTURE_RULE;
                        break;
                    } else if ((int) c == FileUtils.EOF_FLAG) {
                        state = END_CAPTURE_GRAMMAR;
                    }
                    continueRead = false;
                    break;
                case CAPTURE_RULE:
                    if (FileUtils.isAlphanumeric((char) c)) {
                        symbol = symbol.concat(String.valueOf((char) c));
                        break;
                    } else if (c == '>') {
                        currentRule = new Production(symbol, ++rulesCount);
                        this.grammar.addProduction(currentRule);
                        symbol = "";
                        state = WAITING_COMPONENT;
                        break;
                    }
                    continueRead = false;
                    break;
                case WAITING_COMPONENT:
                    if (c == '=') {
                        state = BEGIN_CAPTURE_COMPONENT;
                        break;
                    }
                    continueRead = false;
                    break;
                case BEGIN_CAPTURE_COMPONENT:
                    if (c == '<') {
                        state = CAPTURE_NO_TERMINAL;
                    } else {
                        state = CAPTURE_TERMINAL;
                        FileUtils.unGetChar(c);
                    }
                    break;
                case CAPTURE_TERMINAL:
                    for (; c == ' ' && symbol.equals(""); c = FileUtils.getNextChar(inputStream));
                    if (FileUtils.isSpace(c)) {
                        state = END_CAPTURE_COMPONENT;
                        this.appendTerminalOrEpsilon(symbol, currentRule);
                        symbol = "";
                        if (c == '\n') {
                            FileUtils.unGetChar(c);
                        }
                    } else if ((int) c == FileUtils.EOF_FLAG) {
                        state = END_CAPTURE_COMPONENT;
                    } else {
                        symbol = symbol.concat(String.valueOf(c));
                    }
                    break;
                case CAPTURE_NO_TERMINAL:
                    if (FileUtils.isAlphanumeric(c)) {
                        symbol = symbol.concat(String.valueOf(c));
                        break;
                    } else if (c == '>') {
                        this.appendNoTerminal(symbol, currentRule);
                        state = END_CAPTURE_COMPONENT;
                        symbol = "";
                        break;
                    }
                    continueRead = false;
                    break;
                case END_CAPTURE_COMPONENT:
                    for (; c == ' ' && symbol.equals(""); c = FileUtils.getNextChar(inputStream));

                    if ((int) c == FileUtils.EOF_FLAG) {
                        state = END_CAPTURE_GRAMMAR;
                        continueRead = false;
                    } else {
                        switch (c) {
                            case '\r':
                            case '\n':
                                state = INITIAL;
                                break;
                            case '<':
                                state = CAPTURE_NO_TERMINAL;
                                break;
                            default:
                                state = CAPTURE_TERMINAL;
                                FileUtils.unGetChar(c);
                        }
                    }
                    break;
            }
        }
        if (state != END_CAPTURE_GRAMMAR) {
            System.out.println("Invalid Input");
            System.exit(1);
        }
        joinNoTerminalsWithProductions();
        return this.grammar;
    }

    private void appendTerminalOrEpsilon(String symbol, Production rule) {
        if (symbol.equals("\\e")) {
            Epsilon epsilon = new Epsilon();
            rule.addComponent(epsilon);
        } else {
            try {
                rule.addComponent(symbol, ProductionComponent.TERMINAL);
            } catch (InvalidComponentTypeException ex) {
                Logger.getLogger(GrammarBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void appendNoTerminal(String symbol, Production rule) {
        NoTerminal noTerm = new NoTerminal(symbol);
        rule.addComponent(noTerm);
        this.auxList.add(noTerm);
    }
    
    private void joinNoTerminalsWithProductions(){
        for (int i = 0; i < this.grammar.getGrammarSize(); i++){
            Production prod = this.grammar.getProduction(i);
            this.joinNoTerminalsWithSpecificProduction(prod);
        }
    }
    
    private void joinNoTerminalsWithSpecificProduction(Production prod){
        for (int i = 0; i < this.auxList.size(); i++){
            NoTerminal noTerm = auxList.get(i);
            if (noTerm.symbol.equals(prod.getSymbol())){
                noTerm.appendProductionToPoint(prod);
            }
        }
    }

}

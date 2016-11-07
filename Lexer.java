import java.io.*; 
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';

    Hashtable<String,Word> words = new Hashtable<String,Word>();
    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    Hashtable<String,Number> numbers = new Hashtable<String,Number>();
    void reserve(Number n) {
        numbers.put(n.lexeme, n);
    }
    
    /**
     * Costruttore che gestisce le altre parole chiave.
     */
    public Lexer() {
        reserve( new Word(Tag.VAR, "var"));
        reserve( new Word(Tag.PRINT, "print"));
        reserve( new Word(Tag.EOF, "$"));
        reserve( new Word(Tag.BOOLEAN, "boolean"));
        reserve( new Word(Tag.ASSIGN, ":="));
        reserve( new Word(Tag.EQ, "=="));
        reserve( new Word(Tag.TRUE, "true"));
        reserve( new Word(Tag.FALSE, "false"));
        reserve( new Word(Tag.NOT, "!"));
        reserve( new Word(Tag.INTEGER, "integer"));
        reserve( new Word(Tag.LE, "<="));
        reserve( new Word(Tag.GE, ">="));
    }
    
    /**
     * Si occupa della lettura da tastiera, della verifica che il valore inserito faccia parte dal carattere 0 al 255 del codice ANSI;
     */
    private void readch() {
        try {
            peek = (char) System.in.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    /**
     *
     */
    public Token lexical_scan() {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++; //Questo non andrebbe cambiato?
            readch();
        }
        
        switch (peek) {
            case ',':
                peek = ' ';
                return Token.comma;
            case ';':
            	peek = ' ';
            	return Token.semicolon;
                case '(':
            	peek = ' ';
            	return Token.lpar;
            case ')':
            	peek = ' ';
            	return Token.rpar;
            case '+':
            	peek = ' ';
            	return Token.plus;
            case '-':
            	peek = ' ';
            	return Token.minus;
            case '*':
            	peek = ' ';
            	return Token.mult;
            case '/':
            	peek = ' ';
            	return Token.div;
            case '&':
                readch();
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '|':
            	readch();
            	if (peek == '|'){
            		peek = ' ';
            		return Word.and;
            	} else {
            		System.err.println("Erroneous character" + " after | : " + peek);
            		return null;
            	}
            case '=':
            	readch();
            	if (peek == '='){
            		peek = ' ';
            		return Word.eq;
            	} else {
            		System.err.println("Erroneous character" + " after = : " + peek);
            		return null;
            	}
            case '<':
            	readch();
            	if (peek == '='){
            		peek = ' ';
            		return Word.le;
            	} else if (peek == '>'){
            			peek = ' ';
            			return Word.ne;
            		}
            		else{
            			peek = ' ';
            			return Token.lt;
            		}
            case '>':
            	readch();
            	if (peek == '='){
            		peek = ' ';
            		return Word.ne;
            	} else {
            		peek = ' ';
            		return Token.gt;
            	}
            case ':':
            	readch();
            	if (peek == '='){
            		peek = ' ';
            		return Word.assign;
            	} else {
            		peek = ' ';
            		return Token.colon;
            	}
            default:
                if (Character.isLetter(peek) || peek == '_') {
                    String s = "";
                    do {
                        s += peek;
                        readch();
                    } while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_');
                    
                    if ((Word)words.get(s) != null)
                        return (Word)words.get(s);
                    else {
                        if (Dfa.identificatori(s) == true) {
                            Word w = new Word(Tag.ID, s);
                            words.put(s, w);
                            return w;
                        }
                        else {
                            System.err.println("Erroneous character: " + s);
                            return null;
                        }
                    }
                } 
                else { 
	               	if (Character.isDigit(peek)) {                      //Gestione casi numerici
                		String s = "";
                        boolean flag = true;
                		do {
                			s += peek;
                			if (!Character.isDigit(peek))
                                flag = false;
                            readch();
                		} while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_');

                        if (flag) {
                	       	if ((Number)numbers.get(s) != null)
                                return (Number)numbers.get(s);
                            else {
                                Number n = new Number(Tag.NUM, s);
                                numbers.put(s, n);
                                return n;
                            }
                        }
                        else {
                            System.err.println("Erroneous character: " + s);
                            return null;
                        }
                	}

                    if (peek == '$') {
                        return new Token(Tag.EOF);
                    } else {
                        System.err.println("Erroneous character: " + peek );
                        return null;
                    }
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        
        Token tok;
        do {
            tok = lex.lexical_scan();
            System.out.println("Scan: " + tok);
        } while (tok.tag != Tag.EOF);
    }

}
/*Il Lexer è aggiornato fino a 2.1. 2.2 è da fare*/
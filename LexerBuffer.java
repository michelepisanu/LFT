import java.io.*; 
import java.util.*;

public class LexerBuffer {

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
    public LexerBuffer() {
        reserve( new Word(Tag.VAR, "var"));
        reserve( new Word(Tag.PRINT, "print"));
        reserve( new Word(Tag.EOF, "$"));
        reserve( new Word(Tag.BOOLEAN, "boolean"));
        reserve( new Word(Tag.INTEGER, "integer"));
        reserve( new Word(Tag.ASSIGN, ":="));
        reserve( new Word(Tag.EQ, "=="));
        reserve( new Word(Tag.TRUE, "true"));
        reserve( new Word(Tag.FALSE, "false"));
        reserve( new Word(Tag.IF, "if"));
        reserve( new Word(Tag.ELSE, "else"));
        reserve( new Word(Tag.DO, "do"));
        reserve( new Word(Tag.WHILE, "while"));
        reserve( new Word(Tag.BEGIN, "begin"));
        reserve( new Word(Tag.END, "end"));
        reserve( new Word(Tag.THEN, "then"));
    }
    
    /**
     * Si occupa della lettura da tastiera, della verifica che il valore inserito faccia parte dal carattere 0 al 255 del codice ANSI;
     */
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    /**
     *
     */
    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++; //Questo non andrebbe cambiato?
            readch(br);
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
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '|':
            	readch(br);
            	if (peek == '|'){
            		peek = ' ';
            		return Word.and;
            	} else {
            		System.err.println("Erroneous character" + " after | : " + peek);
            		return null;
            	}
            case '=':
            	readch(br);
            	if (peek == '='){
            		peek = ' ';
            		return Word.eq;
            	} else {
            		System.err.println("Erroneous character" + " after = : " + peek);
            		return null;
            	}
            case '<':
            	readch(br);
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
            	readch(br);
            	if (peek == '='){
            		peek = ' ';
            		return Word.ne;
            	} else {
            		peek = ' ';
            		return Token.gt;
            	}
            case ':':
            	readch(br);
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
                        readch(br);
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
                            readch(br);
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

                    if (peek == (char)-1) {
                        return new Token(Tag.EOF);
                    } else {
                        System.err.println("Erroneous character: " + peek );
                        return null;
                    }
                }
         }
    }
		
    public static void main(String[] args) {
        LexerBuffer lex = new LexerBuffer();
        
        String path = "prova.txt";
        try{
        	BufferedReader br = new BufferedReader(new FileReader(path));
        	Token tok;
        	
        	do {
            tok = lex.lexical_scan(br);
            System.out.println("Scan: " + tok);
        	} while (tok.tag != Tag.EOF);
        	br.close();
    	   
    	   } catch (IOException e) {e.printStackTrace();}

	}
}
/*Il Lexer è aggiornato fino a 2.1. 2.2 è da fare*/

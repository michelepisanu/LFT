import java.io.*;
public class Parser{
 
    private Lexer lex; 
    private Token look;
 
    public Parser(Lexer l) { 
        lex = l; 
        move(); 
    }
     
     
    /******************* MOVE **************************/
    void move(){ 
        look = lex.lexical_scan(); 
        System.err.println("token = " + look); 
    }
     
     
    /******************* ERROR **************************/
    void error(String s) { 
        throw new Error("near line " + lex.line + ": " + s); 
    }
     
     
    /******************* MATCH **************************/
    void match(int t) { 
        if (look.tag == t) { 
            if (look.tag != Tag.EOF) 
                move(); 
        } else error("syntax error"); 
    }
     
     
    /******************* START **************************/
    public void start() { // la procedura start puo‘ essere estesa (opzionale) 
        System.out.println("Start");
        switch(look.tag){
        case '(':
        case Tag.NUM:
            expr();
            break;
        default:
            error("Error in start");
        }
        match(Tag.EOF);
         
    }
     
     
    /******************* EXPR **************************/
    private void expr() { // la procedura expr puo‘ essere estesa (opzionale) 
        switch(look.tag){
            case '(':
            case Tag.NUM:
                term(); 
                exprp();
            break;
        default:
            error("Errore in expr");
        }
          
    }
     
     
    /******************* EXPRP **************************/
    private void exprp() { 
        switch (look.tag) { 
            case '+': 
                match('+'); 
                term(); 
                exprp(); 
            break;
             
            case '-': 
                match('-');
                term();
                exprp();
            break;
             
            case ')':
            case Tag.EOF:
            break;
            default:
            error("Errore in exprp");
            // ... gestire gli altri casi ... // 
        }
    }
 
     
    /******************* TERM **************************/
    private void term() { 
        switch(look.tag){
            case '(':
            case Tag.NUM:
                fact();
                termp();
            break;
        default:
            error("Error in start");
        }        
    }
     
     
    /******************* TERMP **************************/
    private void termp() { 
        switch (look.tag) { 
            case '*': 
                match('*'); 
                fact(); 
                termp(); 
            break;
 
            case '/':
                match('/');
                fact();
                termp();
            break;
             
            case '+':
            case '-':
            case Tag.EOF:
            case ')':
            break;
            default:
                error("Errore in termp");
    // ... gestire gli altri casi ... //
        }
    }
     
     
    /******************* FACT **************************/
    private void fact() { 
        switch (look.tag) { 
        // ... gestire tutti i casi ... // 
 
            case '(':
                match('(');
                expr();
                match(')');
            break;
            case Tag.NUM:
                match(Tag.NUM);
            break;
 
            default:
                error("Errore in fact()");
        } 
 
        //match(Tag.NUM);
    }
     
     
    /******************* MAIN **************************/
    public static void main(String[] args){ 
        System.out.print("Inserisci la stringa > ");
        Lexer lex = new Lexer(); 
        Parser parser = new Parser(lex); 
        parser.start();
    }
}

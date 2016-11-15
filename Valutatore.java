import java.io.*;
public class Valutatore {
	private Lexer lex;
	private Token look;
	// se l'input e‘ letto da un file, creare un campo del tipo BufferedReader,
	// come in Esercizio 3.1
	
	public Valutatore(Lexer l) {
		lex = l;
		move();
		// se l'input e‘ letto da un file, creare un oggetto del tipo
		// BufferedReader, come in Esercizio 3.1
	}

	void move() {
		// come in Esercizio 3.1
	}

	void error(String s) {
		// come in Esercizio 3.1
	}

	void match(int t) {
		// come in Esercizio 3.1
	}

	public void start() {
		int expr_val;
		expr_val = expr();
		match(Tag.EOF);
		System.out.println(expr_val);
	}
	// la procedura start puo‘ essere estesa
	// come in Esercizio 3.1 (opzionale)

	private int expr() {
		int term_val, exprp_val;
		term_val = term();
		exprp_val = exprp(term_val);
		return exprp_val;
	}
// la procedura expr puo‘ essere estesa
// come in Esercizio 3.1 (opzionale)

	private int exprp(int exprp_i) {
		int term_val, exprp_val;
		switch (look.tag) {
			case '+':
				match('+');
				term_val = term();
				exprp_val = exprp(exprp_i + term_val);
			break;
			
			case '-':
				// ... completare ... //
		}
	}

	private int term() {
		// ... completare ... //
	}
	
	private int termp(int termp_i) {
		int fact_val, termp_val;
		switch (look.tag) {
			case '*':
				match('*');
				fact_val = fact();
				termp_val = termp(termp_i * fact_val);
			break;
			// ... completare ... //
		}
	}
	
	private int fact() {
		int fact_val;
		switch (look.tag) {
			case Tag.NUM:
				fact_val = // ... completare ... //
		}
		return fact_val;
	}
}
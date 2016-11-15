public class Parser {
	private Lexer lex;
	private Token look;
	
	public Parser(Lexer l){
		lex = l;
		move();
	}
	
	void move() {
		look = lex.lexical_scan();
		System.err.println("token = " + look);
	}
	
	void error(String s) {
		throw new Error("near line " + lex.line + ": " + s);
	}
	
	void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF) move();
		} 	
		else error("syntax error");
	}
	
	public void start() {
		switch(look.tag){
		case '(':
		case Tag.NUM:
			//match(look.tag);
			expr();
			break;
		default:
			error("syntax error in start()");
		}
		match(Tag.EOF);
	}
	
	private void expr() {
		switch(look.tag){
			case '(':
			case Tag.NUM:
				term();
				exprp();
				break;
			default:
				error("syntax error in expr()");
		}
	}
		
	private void exprp() {
		switch (look.tag) {
			case '+':
			case '-':	
				match(look.tag);
				term();
				exprp();
				break;
			case Tag.EOF:
			case ')':
				break;
			default:
				error("syntax error exprp");
				break;
		}
	}
	
	private void term() {
		switch(look.tag){
			case '(':
			case Tag.NUM:
				fact();
				termp();
				break;
			default:
				error("syntax error in function term()");
		}
	}
		
	private void termp() {
		switch (look.tag) {
			case '(':
			case '*':
			case '/':
				match(look.tag);
				fact();
				termp();
				break;
			case ')':
			case Tag.EOF:
			case '+':
			case '-':
				break;
			default:
				error("syntax error in function termp()");
				break;
		}
	}
	
	private void fact() {
		switch (look.tag) {
			case '(':
				match('(');
				expr();
				match(')');
				break;
			case Tag.NUM:
				match(Tag.NUM);
				break;
			default:
				error("syntax error fact");
				break;
		}
	}
	
	public static void main(String[] args) {
		Lexer lex = new Lexer();
		Parser parser = new Parser(lex);
		parser.start();
	}
}

import java.io.*;

public class ParserBuffer {
	private LexerBuffer lex;
	private BufferedReader pbr;
	private Token look;
	
	public ParserBuffer(LexerBuffer l, BufferedReader br){
		lex = l;
		pbr = br;
		move();
	}
	
	void move() {
		look = lex.lexical_scan(pbr);
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
		LexerBuffer lex = new LexerBuffer();
		String path = "...path...";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ParserBuffer parser = new ParserBuffer(lex, br);
			parser.start();
			br.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}

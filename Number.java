public class Number extends Token{

	public int value=0;

/*    public Number (int tag, String s){
        super(tag);


    }
    public String toString() {
        return "<" + tag + ", " + value + ">";
    }
 */
	public String lexeme = "";
	public Number(int tag, String s) {
		super(tag);
		lexeme = s;
		if (s != null && s.length() != 0) {
			int p = 1, i = s.length()-1;
			while ( i >= 0 ) {
				value += (s.charAt(i)-'0') * p;
				i--;
				p *= 10;
			}
		}
    }

    public String toString() {
		return "<" + tag + ", " + value + ">";
	}
}

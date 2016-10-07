public class Supporto {
	/**
	 * isANumber:
	 * ch valore di tipo char.
	 * return: 
	 * 	True: se è un numero che da 0 a 9;
	 * 	False: se non è un numero.
	 */
	public static boolean isANumber(char ch) {
		return ch > 47 && ch < 58;
	}

	/**
	 * isACharacter:
	 * ch valore di tipo char.
	 * return:
	 * 	True: se appartiene all'alfabeto, tra maiuscole e minuscole.
	 * 	False: se non appartiene.
	 */
	public static boolean isACharacter(char ch) {
		return (ch > 64 && ch < 91) || (ch > 96 && ch < 123);
	}

	/**
	 * UpperCase:
	 * ch valore di tipo char.
	 * return:
	 *	True: se appartiene all'alfabeto --> maiuscolo.
	 *	Fals: se non appartiene.
	 */
	public static boolean UpperCase(char ch) {
		return ch > 64 && ch < 91;
	}

	/**
	 * LowerCase:
	 * ch valore di tipo char.
	 * return:
	 *	True: se appartiene all'alfabeto --> minuscolo.
	 *	Fals: se non appartiene.
	 */
	public static boolean LowerCase(char ch) {
		return ch > 96 && ch < 123;
	}
}
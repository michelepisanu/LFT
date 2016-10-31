/**
 * 	Parte 1 del progetto di LFT;
 */
public class Dfa {
	/**
	 * Esercizio 1.1:
	 * Verifica ci siano 3 zeri consecutivi nella stringa inserita.
	 */ 
	public static boolean treZeri(String s) {
		int state = 0, i = 0;

		while (state >= 0 && i < s.length()) {
			char ch = s.charAt(i++);
			switch (state) {
				case 0: if (ch == '1')
							state = 0;
						else if (ch == '0')
							state = 1;
						else
							state = -1;
						break;
				case 1: if (ch == '0')
							state = 2;
						else if (ch == '1')
							state = 1;
						else
							state = -1;
						break;
				case 2: if (ch == '0')
							state = 3;
						else if (ch == '1')
							state = 0;
						else
							state = -1;
						break;
				case 3: if (ch != '0' && ch != '1')
							state = -1;
						break;
			}
		}
		return state == 3;
	}

	/**
	 * Esercizio 1.1:
	 * Non riconosca stringhe con 3 zeri consecutivi.
	 */
	public static boolean noTreZeri(String s) {
		int state = 0;
		int	i = 0;
		
		while (state >= 0 && i < s.length()) {
			
			final char ch = s.charAt(i++);
			
			switch(state) {
				case 0: if(ch == '0')
							state = 1;
						else if(ch == '1')
							state = 0;
						else
							state = -1;
						break;
				case 1: if(ch == '0')
							state = 2;
						else if(ch == '1')
							state = 0;
						else
							state = -1;
						break;
				case 2: if(ch == '0')
							state = 3;
						else if(ch == '1')
							state = 0;
						else
							state = -1;
						break;
				case 3: if(ch == '0' || ch == '1')
							state = 3;
						else
							state = -1;
						break;
			}
		}
		return state >= 0 && state < 3;
	}
	/** 
	 * Esercizio 1.2:
	 * Riconosce le costanti numeriche in virgola mobile. Esempi di tali costanti sono:
	 * 		123    123.5    .567    +7.5    -.7    67e10    1e-2    -.7e2;
	 */
	public static boolean virgolaMobile(String s) {
		int state = 0, i = 0;
 
        while(state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0: if (ch == '+' || ch == '-')
                        	state = 1;
                    	else if (ch == '.')
                        	state = 2;
                    	else if (Supporto.isANumber(ch))
                        	state = 7;
                    	else 
                    		state = -1;
                		break;
                case 1: if (ch == '.')
                        	state = 2;
                    	else if (Supporto.isANumber(ch))
                        	state = 7;
                    	else
                    		state = -1;
                		break;
               	case 2: if (Supporto.isANumber(ch))
                        	state = 3;
                    	else
                    		state = -1;
                		break;
                case 3: if (ch == 'e')                          //Stato finale
                        	state = 4;
                    	else if (Supporto.isANumber(ch))
                        	state = 3;
                    	else
                    		state = -1;
                		break;
                case 4: if (Supporto.isANumber(ch))
                        	state = 6;
                   		else if (ch == '-')
                        	state = 5;
                    	else if (ch == '+')
                        	state = 5;
                    	else state = -1;
                		break;
                case 5: if (Supporto.isANumber(ch))
                        	state = 6;
                    	else 
                    		state = -1;
                		break;
                case 6: if (Supporto.isANumber(ch))              //Stato finale
                        	state = 6;
                    	else
                    		state = -1;
                case 7: if (Supporto.isANumber(ch))              //Stato finale
                        	state = 7;
                    	else if (ch == '.')
                        	state = 2;
                    	else if (ch == 'e')
                        	state = 4;
                    	else 
                    		state = -1;
                		break;
            }
        }
        return state == 7 || state == 3 || state == 6;
    }

    /**
     * Esercizio 1.3:
     * E' l'esercizio precedente, modificato con l'aggiunta del riconoscimento degli spazi;
     */
    public static boolean virgolaMobileSpazi(String s) {
		int state = 0, i = 0;
 
        while(state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0: if (ch == ' ')							// Stato finale
                			state = 0;
                		else if (ch == '+')
                        	state = 1;
                    	else if (ch == '-')
                        	state = 1;
                    	else if (ch == '.')
                        	state = 2;
                    	else if (Supporto.isANumber(ch))
                        	state = 7;
                    	else 
                    		state = -1;
                		break;
                case 1: if (ch == '.')
                        	state = 2;
                    	else if (Supporto.isANumber(ch))
                        	state = 7;
                    	else
                    		state = -1;
                		break;
               	case 2: if (Supporto.isANumber(ch))
                        	state = 3;
                    	else
                    		state = -1;
                		break;
                case 3: if (ch == ' ')							// Stato finale
                			state = 3;
                		else if (ch == 'e')						
                        	state = 4;
                    	else if (Supporto.isANumber(ch))
                        	state = 3;
                    	else
                    		state = -1;
                		break;
                case 4: if (Supporto.isANumber(ch))
                        	state = 6;
                   		else if (ch == '-')
                        	state = 5;
                    	else if (ch == '+')
                        	state = 5;
                    	else state = -1;
                		break;
                case 5: if (Supporto.isANumber(ch))
                        	state = 6;
                    	else 
                    		state = -1;
                		break;
                case 6: if (ch == ' ')							// Stato finale
                			state = 6;
                		else if (Supporto.isANumber(ch))
                        	state = 6;
                    	else
                    		state = -1;
                case 7: if (ch == ' ')							// Stato finale
                			state = 7;
                		else if (Supporto.isANumber(ch))
                        	state = 7;
                    	else if (ch == '.')
                        	state = 2;
                    	else if (ch == 'e')
                        	state = 4;
                    	else 
                    		state = -1;
                		break;
            }
        }
        return state == 0 || state == 7 || state == 3 || state == 6;
    }

    /**
     * Esercizio 1.4:
     * Progettare e implementare un DFA che riconosca il linguaggio degli identificatori
	 * in un linguaggio in stile Java: un identificatore e una sequenza non vuota di lettere, numeri, ed il `
	 * simbolo di sottolineatura _ che non comincia con un numero e che non puo essere composto solo `da un _.
     *
     */
    public static boolean identificatori(String s){
		int state = 0, i = 0;
		while (state >= 0 && i < s.length()) {
			
			final char ch = s.charAt(i++);
			
			switch(state) {
				case 0: if (ch == '_')
							state = 1;
						else if (Supporto.isACharacter(ch))
							state = 2;
						else 
							state = -1;
						break;
				case 1:	if (ch == '_')
							state = 1;
						else if (Supporto.isACharacter(ch))
							state = 2;
						else if (Supporto.isANumber(ch))
							state = 2;
						else 
							state = -1;
						break;
				case 2: if (Supporto.isANumber(ch))
							state = 2;
						else if (Supporto.isACharacter(ch))
							state = 2;
						else if (ch == '_')
							state = 2;
						else 
							state = -1;
						break;
			}
		}
		return state == 2 ;
	}

    /**
     * Esercizio 1.5:
     * Esercizio 1.5. Progettare e implementare un DFA che riconosca il linguaggio dei numeri binari
     * (stringhe di 0 e 1) il cui valore e multiplo di 3. Per esempio, ` 110 e 1001 sono stringhe del
     * linguaggio (rappresentano rispettivamente i numeri 6 e 9), mentre 10 e 111 no (rappresentano
     * rispettivamente i numeri 2 e 7). 
     */

    public static boolean multipliTre(String s) {
        int state = 0, i = 0;

        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0: if (ch == '0')
                            state = 0;
                        else if (ch == '1')
                            state = 1;
                        else
                            state = -1;
                        break;
                case 1: if (ch == '0')
                            state = 2;
                        else if (ch == '1')
                            state = 0;
                        else 
                            state = -1;
                        break;
                case 2: if (ch == '0')
                            state = 1;
                        else if (ch == '1')
                            state = 2;
                        else
                            state = -1;
                        break;
            }
        }

        return state == 0;
    }

    /**
     * Esercizio 1.6:
     * Progettare un analizzatore lessicale, che si occupa del riconoscimento dei commenti.
     */

    public static boolean comment(String s){
        int state = 0, i = 0;

        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0: if (ch == '/')
                            state = 1;
                        else
                            state = -1;
                        break;
                case 1: if (ch == '*')
                            state = 2;
                        else
                            state = -1;
                        break;
                case 2: if (ch == '*')
                            state = 3;
                        else if (ch == 'a' || ch == '/')
                            state = 2;
                        else
                            state = -1;
                        break;
                case 3: if (ch == '*')
                            state = 3;
                        else if (ch == 'a')
                            state = 2;
                        else if (ch == '/')
                            state = 4;
                        else
                            state = -1;
                        break;
                case 4: if (ch == '/' || ch != '/')             //Stato finale è sempre falso se la stringa non finisce la.
                            state = -1;
                        break;
            }
        }
        return state == 4;
    }

    /**
     * Esercizio 1.7 OPZIONALE
     * Analizzatore precedentemente scritto modificato.
     */
    public static boolean commentM(String s){
        int state = 0, i = 0;

        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0: if (ch == 'a')
                            state = 0;
                        else if (ch == '/')
                            state = 1;
                        else if (ch == '*')
                            state = 3;
                        else
                            state = -1;
                        break;
                case 1: if (ch == '*')
                            state = 2;
                        else if (ch == 'a')
                            state = 0;
                        else
                            state = -1;
                        break;
                case 2: if (ch == '*')
                            state = 3;
                        else if (ch == 'a' || ch == '/')
                            state = 2;
                        else
                            state = -1;
                        break;
                case 3: if (ch == '*')
                            state = 3;
                        else if (ch == 'a')
                            state = 2;
                        else if (ch == '/')
                            state = 4;
                        else
                            state = -1;
                        break;
                case 4: if (ch == 'a')
                            state = 4;
                        else if (ch == '*')
                            state = 5;
                        else
                            state = -1;
                        break;
                case 5: if (ch == '*')
                            state = 5;
                        else if (ch == 'a')
                            state = 6;
                        else
                            state = -1;
                        break;
                case 6: if (ch == 'a')
                            state = 6;
                        else
                            state = -1;
            }
        }
        return state == 0 || state == 4 || state == 6;
    }

    /**
     * Esercizio 1.8:
     * Da un Nfa con mosse silenti a un Dfa minimo.
     * Questo Automa riconosce il linguaggio se è del tipo: ab^n oppure a^nb.
     */
    public static boolean nfaToDfa (String s) {
        int state = 0, i = 0;

        while (state >= 0 && i < s.length()) {

            final char ch = s.charAt(i++);
            
            switch (state) {
                case 0: if (ch == 'a')
                            state = 1;
                        else if (ch == 'b')
                            state = 2;
                        else
                            state = -1;
                        break;
                case 1: if (ch == 'a')
                            state = 1;
                        else if (ch == 'b')
                            state = 4;
                        else
                            state = -1;
                        break;
                case 2: if (ch == 'a')
                            state = 3;
                        else
                            state = -1;
                        break;
                case 3: if (ch == 'a')                      //Stato finale;
                            state = 3;
                        else
                            state = -1;
                        break;
                case 4: if (ch == 'a' || ch == 'b')         //Stato finale;
                            state = -1;
                        else
                            state = -1;
                        break;
            }
        }
        return state == 3 || state == 4;
    }
}

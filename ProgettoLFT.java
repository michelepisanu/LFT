public class ProgettoLFT {
	public static void main(String[] args) {
		// Es 1.1 --> Dfa.treZeri();
		System.out.println("- Esercizio 1.1 - Verifico se ci sono 3 zeri consecutivi:");
		for (String a : args)
			System.out.println(a + " " + (Dfa.treZeri(a) ? "OK" : "NOPE"));

		// Es 1.1 --> Dfa.noTreZeri();
		System.out.println("- Esercizio 1.1 - Verifico che non ci siano 3 zeri consecutivi:");
		for (String a : args)
			System.out.println(a + " " + (Dfa.noTreZeri(a) ? "OK" : "NOPE"));

		// Es 1.2 --> Dfa.virgolaMobile();
		System.out.println("- Esercizio 1.2 - Verifico che appartenga all'insieme delle costanti numeriche in virgola mobile:");
		for (String a : args)
			System.out.println(a + " " + (Dfa.virgolaMobile(a) ? "OK" : "NOPE"));

		// Es 1.3 --> Dfa.virgolaMobileSpazi();
		System.out.println("- Esercizio 1.3 - Verifico che appartenga all'insieme delle costanti numeriche in virgola mobile possono iniziare o/e finire con gli spazi:");
		for (String a : args)
			System.out.println(a + " " + (Dfa.virgolaMobileSpazi(a) ? "OK" : "NOPE"));

		// Es 1.4 --> Dfa.identificatori();
		System.out.println("- Esercizio 1.4 - Identifico una sequenza non vuota di lettere, numeri o simbolo di sottolineatura:");
		for (String a: args)
			System.out.println(a + " " + (Dfa.identificatori(a) ? "OK" : "NOPE"));

		// Es 1.8 --> Dfa.nfaToDfa();
		System.out.println("- Esercizio 1.8 - Verifico che la sia del tipo a^nb oppure ba^n:");
		for (String a: args)
			System.out.println(a + " " + (Dfa.nfaToDfa(a) ? "OK" : "NOPE"));

	}
}
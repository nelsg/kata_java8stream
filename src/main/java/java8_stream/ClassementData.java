package java8_stream;

import java.util.Arrays;
import java.util.List;

public class ClassementData {
	// Les candidats
	public final List<Candidat> candidats = Arrays.asList(
			new Candidat("Inès"),
			new Candidat("Gabriel"),
			new Candidat("Jade"),
			new Candidat("Raphaël"),
			new Candidat("Alice"),
			new Candidat("Louise"));

	// Les votes
	public final String[] votes = new String[] {"Jade", "Jade", "Inès", "Alice", "Alice", "Louise", "Jade", "Raphaël", "Alice", "Gabriel", "Gabrielle", "Jade", "Louise", "Louise", "Alice", "Inès", "Gabriel", "Alice", "Jade", "Inès", "Alice", "Inès"};

}

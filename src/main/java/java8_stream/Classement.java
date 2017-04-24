package java8_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Classement {
	// Les candidats
	private final List<Candidat> candidats = Arrays.asList(
			new Candidat("Inès"),
			new Candidat("Gabriel"),
			new Candidat("Jade"),
			new Candidat("Raphaël"),
			new Candidat("Alice"),
			new Candidat("Louise"));

	// Les votes
	private final String[] votes = new String[] {"Jade", "Jade", "Inès", "Alice", "Alice", "Louise", "Jade", "Raphaël", "Alice", "Gabriel", "Gabrielle", "Jade", "Louise", "Louise", "Alice", "Inès", "Gabriel", "Alice", "Jade", "Inès", "Alice", "Inès"};
	
	/**
	 * Retourner un candidat depuis son prénom, null si non trouvé
	 */
	public Candidat getCandidat(String prenom) {
		for (Candidat candidat: candidats) {
			if (candidat.getPrenom().equals(prenom)) {
				return candidat;
			}
		}
		return null;
	}

	/**
	 * distribution des votes sur les candidats
	 */
	public void appliquerVotes() {
		for (String vote: votes) {
			for (Candidat candidat: candidats) {
				if (candidat.getPrenom().equals(vote)) {
					candidat.addVote();
					break;
				}
			}
		}
	}
	
	static class CandidatComparator implements Comparator<Candidat> {
		@Override
		public int compare(Candidat p1, Candidat p2) {
			return p2.getVotes() - p1.getVotes();
		}
	}
	
	/**
	 * Retourne l'ordre d'arrivée
	 */
	public List<Candidat> podium() {
		List<Candidat> resultat = new ArrayList<Candidat>(candidats);
		
		Collections.sort(resultat, new CandidatComparator());
		
		return resultat;
	}
	
	/**
	 * Retourne la liste des candidats
	 */
	public List<String> listeCandidats() {
		List<String> resultat = new ArrayList<>();
		for (Candidat candidat: candidats) {
			resultat.add(candidat.getPrenom());
		}
		Collections.sort(resultat);
		return resultat;
	}
	
	/**
	 * Retourne la liste des candidats en majuscule
	 */
	public List<String> listeCandidatsUpper() {
		List<String> resultat = new ArrayList<>();
		for (String prenom: listeCandidats()) {
			resultat.add(prenom.toUpperCase());
		}
		Collections.sort(resultat);
		return resultat;
	}

	/**
	 * Retourne le nombre de votes distribués
	 */
	public int votesValides() {
		int resultat = 0;
		for (Candidat candidat: candidats) {
			resultat += candidat.getVotes();
		}
		return resultat;
	}

	/**
	 * Retourne le nombre de votes distribués
	 */
	public int votesNuls() {
		return votes.length - votesValides();
	}
}

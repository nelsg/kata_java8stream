package java8_stream.bak;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java8_stream.Candidat;
import java8_stream.ClassementData;


public class ClassementAfter extends ClassementData {
	/**
	 * Retourner un candidat depuis son prénom, null si non trouvé
	 */
	public Optional<Candidat> getCandidat(String prenom) {
		return candidats.stream().filter(x -> x.getPrenom().equals(prenom)).findFirst();
	}

	/**
	 * distribution des votes sur les candidats
	 */
	public void appliquerVotes() {
		Stream.of(votes).map(x -> getCandidat(x)).filter(x -> x.isPresent()).forEach(x -> x.get().addVote());
	}
	
	/**
	 * Retourne l'ordre d'arrivée
	 */
	public List<Candidat> podium() {
		return candidats.stream().sorted((p1, p2) -> p2.getVotes() - p1.getVotes()).collect(Collectors.toList());
	}
	
	/**
	 * Retourne la liste des candidats
	 */
	public List<String> listeCandidats() {
		return candidats.stream().map(Candidat::getPrenom).sorted().collect(Collectors.toList());
	}
	
	/**
	 * Retourne la liste des candidats en majuscule
	 */
	public List<String> listeCandidatsUpper() {
		return listeCandidats().stream().map(String::toUpperCase).sorted().collect(Collectors.toList());
	}

	/**
	 * Retourne le nombre de votes distribués
	 */
	public int votesValides() {
		return candidats.stream().mapToInt(x -> x.getVotes()).sum();
	}

	/**
	 * Retourne le nombre de votes distribués
	 */
	public int votesNuls() {
		return votes.length - votesValides();
	}
}

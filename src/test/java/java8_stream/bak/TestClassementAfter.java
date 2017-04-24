package java8_stream.bak;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import java8_stream.Candidat;
import java8_stream.bak.ClassementAfter;

public class TestClassementAfter {

	@Test
	public void testAppliquerVotes() {
		ClassementAfter classement = new ClassementAfter();
		classement.appliquerVotes();
		assertFalse(classement.getCandidat("Louis").isPresent());
		assertEquals(4, classement.getCandidat("Inès").get().getVotes());
		assertEquals(2, classement.getCandidat("Gabriel").get().getVotes());
		assertEquals(5, classement.getCandidat("Jade").get().getVotes());
		assertEquals(1, classement.getCandidat("Raphaël").get().getVotes());
		assertEquals(6, classement.getCandidat("Alice").get().getVotes());
		assertEquals(3, classement.getCandidat("Louise").get().getVotes());
	}

	@Test
	public void testPodium() {
		ClassementAfter classement = new ClassementAfter();
		classement.appliquerVotes();
		List<Candidat> podium = classement.podium();
		assertEquals("Alice", podium.get(0).getPrenom());
		assertEquals("Jade", podium.get(1).getPrenom());
		assertEquals("Inès", podium.get(2).getPrenom());
		assertEquals("Louise", podium.get(3).getPrenom());
		assertEquals("Gabriel", podium.get(4).getPrenom());
		assertEquals("Raphaël", podium.get(5).getPrenom());
	}

	@Test
	public void testListeCandidats() {
		ClassementAfter classement = new ClassementAfter();
		assertArrayEquals(new String[] {"Alice", "Gabriel", "Inès", "Jade", "Louise", "Raphaël"}, classement.listeCandidats().toArray());
	}

	@Test
	public void testListeCandidatsUpper() {
		ClassementAfter classement = new ClassementAfter();
		assertArrayEquals(new String[] {"ALICE", "GABRIEL", "INÈS", "JADE", "LOUISE", "RAPHAËL"}, classement.listeCandidatsUpper().toArray());
	}

	@Test
	public void testVotesValides() {
		ClassementAfter classement = new ClassementAfter();
		classement.appliquerVotes();
		assertEquals(21, classement.votesValides());
	}

	@Test
	public void testVotesNuls() {
		ClassementAfter classement = new ClassementAfter();
		classement.appliquerVotes();
		assertEquals(1, classement.votesNuls());
	}
}

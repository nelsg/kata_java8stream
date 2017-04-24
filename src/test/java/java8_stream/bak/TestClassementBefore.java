package java8_stream.bak;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import java8_stream.Candidat;
import java8_stream.bak.ClassementBefore;

public class TestClassementBefore {

	@Test
	public void testAppliquerVotes() {
		ClassementBefore classement = new ClassementBefore();
		classement.appliquerVotes();
		assertNull(classement.getCandidat("Louis"));
		assertEquals(4, classement.getCandidat("Inès").getVotes());
		assertEquals(2, classement.getCandidat("Gabriel").getVotes());
		assertEquals(5, classement.getCandidat("Jade").getVotes());
		assertEquals(1, classement.getCandidat("Raphaël").getVotes());
		assertEquals(6, classement.getCandidat("Alice").getVotes());
		assertEquals(3, classement.getCandidat("Louise").getVotes());
	}

	@Test
	public void testPodium() {
		ClassementBefore classement = new ClassementBefore();
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
		ClassementBefore classement = new ClassementBefore();
		assertArrayEquals(new String[] {"Alice", "Gabriel", "Inès", "Jade", "Louise", "Raphaël"}, classement.listeCandidats().toArray());
	}

	@Test
	public void testListeCandidatsUpper() {
		ClassementBefore classement = new ClassementBefore();
		assertArrayEquals(new String[] {"ALICE", "GABRIEL", "INES", "JADE", "LOUISE", "RAPHAEL"}, classement.listeCandidatsUpper().toArray());
	}

	@Test
	public void testVotesValides() {
		ClassementBefore classement = new ClassementBefore();
		classement.appliquerVotes();
		assertEquals(21, classement.votesValides());
	}

	@Test
	public void testVotesNuls() {
		ClassementBefore classement = new ClassementBefore();
		classement.appliquerVotes();
		assertEquals(1, classement.votesNuls());
	}
}

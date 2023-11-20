package bowling;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MultiPlayerGameTest {
	PartieMultiJoueurs parties;
	String[] joueurs = {"j1", "j2", "j3"};

	@BeforeEach
	public void setUp() {
		parties = new PartieMultiJoueurs();
		parties.demarreNouvellePartie(joueurs);
	}

	@Test
	void testPartieNondemarre() {
		PartieMultiJoueurs partiesTest = new PartieMultiJoueurs();
		try {
			partiesTest.enregistreLancer(7);
			fail("ne catch pas lorsque un lancé est fait sans demarrer partie");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDemarrerTableauVide() {
		PartieMultiJoueurs partiesTest = new PartieMultiJoueurs();
		String[] j = {};
		try {
			partiesTest.demarreNouvellePartie(j);
			fail("ne catch pas lors d'un demarage de partie avec un tableau vide");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testinitiationPartie() {
		PartieMultiJoueurs partiesTest = new PartieMultiJoueurs();
		assertEquals("Prochain tir : joueur j1, tour n° 1, boule n° 1", parties.demarreNouvellePartie(joueurs), "partie pas correctement initialisé");
	}

	@Test
	void testApresUnStrike() {
		assertEquals("Prochain tir : joueur j2, tour n° 1, boule n° 1", parties.enregistreLancer(10));
	}

	@Test
	void testScoreUnknownPlayer() {
		try {
			parties.scorePour("j4");
			fail("Do not catch when unknown player");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testScorePartieLambda() {
		parties.enregistreLancer(5);//j1
		parties.enregistreLancer(3);//j1
		parties.enregistreLancer(10);//j2
		parties.enregistreLancer(10);//j3
		parties.enregistreLancer(7);//j1
		parties.enregistreLancer(3);//j1
		parties.enregistreLancer(5);//j2
		parties.enregistreLancer(2);//j2
		parties.enregistreLancer(10);//j3
		parties.enregistreLancer(3);//j1
		parties.enregistreLancer(4);//j1
		parties.enregistreLancer(0);//j2
		parties.enregistreLancer(0);//j2
		parties.enregistreLancer(7);//j3


		assertEquals(28, parties.scorePour("j1"));
		assertEquals(24, parties.scorePour("j2"));
		assertEquals(51, parties.scorePour("j3"));
	}

	@Test
	void testPartieTermine() {
		int cpt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 6; j++) {
				parties.enregistreLancer(1);
			}
		}
		assertEquals("Partie terminée", parties.enregistreLancer(1));

	}
}

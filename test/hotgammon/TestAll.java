package hotgammon;

import hotgammon.unittest.TestBackGammonWinningStrategy;
import hotgammon.unittest.TestDiceStrategy;
import hotgammon.unittest.TestHypergammonStartingPositionStrategy;
import hotgammon.unittest.TestLocation;
import hotgammon.variants.TestAlphaMon;
import hotgammon.variants.TestBetaMon;
import hotgammon.variants.TestEpsilonMon;
import hotgammon.variants.TestGammaMon;
import hotgammon.variants.TestHandicap;
import hotgammon.variants.TestSemiMon;
import hotgammon.variants.TestZetaMon;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestBackGammonWinningStrategy.class,
		TestDiceStrategy.class, TestHypergammonStartingPositionStrategy.class,
		TestLocation.class, TestAlphaMon.class, TestBetaMon.class,
		TestEpsilonMon.class, TestGammaMon.class, TestHandicap.class,
		TestZetaMon.class, TestSemiMon.class, TestTranscriptDecorator.class,
		TestGameObserver.class })
public class TestAll {
	// Dummy - it is the annotations that tell JUnit what to do...
}

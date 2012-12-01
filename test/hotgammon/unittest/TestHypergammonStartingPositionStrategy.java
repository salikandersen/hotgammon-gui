package hotgammon.unittest;

import static org.junit.Assert.assertEquals;
import hotgammon.Color;
import hotgammon.Location;
import hotgammon.variants.startingposition.HyperGammonStartingPositionStrategi;

import org.junit.Before;
import org.junit.Test;

public class TestHypergammonStartingPositionStrategy {
	HyperGammonStartingPositionStrategi hypergammonStartingPositionStrategi;
	
	@Before
	public void SetUp(){
		hypergammonStartingPositionStrategi = new HyperGammonStartingPositionStrategi();
	}
	@Test
	public void TestVerifyThreeRedCheckersAtB1(){
		int count = hypergammonStartingPositionStrategi.getStartCount(Location.B1);
		assertEquals(Color.RED, hypergammonStartingPositionStrategi.getStartColor(Location.B1));
		assertEquals(3, count);
	}
	
	@Test
	public void TestVerifyThreeRedCheckersAtB2AndB3(){
		int count = hypergammonStartingPositionStrategi.getStartCount(Location.B2);
		assertEquals(Color.RED, hypergammonStartingPositionStrategi.getStartColor(Location.B2));
		assertEquals(3, count);
		
		count = hypergammonStartingPositionStrategi.getStartCount(Location.B3);
		assertEquals(Color.RED, hypergammonStartingPositionStrategi.getStartColor(Location.B3));
		assertEquals(3, count);
	}
	
	@Test
	public void TestVerifyThreeBlackCheckersAtR1AndR2AndR3(){
		int count = hypergammonStartingPositionStrategi.getStartCount(Location.R1);
		assertEquals(Color.BLACK, hypergammonStartingPositionStrategi.getStartColor(Location.R1));
		assertEquals(3, count);
		count = hypergammonStartingPositionStrategi.getStartCount(Location.R2);
		assertEquals(Color.BLACK, hypergammonStartingPositionStrategi.getStartColor(Location.R2));
		assertEquals(3, count);
		
		count = hypergammonStartingPositionStrategi.getStartCount(Location.R3);
		assertEquals(Color.BLACK, hypergammonStartingPositionStrategi.getStartColor(Location.R3));
		assertEquals(3, count);
	}
}

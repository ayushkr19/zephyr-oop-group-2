package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import participatingbody.Hostel;
import events.eventutils.ScoreBoard;
import exceptions.HostelNotFoundException;

public class ScoreBoardTest {

	ScoreBoard scoreBoard = new ScoreBoard();
	Hostel AH3 = new Hostel("AH3");
	@Test
	public void test() {
		assertEquals(ScoreBoard.getScoresMap().size(),0);
	}
	
	@Test(expected = HostelNotFoundException.class)
	public void testHostel() throws HostelNotFoundException{
		ScoreBoard.getScores(AH3);
	}

}

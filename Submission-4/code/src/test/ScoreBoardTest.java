package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import participatingbody.Hostel;
import events.eventutils.ScoreBoard;
import events.eventutils.UpdateScoreType;
import exceptions.HostelNotFoundException;

public class ScoreBoardTest {

	ScoreBoard scoreBoard = new ScoreBoard();
	Hostel AH3 = new Hostel("AH3");
	@Test
	public void test() {
		assertEquals(ScoreBoard.getScoresMap().size(),14);
	}
	
	@Test
	public void testHostel() throws HostelNotFoundException{
		Long before = ScoreBoard.getScores(AH3);
		ScoreBoard.updateScores(AH3, 20L, UpdateScoreType.ADD);
		assertEquals(ScoreBoard.getScores(AH3).longValue() - before, 20);
	}

}

import org.junit.Assert.*; 
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;  

public class SOSGameTests {
	private Board board;
	private SOSGUI gui;
	
	@Before
	public void setUp() {
		//Choosing board size
		board = new Board(3); 
		
	}
	
	//Making sure board size initialized is correct
	@Test
	public void testBoardSize() {
		Assert.assertEquals(3, board.getBoardSize());
	}
	
	//Testing new board size
	@Test
	public void testNewBoardSize() {
		for(int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 0; j < board.getBoardSize(); j++) {
				Assert.assertEquals(Board.Cell.EMPTY, board.getCell(i, j));
			}
		}
	}
	
	
	//Testing to make sure Red can make a valid S or O move
	@Test
	public void testRedMove() {
		board.makeMove(0, 0);
		Assert.assertEquals(Board.Cell.B, board.getCell(0, 0));
		Assert.assertEquals('R', board.getTurn());

	}
	
	//Testing to make sure Blue can make a valid S or O move
	@Test
	public void testBlueMove() {
		board.makeMove(0, 0);
		board.makeMove(0, 1);
		Assert.assertEquals(Board.Cell.R, board.getCell(0, 1));
		Assert.assertEquals('B', board.getTurn());
		

	}
	
	
	//Testing potential invalid moves
	@Test
	public void testInvalidMove() {
		board.makeMove(3,3);
		Assert.assertNull(board.getCell(3, 3));
		
		board.makeMove(0, 0);
		board.makeMove(0, 0);
		Assert.assertEquals('R', board.getTurn());
		Assert.assertEquals(Board.Cell.B, board.getCell(0,0));
	}
	
	
	
}

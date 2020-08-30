package tttProject;
import tttProject.Mark;

public class Board {
	private int boardRow;
	private int boardCol;
	private String boardName;
	private String[][] board;
	
	//initialize board to be a 3x3
	Board(){
		this(3,3,0);
		this.setSize(3,3);
		init();
		
	}

	//set content of board given users requirement
	Board(int row, int col, int count) {
		this.boardCol = col;
		this.boardRow = row;
		this.boardName = "Board #" + count;
		this.setSize(row,col);
		init();
	}

	//set board size to be a 3x3 for ttt
	void setSize(int row, int col) {
		this.board = new String [row][col];
	}
	
	//initialize the numbers to each board space to represent each space available on the board
	private void init() {
		this.board = new String[boardRow][boardCol];
		int numberOnBoard = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				String numOnBoard = "" + numberOnBoard;
				numberOnBoard++;
				board[i][j] = numOnBoard;
			}
		}
	}

	//print overall everything from a board
	void printMyBoard() {
		for(int i = 0; i<boardRow; i++){
			System.out.print(boardName);
		    for(int j = 0; j<boardCol; j++) {
		     System.out.print(" | " + board[i][j] + " | ");
		    }
		    System.out.println();
		}
		
	}
	
	//print the first row of a board
	void printRow1(){
		System.out.print(boardName);
		for(int j = 0; j<boardCol; j++) {
		     System.out.print(" | " + board[0][j] + " | ");
		}
	}
	
	//print the second row of the board
	void printRow2(){
		System.out.print(boardName);
		for(int j = 0; j<boardCol; j++) {
		     System.out.print(" | " + board[1][j] + " | ");
		}
	}
	
	//print the third row of the board
	void printRow3(){
		System.out.print(boardName);
		for(int j = 0; j<boardCol; j++) {
		     System.out.print(" | " + board[2][j] + " | ");
		}
	}

	
	// place the mark on the square chosen of the board
	public void boxToMark(String s, int row, int col) {
		board[row][col] = s;
		
	}

	//check whether a mark has been placed or it's still open for players
	public boolean isAvailable(int row, int col) {
		if(board[row][col]!= Mark.X.getMark() && board[row][col]!= Mark.O.getMark()) {
			return true;
		}
		else
			return false;
	}
	
	//return the current players mark
	public String getMark(int row, int col) {
		return board[row][col];
	}
	
	//return the row of the board
	public int getBoardRow() {
		return this.boardRow;
	}

	//return the col of the board
	public int getBoardCol() {
		return this.boardCol;
	}

	//retrun board name
	public String getName() {
		return this.boardName;
	}

}

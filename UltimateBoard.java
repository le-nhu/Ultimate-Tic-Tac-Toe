package tttProject;
import java.util.Scanner;
import java.util.Random;

public class UltimateBoard extends Board {
	//the main tic tac toe that holds 9 individual game
	private Player[] players = new Player[2];	//create 2 player
	private int currentPlayerIndex = -1;		//represent whos current player
	private int placeRow = 0;					//represent the row num to check available and place mark
	private int placeCol = 0;					//represent the col num to check available and place mark
	private Board board = new Board();			//serve as the smaller individual board
	private Board[][] game = new Board[3][3];	//overall big board for ttc that contains the mini board
	private String[] win = new String[9];		//store who wins what board from the overall board
	public int count = 0;						//use in various methods;general count;go through the boards
	private int startgame = 1;					//choose what type of game is played, player vs player or player vs ai
	private String[] marks = {"X", "O"};		//marks for the 2 players
	int selectBoard;							//board that the player will place their move on
	public int selectSquare;					//square on the board that player will place mark
	private int scoreToWin = 3;					//3 in a row to win
	Random rand = new Random(); 				//use to get the ai selectSquare/board
	int a,b,c,d,e,f,g,h,k = 0; 					//use as count for win test case
	
	//creation of the individual mini ttt game inside main
	UltimateBoard(){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				//each of the 9 slots of the overall ttt will contain another 3x3 
				game[i][j] = new Board(3,3,count);
				count++;
			}
		}
	
		setPlayers();
		start();
	}
	
	//start the game
	public void start() {
		Scanner input = new Scanner(System.in);
		//do a case switch for if the ttt game will be played by two people or 1 player vs AI
		System.out.println("Enter 1 for multiplayer, enter 2 to play against computer!");
		int gameType = input.nextInt();
		System.out.println("Enter 1 to play game that provides a list of possible legal moves to each player, else enter 0");
		int showAvailable = input.nextInt();
		//multiplayer
		if(gameType == 1) {
			System.out.println("ULTIMATE TIC TAC TOE STARTS NOW");
			System.out.println();
			print();
			System.out.println();
			do {
				switchPlayer();
				Player currentPlayer = players[this.currentPlayerIndex]; //currentPlayer
				System.out.println("Current Player is: " + currentPlayer.getMark());
				if(startgame == 1) {
					//first move of the game player can choose anywhere on board
					startgame++;
					System.out.println("Please select a valid board: ");
					selectBoard = input.nextInt();
					//check for validity of option
					while(!validBoard(selectBoard))
					{
						selectBoard = input.nextInt();
						
					}
					selectSquare = selectBoard;
				}
				else if(startgame > 1) {
					//check for validity of board, if there is still squares available to make move
					if(validBoard(selectSquare) == false) {
						selectBoard = input.nextInt();
						while(!validBoard(selectBoard))
						{
							selectBoard = input.nextInt();
							
						}
						selectSquare = selectBoard;
					}
				}
				
				System.out.println("Selected Board : " + selectSquare);
				System.out.println("Please select a valid square on the selected board: ");
				if(showAvailable == 1) {
					printAvailable();
				}
				selectSquare = input.nextInt();
				
				//given the selected square from 0-8 on a board, set where the row and col of that num is
				if(selectSquare == 0) {
					placeRow = 0;
					placeCol = 0;
				}
				else if(selectSquare == 1) {
					placeRow = 0;
					placeCol = 1;
				}
				else if(selectSquare == 2) {
					placeRow = 0;
					placeCol = 2;
				}
				else if(selectSquare == 3) {
					placeRow = 1;
					placeCol = 0;
				}
				else if(selectSquare ==4) {
					placeRow = 1;
					placeCol = 1;
				}
				else if(selectSquare == 5) {
					placeRow = 1;
					placeCol = 2;
				}
				else if(selectSquare == 6) {
					placeRow = 2;
					placeCol = 0;
				}
				else if(selectSquare == 7) {
					placeRow = 2;
					placeCol = 1;
				}
				else if(selectSquare == 8) {
					placeRow = 2;
					placeCol = 2;
				}
				
				//check availability of the selected square, proceed to mark if available otherwise take in new input
				while(!this.board.isAvailable(placeRow, placeCol)) {
					System.out.println("Please reselect a valid square on the selected board. ");
					selectSquare = input.nextInt();
					System.out.println("Selected square : " + selectSquare);
					
					if(selectSquare == 0) {
						placeRow = 0;
						placeCol = 0;
					}
					else if(selectSquare == 1) {
						placeRow = 0;
						placeCol = 1;
					}
					else if(selectSquare == 2) {
						placeRow = 0;
						placeCol = 2;
					}
					else if(selectSquare == 3) {
						placeRow = 1;
						placeCol = 0;
					}
					else if(selectSquare ==4) {
						placeRow = 1;
						placeCol = 1;
					}
					else if(selectSquare == 5) {
						placeRow = 1;
						placeCol = 2;
					}
					else if(selectSquare == 6) {
						placeRow = 2;
						placeCol = 0;
					}
					else if(selectSquare == 7) {
						placeRow = 2;
						placeCol = 1;
					}
					else if(selectSquare == 8) {
						placeRow = 2;
						placeCol = 2;
					}
				}
				
				//place the current player mark on the selected valid sqaure
				this.board.boxToMark(currentPlayer.getMark(), placeRow, placeCol) ;
				print();
				System.out.println();
			
				//continue until game is over
			}while(!gameOver());

		}
		//play against computer
		else if(gameType == 2) {
			String currentMark = "";
			System.out.println("ULTIMATE TIC TAC TOE STARTS NOW");
			System.out.println();
			print();
			System.out.println();
			do {
				switchPlayer();
				if(currentPlayerIndex == 0) { //represent user
					currentMark = "X";
				}
				else if(currentPlayerIndex == 1) { // represent computer
					currentMark = "O";
				}
				System.out.println("Current Player is: " + currentMark);
				
				if(startgame == 1) {
					//first move of the game player can choose anywhere on board
					startgame++;
					System.out.println("Please select a valid board: ");
					selectBoard = input.nextInt();
					while(!validBoard(selectBoard))
					{
						selectBoard = input.nextInt();
						
					}
					selectSquare = selectBoard;
				}
				
				else if(startgame > 1) {
					if(validBoard(selectSquare) == false) {
						if(currentPlayerIndex == 0) {
							selectBoard = input.nextInt();
							while(!validBoard(selectBoard))
							{
								selectBoard = input.nextInt();
								
							}
						}
						else if(currentPlayerIndex == 1) {
							selectBoard = rand.nextInt(9);
							while(!validBoard(selectBoard))
							{
								selectBoard = rand.nextInt(9);
							}
						}
						selectSquare = selectBoard;
					}
				}
				
				System.out.println("Selected Board : " + selectSquare);
				System.out.println("Please select a valid square on the selected board. ");
				if(showAvailable == 1) {
					printAvailable();
				}
				if(currentPlayerIndex == 0) {
					selectSquare = input.nextInt();
					if(selectSquare < 0 || selectSquare > 8) {
						System.out.println("Reenter valid square.");
						selectSquare = input.nextInt();
					}
					System.out.println("Selected square : " + selectSquare);
				}
				else if(currentPlayerIndex == 1) {
					//computer generate random play
					selectSquare = rand.nextInt(9);
					System.out.println("Selected square : " + selectSquare);
				}
				
				if(selectSquare == 0) {
					placeRow = 0;
					placeCol = 0;
				}
				else if(selectSquare == 1) {
					placeRow = 0;
					placeCol = 1;
				}
				else if(selectSquare == 2) {
					placeRow = 0;
					placeCol = 2;
				}
				else if(selectSquare == 3) {
					placeRow = 1;
					placeCol = 0;
				}
				else if(selectSquare ==4) {
					placeRow = 1;
					placeCol = 1;
				}
				else if(selectSquare == 5) {
					placeRow = 1;
					placeCol = 2;
				}
				else if(selectSquare == 6) {
					placeRow = 2;
					placeCol = 0;
				}
				else if(selectSquare == 7) {
					placeRow = 2;
					placeCol = 1;
				}
				else if(selectSquare == 8) {
					placeRow = 2;
					placeCol = 2;
				}
				
				while(!this.board.isAvailable(placeRow, placeCol)) {
					if(currentPlayerIndex == 1) {
						System.out.println("Please reselect a valid square on the selected board. ");
						selectSquare = rand.nextInt(9);
					}
					else if(currentPlayerIndex == 0) {
						System.out.println("Please reselect a valid square on the selected board. ");
						selectSquare = input.nextInt();
					}
					
					System.out.println("Selected square : " + selectSquare);
					
					if(selectSquare == 0) {
						placeRow = 0;
						placeCol = 0;
					}
					else if(selectSquare == 1) {
						placeRow = 0;
						placeCol = 1;
					}
					else if(selectSquare == 2) {
						placeRow = 0;
						placeCol = 2;
					}
					else if(selectSquare == 3) {
						placeRow = 1;
						placeCol = 0;
					}
					else if(selectSquare ==4) {
						placeRow = 1;
						placeCol = 1;
					}
					else if(selectSquare == 5) {
						placeRow = 1;
						placeCol = 2;
					}
					else if(selectSquare == 6) {
						placeRow = 2;
						placeCol = 0;
					}
					else if(selectSquare == 7) {
						placeRow = 2;
						placeCol = 1;
					}
					else if(selectSquare == 8) {
						placeRow = 2;
						placeCol = 2;
					}
				}
				
				this.board.boxToMark(currentMark, placeRow, placeCol);	
				
				print();
				System.out.println();
				
			}while(!gameOver());

		}
		else {
			System.out.println("Invalid entry.");
			start();
		}
		
	}
	
	private boolean validBoard(int selectBoard) {
		int checkAvailable = 9;
		if(selectBoard >= 0 && selectBoard <=8) {
			if(selectBoard==0) {
				board = game[0][0];
			}
			else if(selectBoard==1) {
				board = game[0][1];
			}
			else if(selectBoard==2) {
				board = game[0][2];
			}
			else if(selectBoard==3) {
				board = game[1][0];
			}	
			else if(selectBoard==4) {
				board = game[1][1];
			}
			else if(selectBoard==5) {
				board = game[1][2];
			}
			else if(selectBoard==6) {
				board = game[2][0];
			}
			else if(selectBoard==7) {
				board = game[2][1];
			}
			else if(selectBoard==8) {
				board = game[2][2];
			}
		
			for(int i = 0; i < 3;i++) {
				for(int j = 0; j< 3;j++) {
					if(!board.isAvailable(i,j))
						checkAvailable--;
				}
			}
		
			if(checkAvailable == 0) {
				System.out.println("Please choose a valid board");
				return false;
			}
		
		}
		
		else {
			System.out.println("Invalid board, please reenter a valid board");
			return false;
		}
		
			return true;
	}

	private boolean gameOver() {
		return isOverallWinner();// || board.isFull();
	}

	//overall big ttt game
	private boolean isOverallWinner() {		
		isWinner();
		
		for(int i=0; i<9; i++) {
			if(win[i] != null) {
				System.out.println("Player " + win[i] + " wins board #" + i);
			}
		}
		
		
		//winner from top row
		if(win[0] == win[1] && win[1] == win[2] && win[0] != null) {
			System.out.println();
			System.out.println("Player " + win[0] + " wins!!!");
			return true;
		}
		//winner from mid row
		else if(win[3] == win[4] && win[4] == win[5] && win[3] != null) {
			System.out.println();
			System.out.println("Player " + win[3] + " wins!!!");
			return true;
		}
		//winner from last row
		else if(win[6] == win[7] && win[7] == win[8] && win[8] != null) {
			System.out.println();
			System.out.println("Player " + win[6] + " wins!!!");
			return true;
		}
		//win 1st col
		else if(win[0] == win[3] && win[0] == win[6] && win[6] != null) {
			System.out.println();
			System.out.println("Player " + win[0] + " wins!!!");
			return true;
		}
		//win 2nd col
		else if(win[1] == win[4] && win[4] == win[7] && win[7] != null) {
			System.out.println();
			System.out.println("Player " + win[1] + " wins!!!");
			return true;
		}
		//win 3rd col
		else if(win[2] == win[5] && win[5] == win[8] && win[8] != null) {
			System.out.println();
			System.out.println("Player " + win[2] + " wins!!!");
			return true;
		}
		//win lr diag
		else if(win[0] == win[4] && win[4] == win[8] && win[8] != null) {
			System.out.println();
			System.out.println("Player " + win[0] + " wins!!!");
			return true;
		}
		//win rl diag
		else if(win[2] == win[4] && win[4] == win[6] && win[6] != null) {
			System.out.println();
			System.out.println("Player " + win[2] + " wins!!!");
			return true;
		}
		//tie no winner
		else if(a == 1 && b==1 && c==1 && d==1 && e==1 && f==1 && g==1 && h==1 && k==1) {
			System.out.println();
			System.out.println("GAME TIED");
			return true;
		}
	
		return false;
	}
	
	//check to see if there is a winner
	private String isWinner() {
		if(a == 0) {
			if(board0Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #0");
				win[0] = this.marks[this.currentPlayerIndex];
				a++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(b == 0) {
			if(board1Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #1");
				win[1] = this.marks[this.currentPlayerIndex];
				b++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(c == 0) {
			if(board2Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #2");
				win[2] = this.marks[this.currentPlayerIndex];
				c++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(d == 0) {
			if(board3Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #3");
				win[3] = this.marks[this.currentPlayerIndex];
				d++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(e == 0) {
			if(board4Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #4");
				win[4] = this.marks[this.currentPlayerIndex];
				e++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(f == 0) {
			if(board5Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #5");
				win[5] = this.marks[this.currentPlayerIndex];
				f++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(g == 0) {
			if(board6Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #6");
				win[6] = this.marks[this.currentPlayerIndex];
				g++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(h == 0) {
			if(board7Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #7");
				win[7] = this.marks[this.currentPlayerIndex];
				h++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		if(k == 0) {
			if(board8Win()) { 
				System.out.println(this.marks[this.currentPlayerIndex] + " wins board #8");
				win[8] = this.marks[this.currentPlayerIndex];
				k++;
				return this.marks[this.currentPlayerIndex];	
			}
		}
		
		return "";
	}

	//check who win the zero board
	private boolean board0Win() {
		//checkDiagRL
		if(game[0][0].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[0][0].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[0][0].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[0][0].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[0][0].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[0][0].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[0][0].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[0][0].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//check if theres a winner on the 1st mini board of the ultimate ttt board 
	private boolean board1Win() {
		//checkDiagRL
		if(game[0][1].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[0][1].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[0][1].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[0][1].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[0][1].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[0][1].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[0][1].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[0][1].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
		
	
	//check if theres a winner on the 2nd mini board of the ultimate ttt board 
	private boolean board2Win() {
		//checkDiagRL
		if(game[0][2].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[0][2].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[0][2].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[0][2].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[0][2].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[0][2].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[0][2].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[0][2].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//check if theres a winner on the 3rd mini board of the ultimate ttt board 
	private boolean board3Win() {
		//checkDiagRL
		if(game[1][0].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[1][0].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[1][0].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[1][0].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[1][0].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[1][0].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[1][0].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[1][0].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//check if theres a winner on the 4th mini board of the ultimate ttt board 
	private boolean board4Win() {
		//checkDiagRL
		if(game[1][1].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[1][1].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[1][1].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[1][1].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[1][1].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[1][1].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[1][1].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[1][1].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//check if theres a winner on the 5th mini board of the ultimate ttt board 
	private boolean board5Win() {
		//checkDiagRL
		if(game[1][2].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[1][2].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[1][2].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[1][2].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[1][2].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[1][2].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[1][2].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[1][2].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//check if theres a winner on the 6th mini board of the ultimate ttt board 
	private boolean board6Win() {
		//checkDiagRL
		if(game[2][0].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[2][0].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[2][0].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[2][0].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[2][0].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[2][0].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[2][0].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[2][0].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}

	//check if theres a winner on the 7th mini board of the ultimate ttt board	
	private boolean board7Win() {
		//checkDiagRL
		if(game[2][1].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[2][1].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[2][1].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[2][1].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[2][1].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[2][1].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[2][1].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[2][1].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//check if theres a winner on the 8th mini board of the ultimate ttt board 
	private boolean board8Win() {
		//checkDiagRL
		if(game[2][2].getMark(2,0).equals(players[currentPlayerIndex].getMark()) && game[2][2].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[2][2].getMark(0,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//checkDiagLR
		if(game[2][2].getMark(0,0).equals(players[currentPlayerIndex].getMark()) && game[2][2].getMark(1,1).equals(players[currentPlayerIndex].getMark()) && game[2][2].getMark(2,2).equals(players[currentPlayerIndex].getMark())){
			return true;
		}
		//iterate through each complete column and check if the marks in all 3 are of the same kind
		// checkCol
		int count = 0;
		int col = 0;
		for(int row = 0 ; col < 3 && row < 3; row++) {
			if(game[2][2].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				col++;
				row = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		//iterate through each complete row and check if the marks in all 3 are of the same kind
		//checkRow
		count = 0;
		int row = 0;
		for(col = 0; row < 3 && col < 3; col++) {
			if(game[2][2].getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				count++;
			}
			else {
				row++;
				col = -1;
				count = 0;
			}
		}
		if(count == scoreToWin) {
			return true;
		}
		return false;
	}
	
	//alternate turns for players
	private void switchPlayer() {
		if(this.currentPlayerIndex == -1 || this.currentPlayerIndex ==1)
			this.currentPlayerIndex = 0;
		else this.currentPlayerIndex = 1;
	}

	//create players and indicate which is the "X" and the "O"
	private void setPlayers() {
		for(int i =0; i < players.length; i++) {
			Player p = new Player("player" + i+1 , marks[i]);
			players[i] = p;
		}
	}
	
	//display a list of available moves
	private void printAvailable() {
		int count = 0;
		for(int i = 0; i<3;i++) {
			for(int j = 0; j<3;j++) {
				if(game[0][0].isAvailable(i,j)) {
					System.out.print("Board #0 square " + count + " is open.  ");
				}
				if(game[0][1].isAvailable(i,j)) {
					System.out.print("Board #1 square " + count + " is open.  ");
				}
				if(game[0][2].isAvailable(i,j)) {
					System.out.print("Board #2 square " + count + " is open.  ");
				}
				if(game[1][0].isAvailable(i,j)) {
					System.out.print("Board #3 square " + count + " is open.  ");
				}
				if(game[1][1].isAvailable(i,j)) {
					System.out.print("Board #4 square " + count + " is open.  ");
				}
				if(game[1][2].isAvailable(i,j)) {
					System.out.print("Board #5 square " + count + " is open.  ");
				}
				if(game[2][0].isAvailable(i,j)) {
					System.out.print("Board #6 square " + count + " is open.  ");
				}
				if(game[2][1].isAvailable(i,j)) {
					System.out.print("Board #7 square " + count + " is open.  ");
				}
				if(game[2][2].isAvailable(i,j)) {
					System.out.println("Board #8 square " + count + " is open.  ");
				}
				count++;
			}
		}
	}

	//print out the entire board
	public void print() {
		System.out.print("	");game[0][0].printRow1();System.out.print("	");game[0][1].printRow1();System.out.print("	");game[0][2].printRow1();
		System.out.println();
		System.out.print("	");game[0][0].printRow2();System.out.print("	");game[0][1].printRow2();System.out.print("	");game[0][2].printRow2();
		System.out.println();
		System.out.print("	");game[0][0].printRow3();System.out.print("	");game[0][1].printRow3();System.out.print("	");game[0][2].printRow3();
		System.out.println();
		System.out.println();
		System.out.print("	");game[1][0].printRow1();System.out.print("	");game[1][1].printRow1();System.out.print("	");game[1][2].printRow1();
		System.out.println();
		System.out.print("	");game[1][0].printRow2();System.out.print("	");game[1][1].printRow2();System.out.print("	");game[1][2].printRow2();
		System.out.println();
		System.out.print("	");game[1][0].printRow3();System.out.print("	");game[1][1].printRow3();System.out.print("	");game[1][2].printRow3();
		System.out.println();
		System.out.println();
		System.out.print("	");game[2][0].printRow1();System.out.print("	");game[2][1].printRow1();System.out.print("	");game[2][2].printRow1();
		System.out.println();
		System.out.print("	");game[2][0].printRow2();System.out.print("	");game[2][1].printRow2();System.out.print("	");game[2][2].printRow2();
		System.out.println();
		System.out.print("	");game[2][0].printRow3();System.out.print("	");game[2][1].printRow3();System.out.print("	");game[2][2].printRow3();
		System.out.println();
	}
	
}

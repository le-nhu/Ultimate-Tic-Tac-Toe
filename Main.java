package tttProject;
/*Nhu Le		2336.006
 * 				Analysis
 * The program must create a boardgame of tic tac toe where the overall general board of 3x3 is composed
 * of indivudual tic tac toe board. Players alternate turns where the current player must make a move on the board 
 * number of the previous player's square. The only time a player may pick the board is if it's the very first move
 * of the game of the board to be is already completely filled, only then can a player choose a board with availibility.
 * Program will be able to run both a player vs player game as well as player vs ai. With the two options of player, there
 * will also be the option of the game where legal moves is displayed each turn. Winner is calculated using the overall
 * board game where if there is 3 in a row of the same mark then that mark is the winning player. The smaller boards
 * represents the winning mark in each of the 9 squares of the overall board. To win the overall game players must win
 * the mini ttt games and get 3 wins on 3 boards in a row. Given that every square is fill and there is no winner, the
 * game ends with a draw.
 * 				Design
 * The overall board game titled "game" is a 3x3 array composed of individual 3x3 arrays that represents the 9 board.
 * Start of the game, user will input an int to represent what type of gameplay desired. One of which is to display the
 * list of all avalable moves which is done using double for loops to go through the 3x3 array and using if statements
 * with the availability method from the boardclass. If it returns true for availablle then display it. The first move of the game will
 * take in two ints, one to represent the chosen board and the other the square in the chosen board to make a move. The sqaure
 * chosen will test availability and if available, mark the spot otherwise continue to get a new sqaure. After the mark
 * the game switches player and only one int is entered, the square, and the same thing continues until the do while loop
 * is exited once a winner is found or the game is tied. The board display is seen using 3 methods in the board class
 * where each individual row of the 3 row is displayed. To win the game each indivudal board has a method that tests
 * to see if there is a win in any column, rows, or diagonal and if there is a win take the mark and place it in an array of length
 * 9 and each board# is associated with the index of the length 9 array. If there is 3 in a row of the length 9 array
 * where the shape is of any row,col,or diagonal win like thatt of the ttt board then a winner is found and return thus ending the game.
 * 				Test
 * Test conducted using the sample run of when x wins as well as o win that was provided along with tie. I used the
 * player vs player to simulate the exact move in which follows the sample game provided. Using the player
 * vs AI, I ran random numbers for the player to test that the computer part operated correctly with valid input.
 * The providing list of legal moves worked for both player vs player and player vs ai as it's implemented in the
 * beginning to let user pick what type of game is wanted. The legal moves generates from going through
 * each individual squares and seeing whether or not a mark is placed. Furthermore I tested the validity of 
 * movement of next player board from current player board by seeing if the current player's chosen square is valid
 * and if not then to reenter. They will continue until a valid sqaure is entered and only then will a mark be placed
 * and the selectedBoard becomes the valid square thus no matter how many time an invalid sqaure is entered only
 * the valid one will become the board for the next player. The test that was from both sample run and random worked.
 * 
 */
public class Main {
	public static void main (String arg[]) {
		UltimateBoard test = new UltimateBoard();
	}
}

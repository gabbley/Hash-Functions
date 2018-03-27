import java.util.Arrays;

public class TicTacToe {
	public final static int ROWS = 3;
	public final static int COLS = 3;
	public final static int POSSIBILITIES = (int) Math.pow(3, 9);
	public final static int CHAR_POSSIBILITIES = 3; // x, o or space

	/**
	 * Returns number of occurrences of specified char 
	 * 
	 * @param b
	 * 		matrix to search through for char occurrence
	 * 
	 * @param ch
	 * 		char to search for
	 * 
	 * @return int
	 * 		total number of chars 
	 */
	private static int numChars(char[][] b, char ch) {
		int total = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++)
				if (ch == b[r][c])
					total++;
		return total;
	}

	/**
	 * Ensures the boardString is a valid combination
	 * 
	 * @param board
	 * 		matrix that represents the TicTacToe board
	 * 
	 * @return boolean
	 * 		true if valid, false if not
	 */
	public static boolean valid(char[][] board) {

		// Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
		// Make sure there are at least one more x or one more o
		int numX = numChars(board, 'x');
		int numO = numChars(board, 'o');
		if (!(numX > 2 || numO > 2))
			return false;
		if ((numX == numO + 1) || (numO == numX + 1))
			return true;
		return false;
	}

	/**
	 * Converts TicTacToe board matrix to a String
	 * 
	 * @param b
	 * 		matrix to convert to String
	 * 
	 * @return String
	 * 		final boardString of length 9
	 */
	public static String boardToString(char[][] b) {
		String result = "";
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++)
				result += b[r][c];
			// result += "\n";
		}
		return result;
	}

	/**
	 * Converts boardString to a char matrix
	 * 
	 * @param board
	 * 		String to convert to char matrix
	 * 
	 * return char[][]
	 * 		final converted char[][]
	 */
	public static char[][] stringToBoard(String board) {
		char[][] b = new char[ROWS][COLS];
		int index = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++)
				b[r][c] = whichLetter(board.charAt(index++));
		}
		return b;
	}

	/**
	 * Determines which  ???? IDK
	 * 
	 * @param ch
	 * 
	 * @return ch
	 */
	public static char whichLetter(char ch) {
		switch (ch) {
		case '1':
			return 'x';
		case '2':
			return 'o';
		case '0':
			return ' ';
		default:
			return ch;
		}
	}

	/**
	 * Creates a char matrix from a boardString (hashcode?)
	 * 
	 * @param s
	 * 		boardString to create char matrix from
	 * 
	 * @return char[][]
	 * 		final char matrix/board
	 */
	public static char[][] makeBoard(String s) {
		char[][] b = new char[ROWS][COLS];
		int ch = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++) {
				b[r][c] = whichLetter(s.charAt(ch));
				ch++;
			}
		return b;
	}

	/**
	 * idk
	 * 
	 * @param s
	 * 
	 * @return String
	 * 		
	 */
	private static String addOne(String s) {
		// s is a 9 character string, composed of 0s, 1s, and 2s. Add 1 to the last
		// char, adjusting
		// all the rest of the characters as necessary.
		boolean carry = false;
		char ch[] = s.toCharArray();
		ch[ch.length - 1] = (char) ((int) (ch[ch.length - 1]) + 1);
		for (int n = ch.length - 1; n >= 0; n--) {
			if (carry)
				ch[n] = (char) ((int) (ch[n]) + 1);
			if (ch[n] == '3') {
				carry = true;
				ch[n] = '0';
			} else
				carry = false;
		}
		return new String(ch);
	}

	/**
	 * idk either lol
	 * 
	 * @return String[]
	 * 		
	 */
	public static String[] fillValues() {
		String strBoard = "000000000";
		String[] values = new String[POSSIBILITIES];
		int index = 0;
		values[index++] = strBoard;
		while (!strBoard.equals("222222222")) {
			strBoard = addOne(strBoard);
			values[index++] = strBoard;
		}
		return values;
	}

	/**
	 * Checks for a diagonal TicTacToe win
	 * 
	 * @param board
	 * 		char matrix/TicTacToe board
	 * 
	 * @return boolean
	 * 		returns true if a winning diagonal, false otherwise
	 */
	private static boolean diagonalWin(char[][] board) {

		if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x')
				|| (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) {
			return true;
		} else if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x')
				|| (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) {
			return true;
		}
		return false;
	}

	/**
	 * Checks for a horizontal TicTacToe win
	 * 
	 * @param board
	 * 		char matrix/TicTacToe board
	 * 
	 * @return boolean
	 * 		returns true if a winning row, false otherwise
	 */
	private static boolean rowWin(char[][] board) {
		char ch;
		for (int r = 0; r < ROWS; r++) {
			ch = board[r][0];
			for (int c = 0; c < COLS; c++)
				if (ch != board[r][c])
					return false;
		}
		return true;
	}

	/**
	 * Checks for a vertical TicTacToe win
	 * 
	 * @param board
	 * 		char matrix/TicTacToe board
	 * 
	 * @return boolean
	 * 		returns true if a winning column, false otherwise
	 */
	private static boolean colWin(char[][] board) {
		char ch;
		for (int c = 0; c < COLS; c++) {
			ch = board[0][c];
			for (int r = 0; r < ROWS; r++)
				if (ch != board[r][c])
					return false;
		}
		return true;
	}

	/**
	 * Checks for any valid winning board
	 * 
	 * @param b
	 * 		char matrix/TicTacToe board
	 * 
	 * @return boolean
	 * 		returns true if any type of winning board, false otherwise
	 */
	public static boolean isWin(char[][] b) {
		return valid(b) && (rowWin(b) || colWin(b) || diagonalWin(b));
	}

	/**
	 * Checks for any valid winning boardString
	 * 
	 * @param s
	 * 		boardString representation of board matrix
	 * 
	 * @return boolean
	 * 		returns true if any type of winning board, false otherwise
	 */
	public static boolean isWin(String s) {
		char[][] b = stringToBoard(s);
		return isWin(b);
	}
}

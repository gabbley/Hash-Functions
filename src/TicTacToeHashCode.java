import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TicTacToeHashCode extends Board {

	public static String filename;

	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
		winners = new boolean[(int) Math.pow(3, 9)];
		// TODO Instantiate winners array
	}

	@Override
	public int myHashCode() {

		int[][] pows3 = new int[][] { { 1, 3, 9 }, { 27, 81, 243 }, { 729, 2187, 6561 } };

		int sum = 0;
		for (int row = 0; row < pows3.length; row++) {
			for (int col = 0; col < pows3[row].length; col++) {
				char c = super.charAt(row, col);
				if (c == 'o')
					sum += pows3[row][col] * 1;
				else if (c == 'x')
					sum += pows3[row][col] * 2;
			}
		}
		return sum;
	}

	@Override
	public boolean isWin(String s) {
		// TODO write an isWin method that takes in a String. This should not change the
		// board. Board has an additional charAt
		// TODO method to facilitate this
		super.setBoardString(s);
		return winners[myHashCode()];
	}

	@Override
	public boolean isWin() {
		// TODO write an isWin method that uses boardString
		isWin(super.getBoardString());
		return false;
	}

	/**
	 * Opens file for reading
	 * 
	 * @param filename
	 *            name of file
	 * 
	 * @return Scanner file to be read as Scanner Object
	 */
	public static Scanner openFile(String filename) {

		File f = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		return input;
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		while (true) {
			board.displayRandomString();
			Thread.sleep(4000);
			
			Scanner kb = new Scanner(System.in);

			if (args.length < 1) {
				filename = "TicTacToeWinners.txt";
				if (openFile(filename) == null) {
					System.out.println("Please enter file name");
					filename = kb.next();
				}

				kb.close();
			} else {
				System.out.println("File Found");
				filename = args[0];
			}

			Scanner in = openFile(filename);

			if (in == null)
				System.exit(1);
			else {
				while (in.hasNextLine()) {
					//TODO yeah idk lolol
				}
			}
		}
	}

}

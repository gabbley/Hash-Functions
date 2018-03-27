
//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
      //TODO Instantiate winners array
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
      // TODO write an isWin method that takes in a String.  This should not change the board.  Board has an additional charAt 
      // TODO method to facilitate this
		
		return winners[myHashCode()];
      }
      
	@Override
	public boolean isWin() {
      // TODO write an isWin method that uses boardString
		return false;
      }
      
	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		 while (true) {
		   board.displayRandomString();
		   Thread.sleep(4000);
		 }
	}

}

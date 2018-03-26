//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

 boolean [] winners;  // True if the hash string that maps to this index is a winner, false otherwise
  
  
  // TODO remove the code in this method and replace it with code that 
  // TODO is appropriate for the assignment
  TicTacToeHashCode(String s) {
   super(s);
   // TODO Instantiate/fill winners array.  
   winners = new boolean[45000];
   for (int i = 0; i < winners.length; i++) {
     if (i %450 == 0) 
       winners[i] = true;
     else
       winners[i] = false;
   }
  }
  
  // TODO - write the myHashCode function.  It must create a unique hashcode for all of the 
  //   possible values the game board (3 ^ 9) and it MUST use the super.charAt(row, col) function
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
    // TODO return the value in the winner array for the hash chode of the board string sent in.
	   super.setBoardString(s);
	   return winners[myHashCode()];
    }
    
    @Override
    public boolean isWin() {
       // TODO return the value in the winner array for the hash chode of the current board string.
   //   return isWin(super.getBoardString());
    	return true;
    }
  
   public static void main(String[] args) throws InterruptedException {
      TicTacToeHashCode board = new TicTacToeHashCode ("Tic Tac Toe");
      while (true) {
         board.displayRandomString();      
         Thread.sleep(4000);      
      }
   }
 }  
  
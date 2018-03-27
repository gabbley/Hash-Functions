import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class Board extends JFrame implements ActionListener {

	private JButton buttons[][];
	private JLabel lblHashCode;
	private JLabel lblWinTitle;

	private String boardString = "";

	/**
	 * Constructor for Board
	 * 
	 * @param title
	 *            String of title of frame
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}

	/**
	 * Sets the label that displays the hashcode
	 * 
	 * @param hashcode
	 *            int representation of hashcode
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * Sets the label that displays winner or loser
	 * 
	 * @param result
	 *            String result of game: either Winner or Loser
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}

	/**
	 * Determines the label that displays winner or loser
	 * 
	 * @param result
	 *            boolean result of game: either winner or loser
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}

	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}

	/**
	 * Switches ??????????????? 
	 * 
	 * @param ch
	 *            i literally don't know
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}

	/**
	 * Sets up GUI for program
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}


	/**
	 * Returns random char for TicTacToe board
	 * 
	 * @return char
	 * 		returns a random TicTacToe char
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}

	abstract int myHashCode();

	abstract boolean isWin(String s);

	abstract boolean isWin();


	/**
	 * Returns character at current index in the matrix
	 * 
	 * @param row
	 *            index for the current row
	 *            
	 * @param col
	 *            index for the current column
	 * 
	 * @return char
	 * 			  char at current index in the matrix
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}
   
	/**
	 * Returns character at current index in the matrix **SPECIFY THIS
	 * 
	 * @param s
	 * 			  current TicTacToe String on the board
	 * 
	 * @param row
	 *            index for the current row
	 *            
	 * @param col
	 *            index for the current column
	 *           
	 * @return char
	 * 				char at current index in the matrix
	 */
   public char charAt(String s, int row, int col) {
     int pos = row * TicTacToe.COLS + col;
     if (s.length() >= pos)
       return s.charAt(pos);
     else
       return '*';   
   }

	/**
	 * Shows appropriate TicTacToe chars on the buttons
	 * 
	 * @param s
	 *         current TicTacToe String on the board
	 */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

	
	/**
	 * Resets the current boardString
	 */
	public void resetBoardString() {
   boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}
	
	/**
	 * Sets the boardString
	 * 
	 * @param s
	 *         boardString to set
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}

	/**
	 * Gets and returns the boardString
	 * 
	 * @return String boardString
	 */
	public String getBoardString() {
		return boardString;
	}

	/**
	 * Displays a random String
	 * 
	 * @return String boardString
	 */
	public void displayRandomString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}
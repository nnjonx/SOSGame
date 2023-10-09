import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;



public class SOSGUI extends JFrame {
	private JPanel contentPane;
	private JFrame frame;
	private JButton[][] boardButtons;
	private JTextField brdSizeTxtField;
	private Board board;
	private JLabel playerTurnLabel;
	private JPanel boardPanel;
	private int boardSize = 3;
	private boolean blueChoosesS = true;
	private boolean redChoosesS = true;
	private String blueLetter = "S";
	private String redLetter = "O";
	private String gameMode = "Simple Game";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SOSGUI frame = new SOSGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public SOSGUI() {
		board = new Board(boardSize);
		
		getContentPane().setForeground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,861,579);
		
		setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
		boardPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		boardPanel.setBackground(new Color(240, 240, 240));
		boardPanel.setBounds(192, 79, 458, 411);
		getContentPane().add(boardPanel);
		
		boardButtons = new JButton[boardSize][boardSize];
		initBoardButtons();
		
		JLabel sosLabel = new JLabel("SOS");
		sosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sosLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sosLabel.setBounds(10, 10, 60, 59);
		getContentPane().add(sosLabel);
		
		JRadioButton simpleGameRdBtn = new JRadioButton("Simple Game");
		simpleGameRdBtn.setSelected(true);
		simpleGameRdBtn.setBackground(new Color(255, 255, 255));
		simpleGameRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		simpleGameRdBtn.setBounds(76, 27, 169, 29);
		getContentPane().add(simpleGameRdBtn);
		
		JRadioButton genGameRdBtn = new JRadioButton("General Game");
		genGameRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		genGameRdBtn.setBackground(Color.WHITE);
		genGameRdBtn.setBounds(276, 27, 179, 29);
		getContentPane().add(genGameRdBtn);
		
		ButtonGroup gameMode = new ButtonGroup();
		gameMode.add(simpleGameRdBtn);
		gameMode.add(genGameRdBtn);
		
		JLabel brdSizeLabel = new JLabel("Board Size");
		brdSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		brdSizeLabel.setBounds(484, 10, 163, 59);
		getContentPane().add(brdSizeLabel);
		
		brdSizeTxtField = new JTextField();
		brdSizeTxtField.setHorizontalAlignment(SwingConstants.CENTER);
		brdSizeTxtField.setText("8");
		brdSizeTxtField.setBackground(new Color(255, 255, 255));
		brdSizeTxtField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		brdSizeTxtField.setBounds(646, 17, 60, 46);
		getContentPane().add(brdSizeTxtField);
		brdSizeTxtField.setColumns(10);
		
		JLabel bluePlayerLabel = new JLabel("Blue Player");
		bluePlayerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bluePlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bluePlayerLabel.setBounds(10, 133, 108, 59);
		getContentPane().add(bluePlayerLabel);
		
		
		JRadioButton humanBRdBtn = new JRadioButton("Human");
		humanBRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		humanBRdBtn.setBackground(Color.WHITE);
		humanBRdBtn.setBounds(10, 184, 97, 29);
		getContentPane().add(humanBRdBtn);
		
		JRadioButton sBRdBtn = new JRadioButton("S");
		sBRdBtn.setSelected(true);
		sBRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sBRdBtn.setBackground(Color.WHITE);
		sBRdBtn.setBounds(33, 215, 37, 29);
		getContentPane().add(sBRdBtn);
		
		sBRdBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        blueChoosesS = true;
		    }
		});
		
		JRadioButton oBRdBtn = new JRadioButton("O");
		oBRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oBRdBtn.setBackground(Color.WHITE);
		oBRdBtn.setBounds(31, 246, 39, 29);
		getContentPane().add(oBRdBtn);
		
		oBRdBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        blueChoosesS = false;
		    }
		});
		
		ButtonGroup blueSOGroup = new ButtonGroup();
		blueSOGroup.add(sBRdBtn);
		blueSOGroup.add(oBRdBtn);
		
		JRadioButton computerBRdBtn = new JRadioButton("Computer");
		computerBRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		computerBRdBtn.setBackground(Color.WHITE);
		computerBRdBtn.setBounds(10, 277, 124, 29);
		getContentPane().add(computerBRdBtn);
		
		JCheckBox recordGameChckbx = new JCheckBox("Record game");
		recordGameChckbx.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recordGameChckbx.setBackground(new Color(255, 255, 255));
		recordGameChckbx.setBounds(14, 508, 169, 29);
		getContentPane().add(recordGameChckbx);
		
		JLabel redPlayerLabel = new JLabel("Red Player");
		redPlayerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		redPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		redPlayerLabel.setBounds(695, 133, 108, 59);
		getContentPane().add(redPlayerLabel);
		
		JRadioButton humanRRdBtn = new JRadioButton("Human");
		humanRRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		humanRRdBtn.setBackground(Color.WHITE);
		humanRRdBtn.setBounds(695, 184, 97, 29);
		getContentPane().add(humanRRdBtn);
		
		JRadioButton sRRdBtn = new JRadioButton("S");
		sRRdBtn.setSelected(true);
		sRRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sRRdBtn.setBackground(Color.WHITE);
		sRRdBtn.setBounds(716, 215, 37, 29);
		getContentPane().add(sRRdBtn);
		
		sRRdBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        redChoosesS = true;
		    }
		});
		
		JRadioButton oRRdBtn = new JRadioButton("O");
		oRRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oRRdBtn.setBackground(Color.WHITE);
		oRRdBtn.setBounds(714, 246, 39, 29);
		getContentPane().add(oRRdBtn);

		oRRdBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        redChoosesS = false;
		    }
		});
		
		
		ButtonGroup redSOGroup = new ButtonGroup();
		redSOGroup.add(sRRdBtn);
		redSOGroup.add(oRRdBtn);
		
		JRadioButton computerRRdBtn = new JRadioButton("Computer");
		computerRRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		computerRRdBtn.setBackground(Color.WHITE);
		computerRRdBtn.setBounds(695, 277, 124, 29);
		getContentPane().add(computerRRdBtn);
		
		JButton replayButton = new JButton("Replay");
		replayButton.setBackground(new Color(240, 240, 240));
		replayButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		replayButton.setBounds(695, 461, 135, 33);
		getContentPane().add(replayButton);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.setBackground(new Color(240, 240, 240));
		newGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newGameButton.setBounds(695, 499, 135, 33);
		getContentPane().add(newGameButton);
		
		JLabel currentTurnLabel = new JLabel("Current turn:");
		currentTurnLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentTurnLabel.setBounds(250, 508, 115, 29);
		getContentPane().add(currentTurnLabel);
		
		playerTurnLabel = new JLabel("blue");
		playerTurnLabel.setForeground(Color.BLUE);
		playerTurnLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerTurnLabel.setBounds(368, 508, 45, 29);
		getContentPane().add(playerTurnLabel);
		
		JButton applySizeBtn = new JButton("Apply Size\r\n");
		applySizeBtn.setForeground(new Color(0, 0, 0));
		applySizeBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		applySizeBtn.setBounds(716, 10, 121, 59);
		getContentPane().add(applySizeBtn);
		applySizeBtn.addActionListener(e -> reconstructBoardUI());

		board.setTurn('R'); // Set the initial turn to blue
		playerTurnLabel.setText("blue"); // Update the playerTurnLabel
		playerTurnLabel.setForeground(Color.BLUE);
		

	}
	
	//code for applying new board size
	private void reconstructBoardUI() {
	    int newSize;
	    try {
	        newSize = Integer.parseInt(brdSizeTxtField.getText().trim());
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Invalid entry please try again.");
	        return;
	    }

	    if (newSize < 3) {
	        JOptionPane.showMessageDialog(this, "Please enter a size that's at least 3.");
	        return;
	    }

	    boardPanel.removeAll();

	    boardSize = newSize;
	    board = new Board(boardSize);
	    board.setTurn('R');
	    playerTurnLabel.setText("blue");
        playerTurnLabel.setForeground(Color.BLUE);

	    boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
	    boardPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    boardPanel.setBackground(new Color(240, 240, 240));
	    boardPanel.setBounds(192, 79, 458, 411);

	    boardButtons = new JButton[boardSize][boardSize];
	    initBoardButtons();

	    getContentPane().add(boardPanel);

	    boardPanel.revalidate();
	    boardPanel.repaint();
	}
	
	//creates the board with empty buttons
    private void initBoardButtons() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton btn = new JButton("");
                btn.addActionListener(new ButtonListener(i, j));
                boardPanel.add(btn);
                boardButtons[i][j] = btn;
            }
        }
    }
    private class ButtonListener implements ActionListener {
        private int row;
        private int column;

        public ButtonListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        //code for whether the player wants to place an S or O
        public void actionPerformed(ActionEvent e) {
            board.makeMove(row, column);
            board.checkWin(row, column, gameMode );
            if (board.getTurn() == 'B') {
                boardButtons[row][column].setText(blueChoosesS ? "S" : "O");
            } else {
                boardButtons[row][column].setText(redChoosesS ? "S" : "O");
            }
            toggleTurn();
            boardButtons[row][column].setEnabled(false);
        }
    }
    
    
    //code for handling turns
    private void toggleTurn() {
        if (board.getTurn() == 'R') {
        	playerTurnLabel.setText("blue");
            playerTurnLabel.setForeground(Color.BLUE);
        } else {
        	playerTurnLabel.setText("red");
            playerTurnLabel.setForeground(Color.RED);
        }
    }
}

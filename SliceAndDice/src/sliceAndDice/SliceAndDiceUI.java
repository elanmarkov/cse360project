package sliceAndDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EventListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

//enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};

public class SliceAndDiceUI {
	private final int MAX_HEIGHT = 500;
	private final int MAX_WIDTH = 1000;
	private final int MAX_HEALTH = 100;
	private final int MIN_HEALTH = 0;
	private final int MAX_MANA = 30;
	private final int MIN_MANA = 0;
	private final int MAX_FOOD = 5;
	private final int MIN_FOOD = 0;
	private static int player;
	private String usernameOne;
	private String usernameTwo;
	private String computer = "Evil AI Bot";
	private JSplitPane splitPane;
	private JSplitPane playerPane;
	
	public String tempUser = "";
	public int plOneHlth = 100;
	public int plOneMana = 100;
	public int plOneFood = 100;
	public int plTwoHlth = 100;
	public int plTwoMana = 100;
	public int plTwoFood = 100;
	
	int debug = 0;
	
	Game game;
	
	//Scoreboard gameBoard = new  Scoreboard();
	
	Winner winner = Winner.NONE;
	
		/**
		 * Gets String of installed LAF based on selection String passed as parameter
		 * 
		 * @param lafString
		 * @return String info.getClassName from OS
		 */
		public static String getLookAndFeelClassName(String lafString){
			LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
			for(LookAndFeelInfo info : plafs){
				if(info.getName().contains(lafString)){
					return info.getClassName();
				}
			}
			return null;
		}
		
		/**
		 * Keep track of who's turn it is
		 * 
		 * 1 for player one, 0 otherwise
		 * 
		 * @param plyer
		 */
		public static void setPlayer(int plyer){
			player = plyer;
		}
		
		public static int getPlayer(){
			return player;
		}
		
		public void delayTime(){
			try{
				Thread.sleep(3000);
			}catch(Exception e){}
		}
		
/*
* Show game UI
*/
		public void showUI(){
			
			final ChooseFirst whichPlayer = new ChooseFirst();
			//gameBoard.getPlayerDataFromFile();
/*
 * Container frame
 */
			final JFrame gameFrame = new JFrame("Slice And Dice");
				gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					gameFrame.addWindowListener(new WindowAdapter(){
							public void windowClosing(WindowEvent e){
								
								// TODO add closing actions
								
								e.getWindow().dispose();
								System.exit(0);
							}
					});
				gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png")));
				gameFrame.setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
				
				/*
				 * Get image and add to panel
				 */
				Icon mainPanelDieAnim = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/dice_roll_0.gif"));
				
				final JLabel imageLabel = new JLabel(mainPanelDieAnim);
				
				final JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
					imagePanel.add(imageLabel, BorderLayout.CENTER);
					
				/*
				 * Fonts
				 */
				Font bigButtonFont = new Font("Trebuchet MS", Font.BOLD, 32);
				Font labelFont = new Font("Trebuchet MS", Font.BOLD, 16);
				Font smallLabelFont = new Font("Trebuchet MS", Font.BOLD, 12);
				Font verySmallLabelFont = new Font("Trebuchet MS", Font.BOLD, 10);
				Font smallButtonFont = new Font("Trebuchet MS", Font.BOLD, 16);
				Font largeLabelFont = new Font("Trubuchet MS", Font.BOLD, 24);
				
				/*
				 * Progress bar override default objects
				 */
				final UIDefaults greenDefaults = new UIDefaults();
					greenDefaults.put("ProgressBar[Enabled].foregroundPainter", new FillPainter(new Color(0, 204, 0)));
					greenDefaults.put("ProgressBar[Enabled+Finished].foregroundPainter", new FillPainter(new Color(0, 204, 0)));
				
				final UIDefaults redDefaults = new UIDefaults();
					redDefaults.put("ProgressBar[Enabled].foregroundPainter", new FillPainter(new Color(255, 0, 0)));
					redDefaults.put("ProgressBar[Enabled+Finished].foregroundPainter", new FillPainter(new Color(255, 0, 0)));
					
				final UIDefaults orangeDefaults = new UIDefaults();
					orangeDefaults.put("ProgressBar[Enabled].foregroundPainter", new FillPainter(new Color(240, 174, 41)));
					orangeDefaults.put("ProgressBar[Enabled+Finished].foregroundPainter", new FillPainter(new Color(240, 174, 41)));

/*
 * Mode select panel components
 */
			/*
			 * Create mode select panels
			 */
			final JPanel modePanel = new JPanel(new BorderLayout(5, 5));
				modePanel.setBorder(BorderFactory.createTitledBorder(null, "Select Mode", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			
			final JPanel modePanelButtons = new JPanel(new GridLayout(4, 1, 2, 5));
			
				/*
				 * Mode select buttons
				 */
				final JButton newGameButton = new JButton("New Game");
					newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					newGameButton.setFont(bigButtonFont);
					newGameButton.setForeground(Color.red);
					newGameButton.setToolTipText("Start new game");
					
//				final JButton loadGameButton = new JButton("Load Game");
//					loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//					loadGameButton.setFont(bigButtonFont);
//					loadGameButton.setForeground(Color.red);
//					loadGameButton.setToolTipText("Continue previous game");
					
				final JButton playerStatusButton = new JButton("Player Stats");
					playerStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					playerStatusButton.setFont(bigButtonFont);
					playerStatusButton.setForeground(Color.red);
					playerStatusButton.setToolTipText("View player stats. Requires player username");
				
				final JButton abtGameButton = new JButton("About Game");
					abtGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					abtGameButton.setFont(bigButtonFont);
					abtGameButton.setForeground(Color.red);
					abtGameButton.setToolTipText("Read the rules");
					
				/*
				 * Add buttons to panel
				 */
				modePanelButtons.add(newGameButton);
//				modePanelButtons.add(loadGameButton);
				modePanelButtons.add(playerStatusButton);
				modePanelButtons.add(abtGameButton);
				modePanelButtons.add(new JPanel());

/*
 * New game panel components
 */
			/*
			 * Create new game panels
			 */
			final JPanel newGamePanel = new JPanel(new BorderLayout(5,5));
				newGamePanel.setBorder(BorderFactory.createTitledBorder(null, "New Game", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			
			final JPanel newGameOptionsButtons = new JPanel(new GridLayout(7, 2, 2, 5));
			
			final JPanel newOnePlayerGameUser = new JPanel(new GridLayout(8, 2, 2, 5));
			
			final JPanel newTwoPlayerGameUser = new JPanel(new GridLayout(8, 2, 2, 5));
			
			final JPanel newGamePlayersExist = new JPanel(new GridLayout(7, 1, 2, 5));
				final JPanel notifyTextOne = new JPanel(new BorderLayout(5,5));
				final JPanel notifyTextTwo = new JPanel(new BorderLayout(5,5));
				final JPanel notifyTextThree = new JPanel(new BorderLayout(5,5));
				final JPanel notifyPlayerOne = new JPanel(new BorderLayout(5,5));
				final JPanel notifyPlayerTwo = new JPanel(new BorderLayout(5,5));
				final JPanel notifyPlayerButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			
			/*
			 * Number of players select buttons
			 */
			final JButton onePlayer = new JButton("Single Player");
				onePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
				onePlayer.setFont(smallButtonFont);
				onePlayer.setForeground(Color.red);
				onePlayer.setToolTipText("New one player game");
				
			final JButton twoPlayer = new JButton("Dual");
				twoPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
				twoPlayer.setFont(smallButtonFont);
				twoPlayer.setForeground(Color.red);
				twoPlayer.setToolTipText("New two player game");
				
			final JButton newGameOptionsExit = new JButton("Back");
				newGameOptionsExit.setAlignmentX(Component.CENTER_ALIGNMENT);
				newGameOptionsExit.setFont(smallButtonFont);
				newGameOptionsExit.setForeground(Color.red);
				newGameOptionsExit.setToolTipText("Go back to previous menu");
				
				newGameOptionsButtons.add(onePlayer);
				newGameOptionsButtons.add(twoPlayer);
				for(int i = 2; i < 13; i++){
					newGameOptionsButtons.add(new JPanel());
				}
				newGameOptionsButtons.add(newGameOptionsExit);
			/*
			 * single player option buttons
			 */
			final JLabel newOnePlayerUserName = new JLabel("Enter Username:");
				newOnePlayerUserName.setAlignmentX(Component.LEFT_ALIGNMENT);
				newOnePlayerUserName.setFont(labelFont);
				newOnePlayerUserName.setForeground(Color.red);
				
			final JTextField newOnePlayerUserText = new JTextField();
			
			final JButton newOnePlayerUserStart = new JButton("Start Game");
				newOnePlayerUserStart.setAlignmentX(Component.CENTER_ALIGNMENT);
				newOnePlayerUserStart.setFont(smallButtonFont);
				newOnePlayerUserStart.setForeground(Color.red);
				newOnePlayerUserStart.setToolTipText("Start new one player game");
				
			final JButton newOnePlayerUserExit  = new JButton("Back");
				newOnePlayerUserExit.setAlignmentX(Component.CENTER_ALIGNMENT);
				newOnePlayerUserExit.setFont(smallButtonFont);
				newOnePlayerUserExit.setForeground(Color.red);
				newOnePlayerUserExit.setToolTipText("Go back to previous menu");
				
				newOnePlayerGameUser.add(newOnePlayerUserName);
				newOnePlayerGameUser.add(newOnePlayerUserText);
				for(int i = 2; i<14; i++){
					newOnePlayerGameUser.add(new JPanel());
				}
				newOnePlayerGameUser.add(newOnePlayerUserExit);
				newOnePlayerGameUser.add(newOnePlayerUserStart);
			/*
			 * two player option buttons
			 */
			final JLabel newGameUserName1 = new JLabel("Enter Player 1:");
				newGameUserName1.setAlignmentX(Component.LEFT_ALIGNMENT);
				newGameUserName1.setFont(labelFont);
				newGameUserName1.setForeground(Color.red);
				
			final JTextField userName1Text = new JTextField();
			
			final JLabel newGameUserName2 = new JLabel("Enter Player 2:");
				newGameUserName2.setAlignmentX(Component.LEFT_ALIGNMENT);
				newGameUserName2.setFont(labelFont);
				newGameUserName2.setForeground(Color.red);
				
			final JTextField userName2Text = new JTextField();
			
			final JButton newTwoPlayerUserStart = new JButton("Start Game");
				newTwoPlayerUserStart.setAlignmentX(Component.CENTER_ALIGNMENT);
				newTwoPlayerUserStart.setFont(smallButtonFont);
				newTwoPlayerUserStart.setForeground(Color.red);
				newTwoPlayerUserStart.setToolTipText("Start new two player game");
				
			final JButton newTwoPlayerUserExit  = new JButton("Back");
				newTwoPlayerUserExit.setAlignmentX(Component.CENTER_ALIGNMENT);
				newTwoPlayerUserExit.setFont(smallButtonFont);
				newTwoPlayerUserExit.setForeground(Color.red);
				newTwoPlayerUserExit.setToolTipText("Go back to previous menu");
				
				newTwoPlayerGameUser.add(newGameUserName1);
				newTwoPlayerGameUser.add(userName1Text);
				newTwoPlayerGameUser.add(newGameUserName2);
				newTwoPlayerGameUser.add(userName2Text);
				for(int i = 5; i<15; i++){
					newTwoPlayerGameUser.add(new JPanel());
				}
				newTwoPlayerGameUser.add(newTwoPlayerUserExit);
				newTwoPlayerGameUser.add(newTwoPlayerUserStart);
				
				/*
				 * Player exists panel components
				 */
				final JLabel foundPlayer = new JLabel("The user-name(s):");
				final JLabel foundPlayer2 = new JLabel("already exists.");
				final JLabel foundPlayer3 = new JLabel("Continue with user-name(s) entered?");
					foundPlayer.setFont(labelFont);
					foundPlayer.setAlignmentX(Component.LEFT_ALIGNMENT);
					foundPlayer.setForeground(Color.red);
					
					foundPlayer2.setFont(labelFont);
					foundPlayer2.setAlignmentX(Component.CENTER_ALIGNMENT);
					foundPlayer2.setForeground(Color.red);
					
					foundPlayer3.setFont(labelFont);
					foundPlayer3.setAlignmentX(Component.RIGHT_ALIGNMENT);
					foundPlayer3.setForeground(Color.red);
					
				final JLabel foundPlayerOne = new JLabel();
					foundPlayerOne.setFont(largeLabelFont);
					foundPlayerOne.setAlignmentX(Component.CENTER_ALIGNMENT);
					foundPlayerOne.setForeground(Color.blue);
					
				final JLabel foundPlayerTwo = new JLabel();
					foundPlayerTwo.setFont(largeLabelFont);
					foundPlayerTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
					foundPlayerTwo.setForeground(Color.blue);
				
				final JButton useName = new JButton("Continue");
					useName.setFont(smallButtonFont);
					useName.setAlignmentX(Component.CENTER_ALIGNMENT);
					useName.setForeground(Color.red);
					useName.setToolTipText("Continue using this user name");
					
				final JButton cancelName = new JButton("Back");
					cancelName.setFont(smallButtonFont);
					cancelName.setAlignmentX(Component.CENTER_ALIGNMENT);
					cancelName.setForeground(Color.red);
					cancelName.setToolTipText("Go back to previous menu");
					
					notifyTextOne.add(foundPlayer, BorderLayout.WEST);
					notifyPlayerOne.add(foundPlayerOne, BorderLayout.NORTH);
					notifyPlayerTwo.add(foundPlayerTwo, BorderLayout.SOUTH);
					notifyTextTwo.add(foundPlayer2, BorderLayout.EAST);
					notifyTextThree.add(foundPlayer3, BorderLayout.EAST);
					notifyPlayerButtons.add(cancelName);
					notifyPlayerButtons.add(useName);
					
					newGamePlayersExist.add(notifyTextOne);
					newGamePlayersExist.add(notifyPlayerOne);
					newGamePlayersExist.add(notifyPlayerTwo);
					newGamePlayersExist.add(notifyTextTwo);
					newGamePlayersExist.add(new JPanel());
					newGamePlayersExist.add(notifyTextThree);
					newGamePlayersExist.add(notifyPlayerButtons);

///*
// * Load game panel components
// */
//			
//			/*
//			 * Create load game panels
//			 */
//			final JPanel loadGamePanel = new JPanel(new BorderLayout(5,5));
//				loadGamePanel.setBorder(BorderFactory.createTitledBorder(null, "Load Game", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
//				
//			final JPanel loadGameOptionsPanel = new JPanel(new GridLayout(8, 2, 2, 5));
//			
//			/*
//			 * Load game buttons
//			 */
//			final JLabel loadUserName = new JLabel("Enter Username:");
//				loadUserName.setAlignmentX(Component.LEFT_ALIGNMENT);
//				loadUserName.setFont(labelFont);
//				loadUserName.setForeground(Color.red);
//				
//			final JTextField loadUserNameText = new JTextField();
//				loadUserNameText.setAlignmentX(Component.RIGHT_ALIGNMENT);
//				
//			final JButton loadUserNameFind = new JButton("Find");
//				loadUserNameFind.setAlignmentX(Component.CENTER_ALIGNMENT);
//				loadUserNameFind.setFont(smallButtonFont);
//				loadUserNameFind.setForeground(Color.red);
//				loadUserNameFind.setToolTipText("Find saved game");
//				
//			final JButton loadUserExit = new JButton("Back");
//				loadUserExit.setAlignmentX(Component.CENTER_ALIGNMENT);
//				loadUserExit.setFont(smallButtonFont);
//				loadUserExit.setForeground(Color.red);
//				loadUserExit.setToolTipText("Go back to previous menu");
//				
//				/*
//				 * add buttons to panel
//				 */
//				loadGameOptionsPanel.add(loadUserName);
//				loadGameOptionsPanel.add(loadUserNameText);
//				for(int i = 2; i<14; i++){
//					loadGameOptionsPanel.add(new JPanel());
//				}
//				loadGameOptionsPanel.add(loadUserExit);
//				loadGameOptionsPanel.add(loadUserNameFind);

/*
 * Player stats panel components
 */
			/*
			 * Stats panels
			 */
			final JPanel statsPanel = new JPanel(new BorderLayout(5, 5));
				statsPanel.setBorder(BorderFactory.createTitledBorder(null, "Player Stats", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
				
			final JPanel statPanelOptionsButtons = new JPanel(new GridLayout(8, 2, 2, 5));
			
			final JPanel statPanelSingleUserButtons = new JPanel(new GridLayout(8, 2, 2, 5));
			
			/*
			 * Stats options buttons. One or all users
			 */
			final JButton singleUserStats = new JButton("Single User");
				singleUserStats.setAlignmentX(Component.CENTER_ALIGNMENT);
				singleUserStats.setFont(smallButtonFont);
				singleUserStats.setForeground(Color.red);
				singleUserStats.setToolTipText("Show stats for single user");
			
			final JButton allUserStats = new JButton("All stats");
				allUserStats.setAlignmentX(Component.CENTER_ALIGNMENT);
				allUserStats.setFont(smallButtonFont);
				allUserStats.setForeground(Color.red);
				allUserStats.setToolTipText("Show stats for all users");
				
			final JButton statsExit = new JButton("Back");
				statsExit.setAlignmentX(Component.CENTER_ALIGNMENT);
				statsExit.setFont(smallButtonFont);
				statsExit.setForeground(Color.red);
				statsExit.setToolTipText("Go back to previous menu");
				
				statPanelOptionsButtons.add(singleUserStats);
				statPanelOptionsButtons.add(allUserStats);
				for(int i = 2; i<15; i++){
					statPanelOptionsButtons.add(new JPanel());
				}
				statPanelOptionsButtons.add(statsExit);
				
				
			/*
			 * find single user stats option	
			 */
			final JLabel singleUserStatName = new JLabel("Enter Username:");
				singleUserStatName.setAlignmentX(Component.LEFT_ALIGNMENT);
				singleUserStatName.setFont(labelFont);
				singleUserStatName.setForeground(Color.red);
			
			final JTextField singleUserStatText = new JTextField();
				singleUserStatText.setAlignmentX(Component.RIGHT_ALIGNMENT);
				
			final JButton singleStatExit = new JButton("Back");
				singleStatExit.setAlignmentX(Component.CENTER_ALIGNMENT);
				singleStatExit.setFont(smallButtonFont);
				singleStatExit.setForeground(Color.red);
				singleStatExit.setToolTipText("Go back to previous menu");
				
			final JButton statFind = new JButton("Find");
				statFind.setAlignmentX(Component.CENTER_ALIGNMENT);
				statFind.setFont(smallButtonFont);
				statFind.setForeground(Color.red);
				statFind.setToolTipText("View the selected stats");
				
				/*
				 * add buttons to panel
				 */
				statPanelSingleUserButtons.add(singleUserStatName);
				statPanelSingleUserButtons.add(singleUserStatText);
				for(int i = 2; i<14; i++){
					statPanelSingleUserButtons.add(new JPanel());
				}
				statPanelSingleUserButtons.add(singleStatExit);
				statPanelSingleUserButtons.add(statFind);
				
/*
 * about game panel components
 * 
 * Probably going to open a text file when button is pressed, but heres the space just in case
 */
	
				
/*
 * Gameplay panel
 */
			/*
			 * Main gameplay panel
			 */
			final JPanel gamePlayPanel = new JPanel(new BorderLayout(5, 5));
			
			/*
			 * Top panels
			 */
			final JPanel topGamePanel = new JPanel(new BorderLayout(5, 5));
				topGamePanel.setBorder(BorderFactory.createTitledBorder(""));
				
			final JPanel playerOne = new JPanel(new GridLayout(5, 3, 5, 5));
				playerOne.setBorder(BorderFactory.createTitledBorder(""));
				
			final JPanel playerTwo = new JPanel(new GridLayout(5, 3, 5, 5));
				playerTwo.setBorder(BorderFactory.createTitledBorder(""));
				
			final JLabel gameWinner = new JLabel();
				gameWinner.setFont(largeLabelFont);
				gameWinner.setForeground(Color.blue);
			
			/*
			 * player one top status panel stuff
			 */
			final JLabel playerOneName = new JLabel();	// to be set in listener
				playerOneName.setFont(labelFont);
				playerOneName.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerOneName.setForeground(Color.red);
				
				
			final JLabel playerOneHealth = new JLabel("Health:");
				playerOneHealth.setFont(smallLabelFont);
				playerOneHealth.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneHealth.setForeground(Color.red);
				
			final JLabel playerOneHealthRatio = new JLabel();	// status ratio label, to be set in listener.
				playerOneHealthRatio.setFont(verySmallLabelFont);
				playerOneHealthRatio.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneHealthRatio.setForeground(Color.red);
				
			final JProgressBar plOneHealthStatus = new JProgressBar(MIN_HEALTH, MAX_HEALTH);
					plOneHealthStatus.setOrientation(SwingConstants.HORIZONTAL);
					plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plOneHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					// following value will be reset by listener
					plOneHealthStatus.setValue(plOneHlth);
					
					
			final JLabel playerOneMana = new JLabel("Mana:");
				playerOneMana.setFont(smallLabelFont);
				playerOneMana.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneMana.setForeground(Color.red);
				
			final JLabel playerOneManaRatio = new JLabel();	// status ratio label, to be set in listener.
				playerOneManaRatio.setFont(verySmallLabelFont);
				playerOneManaRatio.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneManaRatio.setForeground(Color.red);
				
			final JProgressBar plOneManaStatus = new JProgressBar(MIN_MANA, MAX_MANA);
					plOneManaStatus.setOrientation(SwingConstants.HORIZONTAL);
					plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plOneManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					// following value will be reset by listener
					plOneManaStatus.setValue(plOneMana);
					
			final JLabel playerOneFood = new JLabel("Food:");
				playerOneFood.setFont(smallLabelFont);
				playerOneFood.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneFood.setForeground(Color.red);
				
			final JLabel playerOneFoodRatio = new JLabel();// status ratio label, to be set in listener.
				playerOneFoodRatio.setFont(verySmallLabelFont);
				playerOneFoodRatio.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneFoodRatio.setForeground(Color.red);
				
			final JProgressBar plOneFoodStatus = new JProgressBar(MIN_FOOD, MAX_FOOD);
					plOneFoodStatus.setOrientation(SwingConstants.HORIZONTAL);
					plOneFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plOneFoodStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					// following value will be reset by listener
					plOneFoodStatus.setValue(plOneFood);
				
				/*
				 * Add player one stuff to player one panel
				 */
				playerOne.add(playerOneName);
				for(int i = 1; i<3; i++){
					playerOne.add(new JPanel());
				}
				playerOne.add(playerOneHealth);
				playerOne.add(plOneHealthStatus);
				playerOne.add(playerOneHealthRatio);
				playerOne.add(playerOneMana);
				playerOne.add(plOneManaStatus);
				playerOne.add(playerOneManaRatio);
				playerOne.add(playerOneFood);
				playerOne.add(plOneFoodStatus);
				playerOne.add(playerOneFoodRatio);
			
			/*
			 * Player two top status panel stuff
			 */
			final JLabel playerTwoName = new JLabel();	// to be set in listener
				playerTwoName.setFont(labelFont);
				playerTwoName.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerTwoName.setForeground(Color.red);
				
			final JLabel playerTwoHealth = new JLabel("Health:");
				playerTwoHealth.setFont(smallLabelFont);
				playerTwoHealth.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoHealth.setForeground(Color.red);
				
			final JLabel playerTwoHealthRatio = new JLabel();	// status ratio label, to be set in listener.
				playerTwoHealthRatio.setFont(verySmallLabelFont);
				playerTwoHealthRatio.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoHealthRatio.setForeground(Color.red);
				
			final JProgressBar plTwoHealthStatus = new JProgressBar(MIN_HEALTH, MAX_HEALTH);
					plTwoHealthStatus.setOrientation(SwingConstants.HORIZONTAL);
					plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					// following value will be reset by listener
					plTwoHealthStatus.setValue(plTwoHlth);
					
			final JLabel playerTwoMana = new JLabel("Mana:");
				playerTwoMana.setFont(smallLabelFont);
				playerTwoMana.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoMana.setForeground(Color.red);
				
			final JLabel playerTwoManaRatio = new JLabel();	// status ratio label, to be set in listener.
				playerTwoManaRatio.setFont(verySmallLabelFont);
				playerTwoManaRatio.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoManaRatio.setForeground(Color.red);
				
			final JProgressBar plTwoManaStatus = new JProgressBar(MIN_MANA, MAX_MANA);
					plTwoManaStatus.setOrientation(SwingConstants.HORIZONTAL);
					plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plTwoManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					// following value will be reset by listener
					plTwoManaStatus.setValue(plTwoMana);
					
			final JLabel playerTwoFood = new JLabel("Food:");
				playerTwoFood.setFont(smallLabelFont);
				playerTwoFood.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoFood.setForeground(Color.red);
				
			final JLabel playerTwoFoodRatio = new JLabel();// status ratio label, to be set in listener.
				playerTwoFoodRatio.setFont(verySmallLabelFont);
				playerTwoFoodRatio.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoFoodRatio.setForeground(Color.red);
				
			final JProgressBar plTwoFoodStatus = new JProgressBar(MIN_FOOD, MAX_FOOD);
					plTwoFoodStatus.setOrientation(SwingConstants.HORIZONTAL);
					plTwoFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plTwoFoodStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					// following value will be reset by listener
					plTwoFoodStatus.setValue(plTwoFood);
				/*
				 * Add player two stuff to player two panel	
				 */
				playerTwo.add(playerTwoName);
				for(int i = 1; i<3; i++){
					playerTwo.add(new JPanel());
				}
				playerTwo.add(playerTwoHealth);
				playerTwo.add(plTwoHealthStatus);
				playerTwo.add(playerTwoHealthRatio);
				playerTwo.add(playerTwoMana);
				playerTwo.add(plTwoManaStatus);
				playerTwo.add(playerTwoManaRatio);
				playerTwo.add(playerTwoFood);
				playerTwo.add(plTwoFoodStatus);
				playerTwo.add(playerTwoFoodRatio);
				
				/*
				 * Add player panels to top panel
				 */
				topGamePanel.add(playerOne, BorderLayout.WEST);
				topGamePanel.add(playerTwo, BorderLayout.EAST);
			
			/*
			 * Middle panels
			 */
			final JPanel middleGamePanel = new JPanel(new BorderLayout(5, 5)); //main middle gameplay panel
			
			/*
			 * Active player title pane
			 */
			final JPanel middlePlayerSel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
				final JLabel activePlayer = new JLabel();
					activePlayer.setFont(labelFont);
					activePlayer.setAlignmentX(Component.RIGHT_ALIGNMENT);
					activePlayer.setForeground(Color.red);
					
				final JLabel yourMove = new JLabel(" , your move!");
					yourMove.setFont(labelFont);
					yourMove.setAlignmentX(Component.RIGHT_ALIGNMENT);
					yourMove.setForeground(Color.red);
					
					middlePlayerSel.add(activePlayer);
					middlePlayerSel.add(yourMove);
					
			final JPanel middleLeftPanel = new JPanel(new BorderLayout(5,5)); // attack button and dice animaiton pane
			
			final JPanel attackButtons = new JPanel(new GridLayout(3, 1, 2, 2));
				attackButtons.setBorder(BorderFactory.createTitledBorder(""));
			
			final JPanel middleRightPanel = new JPanel(new BorderLayout(5,5));	// game animation will play here
				middleRightPanel.setPreferredSize(new Dimension(100, 100));
			final Icon playerOneAttack = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/baseattackP1.gif"));
			final Icon playerTwoAttack = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/baseattackP2.gif"));
			final Icon playerRest = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"));
			
			final JLabel playerOneAttackLabel = new JLabel(playerOneAttack);
			final JLabel playerTwoAttackLabel = new JLabel(playerTwoAttack);	
			/*
			 * dice animation pane
			 */
			final JPanel diceAnimationPanel = new JPanel(new GridLayout(2, 2, 1, 1)); // add to middle left panel. panel to play dice animation
				diceAnimationPanel.setBorder(BorderFactory.createEtchedBorder());
				diceAnimationPanel.setPreferredSize(diceAnimationPanel.getPreferredSize());
				
				final JPanel die1 = new JPanel(new BorderLayout(1, 1));
					die1.setBorder(BorderFactory.createLoweredBevelBorder());
					die1.setPreferredSize(new Dimension(30,30));
					die1.add(new JLabel("temp"), BorderLayout.CENTER);
					
				final JPanel die2 = new JPanel(new BorderLayout(1, 1));
					die2.setBorder(BorderFactory.createLoweredBevelBorder());
					die2.setPreferredSize(new Dimension(30,30));
					die2.add(new JLabel("temp"), BorderLayout.CENTER);
					
				final JPanel die3 = new JPanel(new BorderLayout(1, 1));
					die3.setBorder(BorderFactory.createLoweredBevelBorder());
					die3.setPreferredSize(new Dimension(30,30));
					die3.add(new JLabel("temp"), BorderLayout.CENTER);
					
				final JPanel die4 = new JPanel(new BorderLayout(1, 1));
					die4.setBorder(BorderFactory.createLoweredBevelBorder());
					die4.setPreferredSize(new Dimension(30,30));
					die4.add(new JLabel("temp"), BorderLayout.CENTER);
			
					diceAnimationPanel.add(die1);
					diceAnimationPanel.add(die2);
					diceAnimationPanel.add(die3);
					diceAnimationPanel.add(die4);
			
			/*
			 * Player action buttons
			 */
			final JButton attackButton = new JButton("Attack!");
				attackButton.setFont(smallLabelFont);
				attackButton.setForeground(Color.red);
				attackButton.setAlignmentX(Component.LEFT_ALIGNMENT);
				attackButton.setToolTipText("Attack your opponent");
				
			final JButton healButton = new JButton("Heal");
				healButton.setFont(smallLabelFont);
				healButton.setForeground(Color.red);
				healButton.setAlignmentX(Component.LEFT_ALIGNMENT);
				healButton.setToolTipText("Eat food to heal your HP");
				
			final JButton spAttack = new JButton("Special");
				spAttack.setFont(smallLabelFont);
				spAttack.setForeground(Color.red);
				spAttack.setAlignmentX(Component.LEFT_ALIGNMENT);
				spAttack.setToolTipText("Use special attack powers");
			
				attackButtons.add(attackButton);
				attackButtons.add(healButton);
				attackButtons.add(spAttack);
				
				middleLeftPanel.add(attackButtons, BorderLayout.NORTH);
				middleLeftPanel.add(diceAnimationPanel, BorderLayout.CENTER);
				
				playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel); // goes center of middleGamePanel	
				middleGamePanel.add(middlePlayerSel, BorderLayout.NORTH);
				middleGamePanel.add(playerPane, BorderLayout.CENTER);
			
			/*
			 * Bottom panels
			 */
			final JPanel bottomGamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				bottomGamePanel.setBorder(BorderFactory.createTitledBorder(""));
				
			final JButton gameExit = new JButton("Exit");
			
			final JButton gameSave = new JButton("Save");
				bottomGamePanel.add(gameSave);
				bottomGamePanel.add(gameExit);

			
			/*
			 * Add components to game play panel
			 */
				
				gamePlayPanel.add(topGamePanel, BorderLayout.NORTH);
				gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
				gamePlayPanel.add(bottomGamePanel, BorderLayout.SOUTH);
/*
 * Action listeners
 */
	
			/*
			 * menu button listeners
			 */
			newGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					newGamePanel.removeAll();
					gameFrame.remove(modePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGameOptionsButtons, imagePanel);
					newGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			onePlayer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					JOptionPane.showMessageDialog(gameFrame,  "Fucntion not implemented.");
					return;
//					newGamePanel.removeAll();
//					gameFrame.remove(newGamePanel);
//					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newOnePlayerGameUser, imagePanel);
//					newGamePanel.add(splitPane);
//					gameFrame.setContentPane(newGamePanel);
//					gameFrame.pack();
//					splitPane.setDividerLocation(.30);
//					gameFrame.validate();
				}
			});
			
			twoPlayer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newTwoPlayerGameUser, imagePanel);
					newGamePanel.add(splitPane);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			newGameOptionsExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					modePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modePanelButtons, imagePanel);
					modePanel.add(splitPane);
					gameFrame.setContentPane(modePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});

			newOnePlayerUserStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					// TODO get user name and send to player select class
					
					if(newOnePlayerUserText.getText().isEmpty()){
						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name");
						return;
					}
					
					usernameOne = newOnePlayerUserText.getText();
					
					// TODO add call to check for existing user profile prior to launching player select window
					
//					if(game.findUser(usernameOne) > 0){
//						newGamePanel.removeAll();
//						gameFrame.remove(newGamePanel);
//						
//						foundPlayerOne.setText(usernameOne);
//						foundPlayerTwo.setText("");
//						usernameTwo = computer;
//						
//						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
//						newGamePanel.add(splitPane);
//						gameFrame.setContentPane(newGamePanel);
//						gameFrame.pack();
//						splitPane.setDividerLocation(.30);
//						gameFrame.validate();
//					}else{
					usernameTwo = computer;
					whichPlayer.setPlayerNames(usernameOne, usernameTwo);
					
					/*
					 * Launch player select frame in AWT event dispatch thread
					 */
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								whichPlayer.showPlayerSelect();
							}
						});
//					}
				}
			});
			
			/*
			 * If user name already exists and the choice is made to use it
			 */
			useName.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					whichPlayer.setPlayerNames(usernameOne, usernameTwo);
					/*
					 * Launch player select frame in AWT event dispatch thread
					 */
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								whichPlayer.showPlayerSelect();
							}
						});
				}
			});
			
			/*
			 * If user name already exists and the choice is made to return to previous menu
			 */
			cancelName.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(!newOnePlayerUserText.getText().isEmpty()){
						usernameOne = "";
						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newOnePlayerGameUser, imagePanel);
						newOnePlayerUserText.setText("");
					}
					
					if(!userName1Text.getText().isEmpty() || !userName2Text.getText().isEmpty()){
						usernameOne = usernameTwo = "";
						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newTwoPlayerGameUser, imagePanel);
						userName1Text.setText("");
						userName2Text.setText("");
					}
					
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					newGamePanel.add(splitPane);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			newOnePlayerUserExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(!newOnePlayerUserText.getText().isEmpty()){
						newOnePlayerUserText.setText("");
					}
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGameOptionsButtons, imagePanel);
					newGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			/*
			 * Show game play panel when player select panel closes
			 */
			whichPlayer.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent we){
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					
//					playerOneName.setText(usernameOne);
//					playerTwoName.setText(usernameTwo);
					
					// display the choose player dice roll winner user name
					//System.out.println(player);
					if(player > 0){
						activePlayer.setText(usernameOne);
						game = new Game(usernameOne, usernameTwo);	// pass user names to game
					}else{
						String tmp = usernameOne;	// Swap user names so the winner of the roll will now be "user name 
						usernameOne = usernameTwo;
						usernameTwo = tmp;
						activePlayer.setText(usernameOne);
						game = new Game(usernameOne, usernameTwo);	// pass user names to game
					}
						//added this to try and cope with user order problem
						//swaps player identity
						playerOneName.setText(usernameOne);
						playerTwoName.setText(usernameTwo);
					
						playerOneHealthRatio.setText(game.getPlayerOneStatus().getHitPts() + "/" + Status.getMaxHP());
						playerOneManaRatio.setText(game.getPlayerOneStatus().getMana() + "/" + Status.getMaxMana());
						playerOneFoodRatio.setText(game.getPlayerOneStatus().getFoodCount() + "/" + Status.getMaxFood());
						
						playerTwoHealthRatio.setText(game.getPlayerTwoStatus().getHitPts() + "/" + Status.getMaxHP());
						playerTwoManaRatio.setText(game.getPlayerTwoStatus().getMana() + "/" + Status.getMaxMana());
						playerTwoFoodRatio.setText(game.getPlayerTwoStatus().getFoodCount() + "/" + Status.getMaxFood());
						
						gameFrame.setContentPane(gamePlayPanel);
						gameFrame.pack();
						playerPane.setDividerLocation(.20);
						gameFrame.validate();
				}
			});
			
			/*
			 * Show player select before game play panel
			 */
			newTwoPlayerUserStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					if(userName1Text.getText().isEmpty()&&userName2Text.getText().isEmpty()||userName1Text.getText().isEmpty()||userName2Text.getText().isEmpty()){
						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name for both players");
						return;
					}
					
					usernameOne = userName1Text.getText();
					usernameTwo = userName2Text.getText();
					
					// TODO add call to check for existing user profiles
					
//					if(game.findUser(usernameOne) > 0 && game.findUser(usernameTwo) > 0){
//						newGamePanel.removeAll();
//						gameFrame.remove(newGamePanel);
//						foundPlayerOne.setText(usernameOne);
//						foundPlayerTwo.setText(usernameTwo);
//						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
//						newGamePanel.add(splitPane);
//						gameFrame.setContentPane(newGamePanel);
//						gameFrame.pack();
//						splitPane.setDividerLocation(.30);
//						gameFrame.validate();
//					}else if(game.findUser(usernameOne) > 0 && game.findUser(usernameTwo) == 0){
//						foundPlayerOne.setText(usernameOne);
//						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
//						newGamePanel.add(splitPane);
//						gameFrame.setContentPane(newGamePanel);
//						gameFrame.pack();
//						splitPane.setDividerLocation(.30);
//						gameFrame.validate();
//					}else if(game.findUser(usernameOne) == 0 && game.findUser(usernameTwo) > 0){
//						foundPlayerTwo.setText(usernameTwo);
//						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
//						newGamePanel.add(splitPane);
//						gameFrame.setContentPane(newGamePanel);
//						gameFrame.pack();
//						splitPane.setDividerLocation(.30);
//						gameFrame.validate();
//					}else{
						whichPlayer.setPlayerNames(usernameOne, usernameTwo);
						/*
						 * Launch player select frame in AWT event dispatch thread
						 */
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								whichPlayer.showPlayerSelect();
							}
						});	
//					}
				}
			});
			
			newTwoPlayerUserExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(!userName1Text.getText().isEmpty()||!userName2Text.getText().isEmpty()){
						userName1Text.setText("");
						userName2Text.setText("");
					}
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGameOptionsButtons, imagePanel);
					newGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
//			loadGameButton.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent ae){
//					loadGamePanel.removeAll();
//					gameFrame.remove(modePanel);
//					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, loadGameOptionsPanel, imagePanel);
//					loadGamePanel.add(splitPane, BorderLayout.CENTER);
//					gameFrame.setContentPane(loadGamePanel);
//					gameFrame.pack();
//					splitPane.setDividerLocation(.30);
//					gameFrame.validate();
//				}
//			});
//			
//			loadUserNameFind.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent ae){
//					// TODO add find player game stuff
//					if(loadUserNameText.getText().isEmpty()){
//						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name");
//						return;
//					}
//					//JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
//				}
//			});
//			
//			loadUserExit.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent ae){
//					if(!loadUserNameText.getText().isEmpty()){
//						loadUserNameText.setText("");
//					}
//					modePanel.removeAll();
//					gameFrame.remove(loadGamePanel);
//					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modePanelButtons, imagePanel);
//					modePanel.add(splitPane, BorderLayout.CENTER);
//					gameFrame.setContentPane(modePanel);
//					gameFrame.pack();
//					splitPane.setDividerLocation(.30);
//					gameFrame.validate();
//				}
//			});
			
			playerStatusButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					statsPanel.removeAll();
					gameFrame.remove(modePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statPanelOptionsButtons, imagePanel);
					statsPanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(statsPanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			statFind.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add get user stats stuff
					if(singleUserStatText.getText().isEmpty()){
						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name");
					}
					JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			singleStatExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(!singleUserStatText.getText().isEmpty()){
						singleUserStatText.setText("");
					}
					statsPanel.removeAll();
					gameFrame.remove(statsPanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statPanelOptionsButtons, imagePanel);
					statsPanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(statsPanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			allUserStats.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add get user stats stuff
					JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			statsExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					modePanel.removeAll();
					gameFrame.remove(statsPanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modePanelButtons, imagePanel);
					modePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(modePanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			abtGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add game rules stuff
					JOptionPane.showMessageDialog(gameFrame, "This is the button that will show the game details");
				}
			});
			
			singleUserStats.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					statsPanel.removeAll();
					gameFrame.remove(statsPanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statPanelSingleUserButtons, imagePanel);
					statsPanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(statsPanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			/*
			 * game play button listeners
			 */
			attackButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){

					try{
						winner = game.PlayNextTurn(Move.ATTACK);
					}catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(gameFrame, e.getMessage());
						
						// either exit game or return to start page;  This is a fatal error!!
						
					}

										
					//add test for illegal move here, notify the user and return
					
					
//					if(winner.equals(Winner.NONE)){
//						//middleGamePanel.removeAll();
//						playerPane.removeAll();
//						if(!game.getFirst()){
//							middleRightPanel.add(playerOneAttackLabel, BorderLayout.CENTER);
//						}else{
//							middleRightPanel.add(playerTwoAttackLabel, BorderLayout.CENTER);
//						}
//						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
//						//gamePlayPanel.add(playerPane);
//						middleGamePanel.add(playerPane, BorderLayout.CENTER);
//						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
//						gameFrame.setContentPane(gamePlayPanel);
//						gameFrame.pack();
//						playerPane.setDividerLocation(.20);
//						gameFrame.validate();
//					}
					
					if(winner.equals(Winner.NONE)){
						if(!game.isPlayerOneTurn()){
							
							playerTwoHealthRatio.setText(game.getPlayerTwoStatus().getHitPts() + "/" + Status.getMaxHP());
							playerTwoManaRatio.setText(game.getPlayerTwoStatus().getMana() + "/" + Status.getMaxMana());
							playerTwoFoodRatio.setText(game.getPlayerTwoStatus().getFoodCount() + "/" + Status.getMaxFood());
							plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
							if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 2) && plTwoHealthStatus.getValue() > (MAX_HEALTH / 4)){
								UIDefaults onedefaults = new UIDefaults();
								onedefaults.put("ProgressBar[Enabled].foregroundPainter", new FillPainter(new Color(240, 174, 41)));
								onedefaults.put("ProgressBar[Enabled+Finished].foregroundPainter", new FillPainter(new Color(240, 174, 41)));
								plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
								plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  onedefaults);
								//plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
							}else if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 4)){
								UIDefaults twodefaults = new UIDefaults();
								twodefaults.put("ProgressBar[Enabled].foregroundPainter", new FillPainter(new Color(255, 0, 0)));
								twodefaults.put("ProgressBar[Enabled+Finished].foregroundPainter", new FillPainter(new Color(255, 0, 0)));
								plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
								plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  twodefaults);
								//plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
							}else{
								UIDefaults threedefaults = new UIDefaults();
								threedefaults.put("ProgressBar[Enabled].foregroundPainter", new FillPainter(new Color(0, 204, 0)));
								threedefaults.put("ProgressBar[Enabled+Finished].foregroundPainter", new FillPainter(new Color(255, 0, 0)));
								plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
								plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  threedefaults);
								//plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
							}
							
							/*
							 * move these to their respective listeners
							 */
							plTwoManaStatus.setValue(game.getPlayerTwoStatus().getMana());
							plTwoFoodStatus.setValue(game.getPlayerTwoStatus().getFoodCount());
							
							activePlayer.setText(usernameTwo);
							
							gameFrame.setContentPane(gamePlayPanel);
							gameFrame.pack();
							playerPane.setDividerLocation(.20);
							gameFrame.validate();
						}else if(game.isPlayerOneTurn()){
							
							playerOneHealthRatio.setText(game.getPlayerOneStatus().getHitPts() + "/" + Status.getMaxHP());
							playerOneManaRatio.setText(game.getPlayerOneStatus().getMana() + "/" + Status.getMaxMana());
							playerOneFoodRatio.setText(game.getPlayerOneStatus().getFoodCount() + "/" + Status.getMaxFood());
							plOneHealthStatus.setValue(game.getPlayerOneStatus().getHitPts());
							if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 2) && plOneHealthStatus.getValue() > (MAX_HEALTH / 4)){
								plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
								plOneHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
							}else if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 4)){
								plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
								plOneHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
							}else{
								plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
								plOneHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
							}
							
							plOneManaStatus.setValue(game.getPlayerOneStatus().getMana());
							plOneFoodStatus.setValue(game.getPlayerOneStatus().getFoodCount());
							
							activePlayer.setText(usernameOne);
							
							gameFrame.setContentPane(gamePlayPanel);
							gameFrame.pack();
							playerPane.setDividerLocation(.20);
							gameFrame.validate();
						}else{
							JOptionPane.showMessageDialog(gameFrame, "Something has gone horribly wrong in Winner PlayNextTurn() method");
						}
					}else if(winner.equals(Winner.PLAYER_ONE)){
						// TODO add player one winner stuff
						middleRightPanel.removeAll();
						gameWinner.setText(game.playerOne.getUsername() + " has won!");
						middleRightPanel.add(gameWinner, BorderLayout.CENTER);
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
						gameFrame.pack();
						playerPane.setDividerLocation(.20);
						gameFrame.validate();
					}else if(winner.equals(Winner.PLAYER_TWO)){
						// TODO add player two winner stuff
						middleRightPanel.removeAll();
						gameWinner.setText(game.playerTwo.getUsername() + " has won!");
						middleRightPanel.add(gameWinner, BorderLayout.CENTER);
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
						gameFrame.pack();
						playerPane.setDividerLocation(.20);
						gameFrame.validate();
					}
//					delayTime();
//					middleRightPanel.removeAll();
//					middleRightPanel.add(new JLabel(playerRest), BorderLayout.CENTER);
//					gameFrame.validate();
				}
			});
			
//			middleRightPanel.addMouseMotionListener(new MouseMotionAdapter(){
//				public void mouseMoved(MouseEvent me){
//					if(playerOneAttackLabel.isVisible() || playerTwoAttackLabel.isVisible()){
//						middleRightPanel.removeAll();
//						middleRightPanel.add(new JLabel(playerRest), BorderLayout.CENTER);
//						gameFrame.validate();
//					}
//				}
//			});
			
			healButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					// TODO add heal stuff
				}
			});
			
			spAttack.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					// TODO add special power attack stuff
				}
			});
			
			/*
			 * game play exit/save button listeners
			 */
			gameSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					 // TODO game save tasks here
					
					JOptionPane.showMessageDialog(gameFrame, "This button will save game status");
					
				}
			});
			
			gameExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					// TODO need to add game exit tasks here
					
					gameFrame.dispose();
					System.exit(0);
				}
			});
			
				/*
				 * Initial frame view with mode select panel
				 */
				splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modePanelButtons, imagePanel);
				modePanel.add(splitPane, BorderLayout.CENTER);
				gameFrame.setContentPane(modePanel);
				gameFrame.pack();
				try{
					gameFrame.setLocationByPlatform(true);
				}catch(Throwable ignoreAndContinue){}
				
				/*
				 * Display frame
				 */
				gameFrame.setVisible(true);
				splitPane.setDividerLocation(.30);
				
		}
		
		/**
		 * Entry main.  Deploys UI in AWT event-dispatching thread with selected LAF
		 * @param args
		 */
		public static void main(String[] args){
			final String lafClassName = getLookAndFeelClassName("Nimbus");
			
			SwingUtilities.invokeLater(new Runnable(){	// launch UI in AWT event-dispatching thread
				public void run(){
					try{
						UIManager.setLookAndFeel(lafClassName);
					}catch(ClassNotFoundException ex){
						Logger.getLogger(SliceAndDiceUI.class.getName()).log(Level.SEVERE, null, ex);
					}catch(InstantiationException ex){
						Logger.getLogger(SliceAndDiceUI.class.getName()).log(Level.SEVERE, null, ex);
					}catch(IllegalAccessException ex){
						Logger.getLogger(SliceAndDiceUI.class.getName()).log(Level.SEVERE, null, ex);
					}catch(UnsupportedLookAndFeelException ex){
						Logger.getLogger(SliceAndDiceUI.class.getName()).log(Level.SEVERE, null, ex);
					}
					new SliceAndDiceUI().showUI();
				}
			});
		}
}

/**
 * Custom painter class to fill progress bars with custom color
 * @author Jacob
 *
 */
class FillPainter implements Painter<JComponent>{
	private final Color color;
	
	public FillPainter(Color c){
		color = c;
	}

	@Override
	public void paint(Graphics2D g, JComponent object, int width, int height) {
		g.setColor(color);
		g.fillRect(0, 0, width - 1, height - 1);
	}
}

/**
 * Player select class.  Determines who goes first when new game is started
 * @author Jacob
 *
 */
@SuppressWarnings("serial")
class ChooseFirst extends JFrame{
	
	private JLabel playerOne;
	private JLabel playerTwo;
	Boolean winner = false;
	
	Font labelFont = new Font("Trebuchet MS", Font.BOLD, 16);
	Font smallLabelFont = new Font("Trebuchet MS", Font.BOLD, 12);
	Font verySmallLabelFont = new Font("Trebuchet MS", Font.BOLD, 10);
	Font smallButtonFont = new Font("Trebuchet MS", Font.BOLD, 16);
	Font largeLabelFont = new Font("Trubuchet MS", Font.BOLD, 24);
	
	JPanel choosePlayerPane;
	
	/*
	 * ChooseFirst constructor
	 * 
	 */
	public ChooseFirst(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Slice And Dice");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png")));
		this.setPreferredSize(new Dimension(600, 450));
	}
	
	/**
	 * Set user name labels to be displayed in player select pane
	 * @param plOne
	 * @param plTwo
	 */
	public void setPlayerNames(String plOne, String plTwo){
		playerOne = new JLabel(plOne);
		playerOne.setFont(largeLabelFont);
		playerOne.setAlignmentX(Component.LEFT_ALIGNMENT);
		playerOne.setForeground(Color.red);
		
		playerTwo = new JLabel(plTwo);
		playerTwo.setFont(largeLabelFont);
		playerTwo.setAlignmentX(Component.LEFT_ALIGNMENT);
		playerTwo.setForeground(Color.red);
	}
	
	/**
	 * Generates random number between 1 and 6 inclusive
	 * 
	 * @return randNum
	 */
	private int getRand(){
		Random rand = new Random();
		
		int randNum = rand.nextInt((6 - 1) + 1) + 1;
		
		return randNum;
	}
	
	/*
	 * Pack and validate the frame
	 */
	private void packAndValidate(){
		this.setContentPane(choosePlayerPane);
		this.pack();
		this.validate();
	}
	
	/*
	 * Pack and show the frame
	 */
	private void packAndShow(){
		this.setContentPane(choosePlayerPane);
		this.pack();
		this.setVisible(true);
	}
	
	/*
	 * Dispose frame
	 */
	private void disposeFrame(){
		this.dispose();
	}

	public void showPlayerSelect(){
			
			Icon diceRoll1Icon = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/dice_roll_1.gif"));
			Icon diceRoll2Icon = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/dice_roll_1.gif"));
			
			final JLabel rollOne = new JLabel(diceRoll1Icon);
			final JLabel rollTwo = new JLabel(diceRoll2Icon);
			
			choosePlayerPane = new JPanel(new BorderLayout(5,5));
			choosePlayerPane.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel choosePlayerTop = new JPanel(new BorderLayout(5,5));
			choosePlayerTop.setBorder(BorderFactory.createTitledBorder(""));
			
			final JPanel topRight = new JPanel(new GridLayout(3, 1, 5, 5));
				
				for(int i = 0; i < 3; i++){
					if(i == 0){
						topRight.add(playerTwo);
					}else{
						topRight.add(new JPanel());
					}
				}
				
			final JPanel topLeft = new JPanel(new GridLayout(3, 1, 5, 5));
				
				topLeft.add(playerOne);
				for(int i = 1; i < 3; i++){
					topLeft.add(new JPanel());
				}
				
				choosePlayerTop.add(topRight, BorderLayout.EAST);
				choosePlayerTop.add(topLeft, BorderLayout.WEST);
			
		final JPanel choosePlayerCenter = new JPanel(new BorderLayout(5,5));
			choosePlayerCenter.setBorder(BorderFactory.createTitledBorder(""));
			choosePlayerCenter.setBackground(Color.red);
		final JButton stopRoll = new JButton("Stop Roll");
		final JPanel stopPanel = new JPanel(new GridLayout(3,1,2,2));
		JPanel fillPanel1 = new JPanel();
			fillPanel1.setBackground(Color.red);
		JPanel fillPanel2 = new JPanel();
			fillPanel2.setBackground(Color.red);
			stopPanel.setBackground(Color.red);
			stopPanel.add(fillPanel1);
			stopPanel.add(stopRoll);
			stopPanel.add(fillPanel2);
			
		JPanel choosePlayerBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			
			JButton cont = new JButton("Start Game");
			
			choosePlayerBottom.add(cont);
			
			choosePlayerPane.add(choosePlayerTop, BorderLayout.NORTH);
			choosePlayerPane.add(choosePlayerCenter, BorderLayout.CENTER);
			choosePlayerPane.add(choosePlayerBottom, BorderLayout.SOUTH);
			
			/*
			 * Play dice roll animation when frame opens and determine which player will
			 * go first.
			 */
			this.addWindowListener(new WindowAdapter(){
				public void windowOpened(WindowEvent we){	
					
					// play rolling dice gif when window opens
					choosePlayerCenter.add(rollOne, BorderLayout.WEST);
					choosePlayerCenter.add(stopPanel, BorderLayout.CENTER);
					choosePlayerCenter.add(rollTwo, BorderLayout.EAST);
					
					packAndValidate();
				}
			});
					
			stopRoll.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					if(!winner){	// determine if stop button has been pushed already
						winner = true;
						BufferedImage roll1Icon = null;
						BufferedImage roll2Icon = null;
						int plyr1Roll = getRand();
						int plyr2Roll = getRand();
						
						// eliminate duplicate rolls
						if(plyr1Roll == plyr2Roll){
							if(plyr1Roll == 6){
								plyr1Roll--;
							}else{
								plyr1Roll++;
							}
						}
						
						// get static die pic based on the outcome of getRand() calls
						try{
							roll1Icon = ImageIO.read(getClass().getResourceAsStream("/sliceAndDice/game_resources/die_land_" + plyr1Roll + ".jpg"));
							roll2Icon = ImageIO.read(getClass().getResourceAsStream("/sliceAndDice/game_resources/die_land_" + plyr2Roll + ".jpg"));
						}catch(IOException e){
							JOptionPane.showMessageDialog(null, "IO Error: " + e.getMessage());
							winner = false;
							return;
						}
						
						ImageIcon playerOneRoll = new ImageIcon(roll1Icon);
						ImageIcon playerTwoRoll = new ImageIcon(roll2Icon);
						
						JLabel player1ImageLabel = new JLabel(playerOneRoll);
						JLabel player2ImageLabel = new JLabel(playerTwoRoll);
						
						choosePlayerCenter.removeAll();
						choosePlayerCenter.add(player1ImageLabel, BorderLayout.WEST);
						choosePlayerCenter.add(stopPanel, BorderLayout.CENTER);
						choosePlayerCenter.add(player2ImageLabel, BorderLayout.EAST);
						
						// determine winner of roll
						if(plyr1Roll > plyr2Roll){
							
							// add "You Win" below the winning player name
							SliceAndDiceUI.setPlayer(1);
							topLeft.removeAll();
							topLeft.add(playerOne);
							
							for(int i = 1; i < 3; i++){
								if(i == 2){
									JLabel win = new JLabel("You Win!");
									win.setFont(largeLabelFont);
									win.setForeground(Color.red);
									win.setAlignmentX(Component.LEFT_ALIGNMENT);
									topLeft.add(win);
								}else{
									topLeft.add(new JPanel());
								}
							}
							packAndValidate();
						}
						if(plyr2Roll > plyr1Roll){
							SliceAndDiceUI.setPlayer(0);
							topRight.removeAll();
							
							for(int i = 0; i < 3; i++){
								if(i == 0){
									topRight.add(playerTwo);
								}else if(i == 2){
									JLabel win = new JLabel("You Win!");
									win.setFont(largeLabelFont);
									win.setForeground(Color.red);
									win.setAlignmentX(Component.RIGHT_ALIGNMENT);
									topRight.add(win);
								}else{
									topRight.add(new JPanel());
								}					
							}
							// pack and validate the frame
							packAndValidate();
						}
					}
				}
			});	
			
			cont.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					disposeFrame();
				}
			});
			
			/*
			 * show frame
			 */
			packAndShow();
			
	}
}
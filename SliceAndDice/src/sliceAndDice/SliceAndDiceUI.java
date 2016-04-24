package sliceAndDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 * Game GUI class
 * @author Jacob
 *
 */
public class SliceAndDiceUI {
	private final Dimension GAME_SIZE = new Dimension(1000, 650);
	private final Dimension MENU_SIZE = new Dimension(1000, 450);
	private final int MAX_HEALTH = 100;
	private final int MIN_HEALTH = 0;
	private final int MAX_MANA = 30;
	private final int MIN_MANA = 0;
	private final int MAX_FOOD = 5;
	private final int MIN_FOOD = 0;
	private final String COND_FROZEN = "FRZN";
	private final String COND_POISON = "PZN";
	private final String COND_AURA = "AUR";
	private final String COND_NONE = "NONE";
	private static int player;
	private String usernameOne;
	private String usernameTwo;
	private String computer = "Evil AI Bot";
	private JSplitPane splitPane;
	private JSplitPane playerPane;
	private JScrollPane scrollStats;
	public String tempUser = "";
	Boolean singlePlayer = true;
	int debug = 0;
	Game game;
	GetStats stats = new GetStats();
	Winner winner = Winner.NONE;
	Move move;
	ChooseAttack chooseAtk = new ChooseAttack();
	DoubleAtk dblAtk = new DoubleAtk();
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
		
		/**
		 * Get player variable
		 * @return player
		 */
		public static int getPlayer(){
			return player;
		}
		
		/**
		 * Time delay method
		 */
		public void delayTime(){
			try{
				Thread.sleep(3000);
			}catch(Exception e){}
		}
		
		/**
		 * Show GUI method
		 */
		public void showUI(){
			
			final ChooseFirst whichPlayer = new ChooseFirst();
			final StopRoll stop = new StopRoll();

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
				gameFrame.setLocationRelativeTo(null);
				gameFrame.setPreferredSize(MENU_SIZE);
				gameFrame.setResizable(false);
				
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
			
			final JPanel modePanelButtons = new JPanel(new GridLayout(3, 1, 2, 5));
			
				/*
				 * Mode select buttons
				 */
				final JButton newGameButton = new JButton("New Game");
					newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					newGameButton.setFont(bigButtonFont);
					newGameButton.setForeground(Color.red);
					newGameButton.setToolTipText("Start new game");
					
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
				modePanelButtons.add(playerStatusButton);
				modePanelButtons.add(abtGameButton);

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
				
			final JButton twoPlayer = new JButton("Duel");
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
 * Gameplay panel
 */
			/*
			 * Main gameplay panel
			 */
			final JPanel gamePlayPanel = new JPanel(new BorderLayout(5, 5));
			
			/*
			 * Top panels
			 */
			JPanel topGamePanel = new JPanel(new BorderLayout(5, 5));
				//topGamePanel.setBorder(BorderFactory.createTitledBorder(""));
				
			JPanel topRightPanel = new JPanel(new BorderLayout(5, 5));
				topRightPanel.setBorder(BorderFactory.createTitledBorder(""));
			JPanel topLeftPanel = new JPanel(new BorderLayout(5, 5));
				topLeftPanel.setBorder(BorderFactory.createTitledBorder(""));

				
			JPanel playerOneNameAndStatus = new JPanel(new BorderLayout(5, 5));
			
			final JPanel playerOneCondition = new JPanel(new BorderLayout(5, 5));	//holds condition icon
				playerOneCondition.setBorder(BorderFactory.createTitledBorder(""));
				playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))), BorderLayout.CENTER);
			
			JPanel playerTwoNameAndStatus = new JPanel(new BorderLayout(5, 5));
				
			final JPanel playerTwoCondition = new JPanel(new BorderLayout(5, 5));	//holds condition icon
				playerTwoCondition.setBorder(BorderFactory.createTitledBorder(""));
				playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))), BorderLayout.CENTER);
				
			final JLabel gameWinner = new JLabel();
				gameWinner.setFont(largeLabelFont);
				gameWinner.setForeground(Color.blue);
				gameWinner.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			/*
			 * player one top status panel stuff
			 */
			final JLabel playerOneName = new JLabel();	// to be set in listener
				playerOneName.setFont(largeLabelFont);
				playerOneName.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerOneName.setForeground(Color.red);
				
				
			JLabel playerOneHealth = new JLabel("Health:");
				playerOneHealth.setFont(smallLabelFont);
				playerOneHealth.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneHealth.setForeground(Color.red);
				
			final JLabel playerOneHealthRatio = new JLabel();	// status ratio label, to be set in listener.
				playerOneHealthRatio.setFont(verySmallLabelFont);
				playerOneHealthRatio.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerOneHealthRatio.setForeground(Color.red);
				
			final JProgressBar plOneHealthStatus = new JProgressBar(MIN_HEALTH, MAX_HEALTH);
					plOneHealthStatus.setOrientation(SwingConstants.HORIZONTAL);
					plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plOneHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					
					
			JLabel playerOneMana = new JLabel("Mana:");
				playerOneMana.setFont(smallLabelFont);
				playerOneMana.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneMana.setForeground(Color.red);
				
			final JLabel playerOneManaRatio = new JLabel();	// status ratio label, to be set in listener.
				playerOneManaRatio.setFont(verySmallLabelFont);
				playerOneManaRatio.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerOneManaRatio.setForeground(Color.red);
				
			final JProgressBar plOneManaStatus = new JProgressBar(MIN_MANA, MAX_MANA);
					plOneManaStatus.setOrientation(SwingConstants.HORIZONTAL);
					plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plOneManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					
			JLabel playerOneFood = new JLabel("Food:");
				playerOneFood.setFont(smallLabelFont);
				playerOneFood.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerOneFood.setForeground(Color.red);
				
			final JLabel playerOneFoodRatio = new JLabel();// status ratio label, to be set in listener.
				playerOneFoodRatio.setFont(verySmallLabelFont);
				playerOneFoodRatio.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerOneFoodRatio.setForeground(Color.red);
				
			final JProgressBar plOneFoodStatus = new JProgressBar(MIN_FOOD, MAX_FOOD);
					plOneFoodStatus.setOrientation(SwingConstants.HORIZONTAL);
					plOneFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plOneFoodStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					
			JPanel plOneStats = new JPanel(new FlowLayout(FlowLayout.LEFT));
				plOneStats.setPreferredSize(new Dimension(460, 70));
			JPanel plOneStatusIndicators = new JPanel(new GridLayout(3,1,5,7));
				plOneStatusIndicators.add(playerOneHealthRatio);
				plOneStatusIndicators.add(playerOneManaRatio);
				plOneStatusIndicators.add(playerOneFoodRatio);
				plOneStatusIndicators.setPreferredSize(new Dimension(50,50));
			JPanel plOneStatusBars = new JPanel(new GridLayout(3,1,2,2));
				plOneStatusBars.add(plOneHealthStatus);
				plOneStatusBars.add(plOneManaStatus);
				plOneStatusBars.add(plOneFoodStatus);
			JPanel plOneIndicators = new JPanel(new GridLayout(3,3,5,5));
				plOneIndicators.add(new JPanel());
				plOneIndicators.add(new JPanel());
				plOneIndicators.add(playerOneHealth);
				plOneIndicators.add(new JPanel());
				plOneIndicators.add(new JPanel());
				plOneIndicators.add(playerOneMana);
				plOneIndicators.add(new JPanel());
				plOneIndicators.add(new JPanel());
				plOneIndicators.add(playerOneFood);
					
					/*
					 * player one additions
					 */
				JPanel plOneSpclPanel = new JPanel(new GridLayout(3,2,2,2));
					JLabel playerOneATK = new JLabel("ATK:");
						playerOneATK.setFont(smallLabelFont);
						playerOneATK.setForeground(Color.blue);
						playerOneATK.setAlignmentX(Component.LEFT_ALIGNMENT);
					JLabel playerOneDEF = new JLabel("DEF:");
						playerOneDEF.setFont(smallLabelFont);
						playerOneDEF.setForeground(Color.blue);
						playerOneDEF.setAlignmentX(Component.LEFT_ALIGNMENT);
					JLabel playerOneCOND = new JLabel("COND:");
						playerOneCOND.setFont(smallLabelFont);
						playerOneCOND.setForeground(Color.blue);
						playerOneCOND.setAlignmentX(Component.LEFT_ALIGNMENT);
					final JLabel playerOneATKValue = new JLabel("0");
						playerOneATKValue.setFont(smallLabelFont);
						playerOneATKValue.setForeground(Color.blue);
						playerOneATKValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
					final JLabel playerOneDEFValue = new JLabel("0");
						playerOneDEFValue.setFont(smallLabelFont);
						playerOneDEFValue.setForeground(Color.blue);
						playerOneDEFValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
					final JLabel playerOneCONDValue = new JLabel(COND_NONE);
						playerOneCONDValue.setFont(smallLabelFont);
						playerOneCONDValue.setForeground(Color.blue);
						playerOneCONDValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
					
						plOneSpclPanel.add(playerOneATK);
						plOneSpclPanel.add(playerOneATKValue);
						plOneSpclPanel.add(playerOneDEF);
						plOneSpclPanel.add(playerOneDEFValue);
						plOneSpclPanel.add(playerOneCOND);
						plOneSpclPanel.add(playerOneCONDValue);
					
				
				/*
				 * Add player one stuff to player one panel
				 */
				playerOneNameAndStatus.add(playerOneName, BorderLayout.WEST);
				playerOneNameAndStatus.add(playerOneCondition, BorderLayout.EAST);
				
				plOneStats.add(plOneSpclPanel);
				plOneStats.add(plOneIndicators);
				plOneStats.add(plOneStatusBars);
				plOneStats.add(plOneStatusIndicators);
			
			/*
			 * Player two top status panel stuff
			 */
			final JLabel playerTwoName = new JLabel();	// to be set in listener
				playerTwoName.setFont(largeLabelFont);
				playerTwoName.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerTwoName.setForeground(Color.red);
				
			JLabel playerTwoHealth = new JLabel("Health:");
				playerTwoHealth.setFont(smallLabelFont);
				playerTwoHealth.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoHealth.setForeground(Color.red);
				
			final JLabel playerTwoHealthRatio = new JLabel();	// status ratio label, to be set in listener.
				playerTwoHealthRatio.setFont(verySmallLabelFont);
				playerTwoHealthRatio.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerTwoHealthRatio.setForeground(Color.red);
				
			final JProgressBar plTwoHealthStatus = new JProgressBar(MIN_HEALTH, MAX_HEALTH);
					plTwoHealthStatus.setOrientation(SwingConstants.HORIZONTAL);
					plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					
			JLabel playerTwoMana = new JLabel("Mana:");
				playerTwoMana.setFont(smallLabelFont);
				playerTwoMana.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoMana.setForeground(Color.red);
				
			final JLabel playerTwoManaRatio = new JLabel();	// status ratio label, to be set in listener.
				playerTwoManaRatio.setFont(verySmallLabelFont);
				playerTwoManaRatio.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerTwoManaRatio.setForeground(Color.red);
				
			final JProgressBar plTwoManaStatus = new JProgressBar(MIN_MANA, MAX_MANA);
					plTwoManaStatus.setOrientation(SwingConstants.HORIZONTAL);
					plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plTwoManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					
			JLabel playerTwoFood = new JLabel("Food:");
				playerTwoFood.setFont(smallLabelFont);
				playerTwoFood.setAlignmentX(Component.RIGHT_ALIGNMENT);
				playerTwoFood.setForeground(Color.red);
				
			final JLabel playerTwoFoodRatio = new JLabel();// status ratio label, to be set in listener.
				playerTwoFoodRatio.setFont(verySmallLabelFont);
				playerTwoFoodRatio.setAlignmentX(Component.LEFT_ALIGNMENT);
				playerTwoFoodRatio.setForeground(Color.red);
				
			final JProgressBar plTwoFoodStatus = new JProgressBar(MIN_FOOD, MAX_FOOD);
					plTwoFoodStatus.setOrientation(SwingConstants.HORIZONTAL);
					plTwoFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
					plTwoFoodStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					
			JPanel plTwoStats = new JPanel(new FlowLayout(FlowLayout.LEFT));
				plTwoStats.setPreferredSize(new Dimension(460, 70));
			JPanel plTwoStatusIndicators = new JPanel(new GridLayout(3,1,5,7));
				plTwoStatusIndicators.add(playerTwoHealthRatio);
				plTwoStatusIndicators.add(playerTwoManaRatio);
				plTwoStatusIndicators.add(playerTwoFoodRatio);
				plTwoStatusIndicators.setPreferredSize(new Dimension(50,50));
			JPanel plTwoStatusBars = new JPanel(new GridLayout(3,1,2,2));
				plTwoStatusBars.add(plTwoHealthStatus);
				plTwoStatusBars.add(plTwoManaStatus);
				plTwoStatusBars.add(plTwoFoodStatus);
			JPanel plTwoIndicators = new JPanel(new GridLayout(3,3,5,5));
				plTwoIndicators.add(new JPanel());
				plTwoIndicators.add(new JPanel());
				plTwoIndicators.add(playerTwoHealth);
				plTwoIndicators.add(new JPanel());
				plTwoIndicators.add(new JPanel());
				plTwoIndicators.add(playerTwoMana);
				plTwoIndicators.add(new JPanel());
				plTwoIndicators.add(new JPanel());
				plTwoIndicators.add(playerTwoFood);
					
					/*
					 * player two additions
					 */
				JPanel plTwoSpclPanel = new JPanel(new GridLayout(3,2,2,2));
					JLabel playerTwoATK = new JLabel("ATK:");
						playerTwoATK.setFont(smallLabelFont);
						playerTwoATK.setForeground(Color.blue);
						playerTwoATK.setAlignmentX(Component.LEFT_ALIGNMENT);
					JLabel playerTwoDEF = new JLabel("DEF:");
						playerTwoDEF.setFont(smallLabelFont);
						playerTwoDEF.setForeground(Color.blue);
						playerTwoDEF.setAlignmentX(Component.LEFT_ALIGNMENT);
					JLabel playerTwoCOND = new JLabel("COND:");
						playerTwoCOND.setFont(smallLabelFont);
						playerTwoCOND.setForeground(Color.blue);
						playerTwoCOND.setAlignmentX(Component.LEFT_ALIGNMENT);
					final JLabel playerTwoATKValue = new JLabel("0");
						playerTwoATKValue.setFont(smallLabelFont);
						playerTwoATKValue.setForeground(Color.blue);
						playerTwoATKValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
					final JLabel playerTwoDEFValue = new JLabel("0");
						playerTwoDEFValue.setFont(smallLabelFont);
						playerTwoDEFValue.setForeground(Color.blue);
						playerTwoDEFValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
					final JLabel playerTwoCONDValue = new JLabel(COND_NONE);
						playerTwoCONDValue.setFont(smallLabelFont);
						playerTwoCONDValue.setForeground(Color.blue);
						playerTwoCONDValue.setAlignmentX(Component.RIGHT_ALIGNMENT);
						
						plTwoSpclPanel.add(playerTwoATK);
						plTwoSpclPanel.add(playerTwoATKValue);
						plTwoSpclPanel.add(playerTwoDEF);
						plTwoSpclPanel.add(playerTwoDEFValue);
						plTwoSpclPanel.add(playerTwoCOND);
						plTwoSpclPanel.add(playerTwoCONDValue);
					
					
				/*
				 * Add player two stuff to player two panel	
				 */
				
				plTwoStats.add(plTwoSpclPanel);
				plTwoStats.add(plTwoIndicators);
				plTwoStats.add(plTwoStatusBars);
				plTwoStats.add(plTwoStatusIndicators);
				
				playerTwoNameAndStatus.add(playerTwoName, BorderLayout.WEST);
				playerTwoNameAndStatus.add(playerTwoCondition, BorderLayout.EAST);
				
				/*
				 * Add player panels to top panel
				 */
				topLeftPanel.add(playerOneNameAndStatus, BorderLayout.NORTH);
				topLeftPanel.add(plOneStats, BorderLayout.SOUTH);
				
				topRightPanel.add(playerTwoNameAndStatus, BorderLayout.NORTH);
				topRightPanel.add(plTwoStats, BorderLayout.SOUTH);
				
				topGamePanel.add(topLeftPanel, BorderLayout.WEST);
				topGamePanel.add(topRightPanel, BorderLayout.EAST);
			
			/*
			 * Middle panels
			 */
			final JPanel middleGamePanel = new JPanel(new BorderLayout(5, 5)); //main middle gameplay panel
			
			/*
			 * Active player title pane
			 */
			final JPanel middlePlayerPanel = new JPanel(new BorderLayout(5,5));
				//middlePlayerPanel.setBorder(BorderFactory.createTitledBorder(""));
				
			final JPanel middlePlayerSel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			final JPanel winningPlayer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			
				final JLabel activePlayer = new JLabel();
					activePlayer.setFont(largeLabelFont);
					activePlayer.setAlignmentX(Component.RIGHT_ALIGNMENT);
					activePlayer.setForeground(Color.BLUE);
					
				final JLabel yourMove = new JLabel(", your move!");
					yourMove.setFont(labelFont);
					yourMove.setAlignmentX(Component.RIGHT_ALIGNMENT);
					yourMove.setForeground(Color.red);
					
				final JLabel gamePlayerWinner = new JLabel();
					gamePlayerWinner.setFont(largeLabelFont);
					gamePlayerWinner.setAlignmentX(Component.LEFT_ALIGNMENT);
					gamePlayerWinner.setForeground(Color.BLUE);
				final JLabel winnerLabel = new JLabel(" is the winner!");
					winnerLabel.setFont(labelFont);
					winnerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
					winnerLabel.setForeground(Color.red);
				
					
					middlePlayerSel.add(activePlayer);
					middlePlayerSel.add(yourMove);
					
					middlePlayerPanel.add(middlePlayerSel, BorderLayout.WEST);
					middlePlayerPanel.add(winningPlayer, BorderLayout.EAST);

					
			final JPanel middleLeftPanel = new JPanel(new BorderLayout(5,5)); // attack button and dice animaiton pane
			
			final JPanel attackButtons = new JPanel(new GridLayout(3, 1, 2, 2));
				attackButtons.setBorder(BorderFactory.createTitledBorder(""));
			
			final JPanel middleRightPanel = new JPanel(new BorderLayout(5,5));	// game animation will play here			
				middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))), BorderLayout.CENTER);
			/*
			 * dice animation pane
			 */
			final JPanel diceAnimationPanel = new JPanel(new GridLayout(2, 2, 1, 1)); // add to middle left panel. panel to play dice animation
				//diceAnimationPanel.setBorder(BorderFactory.createTitledBorder(""));
				diceAnimationPanel.setPreferredSize(diceAnimationPanel.getPreferredSize());
				
				final JPanel die1 = new JPanel(new BorderLayout(1, 1));
					die1.setBorder(BorderFactory.createTitledBorder(""));
					die1.setPreferredSize(new Dimension(30,30));
					die1.setBackground(Color.red);
					
					Icon dieOneIcon = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_1_85px.png"));
					final JLabel dieOnePic = new JLabel(dieOneIcon);
					
					die1.add(dieOnePic, BorderLayout.CENTER);
					
				final JPanel die2 = new JPanel(new BorderLayout(1, 1));
					die2.setBorder(BorderFactory.createTitledBorder(""));
					die2.setPreferredSize(new Dimension(30,30));
					die2.setBackground(Color.red);
					
					Icon dieTwoIcon = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_2_85px.png"));
					final JLabel dieTwoPic = new JLabel(dieTwoIcon);
					
					die2.add(dieTwoPic, BorderLayout.CENTER);
					
				final JPanel die3 = new JPanel(new BorderLayout(1, 1));
					die3.setBorder(BorderFactory.createTitledBorder(""));
					die3.setPreferredSize(new Dimension(30,30));
					die3.setBackground(Color.red);
					
					Icon dieThreeIcon = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_3_85px.png"));
					final JLabel dieThreePic = new JLabel(dieThreeIcon);
					
					die3.add(dieThreePic, BorderLayout.CENTER);
					
				final JPanel die4 = new JPanel(new BorderLayout(1, 1));
					die4.setBorder(BorderFactory.createTitledBorder(""));
					die4.setPreferredSize(new Dimension(30,30));
					die4.setBackground(Color.red);
					
					Icon dieFourIcon = new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_4_85px.png"));
					final JLabel dieFourPic = new JLabel(dieFourIcon);
					
					die4.add(dieFourPic, BorderLayout.CENTER);
				
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
				middleGamePanel.add(middlePlayerPanel, BorderLayout.NORTH);
				middleGamePanel.add(playerPane, BorderLayout.CENTER);
			
			/*
			 * Bottom panels
			 */
			final JPanel bottomGamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				bottomGamePanel.setBorder(BorderFactory.createTitledBorder(""));
				
			final JButton gameExit = new JButton("Exit");
				gameExit.setToolTipText("Exit game");
			
			final JButton gameSave = new JButton("Save");
				gameSave.setToolTipText("Save game stats");
				
			final JButton mainMenu = new JButton("Main Menu");
				mainMenu.setToolTipText("Return to main menu");
				
				bottomGamePanel.add(mainMenu);
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
					JOptionPane.showMessageDialog(gameFrame,  "Function not implemented.");
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
					
					if(newOnePlayerUserText.getText().isEmpty()){
						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name");
						return;
					}
					
					usernameOne = newOnePlayerUserText.getText().toLowerCase();
					
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
					if(!singlePlayer){
						if(stats.findPlayer(usernameOne) < 0){
							stats.scoreboard.addNewPlayerFromUsername(usernameOne);
						}else if(stats.findPlayer(usernameTwo) < 0){
							stats.scoreboard.addNewPlayerFromUsername(usernameTwo);
						}
						whichPlayer.setPlayerNames(usernameOne, usernameTwo);
					}
					
					/*
					 * Launch player select frame in AWT event dispatch thread
					 */
						if(gameFrame.isEnabled()){
							gameFrame.setEnabled(false);
						}
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
						usernameOne = "";
						usernameTwo = "";
						foundPlayerOne.setText("");
						foundPlayerTwo.setText("");
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
			dblAtk.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent we){
					if(!gameFrame.isEnabled()){
						gameFrame.setEnabled(true);
					}
					
					die1.setBackground(Color.red);
					die2.setBackground(Color.red);
					die3.setBackground(Color.red);
					die4.setBackground(Color.red);
					
					die1.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_1_85px.png"))), BorderLayout.CENTER);
					die2.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_1_85px.png"))), BorderLayout.CENTER);
					die3.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_1_85px.png"))), BorderLayout.CENTER);
					die4.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_1_85px.png"))), BorderLayout.CENTER);
					
					plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
					plTwoManaStatus.setValue(game.getPlayerTwoStatus().getMana());
					playerTwoATKValue.setText(Integer.toString(game.getPlayerTwoStatus().getAtk()));
					playerTwoDEFValue.setText(Integer.toString(game.getPlayerTwoStatus().getDef()));
					
					
					plOneHealthStatus.setValue(game.getPlayerOneStatus().getHitPts());
					plOneManaStatus.setValue(game.getPlayerOneStatus().getMana());
					playerOneATKValue.setText(Integer.toString(game.getPlayerOneStatus().getAtk()));
					playerOneDEFValue.setText(Integer.toString(game.getPlayerOneStatus().getDef()));
					
					playerTwoHealthRatio.setText(game.getPlayerTwoStatus().getHitPts() + "/" + Status.getMaxHP());
					playerTwoManaRatio.setText(game.getPlayerTwoStatus().getMana() + "/" + Status.getMaxMana());
					
					playerOneHealthRatio.setText(game.getPlayerOneStatus().getHitPts() + "/" + Status.getMaxHP());
					playerOneManaRatio.setText(game.getPlayerOneStatus().getMana() + "/" + Status.getMaxMana());
					
					if(winner.equals(Winner.NONE)){
						switch(game.getPlayerOneStatus().getCondition()){
						case FROZEN:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/frozen.png"))));
							playerOneCONDValue.setText(COND_FROZEN);
							break;
						case AURA1:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerOneCONDValue.setText(COND_AURA);
							break;
						case AURA2:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerOneCONDValue.setText(COND_AURA);
							break;
						case POISON1:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_1.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON2:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_2.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON3:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_3.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON4:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_4.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON5:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_5.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case NONE:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							playerOneCONDValue.setText(COND_NONE);
							break;
						default:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							break;
						}
						
						switch(game.getPlayerTwoStatus().getCondition()){
						case FROZEN:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/frozen.png"))));
							playerTwoCONDValue.setText(COND_FROZEN);
							break;
						case AURA1:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerTwoCONDValue.setText(COND_AURA);
							break;
						case AURA2:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerTwoCONDValue.setText(COND_AURA);
							break;
						case POISON1:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_1.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON2:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_2.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON3:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_3.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON4:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_4.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON5:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_5.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case NONE:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							playerTwoCONDValue.setText(COND_NONE);
							break;
						default:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							break;
						}
						middleRightPanel.removeAll();
							
								if(!game.isPlayerOneTurn()){
									activePlayer.setText(usernameTwo);
									// TODO add double attack animation
								}else if (game.isPlayerOneTurn()){
									activePlayer.setText(usernameOne);
									// TODO add double attack animation
								}

					}else if(winner.equals(Winner.PLAYER_ONE)){
						plTwoHealthStatus.setValue(0);
						playerTwoHealthRatio.setText(0 + "/" + Status.getMaxHP());
						
						gamePlayerWinner.setText(game.getPlayerOneUsername());
						winningPlayer.add(gamePlayerWinner);
						winningPlayer.add(winnerLabel);
						
						middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))));
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
					}else if(winner.equals(Winner.PLAYER_TWO)){
						plOneHealthStatus.setValue(0);
						playerOneHealthRatio.setText(0 + "/" + Status.getMaxHP());
						
						gamePlayerWinner.setText(game.getPlayerTwoUsername());
						winningPlayer.add(gamePlayerWinner);
						winningPlayer.add(winnerLabel);
						
						middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))));
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
					}
					
					if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 2) && plTwoHealthStatus.getValue() > (MAX_HEALTH / 5)){
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 4)){
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 2) && plOneHealthStatus.getValue() > (MAX_HEALTH / 5)){
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 4)){
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plTwoManaStatus.getValue() <= (MAX_MANA / 2) && plTwoManaStatus.getValue() > (MAX_MANA / 5)){
						plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoManaStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plTwoManaStatus.getValue() <= (MAX_MANA / 4)){
						plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoManaStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plOneManaStatus.getValue() <= (MAX_MANA / 2) && plOneManaStatus.getValue() > (MAX_MANA / 5)){
						plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneManaStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plOneManaStatus.getValue() <= (MAX_MANA / 4)){
						plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneManaStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					gameFrame.pack();
					playerPane.setDividerLocation(.25);
					gameFrame.validate();
					
				}
			});
			
			/*
			 * Stop dice animation and add dice pic based on player dice roll
			 */
			stop.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent we){
					if(!gameFrame.isEnabled()){
						gameFrame.setEnabled(true);
					}
					int[] rollResult = game.getLastRoll();
					
					die1.removeAll();
					die2.removeAll();
					die3.removeAll();
					die4.removeAll();
					
					die1.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + 
							rollResult[0] + "_85px.png"))), BorderLayout.CENTER);
					die2.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + 
							rollResult[1] + "_85px.png"))), BorderLayout.CENTER);
					die3.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + 
							rollResult[2] + "_85px.png"))), BorderLayout.CENTER);
					die4.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + 
							rollResult[3] + "_85px.png"))), BorderLayout.CENTER);
					
					plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
					plTwoManaStatus.setValue(game.getPlayerTwoStatus().getMana());
					playerTwoATKValue.setText(Integer.toString(game.getPlayerTwoStatus().getAtk()));
					playerTwoDEFValue.setText(Integer.toString(game.getPlayerTwoStatus().getDef()));
					
					
					plOneHealthStatus.setValue(game.getPlayerOneStatus().getHitPts());
					plOneManaStatus.setValue(game.getPlayerOneStatus().getMana());
					playerOneATKValue.setText(Integer.toString(game.getPlayerOneStatus().getAtk()));
					playerOneDEFValue.setText(Integer.toString(game.getPlayerOneStatus().getDef()));
					
					playerTwoHealthRatio.setText(game.getPlayerTwoStatus().getHitPts() + "/" + Status.getMaxHP());
					playerTwoManaRatio.setText(game.getPlayerTwoStatus().getMana() + "/" + Status.getMaxMana());
					
					playerOneHealthRatio.setText(game.getPlayerOneStatus().getHitPts() + "/" + Status.getMaxHP());
					playerOneManaRatio.setText(game.getPlayerOneStatus().getMana() + "/" + Status.getMaxMana());
					
					if(winner.equals(Winner.NONE)){
						switch(game.getPlayerOneStatus().getCondition()){
						case FROZEN:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/frozen.png"))));
							playerOneCONDValue.setText(COND_FROZEN);
							break;
						case AURA1:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerOneCONDValue.setText(COND_AURA);
							break;
						case AURA2:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerOneCONDValue.setText(COND_AURA);
							break;
						case POISON1:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_1.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON2:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_2.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON3:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_3.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON4:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_4.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON5:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_5.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case NONE:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							playerOneCONDValue.setText(COND_NONE);
							break;
						default:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							break;
						}
						
						switch(game.getPlayerTwoStatus().getCondition()){
						case FROZEN:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/frozen.png"))));
							playerTwoCONDValue.setText(COND_FROZEN);
							break;
						case AURA1:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerTwoCONDValue.setText(COND_AURA);
							break;
						case AURA2:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerTwoCONDValue.setText(COND_AURA);
							break;
						case POISON1:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_1.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON2:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_2.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON3:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_3.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON4:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_4.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON5:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_5.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case NONE:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							playerTwoCONDValue.setText(COND_NONE);
							break;
						default:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							break;
						}
						middleRightPanel.removeAll();
						switch(move){
						case ATTACK:
								if(!game.isPlayerOneTurn()){
									middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/baseattackP1.gif"))), BorderLayout.CENTER);
									activePlayer.setText(usernameTwo);
	
								}else if(game.isPlayerOneTurn()){
									middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/baseattackP2.gif"))), BorderLayout.CENTER);
									activePlayer.setText(usernameOne);
									
								}else{
									JOptionPane.showMessageDialog(gameFrame, "Something has gone horribly wrong in Winner PlayNextTurn() method");
								}
							
							break;
						case FREEZE:
							if(game.getPlayerOneStatus().getCondition().equals(Condition.FROZEN)){
								activePlayer.setText(usernameTwo);
							}else if(game.getPlayerTwoStatus().getCondition().equals(Condition.FROZEN)){
								activePlayer.setText(usernameOne);
							}else{
								
								if(game.isPlayerOneTurn()){
									activePlayer.setText(usernameOne);
									// TODO add frozen player two animation
								}else{
									activePlayer.setText(usernameTwo);
									// TODO add frozen player one animation
								}
							}
							
							break;
						case DOUBLEATK:
							
								if(!game.isPlayerOneTurn()){
									activePlayer.setText(usernameTwo);
									// TODO add double attack animation
								}else if (game.isPlayerOneTurn()){
									activePlayer.setText(usernameOne);
									// TODO add double attack animation
								}
							break;
						case POISON:
							
							if(!game.isPlayerOneTurn()){
								activePlayer.setText(usernameTwo);
								// TODO add poison player two animation
							}else if(game.isPlayerOneTurn()){
								activePlayer.setText(usernameOne);
								// TODO add poison player one animation
							}
							
							break;
						case AURA:
							
							if(!game.isPlayerOneTurn()){
								activePlayer.setText(usernameTwo);
								// TODO add aura player two animation
							}else if(game.isPlayerOneTurn()){
								activePlayer.setText(usernameOne);
								// TODO add aura player one animation
							}
							
							break;
						case CHARGE:
							
							if(!game.isPlayerOneTurn()){
								activePlayer.setText(usernameTwo);
								// TODO add aura player two animation
							}else if(game.isPlayerOneTurn()){
								activePlayer.setText(usernameOne);
								// TODO add aura player one animation
							}
							
							break;
						default:
							JOptionPane.showMessageDialog(gameFrame, "Something terrible has happened!");
						
						}

					}else if(winner.equals(Winner.PLAYER_ONE)){
						plTwoHealthStatus.setValue(0);
						playerTwoHealthRatio.setText(0 + "/" + Status.getMaxHP());
						
						gamePlayerWinner.setText(game.getPlayerOneUsername());
						winningPlayer.add(gamePlayerWinner);
						winningPlayer.add(winnerLabel);
						
						middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))));
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
					}else if(winner.equals(Winner.PLAYER_TWO)){
						plOneHealthStatus.setValue(0);
						playerOneHealthRatio.setText(0 + "/" + Status.getMaxHP());
						
						gamePlayerWinner.setText(game.getPlayerTwoUsername());
						winningPlayer.add(gamePlayerWinner);
						winningPlayer.add(winnerLabel);
						
						middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))));
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
					}
					
					if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 2) && plTwoHealthStatus.getValue() > (MAX_HEALTH / 5)){
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 4)){
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 2) && plOneHealthStatus.getValue() > (MAX_HEALTH / 5)){
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 4)){
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plTwoManaStatus.getValue() <= (MAX_MANA / 2) && plTwoManaStatus.getValue() > (MAX_MANA / 5)){
						plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoManaStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plTwoManaStatus.getValue() <= (MAX_MANA / 4)){
						plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoManaStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plTwoManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plOneManaStatus.getValue() <= (MAX_MANA / 2) && plOneManaStatus.getValue() > (MAX_MANA / 5)){
						plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneManaStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plOneManaStatus.getValue() <= (MAX_MANA / 4)){
						plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneManaStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plOneManaStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneManaStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					gameFrame.pack();
					playerPane.setDividerLocation(.25);
					gameFrame.validate();
				}
			});
			
			/*
			 * Show game play panel when player select panel closes
			 */
			whichPlayer.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent we){
					if(!gameFrame.isEnabled()){
						gameFrame.setEnabled(true);
					}
					gameFrame.setPreferredSize(GAME_SIZE);
					gameFrame.setResizable(false);
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					
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
						
						playerOneName.setText(usernameOne);
						playerTwoName.setText(usernameTwo);
					
						playerOneHealthRatio.setText(game.getPlayerOneStatus().getHitPts() + "/" + Status.getMaxHP());
						playerOneManaRatio.setText(game.getPlayerOneStatus().getMana() + "/" + Status.getMaxMana());
						playerOneFoodRatio.setText(game.getPlayerOneStatus().getFoodCount() + "/" + Status.getMaxFood());
						plOneHealthStatus.setValue(game.getPlayerOneStatus().getHitPts());
						plOneManaStatus.setValue(game.getPlayerOneStatus().getMana());
						plOneFoodStatus.setValue(game.getPlayerOneStatus().getFoodCount());
						
						playerTwoHealthRatio.setText(game.getPlayerTwoStatus().getHitPts() + "/" + Status.getMaxHP());
						playerTwoManaRatio.setText(game.getPlayerTwoStatus().getMana() + "/" + Status.getMaxMana());
						playerTwoFoodRatio.setText(game.getPlayerTwoStatus().getFoodCount() + "/" + Status.getMaxFood());
						plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
						plTwoManaStatus.setValue(game.getPlayerTwoStatus().getMana());
						plTwoFoodStatus.setValue(game.getPlayerTwoStatus().getFoodCount());
						
						gameFrame.setContentPane(gamePlayPanel);
						gameFrame.pack();
						playerPane.setDividerLocation(.25);
						gameFrame.validate();
				}
			});
			
			/*
			 * Show player select before game play panel
			 */
			newTwoPlayerUserStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					if(userName1Text.getText().isEmpty() || userName2Text.getText().isEmpty()){
						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name for both players");
						return;
					}
					if(userName1Text.getText().length() > 15 || userName2Text.getText().length() > 15){
						JOptionPane.showMessageDialog(gameFrame, "Usernames must be less than 15 characters");
						userName1Text.setText("");
						userName2Text.setText("");
						return;
					}
					if(userName1Text.getText().equals(userName2Text.getText())){
						JOptionPane.showMessageDialog(gameFrame, "Usernames cannot match");
						userName1Text.setText("");
						userName2Text.setText("");
						return;
					}
					usernameOne = userName1Text.getText().toLowerCase();
					usernameTwo = userName2Text.getText().toLowerCase();
					singlePlayer = false;
					
					// TODO add call to check for existing user profiles
					
					if(stats.findPlayer(usernameOne) == 0 && stats.findPlayer(usernameTwo) == 0){
						newGamePanel.removeAll();
						gameFrame.remove(newGamePanel);
						foundPlayerOne.setText(usernameOne);
						foundPlayerTwo.setText(usernameTwo);
						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
						newGamePanel.add(splitPane);
						gameFrame.setContentPane(newGamePanel);
						gameFrame.pack();
						splitPane.setDividerLocation(.30);
						gameFrame.validate();
					}else if(stats.findPlayer(usernameOne) == 0 && stats.findPlayer(usernameTwo) < 0){
						newGamePanel.removeAll();
						gameFrame.remove(newGamePanel);
						foundPlayerOne.setText(usernameOne);
						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
						newGamePanel.add(splitPane);
						gameFrame.setContentPane(newGamePanel);
						gameFrame.pack();
						splitPane.setDividerLocation(.30);
						gameFrame.validate();
					}else if(stats.findPlayer(usernameOne) < 0 && stats.findPlayer(usernameTwo) == 0){
						newGamePanel.removeAll();
						gameFrame.remove(newGamePanel);
						foundPlayerTwo.setText(usernameTwo);
						splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGamePlayersExist, imagePanel);
						newGamePanel.add(splitPane);
						gameFrame.setContentPane(newGamePanel);
						gameFrame.pack();
						splitPane.setDividerLocation(.30);
						gameFrame.validate();
					}else{
						gameFrame.setEnabled(false);
						stats.scoreboard.addNewPlayerFromUsername(usernameOne);
						stats.scoreboard.addNewPlayerFromUsername(usernameTwo);
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
			
			/*
			 * Statistics 
			 */
			statFind.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					String user;
					JTable statTable;
					
					if(singleUserStatText.getText().isEmpty()){
						JOptionPane.showMessageDialog(gameFrame, "You must enter a user name");
						return;
					}
					user = singleUserStatText.getText().toLowerCase();
					if(stats.findPlayer(user) < 0){
						JOptionPane.showMessageDialog(gameFrame, "Username does not exist");
						singleUserStatText.setText("");
						return;
					}
					
					splitPane.removeAll();
					statsPanel.removeAll();
					
					statTable = new JTable(stats.getTableOneName(user));
					scrollStats = new JScrollPane(statTable);
					
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statPanelOptionsButtons, scrollStats);
					statsPanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(statsPanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			singleStatExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					if(!singleUserStatText.getText().isEmpty())
						singleUserStatText.setText("");
					
					splitPane.removeAll();
					statsPanel.remove(splitPane);
					
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
					JTable statTable;
					
					splitPane.removeAll();
					statsPanel.remove(splitPane);

					statTable = new JTable(stats.getTableAllNames());
					scrollStats = new JScrollPane(statTable);
					
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statPanelOptionsButtons, scrollStats);
					statsPanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(statsPanel);
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
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
			
			singleUserStats.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					if(!singleUserStatText.getText().isEmpty())
						singleUserStatText.setText("");
					
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
			 * About game
			 */
			abtGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					File aboutGame = new File("src/sliceAndDice/game_resources/about.txt");
					if(Desktop.isDesktopSupported()){
						try {
							Desktop.getDesktop().edit(aboutGame);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(gameFrame, "File Read Error: " + e.getMessage());
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(gameFrame, "Desktop not supported");
					}
				}
			});
			
			/*
			 * game play button listeners
			 */
			attackButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(gameFrame.isEnabled()){
						gameFrame.setEnabled(false);
					}
					winner = game.updateCondition();
					
					if(winner.equals(Winner.NONE)){
						move = Move.ATTACK;
						try{
							winner = game.PlayNextTurn(move);
						}catch(IllegalArgumentException e){
							JOptionPane.showMessageDialog(gameFrame, "Fatal Error! " + e.getMessage());
							if(!gameFrame.isEnabled()){
								gameFrame.setEnabled(true);
							}
							return;
						}
	
						die1.removeAll();
						die2.removeAll();
						die3.removeAll();
						die4.removeAll();
						
						die1.setBackground(Color.red);
						die2.setBackground(Color.red);
						die3.setBackground(Color.red);
						die4.setBackground(Color.red);
						
						die1.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
						die2.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_2.gif"))), BorderLayout.CENTER);
						die3.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_3.gif"))), BorderLayout.CENTER);
						die4.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_4.gif"))), BorderLayout.CENTER);
						
						middleRightPanel.removeAll();
						middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))), BorderLayout.CENTER);
						gameFrame.pack();
						gameFrame.validate();
	
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								stop.showStopRoll();
							}
						});
					}
				}
			});
			
			healButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					winner = game.updateCondition();
					
					move = Move.FOOD;
					if(game.nextMoveLegality(move).equals(IllegalMove.NOFOOD)){
						JOptionPane.showMessageDialog(gameFrame, "You are out of food!");
						return;
					}
					
					try{
						winner = game.PlayNextTurn(move);
					}catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(gameFrame, "Fatal Error! " + e.getMessage());
						if(!gameFrame.isEnabled()){
							gameFrame.setEnabled(true);
						}
						return;
					}
					
					plTwoHealthStatus.setValue(game.getPlayerTwoStatus().getHitPts());
					plTwoManaStatus.setValue(game.getPlayerTwoStatus().getMana());
					plTwoFoodStatus.setValue(game.getPlayerTwoStatus().getFoodCount());
					playerTwoATKValue.setText(Integer.toString(game.getPlayerTwoStatus().getAtk()));
					playerTwoDEFValue.setText(Integer.toString(game.getPlayerTwoStatus().getDef()));
					
					plOneHealthStatus.setValue(game.getPlayerOneStatus().getHitPts());
					plOneManaStatus.setValue(game.getPlayerOneStatus().getMana());
					plOneFoodStatus.setValue(game.getPlayerOneStatus().getFoodCount());
					playerOneATKValue.setText(Integer.toString(game.getPlayerOneStatus().getAtk()));
					playerOneDEFValue.setText(Integer.toString(game.getPlayerOneStatus().getDef()));
					
					playerTwoHealthRatio.setText(game.getPlayerTwoStatus().getHitPts() + "/" + Status.getMaxHP());
					playerTwoManaRatio.setText(game.getPlayerTwoStatus().getMana() + "/" + Status.getMaxMana());
					playerTwoFoodRatio.setText(game.getPlayerTwoStatus().getFoodCount() + "/" + Status.getMaxFood());
					
					playerOneHealthRatio.setText(game.getPlayerOneStatus().getHitPts() + "/" + Status.getMaxHP());
					playerOneManaRatio.setText(game.getPlayerOneStatus().getMana() + "/" + Status.getMaxMana());
					playerOneFoodRatio.setText(game.getPlayerOneStatus().getFoodCount() + "/" + Status.getMaxFood());
					
						switch(game.getPlayerOneStatus().getCondition()){
						case FROZEN:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/frozen.png"))));
							playerOneCONDValue.setText(COND_FROZEN);
							break;
						case AURA1:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerOneCONDValue.setText(COND_AURA);
							break;
						case AURA2:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerOneCONDValue.setText(COND_AURA);
							break;
						case POISON1:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_1.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON2:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_2.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON3:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_3.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON4:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_4.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case POISON5:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_5.png"))));
							playerOneCONDValue.setText(COND_POISON);
							break;
						case NONE:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							playerOneCONDValue.setText(COND_NONE);
							break;
						default:
							playerOneCondition.removeAll();
							playerOneCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							break;
						}
						
						switch(game.getPlayerTwoStatus().getCondition()){
						case FROZEN:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/frozen.png"))));
							playerTwoCONDValue.setText(COND_FROZEN);
							break;
						case AURA1:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerTwoCONDValue.setText(COND_AURA);
							break;
						case AURA2:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/aura.gif"))));
							playerTwoCONDValue.setText(COND_AURA);
							break;
						case POISON1:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_1.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON2:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_2.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON3:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_3.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON4:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_4.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case POISON5:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/poison_5.png"))));
							playerTwoCONDValue.setText(COND_POISON);
							break;
						case NONE:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							playerTwoCONDValue.setText(COND_NONE);
							break;
						default:
							playerTwoCondition.removeAll();
							playerTwoCondition.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/ok.png"))));
							break;
						}
					
					middleRightPanel.removeAll();
					
					if(winner.equals(Winner.NONE)){
						middleRightPanel.removeAll();
						if(!game.isPlayerOneTurn()){
							
							middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/eatfoodP1.gif"))), BorderLayout.CENTER);							
							activePlayer.setText(usernameTwo);
							
						}else if(game.isPlayerOneTurn()){
							
							middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/eatfoodP2.gif"))), BorderLayout.CENTER);								
							activePlayer.setText(usernameOne);
							
						}else{
							JOptionPane.showMessageDialog(gameFrame, "Something has gone horribly wrong in Winner PlayNextTurn() method");
						}
						gameFrame.setContentPane(gamePlayPanel);
					}else if(winner.equals(Winner.PLAYER_ONE)){
						JOptionPane.showMessageDialog(gameFrame, "Something has gone horribly wrong: There should be no winner here");
						return;
						
					}else if(winner.equals(Winner.PLAYER_TWO)){
						JOptionPane.showMessageDialog(gameFrame, "Something has gone horribly wrong: There should be no winner here");
						return;
					}
					
					if(plTwoFoodStatus.getValue() <= (MAX_FOOD / 2) && plTwoFoodStatus.getValue() > (MAX_FOOD / 5)){
						plTwoFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoFoodStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plTwoFoodStatus.getValue() <= (MAX_FOOD / 5)){
						plTwoFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoFoodStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plTwoFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoFoodStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 2) && plTwoHealthStatus.getValue() > (MAX_HEALTH / 5)){
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plTwoHealthStatus.getValue() <= (MAX_HEALTH / 4)){
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plTwoHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plTwoHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plOneFoodStatus.getValue() <= (MAX_FOOD / 2) && plOneFoodStatus.getValue() > (MAX_FOOD / 5)){
						plOneFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneFoodStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plOneFoodStatus.getValue() <= (MAX_FOOD / 5)){
						plOneFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneFoodStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plOneFoodStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneFoodStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					
					if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 2) && plOneHealthStatus.getValue() > (MAX_HEALTH / 5)){
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  orangeDefaults);
					}else if(plOneHealthStatus.getValue() <= (MAX_HEALTH / 4)){
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  redDefaults);
					}else{
						plOneHealthStatus.putClientProperty("Numbus.Overrides.InheritDefaults", Boolean.TRUE);
						plOneHealthStatus.putClientProperty("Nimbus.Overrides",  greenDefaults);
					}
					gameFrame.pack();
					splitPane.setDividerLocation(.30);
					gameFrame.validate();
				}
			});
			
			chooseAtk.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent we){
					if(!gameFrame.isEnabled()){
						gameFrame.setEnabled(true);
					}
					move = chooseAtk.getMove();
					
					if(move == null){
						JOptionPane.showMessageDialog(gameFrame, "You have not selected a move. Try again");
						return;
					}
	
					middleRightPanel.removeAll();
					
					if(winner.equals(Winner.NONE)){
						switch(move){
						case FREEZE:
							die1.setBackground(Color.blue);
							die2.setBackground(Color.blue);
							die3.setBackground(Color.red);
							die4.setBackground(Color.red);
							break;
						case AURA:
							die1.setBackground(Color.cyan);
							die2.setBackground(Color.cyan);
							die3.setBackground(Color.cyan);
							die4.setBackground(Color.cyan);
							break;
						case POISON:
							die1.setBackground(Color.green);
							die2.setBackground(Color.green);
							die3.setBackground(Color.red);
							die4.setBackground(Color.red);
							break;
						case DOUBLEATK:
							die1.setBackground(Color.black);
							die2.setBackground(Color.black);
							die3.setBackground(Color.black);
							die4.setBackground(Color.black);
							break;
						case CHARGE:
							die1.setBackground(Color.red);
							die2.setBackground(Color.red);
							die3.setBackground(Color.red);
							die4.setBackground(Color.red);
							break;
						default:
							JOptionPane.showMessageDialog(gameFrame, "Special Attack Selection Error");
							//break;
						}
						die1.removeAll();
						die2.removeAll();
						die3.removeAll();
						die4.removeAll();
						
						try{
							winner = game.PlayNextTurn(move);
						}catch(IllegalArgumentException e){
							JOptionPane.showMessageDialog(gameFrame, "Fatal Error! " + e.getMessage());
							if(!gameFrame.isEnabled()){
								gameFrame.setEnabled(true);
							}
							return;
						}
						
						if(move.equals(Move.DOUBLEATK)){
							gameFrame.setEnabled(false);
							SwingUtilities.invokeLater(new Runnable(){
								public void run(){
									int[] rollResult = game.getLastRoll();
									dblAtk.showDblAtk(rollResult);
								}
							});
						}else{
							gameFrame.setEnabled(false);
							die1.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
							die2.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_2.gif"))), BorderLayout.CENTER);
							die3.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_3.gif"))), BorderLayout.CENTER);
							die4.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_4.gif"))), BorderLayout.CENTER);
							SwingUtilities.invokeLater(new Runnable(){
								public void run(){
									stop.showStopRoll();
								}
							});
						}
					}else if(winner.equals(Winner.PLAYER_ONE)){
						plTwoHealthStatus.setValue(0);
						playerTwoHealthRatio.setText(0 + "/" + Status.getMaxHP());
						
						gamePlayerWinner.setText(game.getPlayerOneUsername());
						winningPlayer.add(gamePlayerWinner);
						winningPlayer.add(winnerLabel);
						
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
						
					}else if(winner.equals(Winner.PLAYER_TWO)){
						plOneHealthStatus.setValue(0);
						playerOneHealthRatio.setText(0 + "/" + Status.getMaxHP());
						
						gamePlayerWinner.setText(game.getPlayerTwoUsername());
						winningPlayer.add(gamePlayerWinner);
						winningPlayer.add(winnerLabel);
						
						playerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, middleLeftPanel, middleRightPanel);
						middleGamePanel.add(playerPane, BorderLayout.CENTER);
						gamePlayPanel.add(middleGamePanel, BorderLayout.CENTER);
						
					}

					middleRightPanel.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/defaultplayers.jpg"))), BorderLayout.CENTER);
					gameFrame.pack();
					gameFrame.validate();
				}
			});
			
			spAttack.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					gameFrame.setEnabled(false);
					winner = game.updateCondition();
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							if(game.isPlayerOneTurn()){
								chooseAtk.showChooseMove(game.getPlayerOneStatus().getMana());
							}else{
								chooseAtk.showChooseMove(game.getPlayerTwoStatus().getMana());
							}
						}
					});
				}
			});
			
			/*
			 * game play exit/save/main menu button listeners
			 */
			mainMenu.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
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
				gameFrame.dispose();
				}
			});
			gameSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					
					//JOptionPane.showMessageDialog(gameFrame, "Function disabled");
					try{
						stats.scoreboard.sendPlayerDataToFile();
					}catch(IOException e){
						JOptionPane.showMessageDialog(gameFrame, "Error: " + e.getMessage());
					}
					JOptionPane.showMessageDialog(gameFrame, "Game saved!");
				}
			});
			
			gameExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
				Object[] inputs = {"Save", "Exit"};
				int ans = JOptionPane.showOptionDialog(gameFrame, "Exit without saving?", "Slice And Dice",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png"))), inputs, null);
				
					if(ans == 0){
						try{
							stats.scoreboard.sendPlayerDataToFile();
						}catch(IOException e){
							JOptionPane.showMessageDialog(gameFrame, "Error: " + e.getMessage());
						}
						JOptionPane.showMessageDialog(gameFrame, "Game saved!");
					}else{
						gameFrame.dispose();
						System.exit(0);
					}
				}
			});
			
				/*
				 * Initial frame view with mode select panel
				 */
				splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modePanelButtons, imagePanel);
				modePanel.add(splitPane, BorderLayout.CENTER);
				gameFrame.setContentPane(modePanel);
				gameFrame.pack();
				splitPane.setDividerLocation(.30);
				gameFrame.setLocationRelativeTo(null);
				gameFrame.setVisible(true);
				
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
 * Display button to stop in game dice roll
 * @author Jacob
 *
 */
@SuppressWarnings("serial")
class StopRoll extends JFrame{
	
	private JButton stopButton;
	private JPanel stopPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	Font smallButtonFont = new Font("Trebuchet MS", Font.BOLD, 24);
	Font labelFont = new Font("Trebuchet MS", Font.BOLD, 28);
	
	/**
	 * Constructor
	 */
	public StopRoll(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Slice And Dice");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png")));
		this.setPreferredSize(new Dimension(400, 200));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		stopPanel = new JPanel(new BorderLayout(5,5));
		topPanel = new JPanel(new BorderLayout(5,5));
		bottomPanel = new JPanel(new GridLayout(1,3,5,5));
		
		stopPanel.setBorder(BorderFactory.createTitledBorder(""));
		stopButton = new JButton("Stop Roll");
		stopButton.setToolTipText("Push to stop your roll of the dice");
		stopButton.addActionListener(new StopTheRoll());
		
		bottomPanel.add(new JPanel());
		bottomPanel.add(stopButton);
		bottomPanel.add(new JPanel());
		
		JLabel instruct = new JLabel("Push button to stop dice roll");
		instruct.setFont(labelFont);
		instruct.setForeground(Color.red);
		
		topPanel.add(instruct, BorderLayout.CENTER);
		
		stopPanel.add(topPanel, BorderLayout.CENTER);
		stopPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setContentPane(stopPanel);
	}
	
	/**
	 * Pack and show frame
	 */
	private void packAndShow(){
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Dispose frame
	 */
	private void disposeFrame(){
		StopRoll.this.dispose();
	}
	
	/**
	 * Show frame
	 */
	public void showStopRoll(){			
			packAndShow();
	}
	
	public class StopTheRoll implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			disposeFrame();
		}
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
	
	/**
	 * Paint method.  Gets custom color from Graphics2D class
	 */
	@Override
	public void paint(Graphics2D g, JComponent object, int width, int height) {
		g.setColor(color);
		g.fillRect(0, 0, width - 1, height - 1);
	}
}

/**
 * Get player statistic class
 * @author Jacob
 *
 */
@SuppressWarnings("rawtypes")
class GetStats{
	
	Scoreboard scoreboard;
	ArrayList<Player> players;
	Vector<String> colNames;
	Vector<Vector> rowData;
	Player findPlayerStats;
	boolean isVisible;
	
	/**
	 * Constructor
	 */
	public GetStats(){
		scoreboard = new Scoreboard();
		try{
			scoreboard.readDataIntoArrayListFromFile();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		players = Scoreboard.getPlayerArrayList();
		findPlayerStats = new Player();
		colNames = new Vector<String>();
		rowData = new Vector<Vector>();
		isVisible = false;
		setColNames();
	}
	
	/**
	 * Find player stats by user name.  Returns -1 if not found.
	 * @param username
	 * @return found
	 */
	public int findPlayer(String username){
		int found = -1;
		Player tmp = Scoreboard.getPlayerByUsername(username);
		if(tmp != null){
			found = 0;
		}
//		System.out.println(found);
		return found;
	}
	
	/**
	 * Loads column name vector
	 */
	private void setColNames(){
		colNames.add("Username");
		colNames.add("Score");
		colNames.add("Rank");
		colNames.add("Total Games");
		colNames.add("Total Wins");
		colNames.add("Total Attacks");
		colNames.add("Total Meals");
		colNames.add("Total Health Lost");
		colNames.add("Total Mana Used");
		colNames.add("Total Food");
	}
	
	/**
	 * Create vector of single user stats based on user name
	 * @param username
	 */
	private void getSinglePlayer(String username){
		findPlayerStats = Scoreboard.getPlayerByUsername(username);
		
		if(isVisible){
			rowData.clear();
		}
		
		if(findPlayerStats != null){
			Vector<String> foundPlayer = new Vector<String>();
			foundPlayer.add(findPlayerStats.getUsername());
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().score));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().rank));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getGame()));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getWin()));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getAttack()));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getMeal()));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getHPLost()));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getManaUsed()));
			foundPlayer.add(String.valueOf(findPlayerStats.getPlayerData().getFoodUsed()));
			rowData.add(foundPlayer);
			isVisible = true;
		}
	}
	
	/**
	 * Create TableModel for single user stats
	 * @param username
	 * @return singleUserStat
	 */
	public TableModel getTableOneName(String username){
		getSinglePlayer(username);
		TableModel singleUserStat = new DefaultTableModel(rowData, colNames);
		return singleUserStat;
	}
	
	/**
	 * Create TableModel for all user stats
	 * @return allUserStat
	 */
	public TableModel getTableAllNames(){
		TableModel allUserStat;
		
		if(isVisible){
			rowData.clear();
		}
		
		for(int playerLoop = 0; playerLoop < players.size(); playerLoop++){
			Vector<String> getPlayerStats = new Vector<String>();
				getPlayerStats.add(players.get(playerLoop).getUsername());
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().score));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().rank));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getGame()));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getWin()));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getAttack()));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getMeal()));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getHPLost()));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getManaUsed()));
				getPlayerStats.add(String.valueOf(players.get(playerLoop).getPlayerData().getFoodUsed()));
				rowData.add(getPlayerStats);
		}
		
		isVisible = true;
		allUserStat = new DefaultTableModel(rowData, colNames);
		
		return allUserStat;
	}
}

/**
 * Double attack dice roll frame
 * @author Jacob Loden
 */
@SuppressWarnings("serial")
class DoubleAtk extends JFrame{
	private final int WIDTH = 700;
	private final int HEIGHT = 500;
	private boolean rolled;
	
	JPanel mainPanel = new JPanel(new BorderLayout(5,5));
	JPanel topPanel = new JPanel(new BorderLayout(5,5));
	JPanel centerPanel = new JPanel(new GridLayout(2,4,5,5));
	JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel die1 = new JPanel(new BorderLayout(2,2));
	JPanel die2 = new JPanel(new BorderLayout(2,2));
	JPanel die3 = new JPanel(new BorderLayout(2,2));
	JPanel die4 = new JPanel(new BorderLayout(2,2));
	JPanel die5 = new JPanel(new BorderLayout(2,2));
	JPanel die6 = new JPanel(new BorderLayout(2,2));
	JPanel die7 = new JPanel(new BorderLayout(2,2));
	JPanel die8 = new JPanel(new BorderLayout(2,2));
	
	JLabel dblLabel = new JLabel("Double Attack!");
	JButton stopButton = new JButton("Stop Roll");
	JButton closeButton = new JButton("Close");
	
	Font large = new Font("Trubuchet MS", Font.BOLD, 24);
	Font button = new Font("Trebuchet MS", Font.BOLD, 16);

	int[] roll;
	
	/**
	 * Constructor
	 */
	public DoubleAtk(){
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(!rolled)
					JOptionPane.showMessageDialog(DoubleAtk.this, "Your roll will be determined for you");
				
				die1.removeAll();
				die2.removeAll();
				die3.removeAll();
				die4.removeAll();
				die5.removeAll();
				die6.removeAll();
				die7.removeAll();
				die8.removeAll();
				
				disposeFrame();
			}
		});
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Slice And Dice");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png")));
		//rolled = false;
		setFonts();
		addComponents();
	}
	
	/**
	 * Add the components to parent container
	 * Add action listeners to buttons
	 */
	private void addComponents(){
		stopButton.addActionListener(new StopButton());
		closeButton.addActionListener(new CloseButton());
		
		topPanel.add(dblLabel, BorderLayout.WEST);
		topPanel.add(stopButton, BorderLayout.EAST);
		
		centerPanel.add(die1);
		centerPanel.add(die2);
		centerPanel.add(die3);
		centerPanel.add(die4);
		centerPanel.add(die5);
		centerPanel.add(die6);
		centerPanel.add(die7);
		centerPanel.add(die8);
		
		bottomPanel.add(closeButton);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		this.add(mainPanel);
		
	}
	
	/**
	 * Set component fonts and colors
	 */
	private void setFonts(){
		topPanel.setBorder(BorderFactory.createTitledBorder(""));
		centerPanel.setBorder(BorderFactory.createTitledBorder(""));
		centerPanel.setBackground(Color.red);
		
		die1.setBackground(Color.red);
		die2.setBackground(Color.red);
		die3.setBackground(Color.red);
		die4.setBackground(Color.red);
		die5.setBackground(Color.red);
		die6.setBackground(Color.red);
		die7.setBackground(Color.red);
		die8.setBackground(Color.red);
		
		bottomPanel.setBorder(BorderFactory.createTitledBorder(""));
		dblLabel.setFont(large);
		dblLabel.setForeground(Color.red);
		
		stopButton.setFont(button);
		closeButton.setFont(button);
	}
	
	/**
	 * Pack and show frame
	 */
	private void packAndShow(){
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Pack and validate frame
	 */
	private void packAndValidate(){
		this.pack();
		this.validate();
	}
	
	/**
	 * Dispose frame
	 */
	private void disposeFrame(){
		DoubleAtk.this.dispose();
	}
	
	/**
	 * Get parent class
	 */
	private JFrame getParentClass(){
		return this;
	}
	
	/**
	 * Add dice roll animations to panel
	 */
	private void addGifs(){
		die1.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die2.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die3.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die4.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die5.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die6.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die7.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
		die8.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/sm_dice_roll_1.gif"))), BorderLayout.CENTER);
	}
	
	/**
	 * Add the game turn roll to the panel
	 * @param diceRoll
	 */
	private void rollResult(){		
		die1.removeAll();
		die2.removeAll();
		die3.removeAll();
		die4.removeAll();
		die5.removeAll();
		die6.removeAll();
		die7.removeAll();
		die8.removeAll();
		
		die1.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[0] + "_85px.png"))), BorderLayout.CENTER);
		die2.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[1] + "_85px.png"))), BorderLayout.CENTER);
		die3.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[2] + "_85px.png"))), BorderLayout.CENTER);
		die4.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[3] + "_85px.png"))), BorderLayout.CENTER);
		die5.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[4] + "_85px.png"))), BorderLayout.CENTER);
		die6.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[5] + "_85px.png"))), BorderLayout.CENTER);
		die7.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[6] + "_85px.png"))), BorderLayout.CENTER);
		die8.add(new JLabel(new ImageIcon(getClass().getResource("/sliceAndDice/game_resources/die_land_" + roll[7] + "_85px.png"))), BorderLayout.CENTER);
		
	}
	
	/**
	 * Show the Double Attack frame
	 * @param newRoll
	 */
	public void showDblAtk(int[] newRoll){
		rolled = false;
		roll = newRoll;
		addGifs();
		packAndShow();
	}
	
	/**
	 * Action listener classes
	 * @author Jacob Loden
	 *
	 */
	public class StopButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			rollResult();
			rolled = true;
			packAndValidate();
		}
	}
	
	public class CloseButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(rolled){
				die1.removeAll();
				die2.removeAll();
				die3.removeAll();
				die4.removeAll();
				die5.removeAll();
				die6.removeAll();
				die7.removeAll();
				die8.removeAll();
				disposeFrame();
			}else{
				JOptionPane.showMessageDialog(getParentClass(), "You must press 'Stop Roll' first");
			}
		}
	}
}

/**
 * Choose special attack frame
 * @author Jacob Loden
 *
 */
//TODO finish the choose attack class
@SuppressWarnings("serial")
class ChooseAttack extends JFrame{
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	
	private int manaAmt;
	
	JPanel choosePanel = new JPanel(new BorderLayout(5,5));
	JPanel topChoosePanel = new JPanel(new BorderLayout(5,5));
	JPanel bottomChoosePanel = new JPanel(new GridLayout(5,2,2,10));
	
	JLabel chooseAtk = new JLabel("Choose Your Attack!");
	
	JButton freezeButton = new JButton("Freeze");
	JButton dblAtk = new JButton("Double");
	JButton poisonButton = new JButton("Poison");
	JButton auraButton = new JButton("Aura");
	JButton chargeButton = new JButton("Charge");
	
	JLabel freezeLabel = new JLabel("Freeze Attack: ");
	JLabel dblAtkLabel = new JLabel("Double Attack: ");
	JLabel poisonLabel = new JLabel("Poison Attack: ");
	JLabel auraLabel = new JLabel("Cast Aura: ");
	JLabel chargeLabel = new JLabel("Charge Attack: ");
	
	Font largeLabelFont = new Font("Trubuchet MS", Font.BOLD, 24);
	Font labelFont = new Font("Trebuchet MS", Font.BOLD, 16);
	Font buttonFont = new Font("Trebuchet MS", Font.BOLD, 16);
	
	Move move;
	
	/**
	 * Constructor
	 */
	public ChooseAttack(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Slice And Dice");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png")));
		setFontsAndColors();
		addComponents();
		manaAmt = 0;
		move = null;
	}
	
	/**
	 * Sets the chosen move
	 * @param chosenMove
	 */
	private void setMove(Move chosenMove){
		move = chosenMove;
	}
	
	/**
	 * Returns the chosen move
	 * @return chosen move
	 */
	public Move getMove(){
		return move;
	}
	
	/**
	 * Add the components to parent container
	 * Add action listeners to buttons
	 */
	private void addComponents(){
		topChoosePanel.add(chooseAtk, BorderLayout.CENTER);
		
		freezeButton.addActionListener(new FreezeButton());
		dblAtk.addActionListener(new DoubleAtkButton());
		poisonButton.addActionListener(new PoisonButton());
		auraButton.addActionListener(new AuraButton());
		chargeButton.addActionListener(new ChargeButton());
		
		bottomChoosePanel.add(freezeLabel);
		bottomChoosePanel.add(freezeButton);
		bottomChoosePanel.add(dblAtkLabel);
		bottomChoosePanel.add(dblAtk);
		bottomChoosePanel.add(poisonLabel);
		bottomChoosePanel.add(poisonButton);
		bottomChoosePanel.add(auraLabel);
		bottomChoosePanel.add(auraButton);
		bottomChoosePanel.add(chargeLabel);
		bottomChoosePanel.add(chargeButton);
		
		choosePanel.add(topChoosePanel, BorderLayout.NORTH);
		choosePanel.add(bottomChoosePanel, BorderLayout.CENTER);
		
		this.add(choosePanel);
	}
	
	/**
	 * Set component fonts
	 */
	private void setFontsAndColors(){
		
		bottomChoosePanel.setBorder(BorderFactory.createTitledBorder(""));
		topChoosePanel.setBorder(BorderFactory.createTitledBorder(""));
		
		chooseAtk.setFont(largeLabelFont);
		chooseAtk.setForeground(Color.red);
		
		freezeButton.setFont(buttonFont);
		freezeButton.setForeground(Color.red);
		freezeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		dblAtk.setFont(buttonFont);
		dblAtk.setForeground(Color.red);
		dblAtk.setAlignmentX(Component.RIGHT_ALIGNMENT);
		poisonButton.setFont(buttonFont);
		poisonButton.setForeground(Color.red);
		poisonButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		auraButton.setFont(buttonFont);
		auraButton.setForeground(Color.red);
		auraButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		chargeButton.setFont(buttonFont);
		chargeButton.setForeground(Color.red);
		chargeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		freezeLabel.setFont(labelFont);
		freezeLabel.setForeground(Color.red);
		dblAtkLabel.setFont(labelFont);
		dblAtkLabel.setForeground(Color.red);
		poisonLabel.setFont(labelFont);
		poisonLabel.setForeground(Color.red);
		auraLabel.setFont(labelFont);
		auraLabel.setForeground(Color.red);
		chargeLabel.setFont(labelFont);
		chargeLabel.setForeground(Color.red);
	}
	
	/**
	 * Pack and show frame
	 */
	private void packAndShow(){
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Dispose frame
	 */
	private void disposeFrame(){
		this.dispose();
	}
	
	/**
	 * Get parent class
	 */
	private JFrame getParentClass(){
		return this;
	}
	
	/**
	 * Show the choose move frame
	 * @param mana
	 */
	public void showChooseMove(int mana){
		move = null;
		manaAmt = mana;
		packAndShow();
	}
	
	/**
	 * Action listener classes
	 * @author Jacob Loden
	 *
	 */
	public class FreezeButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(manaAmt < 6){
				JOptionPane.showMessageDialog(getParentClass(), "You don't have enough mana for this attack");
			}else{
				setMove(Move.FREEZE);
				disposeFrame();
			}
		}
	}
	
	public class DoubleAtkButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(manaAmt < 10){
				JOptionPane.showMessageDialog(getParentClass(), "You don't have enough mana for this attack");
			}else{
				setMove(Move.DOUBLEATK);
				disposeFrame();
			}
		}
	}
	
	public class PoisonButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(manaAmt < 8){
				JOptionPane.showMessageDialog(getParentClass(), "You don't have enough mana for this attack");
			}else{
				setMove(Move.POISON);
				disposeFrame();
			}
		}
	}
	
	public class AuraButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			if(manaAmt < 10){
				JOptionPane.showMessageDialog(getParentClass(), "You don't have enough mana for this attack");
			}else{
				setMove(Move.AURA);
				disposeFrame();
			}
		}
	}
	
	public class ChargeButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			setMove(Move.CHARGE);
			disposeFrame();
		}
	}
}

/**
 * Player select class.  Determines who goes first when new game is started
 * @author Jacob
 *
 */
@SuppressWarnings("serial")
class ChooseFirst extends JFrame{
	private int plyr1Roll;
	private int plyr2Roll;
	private JLabel playerOne;
	private JLabel playerTwo;
	private JPanel choosePlayerPane;
	Boolean winner = false;
	Boolean beenShown = false;
	
	Font labelFont = new Font("Trebuchet MS", Font.BOLD, 16);
	Font smallLabelFont = new Font("Trebuchet MS", Font.BOLD, 12);
	Font verySmallLabelFont = new Font("Trebuchet MS", Font.BOLD, 10);
	Font smallButtonFont = new Font("Trebuchet MS", Font.BOLD, 16);
	Font largeLabelFont = new Font("Trubuchet MS", Font.BOLD, 24);
	
	/**
	 * Constructor
	 */
	public ChooseFirst(){
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				if(!winner){
					JOptionPane.showMessageDialog(getFrame(), "The first name entered will be 'Player 1' by default");
					SliceAndDiceUI.setPlayer(1);
				}else if(plyr1Roll > plyr2Roll){
					SliceAndDiceUI.setPlayer(1);
				}else{
					SliceAndDiceUI.setPlayer(0);
				}
				disposeFrame();
			}
		});
		
		this.setTitle("Slice And Dice");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sliceAndDice/game_resources/dieIcon.png")));
		this.setPreferredSize(new Dimension(600, 450));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
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
	 * Get container frame
	 * @return this
	 */
	private JFrame getFrame(){
		return this;
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
	
	/**
	 * Pack and validate frame
	 */
	private void packAndValidate(){
		this.setContentPane(choosePlayerPane);
		this.pack();
		this.validate();
	}
	
	/**
	 * Pack and show frame
	 */
	private void packAndShow(){
		this.setContentPane(choosePlayerPane);
		this.pack();
		this.setVisible(true);
		beenShown = true;
	}
	
	/**
	 * Dispose frame
	 */
	private void disposeFrame(){
		this.dispose();
	}
	
	/**
	 * Show GUI
	 */
	public void showPlayerSelect(){
		if(beenShown){
			choosePlayerPane.removeAll();
			
		}
			
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
						plyr1Roll = getRand();
						plyr2Roll = getRand();
						
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
					if(!winner){
						JOptionPane.showMessageDialog(getFrame(), "You must press stop first!");
						return;
					}
					disposeFrame();
				}
			});
			
			/*
			 * show frame
			 */
			packAndShow();
			
	}
}
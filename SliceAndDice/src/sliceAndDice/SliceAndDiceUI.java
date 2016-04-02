package sliceAndDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class SliceAndDiceUI {
	private final int MAX_HEIGHT = 500;
	private final int MAX_WIDTH = 1000;
	private JSplitPane splitPane;
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
* Game UI
*/
		public void showUI(){

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
				 * Get image
				 * Get image and add to panel
				 */
				BufferedImage modePanelImage = null;
				try{
					modePanelImage = ImageIO.read(getClass().getResourceAsStream("/sliceAndDice/game_resources/die_pic.jpg"));
				}catch(IOException e){}
				ImageIcon addImage = new ImageIcon(modePanelImage);
				JLabel imageLabel = new JLabel(addImage);
				
				JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
					imagePanel.add(imageLabel, BorderLayout.CENTER);
					
				/*
				 * Button fonts
				 */
				Font bigButtonFont = new Font("Trebuchet MS", Font.BOLD, 32);
				Font labelFont = new Font("Trebuchet MS", Font.BOLD, 16);
				Font smallButtonFont = new Font("Trebuchet MS", Font.BOLD, 16);

/*
 * Mode select panel components
 */
			/*
			 * Create mode select panels
			 */
			JPanel modePanel = new JPanel(new BorderLayout(5, 5));
				modePanel.setBorder(BorderFactory.createTitledBorder(null, "Select Mode", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			
			JPanel modePanelButtons = new JPanel(new GridLayout(4, 1, 2, 5));
			
				/*
				 * Mode select buttons
				 */
				final JButton newGameButton = new JButton("New Game");
					newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					newGameButton.setFont(bigButtonFont);
					newGameButton.setForeground(Color.red);
					newGameButton.setToolTipText("Start new game");
				final JButton loadGameButton = new JButton("Load Game");
					loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					loadGameButton.setFont(bigButtonFont);
					loadGameButton.setForeground(Color.red);
					loadGameButton.setToolTipText("Continue previous game");
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
				modePanelButtons.add(loadGameButton);
				modePanelButtons.add(playerStatusButton);
				modePanelButtons.add(abtGameButton);

/*
 * New game panel components
 */
			/*
			 * Create new game panels
			 */
			JPanel newGamePanel = new JPanel(new BorderLayout(5,5));
				newGamePanel.setBorder(BorderFactory.createTitledBorder(null, "New Game", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			
			JPanel newGameOptionsButtons = new JPanel(new GridLayout(7, 2, 2, 5));
			
			JPanel newOnePlayerGameUser = new JPanel(new GridLayout(7, 2, 2, 5));
			
			JPanel newTwoPlayerGameUser = new JPanel(new GridLayout(7, 2, 2, 5));
			
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
				for(int i = 2; i<12; i++){
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
				for(int i = 5; i<13; i++){
					newTwoPlayerGameUser.add(new JPanel());
				}
				newTwoPlayerGameUser.add(newTwoPlayerUserExit);
				newTwoPlayerGameUser.add(newTwoPlayerUserStart);
				
/*
 * Load game panel components
 */
			
			/*
			 * Create load game panels
			 */
			JPanel loadGamePanel = new JPanel(new BorderLayout(5,5));
				loadGamePanel.setBorder(BorderFactory.createTitledBorder(null, "Load Game", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
				
			JPanel loadGameOptionsPanel = new JPanel(new GridLayout(7, 2, 2, 5));
			
			/*
			 * Load game buttons
			 */
			final JLabel loadUserName = new JLabel("Enter Username:");
				loadUserName.setAlignmentX(Component.LEFT_ALIGNMENT);
				loadUserName.setFont(labelFont);
				loadUserName.setForeground(Color.red);
				
			final JTextField loadUserNameText = new JTextField();
				loadUserNameText.setAlignmentX(Component.RIGHT_ALIGNMENT);
				
			final JButton loadUserNameFind = new JButton("Find");
				loadUserNameFind.setAlignmentX(Component.CENTER_ALIGNMENT);
				loadUserNameFind.setFont(smallButtonFont);
				loadUserNameFind.setForeground(Color.red);
				loadUserNameFind.setToolTipText("Find saved game");
				
			final JButton loadUserExit = new JButton("Back");
				loadUserExit.setAlignmentX(Component.CENTER_ALIGNMENT);
				loadUserExit.setFont(smallButtonFont);
				loadUserExit.setForeground(Color.red);
				loadUserExit.setToolTipText("Go back to previous menu");
				
				/*
				 * add buttons to panel
				 */
				loadGameOptionsPanel.add(loadUserName);
				loadGameOptionsPanel.add(loadUserNameText);
				for(int i = 2; i<12; i++){
					loadGameOptionsPanel.add(new JPanel());
				}
				loadGameOptionsPanel.add(loadUserExit);
				loadGameOptionsPanel.add(loadUserNameFind);

/*
 * Player stats panel components
 */
			/*
			 * Stats panels
			 */
			JPanel statsPanel = new JPanel(new BorderLayout(5, 5));
				statsPanel.setBorder(BorderFactory.createTitledBorder(null, "Player Stats", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
				
			JPanel statPanelOptionsButtons = new JPanel(new GridLayout(7, 2, 2, 5));
			
			JPanel statPanelSingleUserButtons = new JPanel(new GridLayout(7, 2, 2, 5));
			
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
				for(int i = 2; i<13; i++){
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
				for(int i = 2; i<12; i++){
					statPanelSingleUserButtons.add(new JPanel());
				}
				statPanelSingleUserButtons.add(singleStatExit);
				statPanelSingleUserButtons.add(statFind);
				
/*
 * about game panel components
 * 
 * Probably going to open a text file when button is pressed, but heres the space just in case
 */
	
			final JPanel gamePlayPanel = new JPanel(new BorderLayout(5, 5));
			
				
/*
 * listeners
 */
	
			newGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					newGamePanel.removeAll();
					gameFrame.remove(modePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGameOptionsButtons, imagePanel);
					newGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					gameFrame.validate();
				}
			});
			
			onePlayer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newOnePlayerGameUser, imagePanel);
					newGamePanel.add(splitPane);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					gameFrame.validate();
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
					gameFrame.validate();
				}
			});
			
			newOnePlayerUserStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add one player game start stuff
					JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			newOnePlayerUserExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGameOptionsButtons, imagePanel);
					newGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					gameFrame.validate();
				}
			});
			
			newTwoPlayerUserStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add two player game start stuff
					JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			newTwoPlayerUserExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					newGamePanel.removeAll();
					gameFrame.remove(newGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newGameOptionsButtons, imagePanel);
					newGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(newGamePanel);
					gameFrame.pack();
					gameFrame.validate();
				}
			});
			
			loadGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					loadGamePanel.removeAll();
					gameFrame.remove(modePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, loadGameOptionsPanel, imagePanel);
					loadGamePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(loadGamePanel);
					gameFrame.pack();
					gameFrame.validate();
				}
			});
			
			loadUserNameFind.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add find player game stuff
					JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			loadUserExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					modePanel.removeAll();
					gameFrame.remove(loadGamePanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modePanelButtons, imagePanel);
					modePanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(modePanel);
					gameFrame.pack();
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
					gameFrame.validate();
				}
			});
			
			singleUserStats.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add get user stats stuff
					//JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			statFind.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					// TODO add get user stats stuff
					JOptionPane.showMessageDialog(gameFrame, "Need back end control function for this");
				}
			});
			
			singleStatExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					statsPanel.removeAll();
					gameFrame.remove(statsPanel);
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statPanelOptionsButtons, imagePanel);
					statsPanel.add(splitPane, BorderLayout.CENTER);
					gameFrame.setContentPane(statsPanel);
					gameFrame.pack();
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
					gameFrame.validate();
				}
			});
			
			abtGameButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
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
					gameFrame.validate();
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
				
		}
		
		/**
		 * Entry main.  Deploys UI in new thread with selected LAF
		 * @param args
		 */
		public static void main(String[] args){
			String lafClassName = getLookAndFeelClassName("Nimbus");
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

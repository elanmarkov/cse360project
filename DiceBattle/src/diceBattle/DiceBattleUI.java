package diceBattle;

import java.awt.*;
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

public class DiceBattleUI {
	private final int MAX_HEIGHT = 500;
	private final int MAX_WIDTH = 1000;
	
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
			final JFrame gameFrame = new JFrame("Dice Battle!");
				gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					gameFrame.addWindowListener(new WindowAdapter(){
							public void windowClosing(WindowEvent e){
								
								// stuff needed to be done before closing game,
								// like write stats to database
								
								e.getWindow().dispose();
								System.exit(0);
							}
					});
				gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/diceBattle/game_resources/dieIcon.png")));
				gameFrame.setMinimumSize(new Dimension(MAX_WIDTH , MAX_HEIGHT));
				
				/*
				 * Get image
				 * Get image and add to panel
				 */
				BufferedImage modePanelImage = null;
				try{
					modePanelImage = ImageIO.read(getClass().getResourceAsStream("/diceBattle/game_resources/die_pic.jpg"));
				}catch(IOException e){}
				ImageIcon addImage = new ImageIcon(modePanelImage);
				JLabel imageLabel = new JLabel(addImage);
				
				JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
					imagePanel.add(imageLabel, BorderLayout.CENTER);
					imagePanel.add(imageLabel, BorderLayout.CENTER);
					
				/*
				 * Button fonts
				 */
				Font buttonFont = new Font("Trebuchet MS", Font.BOLD, 32);
				
				JSplitPane splitPane;
/*
 * Mode select panel components
 */
			/*
			 * Create mode select panels
			 */
			JPanel modePanel = new JPanel(new BorderLayout(5, 5));
				modePanel.setBorder(BorderFactory.createTitledBorder(null, "Select Mode", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			
//			JSplitPane modePane;// = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			JPanel modePanelButtons = new JPanel(new GridLayout(4, 1, 5, 10));
				
				/*
				 * Mode select buttons
				 */
				final JButton newGameButton = new JButton("New Game");
					newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					newGameButton.setFont(buttonFont);
					newGameButton.setForeground(Color.red);
					newGameButton.setToolTipText("Start new game");
				final JButton loadGameButton = new JButton("Load Game");
					loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					loadGameButton.setFont(buttonFont);
					loadGameButton.setForeground(Color.red);
					loadGameButton.setToolTipText("Continue previous game");
				final JButton playerStatusButton = new JButton("Player Stats");
					playerStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					playerStatusButton.setFont(buttonFont);
					playerStatusButton.setForeground(Color.red);
					playerStatusButton.setToolTipText("View player stats. Requires player username");
				final JButton abtGameButton = new JButton("About Game");
					abtGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					abtGameButton.setFont(buttonFont);
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
			
			JPanel newGameOptionsButtons = new JPanel(new GridLayout(4, 1, 5, 10));
			
			/*
			 * Player select buttons
			 */
			final JButton onePlayer = new JButton("Single Player");
				onePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
				onePlayer.setFont(buttonFont);
				onePlayer.setForeground(Color.red);
				onePlayer.setToolTipText("New one player game");
			final JButton twoPlayer = new JButton("Dual");
				twoPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
				twoPlayer.setFont(buttonFont);
				twoPlayer.setForeground(Color.red);
				twoPlayer.setToolTipText("New two player game");			
			
				newGameOptionsButtons.add(onePlayer);
				newGameOptionsButtons.add(twoPlayer);
				
/*
 * Load game panel components
 */
			
			/*
			 * Create load game panels
			 */
			JPanel loadGamePanel = new JPanel(new BorderLayout(5,5));
				loadGamePanel.setBorder(BorderFactory.createTitledBorder(null, "Load Game", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
				
			JPanel loadGameOptionsPanel = new JPanel(new GridLayout(6, 2, 5, 10));
			
			/*
			 * Load game buttons
			 */
			final JLabel loadUserName = new JLabel("Enter User Name:");
				//loadUserName.setFont(buttonFont);
				loadUserName.setAlignmentX(Component.LEFT_ALIGNMENT);
				loadUserName.setFont(buttonFont);
				loadUserName.setForeground(Color.red);
				loadUserName.setToolTipText("Enter username to find last incomplete game");
				
			final JTextField loadUserNameText = new JTextField();
				loadGameOptionsPanel.add(loadUserName, 0);
				loadGameOptionsPanel.add(loadUserNameText, 1);
				
			//need placeholder jpanels to fill space in loadGameOptionsPanel
				
				/*
				 * Add left and right panels to splitpane
				 * Set content pane and pack
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
			SwingUtilities.invokeLater(new Runnable(){	//swing is not thread safe, start new thread for UI
				public void run(){
					try{
						UIManager.setLookAndFeel(lafClassName);
					}catch(ClassNotFoundException ex){
						Logger.getLogger(DiceBattleUI.class.getName()).log(Level.SEVERE, null, ex);
					}catch(InstantiationException ex){
						Logger.getLogger(DiceBattleUI.class.getName()).log(Level.SEVERE, null, ex);
					}catch(IllegalAccessException ex){
						Logger.getLogger(DiceBattleUI.class.getName()).log(Level.SEVERE, null, ex);
					}catch(UnsupportedLookAndFeelException ex){
						Logger.getLogger(DiceBattleUI.class.getName()).log(Level.SEVERE, null, ex);
					}
					new DiceBattleUI().showUI();
				}
			});
		}
}

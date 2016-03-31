package diceBattle;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class DiceBattleUI {
	private final int MAX_HEIGHT = 400;
	private final int MAX_WIDTH = 600;
	
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
							}
					});
				gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/diceBattle/game_resources/dieIcon.png")));
				gameFrame.setMinimumSize(new Dimension(MAX_WIDTH , MAX_HEIGHT));
	
			/*
			 * Mode select panel
			 */
			JPanel modePanel = new JPanel();
				modePanel.setLayout(new GridLayout(4, 1, 5, 10));
				modePanel.setBorder(BorderFactory.createTitledBorder(null, "Select Mode", TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION));
				
				/*
				 * Mode select button fonts
				 */
				Font buttonFont = new Font("Trebuchet MS", Font.BOLD, 36);
				
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
				modePanel.add(newGameButton);
				modePanel.add(loadGameButton);
				modePanel.add(playerStatusButton);
				modePanel.add(abtGameButton);
				
				/*
				 * Pack frame
				 */
				gameFrame.setContentPane(modePanel);
				gameFrame.pack();
				try{
					gameFrame.setLocationByPlatform(true);
				}catch(Throwable ignoreAndContinue){}
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class JBrainTester extends JBrainTetris{
	
	int totalScore;
	int gameCount;
	final int numGames = 100000;

	JBrainTester(int width, int height, Brain b) {
		super(width, height, b);
		gameCount = totalScore = 0;
	}

	@Override
	public void startGame()
	{
		super.startGame();
	}
	
	@Override
	public void stopGame()
	{
		super.stopGame();
		System.out.println("Score: " + count);
		totalScore += count;
		gameCount++;
		if(gameCount < numGames)
			startGame();
		else {
			System.out.print("Average after " + gameCount + " tests: ");
			System.out.println(totalScore*1.0/gameCount);
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Tetris 2000");
		JComponent container = (JComponent)frame.getContentPane();
		container.setLayout(new BorderLayout());
                
        // Set the metal look and feel
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName() );
        }
        catch (Exception ignored) {}
		
		// Could create a JTetris or JBrainTetris here
		final int pixels = 16;
		
		
		JTetris tetris = new JBrainTester(10*pixels+2, (24)*pixels+2, new LameBrain());
		container.add(tetris, BorderLayout.CENTER);


//		if (args.length != 0 && args[0].equals("test")) {
//			tetris.testMode = true;
//		}
		
		Container panel = tetris.createControlPanel();
		
		// Add the quit button last so it's at the bottom
		panel.add(Box.createVerticalStrut(12));
		JButton quit = new JButton("Quit");
		panel.add(quit);
		quit.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		container.add(panel, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);

		// Quit on window close
		frame.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);
		
	}
}
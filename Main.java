import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

	private final int WIDTH = 50;
	private Deque<SnakePart> snake = new ArrayDeque<>();
	private Point apple = new Point(0,0);
	private Random rand = new Random();
	
	private boolean isGrowing = false;
	private boolean gameLost = false;
	
	private int offset = 0;
	private int newDirection = 39;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake");
		Main panel = new Main();
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
                panel.onKeyPressed(e.getKeyCode());
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
                panel.onKeyPressed(e.getKeyCode());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				panel.onKeyPressed(e.getKeyCode());	
			}
		});
		frame.setContentPane(panel);
		frame.setSize(13*50, 13*50);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Main() {
		createApple();
		snake.add(new SnakePart(0, 0, 39));
		setBackground(Color.WHITE);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					repaint();
					try {
						Thread.sleep(1000/60l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
    public void createApple() {
		boolean positionAvailable;
		do {
			apple.x = rand.nextInt(12);
			apple.y = rand.nextInt(12);
			positionAvailable = true;
			for(SnakePart p : snake) {
				if(p.x == apple.x && p.y == apple.y) {
					positionAvailable = false;
					break;
				}
			}
		} while(!positionAvailable);
	}
}
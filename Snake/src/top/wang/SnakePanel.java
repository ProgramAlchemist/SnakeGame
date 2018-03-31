package top.wang;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SnakePanel extends JPanel implements KeyListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9193484532691029203L;
	// 属性
	ImageIcon up = new ImageIcon("src/image/head_up.png");
	ImageIcon down = new ImageIcon("src/image/head_down.png");
	ImageIcon right = new ImageIcon("src/image/head_right.png");
	ImageIcon left = new ImageIcon("src/image/head_left.png");
	ImageIcon food = new ImageIcon("src/image/food.png");
	ImageIcon body = new ImageIcon("src/image/body.png");
	ImageIcon background = new ImageIcon("src/image/background.png");
	int round = 1;
	int [] snakex = new int[750];
	int [] snakey = new int[750];
	int len = 3;
	Random random = new Random();
	int foodx = random.nextInt(34)*25+25;
	int foody = random.nextInt(24)*25+75;
	String direction = "R";//R,L,U,D
	Boolean isStarted = false;
	Timer timer = new Timer(100, this);
	
	//构造方法
	public SnakePanel() {
		
		this.setFocusable(true);//初始化
		this.addKeyListener(this);//监听自己的事件
		setup();
		timer.start();
	}
	
	
	
	
	public void paint(Graphics g) {
		this.setBackground(Color.black);
		background.paintIcon(this, g, 0, 0);
		g.fillRect(25, 75, 850, 600);
	
		//head
		if(direction.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if (direction.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if (direction.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if (direction.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//body
		for(int i=1; i<len; i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		g.setColor(Color.black);
		g.setFont(new Font("score", Font.BOLD, 20));
		g.drawString("Score:"+(len-3), 700, 50);
		g.setFont(new Font("score", Font.BOLD, 20));
		g.drawString("Round:"+round, 100, 50);
		//提示
		if (!isStarted&&!isDead()) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Press Space to start/Pause", 300, 300);
		}
		if (isDead()) {
			isStarted = false;
			g.setColor(Color.white);
			g.setFont(new Font("arial2", Font.BOLD, 20));
			g.drawString("You are Dead Press Space to restart", 300, 300);
		}
		food.paintIcon(this, g, foodx, foody);
		
	}
	public void foodSet() {
		foodx = random.nextInt(34)*25+25;
		foody = random.nextInt(24)*25+75;
	}
	public void setup() {
		len = 3;
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		int KeyCode = e.getKeyCode();
		if (KeyCode == KeyEvent.VK_SPACE) {
			isStarted = !isStarted;
			if(isDead()) {
				setup();
				direction = "R";
				round++;
			}
		}
		if (KeyCode == KeyEvent.VK_DOWN) {
			direction = "D";
		}
		if (KeyCode == KeyEvent.VK_UP) {
			direction = "U";
		}
		if (KeyCode == KeyEvent.VK_RIGHT) {
			direction = "R";
		}
		if (KeyCode == KeyEvent.VK_LEFT) {
			direction = "L";
		}
		
		repaint();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 计时器
		timer.start();
		
		//移动数据
		if (isStarted) {
			if (foodx == snakex[0] && foody == snakey[0]) {
				len++;
				foodSet();
			}
			for (int i =len; i>0; i--) {
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			if(direction.equals("R")) {
				snakex[0] += 25;
				if(snakex[0]>850)snakex[0]=25;
			}else if (direction.equals("L")) {
				snakex[0] -= 25;
				if(snakex[0]<25)snakex[0]=850;
			}else if (direction.equals("U")) {
				snakey[0] -= 25;
				if(snakey[0]<75)snakey[0]=650;
			}else if (direction.equals("D")) {
				snakey[0] += 25;
				if(snakey[0]>650)snakey[0]=75;
			}
			
			
		}
		repaint();
	}
	public boolean isDead(){
		for(int i = 1; i<len; i++) {
			if (snakex[0]==snakex[i]&&snakey[0]==snakey[i])return true;
		
		}
		return false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
	}
	
}

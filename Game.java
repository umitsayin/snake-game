package trailGame;

import java.util.ArrayList; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener,KeyListener {

	private static final KeyListener JFrame = null;
	private int positionX;
	private int positionY;
	private int appleX;
	private int appleY;
	private int velocityX;
	private int velocityY;
	private int gridSize;
	private int tileCount;
	private int tailSize;
	private int beforeAppleX;
	private int beforeAppleY;
	private ArrayList<TrailPosition> trail;
	private Timer timer;


	public Game() {
		super();
		this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
		this.trail = new ArrayList<>();
		this.timer = new Timer(75 ,this);
		this.timer.start();
		this.init();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.update();
		repaint();
	}

	public void init() {
		
		this.positionX = this.positionY = 10;
		this.velocityX = this.velocityY = 0;
		this.gridSize = this.tileCount = 20;
		this.tailSize = 5;
		this.appleX = this.appleY = 5;
		this.trail = new ArrayList<>();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 420, 440);
		
		
		g.setColor(Color.white);
		g.drawString("Point:"+(this.tailSize-5)*10, 340, 380);
		
		
		g.setColor(Color.red);
		g.fillRect(this.appleX * this.gridSize, this.appleY * this.gridSize, this.gridSize - 5, this.gridSize - 5);
		
		
		g.setColor(Color.magenta);
		for (TrailPosition trailCount : this.trail) {
			g.fillRect(trailCount.positionX * this.gridSize, trailCount.positionY * this.gridSize, this.gridSize - 5,
					this.gridSize - 5);
		}

	}

	public void clearGame() {
		
			this.init();
	}


	public void update() {
		
		
		this.positionX += this.velocityX;
		this.positionY += this.velocityY;

		
		if (this.positionX < 0) {
			this.positionX = this.tileCount - 1;
		} else if (this.positionY < 0) {
			this.positionY = this.tileCount - 1;
		} else if (this.positionX > this.tileCount - 1) {
			this.positionX = 0;
		} else if (this.positionY > this.tileCount - 1) {
			this.positionY = 0;
		}

		
		
		if(this.trail.size() >3 ) {
			for (TrailPosition t : this.trail) {
				if(t.positionX == this.positionX && t.positionY == this.positionY) {
					this.clearGame();
				}
			}
		}
		
		
		this.trail.add(0, new TrailPosition(this.positionX, this.positionY));

		
		while (this.trail.size() > this.tailSize) {
			this.trail.remove(this.trail.size() - 1);
		}
		
		
		if(this.positionX == this.appleX && this.appleY == this.positionY) {
			this.tailSize++;
			this.appleX =  (int) Math.floor(Math.random()*this.tileCount);
			this.appleY =  (int) Math.floor(Math.random()*this.tileCount);
			boolean control=true;
			
			 while(control){
				 for(TrailPosition t:this.trail) {
					 if(t.positionX != this.appleX && t.positionY != this.appleY){
	                        control = false;      
	                    }else{
	                        this.appleX = (int) Math.floor(Math.random()*this.tileCount);
	                        this.appleY = (int) Math.floor(Math.random()*this.tileCount);
	                    }
				 }
	               
 
			 }       
		}
	}

	public void keyPressed(KeyEvent e) {
		
		
		if (e.getKeyCode() == 37 && this.velocityX != 1) { 
			this.velocityX =-1;
			this.velocityY = 0;
		}
		if (e.getKeyCode() == 38 && this.velocityY != 1) { 
			this.velocityX = 0;
			this.velocityY = -1;
		}
		if (e.getKeyCode() == 39 && this.velocityX != -1) { 
			this.velocityX = 1;
			this.velocityY = 0;
		}
		if (e.getKeyCode() == 40 && this.velocityY != -1) { 
			this.velocityX = 0;
			this.velocityY = 1;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}

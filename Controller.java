// Jordan Lyle, 10/28/2022, Controller.java for Assignment 5

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller extends JPanel implements ActionListener, MouseListener, KeyListener {
	boolean keyLeft;
	boolean keyRight;
	boolean keySpace;
	View view;
	Model model;
	boolean editingMode = false;
	boolean goombaMode;
	int scrollContPos;
	boolean isMoving = false;

	Controller(Model m) 
	{
		model = m;
		scrollContPos = model.mario.x - 2*model.mario.w;
		goombaMode = false;
	}

	void setView(View v) 
	{
		view = v;
	}

	void setModel(Model m) 
	{
		model = m;
	}

	public void mousePressed(MouseEvent e) 
	{
		if (e.getY() < 100) 
		{
			System.out.println("break here");
		}
		int count = 0;
		if (editingMode == true && goombaMode == false)
		{
			Iterator<Sprite> counter = model.sprites.iterator();
			while (counter.hasNext()) 
			{
				Sprite s = counter.next();
				if (s.isPipe())
				{
					if ((((Pipe)s).onUserClickLocation(e.getX() + scrollContPos, e.getY())) == 10) 
					{
						//System.out.println("got here");
						count = 1;
						counter.remove();
					} 
					else if ((((Pipe)s).onUserClickLocation(e.getX() + scrollContPos, e.getY())) == 5) 
					{
						count = 1;
					}
				}
			}
			if (count == 0) 
			{
				Pipe p = new Pipe(e.getX() + scrollContPos, e.getY(), 55, 400);
				model.sprites.add(p);
			}
		}
		if (goombaMode == true && editingMode == true)
		{
			for (int i = 0; i < model.sprites.size(); i++)
			{
				Sprite s = model.sprites.get(i);
				if(s.isGoomba())
				{
					if ((((Goomba)s).onUserClickLocation(e.getX() + scrollContPos, e.getY())) == 10) 
					{
						//System.out.println("got here");
						count = 1;
						model.sprites.remove(i);
					} 
				}
			}
			if (count == 0) 
			{
				Goomba g = new Goomba(e.getX() + scrollContPos, e.getY(), 60, 60);
				model.sprites.add(g);
			}
		}
	}

	public void mouseReleased(MouseEvent e) 
	{
	}

	public void mouseEntered(MouseEvent e) 
	{
	}

	public void mouseExited(MouseEvent e) 
	{
	}

	public void mouseClicked(MouseEvent e) 
	{
	}

	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_RIGHT:
				keyRight = true;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = true;
				break;

		}

		if (((e.getKeyCode()) == (KeyEvent.VK_ESCAPE)) || ((e.getKeyCode()) == (KeyEvent.VK_Q))) 
		{
			System.exit(0);
		}

		if (e.getKeyCode() == KeyEvent.VK_S) 
		{
			model.marshal().save("map.json");
		}

		if (e.getKeyCode() == KeyEvent.VK_L) 
		{
			model.unmarshal();
			scrollContPos = model.mario.x - 2*model.mario.w;
			view.scrollPos = scrollContPos;
		}

		if (e.getKeyCode() == KeyEvent.VK_E)
		{
			if (editingMode == true)
			{
				System.out.println("Edit Mode Toggled == False");
				editingMode = false;
			}
			else
			{
				System.out.println("Edit Mode Toggled == True");
				editingMode = true;
				if (goombaMode == true && editingMode == true)
				{
					System.out.println("Goomba editor");
				}
				else if (goombaMode == false && editingMode == true)
				{
					System.out.println("Pipe editor");
				}
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_G)
		{
			goombaMode = !goombaMode;
			if (goombaMode == true && editingMode == true)
			{
				System.out.println("Goomba editor");
			}
			else if (goombaMode == false && editingMode == true)
			{
				System.out.println("Pipe editor");
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keySpace = true;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_RIGHT:
			{
				keyRight = false;
				isMoving = false;
				break;
			}   
			case KeyEvent.VK_LEFT:
			{
				keyLeft = false;
				isMoving = false;
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				keySpace = false;
				break;
			}
			case KeyEvent.VK_CONTROL:
			{
				model.sprites.add(new Fireball(model.mario.x, model.mario.y, 20, 20));
			}
		}
	}

	public void keyTyped(KeyEvent e) 
	{
	}

	void update() 
	{
		model.mario.marioMoving = isMoving;
		model.mario.setPreviousPosition();
		for (int i = 0; i < model.sprites.size(); i++)
		{
			if (model.sprites.get(i).isGoomba())
			{
				((Goomba)(model.sprites.get(i))).setPreviousPosition();
			}
		}
		if (keyRight) 
		{
			scrollContPos = model.mario.px - 2*model.mario.w;
			isMoving = true;
			model.mario.x += 4;
		}
		if (keyLeft) 
		{
			scrollContPos = model.mario.px - 2*model.mario.w;
			isMoving = true;
			model.mario.x -= 4;
		}
		if (keySpace && model.mario.numFramesInAir < 5)
		{
			//System.out.print("key space");
			model.mario.vertVelocity += -5;
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
	}
}

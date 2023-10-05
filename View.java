// Jordan Lyle, 10/28/2022, View.java for Assignment 5

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	Controller controller;
	int scrollPos;

	View(Controller c, Model m)
	{
		model = m;
		controller = c;
		c.setView(this);
		scrollPos = controller.scrollContPos;
	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		System.out.println("Successfully loaded " + filename + " image.");
		return im;

	}

	void setModel(Model m)
	{
		model = m;
	}
	
	public void paintComponent(Graphics g)
	{
		setModel(controller.model);
		scrollPos = controller.scrollContPos;
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.sprites.size(); i++)
		{
			model.sprites.get(i).draw(g,scrollPos);
		}
		g.setColor(Color.red);
		g.drawLine(0, 600, 2000, 600);
		g.fillRect(0,600,2000,600);
	}
}

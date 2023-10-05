// Jordan Lyle, 10/28/2022, Game.java file for Assignment 5

import javax.swing.JFrame;
import java.awt.Toolkit;
public class Game extends JFrame 
{
	Model model;
	View view;
	Controller controller;

	public Game() 
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A5 - Polymorphism");
		this.setSize(1000, 1000);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		controller.setModel(model);
	}

	public void run() 
	{
		while (true) {
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 50 milliseconds
			try {
				Thread.sleep(40);
			} catch (Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) 
	{
		Game g = new Game();
		g.run();
	}
}

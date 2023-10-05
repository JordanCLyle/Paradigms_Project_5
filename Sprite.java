// Jordan Lyle, 10/28/2022, Sprite.java for Assignment 5
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Sprite
{
	int x, y, w, h;
	abstract void draw(Graphics g, int scroll);
	abstract void update();

	Sprite(int horizontal, int vertical, int width, int height)
	{
		x = horizontal;
		y = vertical;
		w = width;
		h = height;
	}

	abstract Json marshal();
	abstract boolean isGoomba();
	abstract boolean isMario();
	abstract boolean isPipe();
	abstract boolean isFireball();
}
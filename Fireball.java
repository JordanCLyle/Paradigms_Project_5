// Jordan Lyle, 10/28/2022, Fireball.java for Assignment 5
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Fireball extends Sprite
{
	int px;
	int py;
	double vertVelocity;
	int bounceNumber;
	boolean directionSwitch = false;
	static BufferedImage image;

	Fireball(int horizontal,int vertical, int width, int height)
	{
		super(horizontal,vertical, width, height);
		this.x = horizontal;
		this.y = vertical;
		this.h = 37;
		this.w = 45;
		if (image == null)
		{
			image = View.loadImage("fireball.png");
			w = image.getWidth();
			h = image.getHeight();
		}
		px = horizontal;
		py = vertical;
	}

	void draw(Graphics g, int scrollPos)
	{
		g.drawImage(image, x - scrollPos, y, null);
	}

	boolean isMario()
	{
		return false;
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		return ob;
	}

	void update()
	{
		if (directionSwitch == true)
		{
			x += 10;
			vertVelocity += 1.2;
			y += 4 + vertVelocity;
		}
		else if (directionSwitch == false)
		{
			x += 10;
			vertVelocity += 1.2;
			y += -10 + vertVelocity;
		}

		if(y > 560 || ((y < py - 50) && bounceNumber == 0) || (y < 20 && bounceNumber == 0) || (y < 400 && bounceNumber != 0))
		{
			if (y > 560)
			{
				bounceNumber++;
			}
			y = 560;
			vertVelocity = 0;
			directionSwitch = !directionSwitch;
		} 
	}

	// public int onUserClickLocation(int clickX, int clickY)
	// {
	// 	{
	// 		if ((x <= clickX) && (x >= clickX - w) && (y <= clickY) && (y >= clickY - h))
	// 		{
	// 			return 10;
	// 		}
	// 		else if (((x <= clickX + w) && (x >= clickX - w)))
	// 		{
	// 			return 5;
	// 		}
	// 		else
	// 		{
	// 			return 2;
	// 		}
	// 	}
	// }

	boolean isPipe()
	{
		return false;
	}

	boolean isGoomba()
	{
		return false;
	}

	boolean isFireball()
	{
		return true;
	}

	// Json marshal()
	// {
	// 	Json ob = Json.newObject();
	// 	ob.add("x", x);
	// 	ob.add("y", y);
	// 	ob.add("w", w);
	// 	ob.add("h", h);
    //     return ob;
	// }

	// public void setPreviousPosition()
	// {
	// 	px = x;
	// 	py = y;
	// }

	
	// public void getOutOfPipeX()
	// {
	// 	x = px;
	// 	directionSwitch = !directionSwitch;
	// }

	// public void getOutOfPipeY()
	// {
	// 	y = py;
	// 	vertVelocity = 0;
	// }

	// Fireball(Json ob)
	// {
	// 	super((int)ob.getLong("x"), (int)ob.getLong("y"), (int)ob.getLong("w"), (int)ob.getLong("h"));
	// 	x = (int)ob.getLong("x");
	// 	y = (int)ob.getLong("y");
	// 	w = (int)ob.getLong("w");
	// 	h = (int)ob.getLong("h");
	// 	if (image == null)
	// 	{
	// 		image = View.loadImage("goomba.png");
	// 	}
	// }

	@Override
	public String toString()
	{
		return "Fireball (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
	}
}
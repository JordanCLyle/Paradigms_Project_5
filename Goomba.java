// Jordan Lyle, 10/28/2022, Goomba.java for Assignment 5
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Goomba extends Sprite
{
	int px;
	int py;
	int numFrames;
	boolean movingActivated = true;
	double vertVelocity;
	boolean directionSwitch = false;
	static BufferedImage image;
	static BufferedImage fireImage;

	Goomba(int horizontal,int vertical, int width, int height)
	{
		super(horizontal,vertical, width, height);
		this.x = horizontal;
		this.y = vertical;
		this.h = 37;
		this.w = 45;
		if (image == null)
		{
			image = View.loadImage("goomba.png");
			w = image.getWidth();
			h = image.getHeight();
		}
		if (fireImage == null)
		{
			fireImage = View.loadImage("goomba_fire.png");
		}
	}

	void draw(Graphics g, int scrollPos)
	{
		if (movingActivated == true)
		{
			g.drawImage(image, x - scrollPos, y, null);
		}
		if (movingActivated == false)
		{
			g.drawImage(fireImage, x - scrollPos, y, null);
		}
	}

	boolean isMario()
	{
		return false;
	}

	boolean isFireball()
	{
		return false;
	}

	void update()
	{
		if (movingActivated == false)
		{
			numFrames++;
		}
		if (directionSwitch == true && movingActivated == true)
		{
			x -= 4;
		}
		else if (movingActivated == true)
		{
			x += 4;
		}
		vertVelocity += 1.2;
		y += vertVelocity;

		if(y > 560)
		{
			vertVelocity = 0;
			y = 560;
		}
	}

	public int onUserClickLocation(int clickX, int clickY)
	{
		{
			if ((x <= clickX) && (x >= clickX - w) && (y <= clickY) && (y >= clickY - h))
			{
				return 10;
			}
			else if (((x <= clickX + w) && (x >= clickX - w)))
			{
				return 5;
			}
			else
			{
				return 2;
			}
		}
	}

	boolean isPipe()
	{
		return false;
	}

	boolean isGoomba()
	{
		return true;
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
        return ob;
	}

	public void setPreviousPosition()
	{
		px = x;
		py = y;
	}

	
	public void getOutOfPipeX()
	{
		x = px;
		directionSwitch = !directionSwitch;
	}

	public void getOutOfPipeY()
	{
		y = py;
		vertVelocity = 0;
	}

	Goomba(Json ob)
	{
		super((int)ob.getLong("x"), (int)ob.getLong("y"), (int)ob.getLong("w"), (int)ob.getLong("h"));
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		if (image == null)
		{
			image = View.loadImage("goomba.png");
		}
		if (fireImage == null)
		{
			fireImage = View.loadImage("goomba_fire.png");
		}
	}

	@Override
	public String toString()
	{
		return "Goomba (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
	}
}
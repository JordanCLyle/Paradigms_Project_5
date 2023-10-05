// Jordan Lyle, 10/28/2022, Pipe.java for Assignment 5
import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Pipe extends Sprite
{
	static BufferedImage image;

	Pipe(int horizontal,int vertical, int width, int height)
	{
		super(horizontal,vertical, width, height);
		this.x = horizontal;
		this.y = vertical;
		this.w = width;
		this.h = height;

		if (image == null)
		{
			image = View.loadImage("Pipe.png");
		}
	}

	boolean isGoomba()
	{
		return false;
	}

	boolean isMario()
	{
		return false;
	}
	
	boolean isFireball()
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
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

	void update()
	{

	}
	
	@Override
	boolean isPipe()
	{
		//System.out.println("It is pipe");
		return true;
	}

	void draw(Graphics g, int scrollPos)
	{
		g.drawImage(image, x - scrollPos, y, null);
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

	Pipe(Json ob)
	{
		super((int)ob.getLong("x"), (int)ob.getLong("y"), (int)ob.getLong("w"), (int)ob.getLong("h"));
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		if (image == null)
		{
			image = View.loadImage("Pipe.png");
		}
	}
}
// Jordan Lyle, 10/28/2022, Mario.java for Assignment 5
import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Mario extends Sprite
{
	int px;
	int py;
	int drawingCounter;
	double vertVelocity;
	int numFramesInAir;
	boolean marioMoving;
	static BufferedImage[] images;

	Mario(int horizontal,int vertical, int width, int height)
	{		
		super(horizontal, vertical, width, height);
		this.x = horizontal;
		this.y = vertical;
		drawingCounter = 0;
		numFramesInAir = 0;
		this.w = width;
		this.h = height;
		images = new BufferedImage[5];
		for (int i = 0; i < 5; i++)
		{
			if (images[i] == null)
			{
				images[i] = View.loadImage("Mario" + (i+1) + ".png");
			}

		}
	}

	boolean isMario()
	{
		return true;
	}

	void update()
	{
		numFramesInAir++;
		vertVelocity += 1.2;
		y += vertVelocity;

		if(y > 510)
		{
			vertVelocity = 0;
			y = 510;
			numFramesInAir = 0;
		}
	}

	boolean isGoomba()
	{
		return false;
	}

	boolean isPipe()
	{
		return false;
	}

	boolean isFireball()
	{
		return false;
	}

	public void setPreviousPosition()
	{
		px = x;
		py = y;
	}

	public void draw(Graphics g, int scrollPos)
	{
		if (marioMoving == true)
		{
			g.drawImage(images[drawingCounter], x - scrollPos, y, null);
			drawingCounter++;
			if (drawingCounter == 5)
			{
				drawingCounter = 0;
			}
		}
		else
		{
			g.drawImage(images[0], x - scrollPos, y, null);
		}
	}

	public void getOutOfPipeX()
	{
		x = px;
	}

	public void getOutOfPipeY()
	{
		y = py;
		vertVelocity = 0;
		numFramesInAir = 0;
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		// Mario doesn't need to be marshalled.
		return ob;
	}

	@Override
	public String toString()
	{
		return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
	}

}
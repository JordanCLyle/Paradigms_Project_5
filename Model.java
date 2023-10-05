// Jordan Lyle, 10/28/2022, Model.java for Assignment 5
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

class Model
{
    ArrayList<Sprite> sprites;
	Mario mario;
	boolean check;
	boolean checkGoomba;
	Json tmpListPipes;
	Json tmpListGoombas;

	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(100,100, 60, 95);
		sprites.add(mario);
		Json ob = Json.newObject();
		ob = Json.load("map.json");
		tmpListPipes = Json.newList();
		tmpListPipes = ob.get("pipes");
		tmpListGoombas = Json.newList();
		tmpListGoombas = ob.get("goombas");
		for (int i = 0; i < tmpListPipes.size(); i++)
		{
			//System.out.println("Ran2");
			sprites.add(new Pipe (tmpListPipes.get(i)));
			//System.out.println(sprites.get(i));
		}
		for (int i = 0; i < tmpListGoombas.size(); i++)
		{
			sprites.add(new Goomba (tmpListGoombas.get(i)));
		}
		check = false;
		checkGoomba = false;
	}

	public void update()
	{
		for (int i = 0; i < sprites.size(); i++)
		{
			if ((sprites.get(i)).isGoomba())
			{
				if ((((Goomba)sprites.get(i)).movingActivated == false) && (((Goomba)sprites.get(i)).numFrames >= 50))
				{
					sprites.remove(i);
				}
			}
			else if ((sprites.get(i).isFireball()))
			{
				if (((Fireball)sprites.get(i)).x > ((Fireball)sprites.get(i)).px + 3000)
				{
					sprites.remove(i);
				}
			}
		}
		for (int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).update();
		}
		for (int i = 0; i < sprites.size(); i++)
		{
			//System.out.println(sprites.get(i));
			if (sprites.get(i).isPipe())
			{
				//System.out.println(sprites.get(0));
				check = areColliding(mario,(sprites).get(i));
				for (int j = 0; j < sprites.size(); j++)
				{
					if (sprites.get(j).isGoomba())
					{
						checkGoomba = areColliding(sprites.get(j), sprites.get(i));
						if ((checkGoomba) && (((Goomba)(sprites.get(j))).py + ((Goomba)(sprites.get(j))).h <= ((Pipe)(sprites).get(i)).y))
						{
							//System.out.println("Goomba colliding Y");
							((Goomba)(sprites.get(j))).getOutOfPipeY();
						}
						else if (checkGoomba)
						{
							//System.out.println("Goomba colliding X");
							((Goomba)(sprites.get(j))).getOutOfPipeX();
						}
					}
				}
			}
			if ((check == true) && (sprites.get(i).isPipe()))
			{
				if ((mario.py + mario.h <= ((Pipe)(sprites).get(i)).y) || (mario.py >= ((Pipe)(sprites).get(i)).y + ((Pipe)(sprites).get(i)).h))
				{
					//System.out.println("Mario Colliding Y!");
					mario.getOutOfPipeY();
				}
				else
				{
					//System.out.println("Mario Colliding X!");
					mario.getOutOfPipeX();
				}
				//System.out.println("Colliding!");
			}
			if (sprites.get(i).isFireball())
			{
				for (int j = 0; j < sprites.size(); j++)
				{
					if(sprites.get(j).isGoomba())
					{
						checkGoomba = areColliding(sprites.get(j), sprites.get(i));
						{
							if ((checkGoomba == true) && (((Goomba)sprites.get(j)).movingActivated == true))
							{
								((Goomba)sprites.get(j)).movingActivated = false;
								sprites.remove(i);
								break;
							}
						}
					}
				}
			}
		}
	}

	boolean areColliding(Sprite q, Sprite p)
	{
		//System.out.println(q); 
		if(q.x + q.w < p.x)
		{
			//System.out.println(q.x + q.w);
			//System.out.println(p.x);
			//System.out.println("They are not colliding1");
			return false;
		}
		if(q.x > p.x + p.w)
		{
			//System.out.println(p);
			//System.out.println(q.x);
			//System.out.println(p.x + p.w);
			//System.out.println("They are not colliding2");
			return false;
		}
		if(q.y + q.h < p.y) // assumes bigger is downward
		{
			//System.out.println(q.y + q.h);
			//System.out.println(p.y);
			//System.out.println("They are not colliding3");
			return false;
		}
		if(q.y > p.y + p.h) // assumes bigger is downward
		{			
			//System.out.println(q.y);
			//System.out.println(p.y + p.h);
			//System.out.println("They are not colliding4");
			return false;
		}
		//System.out.println(q.x);
		//System.out.println(p.x);
		//System.out.println("They are colliding");
		return true;
	}

	Json marshal()
	{  
		Json ob = Json.newObject(); 
		Json tmpListPipes = Json.newList();    
        ob.add("pipes", tmpListPipes);
		Json tmpListGoombas = Json.newList();
		ob.add("goombas", tmpListGoombas);
        for (int i = 0; i < sprites.size(); i++)
		{
			//System.out.println(sprites.get(i));
			if(sprites.get(i).isPipe())
			{
				tmpListPipes.add(((Pipe)(sprites.get(i))).marshal());
			}
			if(sprites.get(i).isGoomba())
			{
				tmpListGoombas.add(((Goomba)sprites.get(i)).marshal());
			}
		}
		//System.out.println("marshal");
        return ob;
	}

	void unmarshal()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(100,100, 60, 95);
		sprites.add(mario);
		Json ob = Json.newObject();
		ob = Json.load("map.json");
		tmpListPipes = Json.newList();
		tmpListPipes = ob.get("pipes");
		tmpListGoombas = Json.newList();
		tmpListGoombas = ob.get("goombas");
		for (int i = 0; i < tmpListPipes.size(); i++)
		{
			//System.out.println("Ran2");
			sprites.add(new Pipe (tmpListPipes.get(i)));
			//System.out.println(sprites.get(i));
		}
		for (int i = 0; i < tmpListGoombas.size(); i++)
		{
			sprites.add(new Goomba (tmpListGoombas.get(i)));
		}
		mario.x = 100;
		mario.y = 100;
	}
}
package pwntron;
import battlecode.common.*;

public class Soldier {

	public static void doSoldierStuff(RobotController myRC)
	{
		while(true)
		{
			try
			{
				boolean movement = false;
				boolean attack = false;
				while (myRC.isMovementActive() || myRC.getFlux() < 2) // while we cannot move
				{
					myRC.yield();
				}

				Robot nearBots[] = myRC.senseNearbyGameObjects(Robot.class);
				for(Robot r : nearBots)
				{
					if(r.getTeam() != myRC.getTeam() && !myRC.isAttackActive()) // if opposing bot and we can attack
					{
						RobotInfo ri = myRC.senseRobotInfo(r);
						if(myRC.canAttackSquare(ri.location))
						{
							myRC.attackSquare(ri.location, r.getRobotLevel());
							attack = true;
							break;
						}
					}
				}
				if(!attack) // we didn't find an opponent straight ahead turn around
				{

				}

				if (myRC.canMove(myRC.getDirection())) 
				{
					if(!movement)
					{
						myRC.moveForward();
					}
				}
				else 
				{
					if(!movement)
					{
						myRC.setDirection(myRC.getDirection().rotateRight());
					}
				}
				myRC.yield();
			}
			catch (Exception e)
			{
				System.out.println("caught exception:");
				e.printStackTrace();
			}

		}		
	}



}

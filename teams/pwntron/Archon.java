package pwntron;

import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Archon {
//lololSTARCRAFT
	public static void doArchonStuff(RobotController myRC)
	{
		while(true)
		{
			try
			{
				boolean movement = false;
				while (myRC.isMovementActive() || myRC.getFlux() < 2) // while we cannot move
				{
					myRC.yield();
				}

				Robot nearBots[] = myRC.senseNearbyGameObjects(Robot.class);
				for(Robot r : nearBots)
				{
					if(r.getTeam() == myRC.getTeam())
					{
						RobotInfo ri = myRC.senseRobotInfo(r);

						// determine if this is a friendly soldier robot
						if(ri.type == RobotType.SOLDIER)
						{
							//determine if this soldier needs flux
							if(ri.flux < 40) //not sure of exact number
							{
								//determine if this soldier is adjacent to us
								if(ri.location.x - myRC.getLocation().x < 2 && ri.location.x - myRC.getLocation().x > -2 && 
										ri.location.y - myRC.getLocation().y < 2 && ri.location.y - myRC.getLocation().y > -2)
								{
									// if so, give him the juice
									if(myRC.roundsUntilMovementIdle() == 0)
									{
										myRC.transferFlux(ri.location,  r.getRobotLevel(), myRC.getFlux()/3); // not sure of exact number
										movement = true;
									}
									break;
								}
								else // move toward him to give him flux!
								{
									if(myRC.roundsUntilMovementIdle() == 0)
									{
										myRC.setDirection(myRC.getLocation().directionTo(ri.location));
										movement = true;
									}
									break;
								}
							}
						}
					}
				}
				if(myRC.getFlux() >= RobotType.SOLDIER.spawnCost && myRC.canMove(myRC.getDirection()) && myRC.roundsUntilMovementIdle() == 0)
				{
					myRC.spawn(RobotType.SOLDIER);
					movement = true;
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

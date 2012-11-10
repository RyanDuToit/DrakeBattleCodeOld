package pwntron;

import battlecode.common.*;

public class RobotPlayer {

	public static void run(RobotController myRC) 
	{
		switch (myRC.getType())
		{
		case ARCHON:
			Archon.doArchonStuff(myRC);
			break;
		case SOLDIER:
			Soldier.doSoldierStuff(myRC);
			break;
		case SCOUT:
			Scout.doScoutStuff(myRC);
			break;
		case SCORCHER:
			Scorcher.doScorcherStuff(myRC);
			break;
		default:
			
		}
	}
}

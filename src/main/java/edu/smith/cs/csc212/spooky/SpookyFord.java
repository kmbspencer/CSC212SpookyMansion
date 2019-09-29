package edu.smith.cs.csc212.spooky;

import java.util.HashMap;
import java.util.Map;
/**
 * SpookyFord, the game.
 * @author kspencer
 * First edition, SpookyMansion
 * @author jfoley
 * CSC 212, Smith College
 *
 */
public class SpookyFord implements GameWorld {
	private Map<String, Place> places = new HashMap<>();
	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "atrium";
	}
	public SpookyFord() {
		Place atrium = insert(Place.create("atrium","You are in a big open room with three glass walls."));
		atrium.addExit(new Exit("firstFloor", "Go up to the first floor."));
		atrium.addExit(new Exit("basement","Go down to the basement."));
		atrium.addExit(new Exit("chemistry1","Go left to Chemistry."));
		atrium.addExit(new Exit("engineering1","Go right to Engineering."));
		
		Place basement = insert(Place.create("basement", "You are in the basement. You see a sign pointing left to the \"Mole Hole\""));
		basement.addExit(new Exit("moleHole","Follow the Mole Hole Sign."));
		basement.addExit(new Exit("firstFloor","Go back up to the first floor."));
		
		Place moleHole = insert(Place.create("moleHole", "You are in a room with smashed computers, and overturned chairs."));
		moleHole.addExit(new Exit("basement","Go to the base of the stairs."));
		moleHole.addExit(new Exit("rBhallway","Follow the hallway to the right."));
		moleHole.addExit(new Exit("lBhallway","Follow the hallway to the left."));
		
		Place rBHallway = insert(Place.create("rBHallway", "You are in a hallway with one door."));
		rBHallway.addExit(new Exit("whiteboard","Continue on down the hallway."));
		rBHallway.addExit(new Exit("moleHole","A sign points to the Mole Hole."));
		
		Place lBHallway = insert(Place.create("lBHallway", "You are in a hallway with one door"));
		lBHallway.addExit(new Exit("moleHole","A sign points to the Mole Hole."));
		
		Place firstFloor = insert(Place.create("firstFloor", "You have made it to the first floor."));
		firstFloor.addExit(new Exit("secondFloor","Go up to the second floor."));
		firstFloor.addExit(new Exit("atrium","Go down to the atrium."));
		
		Place secondFloor = insert(Place.create("secondFloor", "You have made it to the second floor."));
		secondFloor.addExit(new Exit("thirdFloor","Go up to the third floor."));
		secondFloor.addExit(new Exit("firstFloor","Go down to the first floor."));
		
		Place thirdFloor = insert(Place.create("thirdFloor", "You have made it to the third floor."));
		thirdFloor.addExit(new Exit("secondFloor","Go down to the second floor."));
	}
	/**
	 * JJ likes this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}

		// Now that we've checked every exit for every place, crash if we printed any
		// errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}
	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);
	}
	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}

}

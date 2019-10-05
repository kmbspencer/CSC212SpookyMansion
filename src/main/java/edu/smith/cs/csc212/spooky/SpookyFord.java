package edu.smith.cs.csc212.spooky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SpookyFord, the game.
 * @author kspencer
 * First edition, SpookyMansion
 * @author jfoley
 * CSC 212, Smith College
 *
 */

/**
 * Solution for Kate's favorite exit(via airplane!) (Probably)
 * Take 0 0 1 2 take 0 1  2 2 take 0 0 0 1 2 2 3 take 0 4 1 2 1 take 0 4 1 2 search 1
 * Solution for boring way with key and exit
 * 
 * @author kmbspencer
 *
 */
public class SpookyFord implements GameWorld {
	/**
	 * Maps strings (id?) to places, allows connecting of the game map
	 */
	private Map<String, Place> places = new HashMap<>();
	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "atrium";
	}
	/**
	* contructor makes game board with all exits and places.
	*/
	public SpookyFord() {
		Place atrium = insert(Place.create("atrium","You are in a big open room with three glass walls. There is a small key."));
		atrium.addLockedExit(new LockedExit("basement","Go down to the basement.","a small key"));
		atrium.addLockedExit(new LockedExit("chemistry","Go left to the chemistry wing.","a set of chemistry keys"));
		atrium.addExit(new Exit("firstFloor","Go right to the engineering wing."));
		
		Place chemistry = insert(Place.terminal("chemistry","You unlock the door and a wave of acid comes rushing out, swalowing you whole."));
		
		Place basement = insert(Place.create("basement", "You are in the basement. You see a sign pointing left to the \"Mole Hole\""));
		basement.addExit(new Exit("moleHole","Follow the Mole Hole Sign."));
		basement.addExit(new Exit("atrium","Go back up to the atrium."));
		
		Place moleHole = insert(Place.create("moleHole", "You are in a room with smashed computers, and overturned chairs."));
		moleHole.addExit(new Exit("basement","Go to the base of the stairs."));
		moleHole.addExit(new Exit("rBHallway","Follow the hallway to the right."));
		moleHole.addExit(new Exit("lBHallway","Follow the hallway to the left."));
		moleHole.addSecretExit(new SecretExit("secretRoom","There appears to be a storege closet in the far corner."));
		
		Place secretRoom = insert(Place.create("secretRoom", "It is very dark and you cant see much. But as your eyes adjust you can just make out that there is more.\nThere is a key labeled secret."));
		secretRoom.addExit(new Exit("moleHole","Return to the Mole Hole."));
		
		Place rBHallway = insert(Place.create("rBHallway", "You are in a hallway with one door."));
		rBHallway.addExit(new Exit("whiteboard","Continue on down the hallway."));
		rBHallway.addExit(new Exit("moleHole","A sign points to the Mole Hole."));
		rBHallway.addExit(new Exit("playground", "You go through the door."));
		
		Place playground = insert(Place.create("playground", "A room full of bloody tools. There is a sheet of aluminum."));
		playground.addExit(new Exit("rBHallway","There is more in the hallway."));
		
		Place lBHallway = insert(Place.create("lBHallway", "You are in a hallway with one door"));
		lBHallway.addExit(new Exit("moleHole","A sign points to the Mole Hole."));
		lBHallway.addExit(new Exit("whiteboard","Go further down the hallway."));
		lBHallway.addExit(new Exit("zing","Go through the door."));
		
		Place zing = insert(Place.create("zing", "You are in a room full of scientific instruments. There is a small tool kit."));
		zing.addExit(new Exit("lBHallway","There is more down the hallway."));
		
		Place whiteboard = insert(Place.create("whiteboard", "You are standing in front of a whiteboard. \n\"Get out!\" is scrawled in thin writing."));		
		whiteboard.addExit(new Exit("rFire","You continue to the right."));
		whiteboard.addLockedExit(new LockedExit("lBExit","Take the hallway to the left.","a key labeled secret"));
		
		Place rFire = insert(Place.terminal("rFire", "Two steps in you feel the heat. \nThen you see the wall of fire breifly before it consumes you."));
		
		Place lBExit = insert(Place.terminal("lBExit", "The hallway leads to a set of stairs with a door at the top."
				+"\nYou climb the stairs and open the door. \nYou can feel the cool night air on your face. \nYou are free."));
		
		Place firstFloor = insert(Place.create("firstFloor", "You have made it to the first floor engineering wing."));
		firstFloor.addExit(new Exit("atrium","Go down to the atrium."));
		firstFloor.addExit(new Exit("secondFloor","Go up to the second floor."));
		firstFloor.addExit(new Exit("ffhallway","Go down the first floor hallway."));
		firstFloor.addLockedExit(new LockedExit("chemistry","Go left to Chemistry.","a set of chemistry keys"));
		
		Place ffhallway = insert(Place.create("ffhallway", "You are in a hallway with several offices labeled with Professors names and departments"));
		ffhallway.addExit(new Exit("153","153: ???"));
		ffhallway.addExit(new Exit("152","152: Andrew Guswa, Civil Engineering"));
		ffhallway.addExit(new Exit("151","151: Chris Conley, Design Clinic"));
		ffhallway.addExit(new Exit("150","150: R Koh Mechanical Engineering"));
		ffhallway.addExit(new Exit("firstFloor","Go back down the hallway."));
		
		Place rm153 = insert(Place.create("153", "??????????a???????251?????"));
		rm153.addExit(new Exit("ffhallway","Go back into the hallway"));
		Place rm152 = insert(Place.create("152", "You are standing in a very flooded office. The water level is rising. You should leave"));
		rm152.addExit(new Exit("ffhallway","Go back into the hallway"));
		Place rm151 = insert(Place.create("151", "The only thing in this office is a large taxidermy tiger. It's eyes glint menacingly in the moonlight."));
		rm151.addExit(new Exit("ffhallway","Go back into the hallway"));
		Place rm150 = insert(Place.create("150", "You are in an office filled with plants. They look large, and more sentient than feels safe."
				+ "\nOn the bookshelf you spot a that There is a book of mechanics knowledge."));
		rm150.addExit(new Exit("ffhallway","Go back into the hallway"));
		
	
		
		Place secondFloor = insert(Place.create("secondFloor", "You have made it to the second floor. A pipe drips menacignly in the corner."));
		secondFloor.addExit(new Exit("firstFloor","Go down to the first floor."));
		secondFloor.addExit(new Exit("thirdFloor","Go up to the third floor."));
		secondFloor.addExit(new Exit("sfhallway","Go down the second floor hallway."));
		secondFloor.addLockedExit(new LockedExit("chemistry","Go left to Chemistry.","a set of chemistry keys"));
		

		
		Place sfhallway = insert(Place.create("sfhallway", "You are in a hallway with several offices labeled with Professors names and departments"));
		sfhallway.addExit(new Exit("253","253: ILEANA STREINU, Computer Science"));
		sfhallway.addExit(new Exit("252","252: John Foley, Computer Science"));
		sfhallway.addExit(new Exit("251","251: Paul Voss, Aerospace Engineering"));
		sfhallway.addLockedExit(new LockedExit("250","250: GONE FISHING","no key"));
		sfhallway.addExit(new Exit("secondFloor","Go back down the hallway."));
		
		Place rm253 = insert(Place.terminal("253", "You open the door and books tumble out, crushing you."));
		
		Place rm252 = insert(Place.create("252", "This office is full of fun nick nacks. A plate of oreos sits molding on the desk."
				+ "\nThere is a book of computer knowledge."));
		rm252.addExit(new Exit("sfhallway","Go back into the hallway"));
		Place rm251 = insert(Place.create("251", "In the office there is a blackboard with instructions.\n"
				+ "How to Build a Plane:\n"
				+ "Materials\n"
				+ "-Computer Parts\n"
				+ "-Aluminum Sheeting\n"
				+ "-Toolkit\n"
				+ "-Mechanics Knowledge\n"
				+ "-Computer Knowledge\n"
				+ "To do: test off of balcony."));
		
		rm251.addExit(new Exit("sfhallway","Go back into the hallway"));

		
		
		Place thirdFloor = insert(Place.create("thirdFloor", "You have made it to the third floor. You hear a roar in the distance."));
		thirdFloor.addExit(new Exit("secondFloor","Go down to the second floor."));
		thirdFloor.addExit(new Exit("tfhallway","Go down the third floor hallway."));
		thirdFloor.addExit(new Exit("balcony","A smashed glass door leads to a balcony."));
		thirdFloor.addLockedExit(new LockedExit("chemistry","Go left to Chemistry.","a set of chemistry keys"));
		
		//The parts needed to complete the plane and fly off of the balcony
		ArrayList<String> planeParts = new ArrayList();
		planeParts.add("a book of computer knowledge");
		planeParts.add("a box of computer parts");
		planeParts.add("a book of mechanics knowledge");
		planeParts.add("a sheet of aluminum");
		planeParts.add("a small tool kit");
		
		
		Place balcony = insert(Place.create("balcony", "You can feel the cool night air on your face, you can see the ground below, it seems far away."));
		balcony.addExit(new Exit("jump","You could jump I guess."));
		balcony.addSecretLockedExit(new SecretLockedExit("plane","Maybe you could build something to get down if you had the materials...",planeParts));
		balcony.addExit(new Exit("tfhallway","Go back inside."));
		
		Place jump = insert(Place.terminal("jump", "This was a bad idea."));
		
		Place plane = insert(Place.terminal("plane", "You combine all your materials into the most beautiful plane ever. You climb in and fly off into the night, free at last."));
		
		Place tfhallway = insert(Place.create("tfhallway", "You are in a hallway with several offices labeled with Professors names and departments"));
		tfhallway.addExit(new Exit("355","355: Alicia Grubb, Computer Science"));
		tfhallway.addExit(new Exit("352","352: Mike Kinsinger, Chemical Engineering"));
		tfhallway.addExit(new Exit("351","351: Glenn Ellis"));
		tfhallway.addExit(new Exit("350","350: Wolf Room."));
		tfhallway.addExit(new Exit("thirdFloor","Go back down the hallway."));
		
		Place rm355 = insert(Place.create("355", "You are in an office filled with plush chairs. A kettle boils in one corner.\nOn a coffee table, and on it There is a box of computer parts."));
		rm355.addExit(new Exit("tfhallway","Go back into the hallway"));
		Place rm352 = insert(Place.create("352", "You are inside a brightly lit office, which is weird because you could have sworn it was night before. \n"
				+ "There is a set of chemistry keys"));
		rm352.addExit(new Exit("tfhallway","Go back into the hallway"));
		Place rm351 = insert(Place.create("351", "The room is empty save for a small post it that reads\n \" In a meeting with Paul Voss, in room 251 if needed.\""));
		rm351.addExit(new Exit("tfhallway","Go back into the hallway"));
		Place rm350 = insert(Place.create("350", "There are no wolves. Where did they go..."));
		rm350.addExit(new Exit("tfhallway","Go back into the hallway"));
	}
	/**
	 * Useful method for checking to make sure that the graph makes sense!
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

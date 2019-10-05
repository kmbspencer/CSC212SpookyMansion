package edu.smith.cs.csc212.spooky;

import java.util.ArrayList;
import java.util.List;

/**
 * This is our main class for SpookyMansion.
 * It interacts with a GameWorld and handles user-input.
 * It can play any game, really.
 *
 * @author jfoley
 * 
 * modified by kmbspencer
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * @param input - a helper object to ask the user questions.
	 * @param game - the places and exits that make up the game we're playing.
	 * @return where - the place the player finished.
	 */

	
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		// Maybe we'll expand this to a Player object.
		String place = game.getStart();
		//Makes a bag for the user to store their stuff
		ArrayList<String> bag = new ArrayList();


		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(place);
			//((SpookyFord) game).pickUp(here.items());
			
			
			System.out.println();
			System.out.println("... --- ...");
			System.out.println(here.getDescription());
			here.visit();

			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" "+i+". " + e.getDescription());
			}

			// Figure out what the user wants to do
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();
			
			//Takes the item and adds to list bag, removes from the Place
			if(action.equals("take")) {
					bag.add(here.items());
					here.removeItem();
				continue;
				
			}
			//Looks at the items in bags and prints "You do not have any items." or the list of items.
			if(action.equals("stuff")) {
				if(bag.size() == 0) {
					System.out.println("You do not have any items.\n");
				}
				else if(bag.size() ==1) {
					System.out.print("You have "+bag.get(0)+"\n");
				}
				else if(bag.size()>1) {
					System.out.print("You have ");
					for(int i = 0; i<bag.size()-1;i++) {
						System.out.print(bag.get(i) + ", ");
					}
					System.out.print(" and "+ bag.get(bag.size()-1)+".\n");
				}
				continue;
			}
			//various ways of letting the user quit
			if (action.equals("quit")) {
				if (input.confirm("Are you sure you want to quit?")) {
					return place;
				} else {
					continue;
				}
			}
			if(action.contentEquals("q")) {
				if(input.confirm("Are you sure you want to quit?")) {
					return place;
				} else {
					continue;
				}
			}
			if(action.contentEquals("escape")) {
				if(input.confirm("Are you sure you want to quit?")) {
					return place;
				} else {
					continue;
				}
			}
			//prints helpful intructions
			if(action.contentEquals("help")) {
				System.out.println("Choose a number to go further into the mansion,\n or type \"q\", \"escape\", or \"quit\" to leave.\n "
						+ "To pick up an item, type \"take\". To see your items, type \"stuff\"."
						+ "\nTo find secret exits type \"search\"");
				continue;
			}
			//reveals any hidden exits
			if(action.contentEquals("search")) {
				System.out.println("You search for more exits");
				for(Exit e: here.getAllExits()) {
					e.search();
				}
				continue;
			}

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}
			// if the door is locked, and you don't have the item needed to unlock it, and it isn't the airplane exit,
			//then tell the user the door is locked
			if(   (!exits.get(exitNum).unlocked())&&(!bag.contains(exits.get(exitNum).getKey()))&& (!exits.get(exitNum).isPlane())   )   {
				System.out.println("\nThat door is locked, maybe with a key...");
				continue;
			} 
			// if the door is locked, but you do have the things needed to open, unlock the door and go into that room
			else if(exits.get(exitNum).unlocked()||bag.contains(exits.get(exitNum).getKey())) {
				exits.get(exitNum).unlock();
				// Move to the room they indicated.
				Exit destination = exits.get(exitNum);
				place = destination.getTarget();
			//I tried several ways to make this work that were more flexible for other versions of this game, but in the end to make it 
			//work I had to hard code in the items necessary to build the plane to escape off of the balcony.
			// this statement asks if the door is locked, and the user doesn't have the materials to build the plane, they can't build it.
			}else if(    !exits.get(exitNum).unlocked() && !(bag.contains("a book of computer knowledge")
					&& bag.contains("a box of computer parts")  
					&& bag.contains("a book of mechanics knowledge")
					&& bag.contains("a sheet of aluminum")
					&& bag.contains("a small tool kit"))  ){

				System.out.println("You don't have the materials to build that");
				continue;
			//this statement says that if the door is locked, but the user does have the materials to build the plane,
			//it lets them unlock it and win the game
			} else if (    !exits.get(exitNum).unlocked() 
					&& bag.contains("a book of computer knowledge")
					&& bag.contains("a box of computer parts")  
					&& bag.contains("a book of mechanics knowledge")
					&& bag.contains("a sheet of aluminum")
					&& bag.contains("a small tool kit")
					){
				//moves the user to the desination they want
				Exit destination = exits.get(exitNum);
				place = destination.getTarget();
			}
		
			
		}

		return place;
	}

	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		//GameWorld game = new SpookyMansion();
		GameWorld game = new SpookyFord();

		// Actually play the game.
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
	}

}

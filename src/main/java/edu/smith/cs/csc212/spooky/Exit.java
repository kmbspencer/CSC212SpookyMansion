package edu.smith.cs.csc212.spooky;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents an exit from a Place to another Place.
 * @author jfoley
 *
 */
public class Exit {
	/**
	 * How do we describe this exit to a user, e.g., "A door with a spiderweb."
	 */
	private String description;
	/**
	 * How do we identify the Place that this is going.
	 */
	private String target;
	
	/**
	 * Create a new Exit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public Exit(String target, String description) {
		this.description = description;
		this.target = target;
	}
	/**
	 * Ensures that the exit shows when it isn't secret
	 */
	boolean hidden = false;
	/**
	 * Shows that the door is unlocked
	 */
	boolean locked = false;
	/**
	 * normal exits don't need a key to open
	 */
	public String key;
	
	/**
	 * A getter for the description of this exit.
	 * @return how it looks.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * A getter for the target place of this exit.
	 * @return where it goes.
	 */
	public String getTarget() {
		return this.target;
	}
	
	/**
	 * Make this debug-able when we print it for ourselves.
	 */
	public String toString() {
		return "Exit("+this.description+", "+this.target+")";
	}
	
	/**
	 * Make it so we can put this in a HashMap or HashSet.
	 */
	public int hashCode() {
		return Objects.hash(this.description, this.target);
	}
	
	/**
	 * This is a useful definition of being the same.
	 * @param other - another exit.
	 * @return if they go to the same place.
	 */
	public boolean goesToSamePlace(Exit other) {
		return this.target.equals(other.target);
	}
	
	/**
	 * The other half of hashCode that lets us put it in a HashMap or HashSet.
	 */
	public boolean equals(Object other) {
		if (other instanceof Exit) {
			Exit rhs = (Exit) other;
			return this.target.equals(rhs.target) && this.description.equals(rhs.description); 
		}
		return false;
	}
	/**
	 * @return bool of if the exit is secret or not
	 */
	public boolean isSecret() {
		return this.hidden;
	}
	/**
	 * search makes all methods that were hidden visible
	 */
	public void search() {
		this.reveal();
	}
	/**
	 * if the exit is hidden, reveal it
	 */
	public void reveal() {
		this.hidden = false;
	}
	/**
	 * sets locked to false
	 */
	public void unlock() {
		this.locked = false;
	}
	/**
	 * returns true if unlocked
	 */
	public boolean unlocked() {
		return !this.locked;
	}
	/**
	 * returns the key needed to unlock
	 */
	public String getKey() {
		return this.key;
	}
	/**
	 * if a door needs multiple keys, return the list
	 */
	public ArrayList<String> getListKey(){
		return null;
	}
	/**
	 * states if the exit is the final airplane building exit
	 * ideally would be more nuanced and general
	 */
	public boolean isPlane() {
		return false;
	}
}

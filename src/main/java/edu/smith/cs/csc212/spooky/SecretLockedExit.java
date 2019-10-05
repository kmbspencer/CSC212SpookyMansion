package edu.smith.cs.csc212.spooky;

import java.util.ArrayList;

public class SecretLockedExit extends SecretExit {
	/**
	 * How do we describe this exit to a user, e.g., "A door with a spiderweb."
	 */
	private String description;
	/**
	 * How do we identify the Place that this is going.
	 */
	private String target;
	/**
	 * Ensures that the exit shows when it isn't secret
	 */
	boolean hidden = true;
	/**
	 * Ensures that the user cant go throught the exit if locked
	 */
	boolean locked = true;
	/*
	 * list of keys necessary to unlock door
	 */
	private ArrayList<String> key= new ArrayList();
	/**
	 * Create a new Exit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 * @param key - list of keys to unlock door
	 */
	public SecretLockedExit(String target, String description, ArrayList<String> key) {
		super(target, description);
		this.description= description;
		this.target = target;
		this.key = key;
	}
		/**
		 *Sets list of keys to different strings
		 */
	public void setKey(ArrayList<String> newKey) {
		this.key = newKey;
	}
	/**
	 * makes the door no longer hidden
	 */
	@Override
	public void search() {
		this.reveal();
	}
	/**
	 * makes the door no longer hidden
	 */
	@Override
	public void reveal() {
		this.hidden = false;
		
	}
	/**
	 * returns true if the door is hidden
	 */
	public boolean isSecret() {
		return this.hidden;
	}
	/**
	 * allows the user to pass throught the door
	 */
	@Override
	public void unlock() {
		this.locked = false;
	}
	/**
	 * returns true if unlocked
	 */
	@Override
	public boolean unlocked() {
		return !this.locked;
	}
	/**
	 * Returns the list of keys needed to unlock door
	 */
	public ArrayList<String> getListKey() {
		return this.key;
	}
	/**
	 * returns true if it is the final airplane exit 
	 * In Spooky ford this is the only secret locked exit, 
	 * so it is always true
	 */
	@Override
	public boolean isPlane() {
		return true;
	}
}

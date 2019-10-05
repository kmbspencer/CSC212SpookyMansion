package edu.smith.cs.csc212.spooky;

public class LockedExit extends Exit {
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
	boolean hidden = false;
	/**
	 * Ensures that the user cant go throught the exit if locked
	 */
	boolean locked = true;
	/**
	 * String that described the key needed to open the door
	 */
	private String key;
	
	/**
	 * Create a new Exit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.description = description;
		this.target = target;
		this.key = key;
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
	 * returns key needed to unlock door
	 */
	@Override
	public String getKey() {
		return this.key;
	}

	
}

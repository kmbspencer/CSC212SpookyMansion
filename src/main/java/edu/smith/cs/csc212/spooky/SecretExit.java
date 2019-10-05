package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit {
	/**
	 * Exit is secret until "search" is entered
	 */
	boolean hidden = true;
	/**
	 * once found the door can be opened
	 */
	boolean locked = false;
	/**
	 * How do we describe this exit to a user, e.g., "A door with a spiderweb."
	 */
	private String description;
	/**
	 * How do we identify the Place that this is going.
	 */
	private String target;
	/**
	 * Create a new SecretExit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public SecretExit(String target, String description) {
		super(target, description);
		this.description= description;
		this.target = target;
	}
	/**
	 * returns true if the door is hidden
	 */
	public boolean isSecret() {
		return this.hidden;
	}
	/**
	 * makes the door no longer hidden
	 */
	public void search() {
		this.reveal();
	}
	/**
	 *  makes the door no longer hidden
	 */
	public void reveal() {
		this.hidden = false;
	}


}

package com.example.demo;

/**
 * The {@code Destructible} interface defines the behaviors of objects that can be damaged and destroyed.
 */
public interface Destructible {

	/**
	 * Applies damage to the object, reducing its durability or health.
	 */
	void takeDamage();

	/**
	 * Destroys the object, rendering it unusable or removing it from the game.
	 */
	void destroy();
	
}

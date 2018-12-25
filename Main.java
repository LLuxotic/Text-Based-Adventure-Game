package game;

import java.util.Scanner;
import java.util.Random;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// System Objects
		Scanner in = new Scanner(System.in);
		Random rand = new Random();


		// Game variables
		String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assassin" };
		int maxEnemyHealth = 100; // Maximum enemy health
		int enemyAttackDamage = 30; // Maximum damage enemy could do to player

		// Player Variables
		int health = 150; // Maximum player health
		int attackDamage = 50; // Maximum damage player could to enemy
		int numHealthPotions = 3; // Number of health pots player starts off with
		int healthPotionHealAmount = 35; // Amount of health the potion heals for
		int healthPotionDropChance = 30; // Everytime enemy dies chance the enemy will drop a health potion (in percentage) 

		boolean running = true;

		System.out.println("Welcome to the Luxotic's Dungeon!");

		// Start of game below
		GAME:	
			while (running) {
				System.out.println("--------------------------------------------------");

				int enemyHealth = rand.nextInt(maxEnemyHealth); // Everytime program begins a random health for enemy is set between 0 and max health
				String enemy = enemies[rand.nextInt(enemies.length)]; // Randomizes enemy chosen for round
				System.out.println("\t# A " + enemy + " has appeared! #\n"); // States enemy that has appeared

				while (enemyHealth > 0) {
					System.out.println("\tYour HP: " + health); // States players health
					System.out.println("\t" + enemy + "'s HP: " + enemyHealth); // States enemies health
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Drink health potion");
					System.out.println("\t3. Run!");

					String input = in.nextLine();
					if (input.equals("1")) {
						int damageDealt = rand.nextInt(attackDamage); // Randomize attack damage towards enemy
						int damageTaken = rand.nextInt(enemyAttackDamage); // Randomize attack damage taken from enemy

						enemyHealth -= damageDealt; // Recalculate enemy health
						health -= damageTaken; // Recalculate player health

						System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
						System.out.println("\t> You recieve " + damageTaken + " in retaliation!");

						if (health < 1) { // End game when player dies
							System.out.println("\t> You have taken too much damage, you are too weak to go on");
							break;
						}

					}
					else if (input.equals("2")) { // Drink health potion option
						if (numHealthPotions > 0) { // Check if player has potions in bag
							health += healthPotionHealAmount; // Add the extra health received from potion
							numHealthPotions--;

							System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
									+ "\n\t> You now have " + health + " HP." // Restate players new health
									+ "\n\t> You now have " + numHealthPotions + " health potion(s) left. \n"); // Restate number of health potions left
						} 
						else {
							System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
							// Inform user that they have no health potions
						}

					}
					else if (input.equals("3")) {
						System.out.println("\tYou have run away from the " + enemy + "!");
						// Inform user that they have ran from the enemy
						continue GAME;

					}
					else {
						System.out.println("\tInvalid Command.");
						// Inform user they have inputed an invalid command
					}
				}

				if (health < 1) {
					System.out.println("You limp out of the dungeon, weak from battle.");
					break;
					// End game if player is dead
				}

				// Continue game if player is alive
				System.out.println("--------------------------------------------------");
				System.out.println(" # " + enemy + " was defeated! # ");
				System.out.println(" # You have " + health + "HP left. # ");

				if (rand.nextInt(100) < healthPotionDropChance) { // Potentially provide player with health potions
					numHealthPotions++;
					System.out.println(" # The enemy dropped a health potion! # ");
					System.out.println(" # You now have " + numHealthPotions + " health potion(s). # ");

					// Provide player with new options
					System.out.println("What would you like to do now?");
					System.out.println("1. Continue fighting");
					System.out.println("2. Exit dungeon");

					String input = in.nextLine();

					while (!input.equals("1") && !input.equals("2")) {
						System.out.println("Invalid Command.");
						// Inform user they have inputed an invalid command
						input = in.nextLine();
					}

					if (input.equals("1")) {
						System.out.println("You have continued your adventure!");
					}
					else if (input.equals("2")) {
						System.out.println("You exit the dungeon, successful from your adventure!");
						break;
					}
				}
			}
		System.out.println("#######################");
		System.out.println("# THANKS FOR PLAYING! #");
		System.out.println("#######################");

	}

}

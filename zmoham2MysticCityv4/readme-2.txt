Ramon Barajas - rbaraj3
Stephen Lambert - slambe7
Zoheb Mohammed - zmoham2
CS 342 - 12:30 PM section
Homework 4 - Group 17

=====================================================================================================================================
Note: This program is based off our previous projects which implemented several aspects of Dr. Bell's 2nd project

Note: This program is currently able to work with MysticCity5.1.gdf.  We have included the file in the directory.
=====================================================================================================================================

HOW TO PLAY: ***READ THIS, IT'S SUPER IMPORTANT***
	1.) The beginning of the game acts as a tutorial for how to play the game as a whole
	2.) This is the strategy guide for safe playing:
		A.) Zaphod is poisoned! He must find the antidote before he dies!
		B.) Our hero begins in the entrance hall. Have him travel to the ogre's lair and retrieve the brass key!
		C.) After retrieving the brass key, return to the entrance hall and unlock the door that leads us to the potions storeroom!
		D.) If you look around, you may find something useful to cure Zaphod!
		E.) Get the antidote
		F.) Drink the antidote! (command is... "Drink antidote")

		you have now passed the tutorial for how to play this game. Enjoy the rest!
		IMPORTANT NOTE! This is basically a cheat sheet for how to get cured; if a room is dark, you must get the torch from the entrance hall
			*To use the torch, type "light torch"


READ ME: This program contains equal contributions from all its members. The classes that were enhanced via inheritance are:
	1.) Artifact - modified by Stephen
	2.) Move + Character - modified by Zoheb
	3.) Place - modified by Ramon
============================================================================================================================================

==============
Artifact class
==============

For this portion of the project, Stephen extended the Artifact class with three items. The items were included in the .gdf with their specific types (health, food, tool) in case we decided to include/create new items.

The three items are 
	*Chicken: to increase the health bar/meter of the character
		-If eaten, a message pops up saying player ate it
		*To use, type eat chicken	// already in the inventory
	*Torch: to illuminate an area if it is dark	
		-If used, a message pops up saying room it lit
	*Antidote: to cure a player's ailment
		-If imbibed, a message pops up saying player drank it
		*To use, type drink antidote

	-Note: Our initial idea is to poison the player and have him find the antidote before he dies. If he fails to do so, the game ends. This is meant to 	be a tutorial to move around rooms, use the special artifacts (food, liquids, light)

======================
Move + Character class
======================

In this portion, Zoheb implemented two new classes extended from Move. They are eat and drink. The eat class deals with the FOOD category from our gdf. The drink class deals with HEALTH. 

Then, Zoheb modified the UI to include the eat and drink commands. By eating a food item, a player's HP is affected positively. By drinking, it affects the status of a player (maybe use a design class here to keep track of status?), i.e. curing, put to sleep, poison, healing. 

For simple implementation sake, our eat and drink currently heals the player by raising his/her HP and removing the poison status. 
	1.) Eat chicken: heals a player's HP by 1
	2.) Drink antidote: cures the poisoned status
		-If the player is poisoned, his HP decrements by 1
		-If his/her health reaches 0, the game ends because death
		-If a player is not poisoned and attempts to drink the antidote,
		the game will prevent the player from drinking the potion
	3.) Light torch 
		-The torch, when picked up, will give the player the option to light up a room
		*if they decide not to use it, they risk not knowing what room they are in, who's in it with them, or what artifacts are in the room
		*this information is reflected in the place.display() method via flags
		*future iterations will have certain scenarios affect the player: for example, if an enemy is in the room, they can't fight because they 		can't see but will still incur damage from the enemy

Note: When a character dies, we want the game to continue. However, for this program we kept it simple. If/When we implement our desired outcome of continuing the game, we remove the player from the game by removing him from the collection of characters.

In future iterations of this project, we had ideas for including other types of food/drinks (i.e. rotten meat that may heal you but poison you. A really taste food item may heal someone completely. Antidote will make the player normal and their HP meter will cease to drop. Beer will disallow the player from choosing a direction; instead the direction will be chosen randomly.)

===========
Place class
===========

For our place class, we are instituting a simple behavior mechanism. We are checking if our room is lit or dark. Depending on the status of the room, the behavior of the character is affected. If the room is dark, the character will not be able to tell what artifacts are in the room, nor which room he is in. He can continue in any direction but will not know where he is going (in future iterations, this will cause issues if he runs into an enemy NPC). He may accidentally exit the game or get trapped in a room without a key! 

Our torch artifact remedies this situation; the player HAS to ensure that he picks it up at the very beginning of the game in Entrance Hall. Once he lights the way, he will be able to illuminate whatever room he is and see what he could not before.

To implement this feature, we installed a flag system to make random rooms dark. The only constant is the first player MUST get the torch at the entrance hall. The lost wanderer will (as his name implies) be most likely lost. 

One thing we definitely should do/have done, is check if the player actually has a torch in his inventory. 
UPDATE 2
We extended the place class by making an extra child, with this implementation the player is not able to see anything in that class.

============================================================================================================================================

Additional notes: Since we extended the move class in order to implement our extended artifacts, we thought (for our future project) of possibly implementing a command design pattern; for organizational purposes. Because currently, the commands that a player is allowed to use are mostly listed out in the UI class.

Although it's not a direct implementation; we were thinking of using a chain of responsibility design pattern here. Basically, once the player recognizes a room is dark, they have to do certain events before progressing through the game safely. First, they have to acquire a tool that will help light the way. Second, they have to activate the item. Third, by activating the item, they affect the current room. Once the current room is lit, the player will have more freedom to act. 

Another implementation we wanted to include was a simple combat system. We would create a different interface similar to Decision Maker for combat. If a player and an enemy NPC are in the same in room, they are locked in combat. They are not able to move to a different room until the fight is over. Along with this idea, we wanted to introduce another special artifact: the coin of destiny. The NPC challenges a player to choose heads or tails. If the player chooses correctly, the NPC dies and the player can continue through Mystic City. However, if the player guesses wrong, the player dies and the game ends. Mentioned earlier, either the game can end completely, or we remove the dead character from the game and continue playing with remaining characters. 

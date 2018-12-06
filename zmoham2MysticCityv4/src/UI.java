//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * UI Class                                                                                                    *
 * Provides the functionality for UI Player                                                                    *
 * Currently implements: actions for the player (very akin to hw2 game.play())                                 *
 * ************************************************************************************************************* */

import java.util.*;

public class UI implements DecisionMaker {


    public Move getMove(Character character, Place place)
    {
        // initialize move
        Move move = null;

        // as game progresses; show player details -> player name and location information
        character.display();
        place.display();

        // begin player's command sequences
        System.out.println(character.getName() + ", Test your Might. Your wish?: ");
        Scanner sc = KeyboardScanner.getKeyboard();
        String line = sc.nextLine();
        line = line.toUpperCase();


        // If the user wants, exit the game
        if(line.equalsIgnoreCase("EXIT")|| line.equalsIgnoreCase("QUIT")) {
            System.out.println("Leaving the game...");
            System.exit(0);
        }

        // Look in the current room
        if(line.equalsIgnoreCase("LOOK")) 
        { 
          if(character.getCurrentPlace().getLightStat()) 
            place.showArtifacts(); 
	  else
	  { 
	    System.out.println("The room is dark you cannot see anything");  
	    System.out.println("Feel free to move randomly or if you have it light the torch."); 
	  } 

        } 

        // Check player's inventory
        if (line.equalsIgnoreCase("INVE")|| line.equalsIgnoreCase("INVENTORY")){

            character.printPossesions();
            System.out.println("\n");

        }

        // Get an Artifact
        if(line.length() > 4 && line.substring(0,4).equalsIgnoreCase("GET ")){

            move = new Move(line.substring(0,4), line.substring(4));
            Artifacts a = character.getCurrentPlace().removeArtifactByName(move.getArgument());
            if (a != null)
                character.addArtifact(a);
        }
        
        if (line.length() > 4 && line.substring(0,4).equalsIgnoreCase("EAT "))
        {
        	move = new Eat(line.substring(0,4), line.substring(4));
        	Artifacts a = character.removeArtifactByName(move.getArgument());
		move.displayMessage(); 
        	character.updateHealth(1);
        }
        if (line.length() > 6 && line.substring(0,6).equalsIgnoreCase("DRINK "))
        { 
          if(character.getPStatus()) 
	  { 
            move = new Drink(line.substring(0,6), line.substring(6).trim());
            Artifacts a = character.removeArtifactByName(move.getArgument());
            character.updatePoisonStatus(); 	  
            move.displayMessage(); 
	  } 
          else
	  { 
	    System.out.println("You are not poisoned and cannot drink this now."); 
	    System.out.println("Please attempt again when you are poisoned."); 
          }  
        } 

        if(line.length() > 6 && line.substring(0,6).equalsIgnoreCase("LIGHT ")) 
        { 
	  if(character.inventoryCheck(line.substring(6).trim())) 
	  { 
	    move = new Light(line.substring(0,6), line.substring(6).trim());  
            character.getCurrentPlace().lightPlace();
	    move.displayMessage(); 
	  } 
	  else
	  { 
	    System.out.println("You are not carrying a torch"); 
	    System.out.println("Go find a light source"); 
	  } 
	}
	// Easter egg
        if( line.equalsIgnoreCase( "XYZZY" ) )
        {
            character.updateLocation(Place.getPlaceByID( 12 ));
        } 

        // Put an Artifact down
        if (line.length() > 5 && line.substring(0,5).equalsIgnoreCase("DROP ")){

            move = new Move(line.substring(0,5), line.substring(5).trim());
            Artifacts a = character.removeArtifactByName(move.getArgument());
            //character.getCurrentPlace().addArtifact(a);
        }

        // use Artifact
        if (line.length()>4 && line.substring(0,4).equalsIgnoreCase("USE ")){
		
            move = new Move(line.substring(0,4), line.substring(4)); 
            character.useItem(move.getArgument());
        }

        // Anything else should be a navigation command with or with out go
        if(line.length()>3 && line.substring(0,3).equalsIgnoreCase("GO ")) {

            move = new Move(line.substring(0,3), line.substring(3));

            Place p = character.getCurrentPlace().followDirection(move.getArgument());
            System.out.println("Now entering... " + p.name());
            character.updateLocation(p);
            System.out.println("\n");

        } 

        if(character.getCurrentPlace().isExit())
            System.exit(0); 

        if(character.getPStatus()) 
        { 
          character.updateHealth(-1);
	  if(character.getHealthStatus() == 0)
          { 
	    System.out.println("You ded sukka"); 
            System.exit(0); 
          } 
        } 

    return move;

    }
}



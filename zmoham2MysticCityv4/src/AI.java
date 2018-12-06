//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17

/* *************************************************************************************************************
 * AI Class                                                                                                    *
 * Provides the action (random) for the NPC                                                                    *
 * Currently implements: GO in a random direction                                                              *
 * ************************************************************************************************************* */

import java.util.*;

public class AI implements DecisionMaker {

    public Move getMove(Character character, Place place) {
        character.display();
        place.display();

        Move move = null;
        // generate random numbers for switch cases and for directions
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<String> directions = new ArrayList<String>();

        // for artifacts the AI can use/drop/get
        ArrayList<String> artifacts = new ArrayList<String>();

        list.addAll(Arrays.asList(1,2,3,4));
        directions.addAll(Arrays.asList("N", "S", "E", "W", "U", "D", "NONE", "NE", "NW", "SW", "SE", "NNE", "NNW", "ENE", "WNW", "ESE", "WSW", "SSE", "SSW"));
        artifacts.addAll(Arrays.asList("Hitchiker's Guide", "Pot O Gold", "Four-Leaf Clover", "Jewel-encrusted chest", "Golden chalice", "Purple potion", "Holly wand", "Parchment scroll", "Sparkling rubies", "Oak mallet", "Leather bag", "Brass key", "Golden key", "Ivory key"));

        // randomly select a number based on the list
        int random = new Random().nextInt(list.size());
        int randomDir = new Random().nextInt(directions.size());

        // randomly select a number based on the second list
        int aRandomArt = new Random().nextInt(artifacts.size());

        //System.out.println(directions.get(randomDir));
        //System.out.println(random);

        switch (random){
            case 1:
                // return the argument for choice of direction
		System.out.println("****Testing GO in AI.java****");///////////////////////////////// 
                move = new Move("GO", directions.get(randomDir));
                System.out.println("Trying to go from " + character.getCurrentPlace().name() + " by travelling " + directions.get(randomDir));
                // try to get character to follow direction if possible
                Place p = character.getCurrentPlace().followDirection(move.getArgument());
                System.out.println(character.getName() + " is now in " + p.name());
                // if unable to proceed, it stays in the same place
                character.updateLocation(p);
                System.out.println("\n");
                // if we somehow managed to exit the game, the game ends!
                if(character.getCurrentPlace().isExit())
                    System.exit(0);
                return move;

            case 2:
		System.out.println("****Testing USE in AI.java****");///////////////////////////////// 
                move = new Move("USE", artifacts.get(aRandomArt));
                System.out.println("Trying to use up " + artifacts.get(aRandomArt));
                character.useItem(artifacts.get(aRandomArt));
                return move;
            //System.out.println("Testing: on USE");
            //break;
            case 3:
		System.out.println("****Testing DROP in AI.java****");///////////////////////////////// 
                move = new Move("DROP", artifacts.get(aRandomArt));
                System.out.println("Trying to drop " + artifacts.get(aRandomArt));
                Artifacts a = character.removeArtifactByName(artifacts.get(aRandomArt));
                character.getCurrentPlace().addArtifact(a);
                return move;
            //System.out.println("Testing: on DROP");
            //break;
            case 4:
		System.out.println("****Testing GET in AI.java****");///////////////////////////////// 
                move = new Move("GET", artifacts.get(aRandomArt));
                System.out.println("Trying to pick up " + artifacts.get(aRandomArt));
                Artifacts b = character.getCurrentPlace().removeArtifactByName(artifacts.get(aRandomArt));
                if (b != null)
                    character.addArtifact(b);
                return move;
            //System.out.println("Testing: on GET");
            //break;

        }

        return move;
    }
}

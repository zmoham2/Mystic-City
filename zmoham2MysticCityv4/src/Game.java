//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * Game Class                                                                                                  *
 * Provides the functionality for the gae                                                                      *
 * Currently implements: parses in read in gdf to appropriate classes and calls for players to make moves      *
 * ************************************************************************************************************* */

import java.util.*;
import java.lang.*;

public class Game{

    private static String name;
    private ArrayList<Character> characters;
    private static Vector< Integer > pArr = new Vector< Integer >();

    public Game(Scanner infile){

        characters = new ArrayList<Character>();

        //Parsing file
        String line = CleanLineScanner.getCleanLine(infile);
        Scanner lineScanner = new Scanner(line);
        String word = lineScanner.next();

        if(!word.equalsIgnoreCase("GDF")){
            System.err.println("Error parsing file \"GDF\" not found");
            System.exit(-1);
        }

        // check for correct version
        double version = lineScanner.nextDouble();
        if(version != 5.1){
            System.err.println("Error parsing file. Wrong version" + version);
            System.exit(-2);
        }
        lineScanner.skip("[ \t]*");

        name = lineScanner.nextLine();

        // add in places
        int nPlaces = keywordCount(infile,"PLACES"); 
	boolean r = true;

        for(int i = 0; i < nPlaces;i++) 
        { 
	  if(r) 
	  {
	    new Dark(infile); 
	    r = false; 
	    nPlaces--;
	  } 
            new Place(infile);
            //places.add(new Place(infile));
        }

        // add in directions
        int nDirections = keywordCount(infile, "DIRECTIONS");
        for (int i = 0; i<nDirections;i++) {
            new Direction(infile);
        }

        // add in characters
        int nCharacters = keywordCount(infile, "CHARACTERS");

        for (int i = 0; i < nCharacters; i++)
        {
            line = infile.next();
            String playerType = line;

            lineScanner.skip( "[ \t]*" );

            if(playerType.equalsIgnoreCase("PLAYER"))
            {
                Character character = new Player(infile );
                characters.add(character);
            }
            else if(playerType.equalsIgnoreCase("NPC"))
            {
                Character character = new NPC(infile);
                characters.add(character);
            }
            else
            {
                break;
            }
        }

        // add in artifacts
        int nArtifacts = keywordCount(infile, "ARTIFACTS");

        for( int i = 0; i < nArtifacts; i++ )
        {
            line = CleanLineScanner.getCleanLine( infile );
            lineScanner = new Scanner( line );
            int placeID = lineScanner.nextInt();


            if(placeID < 0)
            {
                // take the absolute value of the placeID from GDF and use it to get the place
                Character.getCharacterByID((Math.abs(placeID))).addArtifact(new Artifacts(infile));
            }
            else if(placeID == 0)
            {
                // place an object with no specified location into a random position
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.addAll(Arrays.asList(12, 13, 23, 22, 21, 11, 101, 102, 103, 104, 105, 106, 107, 108));
                int random = new Random().nextInt(list.size());
                //System.out.println(random);
                int randNum = list.get(random);

                Place.getPlaceByID(randNum).addArtifact(new Artifacts(infile));

            }
            else if(placeID > 0)
            {
                // if the place ID is positive, simply add it
                Place.getPlaceByID( placeID ).addArtifact(new Artifacts(infile));
            }
        }
        int nSpecialItems = keywordCount(infile, "SPECIAL");

        for( int i = 0; i < nSpecialItems; i++ )
        {
            line = CleanLineScanner.getCleanLine( infile );
//            System.out.println("This is the line " + line); //////////////////delete this before submission////////////////// 

            lineScanner = new Scanner( line );
            String spItemType = lineScanner.next();
            int placeID = lineScanner.nextInt();
//            System.out.println("This is the special item type " + spItemType); //////////////////delete this before submission////////////////// 

            if(spItemType.equalsIgnoreCase("HEALTH")) 
            { 
                if(placeID < 0)
                {
                    // take the absolute value of the placeID from GDF and use it to get the place
                    Character.getCharacterByID((Math.abs(placeID))).addArtifact(new Potion(infile));
                }
                else if(placeID == 0)
                {
                    // place an object with no specified location into a random position
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.addAll(Arrays.asList(12, 13, 23, 22, 21, 11, 101, 102, 103, 104, 105, 106, 107, 108));
                    int random = new Random().nextInt(list.size());
                    //System.out.println(random);
                    int randNum = list.get(random);

                    Place.getPlaceByID(randNum).addArtifact(new Potion(infile));

                }
                else if(placeID > 0)
                {
                    // if the place ID is positive, simply add it
                    Place.getPlaceByID( placeID ).addArtifact(new Potion(infile));
                }
            }

            if(spItemType.equalsIgnoreCase("TOOL")) 
            { 
                if(placeID < 0)
                {
                    // take the absolute value of the placeID from GDF and use it to get the place
                    Character.getCharacterByID((Math.abs(placeID))).addArtifact(new Torch(infile));
                }
                else if(placeID == 0)
                {
                    // place an object with no specified location into a random position
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.addAll(Arrays.asList(12, 13, 23, 22, 21, 11, 101, 102, 103, 104, 105, 106, 107, 108));
                    int random = new Random().nextInt(list.size());
                    //System.out.println(random);
                    int randNum = list.get(random);

                    Place.getPlaceByID(randNum).addArtifact(new Torch(infile));

                }
                else if(placeID > 0)
                {
                    // if the place ID is positive, simply add it
                    Place.getPlaceByID( placeID ).addArtifact(new Torch(infile));
                }
            }

            if(spItemType.equalsIgnoreCase("FOOD")) 
            { 
                if(placeID < 0)
                {
                    // take the absolute value of the placeID from GDF and use it to get the place
                    Character.getCharacterByID((Math.abs(placeID))).addArtifact(new Food(infile));
                }
                else if(placeID == 0)
                {
                    // place an object with no specified location into a random position
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.addAll(Arrays.asList(12, 13, 23, 22, 21, 11, 101, 102, 103, 104, 105, 106, 107, 108));
                    int random = new Random().nextInt(list.size());
                    //System.out.println(random);
                    int randNum = list.get(random);

                    Place.getPlaceByID(randNum).addArtifact(new Food(infile));

                }
                else if(placeID > 0)
                {
                    // if the place ID is positive, simply add it
                    Place.getPlaceByID( placeID ).addArtifact(new Food(infile));
                }
            }
        }
        System.out.println("ALL info in");
        return;
    }


    // Print game information
    public void print(){
        for (Character c : characters)
        {
            System.out.println(c.getName());
        }
    }


    public void play() {

        System.out.println("Now playing the game " + name + ".");
        System.out.println("\n");

        if(Place.getSize() < 1)
           return;

        while (true) {

            for (Character players : characters)
            {
                players.makeMove();
            }
        }// end play()
    }


    //public static Place getCurrentPlace(){return currentPlace;}

    private int keywordCount(Scanner infile, String keyword){

        String line = CleanLineScanner.getCleanLine(infile);
        Scanner lineScanner = new Scanner(line);
        String word = lineScanner.next();
        if (!word.equalsIgnoreCase(keyword)) {
            System.err.println("Error parsing file. \"" + keyword + "\" not found");
            System.exit(-4);
        }

        int count = lineScanner.nextInt();
        if (count <= 0) {
                System.err.println("Error - Invalid counter found in \"" + line + "\"");
                System.exit(-5);
        }

        lineScanner.close();
        return count;
    }

    public static void setPosition(int i)
    {
        pArr.addElement(i);
    }

    public static int elementAt(int i)
    {
        return pArr.get(i);
    }

    public static int getSize()
    {
        return pArr.size();
    }
}

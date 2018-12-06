import java.util.*;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/*************************************************************************************
 *                                 Character class
 *
 * The character class is used in the creation of charaters used in the game.  This
 * class takes in a file and creates the player and non player character(NPC).
 *************************************************************************************/
public class Character
{
    // charater attributes below
    private int ID;
    private UI u;
    private AI a;
    protected DecisionMaker decisionMaker;
    private int healthBar;
    private String name;
    private String description;
    private Place currentPlace;
    private ArrayList < Artifacts > characterPossessions; 
    private boolean poisonStatus;

    private static HashMap < Integer, Character > character = new HashMap < Integer, Character >(); // hashmap used to quickly find player


    /* Constructor used in the creation of characters takes Scanner file and int */
    public Character( Scanner infile, int version )
    {
    	this.healthBar = 10;  
        this.poisonStatus = false;
        u = new UI();  // creates new UI
        a = new AI();  // creates new AI

        characterPossessions = new ArrayList < Artifacts >();  // sets memory for character possessions

        String line = CleanLineScanner.getCleanLine(infile);  // method call to return a clean line free of comments and extra lines
        int placeID = Integer.parseInt(line);

        if(placeID > 0)
        {
            this.currentPlace = Place.getPlaceByID(placeID);
        }
        else if(placeID == 0)
        {
            int randNum = (int) ((Math.random() * Game.getSize()) - 1);

            int p = Game.elementAt(randNum);
            this.currentPlace = Place.getPlaceByID(p);
        }
        line = CleanLineScanner.getCleanLine(infile);
        Scanner lineScanner = new Scanner(line);
        ID = lineScanner.nextInt(); 

        if(this.ID == 42)
        { 
          this.poisonStatus = true;
        } 

        lineScanner.skip("[ \t]*");
        name = lineScanner.nextLine();

        line = CleanLineScanner.getCleanLine(infile);
        lineScanner = new Scanner(line);

        /* get the number of lines for the description of the character and runs a loop for that amount of time */
        int nLines = lineScanner.nextInt();
        description = "";
        for (int i = 0; i < nLines; i++)
            description += CleanLineScanner.getCleanLine( infile ) + "\n";

        // add this character to the static collection
        character.put( ID, this );

        return;
    }


    /* constructor for used for manual entry of characters */
    public Character(int id, String name, String desc)
    {
        this.ID = id;
        this.name = name;
        this.description = desc;
    }


    /* method used to get the character from the hashmap based on the character ID */
    public static Character getCharacterByID(int id) { return character.get( id ); }


    /* method used to add artifacts to this character */
    public void addArtifact( Artifacts art )
    {
        characterPossessions.add( art );
        return;
    }


    /* method used to traverse the artifacts help by character and remove the one matching the string */
    public Artifacts removeArtifactByName( String s )
    {
        for (Artifacts a : characterPossessions)
        {
            if( a.match(s) )
            {
                characterPossessions.remove(a);
                return a;
            }
        }
        return null;
    }


    /* method to use an item contained by a character */
    public void useItem(String s)
    {
        for( Artifacts a : characterPossessions )
        {
            if( a.match( s ))
            {
                a.use( this );
                break;
            }
        }
        return;
    }


    /* method to update the characters current location in the world */
    public void updateLocation(Place location)
    {
        this.currentPlace = location;
    }


    /* method to return the place where the character is currently located */
    public Place getCurrentPlace() { return this.currentPlace; }


    /* method to return name of character */
    public String getName()
    {
        return this.name;
    }


    /* method to return the size of the container of the artifacts held by the character */
    public int getSize()
    {
        return characterPossessions.size();
    }


    /* method to print all the possessions held by the character */
    public void printPossesions()
    {
        System.out.println( "You are carrying: " );
        for( Artifacts a : characterPossessions )
            System.out.println( "\t" + a.name() );
        System.out.println( "Your Collection wieghs " +
                Artifacts.measureInventory( characterPossessions ) +
                " kilogram and is worth " +
                Artifacts.evaluateInventory( characterPossessions )+
                " gold coins." );
    }

    /* method used to make a move for the character checks to see if the character is NPC or Player */
    public void makeMove()
    {
        if (this instanceof Player)
        {
            Move m = u.getMove(this, currentPlace);  //calls UI
        }
        else if(this instanceof NPC)
        {
            Move m = a.getMove(this, currentPlace);  // calls AI
        }
    }


    /* print all pertinent information for the character */
    public void print()
    {
        System.out.println("Character ID: " + ID);
        System.out.println("Character Name: " + name);
        System.out.println("Character Description: " + description);
        System.out.println("Character Holds: ");
        for(Artifacts a : characterPossessions)
        {
            a.print();
        }
    }


    /* user friendly method used in the game */
    public void display()
    {
	if(this.poisonStatus)
        { 
          System.out.println( name + " is poisoned"); 
          System.out.println( "find the antidote"); 
        } 
        else
        { 
          System.out.println( name + " is currently healthy"); 
          System.out.println("watch out for poison"); 
        } 
        System.out.println( description );
        System.out.println("Current health " + healthBar); 
    }
    

    public int getHealthStatus()
    { 
      return this.healthBar;
    }  
  
    public void updateHealth(int incrementer)
    { 
      if(incrementer > 0) 
      { 
        this.healthBar += incrementer; 
      } 
      if(incrementer < 0)
      {  
        this.healthBar += incrementer; 
      } 
    } 

    public void updatePoisonStatus()
    { 
       this.poisonStatus = false;
    } 

    public boolean getPStatus()
    { 
      return this.poisonStatus; 
    } 

    public boolean inventoryCheck(String s) 
    { 
      for(Artifacts a : characterPossessions) 
      { 
        if(a.match(s)) 
	  return true; 
      }  
      return false;
    } 
} 

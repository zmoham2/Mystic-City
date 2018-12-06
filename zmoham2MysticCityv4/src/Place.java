import java.util.*;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
public class Place
{
    private int ID;
    private String name, description;
    private ArrayList < Direction > directions;
    private ArrayList < Artifacts > artifacts;
    private ArrayList < Character > characters;
    private static boolean firstPlace = true; 
    protected boolean illumination;

    private static HashMap < Integer, Place > places = new HashMap  < Integer, Place >();

    public Place( Scanner infile )
    { 
        illumination = true;
        // first to initialize the collections
        directions = new ArrayList < Direction >();
        artifacts = new ArrayList < Artifacts >();
        characters = new ArrayList < Character >();

        // read data from file
        String line = CleanLineScanner.getCleanLine(infile);
        Scanner lineScanner = new Scanner(line);
        ID = lineScanner.nextInt(); 
        Game.setPosition(ID);
        lineScanner.skip("[ \t]*");
        name = lineScanner.nextLine();

        line = CleanLineScanner.getCleanLine(infile);
        lineScanner = new Scanner(line);
        int nLines = lineScanner.nextInt();
        description = "";
        for(int i = 0; i < nLines; i++)
            description += CleanLineScanner.getCleanLine(infile) + "\n";

        // add this place to the static collection
        places.put(ID, this);

        // if the first place created, create the two psedo Places
        if(firstPlace)
        {
            firstPlace = false;
            new Place(1, "EXIT", "Pseudo place representing the exit");
            new Place(0,"Nowhere", "This place does not exist");
        }
        return;
    }


    // Place Consturctor
    public Place(int ID, String name, String description)
    {
        this.ID = ID;
        if(!(this.ID == 0 || this.ID == 1))
            Game.setPosition(this.ID);
        this.name = name;
        this.description = description;
        directions = new ArrayList < Direction >();
        artifacts = new ArrayList < Artifacts >();
        characters = new ArrayList < Character >();
        places.put(ID,this);
        return;
    }

    public static Place getPlaceByID( int id ){
        return places.get(id);
    }

    public String name() { return name; }

    public String description() { return description; }

    public void addDirection( Direction dir ) {
        directions.add( dir );
        return;
    }

    public void addArtifact( Artifacts art )
    {
        artifacts.add(art);
        return;
    }

    public Artifacts removeArtifactByName( String s )
    {
        for (Artifacts a : artifacts){
            if( a.match(s)){
                artifacts.remove(a);
                return a;
            }
        }
        return null;
    }

    public void addCharacter( Character c )
    {
        characters.add(c);
        return;
    }

    public void useKey( Artifacts a )
    {
        for( Direction d : directions )
        {
            d.useKey(a);
        }
    }


    public Place followDirection( String s )
    {
        for( Direction d : directions )
        {
            if( d.match(s) )
                return d.follow();
        }
        System.out.println( "You can not go that way" );
        return this;
    }

    public void print()
    {
        System.out.println("Place #" + ID + "-" + name);
        System.out.println(description);
        if( directions.size() > 0 )
        {
            System.out.println("This place has the following directions available");
            for( Direction d : directions )
                d.print();
        }
        else
        {
            System.out.println("This place has no directions");
        }

        if( artifacts.size() > 0 )
        {
            System.out.println("This place has the following artifacts");
            for( Artifacts a : artifacts )
                a.print();
        }
        return;
    }

    public void showArtifacts()
    {
        if( artifacts.size() > 0)
        {
            System.out.println("You see: ");
            for( Artifacts a : artifacts )
                System.out.println( a.name() + "-" + a.description() );
        }
    }

    public static int getSize()
    {
        return places.size();
    }

    public boolean isExit(){return ID == 1|| ID == 0;}

    public void display()
    { 
      if(illumination)
      {
        System.out.println(name);
        System.out.println(description); 
      }
      else
      { 
        System.out.println("The room is dark, you cannot see anything"); 
        System.out.println("Find a tool to light your path."); 
      }
    } 

    public void lightPlace() 
    { 
      this.illumination = true; 
    } 

    public boolean getLightStat() 
    { 
      return this.illumination; 
    } 
}

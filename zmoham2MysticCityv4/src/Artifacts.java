import java.util.ArrayList;
import java.util.Scanner;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/*************************************************************************************
 *                                 Artifacts class
 *
 * The artifacts class serves as the creator of all items in the game.
 *
 *
 *************************************************************************************/
public class Artifacts
{
    private int ID, value,mobility, keypattern;
    private String name, description;

    public Artifacts( Scanner infile )
    { 
        String line = CleanLineScanner.getCleanLine(infile);
        Scanner lineScanner = new Scanner(line);

        ID = lineScanner.nextInt();
        value = lineScanner.nextInt();
        mobility = lineScanner.nextInt();
        keypattern = lineScanner.nextInt();

        lineScanner.skip("[ \t]*");
        name = lineScanner.nextLine();

        line = CleanLineScanner.getCleanLine( infile );
        lineScanner = new Scanner(line);

        int nLines = lineScanner.nextInt();
        description = "";
        for (int i = 0; i < nLines; i++)
            description += CleanLineScanner.getCleanLine( infile )+ "\n";
        return;
    }


    /* method to return string */
    public String name() {return name;}


    /* method to return string */
    public String description(){return description;}



    // method that uses items that contained by the character
    public void use (Character c)
    {
        if(keypattern > 0)
        {
            c.getCurrentPlace().useKey(this); // method call to use this artifact
        } 
        return;
    }


    /* method that calculates how much everything the player carries worth */
    public static int evaluateInventory(ArrayList<Artifacts>stuff)
    {
        int total = 0;
        for (Artifacts a : stuff)
            total += a.mobility > 0 ? a.mobility:0;
        return total;
    }


    /* method that calculates how much everything the player carries affects mobility */
    public static int measureInventory(ArrayList<Artifacts>stuff)
    {
        int total = 0;
        for(Artifacts a : stuff)
            total += a.mobility > 0 ? a.mobility:0;
        return total;

    }


    /* method to check the lock pattern */
    public boolean keyFits(int lockpattern){
        return lockpattern == keypattern;
    }


    /* method to match string with this artifact */
    public boolean match(String s){
        return s.trim().equalsIgnoreCase(name);
    }


    /* method to print information for troubleshooting */
    public void print()
    {
        System.out.println("\""+name+"\"has ID "+ ID + ", value" +
                            value + ",mobility" + mobility +
                            "keypattern" + keypattern+ ":");
        System.out.println(description);
        return;
    }

}

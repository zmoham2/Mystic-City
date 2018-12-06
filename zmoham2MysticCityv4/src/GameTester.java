import java.io.*;
import java.util.*;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/*************************************************************************************
 *                                 GameTester class
 *
 * The GameTester
 *
 *
 *************************************************************************************/
public class GameTester
{
    public GameTester() { }

    public static void main(String[] args)
    {

        System.out.println("Homework 4 - Mystic City 5.1");
	System.out.println("Made by: Stephen Lambert (slambe7), Ramon Barajas (rbaraj3), Zoheb Mohammed (zmoham2)\n");

	String filename = "MysticCity51.gdf";
        if (args.length > 0)
        {
            filename = args[0];
        }

        Scanner infile = null;
        try
        {
            infile = new Scanner(new File(filename));
        }

        catch (FileNotFoundException e)
        {
            System.err.println("FILE Not Found: " + filename);
            System.exit(-3);
        }

        Game g = new Game(infile);

        g.print();
        System.out.println("Lets play");
        g.play();
    }
}

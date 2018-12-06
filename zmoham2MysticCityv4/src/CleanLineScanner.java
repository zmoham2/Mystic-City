import java.util.*;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/*************************************************************************************
 *                                 CleanLineScanner class
 *
 * The clean line scanner class is used in the input of a file for game creation.
 * the purpose is to return a clean line free of comments.  This class also makes
 * sure that we do not return emnpty lines back to the calling method.
 *
 *
 *************************************************************************************/

public class CleanLineScanner
{

    public CleanLineScanner() { }

    /* does what was described above */
    public static String getCleanLine( Scanner s )
    {
        String line;

        while( true )
        {
            if( !s.hasNextLine() )
                break;

            line = s.nextLine();

            int commentStart = line.indexOf("//"); // get the index of the comment start position

            if( commentStart == 0 )  // check to bypass empty line
                continue;

            if( commentStart > 0 )
                line = line.substring( 0, commentStart );  // get new string free of comments

            line = line.trim();

            if(line.length() > 0)  // check to make sure that we only return lines containing information
                return line;
        }
        return null;
    }
}

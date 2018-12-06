//
// Zoheb Mohammed
// zmoham2
// CS342 - Project 3
//

/* *************************************************************************************************************
 * Move Class                                                                                                  *
 * Provides the functionality for moving                                                                       *
 * Currently implements: enum class for basic moves and methods to manipulate + access them                    *
 * ************************************************************************************************************* */

public class Move {
    private MoveType type;
    private String argument;

    public enum MoveType {
        GO("GO"),
        LOOK("LOOK"), // UI
        EXIT("EXIT"), // UI
        QUIT("QUIT"), // UI
        INVE("INVE"), // UI
        GET("GET"),
        DROP("DROP"),
        USE("USE"),
    	EAT("EAT"),
    	DRINK("DRINK"), 
        LIGHT("LIGHT"); 

        MoveType(String argument) {
            this.command = argument;
        }

        private final String command;

        public boolean match(String s) {
            s = s.trim();
            return s.equalsIgnoreCase(command);
        }

        public static MoveType toEnum(String s)
        {
            for (MoveType type: MoveType.values())
            {
                if (type.match(s.toUpperCase()))
                    return type;

            }

            return MoveType.valueOf(s);
        }

        public String toString() {
            return command;
        }
    }


    public Move(String type, String argument) {
        this.type = MoveType.toEnum(type);
        this.argument = argument;
        //System.out.println(argument);
    }

    // this constructor is for commands that do not need an argument (e.g. LOOK)
    /*public Move(String type)
    {
        this.type = MoveType.toEnum(type);
    }*/

    // return argument
    public String getArgument()
    {
        return argument;
    }

    public void displayMessage()
    { 

    } 
}

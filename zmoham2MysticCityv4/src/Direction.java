//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * Direction Class                                                                                             *
 * Provides the functionality for directions                                                                   *
 * Currently implements: enum class for directions and methods to implement/manipulate directions              *
 * ************************************************************************************************************* */
import java.util.*;

public class Direction {

    private int ID, lockpattern;
    private Place from, to;
    private boolean locked;
    private DirType type;


    private enum DirType {

        N("North", "N"),
        W("West", "W"),
        S("South", "S"),
        E("East", "E"),
        U("Up", "U"),
        D("Down", "D"),
        NE("NorthEast", "NE"),
        NW("NorthWest", "NW"),
        SE("SouthEast", "SE"),
        SW("SouthWest", "SW"),
        NNE("North-NorthEast", "NNE"),
        NNW("North-NorthWest", "NNW"),
        ENE("East-NorthEAST", "ENE"),
        WNW("West-NorthWest", "WNW"),
        ESE("East-SouthEast", "ESE"),
        WSW("West-SouthWest", "WSW"),
        SSE("South-SouthEast", "SSE"),
        SSW("South-SouthWest", "SSW"),
        H("Help", "H"),
        NONE("NONE", "NONE");

        private final String text, abrev;

        DirType(String t, String a) {
            text = t;
            abrev = a;
        }

        public boolean match(String s) {
            if (this == NONE)
                return false;
            s = s.trim();
            return s.equalsIgnoreCase(text) || s.equalsIgnoreCase(abrev);
        }

        public String toString() {
            return text;
        }

    }


        public Direction(Scanner infile){
                String line = CleanLineScanner.getCleanLine(infile);
                Scanner lineScanner = new Scanner(line);

                ID = lineScanner.nextInt();

                int sourceID = lineScanner.nextInt();
                from =Place.getPlaceByID(sourceID);

                String dir  = lineScanner.next();
                type = DirType.NONE;
                for (DirType t : DirType.values()){
                    if(t.match(dir)){
                        type = t;
                        break;
                    }
                }

                locked = false;
                int destID = lineScanner.nextInt();
                if(destID <= 0){
                    locked = true;
                    destID = -destID;
                }
                to = Place.getPlaceByID(destID);

                lockpattern = lineScanner.nextInt();
                lockpattern = lockpattern > 0 ? lockpattern:0;

                from.addDirection(this);
                return;
        }


        public Place follow(){
            if(!locked)
                return to;
            System.out.println("That way is locked");
            return from;
        }


        void useKey(Artifacts a){
            if (lockpattern > 0 && a.keyFits(lockpattern))
                locked = !locked;
            return;
        }


        public boolean match(String s){
            return type.match(s);
        }


        // locking and unlocking
        public void lock(){locked = true; return;}
        public void unlock(){locked =false; return;}
        public boolean islocked(){return locked;}

        // Print directions
        public void print(){
            System.out.println("Direction " + ID +"travels "+ type + "from "+
                                from.name() + "to "+ to.name() + ",\n\t\tand is "+
                                "with a lock pattern of " + lockpattern);
            return;
        }



}

//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * Player Class                                                                                                   *
 * Provides the functionality for Player                                                                          *
 * Currently implements: constructor and decision maker for Player                                                *
 * ************************************************************************************************************* */

import java.util.*;

public class Player extends Character {

    // inherits from the Character class
    public Player (Scanner input)
    {
        super(input, 40);
        decisionMaker = new UI();
    }

    // inherits from the Character class
    public Player(int id, String name, String desc)
    {
        super(id, name, desc);
    }

}

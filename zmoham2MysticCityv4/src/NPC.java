//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * NPC Class                                                                                                   *
 * Provides the functionality for NPC                                                                          *
 * Currently implements: constructor and decision maker for NPC                                                *
 * ************************************************************************************************************* */

import java.util.*;

public class NPC extends Character {

    public NPC (Scanner input)
    {
        super(input, 40);
        decisionMaker = new AI();
    }

    public NPC(int id, String name, String desc)
    {
        super(id, name, desc);
    }


}

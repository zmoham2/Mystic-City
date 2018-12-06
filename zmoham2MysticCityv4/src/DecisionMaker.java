//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
/* *************************************************************************************************************
 * Decision Maker Interface                                                                                    *
 * Provides the interface for UI and AI                                                                        *
 * Currently implements: abstract method to be defined in UI and AI                                            *
 * ************************************************************************************************************* */

public interface DecisionMaker {

    public Move getMove(Character character, Place place);

}

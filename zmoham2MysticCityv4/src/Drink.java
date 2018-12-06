import java.util.*;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
public class Drink extends Move 
{ 
  public Drink(String type, String argument) 
  { 
    super(type,argument); 
  } 


  public void displayMessage() 
  { 
    System.out.println("You are no longer poisoned.  YAY!!!!!"); 
  } 
} 

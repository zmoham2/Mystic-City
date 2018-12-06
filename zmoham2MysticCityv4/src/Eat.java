import java.util.*;
public class Eat extends Move 
{ 
  public Eat(String type, String argument) 
  { 
    super(type,argument); 
  } 


  public void displayMessage() 
  { 
    System.out.println("Yummy yummy in the tummy"); 
  } 
} 

import java.util.*;
//Ramon Barajas - rbaraj3
//Stephen Lambert - slambe7
//Zoheb Mohammed - zmoham2
//CS 342 - 12:30 PM section
//Homework 4 - Group 17
public class Torch extends Artifacts 
{ 
  private boolean lit = false; 
  Torch(Scanner scanner)
  { 
    super(scanner);
  } 

  public void lightTorch(String s)
  { 
    this.lit = true; 
  }

  public boolean torchStatus() 
  { 
    return lit; 
  }
} 

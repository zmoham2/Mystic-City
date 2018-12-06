#Name:  Ramon A. Barajas
#ACCC:  rbarajas
#netID: rbaraj3
#Class: CS 342

JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Direction.java \
	Game.java \
	GameTester.java \
	Place.java \
	Artifacts.java \
	Character.java \
	CleanLineScanner.java \
	DecisionMaker.java \
	Move.java \
	NPC.java \
	Player.java \
	UI.java \
	AI.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class


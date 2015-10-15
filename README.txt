Author:					Yahel Nachum, Gregory Port, Cuong Nguyen, Duc Pham
Date:					10/15/2015
Version:				1.0
Project ID:				Assignment Final
CS Class:				CS 4341
Programming Language:	Java
OS/Hardware 
	dependencies:	
						The program requires very little resources to run the iterations 
						of the game.

Problem Description: 	To create an agent (Hero) successfully traverse random worlds 
						with different configurations

Overall Design:
	System structure	
		Game Engine
			GameManager
						This entity keeps track of the game world attributes such as 
						the game objects in the world, and the bounds of the game world.
			BasicObject
						This class defines the attributes and behavior of a basic object 
						in the game world. This will be extended for creating other game 
						objects to use in the game engine.
			GameWindow
						This boundary updates the window to display the current game 
						world state.
						
		Extended Game Engine Objects
						These objects extend the behavior of game engine objects.
			Hero
						This is the agent that learns to traverse the world by 
						exploring through the use of epsilon randomness and adding 
						the new state values using a temporal difference reinforcement 
						learning model (TD RL).
						
						Shows up in aqua and has the name Hero over it.
			Enemy
						This is a block where the Hero can die.
						
						Shows up in red and has the name Enemy over it.
			Wall
						This is a block where the Hero can not go over.
						
						Shows up in magenta and has the name Wall over it.
			BreadCrumb
						Shows the user of the program where the Hero has traversed 
						through the use of a diminishing color to black.
						
						Shows up in a light yellow to blackish color with the name BrCr 
						over it.

	Data representation 
						The TD RL model is represented by the class State. This class 
						takes in a position and calculates the state the Hero is in. 
						This state is used to store and update values in a hashmap 
						based on what the Hero is learing from the rewards function.

	Algorithms 			
						

Program Assumptions 
      and Restrictions:	
      					Some restrictions to the program is that it is not able to 
      					take in any input for making a game world. The game worlds 
      					that can be loaded are defined in the class Main.
      					
Interfaces:			
	User	
						This program does not take in any inputs so the user will 
						have to modify the class Main to create new game worlds and 
						speed up / slow down the iterations / fps / skipped game 
						step variables.
						
						The program outputs the information about the run through 
						the GameWindow which is a JFrame, the DrawingPanel that draws 
						the world out on the JFrame which is just a JPanel, and the 
						console to output the TD RL state table that has been filled 
						out up to that point.

Implementation Details:
	Data			
					The game objects are kept in an ArrayList in the GameManger. The 
					TD RL state values are kept in a hashmap stored statically in the 
					Hero object.
					
	Variables		
					The main game engine objects (GameManager, GameWindow, DrawingPanel) 
					are singletons to make accessing easier across classes.
	 
	Algorithm		
	
How to build the program:	To build the program you must take the following steps on the command line: 
							"(path to javac.exe)\javac.exe" -g src\*
							cd src
							java Main
							
							Ex.
							"C:\Program Files\Java\jdk####\bin\javac.exe" -g src\*
							cd src
							java Main

Program Source:			
	boundaries.graphics
					DrawingPanel	- draws the current state of the game world at that time
					GameWindow		- creates the window for the DrawingPanel to be inserted in
	entities.game.engine.base
					BasicObject		- the basic definition of what a game object 
									  is and what attributes it has
					Direction		- an enumeration of directions that are 
									  available in the game engine
					GameManager		- manages the game world and the objects in it
					Position		- defines the the x and y location of objects 
									  or game world positions
	entities.game.engine.extended
					BreadCrumb		- shows where the Hero has already traversed
					Enemy			- kills the Hero if the Hero steps on this object
					Goal			- where the Hero wants to end up in the end of the iterations
					Hero			- the agent that is supposed to learn to traverse the game world
					Move			- a possible move the Hero can perform, linked 
									  with the state the move will take the Hero 
									  in, and the current value of being in that state
					State			- the state that the Hero position can be in
					Wall			- an object that the Hero can not go on top of or through
	entities.utilities
					Clock			- keeps track of time for the fps
					Utility			- general game engine functions to transform positions
	main
					Main			- creates the game worlds and runs the Hero through them
									  in a number of defined iterations showing the output 
									  through the GameWindow and the console.

Results:
					Look at write-up for this assignment.

Performance Evaluation:
	Time/Space			
					The program generally takes about 2 - 5 minutes to go through enough iterations 
					for the Hero to perform successfully almost every time. In terms of space 
					usage, the game world takes up more space as more objects are in it.
						
	User Interface	
					The program does not have a very good user interface since the user has to go 
					into the Main class file to change display of iterations, fps, and game steps.

References:			Class book, class slides, Professor Beck for help with TD RL, Current IMGD 3000 
					class for designing a game engine.

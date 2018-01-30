Design Plan for Cell Society
=================

## Introduction
This program is intended to be a platform for computer simulations of different types of Cellular Automata models such as the Spread of Fire model and Game of Life model.

* Design Goals
	* The user could choose a simulations from several simulations already included in the program, read in starting configurations (e.g. probability of a cell changing its state, grid size) from an XML file and start the simulation.
	* During the simulation, the user could manipulate parameters to simulate different models.
	* The structure of the program will allow developers to easily add other types of simulations into the program.

* Design Architecture
	* The possible choices of simulations and their rules are preset in the program.
	* The structure of the program will allow developers to easily add other types of simulations.
	* The starting grid size, simulation speed and probability of change are read from an XML file. During the simulation, the user could also change the above values.

## Overview
* Main
	* Main class extends Application class. It is the executable class of the program.
	* Instance variables:
		* A 2D array of Cells
			* With this 2D array of Cells where there is an extra layer of empty cells that serves as neighbors of edge cells, the program could pass neighboring cells to decide how a cell should evolve.
			* The grid is drawn using location and size information stored in Cells in the 2D array.
	* Methods:
		* read and initialize: Main could read from an XML file (this action is done by the Reader class) the initial configurations and using the information to determine which simulation to simulate and initialize the 2D array of Cells accordingly.
		* start: Main starts the simulation by going over a lot of steps until no cells could be updated.
		* pause (stop): Main pauses the current simulation.
		* step: Main evolves all Cells in the 2D array to its next state, calls Simulation.evolve().
	*	User Interface:
		*	Main initializes and accounts for all the interactions of the user interface.
* Simulation
	* Instance variables:
		* A parameter value (such as probCatch in Spread of Fire model)
		* The size of the grid used in initializing the 2D Cell Array
	* Methods:
		* Abstract static method evolve()
			* evolve() contains and implements the "rules" of the simulation. It accepts inputs of the target cell and determines the next state by applying the rules to the neighbors of target cell.
			* It is implemented in the subclasses of Simulation.
		* Abstract method initialize()
			* initialize() creates the 2D Cell array needed for the simulation and returns it to Main class
			* It is implemented in the subclasses of Simulation.
	* Subclasses of Simulation
		* All types of simulations are implemented as subclasses of Simulation. evolve() is implemented in these subclasses.
		* Example 1: GameOfLife
			* GameOfLife implements evolve() and initialize() using the rules of Conway's Game of Life
		* Example 2: SpreadOfFire
			* SpreadOfLife implements evolve() and initialize() using probCatch read from input configuration
* Cell
	* Cell is the superclass of all different subclasses of cells in different simulations.
	* Instance variables:
		* A Rectangle to graphically show the grid
		* x and y coordinates of the Cell stored as doubles.
		* isEmpty: boolean value to tell whether the Cell is empty
		* A list of Cells that are the neighbors of this Cell
	* Subclasses of Cell
		* Empty:
			* Empty is the empty cell in all simulations.
			* Empty fills in the missing neighbors for an edge cell.
		* Example: LiveCell and DeadCell
			* LiveCell and DeadCell represents two kinds of Cells in the GameOfLife simulation. They would be the objects in the 2D array of Cells if GameOfLife simulation is selected.




Jamie
![Game Design Drawing 1](doc/Screen Shot 2018-01-29 at 10.33.00 PM.png "Game Design 1")
## User Interface
As drawn below, the top of the screen will be the simulation, and the bottom of the screen will be the user interface. A user can choose a type of simulation, scroll through the probability and the grid size. We will have three different simulations - fire simulation, biological simulation, and segregation simulation. Scroll bar for probability will be between 0 and 1. The grid size can vary from 10x10 to 50x50. Another scroll bar will be for the user to delay the time between each step. There are also buttons that starts, resets, stops, steps through, and loads a new configuration file.   
As user steps through each frame, the number of rounds, right beneath the simulation, will be updated. Lastly, a user can load a new configuration file, which stops the current simulation and starts the new one. Since we have designed our user interface in a such a way that a user is not reporting inputs to the system, there will not be miscellaneous erroneous situations. 

![Game Design Drawing 2](doc/Screen Shot 2018-01-29 at 10.33.13 PM.png "Game Design 2")
## Design Details 
This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.

The XML file will be handled by a separate reader class that takes the file as its parameter. The reader will return information to Main, which would determine which Simulation to use and initialize the 2D array of Cells using the initialize() function implemented in that specific subclass of Simulation.
After reading the file and initializing the cell, step() function that contains the loop of updating the cells will be executed. Since we have a 2D array, there will be a double for loop that iterates through each row and column. It will statically call Simulation.evolve() to check the states of neighbors of each cell and update the Cells accordingly. When the for loop is completed, every cell will be updated to the next new state. 
The abstract Simulation class will have two instance variables and two abstract methods as shown in the picture above in the overview section. The abstract initialize() method will output the 2D array of Cells used as the grid in the Main class. The abstract evolve() method will be defined in each subclass to update the a specific cell. Since the two methods vary for each simulation, they are abstract methods.
The Cell class will have subclasses implementing possible states of Cells in different simulations. Each Cell would have a list of neighboring Cells as one of its instance variables. Each subclass would have a boolean flag to indicate its type.

__Use Cases__

* Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
* Each cell has a boolean flag that determines whether is is alive or dead. Game of Life’s own evolve() method contains rules that are logic defined to determine the next state of the target cell by considering the death/life of neighboring cells. The rules will be implemented differently in each of the subclasses of Simulation. The neighbors will be accessed as an instance variable array in Cell class.

* Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
* Since we have included an extra layer of Empty cells around the edge of the grid, we do not have to differentiate edge cells from middle cells. The evolve() methods will account for cases where some neighbors are Empty cells, i.e. not count Empty cells in Game Of Life simulation.


Ryan
Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
Loop through every 2D cell and either turn the block on visually (if it’s next state is live) or turn the block off (if it’s next stated is dead). This means for each cell a graphic node (e.g. rectangle/square) at the cell’s x,y coordinate needs to be either black filled (for live) for white filled (for dead).  

Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire
To deal with parameter changes during the simulation, for instance when a GUI bar is moved.  First, the simulation calls the stop() method, instantiated for the specific simulation type.  Then the simulation parameters are modified, and start() is called again.

Switch simulations: use the GUI to change the current simulation from Game of Life to Water
This is basically a restart of the simulator.  Load the parameters for the specific simulation.  Initialize your cell grid.  And call the Simulation.evolve().

   

Design Considerations 
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.
A design trade-off was made between a few methods (e.g. init() and evolve()) versus more, smaller methods.  We chose just the two large methods init/evolve since it makes the flow of the main program simpler to understand/implement. 
When loading XML files that describe a simulation, the design can either re-read the file every time the simulation changes, or the data can be read only once and saved.  Re-reading is inefficient to execute, but easier to code (just call the reader method each time).  Rereading is slower, but doesn’t require a mechanism for storing all the parameters associated with the simulation.  For this design we choose the inefficient but easy to implement and more flexible re-read mechanism.
The main loop really does two things: calculate the new cell values and render the new picture.  This can be designed/implemented as either one function or two.  For instance we chose just one function (evolve()), that both calculates the new cell values and renders the new picture.  We could have two functions evolve/render separately.  For simplicity of understanding the main code flow this is just one function (evolve).
Which parameters can be changed mid-simulation and what is there effect requires some decisions.  Some parameters can be changed without restarting starting the simulation (like probability).  Some parameters required basically a restart -- grid size and simulation type both require a restart of the simulation state.
When defining the XML input file, how is the starting state specified?  You can specify each blocks value (on or off), just specify the on blocks, just specify the off blocks, or only allow random on/off.  Our plan is to have an XML variable that specifiies whether the user is going ro provide On list, off list, start random.  E.g. if on list, the xml provides the x,y coordinates for every cell that is on.  If off list, then provide cells that are off.  If random is selected, then the on/off setting is random for all cells.

Team Responsibilities
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.
Jamie Kim - Grid class and one other simulation.
Siyuan Chen - UI and Fire
Ryan Suggs - Reader class and Game of Life simulation.

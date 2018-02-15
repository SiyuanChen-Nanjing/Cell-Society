
Cell Society Design
===================

* High-Level Design:
	* Implement a platform for simulating cellular automata simulations. 
	* The platform would be flexible for developers to add new simulations. 
	* Each type of Simulation would have its own rules to implement.
* How to add a new feature:
	* To add a new simulation, first create another subclass of the Simulation class and implements its own initialize and evolve methods based on its unique rules. At the same time, create the classes representing its cell types as subclasses of Cell.
	* In addition, update the XMLReader to recognize the added simulation and read the needed information in order to create a Simulation object. The setupUI method also need a little update to include its unique slider.
* Design Choices
	* **Grid**
		* The grid is designed to be a variable in the Simulation object instead of in its own class.
		* Pro: When creating and accessing a Simulation and its grid, their link is easily accessed so that the XMLReader class could create both the grid and the simulation details at the same time, and the grid could be added to the scene via the simulation.
		* Con: The flexibility of implementing different shapes is strictly compromised.
		* I would put the grid in its own class if I had known that we needed to implement different types of grid.
	* **UI**
		* Most of the UI currently resides in the Main class instead of in its own class.
		* We had thought about putting the UI into its own class, but some of the UI need to access Timeline, Stage or Scene objects in Main. These variables are essential elements of an animation and we decided that the UI should be inside Main to avoid troublesome modification of Timeline and other variables.
* Assumptions made:
	* Some variables of simulations is preset and cannot be modified by the user for simplicity.
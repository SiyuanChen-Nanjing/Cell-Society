## Inheritance Review
by Siyuan Chen, Eric Fu, Frank Yin and Jing Yang
* Part 1
	* What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program? 
	
	Cell states can only be updated within its own class, but not within any other classes. The parameter for a simulation could only be initialized in its own class. 

	* What inheritance hierarchies are you intending to build within your area and what behavior are they based around?

	 Calculation class, and grid class are designed to super abstract classes and several subclasses will be written with hierarchies. 
	Cell and Simulation are two superclasses. XXXSimulation extends Simulation and implements its own rules to update the cells.

	* What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?
	
	Simulation class and cell class is closed for modification. Basic functions of grid class are also closed for modification. 
	
	* What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
	
	FileNotFound exception, XML Format exception, parameter format exception (wrong grid size, wrong parameter range, etc.). 

	* Why do you think your design is good (also define what your measure of good is)? 

	Good: easy to understand, flexible for extension and modification, elegent coding 
	
	Our design is good because it fulfills the above requirements to a certain extent. 
	
* Part 2
	* How is your area linked to/dependent on other areas of the project?

	Grid class is linked to cell class and simulation class. Calculation class is linked to grid class. Simulation class is linked to grid class, and calculation class. 
	* Are these dependencies based on the other class's behavior or implementation?

	From our current perspective on the program, few dependencies exist in our code. The only ones occur if new implementations are added to the program, for which new subclasses and switch cases are added to adapt this change.
	
	* How can you minimize these dependencies? 
	* Go over one pair of super/sub classes in detail to see if there is room for improvement. 
	* Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
* Part 3
	* Come up with at least five use cases for your part (most likely these will be useful for both teams).
	* What feature/design problem are you most excited to work on?
	* What feature/design problem are you most worried about working on?

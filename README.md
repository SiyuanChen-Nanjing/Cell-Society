## Cell Society Team 4

CompSci 308 Cell Society Project
by Siyuan Chen, Jamie Kim and Ryan Suggs

* This project is started on Jan. 29th and finished on Feb. 12th. The estimate number of hours spent is about 50 hours.
* Roles of each author:
	* Siyuan: 
		* Sprint 2: implemented Simulation and Cell superclasses, implemented four basic simulations, implemented XML reader and UI
		* Sprint 3: implemented graph and dynamic slider UIs, implemented feature: write current grid to XML, read grid configuration from XML, error checking
	* **Jamie:** 
	* **Ryan:** 
	    * Sprint 2: Helped with XML reader and UI.
	    * Sprint 3: Worked on Grid Edge Types (e.g. Toroidal) and UI components.
* Sources: Java API
* File used to start the file: /src/main/Main.java
* Error Checking
	* Check if the input xml has a type indicated, or the type indicated is recognized by the program; throw error dialog and ask the user to select another file if not;
	* Throw error dialog when no file is indicated in the Load and Record buttons;
	* Throw error dialog when the cell state read is not applicable to the current simulation type
* Data needed:
	* ButtonText.properties in src/assets
	* Test XML files in /data
	* Note: Fire_test.xml requires reading another initial configuration XML file, Fire_record_test.xml
* Current bugs:
	* Grid size would not change unless timeline is unpaused.
	* Java will print error trace when no file is chosen.
* Impression of the project: 
	* I understand now how much flexibility could help when adding new features...
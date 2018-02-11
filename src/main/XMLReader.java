package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import simulations.Fire;
import simulations.GameOfLife;
import simulations.Segregation;
import simulations.Simulation;
import simulations.WaTor;

public class XMLReader {
	
	/**
	 * Using the Java DOMParser to parse an XML file into a Document
	 * @param file XML configuration file
	 * @return Document object containing the XML tree
	 */
	public static Document read(File file) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(file);
	}
	
	/**
	 * Get the title of the simulation
	 * @param file XML configuration file
	 * @return title of the simulation
	 */
	public static String getTitle(File file) throws SAXException, IOException, ParserConfigurationException {
		Document doc = read(file);
		return doc.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
	}
	
	/**
	 * Create a Simulation from XML file
	 * @param file XML configuration file
	 * @return the Simulation object created using the information in the XML file
	 */
	public static Simulation setupSimulation(File file) throws SAXException, IOException, ParserConfigurationException {
		Document doc = read(file);
		String type = doc.getElementsByTagName("type").item(0).getFirstChild().getNodeValue();
		int size = Integer.parseInt(doc.getElementsByTagName("size").item(0).getFirstChild().getNodeValue());
		if (type.equals("GameOfLife")) {
			double ratio = Double.parseDouble(doc.getElementsByTagName("aliveDeadRatio").item(0).getFirstChild().getNodeValue());
			GameOfLife gol = new GameOfLife(size);
			gol.setRatio(ratio);
			gol.initialize();
			return gol;
		}
		else if (type.equals("Fire")) {
			double probCatch = Double.parseDouble(doc.getElementsByTagName("probCatch").item(0).getFirstChild().getNodeValue());
			Fire fire = new Fire(size);
			fire.setProbCatch(probCatch);
			fire.initialize();
			return fire;
		}
		else if (type.equals("Segregation")) {
			double emptyPercent = Double.parseDouble(doc.getElementsByTagName("emptyPercent").item(0).getFirstChild().getNodeValue());
			double ratio = Double.parseDouble(doc.getElementsByTagName("redBlueRatio").item(0).getFirstChild().getNodeValue());
			double satisfaction = Double.parseDouble(doc.getElementsByTagName("satisfaction").item(0).getFirstChild().getNodeValue());
			Segregation seg = new Segregation(size);
			seg.setEmptyPercent(emptyPercent);
			seg.setRatio(ratio);
			seg.setMyMinSatisfaction(satisfaction);
			seg.initialize();
			return seg;
		}
		else if (type.equals("WaTor")) {
			double emptyPercent = Double.parseDouble(doc.getElementsByTagName("emptyPercent").item(0).getFirstChild().getNodeValue());
			double ratio = Double.parseDouble(doc.getElementsByTagName("fishSharkRatio").item(0).getFirstChild().getNodeValue());
			int fishReproduce = Integer.parseInt(doc.getElementsByTagName("fishRoundsToReproduce").item(0).getFirstChild().getNodeValue());
			int sharkReproduce = Integer.parseInt(doc.getElementsByTagName("sharkRoundsToReproduce").item(0).getFirstChild().getNodeValue());
			WaTor wator = new WaTor(size);
			wator.setEmptyPercent(emptyPercent);
			wator.setRatio(ratio);
			wator.setReproductionRounds(fishReproduce, sharkReproduce);
			wator.initialize();
			return wator;
		}
		else {
			throw new IllegalArgumentException("Your input simulation type is not implemented.");
		}
	}
	
	//public static List<List<Cell>> readGrid();
}

package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import simulations.GameOfLife;
import simulations.Segregation;
import simulations.Simulation;
import simulations.WaTor;

public class XMLReader {
	
	public static Document read(String filename) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new File(filename));
	}
	
	public static Simulation setupSimulation(String filename) throws SAXException, IOException, ParserConfigurationException {
		Document doc = read(filename);
		String type = doc.getElementsByTagName("type").item(0).getFirstChild().getNodeValue();
		int size = Integer.parseInt(doc.getElementsByTagName("size").item(0).getFirstChild().getNodeValue());
		if (type.equals("GameOfLife")) {
			double ratio = Double.parseDouble(doc.getElementsByTagName("aliveDeadRatio").item(0).getFirstChild().getNodeValue());
			GameOfLife gol = new GameOfLife(size);
			gol.setRatio(ratio);
			return gol;
		}
		/**
		else if (type.equals("Fire")) {
			double probCatch = Double.parseDouble(doc.getElementsByTagName("probCatch").item(0).getNodeValue());
			Fire fire = new Fire(size);
			fire.setProbCatch(probCatch);
			return fire;
		}
		**/
		else if (type.equals("Segregation")) {
			double emptyPercent = Double.parseDouble(doc.getElementsByTagName("emptyPercent").item(0).getNodeValue());
			double ratio = Double.parseDouble(doc.getElementsByTagName("redBlueRatio").item(0).getNodeValue());
			double satisfaction = Double.parseDouble(doc.getElementsByTagName("satisfaction").item(0).getNodeValue());
			Segregation seg = new Segregation(size);
			seg.setEmptyPercent(emptyPercent);
			seg.setRatio(ratio);
			seg.setMyMinSatisfaction(satisfaction);
			return seg;
		}
		else if (type.equals("WaTor")) {
			double emptyPercent = Double.parseDouble(doc.getElementsByTagName("emptyPercent").item(0).getNodeValue());
			double ratio = Double.parseDouble(doc.getElementsByTagName("fishSharkRatio").item(0).getNodeValue());
			int fishReproduce = Integer.parseInt(doc.getElementsByTagName("fishRoundsToReproduce").item(0).getNodeValue());
			int sharkReproduce = Integer.parseInt(doc.getElementsByTagName("sharkRoundsToReproduce").item(0).getNodeValue());
			WaTor wator = new WaTor(size);
			wator.setEmptyPercent(emptyPercent);
			wator.setRatio(ratio);
			wator.setReproductionRounds(fishReproduce, sharkReproduce);
			return wator;
		}
		else throw new IllegalArgumentException("Your input simulation type is not implemented.");
	}
	
}

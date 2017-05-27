package sudoku;

import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 * Kiolvassa az adatbázisból a Sudoku soron következő tábláját.
 * 
 * @author plkar
 *
 */
public class ReadTable implements ReadTableInterFace {
	String path;

	/**
	 * Konstruktor egy {@code ReadTable} objektum létrehozásához.
	 * 
	 * @param path az adatbázis elérési útvonala.
	 */
	public ReadTable(String path) {
		super();
		this.path = path;
	}
	
	/* (non-Javadoc)
	 * @see sudoku.ReadTableInterFace#returntable(int)
	 */
	@Override
	public int[][] returntable(int tableCounter){
		
		int matrix [][]=new int[9][9];
		try{
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
			String inputFile = path;
			
			Document doc = dbuilder.parse(inputFile);
			NodeList tablelist = doc.getElementsByTagName("table");
			Node table = null;
			try {
				table = tablelist.item(tableCounter);
			} catch (Exception e) {e.printStackTrace();}
			Element element = (Element) table;
			String elem;
			int counter = 0;
			for(int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++) {
					elem = element.getElementsByTagName("column").item(counter).getTextContent();
					matrix[i][j] = Integer.parseInt(elem);
					counter++;
				}
			}
			
		} catch (Exception e) {e.printStackTrace();}
	
		return matrix;
	}
	
	/* (non-Javadoc)
	 * @see sudoku.ReadTableInterFace#tableNumber()
	 */
	@Override
	public int tableNumber() {
		NodeList tablelist = null;
		try{
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
			String inputFile = path;
			
			Document doc = dbuilder.parse(inputFile);
			tablelist = doc.getElementsByTagName("table");
		}
		catch (Exception e) {e.printStackTrace(); }
		return tablelist.getLength();
	}

}
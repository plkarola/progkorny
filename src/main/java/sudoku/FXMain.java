package sudoku;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Osztály a grafikus felület kezeléséhez.
 * 
 * @author plkar
 *
 */
public class FXMain extends Application {
	
	
	/**
	 * Jelzi mely elemek miatt nem elfogadható az adott pozicióra az adott számjegy.
	 * 
	 * @param table a táblát tartalmazó grafikus elem
	 * @param engine a játék logikája
	 */
	public void errored(GridPane table , Engine engine){
				 ObservableList<Node> childrens = table.getChildren();
				for (Node node : childrens) {
					if(!engine.scan(engine.matrix[table.getRowIndex(node)][table.getColumnIndex(node)],  table.getRowIndex(node),table.getColumnIndex(node)))
			        node.setStyle("-fx-control-inner-background:red;  -fx-padding: 1; -fx-pref-width: 3em; -fx-pref-height: 3em;");	
					else
					node.setStyle("-fx-control-inner-background:beige;  -fx-padding: 1; -fx-pref-width: 3em; -fx-pref-height: 3em;");
				}
			
				
	}
	
	
	static Logger logger = LoggerFactory.getLogger(FXMain.class);
	int tablecounter = 0;
	
	boolean end = false;
	@Override
	public void start(Stage primaryStage) {
		ReadTableInterFace RT = new ReadTable(getClass().getClassLoader().getResource("Tables/elsotabla.xml").toString());
		Engine engine = new Engine(RT.returntable(tablecounter));
		GridPane table = new GridPane();
		logger.info("Pálya vázának létrehozása a grafikus felületen.");
		String num="";
		logger.info("Tábla kiolvasása." + getClass().getClassLoader().getResource("Tables/elsotabla.xml").getFile().toString());
		logger.info("Pálya beállitása elméletben.");
		logger.info("Pálya beállitása a grafikus felületen.");
		final Pattern pattern = Pattern.compile("\\d*");
		logger.info("Minta létrehozása: csak számjegyeket lehessen beirni.Tetszőlegesen sokat.");
		for ( int i=0; i<9; i++) {
			for ( int j=0; j<9; j++) {
				final int ii =i;
				final int jj =j;
				table.setStyle("-fx-control-inner-background:beige;  -fx-padding: 1;");
				num = Integer.toString(engine.matrix[j][i]);
				final TextField number = new TextField(num);
				logger.info("Cella létrehozása.");
				number.textProperty().addListener(new ChangeListener<String>() {
					public void changed(final ObservableValue<? extends String> observ, final String oldvalue, String newvalue ){
						end=false;
						if (pattern.matcher(newvalue).matches()) {
							logger.info("Minta érvényesitése: csak számjegyeket lehessen beirni a cellákba.");
							if(newvalue.length()>-1) { 
								try{newvalue =newvalue.substring(newvalue.length()-1, newvalue.length());
								Integer.parseInt(newvalue);
								}catch (Exception e) {
									newvalue="0";
								}
								
									number.setText(newvalue);
								logger.info("[" + jj + "," + ii + "] poziciora: Ha több számjegy kerül a cellába, az utolsó számjegyet tekinti érvényesnek.");
								
							}
							
						}
						else {
							number.setText(oldvalue);
							logger.info("Nem számjegy került beirásra, ezért megtartja az eddig cellában levő értéket.");
						}		
						try {
						engine.insert(Integer.parseInt(newvalue), jj, ii);
						} catch (Exception e) {;}
						
						
							if(engine.verify() && !end) {
								end = true;
								if (RT.tableNumber() > tablecounter+1) {
									tablecounter++;;
									start(primaryStage);
									try {
										Thread.sleep((long)30);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Pálya vége");
									alert.setHeaderText("Nyeremény");
									alert.setContentText("Nyertél. Kezdheted a következő pályát.");
									alert.show();
									logger.info("Adott pálya sikeresen befejezve. Következő pálya inditása.");
								}
								else 
								{
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Játék vége");
									alert.setHeaderText("Nyeremény");
									alert.setContentText("Nyertél. Befejezted az összes pályát.");
									alert.show();
									logger.info("Összes pálya sikeresen befejezve.");
									primaryStage.close();
								}
							}
							errored(table , engine);
					}
				});
				if (engine.matrix[j][i] != 0) {
					number.setEditable(false);
					logger.info("A pálya alapból kitöltött mezőinek játékos általi irását letiltja.");
				}
				number.setId(Integer.toString(i)+Integer.toString(j));
				logger.info("Cella megkapja ID-ként a mátrix "+ i + "sorát, " + j + "oszlopát.");
				number.setStyle("-fx-pref-width: 3em; -fx-pref-height: 3em;");
				number.setAlignment(Pos.CENTER);
				GridPane.setConstraints(number, i, j);
				table.getChildren().add(number);
				logger.info("A pálya vázához hozzáadja a cellát.");
			}
		}
		primaryStage.setScene(new Scene(table));
		logger.info("Scene beállitása.");
		primaryStage.show();
		logger.info("primaryStage megjelenitése.");
	}
	
	/**
	 * Main metódus.
	 * 
	 * @param args parancssori argumentumok
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

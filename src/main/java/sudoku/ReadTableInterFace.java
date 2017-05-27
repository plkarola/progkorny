package sudoku;

public interface ReadTableInterFace {

	/**
	 * Az adatbázisból kiolvassa az éppen következő táblát.
	 * 
	 * @param tableCounter megadja hanyadik tábla következik
	 * @return az adatbázis {@code tableCounter} +1. táblája 
	 */
	int[][] returntable(int tableCounter);

	/**
	 * Megszámolja hány táblánk van az adatbázisban.
	 * 
	 * @return hány táblánk van az adatbázisban
	 */
	int tableNumber();

}
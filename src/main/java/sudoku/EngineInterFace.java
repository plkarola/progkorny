package sudoku;

public interface EngineInterFace {

	/**
	 * @return viszzadja az aktuális táblát
	 */
	int[][] getMatrix();

	/**
	 * Beállítja az aktuális táblát.
	 * 
	 * @param matrix a tábla, amivel játszani szeretnénk
	 */
	void setMatrix(int[][] matrix);

	/**
	 * Feltölti a mátrixot, ami tárolja, hogy mely pozíción levő számjegyek voltak eleve adottak a táblában.
	 * Ezeket a játékos nem módosíthatja.
	 * 
	 * @param matrix a tábla kezdőállapota
	 */
	void init(int[][] matrix);

	/**
	 * Kitörli a táblából az adott pozíción levő számot.
	 * 
	 * @param pos_x törölni kívánt pozíció X koordinátája
	 * @param pos_y törölni kívánt pozíció Z koordinátája
	 * 
	 * @return az adott pozícióról sikerült-e törölni az elemet
	 */
	boolean delete(int pos_x, int pos_y);

	/**
	 * A tábla megfelelő pozíciójára elhelyezi a kívánt számot.
	 * 
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozíció X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozíció Y koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * 
	 * @return az adott pozícióra sikerült-e beírni a kívánt elemet
	 */
	boolean insert(int number, int pos_x, int pos_y);

	/**
	 * Visszaadja, hogy a beírni kívánt szám elfogadható-e az adott pozícióra. Ellenőrizve ezt soron, oszlopon, és 3x3-as táblákon.
	 * 
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozíció X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozíció Y koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @return az adott szám elfogadható-e az adott pozícióra
	 */
	boolean scan(int number, int pos_x, int pos_y);

	/**
	 * Ellenőrzi, hogy a tábla teljesen, és a szabályoknak megfelelően van-e feltöltve.
	 * 
	 * @return a tábla helyessége
	 */
	boolean verify();

	/**
	 * Kiírja az aktuális táblát.
	 */
	void write();

}
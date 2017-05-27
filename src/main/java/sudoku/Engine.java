package sudoku;

/**
 * A játék alapvető logikáját tartalmazza.
 * 
 * @author plkar
 */
public class Engine {


	int matrix [][]=new int[9][9];
	int fix [][] = new int[9][9];
	int i,j;
	
	/**
	 * Konstruktor egy {@code Engine} objektum létrehozásához. Adott táblával.
	 * 
	 * @param matrix a sudoku tábla tárolása mátrixban, ezen mátrix átadása az {@code Engine} példányának
	 */
	public Engine(int[][] matrix) {
		this.matrix = matrix;
		init(matrix);
	}

	/**
	 * Konstruktor egy {@code Engine} objektum létrehozásához. Tábla megadása nélkül.
	 */
	public Engine() {
		super();
	}

	/**
	 * @return viszzadja az aktuális táblát
	 */
	public int[][] getMatrix() {
		return matrix;
	}

	/**
	 * Beállítja az aktuális táblát.
	 * 
	 * @param matrix a tábla, amivel játszani szeretnénk
	 */
	public void setMatrix(int[][] matrix ) {
		this.matrix = matrix;
		init(matrix);
	}

	/**
	 * Feltölti a mátrixot, ami tárolja, hogy mely pozíción levő számjegyek voltak eleve adottak a táblában.
	 * Ezeket a játékos nem módosíthatja.
	 * 
	 * @param matrix a tábla kezdőállapota
	 */
	public void init(int[][] matrix) {
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				if (matrix[x][y] != 0) { fix[x][y] = 1; }
			}
		}
	}
	
	/**
	 * Kitörli a táblából az adott pozíción levő számot.
	 * 
	 * @param pos_x törölni kívánt pozíció X koordinátája
	 * @param pos_y törölni kívánt pozíció Z koordinátája
	 * 
	 * @return az adott pozícióról sikerült-e törölni az elemet
	 */
	public boolean delete(int pos_x, int pos_y) {
		if (fix[pos_x][pos_y] == 0 ) {
			matrix[pos_x][pos_y] = 0;
			return true;
		}
		return false;
	}
	
	/**
	 * A tábla megfelelő pozíciójára elhelyezi a kívánt számot.
	 * 
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozíció X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozíció Y koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * 
	 * @return az adott pozícióra sikerült-e beírni a kívánt elemet
	 */
	public boolean insert(int number, int pos_x, int pos_y) {
		if ((fix[pos_x][pos_y] == 0 )) {
			matrix[pos_x][pos_y] = number;
			return true;
		}
		return false;
			
	}
	
	/**
	 * Visszaadja, hogy a beírni kívánt szám elfogadható-e az adott pozícióra. Ellenőrizve ezt soron, oszlopon, és 3x3-as táblákon.
	 * 
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozíció X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozíció Y koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @return az adott szám elfogadható-e az adott pozícióra
	 */
	public boolean scan(int number, int pos_x, int pos_y) {
		if ((row(number, pos_x, pos_y) && column(number, pos_x, pos_y) && matr(number, pos_x, pos_y)) || number==0)
			return true;
		else 
			return false;
	}
	
	/**
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozició X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozició X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @return a szám szerepel-e az X koordinátájú sorban. 
	 */
	private boolean row(int number, int pos_x, int pos_y) {
		for(int x=0; x<9; x++) {
			if (matrix[pos_x][x] == number && x!=pos_y) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozició X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozíció Y koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @return a szám szerepel-e az Y koordinátájú oszlopban.
	 */
	private boolean column(int number, int pos_x, int pos_y) {
		for(int y=0; y<9; y++){
			if (matrix[y][pos_y] == number && y!=pos_x) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param number a játékos által beírni kívánt számjegy
	 * @param pos_x azon pozíció X koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @param pos_y azon pozíció Y koordinátája, ahova a játékos be szeretné írni a számjegyet
	 * @return a {@code number} szerepel-e abban a 3x3-as táblában, amelyben a koordinátákkal jelölt cella megtalálható.
	 */
	private boolean matr(int number, int pos_x, int pos_y) {
		int osztas_x, osztas_y, x, y;
		
		osztas_x = (pos_x / 3) * 3;
		x = osztas_x;
		osztas_y = (pos_y / 3) * 3;
		y = osztas_y;
		for(x = osztas_x ; x < (osztas_x+3); x++) {
			for(y = osztas_y; y < (osztas_y+3); y++) {
				if(matrix[x][y] ==number && (x!=pos_x && y!=pos_y) )
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Ellenőrzi, hogy a tábla teljesen, és a szabályoknak megfelelően van-e feltöltve.
	 * 
	 * @return a tábla helyessége
	 */
	public boolean verify() {
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				if ( matrix[x][y] == 0 || !scan(matrix[x][y], x, y) ) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Kiírja az aktuális táblát.
	 */
	public void write() {
		for (int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				System.out.print(matrix[x][y] + " ");
			}
			System.out.println();
		}
	}

}

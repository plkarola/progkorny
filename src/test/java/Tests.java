import static org.junit.Assert.*;

import org.junit.Test;

import sudoku.Engine;
import sudoku.EngineInterFace;
import sudoku.ReadTable;
import sudoku.ReadTableInterFace;

public class Tests {

	EngineInterFace engine = new Engine();
	ReadTableInterFace RT = new ReadTable(getClass().getClassLoader().getResource("Tables/elsotabla.xml").getFile());
	int [][] matrix = new int[9][9];
	
	@Test
	public void minimum_table() {
		assertEquals(true , RT.returntable(0) != null );
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void Engine() {
		EngineInterFace en = new Engine(matrix);
		assertEquals(matrix, en.getMatrix());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void equals() {
		matrix = RT.returntable(2);
		engine.setMatrix(matrix);
		engine.insert(2, 0, 0);
		matrix[0][0]=2;
		assertEquals(matrix, engine.getMatrix());
	}
	
	@Test
	public void goal() {
		engine.setMatrix(RT.returntable(2));
		engine.insert(2, 0, 0);
		assertEquals(false, engine.verify());
	}
	
	@Test
	public void not_insert() {
		matrix = RT.returntable(2);
		engine.setMatrix(matrix);
		assertEquals(false, engine.insert(4, 0, 0));
	}
	
	@Test
	public void not_verify() {
		matrix = RT.returntable(2);
		assertEquals(false, engine.verify());
	}
	
	@Test
	public void not_insert_matr() {
		matrix = RT.returntable(1);
		engine.setMatrix(matrix);
		assertEquals(false, engine.insert(3, 6, 1));
	}
	
	@Test
	public void not_insert_column() {
		matrix = RT.returntable(1);
		engine.setMatrix(matrix);
		assertEquals(false, engine.insert(1, 2, 1));
	}
	
	@Test
	public void not_insert_fix() {
		matrix = RT.returntable(1);
		engine.setMatrix(matrix);
		assertEquals(false, engine.insert(6, 2, 2));
	}
	
	@Test
	public void not_insert_fix_scan() {
		matrix = RT.returntable(1);
		engine.setMatrix(matrix);
		assertEquals(false, engine.insert(3, 2, 2));
	}
	
	@Test
	public void not_insert_fix_new() {
		matrix = RT.returntable(1);
		engine.setMatrix(matrix);
		assertEquals(false, engine.insert(3, 2, 2));
	}
	
	@Test
	public void init() {
		matrix = RT.returntable(2);
		engine.setMatrix(matrix);
		engine.init(matrix);
	}
	
	@Test
	public void delete_ok() {
		matrix = RT.returntable(1);
		engine.setMatrix(matrix);
		assertEquals(true, engine.delete(0, 0));
	}
	
	@Test
	public void not_delete() {
		matrix = RT.returntable(2);
		engine.setMatrix(matrix);
		engine.write();
		assertEquals(false, engine.delete(8,8));
	}
	
	@Test
	public void tablenumber() {
		assertEquals(4, RT.tableNumber());
	}
	
	@Test
	public void row_new() {
		engine.setMatrix(RT.returntable(0));
		engine.insert(0, 0, 0);
		assertEquals(true, engine.scan(0, 0, 0));
	}
	
	@Test
	public void matr_new() {
		engine.setMatrix(RT.returntable(0));
		engine.insert(8,0,0);
		assertEquals(false, engine.scan(8, 0, 0));
	
	}
}
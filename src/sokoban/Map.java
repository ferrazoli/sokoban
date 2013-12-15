package sokoban;

public class Map {

	private Field map[][];
	
	public Map() {
		map = new Field[15][20];
	}

	public Field[][] getMap() {
		return map;
	}
	
	public Field getFieldAt(int x, int y) {
		return map[x][y];
	}
	
	public void setFieldAt(int x, int y, Field f) {
		map[x][y] = f;
	}
	
	public boolean isWall (int x, int y) {
		if (map[x][y].isWall()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasBox (int x, int y) {
		if (map[x][y].hasBox()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isTarget (int x, int y) {
		if (map[x][y].isTarget()) {
			return true;
		} else {
			return false;
		}
	}	
}

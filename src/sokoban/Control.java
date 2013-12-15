package sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;

import base.Compass;
import base.IScreen;

public class Control implements KeyListener {
	
	public static final int FIELDSIZE = 39;
	
	private Map map;
	private Actor actor;
	private int level;
	private IScreen screen;
	private int targetCount;
	private int targetHit;
	
	
	
	public void hitTarget() {
		targetHit++;
	}

	public void leaveTarget() {
		targetHit--;
	}
	
	public void setImg(Field f) {
		if (f.isWall()) {
			f.setImg(Sokoban.img_wall);
		} else if (f.isTarget()) {
			f.setImg(Sokoban.img_target);
		} else {
			f.setImg(Sokoban.img_path);
		}
	}
	
	public Control(IScreen s) {
		
		this.screen = s;
		
		map = new Map();
		actor = new Actor(1, 1);
		actor.setMap(map);
		actor.setControl(this);
		
		level = 1;
		
		targetCount = 0;
		targetHit = 0;
		
		readLevel();
	
		display();
				/*screen.addMouseMotionListener(f);
				screen.addMouseListener(f);
				f.addMarcaListener(this);*/
	}
	
	public void display() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 20; j++) {	
				setImg(map.getFieldAt(i, j));
				screen.add(map.getFieldAt(i,j));	
				if (map.getFieldAt(i, j).hasBox()) 
					screen.add(map.getFieldAt(i, j).getBox());
			}
		}
		screen.add(actor);
	}
	
	//TECLADO
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			actor.move(Compass.NORTH);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			actor.move(Compass.SOUTH);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			actor.move(Compass.WEST);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			actor.move(Compass.EAST);
		}	
		if (e.getKeyCode() == KeyEvent.VK_R) {
			restartLevel();
		}
	}
	
	public void restartLevel() {
		readLevel();
		display();
	}
	
	//NÃO UTILIZADOS
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	//COISAS RELACIONADAS AO NIVEL
	public void readLevel() {
		
		// lendo arquivo de texto em um char[][]
		
		char array[][] = new char[15][20];
		try {
			BufferedReader reader = new BufferedReader(new FileReader("resources/level/" + level + ".txt"));
			for (int i=0; i<15; i++) {
				String line = reader.readLine();
				for (int j=0; j<20; j++) {
					array[i][j] = line.charAt(j);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// cria mapa
		
		targetCount = 0;
		targetHit = 0;
		
		for (int i=0; i<15; i++) {
			for (int j=0; j<20; j++) {
				Field f = new Field(j*FIELDSIZE, i*FIELDSIZE);
				switch (array[i][j]) {
					case '0':
						f.setWall(true);
						break;
					case 'a':
						actor.setX(j*FIELDSIZE);
						actor.setY(i*FIELDSIZE);
						actor.setPosition(i,j);
						break;
					case 'b':
						f.setBox(new Box(j*FIELDSIZE, i*FIELDSIZE));
						break;
					case 'x':
						f.setTarget(true);
						targetCount++;
						break;
					case 'A':
						f.setTarget(true);
						actor.setX(j*FIELDSIZE);
						actor.setY(i*FIELDSIZE);
						actor.setPosition(i,j);
						targetCount++;
						break;
					case 'B':
						f.setTarget(true);
						f.setBox(new Box(j*FIELDSIZE, i*FIELDSIZE));
						targetCount++;
						targetHit++;
						break;
					default:
						if (array[i][j] != '1') {
							System.out.println("ERRO!!!!! Verificar o caractere linha " + i + " coluna " + j + " arquivo " + level);
						}
				}
				map.setFieldAt(i, j, f);
			}
		}
	}	
	public void setLevel(int level) {
		if (level > 0) {
			this.level = level;
			readLevel();
			display();
		}
	}
	public int getLevel() {
		return level;
	}
	public void nextLevel() {
		level++;
		readLevel();
		display();
	}
	public void previousLevel() {
		if (!(level == 1)) {
			level--;
			readLevel();
			display();
		}
	}
	
	//retorna true se venceu a fase
	public boolean verifyVictory() {
		if (targetHit == targetCount) {
			targetHit = 0;
			targetCount = 0;
			nextLevel();
			return true;
		}
		return false;
	}
}

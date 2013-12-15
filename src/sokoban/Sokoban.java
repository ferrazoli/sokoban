package sokoban;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Screen;

public class Sokoban {

	public static BufferedImage img_settings = readImage("resources/img/options.png");
	public static BufferedImage img_actornorth = readImage("resources/img/actor_n.png");
	public static BufferedImage img_actoreast = readImage("resources/img/actor_e.png");
	public static BufferedImage img_actorwest = readImage("resources/img/actor_w.png");
	public static BufferedImage img_actorsouth = readImage("resources/img/actor_s.png");
	public static BufferedImage img_box = readImage("resources/img/box.png");
	public static BufferedImage img_path = readImage("resources/img/path.png");
	public static BufferedImage img_target = readImage("resources/img/target.png");
	public static BufferedImage img_wall = readImage("resources/img/wall.png");
	public static BufferedImage img_bg = readImage("resources/img/menu_bg.png");
	
	public static BufferedImage readImage(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		
		}	
		return img;
	}	
	
	Screen screen;
	Control control;
	MenuPrincipal menu;
	
	public Sokoban(){
		screen = new Screen("Sokoban", 800, 600);
		
		menu = new MenuPrincipal(screen, this);
		screen.addKeyListener(menu);
	}
	
	public void startGame() {
		screen.removeKeyListener(menu);
		screen.reset();
		control = new Control(screen);
		screen.addKeyListener(control);
	}

	public static void main(String[] args) {
		new Sokoban();
	}
}
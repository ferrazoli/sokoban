package sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import base.IScreen;
import base.LabelComponent;

public class MenuPrincipal implements KeyListener {
	
	private IScreen screen;
	private int currentOption;
	private LabelComponent[] buttons;
	private Sokoban game;
	private Background bg;
	
	public MenuPrincipal (IScreen s, Sokoban game){
		this.game = game;
		screen = s;
		currentOption = 0;
		buttons = new LabelComponent[3];
		buttons[0] = new LabelComponent(30, 40, "Play", true);
		buttons[1] = new LabelComponent(30, 100, "Select Level", false);
		buttons[2] = new LabelComponent(30, 160, "Quit", false);
		bg = new Background();
				
		screen.add(bg);
		for (int i=0; i<3; i++) {
			screen.add(buttons[i]);
		}
	}
	
	public void selectOption() {
		if (currentOption == 2) {
			System.exit(0);
		} else if (currentOption == 0) {
			game.startGame();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			buttons[currentOption].setBold(false);
			if (currentOption == 0) {
				currentOption = 2;
				buttons[currentOption].setBold(true);
			} else {
				currentOption--;
				buttons[currentOption].setBold(true);
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			buttons[currentOption].setBold(false);
			if (currentOption == 2) {
				currentOption = 0;
				buttons[currentOption].setBold(true);
			} else {
				currentOption++;
				buttons[currentOption].setBold(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			selectOption();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}

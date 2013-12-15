package sokoban;

import java.awt.Graphics;

import base.Component;


public class Box extends Component {
	
	public Box(int x, int y) {
		super(x, y);
		this.img = Sokoban.img_box;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);
	}
}

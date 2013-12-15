package sokoban;

import java.awt.Graphics;
import base.Component;

public class Field extends Component {
	
	private boolean isWall = false;
	private boolean isTarget = false;
	private Box box;
	
	public Field(int x, int y) {
		super(x, y);
	}
	
	public boolean isWall() {
		return isWall;
	}
	
	public boolean isTarget() {
		return isTarget;
	}
	
	public boolean hasBox() {
		if (box == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setBox(Box box) {
		this.box = box;
		box.setPosition(x,y);
	}
	
	public void removeBox() {
		this.box = null;
	}
	
	public Box getBox() {
		return box;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}

	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);
		if (box != null)
			box.paint(g);
	}
}

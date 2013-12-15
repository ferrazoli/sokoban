package sokoban;

import java.awt.Graphics;

import base.Component;

import base.Compass;

public class Actor extends Component {

	private int posX;
	private int posY;
	private Map map;
	private Control control;
	private Compass direction;
	
	public void chooseImg() {
		if (direction == Compass.NORTH) {
			setImg(Sokoban.img_actornorth);
		} else if (direction == Compass.SOUTH) {
			setImg(Sokoban.img_actorsouth);
		} else if (direction == Compass.WEST) {
			setImg(Sokoban.img_actorwest);
		} else {
			setImg(Sokoban.img_actoreast);
		}
		
		y = posX * Control.FIELDSIZE;
		x = posY * Control.FIELDSIZE;
	}

	public Actor(int x, int y) {
		super(x, y);
		direction = Compass.SOUTH;
		x = posX * Control.FIELDSIZE;
		y = posY * Control.FIELDSIZE;
		chooseImg();
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setControl(Control control) {
		this.control = control;
	}
	
	public Compass getDirection() {
		return direction;
	}

	public void move(Compass direction) {
		if (direction == Compass.NORTH) {
			if (!map.isWall(posX-1, posY)) {
				if(map.hasBox(posX-1, posY)) {
					push(Compass.NORTH);
				} else {
					this.posX = posX - 1;
				}
			}
		} else if (direction == Compass.WEST) {
			if (!map.isWall(posX, posY-1)) {
				if(map.hasBox(posX, posY-1)) {
					push(Compass.WEST);
				} else {
					this.posY = posY - 1;
				}
			}
		} else if (direction == Compass.EAST) {
			if (!map.isWall(posX, posY+1)) {
				if(map.hasBox(posX, posY+1)) {
					push(Compass.EAST);
				} else {
					this.posY = posY + 1;
				}
			}
		} else {
			if (!map.isWall(posX+1, posY)) {
				if(map.hasBox(posX+1, posY)) {
					push(Compass.SOUTH);
				} else {
					this.posX = posX + 1;
				}
			}
		}
		this.direction = direction;
		chooseImg();
	}
	
	public void push(Compass direction) {
		if (direction == Compass.NORTH) {
			if (!map.isWall(posX-2, posY) &&
				!map.hasBox(posX-2, posY)) {
				if (map.isTarget(posX-1, posY)) {
					control.leaveTarget();
				}
				if (map.isTarget(posX-2, posY)) {
					control.hitTarget();
					if (control.verifyVictory()) {
						return;
					}
				}
				map.getFieldAt(posX-2, posY).setBox(map.getFieldAt(posX-1, posY).getBox());
				map.getFieldAt(posX-1, posY).removeBox();
				this.posX = posX - 1;
			} 
		} else if (direction == Compass.WEST) {
			if (!map.isWall(posX, posY-2) &&
				!map.hasBox(posX, posY-2)) {
				if (map.isTarget(posX, posY-1)) {
					control.leaveTarget();
				}
				if (map.isTarget(posX, posY-2)) {
					control.hitTarget();
					if (control.verifyVictory()) {
						return;
					}
				}
				map.getFieldAt(posX, posY-2).setBox(map.getFieldAt(posX, posY-1).getBox());
				map.getFieldAt(posX, posY-1).removeBox();
				this.posY = posY - 1;
			} 
		} else if (direction == Compass.EAST) {
			if (!map.isWall(posX, posY+2) &&
				!map.hasBox(posX, posY+2)) {
				if (map.isTarget(posX, posY+1)) {
					control.leaveTarget();
				}
				if (map.isTarget(posX, posY+2)) {
					control.hitTarget();
					if (control.verifyVictory()) {
						return;
					}
				}
				map.getFieldAt(posX, posY+2).setBox(map.getFieldAt(posX, posY+1).getBox());
				map.getFieldAt(posX, posY+1).removeBox();
				this.posY = posY + 1;
			} 
		} else {
			if (!map.isWall(posX+2, posY) &&
				!map.hasBox(posX+2, posY)) {
				if (map.isTarget(posX+1, posY)) {
					control.leaveTarget();
				}
				if (map.isTarget(posX+2, posY)) {
					control.hitTarget();
					if (control.verifyVictory()) {
						return;
					}
				}
				map.getFieldAt(posX+2, posY).setBox(map.getFieldAt(posX+1, posY).getBox());
				map.getFieldAt(posX+1, posY).removeBox();
				this.posX = posX + 1;		
			} 
		}
		this.direction = direction;
		chooseImg();
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);	
	}
}

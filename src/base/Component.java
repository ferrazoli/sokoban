package base;

import java.awt.image.BufferedImage;

public abstract class Component implements IPaintAble {

	protected int x;
	protected int y;
	protected int width;
	protected int heigth;
	protected BufferedImage img;
	
	public Component(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Component(int x, int y, int width, int heigth){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeigth() {
		return heigth;
	}
	public void setHeigth(int heitgh) {
		this.heigth = heitgh;
	}
}

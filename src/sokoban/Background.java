package sokoban;

import java.awt.Graphics;

import base.Component;

public class Background extends Component {

	public Background() {
		super(0, 0);
		setImg(Sokoban.img_bg);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}

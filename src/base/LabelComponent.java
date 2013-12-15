package base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class LabelComponent extends Component {
	
	private String label;
	private boolean bold;
	private Font font;
	
	public LabelComponent(int x, int y, String label, boolean bold) { 
		super(x, y);
		this.label = label;
		this.bold = bold;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/SECRCODE.TTF"));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		font = font.deriveFont(20.0F);
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		if (bold) {
			g.setFont(font.deriveFont(Font.BOLD));
		} else {
			g.setFont(font);
		}
		g.drawString(label, x, y);
	}	
}

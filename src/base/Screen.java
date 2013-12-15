package base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Screen extends JPanel implements IScreen, KeyListener {

	private JFrame frame;
	private boolean isAlive;
	private Vector<IPaintAble> vObj;
	private Painter painter;
	private Vector<KeyListener> vKeyListener;
	
	public Screen (String title, int x, int y){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
         
        }

		frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(x, y));
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
		
		isAlive = true;
		
		vKeyListener = new Vector<KeyListener>();
		vObj = new Vector<IPaintAble>();
		
		
		//frame.setSize(new Dimension(x, y));
		
		
		
	
		
		painter = new Painter();
		Thread t = new Thread(painter);
		t.start();
	}
	
	public void finalize() throws Throwable{
		super.finalize();
		isAlive = false;
	}
	
	
	//VETOR DE KEYLISTENER
	public void addKeyListener(KeyListener kl) {
		this.vKeyListener.add(kl);
	}
	public void removeKeyListener (KeyListener kl){
		this.vKeyListener.remove(kl);
	}
	
	//VETOR DE IPAINTABLE
	public void add(IPaintAble obj){
		this.vObj.add(obj);
	}
	public void remove(IPaintAble obj){
		this.vObj.remove(obj);
	}
	public void reset() {
		this.vObj.removeAllElements();
	}
	public void paint(Graphics g){
		super.paint(g);
		synchronized(g){
			synchronized(vObj){
				for (int i=0; i<vObj.size(); i++){
					IPaintAble obj = vObj.get(i);
					synchronized(obj){
						obj.paint(g);
					}
				}
			}
		}
	}
	
	class Painter implements Runnable{

		@Override
		public void run() {
			while (isAlive){
					while(frame.isVisible()){
						try{
							invalidate();
							repaint();
							Thread.sleep(10);
						}
						catch(InterruptedException ie){
							System.out.println(ie.getStackTrace());
						}
					}
				try{
					Thread.sleep(100);
				}
				catch(InterruptedException ie){
					
				}
			}
		}
	}

	//TECLADO
	@Override
	public void keyPressed(KeyEvent e) {	
		for (KeyListener kl : vKeyListener)
		{
			kl.keyPressed(e);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}

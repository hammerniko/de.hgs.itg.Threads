package Z7_HP2011A3_Pacman;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ZeichenFlaeche extends JPanel{
	
	public static final int ANZAHL_SPALTEN = 15;
	public static final int ANZAHL_ZEILEN = 9;
	
	private int sizeX;
	private int sizeY;
	
	public ZeichenFlaeche() {
		sizeX = this.getWidth();
		sizeY = this.getHeight();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
	}

}

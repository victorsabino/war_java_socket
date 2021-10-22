package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PnlIndicadorJogador extends JPanel implements Observer{
	
	private static PnlIndicadorJogador indicador;

	private GeneralPath indicadorShape;
	
	public void configJPanel(){
		setPreferredSize(new Dimension(16,16));
		setSize(16,16);
		setLocation(12,0);
		setOpaque(false);
	}
	
	private void desenhaExercito(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setPaint(Color.white);
		g2d.fill(indicadorShape);
		
	}
	
	private PnlIndicadorJogador(){
		configJPanel();
	}
	
	
	{
		// Desenho do indicador
		indicadorShape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);		

		indicadorShape.moveTo(1.1, 2.0);
	    indicadorShape.lineTo(8.0, 14.0);
	    indicadorShape.lineTo(14.9, 2.0);
	    indicadorShape.closePath();
		
	}
	
	public static PnlIndicadorJogador getInstance() {
		if(indicador == null) {
			indicador = new PnlIndicadorJogador();
		}
		return indicador;
	}
	
	public GeneralPath getIndicadorShape(){
		
		return indicadorShape;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaExercito(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

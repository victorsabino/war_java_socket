package view;

import java.awt.*;
import java.awt.geom.GeneralPath;

import javax.swing.*;

@SuppressWarnings("serial")
public class PnlBoneco extends JPanel {
	
	protected String nome;
	protected Color cor;
	protected GeneralPath exercitoShape;
	
	// Bloco de inicialização do shape do desenho do exército
	{
		
		exercitoShape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);

		exercitoShape.moveTo(32.9, 48.9);
        exercitoShape.curveTo(33.300003, 49.600002, 33.5, 50.100002, 32.7, 50.600002);
        exercitoShape.curveTo(32.600002, 50.7, 32.7, 51.300003, 32.7, 51.600002);
        exercitoShape.curveTo(32.8, 52.2, 33.100002, 52.800003, 33.0, 53.300003);
        exercitoShape.curveTo(32.8, 54.300003, 32.1, 54.9, 31.0, 54.9);
        exercitoShape.curveTo(30.1, 54.9, 29.2, 54.800003, 28.3, 54.7);
        exercitoShape.curveTo(26.4, 54.5, 25.599998, 54.9, 24.699999, 56.600002);
        exercitoShape.curveTo(23.999998, 58.000004, 23.499998, 59.300003, 22.9, 60.800003);
        exercitoShape.curveTo(22.8, 61.100002, 22.8, 61.600002, 22.9, 61.9);
        exercitoShape.curveTo(23.199999, 62.7, 23.6, 63.5, 24.0, 64.3);
        exercitoShape.curveTo(24.2, 64.700005, 24.2, 65.100006, 24.4, 65.8);
        exercitoShape.curveTo(23.9, 65.600006, 23.6, 65.600006, 23.4, 65.5);
        exercitoShape.curveTo(18.9, 62.8, 14.4, 60.1, 10.0, 57.4);
        exercitoShape.curveTo(9.7, 57.2, 9.4, 57.0, 9.1, 56.7);
        exercitoShape.curveTo(8.200001, 56.0, 8.200001, 55.2, 9.1, 54.4);
        exercitoShape.curveTo(9.200001, 54.300003, 9.400001, 54.2, 9.400001, 54.100002);
        exercitoShape.curveTo(9.6, 53.4, 9.8, 52.7, 9.900001, 51.9);
        exercitoShape.curveTo(9.0, 52.0, 8.2, 52.2, 7.4, 52.3);
        exercitoShape.curveTo(6.7000003, 52.399998, 5.9, 52.6, 5.4, 51.899998);
        exercitoShape.curveTo(4.9, 51.199997, 5.4, 50.499996, 5.7000003, 49.899998);
        exercitoShape.curveTo(6.4, 48.3, 6.0000005, 46.699997, 5.3, 45.3);
        exercitoShape.curveTo(3.8000002, 42.399998, 2.8000002, 39.5, 3.4, 36.199997);
        exercitoShape.curveTo(3.7, 34.399998, 4.6000004, 32.999996, 5.9, 31.799997);
        exercitoShape.curveTo(8.7, 29.199997, 11.9, 26.999996, 15.6, 25.799997);
        exercitoShape.curveTo(20.2, 24.199997, 24.1, 25.599997, 27.400002, 28.899998);
        exercitoShape.curveTo(28.100002, 29.599998, 28.7, 30.299997, 29.300001, 31.099998);
        exercitoShape.curveTo(30.400002, 32.399998, 31.400002, 33.6, 32.5, 34.899998);
        exercitoShape.curveTo(32.7, 35.1, 33.0, 35.199997, 33.2, 35.3);
        exercitoShape.curveTo(34.2, 35.5, 35.2, 35.6, 36.2, 35.8);
        exercitoShape.curveTo(36.5, 35.899998, 36.7, 36.1, 36.9, 36.2);
        exercitoShape.curveTo(36.800003, 36.4, 36.7, 36.8, 36.4, 36.9);
        exercitoShape.curveTo(34.9, 37.9, 33.4, 38.800003, 31.900002, 39.7);
        exercitoShape.curveTo(31.2, 40.100002, 31.100002, 40.4, 31.600002, 41.0);
        exercitoShape.curveTo(32.000004, 41.6, 32.500004, 42.2, 33.000004, 42.8);
        exercitoShape.curveTo(33.500004, 43.399998, 34.100002, 43.899998, 34.600002, 44.399998);
        exercitoShape.curveTo(35.4, 45.199997, 35.100002, 46.199997, 34.000004, 46.499996);
        exercitoShape.curveTo(33.900005, 46.499996, 33.700005, 46.599995, 33.600002, 46.599995);
        exercitoShape.curveTo(33.000004, 46.699993, 32.9, 46.999996, 33.100002, 47.499996);
        exercitoShape.curveTo(33.4, 47.9, 33.5, 48.5, 32.9, 48.9);
		exercitoShape.closePath();

	}

	//Construtor 
	public PnlBoneco(){		
		setLayout(null);		
		setPreferredSize(new Dimension(40,70));
		setOpaque(false);
		repaint();
	}

	// Retorna o nome do exercito
	public String getNome() {
		return this.nome;
	}
	
	// Retorna a cor do exercito
	protected Color getCor() {
		return this.cor;
	}
	
	// Retorna o shape do exercito
	protected GeneralPath getExercitoShape() {
		return exercitoShape;
	}
	
}

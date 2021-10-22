package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author Pedro
 *
 */
public class Territorio {
	
	private GeneralPath shape;
	private String nome;
	private String nomeCarta;	

	private boolean ativo = false;
	private boolean fronteira = false;
	
	private int deslocaX = 0;
	private int deslocaY = -5;
	private int simbolo;
	
	private Ponto soldadoPos;
	
	private ArrayList<Territorio> lstFronteiras = new ArrayList<Territorio>();
	private ArrayList<Soldado> lstSoldados = new ArrayList<Soldado>();
	
	JLabel lblNumExercitos = new JLabel();
	
	public void addSoldado(Soldado s){
		lstSoldados.add(s);
	}
	
	public int getSimbolo() {
		return simbolo;
	}
	
	public ArrayList<Soldado> getLstSoldados() {
		return lstSoldados;
	}
	
	public void desenhaPnlSoldados(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g.create();	
		
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		Shape circulo = new Ellipse2D.Float((float) getSoldadoPos().getX(), (float) getSoldadoPos().getY(),23.0f,23.0f);
		g2d.setColor((Color) getLstSoldados().get(0).getExercito().getCor());
		g2d.fill(circulo);
		g2d.setColor(Color.black);
		g2d.draw(circulo);
	}
	
	public JLabel getLblNumSoldados() {		

		lblNumExercitos.setLocation((int)soldadoPos.getX(), (int)soldadoPos.getY());
		lblNumExercitos.setSize(23, 23);
		lblNumExercitos.setOpaque(false);
		lblNumExercitos.setForeground(Color.WHITE);
		lblNumExercitos.setText(lstSoldados.size()+"");
		lblNumExercitos.setHorizontalAlignment(SwingConstants.CENTER);
		
		return lblNumExercitos;
	}
	
	public void atualizaLblnumsoldados() {
		lblNumExercitos.setText(lstSoldados.size()+"");
	}
	
 	public Territorio(String nome, Ponto[] pontos, String nomeCarta, Ponto soldadoPos, int simbolo) {
		this.nome = nome;
		this.nomeCarta = nomeCarta;
		this.simbolo = simbolo;
		montaShape(pontos);		
		this.soldadoPos = soldadoPos;
	}
 	
 	public Ponto getSoldadoPos(){
 		return soldadoPos;
 	}
	
 	public void setSoldadoPos(Ponto p) {
 		this.soldadoPos = p;
 	}
 	
	/**
	 * Monta o shape do territorio através do vetor de pontos passados
	 * @param pontos
	 */
	private void montaShape(Ponto[] pontos) {
		
		shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);		
		shape.moveTo(pontos[0].getX() + (deslocaX),pontos[0].getY() + (deslocaY));

		for (int i = 1; i < pontos.length; i++) {
			shape.lineTo(pontos[i].getX() + (deslocaX), pontos[i].getY() + (deslocaY));
		}

		shape.closePath();
	}
	
	public GeneralPath getShape() {
		return this.shape;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getNomeCarta() {
		return nomeCarta;
	}

	public void setAtivo() {
		if(ativo) {
			ativo = false;
			//console.addLog(this.nome + " desativado.\n" + "Contem " + this.getLstSoldados().size() + " soldado(s) do exercito " + this.getLstSoldados().get(0).getExercito().getNome());
			marcaFronteiras();			
		} else {
			ativo = true;
			//console.addLog(this.nome + " ativado.\n" + "Contem " + this.getLstSoldados().size() + " soldado(s) do exercito " + this.getLstSoldados().get(0).getExercito().getNome());
			marcaFronteiras();
		}
	}

	public boolean ehAtivo() {
		return this.ativo;
	}

	public boolean ehFronteira(){
		if (fronteira) {
			return true;
		}
		
		return false;
	}
	
	private void marcaFronteiras(){
		
		for(Territorio t: lstFronteiras) {
			t.setFronteira();
		}
		
	}

	public void setFronteira() {
		if(fronteira) {
			fronteira = false;
		} else {
			fronteira = true;
		}
	}
	
	public void setFronteira(Territorio t) {
		lstFronteiras.add(t);
		if(!t.lstFronteiras.contains(this)) {
			t.setFronteira(this);	
		}
	}
	
	public ArrayList<Territorio> getLstFronteiras(){
		return lstFronteiras;
	}

}

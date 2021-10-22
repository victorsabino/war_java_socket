package model;
import java.awt.Color;
import java.util.ArrayList;


/**
 * @author Pedro
 *
 */
public class Continente {
	
	private ArrayList<Territorio> lstTerritorio = new ArrayList<Territorio>();
	private String nome;
	private Color cor;
	private int bonus;
	
	/**
	 * Construtor da classe Continente. Espera receber
	 * @param nome - O nome do continente
	 * @param cor - A cor do continente
	 */
	public Continente(String nome, Color cor, int bonus) {
		this.nome = nome;
		this.cor = cor;
		this.bonus = bonus;
	}

	/**
	 * Retorna a cor do territ�rio
	 * @return
	 */
	public Color getCor() {
		return this.cor;
	}
	
	/**
	 * Retorna o nome do territ�rio
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Retorna o bonus do territ�rio
	 * @return
	 */
	public int getBonus() {
		return this.bonus;
	}
	
	/**
	 * Retorna a lista de territorios do continente
	 * @return
	 */
	public ArrayList<Territorio> getLstTerritorios() {
		return lstTerritorio;
	}
	
	/**
	 * Adiciona o territorio passado como parametro na lista de territorios deste continente
	 * @param t = uma inst�ncia de territorio
	 */
	public void addTerritorio(Territorio t) {
		lstTerritorio.add(t);
	}
	
}

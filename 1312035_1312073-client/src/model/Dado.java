package model;

import java.util.Observable;
import java.util.Random;

public class Dado extends Observable implements Comparable<Dado> {

	private int numero;
	private char tipo;

	private boolean status = true;

	public Dado(char tipo) {
		
		// Seta o tipo do dado, se á de ataque (a) ou defesa (d)
		this.tipo = tipo;
		
		//inicializa todos os dados com o número 6
		this.numero = 0;
	}
	
	public void jogaDado() {
		setStatus(true);
		setNumero(new Random().nextInt(6 - 1 + 1) + 1);
	}
	
	public void setNumero(int n){
		this.numero = n;
		setChanged();
		notifyObservers();
	}
	
	public void setStatus(boolean b) {
		this.status = b;
	}
	
	public char getTipo(){
		return tipo;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public boolean getStatus() {
		return status;
	}

	@Override
	public int compareTo(Dado o) {
		if( o.getNumero() <  this.numero) {
			return -1;
		}
		if(o.getNumero() > this.numero ) {
			return 1;
		}
		return 0;
	}
	
}

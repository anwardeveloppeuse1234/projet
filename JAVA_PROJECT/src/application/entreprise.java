package application;


import java.util.*;

public class entreprise {
	String nomE;
	double Salaire;
	Map<Integer, salaire> salari� = new TreeMap<Integer,salaire>();
	public String getNomE() {
		return nomE;
	}
	public void setNomE(String nomE) {
		this.nomE = nomE;
	}
	public double getSalaire() {
		return Salaire;
	}
	public void setSalaire(double salaire) {
		Salaire = salaire;
	}
	public Map<Integer, salaire> getSalari�() {
		return salari�;
	}
	public void setSalari�(Map<Integer, salaire> salari�) {
		this.salari� = salari�;
	}
	public entreprise(String nomE, Map<Integer, salaire> listSalari�) {
		super();
		this.nomE = nomE;
		this.salari� = listSalari�;
	}  

}

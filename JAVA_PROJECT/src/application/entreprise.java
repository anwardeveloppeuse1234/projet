package application;


import java.util.*;

public class entreprise {
	String nomE;
	double Salaire;
	Map<Integer, salaire> salariť = new TreeMap<Integer,salaire>();
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
	public Map<Integer, salaire> getSalariť() {
		return salariť;
	}
	public void setSalariť(Map<Integer, salaire> salariť) {
		this.salariť = salariť;
	}
	public entreprise(String nomE, Map<Integer, salaire> listSalariť) {
		super();
		this.nomE = nomE;
		this.salariť = listSalariť;
	}  

}

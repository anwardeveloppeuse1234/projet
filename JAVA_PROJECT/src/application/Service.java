package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Service implements Initializable{

	
	
	public void creerSalarier() {
		try{  			
			PreparedStatement ps=connexion.getCon().prepareStatement("insert into entreprise values(?,?,?,?,?,?,?,?)");  
			  
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("enter matricule:");  
			int matricule=Integer.parseInt(br.readLine());
			System.out.println("enter nom:");  
			String nom=br.readLine();  
			System.out.println("enter email:");  
			String email=br.readLine();  
			
			System.out.println("enter dateEmbauche:");  
			double dateE=Double.parseDouble(br.readLine());
			System.out.println("enter valeur supplémentaire:");  
			double sup=Double.parseDouble(br.readLine());
			System.out.println("enter categorie:");  
			String categorie=br.readLine(); 
			
			if(categorie=="employe") {
				Employe s = new Employe(matricule,nom,email,dateE,sup);
				ps.setInt(1, s.matricule);
				ps.setString(2,s.nom);  
				ps.setString(3,s.email);
				ps.setDouble(4, s.Salaire);
				ps.setDouble(5, s.dateE);
				ps.setDouble(6, s.Sup);
				ps.setDouble(7, s.PrixSup);
				ps.setString(8, categorie);int i=ps.executeUpdate();  
				System.out.println(i+" employe ajouté dans entreprise");  
			}else if(categorie=="vendeur"){
				vendeur s = new vendeur(matricule,nom,email,dateE,sup);
				ps.setInt(1, s.matricule);
				ps.setString(2,s.nom);  
				ps.setString(3,s.email);
				ps.setDouble(4, s.Salaire);
				ps.setDouble(5, s.dateE);
				ps.setDouble(6, s.Vente);
				ps.setDouble(7, s.Pourcentage);
				ps.setString(8, categorie);
				int i=ps.executeUpdate();  
				System.out.println(i+" employe ajouté dans entreprise");  
			}
			
			  
			
			
		}catch(Exception e){ System.out.println(e);}
	}

	public List<salaire> tout;
	public void affichertout() {
		ArrayList<salaire> tout = new ArrayList<salaire>();
		try{
			Statement stmt=connexion.getCon().createStatement();  
			ResultSet rs=stmt.executeQuery("select * from entreprise");  
			while(rs.next())  
			tout.add(new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
			connexion.getCon().close();  
		}catch(Exception e){ System.out.println(e);}
	}
	
	public List<salaire> affiche(){
		ArrayList<salaire> tout = new ArrayList<salaire>();
		try{
			Statement stmt=connexion.getCon().createStatement();  
			ResultSet rs=stmt.executeQuery("select * from entreprise");  
			while(rs.next())  
			tout.add(new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6))); 
		}catch(Exception e){ System.out.println(e);}
		return tout;
		
	}

	public void supprimerSalarier(int matricule) {
		try{  			
			PreparedStatement ps=connexion.getCon().prepareStatement("delete from entreprise where matriculeE=?");  
			ps.setInt(1, matricule);
			
			int i=ps.executeUpdate();  
			System.out.println(i+" emaploye from entreprise deleted");  
			  
			connexion.getCon().close();
		}catch(Exception e){ System.out.println(e);}
	}

	public void afficherDetailler(int matricule) {
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("select * from entreprise where matriculeE=?");
			ps.setInt(1, matricule);
			ResultSet rs=ps.executeQuery();  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5)+"  ");  
			connexion.getCon().close();  
		}catch(Exception e){ System.out.println(e);}
	}

	public void listerParSalaire(){
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("select * from entreprise order by salaire asc");
			ResultSet rs=ps.executeQuery();  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5)+"  ");  
			connexion.getCon().close();  
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void listerParCategorie(String cat){
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("select * from entreprise where category="+cat);
			ResultSet rs=ps.executeQuery();  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5)+"  ");  
			connexion.getCon().close();  
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void listerParAncienette(){
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("select * from entreprise order by dateEmbauche asc");
			ResultSet rs=ps.executeQuery();  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5)+"  ");  
			connexion.getCon().close();  
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void listerEntreDeux(double minimum, double maximum){
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("select * from entreprise where salaire between ? and ?");
			ps.setDouble(1, minimum);
			ps.setDouble(2, maximum);
			ResultSet rs=ps.executeQuery();  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5)+"  ");  
			connexion.getCon().close();  
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void listerVendeurA(double minimum, double maximum){
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("select * from entreprise where category='vendeur' order by salaire desc");
			ResultSet rs=ps.executeQuery();  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5)+"  ");   
		}catch(Exception e){ System.out.println(e);}
	}

	@FXML
	ChoiceBox<String> ch;
	
	List<vendeur> listSal;
	List<StringProperty> listCat;
	double val = 0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<StringProperty>();
		String req = "select * from entreprise";
		String req1 = "select category from entreprise";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			Statement stmt1 = connexion.getCon().createStatement();
			ResultSet rs1 = stmt1.executeQuery(req1);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				
			}
			int ab = 0;
			while(rs1.next()) {
				listCat.add(ab,  new SimpleStringProperty( rs1.getString(1)));
				ab+=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<vendeur> ob = FXCollections.observableArrayList(listSal);
		ObservableList<StringProperty> data = FXCollections.observableArrayList(listCat);
		tableau.getItems().addAll(ob);
		
		colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colSalaire.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
		colCategorie.setCellValueFactory(data -> data.getValue().get(1));
		
		
		
		
		ch.getItems().add("Employe");
		ch.getItems().add("Vendeur");
		
		tableau.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        matricule.setText(Integer.toString(tableau.getSelectionModel().getSelectedItem().getMatricule()));
		        nom.setText(tableau.getSelectionModel().getSelectedItem().getNom());
		        Date.setText(Double.toString(tableau.getSelectionModel().getSelectedItem().getDateE()));
		        email.setText(tableau.getSelectionModel().getSelectedItem().getEmail());
		        if(tableau.getSelectionModel().getSelectedItem().getDateE()>2005) {
		        	val = 280 - (tableau.getSelectionModel().getSelectedItem().getSalaire()/4);
		        }else {
		        	val = 400 - (tableau.getSelectionModel().getSelectedItem().getSalaire()/4);
		        }
		        valeursup.setText(Double.toString(val));
		    }
		});
		
		this.affiche();
	}	
	
	@FXML
	public void refresh() {
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
		tableauCat.getItems().addAll(obC);
	}
	
	@FXML
	TableView<StringProperty> tableauCat;
	
	@FXML
	TextField matricule;
	
	@FXML
	TextField nom;
	
	@FXML
	TextField Date;
	
	@FXML
	TextField email;
	
	@FXML
	TextField valeursup;

	@FXML
	TextField categorie;
	
	@FXML
	TextField minimum;
	
	@FXML
	TextField maximum;
	
	@FXML
	Button ajouter;
	
	@FXML
	Button modifier;
	
	@FXML
	Button supprimer;
	
	@ FXML
	Button listerLesEmployes;
	
	@FXML
	Button listerLesVendeurs;
	
	@FXML
	Button ListerparAncienneté;
	
	@FXML
	Button ListerEntredeux;
	
	@FXML
	Button ListerparSalaire;
	
	@FXML
	Button Lister;
	
	@FXML 
	Button Exporter;
	
	@FXML
	TableView<salaire> tableau;
	
	@FXML
	TableColumn<salaire, Integer> colMatricule;
	
	@FXML
	TableColumn<salaire, String> colNom;
	
	@FXML
	TableColumn<salaire, String> colEmail;
	
	@FXML
	TableColumn<salaire, Double> colSalaire;
	
	@FXML
	TableColumn<StringProperty, String> colCategorie;
	public void ajout() {
		try{
			PreparedStatement ps=connexion.getCon().prepareStatement("insert into entreprise values(?,?,?,?,?,?,?,?)");  			
			int Matricule=Integer.parseInt(matricule.getText());
			String newNom=nom.getText();  
			String newEmail=email.getText();  
			double NewDateE=Double.parseDouble(Date.getText());
			double NewSup=Double.parseDouble(valeursup.getText());
			String newCategory=(String) ch.getValue();
			if(newCategory=="employe") {
				vendeur s = new vendeur(Matricule,newNom,newEmail,NewDateE,NewSup);
				ps.setInt(1, s.getMatricule());
				ps.setString(2,s.getNom());  
				ps.setString(3,s.getEmail());
				ps.setDouble(4, s.getSalaire());
				ps.setDouble(5, s.getDateE());
				ps.setDouble(6, s.getVente());
				ps.setDouble(7, s.getPourcentage());
				ps.setString(8, newCategory);
				}
			else {
				Employe s = new Employe(Matricule,newNom,newEmail,NewDateE,NewSup);
				ps.setInt(1, s.getMatricule());
				ps.setString(2,s.getNom());  
				ps.setString(3,s.getEmail());
				ps.setDouble(4, s.getSalaire());
				ps.setDouble(5, s.getDateE());
				ps.setDouble(6, s.getSup());
				ps.setDouble(7, s.getPrixSup());
				ps.setString(8, newCategory);
			}
			ps.executeUpdate();
			for(int i = 0; i<tableau.getItems().size(); i++) {
				tableau.getItems().clear();
			}
			refresh();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public static double setSalaireFinal (double dateE, double pour) {
		if (dateE>=2005) {
			return  (400 + pour);
		}else {
			return  280 + pour;
		}
	}
	
	public static double setP(double su) {
		return su*0.2;
	}
	
	@FXML
	public void modifier() {
		try {
		PreparedStatement ps=connexion.getCon().prepareStatement("update entreprise set nom=?, email=?, salaire=?, dateEmbauche=?, sup=?, supDT=?, category=? where matriculeE=?");  			
		int Matricule=Integer.parseInt(matricule.getText());
		String newNom=nom.getText();  
		String newEmail=email.getText();  
		double NewDateE=Double.parseDouble(Date.getText());
		double NewSup=Double.parseDouble(valeursup.getText());
		String newCategory=(String) ch.getValue();
		if(newCategory=="employe") {
			vendeur s = new vendeur(Matricule,newNom,newEmail,NewDateE,NewSup);
			ps.setInt(1, s.getMatricule());
			ps.setString(2,s.getNom());  
			ps.setString(3,s.getEmail());
			ps.setDouble(4, s.getSalaire());
			ps.setDouble(5, s.getDateE());
			ps.setDouble(6, s.getVente());
			ps.setDouble(7, s.getPourcentage());
			ps.setString(8, newCategory);
			}
		else {
			Employe s = new Employe(Matricule,newNom,newEmail,NewDateE,NewSup);
			ps.setInt(8, s.getMatricule());
			ps.setString(1,s.getNom());  
			ps.setString(2,s.getEmail());
			ps.setDouble(3, s.getSalaire());
			ps.setDouble(4, s.getDateE());
			ps.setDouble(5, s.getSup());
			ps.setDouble(6, s.getPrixSup());
			ps.setString(7, newCategory);
		}
		ps.executeUpdate();
		for(int i = 0; i<tableau.getItems().size(); i++) {
			tableau.getItems().remove(i);
		}
		refresh();  
	}catch(Exception e){ System.out.println(e);}
	}
	
	@FXML
	public void supprimer() throws SQLException {
		int index = tableau.getSelectionModel().getSelectedItem().getMatricule();
		PreparedStatement ps=connexion.getCon().prepareStatement("delete from entreprise where matriculeE=?");
		ps.setInt(1, index);
		ps.executeUpdate();
		for(int i = 0; i<tableau.getItems().size(); i++) {
			tableau.getItems().remove(i);
		}
		refresh();
	}
	
	@FXML
	public void listerLesEmploye(){
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise where category='employe'";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
	}
	
	@FXML
	public void listerLesVendeurs(){
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise where category='vendeur'";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
	}
	@FXML
	public void ListerparAncienneté(){
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise order by dateEmbauche asc";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
	}
	
	@FXML
	public void filtrer() {
		ListerEntredeux(Double.parseDouble(minimum.getText()) , Double.parseDouble(maximum.getText()));
	}
	
	@FXML
	public void ListerEntredeux(double min, double max){
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise where salaire between '"+min +"' AND '"+max+"'";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
	}
	
	@FXML
	public void ListerparSalaire(){
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise order by salaire asc";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
	}
	
	@FXML
	public void Lister(){
		listSal = new ArrayList<vendeur>();
		listCat = new ArrayList<String>();
		String req = "select * from entreprise";
		try {
			Statement stmt = connexion.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next()) {
				listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
				listCat.add(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<salaire> ob = FXCollections.observableArrayList(listSal);
		ObservableList<String> obC = FXCollections.observableArrayList(listCat);
		tableau.setItems(ob);
	}
	 
	@FXML
	public void exporter() throws IOException {
		
		listSal = new ArrayList<vendeur>();
				
				int col = 15;
				File exFile = new File("C:\\Users\\Guide Info\\Desktop\\DSI Deuxiéme semestre\\JAVA avancé\\Java.txt");
				FileWriter exFr = new FileWriter(exFile);
				BufferedWriter bfr = new BufferedWriter(exFr);
				String newLine = "";
				try{  		
					Statement stmt=connexion.getCon().createStatement();  
					ResultSet rs=stmt.executeQuery("select * from entreprise");
			
					while(rs.next())  
						
							listSal.add(new vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getDouble(6)));
							
						
				}catch(Exception e){ System.out.println(e);} 
				
				Iterator<vendeur> itS = listSal.iterator();
		
				
				while(itS.hasNext()) {
				
					bfr.newLine();
					newLine = itS.next().toString();
					bfr.write(newLine);
				}
				System.out.println("sqcdc");
				bfr.close();
	}
}
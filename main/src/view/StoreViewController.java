package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Product;
import model.Tietokanta;

public class StoreViewController {
	
	private Product[] products;
	
	
	@FXML private Button toMainBtn;
	
	@FXML private TableView<Product> productsTable;
	@FXML private TableColumn<Product, String> nameColumn; //Tuotenimi
	@FXML private TableColumn<Product, Double> creditColumn; //Kuinka monta krediittiä ostoksesta saa
	@FXML private TableColumn<Product, Integer> coinColumn; //Kuinka monta kolikkoa ostoksesta saa
	@FXML private TableColumn<Product, Double> priceColumn; //Hinta
	@FXML private TableColumn<Product, Boolean> buy; //Tarkoitus toimia osto-"buttonina"
	
	
	@FXML
	public void initialize() {
		
		//Mitä muuttujaa mikäkin kolumni vastaa Product-luokassa
		nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
		creditColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("creditAmount"));
		coinColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("coinAmount"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
		
		Product[] allProducts = Tietokanta.getProducts();
		products = new Product[allProducts.length];
		
		int j = 0;
		for (int i = 0; i < allProducts.length; i++) {
			
			if (allProducts[i].getForSaleStatus()) { //Jos myynnissä, lisätään products-taulukkoon
				products[j++] = allProducts[i];
			}
			
		}
		
		showProducts();
	}
	
	//Asettaa tuotteiden tiedot TableColumneihin
	@FXML
	public void showProducts() {
		
		ObservableList<Product> productOL = FXCollections.observableArrayList();
		
		for (Product p : products) {
			productOL.add(p);
			
		}
		
		productsTable.setItems(productOL);
		
		/*
		productTF2.setText("\n\n" + String.valueOf(products[1].getCreditAmount()) + " krediittiä\n\n"
				+  "Hinta: " + String.valueOf(products[1].getPrice()) + " €!");  */
		
	}
	
	public void toHomepage(ActionEvent e) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("HomepageView.fxml"));
            AnchorPane homepageView = (AnchorPane) loader.load();
            Scene homepageScene = new Scene(homepageView);
			Stage window = (Stage) toMainBtn.getScene().getWindow();
			window.setScene(homepageScene);
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
	}
	
	public void purchaseOffer(ActionEvent e) {
		
	}
	
	public void purchaseProduct1(ActionEvent e) {
		/*
		double amount = products[0].getCreditAmount();
		int updatedRows = Tietokanta.increaseCreditBalance(amount);
		System.out.println(updatedRows + " rows updated.");
		*/
		Tietokanta.buyProduct(products[0]);
	}
	
	public void purchaseProduct2(ActionEvent e) {
		/*
		double amount = products[1].getCreditAmount();
		int updatedRows = Tietokanta.increaseCreditBalance(amount);
		System.out.println(updatedRows + " rows updated.");
		*/
		Tietokanta.buyProduct(products[1]);
	}
	
	public void toMainView(ActionEvent e) {
		
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("HomepageView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();
            Scene mainScene = new Scene(mainView);
			Stage window = (Stage) toMainBtn.getScene().getWindow();
			window.setScene(mainScene);
			
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
	}

	
	
}

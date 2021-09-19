package view;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Tietokanta;

/**
* Kontrolleri jolla ohjataan RekisterointiViewin toimintoja.
*/

public class RekisterointiViewController {
	@FXML private TextField kayttajatunnusInput2;
	@FXML private TextField salasanaInput2;
	@FXML private Button rekisteroidyButton;
	@FXML private Button tokirjautumisButton;
	@FXML private TextField etunimiInput;
	@FXML private TextField sukunimiInput;
	@FXML private TextField sahkopostiInput;
	@FXML private TextField salasanauudelleenInput;
	
	/**
	* Metodi käyttäjän rekisteröintiä varten. Lähetetään DAO:lle rekisteröintiin tarvittavat tiedot.
	*/
	public void rekisteroidy(ActionEvent e) {
		if(!salasanaInput2.getText().equals(salasanauudelleenInput.getText())) {
			System.out.println("Salasanat eivät täsmää");
			
		} else if(kayttajatunnusInput2.getText().isEmpty() || salasanaInput2.getText().isEmpty() || etunimiInput.getText().isEmpty() || sukunimiInput.getText().isEmpty() 
				|| sahkopostiInput.getText().isEmpty() || salasanauudelleenInput.getText().isEmpty()) {
			System.out.println("Kaikki tiedot on syötettävä oikein");
			
		} else {
			System.out.println("Käyttäjätunnus: " + kayttajatunnusInput2.getText());
			System.out.println("Salasana: " + salasanaInput2.getText());
			System.out.println("Salasana Uudelleen: " + salasanauudelleenInput.getText());
			System.out.println("Etunimi: " + etunimiInput.getText());
			System.out.println("Sukunimi: " + sukunimiInput.getText());
			System.out.println("Sähköposti: " + sahkopostiInput.getText());
			
			Boolean onnistuiko = Tietokanta.register(kayttajatunnusInput2.getText(), salasanaInput2.getText(), sahkopostiInput.getText(), etunimiInput.getText(), sukunimiInput.getText());
			
			if(onnistuiko)
				System.out.println("kirjautuminen onnistui :)");
			else
				System.out.println("kirjautuminen epäonnistui :(");
				
		}
	}
	
	/**
	* Metodi jolla asetetaan kirjautumisiScene nykyiseen Stageen
	*/
	public void toKirjautuminen(ActionEvent e) {	
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("KirjautumisView.fxml"));
            AnchorPane kirjautumisView = (AnchorPane) loader.load();
            Scene kirjautumisScene = new Scene(kirjautumisView);
			Stage window = (Stage) tokirjautumisButton.getScene().getWindow();
			window.setScene(kirjautumisScene);
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
	}
}

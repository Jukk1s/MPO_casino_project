package testi;

import java.sql.*;

//T�M� LUOKKA PIT�� MY�HEMMIN MUOKATA NIIN ETTEI K�YTT�J� PUOLELLA SUORAAN P��ST� MUUTTAMAAN TIETOKANNAN TIETOAJA
//VAAN V�LIK�TEN� OILISI PARHAASSA TILANTEESSA PALVELIMELLA PY�RIV� OHJELMA JOKA OTTAA PARAMETRIT VASTAAN (nimi salasana yms)

public class TietokantaTesti {
	public static void main(String[] args) {
	
		Connection con;
		
		//final String URL = "jdbc:mariadb://10.114.32.22:3306/kasino";
		final String URL = "jdbc:mariadb://10.114.32.22:3306/kasino";
		final String USERNAME = "remote";
		final String PASSWORD = "remote";
		
		try {
			con = DriverManager.getConnection(
					URL + "?user=" + USERNAME + "&password=" + PASSWORD);
			
			Statement stmt = con.createStatement();
			
			//SQL sy�tt� kutsu, tehd��n Kayttaja tauluun uusi rivi
			String query = "INSERT INTO Kayttaja (Kayttajanimi, Salasana, Tilinumero, Sahkoposti, Firstname, Lastname) "
					+ "values ('Testik�ytt�j�', 123, 'FI20 40 8950 1253 1250 20', 'testi@testi.fi', 'Mikko', 'Suomalainen')";
			
			stmt.executeQuery(query);
			
			//Tehd��n SQL haku kutsu ja haetaan Testik�ytt�j�/k�ytt�j�t
			query = "SELECT KayttajaID, Kayttajanimi, Sahkoposti FROM Kayttaja WHERE Kayttajanimi = 'Testik�ytt�j�'";
			
			ResultSet rs = stmt.executeQuery(query);
			
			int luku = 0;
			while(rs.next()) {
				System.out.println("Tulos "+luku+" | "
			+"KayttajaID "+rs.getInt("KayttajaID")
			+"    Kayttajanimi "+rs.getString("Kayttajanimi")
			+"    Sahkoposti "+rs.getString("Sahkoposti"));
			}
			
			//Poistetaan Kayttaja taulusta mahdolliset "testik�ytt�j�t"
			query = "DELETE FROM Kayttaja WHERE Kayttajanimi = 'Testik�ytt�j�'";
			
			stmt.execute(query);
			
			System.out.println("Testik�ytt�j�(t) poistettiin tietokannasta.");
			
			
		} catch (SQLException e) {
			do {
				System.err.println("Viesti: "+e.getMessage());
				System.err.println("Virhekoodi: "+e.getErrorCode());
				System.err.println("SQL-tilakoodi: "+e.getSQLState());
			} while (e.getNextException() != null);
		}
		System.out.println("onnistui");
		
		
	}

}

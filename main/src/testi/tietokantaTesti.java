package testi;

import java.sql.*;

//T�M� LUOKKA PIT�� MY�HEMMIN MUOKATA NIIN ETTEI K�YTT�J� PUOLELLA SUORAAN P��ST� MUUTTAMAAN TIETOKANNAN TIETOAJA
//VAAN V�LIK�TEN� OILISI PARHAASSA TILANTEESSA PALVELIMELLA PY�RIV� OHJELMA JOKA OTTAA PARAMETRIT VASTAAN (nimi salasana yms)

public class tietokantaTesti {
	public static void main(String[] args) {
	
		Connection con;
		
		final String URL = "jdbc:mariadb://10.114.32.22:3306/kasino";
		final String USERNAME = "r12";
		final String PASSWORD = "r12";
		
		try {
			con = DriverManager.getConnection(
					URL + "?user=" + USERNAME + "&password=" + PASSWORD);
		} catch (SQLException e) {
			do {
				System.err.println("Viesti: "+e.getMessage());
				System.err.println("Virhekoodi: "+e.getErrorCode());
				System.err.println("SQL-tilakoodi: "+e.getSQLState());
			} while (e.getNextException() != null);
		}
		
	}

}

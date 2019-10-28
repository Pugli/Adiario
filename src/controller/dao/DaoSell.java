package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.model.Product;
import controller.model.Sell;
/**
 * persistance of Sell
 * @author Pugliese, Agustin Gonzalo
 *
 */
public class DaoSell {

	private Connection conexion = null;
	private Statement sentencia;
	private ResultSet resultado;
/**
 * insert into databases values 
 * @param n
 * @param today
 * @param quantity_sell
 */
	public void insert(Product n, LocalDate today, int quantity_sell) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("No se pudo cargar el puente JDBC-ODBC.");
			return;
		}
		try {

			// Se establece la conexión con la base de datos
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			sentencia = conexion.createStatement();
			sentencia.execute("INSERT INTO sell (fk_product,sell_date,sell_value,quantity_sell) " + " VALUES ("
					+ n.getid() + ", \" " + today + " \" ," + n.getValue() + "," + quantity_sell + ");");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Looks for element between two dates
 * @param date1
 * @param date2
 * @return ArrayList<Sell>
 */
	public ArrayList<Sell> search(LocalDate date1, LocalDate date2) {
		ArrayList<Sell> sells = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("No se pudo cargar el puente JDBC-ODBC.");

		}
		try {

			// Se establece la conexión con la base de datos
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery("select p.product_name,p.category,p.product_date,"
					+ "s.id_sell,s.fk_product,s.sell_date,s.sell_value,s.quantity_sell from product as p inner join sell as s ON p.id_product=s.fk_product "
					+ "where s.sell_date BETWEEN \"" + date1 + "\" AND \"" + date2 + "\";");
			while (resultado.next()) {
				Sell n = new Sell(resultado.getInt("id_sell"), resultado.getInt("quantity_sell"),
						resultado.getDate("sell_date"), resultado.getString("product_name"),
						resultado.getFloat("sell_value"));

				sells.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sells;
	}

}
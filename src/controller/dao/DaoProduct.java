package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.model.Product;

/**
 * Persistance of products
 * 
 * @author Pugliese,Agustin Gonzalo
 *
 */
public class DaoProduct {

	private Connection conexion = null;
	private Statement sentencia;
	private ResultSet resultado;

	/**
	 * Insert into database values.
	 * 
	 * @param n
	 */
	public void insert(Product n) {

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
			sentencia.execute("INSERT INTO product (product_name,quantity,product_value,category,product_date) "
					+ "VALUES (\"" + n.getName() + "\",\"" + n.getQuantity() + "\",\"" + n.getValue() + "\",\""
					+ n.getCategory() + "\",\"" + n.getDate() + "\");");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get all data.
	 */

	public ArrayList<Product> getAll() {
		ArrayList<Product> productos = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			String sql;
			sql = "Select * from product";
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				Product n = new Product(resultado.getInt("id_product"), resultado.getString("product_name"),
						resultado.getInt("quantity"), resultado.getInt("quantity_sell"),
						resultado.getFloat("product_value"), resultado.getString("category"),
						resultado.getDate("product_date"));
				productos.add(n);
			}
		} catch (SQLException se) {
			System.out.println("error sql");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("error classforname");
			e.printStackTrace();
		}
		return productos;

	}

	/**
	 * get data by id
	 * 
	 * @param id
	 * @return Product
	 */
	public Product getXId(int id) {
		Product n = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			String sql;
			sql = "Select * from product where id_product=\"" + id + "\";";
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				n = new Product();
			}
		} catch (SQLException se) {
			System.out.println("error sql");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("error classforname");
			e.printStackTrace();
		}
		return n;
	}

	/**
	 * update a product
	 * 
	 * @param product
	 */
	public void udapte(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			String sql;
			sql = "UPDATE product SET product_name = \" " + product.getName() + " \" , quantity =  "
					+ product.getQuantity() + "  , quantity_sell =  " + product.getQuantitySell()
					+ "  , product_value =  " + (float) product.getValue() + "  " + ", category = \" "
					+ product.getCategory() + " \" " + ", product_date = \" " + product.getDate() + " \" "
					+ "WHERE id_product = " + product.getid() + " ; ";
			sentencia = conexion.createStatement();
			sentencia.execute(sql);

		} catch (SQLException se) {
			System.out.println("error sql");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("error classforname");
			e.printStackTrace();
		}
	}

	/**
	 * delete of database
	 * 
	 * @param id
	 */
	public void delete(int id) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			String sql;
			sql = "delete from product where id_product=" + id + ";";
			sentencia = conexion.createStatement();
			sentencia.execute(sql);
		} catch (SQLException se) {
			System.out.println("error sql");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("error classforname");
			e.printStackTrace();
		}
	}

	/**
	 * update the quantity of a product.
	 * 
	 * @param product
	 */
	public void udapteQuantity(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-3",
					"root", "");
			String sql;
			sql = "UPDATE product \n" + "SET quantity =  " + product.getQuantity() + " , quantity_sell= "
					+ product.getQuantitySell() + " WHERE id_product = " + product.getid() + " ; ";
			sentencia = conexion.createStatement();
			sentencia.execute(sql);

		} catch (SQLException se) {
			System.out.println("error sql");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("error classforname");
			e.printStackTrace();
		}
	}
}

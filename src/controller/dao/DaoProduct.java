package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.model.Product;

public class DaoProduct {

	private Connection conexion = null;
    private Statement sentencia;
    private ResultSet resultado;
    
    public void insertar(Product n) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el puente JDBC-ODBC.");
            return;
        }
        try {
            // Se establece la conexión con la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false", "root", "");
            sentencia = conexion.createStatement();
            sentencia.execute("INSERT INTO producto (product_name,quantity,product_value,category,product_date) " +
                    "VALUES (\"" + n.getName() + "\",\"" + n.getQuantity() + "\",\"" + n.getValue() + "\",\"" + n.getCategory() +"\",\"" + n.getDate() + "\");");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.model.Product;

public class DaoSell {

	private Connection conexion = null;
    private Statement sentencia;
    private ResultSet resultado;
    
    public void insert(Product n,LocalDate today, int quantity_sell) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el puente JDBC-ODBC.");
            return;
        }
        try {
        
            // Se establece la conexión con la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProyectoADiario?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            sentencia = conexion.createStatement();
            sentencia.execute("INSERT INTO sell (fk_product,sell_date,sell_value,quantity_sell) " +
                    " VALUES (" + n.getid() + ", \"" + today + "\"," + n.getValue() +","+quantity_sell+");");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package controller.model;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for sell element
 * 
 * @author Pugliese, Agustin Gonzalo
 *
 */
public class Sell {
	private final IntegerProperty id;
	private final StringProperty quantitySell;
	private final StringProperty quantityValue;
	private final ObjectProperty<LocalDate> fecha;
	private final StringProperty nombre;

	public Sell(int id, int quantitySell, Date fecha, String nombre, Float quantityValue) {
		this.id = new SimpleIntegerProperty(id);
		this.quantitySell = new SimpleStringProperty(Integer.toString(quantitySell));
		this.fecha = new SimpleObjectProperty<LocalDate>(fecha.toLocalDate());
		this.nombre = new SimpleStringProperty(nombre);
		this.quantityValue = new SimpleStringProperty(quantityValue.toString());
	}

	/**
	 * Setter and getters of id
	 * 
	 * @return id
	 */
	public int getid() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	/**
	 * Setter and getters of quantitySell
	 * 
	 * @return quantitySell
	 */
	public String getQuantitySell() {
		return quantitySell.get();
	}

	public void setQuantitySell(String quantitySell) {
		this.quantitySell.set(quantitySell);
	}

	public StringProperty quantitySellProperty() {
		return quantitySell;
	}

	/**
	 * Setter and getters of date
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return fecha.get();
	}

	public void setDate(LocalDate date) {
		this.fecha.set(date);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return fecha;
	}

	/**
	 * Setter and getters of name
	 * 
	 * @return name
	 */
	public String getName() {
		return nombre.get();
	}

	public void setName(String name) {
		this.nombre.set(name);
	}

	public StringProperty nameProperty() {
		return nombre;
	}

	/**
	 * Setter and getters of value
	 * 
	 * @return value
	 */

	public String getValue() {
		return quantityValue.get();
	}

	public void setvalue(String value) {
		this.quantityValue.set(value);
	}

	public StringProperty valueProperty() {
		return quantityValue;
	}

}

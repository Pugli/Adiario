package controller.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

/**
 * Model of product
 * 
 * @author Pugliese, Agustin Gonzalo
 *
 */
public class Product {
	private final IntegerProperty id;
	private final StringProperty name;
	private final IntegerProperty quantity;
	private final IntegerProperty quantitySell;
	private final FloatProperty value;
	private final StringProperty category;
	private final ObjectProperty<LocalDate> date;

	/**
	 * Contructor without parameters
	 */
	public Product() {
		this.id = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty(null);
		this.quantity = new SimpleIntegerProperty(0);
		this.quantitySell = new SimpleIntegerProperty(0);
		this.value = new SimpleFloatProperty(0);
		this.category = new SimpleStringProperty(null);
		this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(2019, 1, 1));
	}

	/**
	 * General constructor whit all parameters
	 * 
	 * @param id
	 * @param name
	 * @param quantity
	 * @param quantitySell
	 * @param value
	 * @param category
	 * @param date
	 */
	public Product(int id, String name, int quantity, int quantitySell, Float value, String category, Date date) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.quantitySell = new SimpleIntegerProperty(quantitySell);
		this.value = new SimpleFloatProperty(value);
		this.category = new SimpleStringProperty(category);
		this.date = new SimpleObjectProperty<LocalDate>(date.toLocalDate());
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
	 * Setter and getters of name
	 * 
	 * @return name
	 */
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	/**
	 * Setter and getters of quantity
	 * 
	 * @return quantity
	 */

	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(int quantity) {
		this.quantity.set(quantity - this.getQuantitySell());
	}

	public IntegerProperty quantityProperty() {
		return quantity;
	}

	/**
	 * Setter and getters of quantitySell
	 * 
	 * @return quantitySell
	 */
	public int getQuantitySell() {
		return quantitySell.get();
	}

	public void setQuantitySell(int quantitySell) {
		this.quantitySell.set(this.getQuantitySell() + quantitySell);
	}

	public IntegerProperty quantitySellProperty() {
		return quantitySell;
	}

	/**
	 * Setter and getters of value
	 * 
	 * @return value
	 */

	public Float getValue() {
		return value.get();
	}

	public void setvalue(Float value) {
		this.value.set(value);
	}

	public FloatProperty valueProperty() {
		return value;
	}

	/**
	 * Setter and getters of category
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public StringProperty categoryProperty() {
		return category;
	}

	/**
	 * Setter and getters of date
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(LocalDate date) {
		this.date.set(date);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}

	public Date getTypeDate() {
		Date date1 = (Date) Date.from(this.date.get().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime());
		return sqlStartDate;
	}

}

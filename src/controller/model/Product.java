package controller.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Product {

	//private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final IntegerProperty quantitySell;
    private final FloatProperty value;
    private final StringProperty category;
    //private final ObjectProperty<LocalDate> date;
    
    
    public Product() {
    	this.name = new SimpleStringProperty(null);
		this.quantity = new SimpleIntegerProperty(0);
		this.quantitySell = new SimpleIntegerProperty(0);
		this.value = new SimpleFloatProperty(0);
		this.category = new SimpleStringProperty(null);
    }
    
	public Product (String name, int quantity, int quantitySell,
			Float value, String category) {
		//this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.quantitySell = new SimpleIntegerProperty(quantitySell);
		this.value = new SimpleFloatProperty(value);
		this.category = new SimpleStringProperty(category);
		//this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	

	public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
     
    
    
    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
    
    public IntegerProperty quantityProperty() {
        return quantity;
    }
    
    public int getQuantitySell() {
        return quantitySell.get();
    }

    public void setQuantitySell(int quantitySell) {
        this.quantitySell.set(quantitySell);
    }
    
    public IntegerProperty quantitySellProperty() {
        return quantitySell;
    }
    
    public Float getValue() {
        return value.get();
    }

    public void setvalue(Float value) {
        this.value.set(value);
    }
    
    public FloatProperty valueProperty() {
        return value;
    }
    
    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
    
    public StringProperty categoryProperty() {
        return category;
    }
    
   /* public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
    
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    */
	
}

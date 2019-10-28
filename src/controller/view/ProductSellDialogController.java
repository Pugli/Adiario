package controller.view;



import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import controller.dao.DaoProduct;
import controller.dao.DaoSell;
import controller.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class with dialog for sell products.
 * 
 * @author Pugliese, Agustin Gonzalo
 *
 */
public class ProductSellDialogController {
	@FXML
    private TextField sellField;
	private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;
    private DaoProduct DAOproduct;
    private DaoSell DAOsell;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	try {
    	this.DAOproduct = new DaoProduct();
        this.DAOsell = new DaoSell();
    	}
    	catch(Exception e){
    		this.warning("No se pudo inicializar la ventana");
    	}
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    /**
     * set product
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    /**
     * button OK or "aceptar".
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) { 
            product.setQuantitySell(Integer.parseInt(sellField.getText()));
            product.setQuantity(product.getQuantity());
            this.save(Integer.parseInt(sellField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }
    /**
     * Connection with database for save the quantity sell.
     * @param quantity_sell
     */
    
    private void save(int quantity_sell) {
    	try {
    	DAOproduct.udapteQuantity(this.product);
    	DAOsell.insert(this.product,today(),quantity_sell);
    	}
    	catch(Exception e){
    		this.warning("Hubo un error con la base de datos. ProductSellDialogController");
    	}
    }
    /**
     * get today's date
     * @return dateToday
     */
    private LocalDate today() {
    	Calendar fecha = new GregorianCalendar();
    	Integer año = fecha.get(Calendar.YEAR);
    	Integer mes = fecha.get(Calendar.MONTH) + 1;
    	Integer dia = fecha.get(Calendar.DAY_OF_MONTH);
        LocalDate dateToday =  LocalDate.of(año, mes, dia);
        return dateToday;
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (sellField.getText() == null || sellField.getText().length() == 0) {
            errorMessage += "Numero no valido!\n"; 
        }
        else {
			// try to parse sellField code into an int.
			try {
				int number = Integer.parseInt(sellField.getText());
				if(product.getQuantity()<number){
					errorMessage += "Se excedio de la cantidad de productos disponibles!\n";
				}
				if (number < 0) {
					errorMessage += "Cantidad no puede ser un numero negativo!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Se ingreso un valor incorrecto!\n";
			}
		}
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	// Nothing selected.
        	this.warning(errorMessage);
            return false;
        }
    }

	/**
	 * warning functions for alert an error
	 * 
	 * @param message
	 */
	private void warning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Hubo un problema");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}

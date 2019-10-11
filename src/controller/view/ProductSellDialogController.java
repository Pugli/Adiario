package controller.view;



import controller.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ProductSellDialogController {
	@FXML
    private TextField sellField;
	
	private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    @FXML
    private void handleOk() {
        if (isInputValid()) { 
            product.setQuantitySell(Integer.parseInt(sellField.getText()));
            product.setQuantity(product.getQuantity());
            
            okClicked = true;
            dialogStage.close();
        }
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
            errorMessage += "No valid name!\n"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	// Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Sin seleccion");
        	alert.setHeaderText(null);
        	alert.setContentText("invalid field");
        	alert.showAndWait();
            return false;
        }
    }
}

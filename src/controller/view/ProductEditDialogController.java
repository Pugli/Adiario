package controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import controller.dao.DaoProduct;
import controller.model.*;
import controller.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class ProductEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField valueField;
   // @FXML
   // private TextField cityField;
    @FXML
    private TextField dateField;

    private int id;
    private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;
    
    private DaoProduct DAOproduct = new DaoProduct();

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

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setProduct(Product product) {
        this.product = product;
         this.id= product.getid();
        nameField.setText(product.getName());
        categoryField.setText(product.getCategory());
        quantityField.setText(Integer.toString(product.getQuantity()));
        valueField.setText(Float.toString(product.getValue()));
       // cityField.setText(person.getCity());
        dateField.setText(DateUtil.format(product.getDate()));
        dateField.setPromptText("dd.mm.yyyy");
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            product.setName(nameField.getText());
            product.setCategory(categoryField.getText());
            product.setQuantity(Integer.parseInt(quantityField.getText()));
            product.setvalue(Float.parseFloat(valueField.getText()));
            product.setQuantitySell(0);
            product.setDate(DateUtil.parse(dateField.getText()));
            this.save();
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private void save() {
    	if(this.DAOproduct.getXId(this.id)==null) {
    		this.DAOproduct.insert(product);
    	}
    	else {
    	this.DAOproduct.udapte(product);
    	}
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

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }
        else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid quantity (must be an integer)!\n"; 
            }
        }

        if (valueField.getText() == null || valueField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } 
        else {
            // try to parse the postal code into an int.
            try {
               // Integer.parseInt(valueField.getText());
                Float.parseFloat(valueField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid value (must be an float)!\n"; 
            }
        }

        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "No valid date!\n";
        } else {
            if (!DateUtil.validDate(dateField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	// Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Sin seleccion");
        	alert.setHeaderText(null);
        	alert.setContentText(errorMessage);
        	alert.showAndWait();
            return false;
        }
    }
}
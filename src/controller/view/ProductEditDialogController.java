package controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



import controller.model.*;

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
  //  @FXML
   // private TextField birthdayField;


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

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setProduct(Product product) {
        this.product = product;

        nameField.setText(product.getName());
        categoryField.setText(product.getCategory());
        quantityField.setText(Integer.toString(product.getQuantity()));
        valueField.setText(Float.toString(product.getValue()));
       // cityField.setText(person.getCity());
       // birthdayField.setText(DateUtil.format(person.getBirthday()));
       // birthdayField.setPromptText("dd.mm.yyyy");
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

            okClicked = true;
            dialogStage.close();
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

        if (valueField.getText() == null || valueField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } 

       
        if (errorMessage.length() == 0) {
            return true;
        } else {
        	// Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Sin seleccion");
        	alert.setHeaderText(null);
        	alert.setContentText("invalid");
        	alert.showAndWait();
            return false;
        }
    }
}
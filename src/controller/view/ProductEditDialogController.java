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
 * Dialog to edit details of a product.
 * 
 * @author Pugliese, Agustin Gonzalo
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
	@FXML
	private TextField dateField;

	private int id;
	private Stage dialogStage;
	private Product product;
	private boolean okClicked = false;
	private DaoProduct DAOproduct = new DaoProduct();

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
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
	 * Sets the product to be edited in the dialog.
	 * 
	 * @param Product
	 */
	public void setProduct(Product product) {
		this.product = product;
		this.id = product.getid();
		nameField.setText(product.getName());
		categoryField.setText(product.getCategory());
		quantityField.setText(Integer.toString(product.getQuantity()));
		valueField.setText(Float.toString(product.getValue()));
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

	/**
	 * Save or update product if the product was created or not.
	 */
	private void save() {
		try {
			if (this.DAOproduct.getXId(this.id) == null) {
				this.DAOproduct.insert(product);
			} else {
				this.DAOproduct.udapte(product);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Hubo un error");
			alert.setHeaderText(null);
			alert.setContentText("Hubo un error");
			alert.showAndWait();
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
			errorMessage += "Nombre no valido!\n";
		}
		if (categoryField.getText() == null || categoryField.getText().length() == 0) {
			errorMessage += "Categoria no valida!\n";
		}
		if (quantityField.getText() == null || quantityField.getText().length() == 0) {
			errorMessage += "Cantidad no valida!\n";
		} else {
			// try to parse quantityField code into an int.
			try {
				int number = Integer.parseInt(quantityField.getText());
				if (number < 0) {
					errorMessage += "Cantidad no puede ser un numero negativo!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Se ingreso un valor incorrecto!\n";
			}
		}

		if (valueField.getText() == null || valueField.getText().length() == 0) {
			errorMessage += "Valor no valido!\n";
		} else {
			// try to parse the valueField into a float.
			try {
				Float numberF = Float.parseFloat(valueField.getText());
				if (numberF < 0) {
					errorMessage += "Valor no puede ser un numero negativo!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Se ingreso un valor incorrecto!\n";
			}
		}

		if (dateField.getText() == null || dateField.getText().length() == 0) {
			errorMessage += "Fecha no valida!\n";
		} else {
			if (!DateUtil.validDate(dateField.getText())) {
				errorMessage += "Fecha no valida. Usar el formato dd.mm.yyyy!\n";
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
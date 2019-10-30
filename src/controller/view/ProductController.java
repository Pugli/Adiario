package controller.view;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import controller.MainApp;
import controller.dao.DaoProduct;
import controller.model.Product;
import controller.util.DateUtil;

/**
 * Interface with table and information about product. button for all actions.
 * 
 * @author Pugliese, Agustin Gonzalo
 */

public class ProductController {
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, String> categoryColumn;
	@FXML
	private TextField filterField;
	@FXML
	private Label nameLabel;
	@FXML
	private Label categoryLabel;
	@FXML
	private Label quantityLabel;
	@FXML
	private Label quantitySellLabel;
	@FXML
	private Label valuelLabel;
	@FXML
	private Label dateLabel;
	private ObservableList<Product> masterData = FXCollections.observableArrayList();
	// Reference to the main application.
	private MainApp mainApp;
	private DaoProduct DAOproduct;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public ProductController() {
		this.DAOproduct = new DaoProduct();
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the product table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
		// clear product details
		showProductDetails(null);
		productTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showProductDetails(newValue));
	}

	/**
	 * filter table for search specify element
	 * 
	 * @param event
	 */
	@FXML
	private void filterAndSorting(KeyEvent event) {
		String filterName = this.filterField.getText();
		if (filterName.isEmpty()) {
			this.productTable.setItems(mainApp.getProductData());
		} else {
			this.masterData.clear();
			for (Product p : this.mainApp.getProductData()) {
				if (p.getName().toLowerCase().contains(filterName.toLowerCase())) {
					this.masterData.add(p); // Filter matches first name.
				}
			}
			this.productTable.setItems(masterData);
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		// set data
		ArrayList<Product> daoProduct;
		try {
			daoProduct = DAOproduct.getAll();
			for (Product n : daoProduct) {
				mainApp.getProductData().add(n);
				
			}
			// Add observable list data to the table
			productTable.setItems(mainApp.getProductData());
		} catch (Exception e) {
			this.warning("Hubo un error");
		}
	}

	/**
	 * Fills all text fields to show details about the product. If the specified
	 * product is null, all text fields are cleared.
	 * 
	 * @param product or null
	 */
	private void showProductDetails(Product product) {
		if (product != null) {
			// Fill the labels with info from the person object.
			nameLabel.setText(product.getName());
			categoryLabel.setText(product.getCategory());
			quantityLabel.setText(Integer.toString(product.getQuantity()));
			quantitySellLabel.setText(Integer.toString(product.getQuantitySell()));
			valuelLabel.setText(Float.toString(product.getValue()));
			dateLabel.setText(DateUtil.format(product.getDate()));
		} else {
			// Person is null, remove all the text.
			nameLabel.setText("");
			categoryLabel.setText("");
			quantityLabel.setText("");
			quantitySellLabel.setText("");
			valuelLabel.setText("");
			dateLabel.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			DAOproduct.delete(productTable.getItems().get(selectedIndex).getid());
			productTable.getItems().remove(selectedIndex);

		} else {
			// Nothing selected.
			this.warning("Por favor, seleccione un producto de la tabla");
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details
	 * for a new product.
	 */
	@FXML
	private void handleNewProduct() {
		Product tempProduct = new Product();
		boolean okClicked = mainApp.showProductEditDialog(tempProduct);
		if (okClicked) {
			productTable.getItems().clear();
			mainApp.getProductData().removeAll();
			ArrayList<Product> daoProduct;
			try {
				daoProduct = DAOproduct.getAll();
				for (Product n : daoProduct) {
					mainApp.getProductData().add(n);
				}
				// Add observable list data to the table
				
				
				productTable.setItems(mainApp.getProductData());
			} catch (Exception e) {
				this.warning("Hubo un error");
			}
			
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit details
	 * for the selected product.
	 */
	@FXML
	private void handleEditPerson() {
		Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
		if (selectedProduct != null) {
			boolean okClicked = mainApp.showProductEditDialog(selectedProduct);
			if (okClicked) {
				showProductDetails(selectedProduct);
			}
		} else {
			// Nothing selected.
			this.warning("Por favor, seleccione un producto de la tabla");
		}
	}

	/**
	 * sell of product
	 */
	@FXML
	private void sell() {
		Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
		if (selectedProduct != null) {
			boolean okClicked = mainApp.showProductSellDialog(selectedProduct);
			if (okClicked) {
				showProductDetails(selectedProduct);
			}

		} else {
			// Nothing selected.
			this.warning("Por favor, seleccione un producto de la tabla");
		}
	}

	/*
	 * Function for search sells between determinate dates.
	 */
	@FXML
	private void searchSell() {
		mainApp.showSearchSellDialog();
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
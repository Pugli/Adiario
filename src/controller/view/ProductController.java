package controller.view;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

//import org.controlsfx.dialog.Dialogs;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import controller.MainApp;
import controller.model.Product;

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
   // @FXML
  //  private Label dateLabel;

    private ObservableList<Product> masterData = FXCollections.observableArrayList();
    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProductController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
    	
    	//clear product details
    	showProductDetails(null);
    	
    	productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));
    	
    	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredData = new FilteredList<>(masterData, p -> true);
    
     // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (product.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(productTable.comparatorProperty());
        
     // 5. Add sorted (and filtered) data to the table.
        productTable.setItems(sortedData);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        productTable.setItems(mainApp.getProductData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showProductDetails(Product product) {
        if (product != null) {
            // Fill the labels with info from the person object.
            nameLabel.setText(product.getName());
            categoryLabel.setText(product.getCategory());
            quantityLabel.setText(Integer.toString(product.getQuantity()));
            quantitySellLabel.setText(Integer.toString(product.getQuantitySell()));
            valuelLabel.setText(Float.toString(product.getValue()));

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            nameLabel.setText("");
            categoryLabel.setText("");
            quantityLabel.setText("");
            quantitySellLabel.setText("");
            valuelLabel.setText("");
            //dateLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteProduct() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Sin seleccion");
        	alert.setHeaderText(null);
        	alert.setContentText("Por favor, seleccione un producto de la tabla");
        	alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewProduct() {
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showProductEditDialog(tempProduct);
        if (okClicked) {
            mainApp.getProductData().add(tempProduct);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
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
        	// Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Sin seleccion");
        	alert.setHeaderText(null);
        	alert.setContentText("Por favor, seleccione un producto de la tabla");
        	alert.showAndWait();
        }
    }
    
    /**
     * sell of product
     * 
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
        	// Nothing selected.
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Sin seleccion");
        	alert.setHeaderText(null);
        	alert.setContentText("invalid");
        	alert.showAndWait();
        }
    }
}
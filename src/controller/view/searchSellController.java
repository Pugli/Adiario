package controller.view;

import java.time.LocalDate;
import java.util.ArrayList;
import controller.dao.DaoSell;
import controller.model.Sell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * Class for search all sell between determinate dates.
 * @author Pugliese, Agustin Gonzalo
 *
 */

public class SearchSellController extends ProductController {
	@FXML
    private TextField dayField1;
	@FXML
    private TextField monthField1;
	@FXML
    private TextField yearField1;
	@FXML
    private TextField dayField2;
	@FXML
    private TextField monthField2;
	@FXML
    private TextField yearField2;
	@FXML
	private TableView<Sell> sellTable;
	@FXML
	private TableColumn<Sell, String> nameColumn;
	@FXML
	private TableColumn<Sell, LocalDate> dateColumn;
	@FXML
	private TableColumn<Sell, String> quantityColumn;
	@FXML
	private TableColumn<Sell, String> valueColumn;
	private Stage dialogStage;
    private boolean okClicked = false;
    private ObservableList<Sell> masterData = FXCollections.observableArrayList();
    private DaoSell daoSell;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	this.daoSell = new DaoSell();
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
	 * Called when the user clicks ok. transform numbers to dates and search in databases elements.
	 */
    @FXML
    private void handleOk() {
        if (isInputValid()) { 
        	Integer day1=Integer.parseInt(dayField1.getText());
        	Integer month1=Integer.parseInt(monthField1.getText());
        	Integer year1=Integer.parseInt(yearField1.getText());
        	LocalDate dateToday =  LocalDate.of(year1, month1, day1);
        	Integer day2=Integer.parseInt(dayField2.getText());
        	Integer month2=Integer.parseInt(monthField2.getText());
        	Integer year2=Integer.parseInt(yearField2.getText());
        	LocalDate dateToday2 =  LocalDate.of(year2, month2, day2);
        	ArrayList<Sell> arraySell;
        	try {
        	arraySell=daoSell.search(dateToday, dateToday2);
        	for (Sell n: arraySell) {
        		masterData.add(n);
           }
        	}
        	catch(Exception e) {
        		this.warning("Ocurrio un problema con la base de datos. SearchSellController");
        	}
        	sellTable.getItems().clear();
        	sellTable.setItems(masterData);
        	nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        	dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        	quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantitySellProperty());
        	valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
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
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (dayField1.getText() == null || dayField1.getText().length() == 0) {
			errorMessage += "Dia no valido!\n";
		} else {
			// try to parse the day into an int.
			try {
				int day1 = Integer.parseInt(dayField1.getText());
				if (day1 < 1 || day1 > 31) {
					errorMessage += "Dia no valido!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Debe ingresar un caracter numerico!\n";
			}
		}

		if (dayField2.getText() == null || dayField2.getText().length() == 0) {
			errorMessage += "Dia no valido!\n";
		} else {
			// try to parse the day into an int.
			try {
				int day1 = Integer.parseInt(dayField2.getText());
				if (day1 < 1 || day1 > 31) {
					errorMessage += "Dia no valido!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Debe ingresar un caracter numerico!\n";
			}
		}

		if (monthField1.getText() == null || monthField1.getText().length() == 0) {
			errorMessage += "mes no valido!\n";
		} else {
			// try to parse month into an int.
			try {
				int day1 = Integer.parseInt(monthField1.getText());
				if (day1 < 1 || day1 > 12) {
					errorMessage += "mes no valido!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Debe ingresar un caracter numerico!\n";
			}
		}

		if (monthField2.getText() == null || monthField2.getText().length() == 0) {
			errorMessage += "mes no valido!\n";
		} else {
			// try to parse month into an int.
			try {
				int day1 = Integer.parseInt(monthField2.getText());
				if (day1 < 1 || day1 > 12) {
					errorMessage += "mes no valido!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Debe ingresar un caracter numerico!\n";
			}
		}

		if (yearField1.getText() == null || yearField1.getText().length() == 0) {
			errorMessage += "Fecha no valida!\n";
		} else {
			// try to parse year into an int.
			try {
				int day1 = Integer.parseInt(yearField1.getText());
				if (day1 < 2000 || day1 > 2050) {
					errorMessage += "Fecha no valida!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Debe ingresar un caracter numerico!\n";
			}
		}

		if (yearField2.getText() == null || yearField2.getText().length() == 0) {
			errorMessage += "Fecha no valida!\n";
		} else {
			// try to parse year into an int.
			try {
				int day1 = Integer.parseInt(yearField2.getText());
				if (day1 < 2000 || day1 > 2050) {
					errorMessage += "Fecha no valida!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Debe ingresar un caracter numerico!\n";
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

package controller.view;


import java.util.Date;

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
import controller.util.DateUtil;

public class ProductSellDialogController {
	@FXML
    private TextField sellField;
	
	private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;
    
    private DaoProduct DAOproduct = new DaoProduct();
    private DaoSell DAOsell = new DaoSell();
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
            this.save(Integer.parseInt(sellField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }
    
    
    private void save(int quantity_sell) {
    	DAOproduct.udapteQuantity(this.product);
    	DAOsell.insert(this.product,today(),quantity_sell);
    	
    }
    private LocalDate today() {
    	Calendar fecha = new GregorianCalendar();
    	Integer año = fecha.get(Calendar.YEAR);
    	Integer mes = fecha.get(Calendar.MONTH) + 1;
    	Integer dia = fecha.get(Calendar.DAY_OF_MONTH);
        
       /* String sAño = año.toString();
        String sMes = mes.toString();
        String sDia = dia.toString();
        
        String sFecha = sDia+"."+sMes+"."+sAño;
        System.out.println(sFecha);*/
        //Date date = fecha.getTime();
       
       
        
        /*String c = fecha.toString();
        System.out.println(c);
        LocalDate dateToday = DateUtil.parse(c);*/
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

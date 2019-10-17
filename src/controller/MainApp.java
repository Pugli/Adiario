package controller;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.gluonhq.charm.glisten.control.TextField; 
import controller.model.Product;
import controller.view.ProductController;
import controller.view.ProductEditDialogController;
import controller.view.ProductSellDialogController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Product> ProductData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
    	ProductData.add(new Product(1,"La capital",100,0,(float) 30,"Diario"));
    	ProductData.add(new Product(2,"las flores",30,0,(float) 100,"Revista"));
    	ProductData.add(new Product(3,"Batman vs Superman",10,0,(float) 500,"Comic"));
        ProductData.add(new Product(4,"Perritos y como educarlos",20,0,(float) 30,"Revista"));
        ProductData.add(new Product(5,"Clarin",100,0,(float) 350,"Diario"));
        ProductData.add(new Product(6,"mi valijita",45,0,(float) 120,"Infantil"));
        ProductData.add(new Product(8,"argentina",55,0,(float) 70,"Revista"));
        ProductData.add(new Product(9,"Shazam",25,0,(float) 200,"Comic"));
        ProductData.add(new Product(10,"One piece Ikea",4,0,(float) 450,"figuras"));
    }
  
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Product> getProductData() {
        return ProductData;
    }
   
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Adiario");

        initRootLayout();

        showProductOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    /*
    public void showProductOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Product.fxml"));
           // loader.setLocation(MainApp.class.getResource("../view/Product.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(productOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    */
    /**
     * Shows the person overview inside the root layout.
     */
    
    
    public void showProductOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Product.fxml"));
            AnchorPane product = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(product);

            // Give the controller access to the main app.
            ProductController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showProductEditDialog(Product product) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar producto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProductEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showProductSellDialog(Product product) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/sell.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Vender");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProductSellDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getProductFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setProductFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Adiario - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Adiario");
        }
    }
}

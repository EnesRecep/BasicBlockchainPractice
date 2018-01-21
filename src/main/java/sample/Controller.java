package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField addData;
    @FXML
    private Button control;
    @FXML
    private Button addButton;

    public void onControl(){
        Blockchain.control();
    }

    public void addBlock(){

        Blockchain.addBlock(addData.getText());
    }
}

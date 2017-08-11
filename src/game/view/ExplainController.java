/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ExplainController implements Initializable {
    
    @FXML
    private AnchorPane anchor = new AnchorPane();
    
    String fondo = "sources/fondos/botones.jpg";
    
    @FXML
    private void close(ActionEvent event) throws IOException{
        
        Stage NextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        NextStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         anchor.setStyle("-fx-background-image: url('" + fondo + "');" /*+ "-fx-background-position: center center; "*/ + "-fx-background-repeat: stretch;");
    }    
    
}

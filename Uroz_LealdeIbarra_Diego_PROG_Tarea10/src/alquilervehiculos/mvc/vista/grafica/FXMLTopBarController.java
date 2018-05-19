/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class FXMLTopBarController implements Initializable
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private ImageView img_clients, img_vehicles, img_rents, img_door;

    @FXML
    private AnchorPane v_clients, v_vehicles, v_rents;

    @FXML
    private Button btn_c_new, btn_c_search, btn_v_new, btn_v_search, btn_r_new, btn_r_search;

    @FXML
    private void handleBarButtonAction(MouseEvent event)
    {
        if (event.getTarget() == img_clients)
        {
            v_clients.setVisible(true);
            v_vehicles.setVisible(false);
            v_rents.setVisible(false);
        } else if (event.getTarget() == img_vehicles)
        {
            v_clients.setVisible(false);
            v_vehicles.setVisible(true);
            v_rents.setVisible(false);
        } else if (event.getTarget() == img_rents)
        {
            v_clients.setVisible(false);
            v_vehicles.setVisible(false);
            v_rents.setVisible(true);
        } else if (event.getTarget() == img_door)
        {
            Platform.exit();
        }
    }

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_c_new)
        {
            sceneLoad(event, "clients/FXMLClientsNew.fxml");
        } else if (event.getSource() == btn_c_search)
        {
            sceneLoad(event, "clients/FXMLClientSearch.fxml");
        } else if (event.getSource() == btn_v_new)
        {
            sceneLoad(event, "vehicles/FXMLVehiclesNew.fxml");
        } else if (event.getSource() == btn_v_search)
        {
            sceneLoad(event, "vehicles/FXMLVehiclesSearch.fxml");
        } else if (event.getSource() == btn_r_new)
        {
            sceneLoad(event, "rents/FXMLRentsNew.fxml");
        } else if (event.getSource() == btn_r_search)
        {
            sceneLoad(event, "rents/FXMLRentsSearch.fxml");
        }
    }

    private void sceneLoad(ActionEvent event, String fxmlFile) throws IOException
    {
        Parent parentView = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene Scene = new Scene(parentView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneMove(parentView, stage);
        stage.setScene(Scene);
        stage.show();
    }
    

    private void sceneMove(Parent parent, Stage stage)
    {
        parent.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        }
        );

        parent.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        }
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}

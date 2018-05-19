/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.clients;

import alquilervehiculos.mvc.vista.grafica.Mensajes;
import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLClientsNewController
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button btn_accept, btn_cancel;

    @FXML
    private TextField tFNombre, tFDni, tFCalle, tFLocalidad, tFCodigoP;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            try
            {
                JavaFXMainStage.controlador.anadirCliente(new Cliente(tFNombre.getText(), tFDni.getText(), tFCalle.getText(), tFLocalidad.getText(), tFCodigoP.getText()));
                Mensajes.mostrarInfo("Clientes", "Cliente añadido");
                JavaFXMainStage.controlador.modelo.escribirClientes();
            } catch (ExcepcionAlquilerVehiculos e)
            {
                Mensajes.mostrarError("Clientes", e.getMessage());
            }
        } else if (event.getSource() == btn_cancel)
        {
            loadScene(event, "../FXMLTopBar.fxml");
        }
    }

    private void loadScene(ActionEvent event, String fxmlString) throws IOException
    {
        Parent root1 = FXMLLoader.load(getClass().getResource(fxmlString));
        Scene scene1 = new Scene(root1);
        scene1.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        moveScene(root1, stage);
        stage.setScene(scene1);
        stage.show();
    }

    private void moveScene(Parent root1, Stage stage)
    {
        root1.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        }
        );
        
        root1.setOnMouseDragged(new EventHandler<MouseEvent>()
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

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize()
    {
        // TODO
    }

}

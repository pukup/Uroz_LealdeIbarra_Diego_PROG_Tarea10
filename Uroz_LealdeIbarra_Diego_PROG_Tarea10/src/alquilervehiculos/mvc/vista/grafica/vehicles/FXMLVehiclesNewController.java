/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.vehicles;

import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import alquilervehiculos.mvc.vista.grafica.Mensajes;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLVehiclesNewController
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton rBTurismo, rBAutobus, rBCarga;

    @FXML
    private TextField tFMarca, tFModelo, tFMatricula, tFCilindrada, tFNumeroP, tFPma;

    @FXML
    private Button btn_accept, btn_cancel;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            Vehiculo vehiculo = null;
            if (rBTurismo.isSelected())
            {
                try
                {
                    vehiculo = TipoVehiculo.TURISMO.getInstancia(tFMatricula.getText(), tFMarca.getText(), tFModelo.getText(), Integer.parseInt(tFCilindrada.getText()), Integer.parseInt(tFNumeroP.getText()), Integer.parseInt(tFPma.getText()));
                    JavaFXMainStage.controlador.anadirVehiculo(vehiculo);
                    Mensajes.mostrarInfo("Vehículos", "Vehículo añadido");
                } catch (ExcepcionAlquilerVehiculos e)
                {
                    Mensajes.mostrarError("Vehículos", e.getMessage());
                }
            } else if (rBAutobus.isSelected())
            {
                try
                {
                    vehiculo = TipoVehiculo.AUTOBUS.getInstancia(tFMatricula.getText(), tFMarca.getText(), tFModelo.getText(), Integer.parseInt(tFCilindrada.getText()), Integer.parseInt(tFNumeroP.getText()), Integer.parseInt(tFPma.getText()));
                    JavaFXMainStage.controlador.anadirVehiculo(vehiculo);
                    Mensajes.mostrarInfo("Vehículos", "Vehículo añadido");
                } catch (ExcepcionAlquilerVehiculos e)
                {
                    Mensajes.mostrarError("Vehículos", e.getMessage());
                }
            } else if (rBCarga.isSelected())
            {
                try
                {
                    vehiculo = TipoVehiculo.DE_CARGA.getInstancia(tFMatricula.getText(), tFMarca.getText(), tFModelo.getText(), Integer.parseInt(tFCilindrada.getText()), Integer.parseInt(tFNumeroP.getText()), Integer.parseInt(tFPma.getText()));
                    JavaFXMainStage.controlador.anadirVehiculo(vehiculo);
                    Mensajes.mostrarInfo("Vehículos", "Vehíuclo añadido");
                } catch (ExcepcionAlquilerVehiculos e)
                {
                    Mensajes.mostrarError("Vehículos", e.getMessage());
                }
            } else
            {
                Mensajes.mostrarError("Vehíuclo", "Debe seleccionar un tipo");
            }
            try
            {
                JavaFXMainStage.controlador.modelo.escribirVehiculos();
            } catch (ExcepcionAlquilerVehiculos e)
            {
                Mensajes.mostrarError("Vehículos", e.getMessage());
            }
        } else if (event.getSource()
                == btn_cancel)
        {
            loadScene(event, "../FXMLTopBar.fxml");
        }
    }

    private void setRadioButtons()
    {
        rBTurismo.setToggleGroup(group);
        rBCarga.setToggleGroup(group);
        rBAutobus.setToggleGroup(group);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.vehicles;

import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import alquilervehiculos.mvc.vista.grafica.Mensajes;
import alquilervehiculos.mvc.vista.grafica.vehicles.vehiclesrents.FXMLVehiclesRentsController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLVehiclesSearchController
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField tFDisponible, tFMarca, tFModelo, tFMatricula, tFCilindrada, tFNumeroP, tFPma, tFBuscar;

    @FXML
    private ListView<String> lista;

    @FXML
    private Button btn_accept, btn_cancel, btn_delete;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            if (Vehiculo.compruebaMatricula(tFBuscar.getText()))
            {
                String matricula = tFBuscar.getText();
                Vehiculo vehiculo = JavaFXMainStage.controlador.buscarVehiculo(matricula);
                showVehiclesRents(event, vehiculo);
            } else
            {
                Mensajes.mostrarError("Vehículos", "Matrícula incorrecta.");
            }

        } else if (event.getSource() == btn_delete)
        {
            String matriculaString = lista.getSelectionModel().getSelectedItem().toString();
            JavaFXMainStage.controlador.borrarVehiculo(matriculaString);
            JavaFXMainStage.controlador.modelo.escribirVehiculos();
            Mensajes.mostrarInfo("Vehiculos", "Eliminado.");
            listarVehiculos();
            clearTextFields();
        } else if (event.getSource() == btn_cancel)
        {
            loadScene(event, "../FXMLTopBar.fxml");
        }
    }

    private void displayTextFields(Vehiculo vehiculo)
    {
        tFDisponible.setText(vehiculo.getDisponibleToString() + " " + vehiculo.getTipoVehiculo().toString());
        tFMarca.setText(vehiculo.getMarca());
        tFModelo.setText(vehiculo.getModelo());
        tFMatricula.setText(vehiculo.getMatricula());
        tFCilindrada.setText(String.valueOf(vehiculo.getDatosTecnicos().getCilindrada()));
        tFNumeroP.setText(String.valueOf(vehiculo.getDatosTecnicos().getNumeroPlazas()));
        tFPma.setText(String.valueOf(vehiculo.getDatosTecnicos().getPma()));
    }
    
        private void clearTextFields()
    {
        tFDisponible.setText("");
        tFMarca.setText("");
        tFModelo.setText("");
        tFMatricula.setText("");
        tFCilindrada.setText("");
        tFNumeroP.setText("");
        tFPma.setText("");
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

    public void showVehiclesRents(ActionEvent event, Vehiculo vehiculo) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vehiclesrents/FXMLVehiclesRents.fxml"));
        Scene scene2 = new Scene((Pane) loader.load());
        scene2.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLVehiclesRentsController vehiclesRentsController = loader.<FXMLVehiclesRentsController>getController();
        vehiclesRentsController.initData(vehiculo);

        stage.setScene(scene2);
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

    @FXML
    private void listClicked(MouseEvent event)
    {
        String matriculaString = lista.getSelectionModel().getSelectedItem().toString();
        try
        {
            Vehiculo vehiculo = JavaFXMainStage.controlador.buscarVehiculo(matriculaString);
            displayTextFields(vehiculo);
        } catch (ExcepcionAlquilerVehiculos e)
        {
            Mensajes.mostrarError("Vehiculos", e.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize()
    {
        listarVehiculos();
    }

    private void listarVehiculos()
    {
        ObservableList<String> vehiculos = FXCollections.observableArrayList();
        for (Vehiculo vehiculo : JavaFXMainStage.controlador.obtenerVehiculos())
        {
            vehiculos.add(vehiculo.getMatricula());
        }
        lista.setItems(vehiculos);
    }

}

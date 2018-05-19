/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.rents;

import alquilervehiculos.mvc.modelo.dominio.Alquiler;
import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import alquilervehiculos.mvc.vista.grafica.Mensajes;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLRentsNewController
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button btn_accept, btn_cancel;

    @FXML
    private ListView<Cliente> listC;

    @FXML
    private ListView<Vehiculo> listV;

    @FXML
    private TextField tFDni, tFMatricula;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            try
            {
                Cliente cliente = JavaFXMainStage.controlador.buscarCliente(tFDni.getText());
                Vehiculo vehiculo = JavaFXMainStage.controlador.buscarVehiculo(tFMatricula.getText());
                JavaFXMainStage.controlador.abrirAlquiler(cliente, vehiculo);
                JavaFXMainStage.controlador.modelo.escribirAlquileres();
                Mensajes.mostrarInfo("Alquileres", "Alquiler añadido");
                listV.refresh();
                listC.refresh();
            } catch (ExcepcionAlquilerVehiculos e)
            {
                Mensajes.mostrarError("Alquileres", e.getMessage());
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
    public void initialize()
    {
        listarVehiculos();
        listarClientes();
    }

    private void listarClientes()
    {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (Cliente cliente : JavaFXMainStage.controlador.obtenerClientes())
        {
            clientes.add(cliente);
        }
        listC.setItems(clientes);
    }

    private void listarVehiculos()
    {
        ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList();
        for (Vehiculo vehiculo : JavaFXMainStage.controlador.obtenerVehiculos())
        {
            vehiculos.add(vehiculo);
        }
        listV.setItems(vehiculos);
    }

    @FXML
    private void listClicked(MouseEvent event)
    {
        try
        {
            Cliente cliente = listC.getSelectionModel().getSelectedItem();
            tFDni.setText(cliente.getDni());
            Vehiculo vehiculo = listV.getSelectionModel().getSelectedItem();
            tFMatricula.setText(vehiculo.getMatricula());
        } catch (ExcepcionAlquilerVehiculos e)
        {
            Mensajes.mostrarError("Alquileres", e.getMessage());
        }
    }

}

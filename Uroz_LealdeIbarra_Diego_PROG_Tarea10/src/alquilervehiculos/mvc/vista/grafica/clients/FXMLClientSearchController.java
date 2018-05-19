/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.clients;

import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import alquilervehiculos.mvc.vista.grafica.Mensajes;
import alquilervehiculos.mvc.vista.grafica.clients.clientsrents.FXMLClientsRentsController;
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
public class FXMLClientSearchController
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField tFId, tFNombre, tFDni, tFCalle, tFLocalidad, tFCodigoP, tFBuscar;

    @FXML
    private ListView<String> lista;

    @FXML
    private Button btn_accept, btn_cancel, btn_delete;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            if (Cliente.formatoDniCorrecto(tFBuscar.getText()))
            {
                String dniCliente = tFBuscar.getText();
                Cliente cliente = JavaFXMainStage.controlador.buscarCliente(dniCliente);
                showClientsRents(event, cliente);
            } else
            {
                Mensajes.mostrarError("Clientes", "DNI incorrecto.");
            }
        } else if (event.getSource() == btn_delete)
        {
            String dniString = lista.getSelectionModel().getSelectedItem().toString();
            JavaFXMainStage.controlador.borrarCliente(dniString);
            JavaFXMainStage.controlador.modelo.escribirClientes();
            Mensajes.mostrarInfo("Clientes", "Eliminado.");
            listarClientes();
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

    public void showClientsRents(ActionEvent event, Cliente cliente) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientsrents/FXMLClientsRents.fxml"));
        Scene scene2 = new Scene((Pane) loader.load());
        scene2.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLClientsRentsController clientsRentsController = loader.<FXMLClientsRentsController>getController();
        clientsRentsController.initData(cliente);

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
        try
        {
            String dniString = lista.getSelectionModel().getSelectedItem().toString();
            Cliente cliente = JavaFXMainStage.controlador.buscarCliente(dniString);
            displayTextFields(cliente);
        } catch (ExcepcionAlquilerVehiculos e)
        {
            Mensajes.mostrarError("Clientes", e.getMessage());
        }
    }

    private void displayTextFields(Cliente cliente)
    {
        tFId.setText("Cliente -" + String.valueOf(cliente.getIdentificador() + "-"));
        tFNombre.setText(cliente.getNombre());
        tFDni.setText(cliente.getDni());
        tFCalle.setText(cliente.getDireccionPostal().getCalle());
        tFLocalidad.setText(cliente.getDireccionPostal().getLocalidad());
        tFCodigoP.setText(cliente.getDireccionPostal().getCodigoPostal());
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize()
    {
        listarClientes();
    }

    private void listarClientes()
    {
        ObservableList<String> clientes = FXCollections.observableArrayList();
        for (Cliente cliente : JavaFXMainStage.controlador.obtenerClientes())
        {
            clientes.add(cliente.getDni());
        }
        lista.setItems(clientes);
    }

}

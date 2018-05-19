/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.clients.clientsrents;

import alquilervehiculos.mvc.modelo.dominio.Alquiler;
import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
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
public class FXMLClientsRentsController
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField tFVigente, tFCliente, tFVehiculo, tFFechaI, tFFechaF, tFPrecio;

    @FXML
    private ListView<Alquiler> lista;

    @FXML
    private Button btn_cancel, btn_delete;
    private String stringDni;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_delete)
        {
            try
            {
                String matriculaString = lista.getSelectionModel().getSelectedItem().getVehiculo().getMatricula();
                JavaFXMainStage.controlador.cerrarAlquiler(matriculaString);
                JavaFXMainStage.controlador.modelo.escribirAlquileres();
                Mensajes.mostrarInfo("Clientes", "Cerrado.");
                lista.refresh();

            } catch (ExcepcionAlquilerVehiculos e)
            {
                Mensajes.mostrarError("Clientes", e.getMessage());
            }
        } else if (event.getSource() == btn_cancel)
        {
            loadScene(event, "../FXMLClientSearch.fxml");
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

    @FXML
    public void initData(Cliente cliente)
    {
        this.stringDni = cliente.getDni();
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

    private void displayTextFields(Alquiler alquiler)
    {
        tFVigente.setText(alquiler.getVehiculo().getTipoVehiculo().toString() + " " + alquiler.getEstadoAlquilerString());
        tFCliente.setText(alquiler.getCliente().getNombre());
        tFVehiculo.setText(alquiler.getVehiculo().getMatricula());
        tFFechaI.setText(alquiler.getFechaInicioAlquiler().toString());
        if (alquiler.getAlquilerAbierto())
        {
            tFFechaF.setText("El alquiler sigue abierto");
            tFPrecio.setText(String.valueOf(alquiler.getPrecioSegunDias(1)) + " euros al día");
        } else
        {
            tFFechaF.setText(alquiler.getFechaFinAlquiler().toString());
            tFPrecio.setText(String.valueOf(alquiler.getPrecioSegunDias(alquiler.getDuracionAlquiler()) + " euros"));
        }
    }

    @FXML
    private void listClicked(MouseEvent event)
    {
        try
        {
            Alquiler alquiler = lista.getSelectionModel().getSelectedItem();
            displayTextFields(alquiler);
        } catch (ExcepcionAlquilerVehiculos e)
        {
            Mensajes.mostrarError("Alquileres", e.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize()
    {
        try
        {
            listarAlquileres();
        } catch (IOException e)
        {
            Mensajes.mostrarError("Clientes", "Sin alquileres");
        }
    }

    private void listarAlquileres() throws IOException
    {
        ObservableList<Alquiler> alquileres = FXCollections.observableArrayList(JavaFXMainStage.controlador.obtenerAlquileresCliente(stringDni));
        lista.setItems(alquileres);
    }

}

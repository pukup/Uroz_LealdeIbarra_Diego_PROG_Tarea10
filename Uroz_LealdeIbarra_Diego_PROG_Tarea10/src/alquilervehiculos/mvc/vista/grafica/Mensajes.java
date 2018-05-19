/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class Mensajes
{

    public static void mostrarError(String titulo, String contenido)
    {
        Alert dialogo = new Alert(Alert.AlertType.ERROR);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.showAndWait();
    }

    public static void mostrarInfo(String titulo, String contenido)
    {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        dialogo.showAndWait();
    }
    
}

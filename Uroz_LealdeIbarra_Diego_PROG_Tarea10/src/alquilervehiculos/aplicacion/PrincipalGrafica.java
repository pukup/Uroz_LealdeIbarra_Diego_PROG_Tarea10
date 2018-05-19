/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.aplicacion;

import alquilervehiculos.mvc.controlador.ControladorAlquilerVehiculos;
import alquilervehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.IModeloAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.ModeloAlquilerVehiculos;
import alquilervehiculos.mvc.vista.IVistaAlquilerVehiculos;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;

/**
 *
 * @author pc
 */
public class PrincipalGrafica
{

    public static void main(String[] args)
    {
        IModeloAlquilerVehiculos modelo = new ModeloAlquilerVehiculos();
        IVistaAlquilerVehiculos vista = new JavaFXMainStage();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);

        controlador.comenzar();

    }
}

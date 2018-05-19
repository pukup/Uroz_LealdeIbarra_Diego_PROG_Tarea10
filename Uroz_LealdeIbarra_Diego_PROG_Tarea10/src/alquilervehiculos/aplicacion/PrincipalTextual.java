/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.aplicacion;

import alquilervehiculos.mvc.controlador.ControladorAlquilerVehiculos;
import alquilervehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.ficheros.ModeloFicherosAlquilerVehiculos;
import alquilervehiculos.mvc.vista.textual.IUTextual;
import alquilervehiculos.mvc.vista.IVistaAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.IModeloAlquilerVehiculos;

/**
 *
 * @author lol
 */
public class PrincipalTextual
{

    public static void main(String[] args)
    {
        IModeloAlquilerVehiculos modelo = new ModeloFicherosAlquilerVehiculos();
        IVistaAlquilerVehiculos vista = new IUTextual();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);
        controlador.comenzar();
    }
}

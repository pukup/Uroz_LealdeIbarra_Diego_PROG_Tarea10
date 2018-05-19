/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.modelo.ficheros;

import alquilervehiculos.mvc.modelo.dominio.Alquiler;
import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import java.util.List;

/**
 *
 * @author lol
 */
public interface IModeloFicherosAlquilerVehiculos
{

    List <Alquiler> obtenerAlquileres();
    
    List <Alquiler> obtenerAlquileresAbiertos();
    
    List <Alquiler> obtenerAlquileresCliente(String dni);
    
    List <Alquiler> obtenerAlquileresVehiculos(String matricula);

    List<Cliente> obtenerClientes();

    List<Vehiculo> obtenerVehiculos();

    void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

    void cerrarAlquiler(String matricula);

    void anadirCliente(Cliente cliente);

    void borrarCliente(String dni);

    void anadirVehiculo(Vehiculo vehiculo);

    void borrarVehiculo(String matricula);

    Cliente buscarCliente(String dni);

    Vehiculo buscarVehiculo(String matricula);      
    
    Alquiler buscarAlquiler(String matricula);

    void leerClientes();

    void escribirClientes();

    void leerVehiculos();

    void escribirVehiculos();

    void leerAlquileres();

    void escribirAlquileres();
}

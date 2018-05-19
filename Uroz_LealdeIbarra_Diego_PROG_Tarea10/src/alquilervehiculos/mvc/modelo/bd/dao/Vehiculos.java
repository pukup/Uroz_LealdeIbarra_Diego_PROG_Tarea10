/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.modelo.bd.dao;

import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author lol
 */
public class Vehiculos
{

    private Map<String, Vehiculo> vehiculos;
    private static final String PATH_FICHERO = "datos/vehiculos.dat";

    public Vehiculos()
    {
        vehiculos = new HashMap<String, Vehiculo>();
    }

    public void anadir(Vehiculo vehiculo)
    {
        if (vehiculo == null || vehiculos.containsKey(vehiculo.getMatricula()))
        {
            throw new ExcepcionAlquilerVehiculos("El vehículo ya existe");
        } else
        {
            vehiculos.put(vehiculo.getMatricula(), vehiculo);
        }
    }

    public void borrar(String matricula)
    {
        if (vehiculos.containsKey(matricula))
        {
            vehiculos.remove(matricula);
        } else
        {
            throw new ExcepcionAlquilerVehiculos("El vehículo no existe");
        }
    }

    public List<Vehiculo> getVehiculos()
    {
        return new Vector<Vehiculo>(vehiculos.values());
    }

    public Vehiculo getVehiculo(String matricula)
    {
        if (vehiculos.containsKey(matricula))
        {
            return vehiculos.get(matricula);
        } else
        {
            throw new ExcepcionAlquilerVehiculos("El vehículo no existe");
        }
    }

    public void escribirFicheroObjetos()
    {

    }

    private File nuevoFichero()
    {
        File fichero = new File(PATH_FICHERO);
        return fichero;
    }

    public void leerFicheroObjetos()
    {

    }
}

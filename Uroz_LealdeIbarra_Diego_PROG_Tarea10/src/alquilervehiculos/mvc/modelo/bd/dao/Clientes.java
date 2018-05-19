/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.modelo.bd.dao;

import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author lol
 */
public class Clientes
{

    private Map<String, Cliente> clientes;
    private final String PATH_FICHERO = "datos/clientes.dat";

    public Clientes()
    {
        clientes = new HashMap<String, Cliente>();
    }

    public void anadir(Cliente cliente)
    {
        if (cliente == null || clientes.containsKey(cliente.getDni()))
        {
            throw new ExcepcionAlquilerVehiculos("El cliente ya existe.");
        } else
        {
            clientes.put(cliente.getDni(), cliente);
        }
    }

    public void borrar(String dni)
    {
        if (clientes.containsKey(dni))
        {
            clientes.remove(dni);
        } else
        {
            throw new ExcepcionAlquilerVehiculos("El cliente no existe.");
        }
    }

    public List<Cliente> getClientes()
    {
        List<Cliente> clientesOrdenados = new Vector<Cliente>(clientes.values());
        Collections.sort(clientesOrdenados, ordenadorNombre());
        return clientesOrdenados;
    }

    private Comparator<Cliente> ordenadorNombre()
    {
        return new Comparator<Cliente>()
        {
            public int compare(Cliente c1, Cliente c2)
            {
                return c1.getNombre().compareTo(c2.getNombre());
            }
        };
    }

    public Cliente getCliente(String dni)
    {
        if (clientes.containsKey(dni))
        {
            return new Cliente(clientes.get(dni));
        } else
        {
            throw new ExcepcionAlquilerVehiculos("El cliente no existe.");
        }
    }

    public void escribirFicheroObjetos()
    {

    }

    private File crearNevoFichero()
    {
        File fichero = new File(PATH_FICHERO);
        return fichero;
    }

    public void leerObjetosDeFichero() //throws FileNotFoundException, EOFException, IOException, ClassNotFoundException. No lo tengo claro ¿Instead trycatch?
    {

    }

    private void actualizarUltimoIdentificador()
    {
        while (Cliente.ultimoIdentificador != getUltimoIdentificadorEnFichero())
        {
            Cliente.aumentarUltimoIdentificador();
        }
    }

    private int getUltimoIdentificadorEnFichero()
    {
        int ultimoIdentificador = 0;
        for (Cliente cliente : clientes.values())
        {
            if (cliente.getIdentificador() > ultimoIdentificador)
            {
                ultimoIdentificador = cliente.getIdentificador();
            }
        }
        return ultimoIdentificador;
    }

}

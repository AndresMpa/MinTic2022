/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_tiendaDeLibros
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tiendadelibros.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import uniandes.cupi2.tiendadelibros.mundo.*;
import uniandes.cupi2.tiendadelibros.mundo.Transaccion.Tipo;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Transaccion estén correctamente implementados
 */
public class TransaccionTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase que se utilizará para las pruebas
     */
    private Libro libro;

    /**
     * Fecha predeterminada para la prueba
     */
    private Date fecha;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Libro sin transacciones
     */
    private void setupEscenario1( )
    {
        libro = new Libro( "Las mil y una noches", "HAGFSD123", 34000, 50000, "" );
        fecha = new Date( );
    }

    /**
     * Construye un nuevo Libro con transacciones
     */
    private void setupEscenario2( )
    {
        libro = new Libro( "Las mil y una noches", "HAGFSD123", 34000, 50000, "" );
        fecha = new Date( );
        libro.abastecer( 50, fecha.toGMTString( ) );
        libro.vender( 20, fecha.toGMTString( ) );
        libro.vender( 10, fecha.toGMTString( ) );
        libro.abastecer( 5, fecha.toGMTString( ) );
    }

    /**
     * Prueba 1 - Dar tipo
     */
    @Test
    public void testDarTipo( )
    {
        setupEscenario2( );
        ArrayList<Transaccion> transacciones = libro.darTransacciones( );
        assertEquals( "El tipo es incorrecto", transacciones.get( 0 ).darTipo( ), Tipo.ABASTECIMIENTO );
        assertEquals( "El tipo es incorrecto", transacciones.get( 1 ).darTipo( ), Tipo.VENTA );
        assertEquals( "El tipo es incorrecto", transacciones.get( 2 ).darTipo( ), Tipo.VENTA );
        assertEquals( "El tipo es incorrecto", transacciones.get( 3 ).darTipo( ), Tipo.ABASTECIMIENTO );

    }

    /**
     * Prueba 2 - Dar Cantidad
     */
    @Test
    public void testDarCantidad( )
    {
        setupEscenario1( );
        libro.abastecer( 50, fecha.toGMTString( ) );
        libro.vender( 20, fecha.toGMTString( ) );
        ArrayList<Transaccion> transacciones = libro.darTransacciones( );
        assertTrue( "La cantidad es incorrecta", transacciones.get( 0 ).darCantidad( ) == 50 );
        assertTrue( "La cantidad es incorrecta", transacciones.get( 1 ).darCantidad( ) == 20 );

    }

    /**
     * Prueba 3 - Dar fecha
     */
    @Test
    public void testTDarPrecio( )
    {
        setupEscenario1( );
        Date fechaTransaccion = new Date( );
        libro.abastecer( 50, fechaTransaccion.toGMTString( ) );
        ArrayList<Transaccion> transacciones = libro.darTransacciones( );
        assertTrue( "La fecha es incorrecta", transacciones.get( 0 ).darFecha( ).equals( fechaTransaccion.toGMTString( ) ) );
    }
}

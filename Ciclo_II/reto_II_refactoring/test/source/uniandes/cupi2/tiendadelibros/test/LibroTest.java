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

import java.util.Date;

import org.junit.Test;

import uniandes.cupi2.tiendadelibros.mundo.Libro;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Libro estén correctamente implementados
 */
public class LibroTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Libro libro;

    /**
     * Fecha predeterminada para transacciones
     */
    private Date fecha;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Libro sin unidades
     */
    private void setupEscenario1( )
    {
        libro = new Libro( "Las mil y una noches", "HAGFSD123", 34000, 50000, "./data/imagenes/lasmilyunanoches.jpg" );
        fecha = new Date( );
    }

    /**
     * Construye un nuevo Libro con unidades
     */
    private void setupEscenario2( )
    {
        libro = new Libro( "Las mil y una noches", "HAGFSD123", 34000, 50000, "./data/imagenes/lasmilyunanoches.jpg" );
        fecha = new Date( );
        libro.abastecer( 50, fecha.toGMTString( ) );
        libro.abastecer( 100, fecha.toGMTString( ) );
        libro.abastecer( 8, fecha.toGMTString( ) );
    }

    /**
     * Prueba 1 - Dar título
     */
    @Test
    public void testDarTitulo( )
    {
        setupEscenario1( );
        assertEquals( "El título es incorrecto", "Las mil y una noches", libro.darTitulo( ) );
    }

    /**
     * Prueba 2 - Dar ISBN
     */
    @Test
    public void testDarISBN( )
    {
        setupEscenario1( );
        assertEquals( "El ISBN es incorrecto", "HAGFSD123", libro.darIsbn( ) );
    }

    /**
     * Prueba 3 - Dar precio
     */
    @Test
    public void testTDarPrecioCompra( )
    {
        setupEscenario1( );
        assertTrue( "El precio es incorrecto", 50000 == libro.darPrecioCompra( ) );
    }

    /**
     * Prueba 3 - Dar precio
     */
    @Test
    public void testTDarPrecioVenta( )
    {
        setupEscenario1( );
        assertTrue( "El precio es incorrecto", 34000 == libro.darPrecioVenta( ) );
    }

    /**
     * Prueba 4 - Dar cantidad actual
     */
    @Test
    public void testDarCantidadActual( )
    {
        setupEscenario1( );
        assertTrue( "La cantidad actual es incorrecta", 5 == libro.darCantidadActual( ) );
        libro.abastecer( 50, fecha.toGMTString( ) );
        assertTrue( "La cantidad actual es incorrecta", 55 == libro.darCantidadActual( ) );
    }

    /**
     * Prueba 5 - Abastecer
     */
    @Test
    public void testAbastecer( )
    {
        setupEscenario1( );
        libro.abastecer( 50, fecha.toGMTString( ) );
        libro.abastecer( 100, fecha.toGMTString( ) );
        libro.abastecer( 8, fecha.toGMTString( ) );
        assertTrue( "No se abasteció correctamente", 158 + 5 == libro.darCantidadActual( ) );
    }

    /**
     * Prueba 6 - Vender
     */
    @Test
    public void testVender( )
    {
        setupEscenario2( );
        libro.vender( 100, fecha.toGMTString( ) );
        libro.vender( 58, fecha.toGMTString( ) );
        assertTrue( "No se vendió correctamente", 5 == libro.darCantidadActual( ) );
    }

    /**
     * Prueba 7 - Dar transacciones
     */
    @Test
    public void testDarTransacciones( )
    {
        setupEscenario2( );
        assertTrue( "Número de transacciones incorrecto", 3 == libro.darTransacciones( ).size( ) );
        libro.vender( 100, fecha.toGMTString( ) );
        libro.vender( 58, fecha.toGMTString( ) );
        assertTrue( "Número de transacciones incorrecto", 5 == libro.darTransacciones( ).size( ) );
    }

}

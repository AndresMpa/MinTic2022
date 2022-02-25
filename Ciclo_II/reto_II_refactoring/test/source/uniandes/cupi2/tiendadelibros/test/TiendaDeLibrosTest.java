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
import uniandes.cupi2.tiendadelibros.mundo.TiendaDeLibros;

/**
 * Esta es la clase usada para verificar que los métodos de la clase TiendaDeLibros estén correctamente implementados
 */
public class TiendaDeLibrosTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private TiendaDeLibros tiendaDeLibros;

    /**
     * Fecha predeterminada para la prueba
     */
    private Date fecha;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva tienda de libros vacía
     */
    private void setupEscenario1( )
    {
        tiendaDeLibros = new TiendaDeLibros( );
        fecha = new Date( );
    }

    /**
     * Construye una nueva tienda con libros
     */
    private void setupEscenario2( )
    {
        tiendaDeLibros = new TiendaDeLibros( );
        fecha = new Date( );
        tiendaDeLibros.registrarLibro( "Las mil y una noches", "AGHF324", 530, 340, "" );
        tiendaDeLibros.registrarLibro( "Pulgarcito", "HAGF456", 500, 250, "" );
    }

    /**
     * Prueba 1 - Buscar por título
     */
    @Test
    public void testBuscarPorTitulo( )
    {
        setupEscenario1( );
        tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "" );
        Libro temp = new Libro( "Libro 1", "ISBN1", 50000, 50000, "" );
        assertEquals( "Falló la búsqueda por título", temp.darIsbn( ), tiendaDeLibros.buscarLibroPorTitulo( "Libro 1" ).darIsbn( ) );
    }

    /**
     * Prueba 2 - Buscar por ISBN
     */
    @Test
    public void testBuscarPorISBN( )
    {
        setupEscenario1( );
        tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "" );
        Libro temp = new Libro( "Libro 1", "ISBN1", 50000, 50000, "" );
        assertEquals( "Falló la búsqueda por ISBN", temp.darIsbn( ), tiendaDeLibros.buscarLibroPorISBN( "ISBN1" ).darIsbn( ) );
    }

    /**
     * Prueba 3 - Registrar libro
     */
    @Test
    public void testRegistrarLibro( )
    {
        setupEscenario1( );
        assertTrue( "El tamaño del catálogo es incorrecto, debería ser 0", 0 == tiendaDeLibros.darCatalogo( ).size( ) );
        tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "" );
        assertTrue( "El tamaño del catálogo es incorrecto, debería registrar", 1 == tiendaDeLibros.darCatalogo( ).size( ) );
        tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "" );
        assertTrue( "El tamaño del catálogo es incorrecto, no debería registrar", 1 == tiendaDeLibros.darCatalogo( ).size( ) );

    }

    /**
     * Prueba 4 - Eliminar libro
     */
    @Test
    public void testEliminarLibro( )
    {
        setupEscenario1( );
        tiendaDeLibros.registrarLibro( "Libro1", "ISBN1", 50000, 50000, "" );
        tiendaDeLibros.registrarLibro( "Libro2", "ISBN2", 50000, 50000, "" );
        tiendaDeLibros.vender("ISBN2", 5, fecha.toGMTString());
        tiendaDeLibros.eliminarLibro( "ISBN2" );
        assertTrue( "El tamaño del catálogo es incorrecto, debería eliminar", 1 == tiendaDeLibros.darCatalogo( ).size( ) );
        tiendaDeLibros.eliminarLibro( "ISBN2" );
        assertTrue( "El tamaño del catálogo es incorrecto, no debería eliminar", 1 == tiendaDeLibros.darCatalogo( ).size( ) );
    }

    /**
     * Prueba 5 - Abastecer
     */
    @Test
    public void testAbastecer( )
    {
        setupEscenario2( );
        tiendaDeLibros.abastecer( "AGHF324", 50, fecha.toGMTString( ) );
        assertTrue( "No se abasteció correctamente", 55 == tiendaDeLibros.buscarLibroPorISBN( "AGHF324" ).darCantidadActual( ) );
    }

    /**
     * Prueba 6 - Vender
     */
    @Test
    public void testVender( )
    {
        setupEscenario2( );
        tiendaDeLibros.abastecer( "AGHF324", 50, fecha.toGMTString( ) );
        tiendaDeLibros.vender( "AGHF324", 20, fecha.toGMTString( ) );
        assertEquals( "No se vendió correctamente", 35, tiendaDeLibros.buscarLibroPorISBN( "AGHF324" ).darCantidadActual( ) );
        boolean venta = tiendaDeLibros.vender( "AGHF324", 36, fecha.toGMTString( ) );
        assertFalse( "No debería realizarse la venta", venta );
    }

    /**
     * Prueba 7 - Dar Libro Más Costoso
     */
    @Test
    public void testDarLibroMasCostoso( )
    {
        setupEscenario2( );
        Libro temp = new Libro( "Las mil y una noches", "AGHF324", 340, 500, "" );
        assertEquals( "No retorna el libro correcto", temp.darIsbn( ), tiendaDeLibros.darLibroMasCostoso( ).darIsbn( ) );
    }

    /**
     * Prueba 8 - Dar Libro Menos Costoso
     */
    @Test
    public void testDarLibroMenosCostoso( )
    {
        setupEscenario2( );
        Libro temp = new Libro( "Pulgarcito", "HAGF456", 5000, 5000, "" );
        tiendaDeLibros.registrarLibro( "Pulgarcito", "HAGF456", 5000, 5000, "" );
        assertEquals( "No retorna el libro correcto", temp.darIsbn( ), tiendaDeLibros.darLibroMasEconomico( ).darIsbn( ) );
    }

    /**
     * Prueba 9 - Dar Libro Más Vendido
     */
    @Test
    public void testDarLibroMasVendido( )
    {
        setupEscenario2( );
        tiendaDeLibros.abastecer( "AGHF324", 500, fecha.toGMTString( ) );
        tiendaDeLibros.abastecer( "HAGF456", 200, fecha.toGMTString( ) );
        tiendaDeLibros.vender( "AGHF324", 500, fecha.toGMTString( ) );
        tiendaDeLibros.vender( "HAGF456", 200, fecha.toGMTString( ) );
        assertEquals( "No retorna el libro correcto", "AGHF324", tiendaDeLibros.darLibroMasVendido( ).darIsbn( ) );
    }

    /**
     * Prueba 10 - Dar Cantidad Transacciones Abastecimiento
     */
    @Test
    public void testDarCantidadTransaccionesAbastecimiento( )
    {
        setupEscenario1( );
        tiendaDeLibros.registrarLibro( "LibroPrueba", "1SBN", 450, 500, "" );
        tiendaDeLibros.abastecer( "1SBN", 500, fecha.toGMTString( ) );
        tiendaDeLibros.abastecer( "1SBN", 200, fecha.toGMTString( ) );
        assertEquals( "La cantidad es incorrecta", 2, tiendaDeLibros.darCantidadTransaccionesAbastecimiento( "1SBN" ) );
        tiendaDeLibros.vender( "1SBN", 500, fecha.toGMTString( ) );
        tiendaDeLibros.vender( "1SBN", 200, fecha.toGMTString( ) );
        assertTrue( "La cantidad es incorrecta, no debió aumentar", 2 == tiendaDeLibros.darCantidadTransaccionesAbastecimiento( "1SBN" ) );

    }

}

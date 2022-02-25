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
package uniandes.cupi2.tiendadelibros.mundo;

import java.util.ArrayList;

/**
 * Clase que representa el libro.
 */
public class Libro
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El código ISBN del libro.
     */
    private String isbn;

    /**
     * El título del libro.
     */
    private String titulo;

    /**
     * El precio de venta del libro.
     */
    private double precioVenta;

    /**
     * El precio de compra del libro.
     */
    private double precioCompra;

    /**
     * Cantidad actual de ejemplares del libro.
     */
    private int cantidadActual;

    /**
     * Ruta de la imagen del libro.
     */
    private String rutaImagen;

    /**
     * Colección de transacciones realizadas.
     */
    private ArrayList<Transaccion> transacciones;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea un libro con su título, isbn, autor y precioVenta. La cantidad actual se inicializa en cero y la colección de transacciones es vacía.
     * @param pTitulo Título del libro. pTitulo != null && pTitulo != "".
     * @param pIsbn Código ISBN del libro. pIsbn != null && pIsbn != "".
     * @param pPrecioVenta precioVenta del libro. pPrecioVenta > 0.
     * @param pPrecioCompra precioVenta del libro. pPrecioCompra > 0.
     * @param pRutaImagen Ruta de la imagen del libro. pRutaImagen != null && pRutaImagen != "".
     */
    public Libro( String pTitulo, String pIsbn, double pPrecioVenta, double pPrecioCompra, String pRutaImagen )    {
        titulo = pTitulo;
        isbn = pIsbn;
        precioVenta = pPrecioVenta;
        precioCompra = pPrecioCompra;
        rutaImagen = pRutaImagen;
        cantidadActual = 5;
        transacciones = new ArrayList<Transaccion>( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el código IBSN del libro.
     * @return El código ISBN del libro.
     */
    public String darIsbn( )    {
        return isbn;
    }

    /**
     * Retorna el título del libro.
     * @return El título del libro.
     */
    public String darTitulo( )    {
        return titulo;
    }

    /**
     * Retorna el precio de venta del libro.
     * @return El precio de venta del libro.
     */
    public double darPrecioVenta( )    {
        return precioVenta;
    }

    /**
     * Retorna el precio de compra del libro.
     * @return El precio de compra del libro.
     */
    public double darPrecioCompra( )    {
        return precioCompra;
    }

    /**
     * Retorna la cantidad actual de ejemplares del libro.
     * @return La cantidad actual de ejemplares del libro.
     */
    public int darCantidadActual( )    {
        return cantidadActual;
    }

    /**
     * Retorna la ruta de la imagen del libro.
     * @return La ruta de la imagen del libro.
     */
    public String darRutaImagen( )    {
        return rutaImagen;
    }
    
    /**
     * Retorna la colección de transacciones.
     * @return La colección de transacciones.
     */
    public ArrayList<Transaccion> darTransacciones( )    {
        return transacciones;
    }
    
    /**
     * Cambia el precio actual del libro.
     * @return La ruta de la imagen del libro.
     */
    public void obtenerPrecioVenta( double precio )    {
    	this.precioVenta = precio;
    }

    /**
     * Vende la cantidad de ejemplares que entra por parámetro.
     * @param pCantidad La cantidad de ejemplares que se van a vender.
     * @param pFecha La fecha en la que se realizó la transacción. pFecha != "" && pFecha != null.
     * @return Retorna true en caso de que se pueda vender la cantidad que entra por parámetro. Retorna false en caso de que la cantidad sea mayor a la actual.
     */
    public boolean vender( int pCantidad, String pFecha )    {
        boolean vendido = false;
        // Verifica que la cantidad que entra por parámetro sea menor o igual a la cantidad igual
        if( pCantidad <= cantidadActual )
        {
            // Disminuye la cantidad actual de ejemplares
            cantidadActual -= pCantidad;
            // Crear el codigo de la transaccion
            String idTransaccion = pFecha + "-" + isbn + "-" + pCantidad;
            // Crea una nueva transacción con el tipo venta y la fecha actual
            Transaccion nueva = new Transaccion( Transaccion.Tipo.VENTA, pCantidad, pFecha, idTransaccion );
            // Agrega la nueva transacción a la colección de transacciones del libro
            transacciones.add( nueva );
            vendido = true;
        }
        return vendido;
    }

    /**
     * Abastece la cantidad de ejemplares que entra por parámetro.
     * @param pCantidad La cantidad de ejemplares que se van a agregar a la cantidad actual.
     * @param pFecha La fecha en la que se realizó la transacción. pFecha != "" && pFecha != null.
     */
    public void abastecer( int pCantidad, String pFecha )    {
        // Aumenta la cantidad actual de ejemplares
        cantidadActual += pCantidad;
        // Crea el codigo de la transaccion
        String idTransaccion = pFecha + "-" + isbn + "-" + pCantidad;
        // Crea una nueva transacción con el tipo abastecimiento y la fecha actual
        Transaccion nueva = new Transaccion( Transaccion.Tipo.ABASTECIMIENTO, pCantidad, pFecha, idTransaccion );
        // Agrega la nueva transacción a la colección de transacciones del libro
        transacciones.add( nueva );
    }

    /**
     * Retorna la representación en cadena de caracteres un objeto Libro.
     * @return La representación en cadena de caracteres del objeto Libro.
     */
    public String toString( )    {
        String representacion = titulo + " (" + isbn + ")";
        return representacion;
    }

}

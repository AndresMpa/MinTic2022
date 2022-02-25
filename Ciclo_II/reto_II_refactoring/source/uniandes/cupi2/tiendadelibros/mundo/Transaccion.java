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

/**
 * Clase que representa una transacción de un libro.
 */
public class Transaccion
{
    // -----------------------------------------------------------------
    // Enumeraciones
    // -----------------------------------------------------------------

    /**
     * Enumeradores para los tipos de transacciones posibles.
     */
    public enum Tipo {
        /**
         * Representa el tipo venta
         */
        VENTA,
        /**
         * Representa el tipo abastecimiento
         */
        ABASTECIMIENTO
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tipo de la transacción.
     */
    private Tipo tipo;

    /**
     * Cantidad de ejemplares utilizados en la transacción.
     */
    private int cantidad;

    /**
     * Fecha en que se realizó la transacción.
     */
    private String fecha;

    /**
     * Fecha-ISBN-Cantidad.
     */
    private String idTransaccion;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea una transacción con el tipo, cantidad de ejemplares y la fecha en que se realizó.
     * @param pTipo Tipo de la transacción. pTipo != null.
     * @param pCantidad Cantidad de ejemplares utilizados en la transacción. pCantidad > 0.
     * @param pFecha Fecha en que se realizó la transacción. pFecha != null && pFecha != "".
     */
    public Transaccion( Tipo pTipo, int pCantidad, String pFecha, String pIdTransaccion )    {
    	tipo = pTipo;
        cantidad = pCantidad;
        fecha = pFecha;
        idTransaccion = pIdTransaccion;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo de la transacción.
     * @return El tipo de la transacción.
     */
    public Tipo darTipo( )    {
        return tipo;
    }

    /**
     * Retorna la cantidad de ejemplares utilizados en la transacción.
     * @return La cantidad de ejemplares utilizados en la transacción.
     */
    public int darCantidad( )    {
        return cantidad;
    }

    /**
     * Retorna la fecha de la transacción.
     * @return La fecha de la transacción.
     */
    public String darFecha( )    {
        return fecha;
    }
    
    public String darIdTransaccion()    {
    	return idTransaccion;
    }

    /**
     * Retorna la representación en cadena de caracteres un objeto Transaccion.
     * @return La representación en cadena de caracteres del objeto Transaccion.
     */
    public String toString( )    {
        String representacion = fecha + " - " + tipo + ": " + cantidad;
        return representacion;
    }
}

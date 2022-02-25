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
 * Clase que representa la tienda de libros.
 */
public class TiendaDeLibros
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Catalogo de libros registrados en la tienda.
     */
    private ArrayList<Libro> catalogo;

    /**
     * Cantidad actual en la caja de la tienda de libros.
     */
    private double caja;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea una nueva tienda de libros. <br>
     * <b>post:</b>El catalogo de libros fue inicializado. <br>
     * La caja fue inicializada en 1000000.
     */
    public TiendaDeLibros( )    {
        catalogo = new ArrayList<Libro>( );
        caja = 1000000;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el catálogo de libros.
     * @return El catálogo de libros.
     */
    public ArrayList<Libro> darCatalogo( )    {
        return catalogo;
    }

    /**
     * Retorna el valor actual en la caja.
     * @return El valor actual en caja.
     */
    public double darCaja( )    {
        return caja;
    }

    /**
     * Modifica el valor actual de la caja.
     * @param pCaja El nuevo valor de la caja.
     */
    public void cambiarCaja( double pCaja )    {
        caja = pCaja;
    }

    /**
     * Busca un libro por el título dado por parámetro.
     * @param pTitulo El titulo del libro que se quiere buscar. pTitulo != null && pTitulo != "".
     * @return Si existe un libro con ese título, lo retorna. En caso contrario, retorna null.
     */
    public Libro buscarLibroPorTitulo( String pTitulo )    {
        Libro buscado = null;
        int i = 0;
        while( i < catalogo.size( ) && buscado == null ) {
            Libro actual = catalogo.get( i );
            if( actual.darTitulo( ).equals( pTitulo ) ) {
                buscado = actual;
            }
            i++;
        }
        return buscado;
    }

    /**
     * Busca un libro por el código ISBN dado por parámetro.
     * @param pIsbn El código ISBN del libro que se quiere buscar. pIsbn != null && pIsbn != "".
     * @return Si existe un libro con ese ISBN, lo retorna. En caso contrario, retorna null.
     */
    public Libro buscarLibroPorISBN( String pIsbn )    {
        Libro buscado = null;
        for( int i = 0; i < catalogo.size( ) && buscado == null; i++ ) {
            Libro actual = catalogo.get( i );
            if( actual.darIsbn( ).equals( pIsbn ) ) {
                buscado = actual;
            }
        }

        return buscado;
    }

    /**
     * Registra un libro en la tienda de libros. <br>
     * <b>post: </b> El libro fue creado y agregado al catálogo.
     * @param pTitulo El título del libro que se quiere agregar. pTitulo != null && pTitulo != "".
     * @param pIsbn El código ISBN del libro que se quiere agregar. pIsbn != null && pIsbn != "".
     * @param pPrecioVenta El precio de venta del libro que se quiere agregar. pPrecioVenta > 0.
     * @param pPrecioCompra El precio de compra del libro que se quiere agregar. pPrecioCompra > 0.
     * @param pRutaImagen La ruta de la imagen del libro. pRutaImagen != null && pRutaImagen != "".
     * @return El nuevo libro registrado en caso de que si se haya podido realizar la operación, null en caso de que el libro ya exista.
     */
    public Libro registrarLibro( String pTitulo, String pIsbn, double pPrecioVenta, double pPrecioCompra, String pRutaImagen ) {
        // Comprueba si el libro con ese ISBN no ha sido creado
        Libro buscado = buscarLibroPorISBN( pIsbn );
        Libro nuevo = null;
        if( buscado == null ) {
            nuevo = new Libro( pTitulo, pIsbn, pPrecioVenta, pPrecioCompra, pRutaImagen );
            catalogo.add( nuevo );
        }
        return nuevo;
    }

    /**
     * Elimina un libro con el ISBN dado por parámetro. Si la cantidad actual de ejemplares es mayor a cero, no se eliminará el libro. <br>
     * <b>post: </b> El libro fue eliminado del catálogo.
     * @param pIsbn El ISBN del libro que se quiere eliminar. pIsbn != null && pIsbn != "".
     * @return Retorna true si se pudo eliminar, false si el libro no existe o si la cantidad actual de ejemplares es mayor a cero.
     */
    public boolean eliminarLibro( String pIsbn ) {
        boolean eliminado = false;
        Libro buscado = buscarLibroPorISBN( pIsbn );
        if( buscado != null ) {
            if( buscado.darCantidadActual( ) == 0 ) {
                catalogo.remove( buscado );
                eliminado = true;
            }

        }
        return eliminado;
    }

    /**
     * Abastece un libro con la cantidad de ejemplares dada por parámetro. <br>
     * <b>post: </b> Se abasteció el libro con el ISBN dado y se disminuyó la cantidad en caja con el precio final del abastecimiento.
     * @param pIsbn El Código ISBN del libro que se quiere abastecer. pIsbn!= null && pISBN != "".
     * @param pFecha La fecha en la que se realizó la transacción. pFecha != "" && pFecha != null.
     * @param pCantidad La cantidad de ejemplares que se van a abastecer. pCantidad >= 0.
     * @return Retorna true si se pudo abastecer el libro, false en caso contrario.
     */
    public boolean abastecer( String pIsbn, int pCantidad, String pFecha ) {
        Libro buscado = buscarLibroPorISBN( pIsbn );
        boolean seAbastecio = false;
        if( buscado != null && caja >= pCantidad * buscado.darPrecioCompra( ) ) {
            buscado.abastecer( pCantidad, pFecha );
            // Disminuye la caja con el valor total de los ejemplares abastecidos
            caja -= pCantidad * buscado.darPrecioCompra( );
            seAbastecio = true;
        }
        return seAbastecio;
    }

    /**
     * Vende la cantidad de ejemplares del libro con el ISBN dado por parámetro. <br>
     * <b>post: </b> Se vendió el libro con el ISBN dado y se aumentó la cantidad en caja con el precio final de la venta.
     * @param pIsbn El Código ISBN del libro que se quiere vender. pIsbn != null && pIsbn != "".
     * @param pCantidad La cantidad de ejemplares que se van a vender.
     * @param pFecha La fecha en la que se realizó la transacción. pFecha != "" && pFecha != null.
     * @return Retorna true en caso de que se pueda vender la cantidad de ejemplares dada por parámetro. False en caso contrario.
     */
    public boolean vender( String pIsbn, int pCantidad, String pFecha ) {
        boolean vendido = false;
        Libro buscado = buscarLibroPorISBN( pIsbn );
        if( buscado != null ) {
            vendido = buscado.vender( pCantidad, pFecha );
            // Aumenta la caja con el valor total de los ejemplares vendidos
            caja += pCantidad * buscado.darPrecioVenta( );
        }
        return vendido;
    }

    /**
     * Busca el libro más costoso, es decir el libro con el mayor precio de venta en el catálogo.
     * @return El libro más costoso. En caso de que el catalogo esté vacío, retorna null
     */
    public Libro darLibroMasCostoso( ) {
        // Guarda el libro más costoso y su precio
        Libro masCostoso = null;
        double precioMasCostoso = 0.0;

        for( Libro actual : catalogo ) {
            // Verifica si el libro actual tiene un precio mayor al que está guardado
            if( actual.darPrecioVenta( ) > precioMasCostoso ) {
                masCostoso = actual;
                precioMasCostoso = actual.darPrecioVenta( );
            }
        }

        return masCostoso;
    }

    /**
     * Busca el libro más económico en el catálogo de libros. El libros más económico es el libro con el menor precio de venta.
     * @return El libro menos costoso. En caso de que el catálogo esté vacío, retorna null.
     */
    public Libro darLibroMasEconomico( ) {
        Libro menosCostoso = null;
        double precioMenosCostoso = 0.0;

        // Revisa que el catálogo no esté creado
        if( catalogo.size( ) > 0 ) {
            // Para comenzar el recorrido, se asume que el primer libro es el más económico
            menosCostoso = catalogo.get( 0 );
            precioMenosCostoso = catalogo.get( 0 ).darPrecioVenta( );

            for( int i = 0; i < catalogo.size( ); i++ ) {
                Libro actual = catalogo.get( i );
                // Pregunta si el precio del libro actual es menos costoso
                if( actual.darPrecioVenta( ) < precioMenosCostoso ) {
                    menosCostoso = actual;
                    precioMenosCostoso = actual.darPrecioVenta( );
                }
            }
        }
        return menosCostoso;
    }

    /**
     * Busca el libro más vendido, es decir el libro con más transacciones de tipo VENTA.
     * @return El libro más vendido. En caso de que el catálogo esté vacío, retorna null.
     */
    public Libro darLibroMasVendido( ) {
        Libro masVendido = null;
        int ventas = 0;
        // Recorre el catalogo de libros
        for( Libro libroActual : catalogo ) {
            int ventasActual = 0;
            // Recorre las transacciones del libro actual
            for( Transaccion transaccionActual : libroActual.darTransacciones( ) ) {
                if( transaccionActual.darTipo( ).equals( Transaccion.Tipo.VENTA ) ) {
                    // Cuenta los libros vendidos en la transacción actual.
                    ventasActual += transaccionActual.darCantidad( );
                }
            }
            // Verifica que las ventas actuales sean mayores a las ventas guardadas
            if( ventasActual > ventas ) {
                masVendido = libroActual;
                ventas = ventasActual;
            }
        }

        return masVendido;
    }

    /**
     * Calcula la cantidad de transacciones de abastecimiento del libro con el ISBN dado por parámetro.
     * @param pIsbn El código ISBN del libro que se quiere buscar. pIsbn != null && pIsbn != "".
     * @return La cantidad de transacciones de abastecimiento. En caso de que no encuentre el libro o no tenga transacciones, retorna cero.
     */
    public int darCantidadTransaccionesAbastecimiento( String pIsbn ) {
        // Busca el libro con el ISBN dado por parámetro
        Libro buscado = buscarLibroPorISBN( pIsbn );
        int cantidadTransacciones = 0;
        // Verifica que si exista el libro
        if( buscado != null ) {
            // Guarda las transacciones del libro buscado
            ArrayList<Transaccion> transacciones = buscado.darTransacciones( );
            for( int i = 0; i < transacciones.size( ); i++ ) {
                Transaccion actual = transacciones.get( i );
                // Verifica y cuenta las transacciones de tipo ABASTECIMIENTO
                if( actual.darTipo( ).equals( Transaccion.Tipo.ABASTECIMIENTO ) ) {
                    cantidadTransacciones++;
                }
            }
        }
        return cantidadTransacciones;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta 1.
     */
    public String metodo1(int[] lim_inf, int[] lim_sup, double[] descuentos ) {
    	for( Libro libroActual: this.catalogo ) {
    		double precioActual = libroActual.darPrecioVenta();
    		    		
    		for( int i = 0; i < lim_inf.length; i++ ) {
    			if( precioActual >= lim_inf[i] && precioActual < lim_sup[i] ) {
    				libroActual.obtenerPrecioVenta(precioActual - (precioActual * descuentos[i]));
    			}	
    		}
    	}
    	
        return "Precios actualizados";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta 2.
     */
    public Transaccion metodo2( ) {
    	Transaccion transaccionMasCostosa = null;
    	double precioMasAlto = 0;
    	
    	for( Libro libroActual: this.catalogo ) {
    		for( Transaccion transaccionActual: libroActual.darTransacciones()) {
    			double precioTransaccion = libroActual.darPrecioVenta() * transaccionActual.darCantidad();
    			
    			if( precioTransaccion > precioMasAlto ) {
    				precioMasAlto = precioTransaccion;
    				transaccionMasCostosa = transaccionActual;
    			}
    		}
    	}
    	
        return transaccionMasCostosa;
    }

}

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
package uniandes.cupi2.tiendadelibros.interfaz;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uniandes.cupi2.tiendadelibros.mundo.Libro;
import uniandes.cupi2.tiendadelibros.mundo.TiendaDeLibros;
import uniandes.cupi2.tiendadelibros.mundo.Transaccion;

/**
 * Clase que representa la ventana principal de la tienda de libros.
 */
@SuppressWarnings("serial")
public class InterfazTiendaLibros extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La tienda de libros que se está administrando.
     */
	
	private final int[] lim_inf = {0, 5000, 8000, 16000, 30000, 50000};
	private final int[] lim_sup = {5000, 8000, 16000, 30000, 50000, Integer.MAX_VALUE};
	private final double[] descuentos = {0.02, 0.04, 0.08, 0.15, 0.2, 0.3};
	
    private TiendaDeLibros tiendaDeLibros;

    // -----------------------------------------------------------------
    // Componentes de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene la imagen.
     */
    private PanelImagen panelImagen;

    /**
     * Panel que muestra la caja de la tienda.
     */
    private PanelCaja panelCaja;

    /**
     * Panel que presenta los libros actuales en la tienda y permite hacer modificaciones en el inventario.
     */
    private PanelBodega panelBodega;

    /**
     * Panel para hacer consultas sobre los libros en la tienda.
     */
    private PanelConsultas panelConsultas;

    /**
     * Panel extensión.
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana principal de la aplicación. <br>
     * <b>post: </b> Se creó la ventana con sus paneles, y la ventana está conectada al mundo.
     */
    public InterfazTiendaLibros( )
    {
    	
    	
        setTitle( "Tienda de Libros" );
        setSize( 750, 700 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        tiendaDeLibros = new TiendaDeLibros( );
        tiendaDeLibros.registrarLibro("Henry Potter", "IS123456789", 38500.0, 30000.0, "./data/imagenes/henrypotter.jpg");
        tiendaDeLibros.registrarLibro("La Principita", "ISLEPETTITPRINC23", 23232.3, 2323.23, "./data/imagenes/laprincipita.jpg");
        tiendaDeLibros.registrarLibro("Las Mil y Dos Noches", "IS1AMIL7869", 3900.0, 2740.0, "./data/imagenes/lasmilydosnoches.jpg");
        tiendaDeLibros.registrarLibro("El Hubbit", "ISHB784726", 6100.0, 4900.0, "./data/imagenes/elhubbit.jpg");
        tiendaDeLibros.registrarLibro("Crespusculo", "ISCRSS25874", 50700, 44000.0, "./data/imagenes/crespusculo.jpg");

        
        setLayout( new BorderLayout( ) );

        JPanel centro = new JPanel( );
        centro.setLayout( new BorderLayout( ) );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelCaja = new PanelCaja( );
        centro.add( panelCaja, BorderLayout.NORTH );
        panelBodega = new PanelBodega( this );
        centro.add( panelBodega, BorderLayout.CENTER );
        panelConsultas = new PanelConsultas( this );
        centro.add( panelConsultas, BorderLayout.SOUTH );
        add( centro, BorderLayout.CENTER );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        setLocationRelativeTo( null );
        setResizable( false );

        panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea la ventana de diálogo para registrar un nuevo libro en la tienda. <br>
     * <b>post: </b> Se crea la ventana de diálogo.
     */
    public void dialogoRegistrarLibro( )
    {
        DialogoRegistrar dialogo = new DialogoRegistrar( this );
        dialogo.setVisible( true );
    }

    /**
     * Registra el nuevo libro en la tienda de libros. <br>
     * <b>post: </b> Si los datos ingresados por el usuario son válidos queda registrado el libro, de lo contrario se le informa al usuario.
     * @param pIsbn El ISBN del nuevo libro.
     * @param pTitulo El título del nuevo libro.
     * @param pPrecioCompra El precio de compra del nuevo libro.
     * @param pPrecioVenta El precio de venta del nuevo libro.
     * @param pRutaImagen La ruta de la imagen del libro.
     * @param pDialogo Ventana de diálogo de la cual se obtuvo la información.
     */
    public void registrarLibro( String pTitulo, String pIsbn, String pPrecioCompra, String pPrecioVenta, String pRutaImagen, DialogoRegistrar pDialogo )
    {
        double precioCompra = 0;
        double precioVenta = 0;
        boolean condicionesValidas = true;

        if( pIsbn.equals( "" ) || pTitulo.equals( "" ) || pPrecioCompra.equals( "" ) || pPrecioVenta.equals( "" ) )
        {
            condicionesValidas = false;
            JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE );
        }

        if( condicionesValidas )
        {
            try
            {
                precioCompra = Double.parseDouble( pPrecioCompra );
            }
            catch( Exception e )
            {
                condicionesValidas = false;
                JOptionPane.showMessageDialog( this, "El precio de compra debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

        if( condicionesValidas )
        {
            try
            {
                precioVenta = Double.parseDouble( pPrecioVenta );
            }
            catch( Exception e )
            {
                condicionesValidas = false;
                JOptionPane.showMessageDialog( this, "El precio de venta debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

        if( condicionesValidas )
        {
            if( precioCompra <= 0 )
            {
                JOptionPane.showMessageDialog( this, "Ingrese un precio de compra válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( precioVenta <= 0 )
            {
                JOptionPane.showMessageDialog( this, "Ingrese un precio de venta válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( pRutaImagen.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Eliga una imagen", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                Libro registrado = tiendaDeLibros.registrarLibro( pTitulo, pIsbn, precioVenta, precioCompra, pRutaImagen );
                if( registrado == null )
                {
                    JOptionPane.showMessageDialog( this, "Ya existe un libro con ese ISBN", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    pDialogo.dispose( );
                    panelBodega.refrescar( tiendaDeLibros.darCatalogo( ) );
                    panelBodega.cambiarSeleccionado( registrado );
                    panelConsultas.limpiar( );
                    JOptionPane.showMessageDialog( this, "Se agregó el libro exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE );
                }

            }
        }
    }

    /**
     * Método para aumentar las unidades disponibles de un libro existente. <br>
     * <b>post: </b> La cantidad actual del libro se aumenta por el número de unidades dispuesto por el usuario.
     * @param pLibro Libro del cual se desea adquirir más unidades.
     */
    public void abastecerLibro( Libro pLibro )
    {
        if( pLibro != null )
        {
            String respuesta = JOptionPane.showInputDialog( this, "Introduzca la cantidad de unidades", "Abastecer libro", JOptionPane.QUESTION_MESSAGE );
            if( respuesta != null )
            {
                try
                {
                    int unidades = Integer.parseInt( respuesta );
                    if( unidades <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "La cantidad debe ser mayor a cero. ", "Abastecer libro", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                        Date fechaActual = new Date( );
                        SimpleDateFormat formato = new SimpleDateFormat( "yyyy-mm-dd hh:mm:ss" );
                        String fechaString = formato.format( fechaActual );
                        boolean exito = tiendaDeLibros.abastecer( pLibro.darIsbn( ), unidades, fechaString );
                        if( exito )
                        {
                            panelBodega.refrescar( tiendaDeLibros.darCatalogo( ) );
                            panelBodega.cambiarSeleccionado( pLibro );
                            panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
                            panelConsultas.limpiar( );
                        }
                        else
                        {
                            double valor = pLibro.darPrecioCompra( ) * unidades;
                            NumberFormat formatter = NumberFormat.getInstance( );
                            String moneyString = formatter.format( valor );

                            JOptionPane.showMessageDialog( this, "No hay dinero suficiente en caja para realizar esta compra. Valor: $" + moneyString, "Abastecer libro", JOptionPane.ERROR_MESSAGE );
                        }
                    }
                }
                catch( NumberFormatException e )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca una cantidad válida", "Abastecer libro", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Seleccione un libro", "Abastecer libro", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Método para disminuir las unidades disponibles de un libro existente. <br>
     * <b>post: </b> La cantidad actual del libro se disminuye por el número de unidades que el usuario desea vender, si no hay unidades suficientes se informa al usuario.
     * @param pLibro Libro del cual se desea vender unidades.
     */
    public void venderLibro( Libro pLibro )
    {
        if( pLibro != null )
        {
            String respuesta = JOptionPane.showInputDialog( this, "Introduzca la cantidad de unidades", "Vender libro", JOptionPane.QUESTION_MESSAGE );
            if( respuesta != null )
            {
                try
                {
                    int unidades = Integer.parseInt( respuesta );
                    if( unidades <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "La cantidad debe ser mayor a cero. ", "Vender libro", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                        Date fechaActual = new Date( );
                        SimpleDateFormat formato = new SimpleDateFormat( "yyyy-mm-dd hh:mm:ss" );
                        String fechaString = formato.format( fechaActual );
                        boolean vender = tiendaDeLibros.vender( pLibro.darIsbn( ), unidades, fechaString );
                        if( !vender )
                        {
                            JOptionPane.showMessageDialog( this, "No hay suficientes libros para vender", "Vender libro", JOptionPane.ERROR_MESSAGE );
                        }
                        else
                        {
                            panelBodega.refrescar( tiendaDeLibros.darCatalogo( ) );
                            panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
                            panelBodega.cambiarSeleccionado( pLibro );
                            panelConsultas.limpiar( );
                        }
                    }
                }
                catch( NumberFormatException e )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca una cantidad válida", "Vender libro", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Seleccione un libro", "Vender libro", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Método para eliminar el libro seleccionado por el usuario. <br>
     * <b>post: </b> Se elimina el libro de la tienda si es posible. En caso contrario, se informa al usuario.
     * @param pLibro Libro a eliminar.
     */
    public void eliminarLibro( Libro pLibro )
    {
        if( pLibro != null )
        {
            boolean exito = tiendaDeLibros.eliminarLibro( pLibro.darIsbn( ) );
            if( !exito )
            {
                JOptionPane.showMessageDialog( this, "No fue posible eliminar el libro, aún posee unidades", "Eliminar libro", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                actualizar( );
                JOptionPane.showMessageDialog( this, "Se ha eliminado el libro", "Eliminar libro", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Seleccione un libro", "Eliminar libro", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Busca el libro que su ISBN coincide con el ingresado por el usuario. <br>
     * <b>post: </b> Se refresca el panel con el libro encontrado. Si no lo encuentra notifica al usuario.
     */
    public void buscarPorISBN( )
    {
        String respuesta = JOptionPane.showInputDialog( this, "Introduzca el ISBN", "Buscar libro", JOptionPane.QUESTION_MESSAGE );
        if( respuesta != null )
        {
            if( !respuesta.equals( "" ) )
            {
                Libro encontrado = tiendaDeLibros.buscarLibroPorISBN( respuesta );
                if( encontrado == null )
                {
                    JOptionPane.showMessageDialog( this, "No se encontró ningún libro con ese ISBN", "Buscar libro", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    panelConsultas.actualizar( encontrado );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Introduzca el ISBN", "Buscar libro", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Busca el libro que su título coincide con el ingresado por el usuario. <br>
     * <b>post: </b> Se refresca el panel con el libro encontrado. Si no lo encuentra notifica al usuario.
     */
    public void buscarPorTitulo( )
    {
        String respuesta = JOptionPane.showInputDialog( this, "Introduzca el título", "Buscar por título", JOptionPane.QUESTION_MESSAGE );
        if( respuesta != null )
        {
            if( !respuesta.equals( "" ) )
            {
                Libro encontrado = tiendaDeLibros.buscarLibroPorTitulo( respuesta );
                if( encontrado == null )
                {
                    JOptionPane.showMessageDialog( this, "No se encontró ningún libro con ese título", "Buscar por título", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    panelConsultas.actualizar( encontrado );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Introduzca el título", "Buscar por título", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Busca el libro con el mayor precio en la tienda. <br>
     * <b>post: </b> Se refresca el panel con el libro encontrado.
     */
    public void buscarMasCostoso( )
    {
        Libro encontrado = tiendaDeLibros.darLibroMasCostoso( );
        panelConsultas.actualizar( encontrado );
    }

    /**
     * Busca el libro con el menor precio en la tienda. <br>
     * <b>post: </b> Se refresca el panel con el libro encontrado.
     */
    public void buscarMasEconomico( )
    {
        Libro encontrado = tiendaDeLibros.darLibroMasEconomico( );
        panelConsultas.actualizar( encontrado );
    }

    /**
     * Busca el libro con el mayor número de unidades vendidas en la tienda. <br>
     * <b>post: </b> Se refresca el panel con el libro encontrado.
     */
    public void buscarMasVendido( )
    {
        Libro encontrado = tiendaDeLibros.darLibroMasVendido( );
        panelConsultas.actualizar( encontrado );
    }

    /**
     * Actualiza la información.
     */
    public void actualizar( )
    {
        ArrayList<Libro> catalogo = tiendaDeLibros.darCatalogo( );
        panelBodega.refrescar( catalogo );
        if(!catalogo.isEmpty( ))
        {
            panelBodega.cambiarSeleccionado( catalogo.get( 0 ) );
        }
        panelConsultas.limpiar( );
        panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la opción 1.
     */
    public void reqFuncOpcion1( )
    {
        String respuesta = tiendaDeLibros.metodo1(lim_inf, lim_sup, descuentos );
        actualizar( );
        JOptionPane.showMessageDialog( this, respuesta, "Requerimiento 1", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Este método ejecuta la opción 2.
     */
    public void reqFuncOpcion2( )
    {
        Transaccion transaccion = tiendaDeLibros.metodo2( );
        actualizar( );
        
        String respuesta = "N/A";
        
        if (transaccion != null)
        {
        	respuesta = transaccion.darIdTransaccion();
        }
        
        JOptionPane.showMessageDialog( this, "La transaccion más cara fué: " + respuesta, "Requerimiento 2", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación.
     * @param pArgs Parámetros de la ejecución. No son necesarios.
     */
    public static void main( String[] pArgs )
    {
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazTiendaLibros ventana = new InterfazTiendaLibros( );
            ventana.setVisible( true );
            ventana.actualizar();
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}

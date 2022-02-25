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

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.tiendadelibros.mundo.Libro;

/**
 * Panel que muestra la información actual de la bodega en la tienda de libros.
 */
@SuppressWarnings("serial")
public class PanelBodega extends JPanel implements ActionListener, ListSelectionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para registrar un nuevo libro.
     */
    public final static String REGISTRAR = "Registrar";

    /**
     * Constante para abastecer el libro seleccionado.
     */
    public final static String ABASTECER = "Abastecer";

    /**
     * Constante para vender el libro seleccionado.
     */
    public final static String VENDER = "Vender";

    /**
     * Constante para eliminar el libro seleccionado.
     */
    public final static String ELIMINAR = "Eliminar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta para imagen del libro.
     */
    private JLabel imagen;

    /**
     * Etiqueta ISBN libro actual.
     */
    private JLabel lblISBN;

    /**
     * Etiqueta Título libro actual.
     */
    private JLabel lblTitulo;

    /**
     * Etiqueta Precio de compra libro actual.
     */
    private JLabel lblPrecioCompra;

    /**
     * Etiqueta Precio de venta libro actual.
     */
    private JLabel lblPrecioVenta;

    /**
     * Etiqueta Cantidad libro actual.
     */
    private JLabel lblCantidad;

    /**
     * Campo de texto para el ISBN libro actual.
     */
    private JTextField txtISBN;

    /**
     * Campo de texto para el título libro actual.
     */
    private JTextField txtTitulo;

    /**
     * Campo de texto para el precio de compra libro actual.
     */
    private JTextField txtPrecioCompra;

    /**
     * Campo de texto para el precio de venta libro actual.
     */
    private JTextField txtPrecioVenta;

    /**
     * Campo de texto para la cantidad libro actual.
     */
    private JTextField txtCantidad;

    /**
     * Scroll que contiene la lista de libros.
     */
    private JScrollPane scroll;

    /**
     * La lista de libros.
     */
    private JList libros;

    /**
     * Botón registrar.
     */
    private JButton btnRegistrar;

    /**
     * Botón abastecer.
     */
    private JButton btnAbastecer;

    /**
     * Botón vender.
     */
    private JButton btnVender;

    /**
     * Botón eliminar.
     */
    private JButton btnEliminar;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la tienda de libros.
     */
    private InterfazTiendaLibros interfaz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel de la bodega para la tienda de libros. <br>
     * <b>post: </b> El panel se creó, la lista de libros está vacía.
     * @param pPrincipal Ventana principal de la aplicación.
     */
    public PanelBodega( InterfazTiendaLibros pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout( ) );

        JPanel actual = new JPanel( );
        actual.setLayout( new GridLayout( 5, 2 ) );
        actual.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );

        lblISBN = new JLabel( "ISBN: " );
        actual.add( lblISBN );
        txtISBN = new JTextField( );
        txtISBN.setEditable( false );
        actual.add( txtISBN );

        lblTitulo = new JLabel( "Título: " );
        actual.add( lblTitulo );
        txtTitulo = new JTextField( );
        txtTitulo.setEditable( false );
        actual.add( txtTitulo );

        lblPrecioCompra = new JLabel( "Precio Compra: " );
        actual.add( lblPrecioCompra );
        txtPrecioCompra = new JTextField( );
        txtPrecioCompra.setEditable( false );
        actual.add( txtPrecioCompra );

        lblPrecioVenta = new JLabel( "Precio Venta: " );
        actual.add( lblPrecioVenta );
        txtPrecioVenta = new JTextField( );
        txtPrecioVenta.setEditable( false );
        actual.add( txtPrecioVenta );

        lblCantidad = new JLabel( "Unidades: " );
        actual.add( lblCantidad );
        txtCantidad = new JTextField( );
        txtCantidad.setEditable( false );
        actual.add( txtCantidad );

        JPanel botones = new JPanel( );
        botones.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        botones.setLayout( new GridLayout( 1, 3 ) );

        btnAbastecer = new JButton( ABASTECER );
        btnAbastecer.addActionListener( this );
        btnAbastecer.setActionCommand( ABASTECER );
        botones.add( btnAbastecer );

        btnVender = new JButton( VENDER );
        btnVender.addActionListener( this );
        btnVender.setActionCommand( VENDER );
        botones.add( btnVender );

        btnEliminar = new JButton( ELIMINAR );
        btnEliminar.addActionListener( this );
        btnEliminar.setActionCommand( ELIMINAR );
        botones.add( btnEliminar );

        imagen = new JLabel( );

        JPanel centro = new JPanel( );
        centro.setLayout( new BorderLayout( ) );
        centro.setBorder( new TitledBorder( "Libro Actual" ) );
        centro.add( actual, BorderLayout.CENTER );
        centro.add( botones, BorderLayout.SOUTH );
        centro.add( imagen, BorderLayout.WEST );
        add( centro, BorderLayout.CENTER );

        JPanel oeste = new JPanel( );
        oeste.setLayout( new BorderLayout( ) );
        oeste.setPreferredSize( new Dimension( 300, 400 ) );
        add( oeste, BorderLayout.WEST );

        libros = new JList( );
        libros.addListSelectionListener( this );
        scroll = new JScrollPane( );
        setBorder( new TitledBorder( "Bodega de la tienda" ) );
        scroll.setViewportView( libros );
        scroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

        btnRegistrar = new JButton( REGISTRAR );
        btnRegistrar.addActionListener( this );
        btnRegistrar.setActionCommand( REGISTRAR );
        oeste.add( scroll, BorderLayout.CENTER );
        oeste.add( btnRegistrar, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista con el catálogo enviado por parámetro.
     * @param pCatalogo Catálogo de libros de la tienda.
     */
    public void refrescar( ArrayList<Libro> pCatalogo )
    {
        libros.setListData( pCatalogo.toArray( ) );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( REGISTRAR ) )
        {
            interfaz.dialogoRegistrarLibro( );
        }
        else if( comando.equals( ABASTECER ) )
        {
            interfaz.abastecerLibro( ( Libro )libros.getSelectedValue( ) );
        }
        else if( comando.equals( VENDER ) )
        {
            interfaz.venderLibro( ( Libro )libros.getSelectedValue( ) );
        }
        else
        {
            interfaz.eliminarLibro( ( Libro )libros.getSelectedValue( ) );
        }

    }

    /**
     * Manejo de los eventos del cambio de valor en la lista de libros.
     * @param pEvento Cambio de elemento que generó el evento.
     */
    public void valueChanged( ListSelectionEvent pEvento )
    {
        if( libros.getSelectedValue( ) != null )
        {
            Libro pLibro = ( Libro )libros.getSelectedValue( );
            txtTitulo.setText( pLibro.darTitulo( ) );
            txtISBN.setText( pLibro.darIsbn( ) );
            txtCantidad.setText( pLibro.darCantidadActual( ) + "" );
            NumberFormat formatter = NumberFormat.getNumberInstance( );
            String moneyString = formatter.format( pLibro.darPrecioCompra( ) );
            txtPrecioCompra.setText( "$" + moneyString );
            String moneyString2 = formatter.format( pLibro.darPrecioVenta( ) );
            txtPrecioVenta.setText( "$" + moneyString2 );
            imagen.setIcon( new ImageIcon( new ImageIcon( pLibro.darRutaImagen( ) ).getImage( ).getScaledInstance( 120, 176, Image.SCALE_DEFAULT ) ) );
        }
        else
        {
            txtTitulo.setText( "" );
            txtISBN.setText( "" );
            txtCantidad.setText( "" );
            txtPrecioCompra.setText( "" );
            txtPrecioVenta.setText( "" );
            imagen.setIcon( null );
        }
    }

    /**
     * Modifica el elemento seleccionado de la lista.
     * @param pLibro Elemento de la lista a seleccionar.
     */
    public void cambiarSeleccionado( Libro pLibro )
    {
        libros.setSelectedValue( pLibro, true );
    }

}

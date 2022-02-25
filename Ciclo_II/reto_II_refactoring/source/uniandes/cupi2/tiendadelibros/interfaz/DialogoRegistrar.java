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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Ventana para el registro de un nuevo libro en la tienda.
 */
@SuppressWarnings("serial")
public class DialogoRegistrar extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para confirmar el registro.
     */
    public final static String REGISTRAR = "Registrar";

    /**
     * Constante para cancelar el registro.
     */
    public final static String CANCELAR = "Cancelar";

    /**
     * Constante para elegir la imagen.
     */
    public final static String IMAGEN = "Elegir imagen";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta ISBN.
     */
    private JLabel lblISBN;

    /**
     * Etiqueta título.
     */
    private JLabel lblTitulo;

    /**
     * Etiqueta precio de compra.
     */
    private JLabel lblPrecioCompra;

    /**
     * Etiqueta precio de venta.
     */
    private JLabel lblPrecioVenta;

    /**
     * Campo de texto para el ISBN.
     */
    private JTextField txtISBN;

    /**
     * Campo de texto para el título.
     */
    private JTextField txtTitulo;

    /**
     * Campo de texto para el precio de compra.
     */
    private JTextField txtPrecioCompra;

    /**
     * Campo de texto para el precio de venta.
     */
    private JTextField txtPrecioVenta;

    /**
     * Campo de texto para la ruta de la imagen.
     */
    private JTextField txtRutaImagen;

    /**
     * Botón elegir imagen.
     */
    private JButton btnImagen;

    /**
     * Botón registrar.
     */
    private JButton btnRegistrar;

    /**
     * Botón cancelar.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la tienda de libros.
     */
    private InterfazTiendaLibros principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo.
     * @param pPrincipal Ventana principal de la aplicación.
     */
    public DialogoRegistrar( InterfazTiendaLibros pPrincipal )
    {
        principal = pPrincipal;

        setTitle( "Registrar Libro" );
        setSize( 400, 200 );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        setLayout( new GridLayout( 6, 2 ) );

        lblISBN = new JLabel( "ISBN: " );
        add( lblISBN );
        txtISBN = new JTextField( );
        add( txtISBN );

        lblTitulo = new JLabel( "Título: " );
        add( lblTitulo );
        txtTitulo = new JTextField( );
        add( txtTitulo );

        lblPrecioCompra = new JLabel( "Precio de compra: " );
        add( lblPrecioCompra );
        txtPrecioCompra = new JTextField( );
        add( txtPrecioCompra );

        lblPrecioVenta = new JLabel( "Precio de venta: " );
        add( lblPrecioVenta );
        txtPrecioVenta = new JTextField( );
        add( txtPrecioVenta );

        txtRutaImagen = new JTextField( );
        txtRutaImagen.setEditable( false );
        add( txtRutaImagen );
        btnImagen = new JButton( "Seleccionar Imagen" );
        btnImagen.addActionListener( this );
        btnImagen.setActionCommand( IMAGEN );
        add( btnImagen );

        btnRegistrar = new JButton( "Registrar" );
        btnRegistrar.addActionListener( this );
        btnRegistrar.setActionCommand( REGISTRAR );
        add( btnRegistrar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        add( btnCancelar );

        setModal( true );
        setLocationRelativeTo( null );
        setResizable( false );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( CANCELAR ) )
        {
            dispose( );
        }
        else if( comando.equals( IMAGEN ) )
        {
            JFileChooser fc = new JFileChooser( "./data/imagenes" );
            fc.showOpenDialog( this );

            if( fc.getSelectedFile( ) != null )
            {
                txtRutaImagen.setText( fc.getSelectedFile( ).getPath( ) );
            }
        }
        else
        {
            principal.registrarLibro( txtTitulo.getText( ), txtISBN.getText( ), txtPrecioCompra.getText( ), txtPrecioVenta.getText( ), txtRutaImagen.getText( ), this );
        }

    }

}

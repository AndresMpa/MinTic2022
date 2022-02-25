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
import javax.swing.border.TitledBorder;

/**
 * Panel para el acceso a los métodos de extensión.
 */
@SuppressWarnings("serial")
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para el método de extensión 1.
     */
    public final static String COMANDO_1 = "UNO";

    /**
     * Constante para el método de extensión 2.
     */
    public final static String COMANDO_2 = "DOS";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Botón opción 1.
     */
    private JButton btnOpc1;

    /**
     * Botón opción 2.
     */
    private JButton btnOpc2;

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
     * Constructor del panel para los métodos de extensión.
     * @param pPrincipal Ventana principal de la aplicación.
     */
    public PanelExtension( InterfazTiendaLibros pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new GridLayout( 1, 2 ) );
        setBorder( new TitledBorder( "Botones reto" ) );

        btnOpc1 = new JButton( "Aplicar descuento" );
        btnOpc1.setActionCommand( COMANDO_1 );
        btnOpc1.addActionListener( this );
        add( btnOpc1 );

        btnOpc2 = new JButton( "Venta mas cara" );
        btnOpc2.setActionCommand( COMANDO_2 );
        btnOpc2.addActionListener( this );
        add( btnOpc2 );

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

        if( comando.equals( COMANDO_1 ) )
        {
            interfaz.reqFuncOpcion1( );
        }
        else
        {
            interfaz.reqFuncOpcion2( );
        }

    }
}

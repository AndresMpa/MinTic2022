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
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Panel que muestra la caja de la tienda.
 */
@SuppressWarnings("serial")
public class PanelCaja extends JPanel
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta de la caja.
     */
    private JLabel lblCaja;

    /**
     * Campo de texto caja.
     */
    private JTextField txtCaja;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel que muestra el dinero en caja de la tienda. <br>
     * <b>post: </b> El panel se creó, se muestra el presupuesto en la tienda.
     */
    public PanelCaja( )
    {
        setLayout( new GridLayout( 1, 2 ) );
        setBorder( new EmptyBorder( 8, 20, 8, 20 ) );

        lblCaja = new JLabel( "Caja: " );
        lblCaja.setHorizontalAlignment( getWidth( ) );
        add( lblCaja );
        txtCaja = new JTextField( );
        txtCaja.setEditable( false );
        add( txtCaja );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el valor en la caja.
     * @param pValor Nuevo valor en la caja.
     */
    public void actualizarCaja( double pValor )
    {
        NumberFormat formatter = NumberFormat.getInstance( );
        String moneyString = formatter.format( pValor );
        txtCaja.setText( "$" + moneyString );
    }
}

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

import javax.swing.*;

/**
 * Panel del banner de la aplicación.
 */
@SuppressWarnings("serial")
public class PanelImagen extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta de la imagen del banner.
     */
    private static final String RUTA_IMAGEN = "./data/imagenes/titulo.jpg";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta en la que se implementa la imagen.
     */
    private JLabel etiqueta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel del banner.
     */
    public PanelImagen( )
    {
        etiqueta = new JLabel( );
        etiqueta.setIcon( new ImageIcon( RUTA_IMAGEN ) );
        add( etiqueta );
    }
}

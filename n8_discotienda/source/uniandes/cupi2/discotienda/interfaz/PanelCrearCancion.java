/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Discotienda 
 * Autor: Nicolás López
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.discotienda.interfaz;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Es el panel donde se ingresan los datos para una nueva canci�n
 */
public class PanelCrearCancion extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es la etiqueta "Nombre"
     */
    private JLabel etiquetaNombre;

    /**
     * Es el campo de texto para ingresar el nombre de la nueva canci�n
     */
    private JTextField txtNombre;

    /**
     * Es la etiqueta "Precio"
     */
    private JLabel etiquetaPrecio;

    /**
     * Es el campo de texto para ingresar el precio de la nueva canci�n
     */
    private JTextField txtPrecio;

    /**
     * Es la etiqueta "Minutos"
     */
    private JLabel etiquetaMinutos;

    /**
     * Es el campo de texto para ingresar los minutos de la nueva canci�n
     */
    private JTextField txtMinutos;

    /**
     * Es la etiqueta "Segundos"
     */
    private JLabel etiquetaSegundos;

    /**
     * Es el campo de texto para ingresar los segundos de la nueva canci�n
     */
    private JTextField txtSegundos;

    /**
     * Es la etiqueta "Calidad"
     */
    private JLabel etiquetaCalidad;

    /**
     * Es el campo de texto para ingresar la calidad de la nueva canci�n
     */
    private JTextField txtCalidad;

    /**
     * Es la etiqueta "Tama�o"
     */
    private JLabel etiquetaTamano;

    /**
     * Es el campo de texto para ingresar el tama�o de la nueva canci�n
     */
    private JTextField txtTamano;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel creando las etiquetas y los campos de texto necesarios para crear una nueva canci�n
     */
    public PanelCrearCancion( )
    {
        setLayout( new GridLayout( 6, 2, 5, 5 ) );

        etiquetaNombre = new JLabel( "Nombre: " );
        etiquetaNombre.setFont( etiquetaNombre.getFont( ).deriveFont( Font.PLAIN ) );
        add( etiquetaNombre );

        txtNombre = new JTextField( 2 );
        add( txtNombre );

        etiquetaPrecio = new JLabel( "Precio ($): " );
        etiquetaPrecio.setFont( etiquetaPrecio.getFont( ).deriveFont( Font.PLAIN ) );
        add( etiquetaPrecio );

        txtPrecio = new JTextField( 2 );
        add( txtPrecio );

        etiquetaCalidad = new JLabel( "Calidad (kbps): " );
        etiquetaCalidad.setFont( etiquetaCalidad.getFont( ).deriveFont( Font.PLAIN ) );
        add( etiquetaCalidad );

        txtCalidad = new JTextField( 2 );
        add( txtCalidad );

        etiquetaTamano = new JLabel( "Tama�o (mb): " );
        etiquetaTamano.setFont( etiquetaTamano.getFont( ).deriveFont( Font.PLAIN ) );
        add( etiquetaTamano );

        txtTamano = new JTextField( 2 );
        add( txtTamano );

        etiquetaMinutos = new JLabel( "Minutos: " );
        etiquetaMinutos.setFont( etiquetaMinutos.getFont( ).deriveFont( Font.PLAIN ) );
        add( etiquetaMinutos );

        txtMinutos = new JTextField( 2 );
        add( txtMinutos );

        etiquetaSegundos = new JLabel( "Segundos: " );
        etiquetaSegundos.setFont( etiquetaSegundos.getFont( ).deriveFont( Font.PLAIN ) );
        add( etiquetaSegundos );

        txtSegundos = new JTextField( 2 );
        add( txtSegundos );

        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Da el valor del campo de texto con el nombre de la canci�n
     * @return El nombre de la canci�n
     */
    public String darNombre( )
    {
        return txtNombre.getText( );
    }

    /**
     * Da el valor del campo de texto con el precio de la canci�n
     * @return El precio de la canci�n
     */
    public String darPrecio( )
    {
        return txtPrecio.getText( );
    }

    /**
     * Da el valor del campo de texto con la calidad de la canci�n
     * @return La calidad de la canci�n
     */
    public String darCalidad( )
    {
        return txtCalidad.getText( );
    }

    /**
     * Da el valor del campo de texto con el tama�o de la canci�n
     * @return El tama�o de la canci�n
     */
    public String darTamano( )
    {
        return txtTamano.getText( );
    }

    /**
     * Da el valor del campo de texto con los minutos de la canci�n
     * @return La duraci�n en minutos de la canci�n
     */
    public String darMinutos( )
    {
        return txtMinutos.getText( );
    }

    /**
     * Da el valor del campo de texto con los segundos de la canci�n
     * @return La duraci�n en segundos de la canci�n
     */
    public String darSegundos( )
    {
        return txtSegundos.getText( );
    }
}

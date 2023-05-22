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

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Es el di�logo para crear una nuevo canci�n
 */
public class DialogoCrearCancion extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazDiscotienda principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel con los datos para crear la nueva canci�n
     */
    private PanelCrearCancion panelDatos;

    /**
     * Es el panel con los botones para guardar la canci�n
     */
    private PanelBotonesCancion panelBotones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo
     * 
     * @param id es una referencia a la clase principal de la interfaz
     */
    public DialogoCrearCancion( InterfazDiscotienda id )
    {
        super( id, true );
        principal = id;

        panelDatos = new PanelCrearCancion( );
        panelBotones = new PanelBotonesCancion( this );

        getContentPane( ).add( panelDatos, BorderLayout.CENTER );
        getContentPane( ).add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Crear Canci�n" );
        pack( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Guarda la canci�n
     */
    public void crearCancion( )
    {
        boolean parametersOk = true;
        double precio = 0;
        int calidad = 0;
        double tamano = 0;
        int minutos = 0;
        int segundos = 0;
        String nombre = panelDatos.darNombre( );
        if( nombre.equals( "" ) )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "Debe ingresar el nombre de la canci�n " );
        }
        try
        {
            precio = Double.parseDouble( panelDatos.darPrecio( ) );
            if( precio < 0 )
            {
                parametersOk = false;
                JOptionPane.showMessageDialog( this, "El precio ingresado no es un valor v�lido" );
            }
        }
        catch( Exception e )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "El precio ingresado no es un valor v�lido" );
        }

        try
        {
            calidad = Integer.parseInt( panelDatos.darCalidad( ) );
            if( calidad < 0 )
            {
                parametersOk = false;
                JOptionPane.showMessageDialog( this, "La calidad ingresada no es un valor v�lido" );
            }
        }
        catch( Exception e )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "La calidad ingresada no es un valor v�lido" );
        }

        try
        {
            tamano = Double.parseDouble( panelDatos.darTamano( ) );
            if( tamano < 0 )
            {
                parametersOk = false;
                JOptionPane.showMessageDialog( this, "El tama�o ingresado no es un valor v�lido" );
            }
        }
        catch( Exception e )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "El tama�o ingresado no es un valor v�lido" );
        }

        try
        {
            minutos = Integer.parseInt( panelDatos.darMinutos( ) );
            if( minutos < 0 )
            {
                parametersOk = false;
                JOptionPane.showMessageDialog( this, "Los minutos ingresados no son un valor v�lido" );
            }
        }
        catch( Exception e )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "Los minutos ingresados nos son un valor v�lido" );
        }

        try
        {
            segundos = Integer.parseInt( panelDatos.darSegundos( ) );
            if( segundos < 0 || segundos >= 60 )
            {
                parametersOk = false;
                JOptionPane.showMessageDialog( this, "Los segundos ingresados no son un valor v�lido" );
            }
        }
        catch( Exception e )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "Los segundos ingresados nos son un valor v�lido" );
        }

        if( parametersOk )
        {
            boolean ok = principal.crearCancion( nombre, minutos, segundos, precio, tamano, calidad );

            if( ok )
                dispose( );
        }
    }
}

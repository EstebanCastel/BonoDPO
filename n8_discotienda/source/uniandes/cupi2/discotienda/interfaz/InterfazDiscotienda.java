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

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import uniandes.cupi2.discotienda.mundo.*;

/**
 * Es la clase principal de la interfaz
 */
public class InterfazDiscotienda extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La ruta donde deben guardarse las facturas
     */
    private static final String RUTA_FACTURAS = "./data/facturas";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la discotienda
     */
    private Discotienda discotienda;

    /**
     * Es una referencia al disco de la cual se est�n mostrando los datos
     */
    private Disco discoSeleccionado;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel con los botones para las extensiones de la aplicaci�n
     */
    private PanelExtension panelExtension;

    /**
     * Es el panel con la informaci�n del disco seleccionado
     */
    private PanelDiscos panelDiscos;

    /**
     * Es el panel para presentar las canciones del disco
     */
    private PanelDatosCanciones panelDatosCanciones;

    /**
     * Es el panel donde se muestra una imagen decorativa
     */
    private PanelImagen panelImagen;

    /**
     * Es el panel donde se realizan los pedidos
     */
    private PanelPedido panelPedido;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicaci�n
     * @param d es la discotienda que se va a mostrar
     */
    public InterfazDiscotienda( Discotienda d )
    {
        discotienda = d;

        // Panel con la Imagen
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        // Panel central con los datos del disco, de las canciones y el bot�n para cargar un pedido
        JPanel panelCentral = new JPanel( new BorderLayout( ) );
        add( panelCentral, BorderLayout.CENTER );

        panelDiscos = new PanelDiscos( this, discotienda.darDiscos( ) );
        panelCentral.add( panelDiscos, BorderLayout.CENTER );

        panelDatosCanciones = new PanelDatosCanciones( this );
        panelCentral.add( panelDatosCanciones, BorderLayout.EAST );

        ArrayList discos = discotienda.darDiscos( );
        if( discos.size( ) > 0 )
        {
            cambiarDiscoSeleccionado( ( ( String )discos.get( 0 ) ) );
        }

        panelPedido = new PanelPedido( this );
        panelCentral.add( panelPedido, BorderLayout.SOUTH );

        // Panel inferior con los botones para las extensiones del ejercicio
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        setTitle( "miDiscoTienda" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        pack( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cambia el disco seleccionado en el panel de detalles del disco
     * @param nombreDisco el nombre del disco a mostrar los detalles
     */
    public void cambiarDiscoSeleccionado( String nombreDisco )
    {
        discoSeleccionado = discotienda.darDisco( nombreDisco );
        panelDiscos.cambiarDisco( discoSeleccionado );
        panelDatosCanciones.cambiarDisco( discoSeleccionado );
    }

    /**
     * Muestra el di�logo para agregar un nuevo disco a la discotienda
     */
    public void mostrarDialogoAgregarDisco( )
    {
        DialogoCrearDisco dialogo = new DialogoCrearDisco( this );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el di�logo para agregar una nueva canci�n al disco en el panel de detalles del disco
     */
    public void mostrarDialogoAgregarCancion( )
    {
        DialogoCrearCancion dialogo = new DialogoCrearCancion( this );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    /**
     * Crea un nuevo disco en la discotienda y actualiza el panel con la lista de discos <br>
     * <b>pre: <b>No debe haber otro disco con el mismo nombre en la discotienda
     * @param nombreDisco El nombreDisco del disco a crear
     * @param artista el artista del nuevo disco
     * @param genero el genero del nuevo disco
     * @param imagen el nombre de la imagen asociada al nuevo disco
     * @return Retorna true si la canci�n se pudo agregar. Esto sirve para saber si se debe cerrar el di�logo.
     */
    public boolean crearDisco( String nombreDisco, String artista, String genero, String imagen )
    {
        boolean ok = false;
        try
        {
            discotienda.agregarDisco( nombreDisco, artista, genero, imagen );
            panelDiscos.refrescarDiscos( discotienda.darDiscos( ) );
            ok = true;
        }
        catch( ElementoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
        }

        return ok;
    }

    /**
     * Crea una nueva canci�n en el disco que se muestra en los detalles de disco en la discotienda <br>
     * <b>pre: <b>No debe haber otra canci�n con el mismo nombre en el disco
     * @param nombre el nombre de la nueva canci�n
     * @param minutos el n�mero de minutos de duraci�n de la canci�n
     * @param segundos el n�mero de segundos de duraci�n de la canci�n
     * @param precio el precio de la canci�n
     * @param tamano el tama�o en MB de la canci�n
     * @param calidad la calidad de la canci�n en KBps
     * @return Retorna true si la canci�n se pudo agregar. Esto sirve para saber si se debe cerrar el di�logo.
     */
    public boolean crearCancion( String nombre, int minutos, int segundos, double precio, double tamano, int calidad )
    {
        boolean ok = false;

        if( discoSeleccionado != null )
        {
            try
            {
                discotienda.agregarCancionADisco( discoSeleccionado.darNombreDisco( ), nombre, minutos, segundos, precio, tamano, calidad );
                discoSeleccionado = discotienda.darDisco( discoSeleccionado.darNombreDisco( ) );
                panelDiscos.cambiarDisco( discoSeleccionado );
                ok = true;
            }
            catch( ElementoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ) );
            }
        }

        return ok;
    }

    /**
     * Vende una canci�n a una persona
     * @param disco el disco al que pertenece la canci�n que se va a vender - disco != null
     * @param cancion la canci�n que se va a vender - cancion != null
     */
    public void venderCancion( Disco disco, Cancion cancion )
    {
        String email = JOptionPane.showInputDialog( this, "Indique el email del comprador", "Email", JOptionPane.QUESTION_MESSAGE );
        if( email != null )
        {
            if( discotienda.validarEmail( email ) )
            {
                try
                {
                    String archivoFactura = discotienda.venderCancion( disco, cancion, email, RUTA_FACTURAS );
                    JOptionPane.showMessageDialog( this, "La factura se guard� en el archivo: " + archivoFactura, "Factura Guardada", JOptionPane.INFORMATION_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema guardando el archivo de la factura:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El email indicado no es v�lido", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Este m�todo se encarga de cargar la informaci�n de un pedido
     */
    public void cargarPedido( )
    {
        JFileChooser fc = new JFileChooser( "./data" );
        fc.setDialogTitle( "Pedido" );
        int resultado = fc.showOpenDialog( this );
        if( resultado == JFileChooser.APPROVE_OPTION )
        {
            File archivo = fc.getSelectedFile( );
            if( archivo != null )
            {
                try
                {
                    String archivoFactura = discotienda.venderListaCanciones( archivo, RUTA_FACTURAS );
                    JOptionPane.showMessageDialog( this, "La factura se guard� en el archivo: " + archivoFactura, "Factura Guardada", JOptionPane.INFORMATION_MESSAGE );
                }
                catch( FileNotFoundException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema leyendo el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema leyendo el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( ArchivoVentaException e )
                {
                    JOptionPane.showMessageDialog( this, "Se present� un problema debido al formato del archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Este m�todo se encarga de salvar la informaci�n de la discotienda, justo antes de cerrar la aplicaci�n
     */
    public void dispose( )
    {
        try
        {
            discotienda.salvarDiscotienda( );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la informaci�n de la discotienda:\n" + e.getMessage( ) + "\n�Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Ejecuta el punto de extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = discotienda.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta el punto de extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = discotienda.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta el punto de extensi�n 3
     */
    public void reqFuncOpcion3( )
    {
        String resultado = discotienda.metodo3( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta el punto de extensi�n 4
     */
    public void reqFuncOpcion4( )
    {
        String resultado = discotienda.metodo4( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta el punto de extensi�n 5
     */
    public void reqFuncOpcion5( )
    {
        String resultado = discotienda.metodo5( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta el punto de extensi�n 6
     */
    public void reqFuncOpcion6( )
    {
        String resultado = discotienda.metodo6( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Programa principal
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicaci�n
     * @param args son par�metros de ejecuci�n de la aplicaci�n. No se usan en este programa
     */
    public static void main( String[] args )
    {
        Discotienda discotienda = null;
        try
        {
            discotienda = new Discotienda( "./data/discotienda.discos" );
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            System.exit( 1 );
        }
        InterfazDiscotienda id = new InterfazDiscotienda( discotienda );
        id.setVisible( true );
    }

    /**
     * Alista la lista de discos en la interfaz
     */
	public void actualizarLista() {
		panelDiscos.refrescarDiscos(discotienda.darDiscos());
	}
}

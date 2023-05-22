package uniandes.cupi2.discotienda.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.discotienda.mundo.Cancion;
import uniandes.cupi2.discotienda.mundo.ElementoExisteException;
import uniandes.cupi2.discotienda.mundo.Disco;
import uniandes.cupi2.discotienda.mundo.Discotienda;
import uniandes.cupi2.discotienda.mundo.ArchivoVentaException;


public class DiscotiendaTest extends TestCase
{

    private Discotienda discotienda1;

    private Discotienda discotienda2;

    private void setupEscenario1( )
    {
        try
        {
            File archivo = new File( "./test/data/discotienda.dat" );
            if( archivo.exists( ) )
            {
                archivo.delete( );
            }

            discotienda1 = new Discotienda( "./test/data/discotienda.dat" );
        }
        catch( Exception e )
        {
            fail( "No deber a haber problemas cargando el archivo:" + e.getMessage( ) );
        }
    }


    private void setupEscenario2( )
    {
        try
        {
            File archivo = new File( "./test/data/discotienda.dat" );
            if( archivo.exists( ) )
            {
                archivo.delete( );
            }

            discotienda1 = new Discotienda( "./test/data/discotienda.dat" );
            discotienda1.agregarDisco( "disco1", "artista1", "genero1", "imagen1" );
            discotienda1.agregarCancionADisco( "disco1", "cancion1", 1, 1, 1, 1, 1 );
            discotienda1.agregarCancionADisco( "disco1", "cancion2", 2, 2, 2, 2, 2 );
            discotienda1.agregarCancionADisco( "disco1", "cancion3", 3, 3, 3, 3, 3 );

            discotienda1.agregarDisco( "disco2", "artista2", "genero2", "imagen2" );
            discotienda1.agregarCancionADisco( "disco2", "cancion1", 1, 1, 1, 1, 1 );
            discotienda1.agregarCancionADisco( "disco2", "cancion2", 2, 2, 2, 2, 2 );
            discotienda1.agregarCancionADisco( "disco2", "cancion3", 3, 3, 3, 3, 3 );

            discotienda1.agregarDisco( "disco3", "artista3", "genero3", "imagen3" );
            discotienda1.agregarCancionADisco( "disco3", "cancion1", 1, 1, 1, 1, 1 );
            discotienda1.agregarCancionADisco( "disco3", "cancion2", 2, 2, 2, 2, 2 );
            discotienda1.agregarCancionADisco( "disco3", "cancion3", 3, 3, 3, 3, 3 );

        }
        catch( Exception e )
        {
            fail( "No deber a haber problemas cargando el archivo:" + e.getMessage( ) );
        }
    }


    public void testDiscotienda( )
    {
        setupEscenario1( );

        ArrayList discos = discotienda1.darDiscos( );
        assertEquals( "El n mero de discos es incorrecto", 0, discos.size( ) );
    }


    public void testDarDiscoOk( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco1" );
        assertNotNull( "No se encontr  el disco", disco );
        assertEquals( "El nombre del disco retornado no es el esperado", "disco1", disco.darNombreDisco( ) );
    }

    public void testDarDiscoError( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco4" );
        assertNull( "Se encontr  un disco que no deber a existir en la discotienda", disco );
    }


    public void testAgregarDiscoOk( )
    {
        setupEscenario2( );

        try
        {
 
            discotienda1.agregarDisco( "Mi disco de prueba", "artistaPrueba", "Latino", "./data/imagenes/prueba.jpg" );
            assertEquals( "El disco no fue agregado correctamente a la discotienda", 4, discotienda1.darDiscos( ).size( ) );

            Disco disco = discotienda1.darDisco( "Mi disco de prueba" );
            assertNotNull( "No se encontr  el disco", disco );
            assertEquals( "El nombre del disco retornado no es el esperado", "Mi disco de prueba", disco.darNombreDisco( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "El disco deber a haberse agregado correctamente, sin generar una excepci n: " + e.getMessage( ) );
        }
    }

    public void testAgregarDiscoError( )
    {
        setupEscenario2( );

        int numeroDiscos = discotienda1.darDiscos( ).size( );
        try
        {
            discotienda1.agregarDisco( "disco1", "artistaPrueba", "Rock", "./data/imagenes/prueba.jpg" );
            fail( "El disco no deber a haberse agregado porque ya hay otro disco con el mismo nombre" );
        }
        catch( ElementoExisteException e )
        {
            int numeroDiscos2 = discotienda1.darDiscos( ).size( );
            assertEquals( "Cambi  el n mero de discos en la discotienda", numeroDiscos, numeroDiscos2 );
        }
    }


    public void testAgregarCancionADiscoOk( )
    {
        setupEscenario2( );

        try
        {
            discotienda1.agregarCancionADisco( "disco1", "cancion4", 4, 4, 4, 4, 4 );

            Disco disco = discotienda1.darDisco( "disco1" );
            assertNotNull( "No se encontr  el disco", disco );
            assertEquals( "La canci n no fue agregada al disco", 4, disco.darNombresCanciones( ).size( ) );

            Cancion c = disco.darCancion( "cancion4" );
            assertNotNull( "No se encontr  la cancion", c );
            assertEquals( "La canci n no fue agregada correctamente al disco", "cancion4", c.darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "La canci n deber a haberse agregado correctamente: " + e.getMessage( ) );
        }
    }


    public void testAgregarCancionADiscoError( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco1" );
        int numCanciones = disco.darNombresCanciones( ).size( );

        try
        {
            discotienda1.agregarCancionADisco( "disco1", "cancion2", 1, 20, 78.10, 2, 96 );
            fail( "La canci n no deber a haberse agregado porque el nombre est  repetido y se deber a generar una excepci n" );
        }
        catch( ElementoExisteException e )
        {

            int numCanciones2 = disco.darNombresCanciones( ).size( );
            assertEquals( "Cambi  el n mero de canciones en el disco", numCanciones, numCanciones2 );
        }
    }

    public void testSalvarDiscotienda( ) throws Exception
    {

        Date fecha = new Date( );
        long tiempo = fecha.getTime( );
        String archivo = "./test/data/discotienda" + tiempo + ".dat";


        try
        {
            discotienda1 = new Discotienda( archivo );
        }
        catch( Exception e )
        {
            fail( "no se pudo cargar el archivo de prueba" );
        }


        generarInformacion( discotienda1 );


        discotienda1.salvarDiscotienda( );


        discotienda2 = new Discotienda( archivo );

        compararDiscotiendas( discotienda1, discotienda2 );

        File archivoPrueba2 = new File( archivo );
        archivoPrueba2.delete( );
    }

    public void testValidarEmailOk( )
    {
        setupEscenario1( );

        assertTrue( "El email indicado es v lido", discotienda1.validarEmail( "pe-rojas@uniedu.edu.tx" ) );
        assertTrue( "El email indicado es v lido", discotienda1.validarEmail( "abc.def@cupi2.edu" ) );
        assertTrue( "El email indicado es v lido", discotienda1.validarEmail( "qwerty@freemail.com" ) );
        assertTrue( "El email indicado es v lido", discotienda1.validarEmail( "mail@correo.com.mm" ) );
    }

    public void testValidarEmailError( )
    {
        setupEscenario1( );

        assertFalse( "El email est  incompleto", discotienda1.validarEmail( "pe-rojas" ) );
        assertFalse( "El email est  incompleto", discotienda1.validarEmail( "abc.def@" ) );
        assertFalse( "El email est  incompleto", discotienda1.validarEmail( "qwerty@freemail" ) );
        assertFalse( "El email est  incompleto y termina con un punto", discotienda1.validarEmail( "qwerty@freemail." ) );
    }

    public void testVenderCancion( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco2" );
        Cancion cancion = disco.darCancion( "cancion2" );
        int unidadesVendidas = cancion.darUnidadesVendidas( );
        try
        {
            String nombreArchivoFactura = discotienda1.venderCancion( disco, cancion, "prueba@prueba.com", "./test/data/factura/" );
            assertNotNull( "El nombre del archivo no debe ser null", nombreArchivoFactura );

            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            assertTrue( "El archivo debe existir", archivoFactura.exists( ) );

            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            String titulo = br.readLine( );
            assertNotNull( "La l nea no es la esperada", titulo );

            String fecha = br.readLine( );
            assertNotNull( "La segunda l nea debe tener la fecha", fecha );
            assertTrue( "La l nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            String email = br.readLine( );
            assertNotNull( "La segunda l nea debe tener el email", email );
            assertTrue( "La l nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            String cancion1 = br.readLine( );
            assertNotNull( "La tercera l nea debe tener el nombre y el artista de la canci n", cancion1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion1, cancion1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion1.indexOf( "cancion2 - artista2" ) != -1 );

            String cancion2 = br.readLine( );
            assertNotNull( "La cuarta l nea debe tener el nombre del disco", cancion2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion2, cancion2.trim( ).indexOf( "disco2" ) == 0 );

            String numCanciones = br.readLine( );
            assertNotNull( "La quinta l nea debe tener el n mero de canciones", numCanciones );
            assertTrue( "La l nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n mero de canciones no es el esperado", numCanciones.indexOf( "1" ) != -1 );

            String total = br.readLine( );
            assertNotNull( "La sexta l nea debe tener el valor total", total );
            assertTrue( "La l nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "2,00" ) != -1 );

            int nuevasUnidadesVendidas = cancion.darUnidadesVendidas( );
            assertEquals( "El n mero de unidades vendidas de la canci n no aument  correctamente", unidadesVendidas + 1, nuevasUnidadesVendidas );
        }
        catch( IOException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
    }

    public void testCargarPedido( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            assertNotNull( "El nombre del archivo no debe ser null", nombreArchivoFactura );

            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            assertTrue( "El archivo debe existir", archivoFactura.exists( ) );

            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            String titulo = br.readLine( );
            assertNotNull( "La l nea no es la esperada", titulo );

            String fecha = br.readLine( );
            assertNotNull( "La segunda l nea debe tener la fecha", fecha );
            assertTrue( "La l nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            String email = br.readLine( );
            assertNotNull( "La segunda l nea debe tener el email", email );
            assertTrue( "La l nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            String cancion1_1 = br.readLine( );
            assertNotNull( "La tercera l nea debe tener el nombre y el artista de la canci n", cancion1_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion1_1, cancion1_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion1_1.indexOf( "cancion2 - artista1" ) != -1 );

            String cancion1_2 = br.readLine( );
            assertNotNull( "La cuarta l nea debe tener el nombre del disco", cancion1_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion1_2, cancion1_2.trim( ).indexOf( "disco1" ) == 0 );

            String cancion2_1 = br.readLine( );
            assertNotNull( "La quinta l nea debe tener el nombre y el artista de la canci n", cancion2_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion2_1, cancion2_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion2_1.indexOf( "cancion3 - artista1" ) != -1 );

            String cancion2_2 = br.readLine( );
            assertNotNull( "La sexta l nea debe tener el nombre del disco", cancion2_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion2_2, cancion2_2.trim( ).indexOf( "disco1" ) == 0 );

            String cancion3_1 = br.readLine( );
            assertNotNull( "La s ptima l nea debe tener el nombre y el artista de la canci n", cancion3_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion3_1, cancion3_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion3_1.indexOf( "cancion2 - artista2" ) != -1 );

            String cancion3_2 = br.readLine( );
            assertNotNull( "La octava l nea debe tener el nombre del disco", cancion3_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion3_2, cancion3_2.trim( ).indexOf( "disco2" ) == 0 );

            String numCanciones = br.readLine( );
            assertNotNull( "La novena l nea debe tener el n mero de canciones", numCanciones );
            assertTrue( "La l nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n mero de canciones no es el esperado", numCanciones.indexOf( "3" ) != -1 );

            String total = br.readLine( );
            assertNotNull( "La d cima l nea debe tener el valor total", total );
            assertTrue( "La l nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "7,00" ) != -1 );

            Disco disco1 = discotienda1.darDisco( "disco1" );
            Cancion cancion1 = disco1.darCancion( "cancion2" );
            assertEquals( "El n mero de unidades vendidas de la canci n no aument  correctamente", 1, cancion1.darUnidadesVendidas( ) );

            Cancion cancion2 = disco1.darCancion( "cancion3" );
            assertEquals( "El n mero de unidades vendidas de la canci n no aument  correctamente", 1, cancion2.darUnidadesVendidas( ) );

            Disco disco2 = discotienda1.darDisco( "disco2" );
            Cancion cancion3 = disco2.darCancion( "cancion2" );
            assertEquals( "El n mero de unidades vendidas de la canci n no aument  correctamente", 1, cancion3.darUnidadesVendidas( ) );

        }
        catch( IOException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
    }

    public void testCargarPedidoError1( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido1.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );

            
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

          
            String titulo = br.readLine( );
            assertNotNull( "La l nea no es la esperada", titulo );

          
            String fecha = br.readLine( );
            assertNotNull( "La segunda l nea debe tener la fecha", fecha );
            assertTrue( "La l nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

          
            String email = br.readLine( );
            assertNotNull( "La segunda l nea debe tener el email", email );
            assertTrue( "La l nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

           
            String cancion1_1 = br.readLine( );
            assertNotNull( "La tercera l nea debe tener el nombre y el artista de la canci n", cancion1_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion1_1, cancion1_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion1_1.indexOf( "cancion3 - artista1" ) != -1 );

           
            String cancion1_2 = br.readLine( );
            assertNotNull( "La cuarta l nea debe tener el nombre del disco", cancion1_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion1_2, cancion1_2.trim( ).indexOf( "disco1" ) == 0 );

           
            String cancion2_1 = br.readLine( );
            assertNotNull( "La quinta l nea debe tener el nombre y el artista de la canci n", cancion2_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion2_1, cancion2_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion2_1.indexOf( "cancion2 - artista2" ) != -1 );

           
            String cancion2_2 = br.readLine( );
            assertNotNull( "La sexta l nea debe tener el nombre del disco", cancion2_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion2_2, cancion2_2.trim( ).indexOf( "disco2" ) == 0 );

            
            String numCanciones = br.readLine( );
            assertNotNull( "La novena l nea debe tener el n mero de canciones", numCanciones );
            assertTrue( "La l nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n mero de canciones no es el esperado", numCanciones.indexOf( "2" ) != -1 );

           
            String total = br.readLine( );
            assertNotNull( "La d cima l nea debe tener el valor total", total );
            assertTrue( "La l nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "5,00" ) != -1 );

            
            String lineaVacia = br.readLine( );
            assertEquals( "La l nea no tiene el formato esperado", "", lineaVacia );

            String tituloNoEncontradas = br.readLine( );
            assertTrue( "La l nea no tiene el formato esperado", tituloNoEncontradas.startsWith( "Canciones no encontradas" ) );

            String noEncontradas = br.readLine( );
            assertTrue( "La l nea no tiene el formato esperado", noEncontradas.startsWith( "disco1@artista1#cancion2" ) );

        }
        catch( IOException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
    }

    
     
    public void testCargarPedidoError2( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido2.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            
            String titulo = br.readLine( );
            assertNotNull( "La l nea no es la esperada", titulo );

            
            String fecha = br.readLine( );
            assertNotNull( "La segunda l nea debe tener la fecha", fecha );
            assertTrue( "La l nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            
            String email = br.readLine( );
            assertNotNull( "La segunda l nea debe tener el email", email );
            assertTrue( "La l nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            String cancion1_1 = br.readLine( );
            assertNotNull( "La tercera l nea debe tener el nombre y el artista de la canci n", cancion1_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion1_1, cancion1_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion1_1.indexOf( "cancion2 - artista1" ) != -1 );

            String cancion1_2 = br.readLine( );
            assertNotNull( "La cuarta l nea debe tener el nombre del disco", cancion1_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion1_2, cancion1_2.trim( ).indexOf( "disco1" ) == 0 );

            String cancion2_1 = br.readLine( );
            assertNotNull( "La quinta l nea debe tener el nombre y el artista de la canci n", cancion2_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion2_1, cancion2_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion2_1.indexOf( "cancion2 - artista2" ) != -1 );

            String cancion2_2 = br.readLine( );
            assertNotNull( "La sexta l nea debe tener el nombre del disco", cancion2_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion2_2, cancion2_2.trim( ).indexOf( "disco2" ) == 0 );

            String numCanciones = br.readLine( );
            assertNotNull( "La novena l nea debe tener el n mero de canciones", numCanciones );
            assertTrue( "La l nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n mero de canciones no es el esperado", numCanciones.indexOf( "2" ) != -1 );

            String total = br.readLine( );
            assertNotNull( "La d cima l nea debe tener el valor total", total );
            assertTrue( "La l nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "4,00" ) != -1 );

            String lineaVacia = br.readLine( );
            assertEquals( "La l nea no tiene el formato esperado", "", lineaVacia );

            String tituloNoEncontradas = br.readLine( );
            assertTrue( "La l nea no tiene el formato esperado", tituloNoEncontradas.startsWith( "Canciones no encontradas" ) );

            String noEncontradas = br.readLine( );
            assertTrue( "La l nea no tiene el formato esperado", noEncontradas.startsWith( "disco1#artista1#cancion5" ) );
        }
        catch( IOException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
    }

    public void testCargarPedidoError3( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido2.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            String titulo = br.readLine( );
            assertNotNull( "La l nea no es la esperada", titulo );

            String fecha = br.readLine( );
            assertNotNull( "La segunda l nea debe tener la fecha", fecha );
            assertTrue( "La l nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            String email = br.readLine( );
            assertNotNull( "La segunda l nea debe tener el email", email );
            assertTrue( "La l nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            String cancion1_1 = br.readLine( );
            assertNotNull( "La tercera l nea debe tener el nombre y el artista de la canci n", cancion1_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion1_1, cancion1_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion1_1.indexOf( "cancion2 - artista1" ) != -1 );

            String cancion1_2 = br.readLine( );
            assertNotNull( "La cuarta l nea debe tener el nombre del disco", cancion1_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion1_2, cancion1_2.trim( ).indexOf( "disco1" ) == 0 );

            String cancion2_1 = br.readLine( );
            assertNotNull( "La quinta l nea debe tener el nombre y el artista de la canci n", cancion2_1 );
            assertTrue( "La l nea no tiene el formato esperado - " + cancion2_1, cancion2_1.startsWith( "Canci n:" ) );
            assertTrue( "El contenido de la l nea no es el esperado", cancion2_1.indexOf( "cancion2 - artista2" ) != -1 );


            String cancion2_2 = br.readLine( );
            assertNotNull( "La sexta l nea debe tener el nombre del disco", cancion2_2 );
            assertTrue( "El contenido de la l nea no es el esperado - " + cancion2_2, cancion2_2.trim( ).indexOf( "disco2" ) == 0 );

            String numCanciones = br.readLine( );
            assertNotNull( "La novena l nea debe tener el n mero de canciones", numCanciones );
            assertTrue( "La l nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n mero de canciones no es el esperado", numCanciones.indexOf( "2" ) != -1 );

            String total = br.readLine( );
            assertNotNull( "La d cima l nea debe tener el valor total", total );
            assertTrue( "La l nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "4,00" ) != -1 );

            String lineaVacia = br.readLine( );
            assertEquals( "La l nea no tiene el formato esperado", "", lineaVacia );

            String tituloNoEncontradas = br.readLine( );
            assertTrue( "La l nea no tiene el formato esperado", tituloNoEncontradas.startsWith( "Canciones no encontradas" ) );

            String noEncontradas = br.readLine( );
            assertTrue( "La l nea no tiene el formato esperado", noEncontradas.startsWith( "disco1#artista1#cancion5" ) );

        }
        catch( IOException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber a producirse esta excepci n: " + e.getMessage( ) );
        }
    }

    private void compararDiscotiendas( Discotienda discotienda1, Discotienda discotienda2 )
    {
        ArrayList discos1 = discotienda1.darDiscos( );
        ArrayList discos2 = discotienda2.darDiscos( );
        assertEquals( "El n mero de discos es diferente", discos1.size( ), discos2.size( ) );

        for( int i = 0; i < discos1.size( ); i++ )
        {
            String nombre_disco = ( String )discos1.get( i );
            Disco d1 = discotienda1.darDisco( nombre_disco );
            Disco d2 = discotienda2.darDisco( nombre_disco );
            assertNotNull( "La segunda discotienda no conten a al disco " + nombre_disco, d2 );

            ArrayList canciones_d1 = d1.darNombresCanciones( );
            ArrayList canciones_d2 = d2.darNombresCanciones( );
            assertEquals( "El n mero de canciones es diferente", canciones_d1.size( ), canciones_d2.size( ) );
            for( int j = 0; j < canciones_d1.size( ); j++ )
            {
                String nombre_cancion = ( String )canciones_d1.get( j );
                Cancion c1 = d1.darCancion( nombre_cancion );
                Cancion c2 = d2.darCancion( nombre_cancion );
                assertNotNull( "El disco " + nombre_disco + "de la segunda discotienda no conten a la canci n " + nombre_cancion, d2 );

                assertEquals( "Los atributos de las dos canciones no son id nticos", c1.darCalidad( ), c2.darCalidad( ), 0 );
                assertEquals( "Los atributos de las dos canciones no son id nticos", c1.darMinutos( ), c2.darMinutos( ), 0 );
                assertEquals( "Los atributos de las dos canciones no son id nticos", c1.darNombre( ), c2.darNombre( ) );
                assertEquals( "Los atributos de las dos canciones no son id nticos", c1.darPrecio( ), c2.darPrecio( ), 0 );
                assertEquals( "Los atributos de las dos canciones no son id nticos", c1.darSegundos( ), c2.darSegundos( ), 0 );
                assertEquals( "Los atributos de las dos canciones no son id nticos", c1.darTamano( ), c2.darTamano( ), 0 );
            }
        }
    }

    private void generarInformacion( Discotienda discotienda )
    {
        int numeroDiscos = ( int ) ( Math.random( ) * 10 );
        int numeroCanciones = ( int ) ( Math.random( ) * 20 );

        try
        {
            for( int i = 0; i < numeroDiscos; i++ )
            {
                String nombreDisco = "disco_" + i;
                discotienda.agregarDisco( nombreDisco, "random", "m sica aleatoria", "random.jpg" );

                for( int j = 0; j < numeroCanciones; j++ )
                {
                    String nombreCancion = "cancion_" + j;
                    discotienda.agregarCancionADisco( nombreDisco, nombreCancion, 1, 2, 3, 4, 5 );
                }
            }
        }
        catch( ElementoExisteException e )
        {
            fail( "Deber a haberse podido agregar el disco o la canci n: " + e.getMessage( ) );
        }
    }
}
package uniandes.cupi2.discotienda.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.discotienda.mundo.*;

public class DiscoTest extends TestCase
{

    private Disco disco1;

    private void setupEscenario1( )
    {
        disco1 = new Disco( "Mi disco1", "artistaPrueba", "Latino", "prueba.jpg" );
    }

    private void setupEscenario2( )
    {
        disco1 = new Disco( "Mi disco1", "artistaPrueba", "Latino", "./data/imagenes/prueba.jpg" );

        Cancion c1 = new Cancion( "C1", 1, 20, 1.50, 2, 96, 2 );
        Cancion c2 = new Cancion( "C2", 1, 20, 3.45, 2, 96, 2 );
        Cancion c3 = new Cancion( "C3", 1, 20, 78.10, 2, 96, 2 );
        try
        {
            disco1.agregarCancion( c1 );
            disco1.agregarCancion( c2 );
            disco1.agregarCancion( c3 );
        }
        catch( ElementoExisteException e )
        {
            e.printStackTrace( );
        }
    }


    public void testAgregarCancion( )
    {
        setupEscenario1( );

        Cancion c1 = new Cancion( "Cancion1", 1, 20, 1.50, 2, 96, 0 );
        Cancion c2 = new Cancion( "Cancion1", 2, 40, 3.45, 2, 96, 0 );
        try
        {
            disco1.agregarCancion( c1 );
        }
        catch( ElementoExisteException e )
        {
            fail( );
        }
        try
        {
            disco1.agregarCancion( c2 );
            fail( );
        }
        catch( ElementoExisteException e )
        {
            assertTrue( true );
        }
    }

    public void testDatos( )
    {
        setupEscenario1( );

        assertEquals( "El nombre del disco1 est  mal", "Mi disco1", disco1.darNombreDisco( ) );
        assertEquals( "El artista del disco1 est  mal", "artistaPrueba", disco1.darArtista( ) );
        assertEquals( "El g nero del disco1 est  mal", "Latino", disco1.darGenero( ) );
        assertEquals( "La imagen del disco1 est  mal", "prueba.jpg", disco1.darImagen( ) );
    }


    public void testCalcularPrecio( )
    {
        setupEscenario2( );

        assertEquals( "El c lculo del precio del disco1 est  mal", "83.05", Double.toString( disco1.darPrecioDisco( ) ) );
    }


    public void testDarCancionOk( )
    {
        setupEscenario2( );

        Cancion c = disco1.darCancion( "C2" );
        assertNotNull( "La canci n C2 se deber a haber encontrado", c );
    }


    public void testDarCancionError( )
    {
        setupEscenario2( );

        Cancion c = disco1.darCancion( "C125" );
        assertNull( "La canci n C125 no se deber a haber encontrado", c );
    }


    public void testDarNombresCanciones( )
    {
        setupEscenario2( );

        ArrayList nombres = disco1.darNombresCanciones( );
        assertEquals( "El n mero de nombres es incorrecto", 3, nombres.size( ) );

        String nombre1 = ( String )nombres.get( 0 );
        String nombre2 = ( String )nombres.get( 1 );
        String nombre3 = ( String )nombres.get( 2 );

        assertEquals( "El nombre de la canci n no es el esperado", "C1", nombre1 );
        assertEquals( "El nombre de la canci n no es el esperado", "C2", nombre2 );
        assertEquals( "El nombre de la canci n no es el esperado", "C3", nombre3 );
    }

}


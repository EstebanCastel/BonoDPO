package uniandes.cupi2.discotienda.test;

import junit.framework.TestCase;
import uniandes.cupi2.discotienda.mundo.Cancion;

public class CancionTest extends TestCase
{

    private Cancion cancion1;

    private void setupEscenario1( )
    {
        cancion1 = new Cancion( "CancionPrueba", 1, 20, 1.5, 2.0, 96, 2 );
    }

    public void testDatos( )
    {
        setupEscenario1( );

        assertEquals( "El nombre de la canci n est  mal", "CancionPrueba", cancion1.darNombre( ) );
        assertEquals( "Los minutos de la canci n est  mal", 1, cancion1.darMinutos( ) );
        assertEquals( "Los segundos de la canci n est  mal", 20, cancion1.darSegundos( ) );
        assertEquals( "El precio de la canci n est  mal", "1.5", Double.toString( cancion1.darPrecio( ) ) );
        assertEquals( "El tama o de la canci n est  mal", "2.0", Double.toString( cancion1.darTamano( ) ) );
        assertEquals( "La calidad de la canci n est  mal", 96, cancion1.darCalidad( ) );
        assertEquals( "El n mero de unidades vendidas de la canci n est  mal", 2, cancion1.darUnidadesVendidas( ) );
    }


    public void testIncrementarUnidadesVendidas( )
    {
        setupEscenario1( );

        int unidadesVendidas = cancion1.darUnidadesVendidas( );
        int numeroIteraciones = ( int )Math.random( ) * 10000;
        for( int i = 0; i < numeroIteraciones; i++ )
        {
            unidadesVendidas++;
            cancion1.vender( );
            assertEquals( "El n mero de unidades vendidas no es correcto", unidadesVendidas, cancion1.darUnidadesVendidas( ) );
        }
    }

}

/** 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Discotienda
 * Autor: Mario Sánchez
 * Autor: Jorge Villalobos
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.discotienda.mundo;

/**
 * Esta excepci�n sirve para indicar que hubo un problema procesando el archivo de ventas
 */
public class ArchivoVentaException extends Exception
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * N�mero de canciones que se pudieron vender a pesar del error
     */
    private int cancionesVendidas;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n con una causa del error y la cantidad de canciones vendidas a pesar del error
     * @param causa El mensaje que describe el problema que se present�
     * @param ventas La cantidad de canciones vendidas a pesar del error
     */
    public ArchivoVentaException( String causa, int ventas )
    {
        super( causa );
        cancionesVendidas = ventas;
    }

    /**
     * Retorna la cantidad de canciones vendidas a pesar del error
     * @return La cantidad de canciones vendidas a pesar del error
     */
    public int darCancionesVendidas( )
    {
        return cancionesVendidas;
    }
}

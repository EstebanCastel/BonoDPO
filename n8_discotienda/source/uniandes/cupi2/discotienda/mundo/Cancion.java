/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Discotienda 
 * Autor: Nicolás López
 * Autor: Jorge Villalobos
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.discotienda.mundo;

import java.io.Serializable;

/**
 * Es la clase que representa a una canci�n en MP3 que hace parte de un disco. <br>
 * <b>inv: </b> <br>
 * nombre != null, nombre != "" <br>
 * minutos >= 0 <br>
 * 0 <= segundos < 60 <br>
 * minutos + segundos > 0 <br>
 * tamano > 0 <br>
 * calidad > 0 <br>
 * precio > 0 <br>
 * unidadesVendidas >= 0
 */
public class Cancion implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n
     */
    private static final long serialVersionUID = 200L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el nombre de la canci�n
     */
    private String nombre;

    /**
     * Es la duraci�n en minutos de la canci�n
     */
    private int minutos;

    /**
     * Es la duraci�n en segundos de la canci�n
     */
    private int segundos;

    /**
     * El tama�o en megabytes de la canci�n
     */
    private double tamano;

    /**
     * Es la calidad del mp3 en kbps p.e. 96 kbps, 128 kbps
     */
    private int calidad;

    /**
     * Es el precio de la canci�n;
     */
    private double precio;

    /**
     * El n�mero de veces que se ha vendido esta canci�n
     */
    private int unidadesVendidas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva canci�n con los datos suministrados
     * @param nombreC el nombre de la canci�n - nombreC != null, nombreC != ""
     * @param minutosC el n�mero de minutos de duraci�n de la canci�n - minutosC >= 0
     * @param segundosC el n�mero de segundos de duraci�n de la canci�n - segundosC >= 0 && segundosC < 60, minutos + segundos > 0
     * @param precioC es el precio de la canci�n - precioC > 0
     * @param tamanoC es el tama�o de la canci�n en MB - tamanoC > 0
     * @param calidadC es la calidad de la canci�n en Kbps - calidadC > 0
     * @param cantidad es el n�mero de veces que se ha vendido esta canci�n - cantidad >= 0
     */
    public Cancion( String nombreC, int minutosC, int segundosC, double precioC, double tamanoC, int calidadC, int cantidad )
    {
        nombre = nombreC;
        minutos = minutosC;
        segundos = segundosC;
        precio = precioC;
        tamano = tamanoC;
        calidad = calidadC;
        unidadesVendidas = cantidad;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la canci�n
     * @return Nombre de la canci�n
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la duraci�n en minutos de la canci�n
     * @return Duraci�n en minutos de la canci�n
     */
    public int darMinutos( )
    {
        return minutos;
    }

    /**
     * Retorna la duraci�n en segundos de la canci�n
     * @return Duraci�n en segundos de la canci�n
     */
    public int darSegundos( )
    {
        return segundos;
    }

    /**
     * Retorna el precio de la canci�n
     * @return Precio de la canci�n
     */
    public double darPrecio( )
    {
        return precio;
    }

    /**
     * Retorna el tama�o de la canci�n
     * @return Tama�o de la canci�n
     */
    public double darTamano( )
    {
        return tamano;
    }

    /**
     * Retorna la calidad de la canci�n
     * @return Calidad de la canci�n
     */
    public int darCalidad( )
    {
        return calidad;
    }

    /**
     * Retorna el n�mero de unidades vendidas
     * @return N�mero de unidades vendidas
     */
    public int darUnidadesVendidas( )
    {
        return unidadesVendidas;
    }

    /**
     * Aumenta en uno la cantidad de unidades vendidas
     */
    public void vender( )
    {
        unidadesVendidas++;
    }

    /**
     * Indica si la canci�n tiene el nombre que llega como par�metro
     * @param nombreCancion nombre de una canci�n - nombreCancion != null
     * @return True si la canci�n tiene el nombre que llega como par�metro
     */
    public boolean equals( String nombreCancion )
    {
        return nombre.equalsIgnoreCase( nombreCancion );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * nombre != null, nombre != "" <br>
     * minutos >= 0 <br>
     * 0 <= segundos < 60 <br>
     * minutos + segundos > 0 <br>
     * tamano > 0 <br>
     * calidad > 0 <br>
     * precio > 0 <br>
     * unidadesVendidas >= 0
     */
    private void verificarInvariante( )
    {
        assert nombre != null && !nombre.equals( "" ) : "El nombre es inv�lido";
        assert minutos >= 0 : "Los minutos deben ser un valor positivo";
        assert 0 <= segundos && segundos < 60 : "Los segundos deben ser un valor entre 0 y 60";
        assert minutos + segundos > 0 : "La canci�n debe tener una duraci�n positiva";
        assert tamano > 0 : "El tama�o debe ser un valor positivo";
        assert calidad > 0 : "La calidad debe ser un valor positivo";
        assert precio > 0 : "El precio debe ser un valor positivo";
        assert unidadesVendidas >= 0 : "El n�mero de unidades vendidas debe ser un entero positivo";
    }
}

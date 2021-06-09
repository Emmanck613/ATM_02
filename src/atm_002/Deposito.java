package atm_002;

// Deposito.java
// Representa una transacci�n de dep�sito en el ATM

public class Deposito extends Transaccion
{
   private double monto; // monto a depositar
   private RanuraDeposito ranuraDeposito; // referencia a la ranura de dep�sito
   private final static int CANCELO = 0; // constante para la opci�n de cancelar

   // constructor de Deposito
   public Deposito( int numeroCuentaUsuario, INTERFAZ_TRANSACCIONES pantallaATM, 
      BaseDatosBanco baseDatosBanco, 
      RanuraDeposito ranuraDepositoATM )
   {
      // inicializa las variables de la superclase
      super( numeroCuentaUsuario, pantallaATM, baseDatosBanco );

      // inicializa la referencia a la ranura de dep�sito
      ranuraDeposito = ranuraDepositoATM;
   } // fin del constructor de Deposito

   // realiza la transacci�n
   public void ejecutar()
   {
       BaseDatosBanco baseDatosBanco = obtenerBaseDatosBanco(); // obtiene la referencia
      INTERFAZ_TRANSACCIONES pantalla = obtenerPantalla(); // obtiene la referencia

      monto = pantalla.monto/100; // obtiene el monto a depositar del usuario

         // recibe el sobre de dep�sito
         boolean seRecibioSobre = ranuraDeposito.seRecibioSobre();

         // comprueba si se recibi� el sobre de dep�sito
         if ( seRecibioSobre )
         {  
             pantalla.jLabel_menu.setText("Deposito");
             pantalla.mensaje.setText("Se recibio su sobre"+"/n Presiona aceptar para continuar ");
            
            // hace un abono a la cuenta para reflejar el dep�sito
            baseDatosBanco.abonar( obtenerNumeroCuenta(), monto ); 
         } // fin de if
         else // no se recibi� el sobre de dep�sito
         {
            
         } // fin de else

   } // fin del m�todo ejecutar

   // pide al usuario que introduzca un monto a depositar en centavos
   private double pedirMontoADepositar()
   {
      INTERFAZ_TRANSACCIONES pantalla = obtenerPantalla(); // obtiene referencia a la pantalla

      // muestra el indicador
      pantalla.mensaje.setText( "\nIntroduzca un monto a depositar en " + 
         "CENTAVOS");
      int entrada = pantalla.monto; // recibe la entrada del monto de dep�sito
         return ( double ) entrada / 100; // devuelve el monto en d�lares

} // fin de la clase Deposito

}

/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigaci�n y prueba de las teor�as y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garant�a de      *
 * ning�n tipo, expresa o impl�cita, en relaci�n con estos programas o    *
 * con la documentaci�n contenida en estos libros. Los autores y el       *
 * editor no ser�n responsables en ning�n caso por los da�os consecuentes *
 * en conexi�n con, o que surjan de, el suministro, desempe�o o uso de    *
 * estos programas.                                                       *
 *************************************************************************/
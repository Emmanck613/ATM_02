package atm_002;


// Retiro.java
// Representa una transacci�n de retiro en el ATM

public class Retiro extends Transaccion
{
   private int monto; // monto a retirar
   private DispensadorEfectivo dispensadorEfectivo; // referencia al dispensador de efectivo

   // constructor de Retiro
   public Retiro( int numeroCuentaUsuario, INTERFAZ_TRANSACCIONES pantallaATM, 
      BaseDatosBanco baseDatosBanco,
      DispensadorEfectivo dispensadorEfectivoATM )
   {
      // inicializa las variables de la superclase
      super( numeroCuentaUsuario, pantallaATM, baseDatosBanco );
      
      // inicializa las referencias al dispensador de efectivo
      dispensadorEfectivo = dispensadorEfectivoATM;
   } // fin del constructor de Retiro

   // realiza la transacci�n
   public void ejecutar()
   {
      boolean efectivoDispensado = false; // no se ha dispensado a�n el efectivo
      double saldoTotal; // monto disponible para retirar

      // obtiene referencias a la base de datos del banco y la pantalla
      BaseDatosBanco baseDatosBanco = obtenerBaseDatosBanco(); 
      INTERFAZ_TRANSACCIONES pantalla = obtenerPantalla();

      // obtiene un monto de retiro elegido por el usuario
         monto = pantalla.monto;
            // obtiene el saldo disponible de la cuenta implicada
            saldoTotal = baseDatosBanco.obtenerSaldoTotal( obtenerNumeroCuenta() );
      
            // comprueba si el usuario tiene suficiente dinero en la cuenta 
            if ( monto <= saldoTotal )
            {   
               // comprueba si el dispensador de efectivo tiene suficiente dinero
               if ( dispensadorEfectivo.EfectivoDisponible( monto ) )
               {
                  // actualiza la cuenta implicada para reflejar el saldo
                  baseDatosBanco.cargar( obtenerNumeroCuenta(), monto );
                  
                  dispensadorEfectivo.dispensarEfectivo( monto ); // dispensar efectivo
                  efectivoDispensado = true; // se dispens� el efectivo
                  pantalla.jLabel_menu.setText("Retiro");
                  pantalla.jButton_ranura.setText("Toma tu dinero:");
                  pantalla.mensaje.setText("Exito! Presiona aceptar para continuar ");
                  
               } // fin de if
               else // el dispensador no tiene suficiente efectivo
               {
                  pantalla.jLabel_menu.setText("Aviso");
                  pantalla.mensaje.setText("Insuficiente efectivo en cajero."+"/nPresiona aceptar para continuar");
               }   
            } // fin de if
            else // no hay suficiente dinero disponible en la cuenta del usuario
            {
                pantalla.mensaje.setText("Aviso");
                pantalla.mensaje.setText("Insuficiente fondos en su cuenta");
            } // fin de else


   } // fin del m�todo ejecutar

} // fin de la clase Retiro



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
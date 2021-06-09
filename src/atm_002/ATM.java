/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_002;

/**
 *
 * @author Emmanuel Guerrero
 */
// ATM.java
// Representa a un cajero automático

public class ATM 
{
   public boolean usuarioAutenticado; // indica si el usuario es autenticado
   private int numeroCuentaActual; // current user's account number
   private DispensadorEfectivo dispensadorEfectivo; // dispensador de efectivo del ATM
   private RanuraDeposito ranuraDeposito; // ranura de depósito del ATM
   private BaseDatosBanco baseDatosBanco; //  base de datos de información de las cuentas
   private INTERFAZ_TRANSACCIONES transacciones;


   // constantes correspondientes a las opciones del menú principal
   private static final int SOLICITUD_SALDO = 1;
   private static final int RETIRO = 2;
   private static final int DEPOSITO = 3;


   // el constructor sin argumentos de ATM inicializa las variables de instancia
   public ATM(INTERFAZ_TRANSACCIONES atm) 
   {
      usuarioAutenticado = false; // al principio, el usuario no está autenticado
      numeroCuentaActual = 0; // al principio, no hay número de cuenta
      dispensadorEfectivo = new DispensadorEfectivo(); // crea el dispensador de efectivo
      ranuraDeposito = new RanuraDeposito(); // crea la ranura de depósito
      baseDatosBanco = new BaseDatosBanco(dispensadorEfectivo); // crea la base de datos de información de cuentas
      transacciones = atm;
   } // fin del constructor sin argumentos de ATM

   // trata de autenticar al usuario en la base de datos
   public void autenticarUsuario(int numCuenta, int nipCuenta) 
   {
      int numeroCuenta = numCuenta; // recibe como entrada el n�mero de cuenta
      int nip = nipCuenta; // recibe como entrada el NIP
       
      usuarioAutenticado = baseDatosBanco.autenticarUsuario( numeroCuenta, nip );
      
      // verifica si la autenticaci�n tuvo �xito
      if ( usuarioAutenticado ==true ){
         numeroCuentaActual = numeroCuenta; // guarda el # de cuenta del usuario  
      }  
   } // fin del m�todo autenticarUsuario

   // muestra el menú principal y realiza transacciones
   public void realizarTransacciones(int seleccion) 
   {
      // variable local para almacenar la transacción que se procesa actualmente
      Transaccion transaccionActual = null;
      
      boolean usuarioSalio = false; // el usuario no ha elegido salir

      // itera mientras que el usuario no haya elegido la opción para salir del sistema
      while ( !usuarioSalio )
      {     
         // muestra el menú principal y obtiene la selección del usuario
         int seleccionMenuPrincipal = seleccion;

         // decide cómo proceder, con base en la opción del menú seleccionada por el usuario
         switch ( seleccionMenuPrincipal )
         {
            // el usuario eligió realizar uno de tres tipos de transacciones
            case SOLICITUD_SALDO: 
            case RETIRO: 
            case DEPOSITO:

               // inicializa como nuevo objeto del tipo elegido
               transaccionActual = 
                  crearTransaccion( seleccionMenuPrincipal );

               transaccionActual.ejecutar(); // ejecuta la transacción
               break; 
   } 
      } 
   }    // fin del método realizarTransacciones
         
   // devuelve un objeto de la subclase especificada de Transaccion
   public Transaccion crearTransaccion( int tipo )
   {
      Transaccion temp = null; // variable temporal Transaccion
      
      // determina qué tipo de Transaccion crear     
      switch ( tipo )
      {
         case SOLICITUD_SALDO: // crea una nueva transacción SolicitudSaldo
            temp = new SolicitudSaldo( 
               numeroCuentaActual, transacciones, baseDatosBanco );
            break;
         case RETIRO: // crea una nueva transacción Retiro
            temp = new Retiro( numeroCuentaActual, transacciones, 
               baseDatosBanco, dispensadorEfectivo );
            break; 
         case DEPOSITO: // crea una nueva transacción Deposito
            temp = new Deposito( numeroCuentaActual, transacciones, 
               baseDatosBanco, ranuraDeposito );
            break;
      } // fin de switch

      return temp; // devuelve el obejto recién creado
   } // fin del método crearTransaccion
   
    public BaseDatosBanco obtenerBase()
   {
       return baseDatosBanco;
   }
   
} // fin de la clase ATM
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_002;

import javax.swing.JOptionPane;

    
    // BaseDatosBanco.java
// Representa a la base de datos de información de cuentas bancarias

public class BaseDatosBanco
{
   DispensadorEfectivo dispensadorEfectivo;
    
   public static Cuenta cuentas[]; // arreglo de objetos Cuenta
   // el constructor sin argumentos de BaseDatosBanco inicializa a cuentas
   public  BaseDatosBanco(DispensadorEfectivo retirar)
   {
      dispensadorEfectivo=retirar; 
      cuentas = new Cuenta[ 2 ]; // sólo 2 cuentas para probar
      cuentas[ 0 ] = new Cuenta(12345, 54321, 1200.0);
      cuentas[ 1 ] = new Cuenta(98765, 56789, 200.0 );  
   } // fin del constructor sin argumentos de BaseDatosBanco

   
   // obtiene el objeto Cuenta que contiene el número de cuenta especificado
   private Cuenta obtenerCuenta( int numeroCuenta ){
      // itera a través de cuentas, buscando el número de cuenta que coincida
      for ( Cuenta cuentaActual : cuentas ){
         // devuelve la cuenta actual si encuentra una coincidencia
         if ( cuentaActual.obtenerNumeroCuenta() == numeroCuenta )
            return cuentaActual;
      } // fin de for
      return null; // si no se enontró una cuenta que coincida, devuelve null
   } // fin del método obtenerCuenta

      public boolean obtenerCuentaExistente( int numeroCuenta )
   {
      // itera a trav�s de cuentas, buscando el n�mero de cuenta que coincida
      for ( Cuenta cuentaActual : cuentas )
      {
         // devuelve la cuenta actual si encuentra una coincidencia
         if ( cuentaActual.obtenerNumeroCuenta() == numeroCuenta )
            return true;
      } // fin de for

      return false; // si no se enontr� una cuenta que coincida, devuelve null
   } // fin del m�todo obtenerCuenta
   
   // determina si el número de cuenta y el NIP especificados por el usuario coinciden
   // con los de una cuenta en la base de datos
   public boolean autenticarUsuario( int numeroCuentaUsuario, int nipUsuario ){
      // trata de obtener la cuenta con el número de cuenta
      Cuenta cuentaUsuario = obtenerCuenta( numeroCuentaUsuario );
      // si la cuenta existe, devuelve el resultado del método validarNIP de Cuenta
      if ( cuentaUsuario != null )
         return cuentaUsuario.validarNIP( nipUsuario );
      else
         return false; // no se encontró el número de cuenta, por lo que devuelve false
   } // fin del método autenticarUsuario


   // devuelve el saldo total de la Cuenta con el número de cuenta especificado
   public double obtenerSaldoTotal( int numeroCuentaUsuario ){
      return obtenerCuenta( numeroCuentaUsuario ).obtenerSaldoTotal();
   } // fin del método obtenerSaldoTotal

   // abona un monto a la Cuenta a través del número de cuenta especificador
   public void abonar( int numeroCuentaUsuario, double monto ){
      obtenerCuenta( numeroCuentaUsuario ).abonar( monto );
   } // fin del método abonar

   // carga un monto a la Cuenta con el número de cuenta especificado
   public void cargar( int numeroCuentaUsuario, double monto ){
      obtenerCuenta( numeroCuentaUsuario ).cargar( monto );
   } // fin del método cargar
   
   
} // fin de la clase BaseDatosBanco



/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigación y prueba de las teorías y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garantía de      *
 * ningún tipo, expresa o implícita, en relación con estos programas o    *
 * con la documentación contenida en estos libros. Los autores y el       *
 * editor no serán responsables en ningún caso por los daños consecuentes *
 * en conexión con, o que surjan de, el suministro, desempeño o uso de    *
 * estos programas.                                                       *
 *************************************************************************/
    


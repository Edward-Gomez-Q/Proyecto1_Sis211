/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import bo.edu.ucb.est.eleccion.Eleccion;
import bo.edu.ucb.est.modelo.Banco;
import bo.edu.ucb.est.modelo.Cliente;
import bo.edu.ucb.est.modelo.Cuenta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author HP
 */

public class MultiusuarioBot extends TelegramLongPollingBot {
    public void Mensaje(Update update, String b){
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
        message.setText(b);
        try {
            execute(message); // Envia el mensaje
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    private Map<String, String> EstadoUsuario= new HashMap();
    private Map<String, String> NombreUsuario= new HashMap();
    private Map<String, String> PINUsuario= new HashMap();
    private Map<String, String> EleccionUsuario= new HashMap();
    private Map<String, String> CuentaUsuario= new HashMap();
    private Banco fortuna = new Banco("BANCO FORTUNA");
    private  int NumeroCuentas=10000;
    private Cliente cliente;
    Eleccion eleccion= new Eleccion();
    @Override
    public String getBotToken() {
        return "2020219176:AAFcBXEiFmqsjo0Rn4Dopt2P4G4PatcuG1U" ; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        if(update.hasMessage())
        {
            
            String id= Long.toString(update.getMessage().getChatId());
            if(EstadoUsuario.get(id)==null)
            {
                EstadoUsuario.put(id, "0");
            }
            switch(EstadoUsuario.get(id))
            {
                case "0":
                    Mensaje(update, "Bienvenid@ al Banco de la Fortuna.");
                    Mensaje(update, "He notado que aún no eres cliente, procedamos a registrarte.");
                    Mensaje(update, "¿Cual es tu nombre completo?");
                    EstadoUsuario.put(id, "1");
                    break;
                case "4":
                    Mensaje(update, ("Hola de nuevo " + NombreUsuario.get(id)));
                    Mensaje(update, "Solo por seguridad ¿Cual es tu PIN?");
                    EstadoUsuario.put(id, "3");
                    break;
                case "1":
                        if(isNumeric(update.getMessage().getText())==true)
                        {
                            Mensaje(update, "Error al ingresar el nombre, vuelva a intentar.");
                        }
                        else
                        {
                            Mensaje(update, "Por favor elige un PIN de seguridad, este te será requerido cada que ingrese al sistema.");
                            EstadoUsuario.put(id, "2");
                            NombreUsuario.put(id,update.getMessage().getText());
                        }
                        break;
                case "2": 
                        if(isNumeric(update.getMessage().getText())==false)
                        {
                            Mensaje(update, "Error al ingresar el PIN, vuelva a intentar.");
                        }
                        else
                        {
                            Mensaje(update, "Te hemos registrado correctamente.");
                            PINUsuario.put(id,update.getMessage().getText());
                            Mensaje(update, ("Hola de nuevo " + NombreUsuario.get(id)));
                            Mensaje(update, "Solo por seguridad ¿Cual es tu PIN?");
                            EstadoUsuario.put(id, "3");
                        }
                        break;
                case "3":
                        if(isNumeric(update.getMessage().getText())==true &&(update.getMessage().getText().equals(PINUsuario.get(id)) ))
                        {
                            Mensaje(update, "Bienvenido.");
                            Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                            PINUsuario.put(id,update.getMessage().getText());
                            EstadoUsuario.put(id, "5");
                            Cliente cliente = new Cliente(NombreUsuario.get(id), id, PINUsuario.get(id));
                            fortuna.agregarCliente(cliente);
                        }
                        else
                        {
                            
                            Mensaje(update, "Lo siento, el PIN es incorrecto.");
                            Mensaje(update, ("Hola de nuevo " + NombreUsuario.get(id)));
                            Mensaje(update, "Solo por seguridad ¿Cual es tu PIN?");
                        }
                        break;
                case "5":
                    if(isNumeric(update.getMessage().getText()) && Integer.parseInt(update.getMessage().getText())>=1 && Integer.parseInt(update.getMessage().getText())<=5 )
                    {
                        switch(update.getMessage().getText()){
                        case "1":   
                                    cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                                    if(cliente.MostrarCuentas(update)==false)
                                    {
                                        Mensaje(update,"Usted no tiene cuentas, cree una primero.");
                                        Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                    }
                                    else
                                    {
                                        EstadoUsuario.put(id, "6");
                                    }
                                    break;
                        case "2":
                                    cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                                    if(cliente.MostrarCuentas(update)==false)
                                    {
                                        Mensaje(update,"Usted no tiene cuentas, cree una primero.");
                                        Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                    }
                                    else
                                    {
                                        EstadoUsuario.put(id, "9");
                                    }
                                    break;
                        case "3":   
                                    cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                                    if(cliente.MostrarCuentas(update)==false)
                                    {
                                        Mensaje(update,"Usted no tiene cuentas, cree una primero.");
                                        Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                    }
                                    else
                                    {
                                        EstadoUsuario.put(id, "11");
                                    }
                                    break;
                        case "4":   Mensaje(update,"Seleccione la moneda:\n1. Dólares\n2. Bolivianos");
                                    EstadoUsuario.put(id, "8");
                                    break;
                        case "5":   Mensaje(update,"Adiós. Para volver a usar el Bot envie cualquier mensaje.");
                                    EstadoUsuario.put(id, "4");
                                    break;
                        }
                    }
                    else
                    {
                        Mensaje(update, "Error, seleccione una  de las opciones.");
                        Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                    }
                    break;
                case "6":   
                            cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                            if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>=1 && Integer.parseInt(update.getMessage().getText())<=cliente.TotalCuentas())
                            {
                                cliente.BuscarCuentas(Integer.parseInt(update.getMessage().getText()), 1, update, 0);
                                Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                EstadoUsuario.put(id, "5");
                            }
                            else
                            {
                                Mensaje(update, "Error, vuelva a intentar seleccionar una de sus cuentas.");
                            }
                            break;
                case "8":
                            if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>=1 && Integer.parseInt(update.getMessage().getText())<=2)
                            {
                                EleccionUsuario.put(id,update.getMessage().getText());
                                Mensaje(update,"Seleccione el tipo:\n1. Caja de Ahorros\n2. Cuenta Corriente");
                                EstadoUsuario.put(id,"7");
                            }
                            else
                            {
                                Mensaje(update, "Error");
                                Mensaje(update, "Seleccione la moneda:\n1. Dólares\n2. Bolivianos");
                            }
                            break;
                case "7":
                        cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                        if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>=1 && Integer.parseInt(update.getMessage().getText())<=2)
                        {
                            NumeroCuentas++;
                            String Moneda="",Tipo="", Correcto="Se ha creado una cuenta de tipo ";
                            switch(EleccionUsuario.get(id))
                            {
                                case "2":
                                    Moneda="BOB";
                                    
                                    break;
                                case "1":
                                    Moneda="USD";
                                    
                                    break;
                            }
                            switch(update.getMessage().getText())
                            {
                                case "2":
                                    
                                    Tipo="Cuenta Corriente";
                                    break;
                                case "1":
                                    
                                    Tipo="Caja de Ahorros";
                                    
                                    break;
                            }
                            Correcto=Correcto+ Tipo + " en " + Moneda + ", con saldo 0.0";
                            Cuenta cta = new Cuenta(NumeroCuentas,Moneda, Tipo,0.0);
                            cliente.agregarCuenta(cta);
                            Mensaje(update, Correcto);
                            EstadoUsuario.put(id,"5");
                            Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                        }
                        else
                        {
                            Mensaje(update, "Error");
                            Mensaje(update,"Seleccione el tipo:\n1. Caja de Ahorros\n2. Cuenta Corriente");
                        }
                        break;
                case "9":
                            cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                            if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>=1 && Integer.parseInt(update.getMessage().getText())<=cliente.TotalCuentas())
                            {
                                CuentaUsuario.put(id, update.getMessage().getText());
                                if(cliente.SaldoDeUnaCuenta(Integer.parseInt(CuentaUsuario.get(id)))>0)
                                {
                                    Mensaje(update, "Ingrese el monto a retirar: ");
                                    EstadoUsuario.put(id, "10");
                                }
                                else
                                {
                                    Mensaje(update, "Cuenta sin Fondos, se le llevará al menú principal.");
                                    Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                    EstadoUsuario.put(id, "5");
                                }
                                
                            }
                            else
                            {
                                Mensaje(update, "Error, vuelva a intentar seleccionar una de sus cuentas.");
                            }
                            break;
                case "10":
                    
                            
                            cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                            if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>0 && Integer.parseInt(update.getMessage().getText())<=cliente.SaldoDeUnaCuenta(Integer.parseInt(CuentaUsuario.get(id))))
                            {
                                cliente.BuscarCuentas(Integer.parseInt(CuentaUsuario.get(id)), 2, update, Integer.parseInt(update.getMessage().getText()));
                                Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                EstadoUsuario.put(id, "5");
                            }
                            else
                            {
                                Mensaje(update, "Error, se le llevará al menú principal.");
                                Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                EstadoUsuario.put(id, "5");
                            }
                            
                            break;
                case "11":
                            cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                            if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>=1 && Integer.parseInt(update.getMessage().getText())<=cliente.TotalCuentas())
                            {
                                CuentaUsuario.put(id, update.getMessage().getText());
                                Mensaje(update, "Ingrese el monto a depositar: ");
                                EstadoUsuario.put(id, "12");
                            }
                            else
                            {
                                Mensaje(update, "Error, vuelva a intentar seleccionar una de sus cuentas.");
                            }
      
                            break;
                case "12":
                            cliente=fortuna.buscarClientePorCodigo(id, PINUsuario.get(id));
                            if(isNumeric(update.getMessage().getText()) == true && Integer.parseInt(update.getMessage().getText())>0)
                            {
                                cliente.BuscarCuentas(Integer.parseInt(CuentaUsuario.get(id)), 3, update, Integer.parseInt(update.getMessage().getText()));
                                Mensaje(update, "Elige una opción:\n\n1. Ver Saldo.\n2. Retirar dinero.\n3. Depositar dinero.\n4. Crear Cuenta.\n5. Salir.");
                                EstadoUsuario.put(id, "5");
                            }
                            else
                            {
                                Mensaje(update, "Error, vuelva a ingresar el monto.");
                            }
                            break;
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "ucb_est_edward_proyecto_bo" ; //To change body of generated methods, choose Tools | Templates.
    }
}

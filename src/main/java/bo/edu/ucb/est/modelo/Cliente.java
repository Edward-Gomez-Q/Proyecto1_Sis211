/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import bo.edu.ucb.est.interfaz.Bienvenido;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ecampohermoso
 */
public class Cliente extends TelegramLongPollingBot {
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
    private String nombre;
    private String codigoCliente;
    private String pinSeguridad;
    private List<Cuenta> cuentas;
    double monto;
    boolean S;
    Scanner leer =new Scanner(System.in); 
    Bienvenido uno=new Bienvenido();
    
    public Cliente(String nombre, String codigoCliente, String pinSeguridad) {
        this.nombre = nombre;
        this.codigoCliente = codigoCliente;
        this.pinSeguridad = pinSeguridad;
        this.cuentas = new ArrayList();
    }
    public boolean MostrarCuentas(Update update){
        if(cuentas.size()==0)
        {
            return false;
        }
        else
        {
            int i;
            for (i = 0; i < cuentas.size(); i++) {
                Cuenta cuent = cuentas.get(i);
                cuent.Mostrar(i+1, update);
            }
            return true;
        }
    }
    public double SaldoDeUnaCuenta(int Eleccion)
    {
        return cuentas.get(Eleccion-1).getSaldo();
    }
    public void BuscarCuentas(int Eleccion, int opcion, Update update, int monto){
        if(Eleccion>0 && Eleccion<(cuentas.size()+1))
        {
            Cuenta cuent = cuentas.get(Eleccion-1);
            switch(opcion){
                case 1:
                    cuent.MostrarTodo(Eleccion-1, update);
                    break;
                case 2:
                    //cuent.MostrarTodo(Eleccion-1, update);
                    do{      
                        uno.retirar();
                        S=cuent.retirar(monto);
                        if(S==false){
                            Mensaje(update,"Error.");
                        }else{
                            Mensaje(update,"Operación Exitosa.");
                        }
                    }while(S==false);
                    break;
                case 3:
                    //cuent.MostrarTodo(Eleccion-1, update);
                    do{
                        uno.depositar();
                        S=cuent.depositar(monto);
                        if(S==false){
                            Mensaje(update,"Error.");
                        }else{
                            Mensaje(update,"Operación Exitosa.");
                        }
                    }while(S==false);
                    break;
            }
            
        }
        else
        {
            if(Eleccion==(cuentas.size()+1))
            {
                System.out.println("Volviendo...");
            }
            else
            {
                System.out.println("Error");
            }
            
        }
    }
    public int TotalCuentas()
    {
        return cuentas.size();
    }
    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getPinSeguridad() {
        return pinSeguridad;
    }

    public void setPinSeguridad(String pinSeguridad) {
        this.pinSeguridad = pinSeguridad;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String getBotToken() {
        return "2020219176:AAFcBXEiFmqsjo0Rn4Dopt2P4G4PatcuG1U" ; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void onUpdateReceived(Update arg0) {
       
    }
    @Override
    public String getBotUsername() {
        return "ucb_est_edward_proyecto_bo" ; //To change body of generated methods, choose Tools | Templates.
    }
    
}

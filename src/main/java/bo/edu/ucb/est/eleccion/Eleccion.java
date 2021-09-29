/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.eleccion;

import bo.edu.ucb.est.modelo.Cliente;
import bo.edu.ucb.est.interfaz.Bienvenido;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author HP
 */
public class Eleccion extends TelegramLongPollingBot {
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
    Bienvenido uno=new Bienvenido();
    private Map<String, String> EleccionUsuario= new HashMap();
    Scanner leer =new Scanner(System.in);
    int opcion;

    public void eleccionMenu(Update update,Cliente cli,String Opcion){
        if(isNumeric(update.getMessage().getText())==true && (Integer.parseInt(update.getMessage().getText())>=1 ||Integer.parseInt(update.getMessage().getText())<=5))
        {
            switch(Integer.parseInt(update.getMessage().getText())){
            case 1:
                //cli.MostrarCuentas();
                //Mensaje(update,"Elija una de las cuentas:\n");
                uno.Linea();
                uno.Opcion();
                opcion=leer.nextInt();
                uno.Linea();
                //cli.BuscarCuentas(opcion,1);
                uno.Linea();
                break;
            case 2:
                //cli.MostrarCuentas();
                uno.Linea();
                uno.Opcion();
                opcion=leer.nextInt();
                uno.Linea();
               // cli.BuscarCuentas(opcion,2);
                uno.Linea();
                break;
            case 3: 
               // cli.MostrarCuentas();
                uno.Linea();
                uno.Opcion();
                opcion=leer.nextInt();
                uno.Linea();
                //cli.BuscarCuentas(opcion,3);
                uno.Linea();
                break;
            default:    
            }
        }
    }
    @Override
    public String getBotToken() {
        return "2020219176:AAFcBXEiFmqsjo0Rn4Dopt2P4G4PatcuG1U" ; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void onUpdateReceived(Update update) {
        
    }
    @Override
    public String getBotUsername() {
        return "ucb_est_edward_proyecto_bo" ;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.modelo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author ecampohermoso
 */
public class Cuenta extends TelegramLongPollingBot {
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
    int Codigo;
    private String moneda;
    private String tipo;
    private double saldo;
    public Cuenta(int Codigo,String moneda, String tipo, double saldoInicial) {
        this.Codigo=Codigo;
        this.moneda = moneda;
        this.tipo = tipo;
        this.saldo = saldoInicial;
    }
    public void Mostrar(int i, Update update){
        Mensaje(update, Integer.toString(i) + ". Cuenta Numero: " + Integer.toString(Codigo) + "\n De tipo:  " + tipo + " (" + moneda+ ")");;
    }
    public void MostrarTodo(int i, Update update){
        Mensaje(update, "Información de la cuenta:\n\n"+ "Cuenta "  + Codigo + "\n" + tipo + "\nSaldo: " + saldo + " " + moneda + "\n");
    }
    public boolean retirar(double monto) {
        boolean resultado = false;
        if (monto > 0 && monto <= saldo) { // verifica que no sea negativo, cero o exceda su saldo
            saldo = saldo - monto;
            resultado = true; // si he podido retirar
        }
        return resultado;
    }
    
    public boolean depositar(double monto) {
        boolean resultado = false;
        if (monto > 0 ) { // verifica que no sea negativo, cero o exceda su saldo
            saldo = saldo + monto;
            resultado = true; // si he podido retirar
        }
        return resultado;
    }
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        this.Codigo = codigo;
    }        
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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

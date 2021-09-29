/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.interfaz;



/**
 *
 * @author HP
 */
public class Bienvenido {

    public void Bienvenido(){
        System.out.println("Bienvenido al BANCO BISA\n\nIngrese su codigo de cliente: ");
    }
    public void pin(){
        System.out.println("Ingrese su pin: ");
    }
    public void CredencialesIncorrectas(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\nCredenciales Incorrectas\nIntentelo de nuevo\n");
    }
    public void Linea(){
        System.out.println("**********************");
    }
    public void Menu(){
        System.out.println("Bienvenido al BANCO BISA\n\nElija una opción:\n\n1.Ver Saldo.\n2.Retirar dinero.\n3.Depositar dinero.\n4.Salir.\n");
    }
    public void Opcion(){
        System.out.println("Elija una opción: ");
    }
    public void error(){
        System.out.println("Error");
    }
    public void retirar(){
        System.out.println("Ingrese la cantidad a retirar: ");
    }
    public void depositar(){
        System.out.println("Ingrese la cantidad a depositar: ");
    }
    public void Exito(){
        System.out.println("Operación exitosa");
    }
}

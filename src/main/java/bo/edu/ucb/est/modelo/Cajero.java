/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.modelo;

import bo.edu.ucb.est.eleccion.Eleccion;
import bo.edu.ucb.est.interfaz.Bienvenido;
import bo.edu.ucb.est.modelo.Banco;
import java.util.Scanner;
/**
 *
 * @author ecampohermoso
 */
public class Cajero {
    public Cajero(Banco bisa){
        this.bisa=bisa;
    }
    Bienvenido uno=new Bienvenido();
    Banco bisa = new Banco("BANCO BISA");
    Scanner leer =new Scanner(System.in);
    Eleccion elecci=new Eleccion();
    String codeCli;
    String pin;
    Cliente cli;
    int eleccion;
    public void MostrarBienvenida(){
        do{
        uno.Linea();
        uno.Bienvenido();
        codeCli=leer.next();
        uno.pin();
        pin=leer.next();
        uno.Linea();
        cli=bisa.buscarClientePorCodigo(codeCli,pin);
        }while(cli==null);
        
    }
    public void MenuPrincipal(){
       do{
            uno.Menu();
            uno.Linea();
            uno.Opcion();
            eleccion=leer.nextInt();
            uno.Linea();
            
           //elecci.eleccionMenu(eleccion, cli);
        }while(eleccion!=4);
       System.out.println("Adios...");
    }
}

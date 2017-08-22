/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vbarrera
 */
public class Telnet {

    final String HOST = "10.43.1.90";
    final int PUERTO = 10001;
    Thread hilo;
    BufferedReader entrada;

    public void conectar() {
        try {
            Socket conector = new Socket(HOST, PUERTO);
            System.out.println("Conectado a " + conector.getInetAddress().getHostAddress());
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        String dato;
                        entrada = new BufferedReader(new InputStreamReader(conector.getInputStream()));
                        
                        while ((dato = entrada.readLine()) != null) {//(dato = entrada.readLine()) != null
                            System.err.println("\n" + dato);
                         }
                    } catch (IOException ex) {
                        Logger.getLogger(Telnet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            hilo.start();
        } catch (SocketException ex) {
            System.out.println("4 " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Cummins Desconectado o Apagado");

        } catch (IOException ex) {
            System.out.println("5 " + ex.getMessage());
        }

    }

    public static void main(String[] args) {

        Telnet t = new Telnet();
        t.conectar();
    }
}

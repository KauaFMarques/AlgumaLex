/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algumalex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferre
 */
public class LeitorDeArquivosTexto {
    private InputStream is;
    private int ultimoCaractere = -1; // Para armazenar o último caractere lido
    private boolean podeRetroceder = false; // Para indicar se podemos retroceder

    public LeitorDeArquivosTexto(String arquivo) {
        try {
            is = new FileInputStream(new File(arquivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int lerProximoCaractere() {
        try {
            if (podeRetroceder) {
                podeRetroceder = false;
                return ultimoCaractere; // Retorna o caractere anterior se estiver em modo "retroceder"
            }

            ultimoCaractere = is.read();
            System.out.print((char) ultimoCaractere);  // Apenas para fins de depuração
            return ultimoCaractere; // Retorna o próximo caractere do arquivo
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public void retrocederCaractere() {
        if (ultimoCaractere != -1) {
            podeRetroceder = true; // Ativa o modo "retroceder"
        }
    }
}
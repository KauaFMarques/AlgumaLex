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
    InputStream is;

    public LeitorDeArquivosTexto(String arquivo){
        try {
            is = new FileInputStream(new File(arquivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int lerProximoCaractere(){
        try {
            int ret = is.read();
            System.out.println((char) ret);  // Apenas para fins de depuração
            return ret;  // Aqui faltava o retorno
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}

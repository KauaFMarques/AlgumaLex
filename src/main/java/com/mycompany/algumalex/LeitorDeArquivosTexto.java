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
    private final static int TAMANHO_BUFFER=5;
    int[] bufferDeLeitura;
    int ponteiro;

    public LeitorDeArquivosTexto(String arquivo) {
        try {
            is = new FileInputStream(new File(arquivo));
            inicializarBuffer();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inicializarBuffer(){
        bufferDeLeitura=new int[TAMANHO_BUFFER*2];
        ponteiro=0;
        recarregarBuffer1();
    }
    public void incrementarPonteiro(){
        ponteiro++;
        //logica circular
        if(ponteiro==TAMANHO_BUFFER){
            recarregarBuffer2();
        }else if(ponteiro==TAMANHO_BUFFER*2){
            recarregarBuffer1();
            ponteiro=0;
        }
    }
    
    public void recarregarBuffer1() {
        try {
            for (int i = TAMANHO_BUFFER; i < TAMANHO_BUFFER * 2; i++) {
                bufferDeLeitura[i] = is.read();
                if (bufferDeLeitura[i] == -1) {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recarregarBuffer2() {
        try {
            for (int i = 0; i < TAMANHO_BUFFER; i++) {
                bufferDeLeitura[i] = is.read();
                if (bufferDeLeitura[i] == -1) {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LeitorDeArquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    private int lerCaractereDoBuffer(){
        int ret=bufferDeLeitura[ponteiro];
        incrementarPonteiro();
        return ret;
    }
    
    /*public int lerProximoCaractere() {
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
    }*/
    public int lerProximoCaractere(){
        int c=lerCaractereDoBuffer();
        System.out.print((char)c);
        return c;
    }
    public void retroceder(){
        ponteiro--;
        if(ponteiro<0){
            ponteiro=TAMANHO_BUFFER*2-1;
        }
    }

    public void retrocederCaractere() {
        if (ultimoCaractere != -1) {
            podeRetroceder = true; // Ativa o modo "retroceder"
        }
    }
}
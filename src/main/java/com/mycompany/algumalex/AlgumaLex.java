/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.algumalex;

/**
 *
 * @author ferre
 */
public class AlgumaLex {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.print("C:\\Users\\ferre\\OneDrive\\Área de Trabalho\\codigo.txt");
            return;
        }

        AlgumaLexico lex = new AlgumaLexico(args[0]);
        Token t = null;

        while ((t = lex.proximoToken()) != null) {
            System.out.println(t);
        }
    }
}

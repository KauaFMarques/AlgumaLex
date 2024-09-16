/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algumalex;

/**
 *
 * @author ferre
 */
public class AlgumaLexico {
    LeitorDeArquivosTexto ldat;

    public AlgumaLexico(String arquivo) {
        ldat = new LeitorDeArquivosTexto(arquivo);
    }

    public Token proximoToken() {
        int caractereLido = -1;
        StringBuilder lexema = new StringBuilder();

        while ((caractereLido = ldat.lerProximoCaractere()) != -1) {
            char c = (char) caractereLido;

            if (Character.isWhitespace(c)) {
                // Pular espaços em branco e novas linhas
                if (lexema.length() > 0) {
                    // Criar um token com o lexema atual
                    Token token = criarToken(lexema.toString());
                    lexema.setLength(0); // Limpar o lexema
                    return token;
                }
                continue;
            }

            lexema.append(c);
        }

        // Se houver um lexema não vazio ao final da leitura, criar um token
        if (lexema.length() > 0) {
            return criarToken(lexema.toString());
        }

        return null; // Fim do arquivo
    }

    private Token criarToken(String lexema) {
        // Lógica para criar tokens com base no lexema
        // Exemplo simplificado: Se o lexema é uma palavra-chave, crie um Token do tipo correspondente
        TipoToken tipo = TipoToken.Var; // Substitua isso pela lógica real
        return new Token(tipo, lexema);
    }
}
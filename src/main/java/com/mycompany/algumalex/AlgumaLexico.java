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
            if (c == ' ' || c == '\n')
                continue;

            // Delimitador e Operadores Aritméticos
            if (c == ':') {
                return new Token(TipoToken.Delim, ":");
            } else if (c == '*') {
                return new Token(TipoToken.PCAritMult, "*");
            } else if (c == '+') {
                return new Token(TipoToken.PCAritSom, "+");
            } else if (c == '-') {
                return new Token(TipoToken.PCAritSub, "-");
            } else if (c == '/') {
                return new Token(TipoToken.PCAritDiv, "/");
            }

            // Operadores Relacionais
            else if (c == '>') {
                char next = (char) ldat.lerProximoCaractere();
                if (next == '=') {
                    return new Token(TipoToken.OpRelMaiorIgual, ">=");
                } else {
                    ldat.retrocederCaractere(); // Se não for '=', volta o caractere
                    return new Token(TipoToken.OpRelMaior, ">");
                }
            } else if (c == '<') {
                char next = (char) ldat.lerProximoCaractere();
                if (next == '=') {
                    return new Token(TipoToken.OpRelMenorIgual, "<=");
                } else if (next == '>') {
                    return new Token(TipoToken.OpRelDif, "<>");
                } else {
                    ldat.retroceder();
                    return new Token(TipoToken.OpRelMenor, "<");
                }
            } else if (c == '=') {
                return new Token(TipoToken.OpRelIgual, "=");
            }

            // Caso não seja nenhum símbolo reconhecido, continuar lendo caracteres para formar um lexema
            lexema.append(c);
        }

        // Se houver um lexema não vazio ao final da leitura, criar um token
        if (lexema.length() > 0) {
            return criarToken(lexema.toString());
        }

        return null; // Fim do arquivo
    }
    
    private Token operadorAritmetico(){
        int caractereLido = ldat.lerProximoCaractere();
        char c=(char) caractereLido;
        
        if (c == ':') {
                return new Token(TipoToken.Delim, ":");
            } else if (c == '*') {
                return new Token(TipoToken.PCAritMult, "*");
            } else if (c == '+') {
                return new Token(TipoToken.PCAritSom, "+");
            } else if (c == '-') {
                return new Token(TipoToken.PCAritSub, "-");
            } else if (c == '/') {
                return new Token(TipoToken.PCAritDiv, "/");
            }else{
                return null;
            }
    }
    
    private Token delimitador(){
        int caractereLido = ldat.lerProximoCaractere();
        char c=(char) caractereLido;
        
        if (c == ':'){
            return new Token(TipoToken.Delim,":");
        }else{
            return null;
        }
    }
    
    private Token operadorRelacional() {
        int caractereLido = ldat.lerProximoCaractere();
        char c = (char) caractereLido;

        if (c == '<') {
            char next = (char) ldat.lerProximoCaractere();
            if (next == '=') {
                return new Token(TipoToken.OpRelMenorIgual, "<=");
            } else if (next == '>') {
                return new Token(TipoToken.OpRelDif, "<>");
            } else {
                ldat.retroceder();
                return new Token(TipoToken.OpRelMenor, "<");
            }
        } else if (c == '>') {
            char next = (char) ldat.lerProximoCaractere();
            if (next == '=') {
                return new Token(TipoToken.OpRelMaiorIgual, ">=");
            } else {
                ldat.retroceder();
                return new Token(TipoToken.OpRelMaior, ">");
            }
        } else if (c == '=') {
            return new Token(TipoToken.OpRelIgual, "=");
        } else {
            return null;
        }
    }


    private Token criarToken(String lexema) {
        // Lógica para criar tokens com base no lexema
        // Exemplo: Se o lexema é uma palavra-chave ou identificador, crie um Token do tipo correspondente
        switch (lexema) {
            case "algoritmo":
                return new Token(TipoToken.PCAlgoritmo, lexema);
            case "declaracoes":
                return new Token(TipoToken.PCDeclaracoes, lexema);
            // Adicione mais palavras-chave aqui conforme necessário
            default:
                return new Token(TipoToken.Var, lexema); // Assumindo que seja um identificador (variável)
        }
    }
}
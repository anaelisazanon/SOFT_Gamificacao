package br.udesc.gamificacaosteam.strategy;

public class PontuacaoTeorica implements RegraPontuacao {
    @Override
    public int calcular(double notaIgnorada) {
        return 5; // ler dá 5 pontos fixos
    }
}
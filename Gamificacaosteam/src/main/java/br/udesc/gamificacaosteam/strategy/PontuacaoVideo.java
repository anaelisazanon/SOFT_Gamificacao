package br.udesc.gamificacaosteam.strategy;

public class PontuacaoVideo implements RegraPontuacao {
    @Override
    public int calcular(double duracaoIgnorada) {
        return 10; // 10 pontos fixos
    }
}
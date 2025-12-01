package br.udesc.gamificacaosteam.strategy;

public class PontuacaoPratica implements RegraPontuacao {
    @Override
    public int calcular(double nota) {
        return (int) (nota * 5); // Nota*5
    }
}

package br.udesc.gamificacaosteam.service;

import br.udesc.gamificacaosteam.model.Aluna;
import br.udesc.gamificacaosteam.strategy.RegraPontuacao;
import java.util.HashMap;
import java.util.Map;

public class Gamificacao {

    private Map<String, Aluna> repositorioAlunas = new HashMap<>();

    public void registrarAluna(String id, String nome) {
        repositorioAlunas.put(id, new Aluna(id, nome));
    }

    public void processarEvento(String idAluna, RegraPontuacao regra, double valorBase) {
        Aluna aluna = repositorioAlunas.get(idAluna);
        if (aluna != null) {
            int pontos = regra.calcular(valorBase);
            aluna.adicionarPontos(pontos);
        } else {
            throw new IllegalArgumentException("Aluna não encontrada: " + idAluna);
        }
    }

    public Aluna consultarEstado(String idAluna) {
        return repositorioAlunas.get(idAluna);
    }
}
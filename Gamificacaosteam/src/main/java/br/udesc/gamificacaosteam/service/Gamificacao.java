package br.udesc.gamificacaosteam.service;

import br.udesc.gamificacaosteam.model.Aluna;
import br.udesc.gamificacaosteam.strategy.RegraPontuacao;
import java.util.HashMap;
import java.util.Map;

public class Gamificacao {

    private Map<String, Aluna> repositorioAlunas = new HashMap<>(); //simula banco de dados

    public void registrarAluna(String id, String nome) {
        repositorioAlunas.put(id, new Aluna(id, nome));
    }


    public void processarEvento(String idAluna, RegraPontuacao regra, double valorBase) {
        System.out.println("   ");
        System.out.println("ID Aluna: " + idAluna);

        Aluna aluna = repositorioAlunas.get(idAluna);
        if (aluna != null) {
            System.out.println("Aluna:" + aluna.getNome());


            int pontos = regra.calcular(valorBase);
            System.out.println("   " +regra.getClass().getSimpleName() + " calculou " + pontos + " pontos.");

            aluna.adicionarPontos(pontos);
        } else {
            throw new IllegalArgumentException("Aluna não encontrada: " + idAluna);
        }
    }

    public Aluna consultarEstado(String idAluna) {
        return repositorioAlunas.get(idAluna);
    }
}
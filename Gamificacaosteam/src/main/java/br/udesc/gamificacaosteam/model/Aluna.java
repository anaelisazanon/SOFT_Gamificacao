package br.udesc.gamificacaosteam.model;

public class Aluna {
    private String id;
    private String nome;
    private int pontosAcumulados;
    private String patenteAtual;

    public Aluna(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.pontosAcumulados = 0;
        this.patenteAtual = "Iniciante";
    }

    public void adicionarPontos(int quantidade) {
        this.pontosAcumulados += quantidade;
        atualizarPatente();
    }

    private void atualizarPatente() {
        if (this.pontosAcumulados >= 100) {
            this.patenteAtual = "Cientista Júnior";
        } else if (this.pontosAcumulados >= 50) {
            this.patenteAtual = "Exploradora";
        }
    }

    public int getPontosAcumulados() { return pontosAcumulados; }
    public String getPatenteAtual() { return patenteAtual; }
}
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
        System.out.println("   Pontuação anterior = " + this.pontosAcumulados);
        System.out.println("   Recebe " + quantidade + " pontos de adicionarPontos");
        this.pontosAcumulados += quantidade;
        System.out.println("   Pontuação nova = " + this.pontosAcumulados);
        atualizarPatente();
    }

    private void atualizarPatente() {
        String patenteAntiga = this.patenteAtual;
        if (this.pontosAcumulados >= 100) {
            this.patenteAtual = "Cientista Júnior";
        } else if (this.pontosAcumulados >= 50) {
            this.patenteAtual = "Exploradora";
        }

        if (!patenteAntiga.equals(this.patenteAtual)) {
            System.out.println("   ---------A ALUNA subiu de " + patenteAntiga + " para " + this.patenteAtual + "---------");
            System.out.println("  ");
        }
    }

    public String getNome() {
        return nome;
    }

    public int getPontosAcumulados() {
        return pontosAcumulados;
    }

    public String getPatenteAtual() {
        return patenteAtual;
    }
}
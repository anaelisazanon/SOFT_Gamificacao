package br.udesc.gamificacaosteam.model;

public class Atividade {
    private String id;
    private String titulo;
    private TipoAtividade tipo;

    public Atividade(String id, String titulo, TipoAtividade tipo) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public TipoAtividade getTipo() { return tipo; }
}
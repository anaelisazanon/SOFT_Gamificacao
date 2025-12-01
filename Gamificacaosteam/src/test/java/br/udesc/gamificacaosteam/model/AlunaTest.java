package br.udesc.gamificacaosteam.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunaTest {          //Mudança de patente

    @Test
    void alunaNovaDeveComecarZerada() {
        Aluna aluna = new Aluna("1", "Marie");

        assertEquals(0, aluna.getPontosAcumulados());
        assertEquals("Iniciante", aluna.getPatenteAtual());
    }

    @Test
    void deveSubirDePatenteAutomaticamente() {
        Aluna aluna = new Aluna("1", "Ada");

        // Simula ganho de 50 pontos (Regra: >= 50 vira Exploradora)
        aluna.adicionarPontos(50);
        assertEquals("Exploradora", aluna.getPatenteAtual());

        // + 50 pontos (=100 e vira Cientista Júnior)
        aluna.adicionarPontos(50);
        assertEquals(100, aluna.getPontosAcumulados());
        assertEquals("Cientista Júnior", aluna.getPatenteAtual());
    }

    @Test
    void naoDeveMudarPatenteSePontosInsuficientes() {
        Aluna aluna = new Aluna("1", "Graciane");

        aluna.adicionarPontos(49);
        assertEquals("Iniciante", aluna.getPatenteAtual());
    }
}
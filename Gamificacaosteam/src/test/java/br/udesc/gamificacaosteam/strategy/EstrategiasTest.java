package br.udesc.gamificacaosteam.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstrategiasTest {   //cálculos

    @Test
    void regraPraticaDeveMultiplicarPorCinco() {
        PontuacaoPratica regra = new PontuacaoPratica();

        // nota 8*5 = 40
        assertEquals(40, regra.calcular(8.0));

        // nota 0*5= 0
        assertEquals(0, regra.calcular(0.0));
    }

    @Test
    void regraVideoDeveSerFixa() {
        PontuacaoVideo regra = new PontuacaoVideo();

        // deve dar 10
        assertEquals(10, regra.calcular(100.0));
        assertEquals(10, regra.calcular(0.0));
    }

    @Test
    void regraTeoricaDeveSerFixa() {
        PontuacaoTeorica regra = new PontuacaoTeorica();

        // Leitura dá 5 pontos fixos
        assertEquals(5, regra.calcular(10.0));
    }
}
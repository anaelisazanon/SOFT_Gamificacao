package br.udesc.gamificacaosteam;

import br.udesc.gamificacaosteam.model.Aluna;
import br.udesc.gamificacaosteam.service.Gamificacao;
import br.udesc.gamificacaosteam.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GamificacaoTest {

    private Gamificacao service;
    private final String ID_ALUNA = "A001";

    @BeforeEach
    void setUp() {
        service = new Gamificacao();
        service.registrarAluna(ID_ALUNA, "Marie Curie");
    }

    @Test
    void deveGanhar10PontosAoAssistirVideo() {
        // Arrange & Act
        service.processarEvento(ID_ALUNA, new PontuacaoVideo(), 0);

        // Assert
        Aluna aluna = service.consultarEstado(ID_ALUNA);

        assertEquals(10, aluna.getPontosAcumulados());
    }

    @Test
    void deveMultiplicarPor5EmAtividadePratica() {
        // Aluna tirou 10 na prática. Esperado: 50 pts
        service.processarEvento(ID_ALUNA, new PontuacaoPratica(), 10.0);

        Aluna aluna = service.consultarEstado(ID_ALUNA);
        assertEquals(50, aluna.getPontosAcumulados());
    }

    @Test
    void deveSubirPatenteParaExploradora() {
        // Ganha 50 pontos (Nota 10 na prática)
        service.processarEvento(ID_ALUNA, new PontuacaoPratica(), 10.0);

        Aluna aluna = service.consultarEstado(ID_ALUNA);
        assertEquals("Exploradora", aluna.getPatenteAtual());
    }

    @Test
    void deveLancarErroParaAlunaInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.processarEvento("999", new PontuacaoVideo(), 0);
        });
    }
}
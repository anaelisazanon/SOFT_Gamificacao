/*Start - Cria Katherine - Tenta ID Falso - OK teste
Reset - Cria Katherine - Soma 10 + 5 - OK teste
Reset - Cria Katherine - Ganha 10 - OK teste
Reset - Cria Katherine - Ganha 50 e Upa de Nível - OK teste
*/

package br.udesc.gamificacaosteam.service;

import br.udesc.gamificacaosteam.model.Aluna;
import br.udesc.gamificacaosteam.strategy.PontuacaoPratica;
import br.udesc.gamificacaosteam.strategy.PontuacaoTeorica;
import br.udesc.gamificacaosteam.strategy.PontuacaoVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GamificacaoServiceTest {

    private Gamificacao service;
    private final String ID_ALUNA = "A001";

    @BeforeEach //isolamento dos testes
    void setUp() {
        service = new Gamificacao();
        service.registrarAluna(ID_ALUNA, "Katherine");
    }

    @Test
    void deveProcessarPontosDeVideo() {
        // Vídeo +10 pontos
        service.processarEvento(ID_ALUNA, new PontuacaoVideo(), 0);

        Aluna aluna = service.consultarEstado(ID_ALUNA);
        assertEquals(10, aluna.getPontosAcumulados());
    }

    @Test
    void deveProcessarPontosDeAtividadePratica() {
        // Prática * por 5 (Nota 10 * 5 = 50)
        service.processarEvento(ID_ALUNA, new PontuacaoPratica(), 10.0);

        Aluna aluna = service.consultarEstado(ID_ALUNA);
        assertEquals(50, aluna.getPontosAcumulados());

        assertEquals("Exploradora", aluna.getPatenteAtual()); // Verifica se subiu de nível
    }

    @Test
    void deveAcumularPontosDeVariosEventos() {
        // assistir Vídeo +10
        service.processarEvento(ID_ALUNA, new PontuacaoVideo(), 0);

        // Teórica nota 10 (+5 leitura)
        service.processarEvento(ID_ALUNA, new PontuacaoTeorica(), 10.0);

        // Total esperado= 15
        assertEquals(15, service.consultarEstado(ID_ALUNA).getPontosAcumulados());
    }


    @Test
    void deveLancarErroSeAlunaNaoExistir() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.processarEvento("ERRO PORQUE ID NAO EXISTE", new PontuacaoVideo(), 0);
        });
    }
}
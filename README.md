# Módulo de Gamificação **Trabalho Final de Engenharia de Software**
> Objetivo: engajar alunas em atividades STEM (Ciência, Tecnologia, Engenharia e Matemática) através de gamificação.

## 📋 Sobre o Projeto
Este projeto consiste em um **microsserviço de gamificação** responsável por processar eventos acadêmicos (como assistir vídeos ou entregar atividades) e convertê-los em pontos e conquistas (badges).
O foco do desenvolvimento foi a aplicação de boas práticas de Engenharia de Software, incluindo:
- **Arquitetura em Camadas** (Model, Service, Strategy).
- **Padrão de Projeto Strategy** para regras de pontuação.
- **TDD (Test Driven Development)** para garantia de qualidade.

---
## Como Executar
- Clone o repositório.
- Abra no IntelliJ IDEA.
- Aguarde o Maven baixar as dependências (JUnit 5).
- Navegue até a pasta src/test.
- Clique com o botão direito na pasta java e selecione "Run 'All Tests'".

---
## Arquitetura e Explicação do Código

O código foi organizado para desacoplar a regra de negócio (cálculo) da gestão de estado (entidade). Abaixo, a explicação detalhada de cada pacote:

### 1. Pacote `model` (Domínio)
Contém as entidades que representam o coração do sistema.
- **`Aluna.java`**: Não é uma classe de dados (DTO) que possui **lógica de negócio encapsulada**.
    - O método `atualizarPatente()` é privado e chamado automaticamente sempre que pontos são adicionados. Isso garante a integridade dos dados (uma aluna nunca terá pontuação de "Cientista" com patente de "Iniciante").

### 2. Pacote `strategy` (Padrão de Projeto)
Aqui foi aplicado o **Strategy Pattern** para eliminar condicionais complexas (`if/else`) no cálculo de pontos.
- **`RegraPontuacao` (Interface)**: Define o contrato `calcular(double valor)`. O sistema depende dessa abstração, não das implementações concretas (Inversão de Dependência).
- **`PontuacaoPratica`**: Implementa a regra de multiplicador (Nota * 5).
- **`PontuacaoVideo`**: Implementa a regra de pontos fixos, ignorando o parâmetro de entrada.
- **`PontuacaoTeorica`**: Implementa a regra de leitura simples.

### 3. Pacote `service` (Camada de Serviço)
Atua como a fachada (API) do módulo.
- **`Gamificacao.java`**: Responsável por orquestrar o fluxo.
    - *Método `processarEvento`*: Recebe o ID da aluna e uma **Estratégia** como parâmetro. Ele não sabe como o cálculo é feito, apenas delega para a estratégia injetada. Isso torna o código extensível (Princípio Open/Closed do SOLID).

---
## Testes Automatizados (TDD)

O projeto possui cobertura de testes, divididos por responsabilidade:

### `GamificacaoServiceTest.java` (Teste de Integração)
É o teste principal. Verifica se as peças (Service + Strategy + Model) funcionam juntas.
- Testa se assistir vídeos soma pontos corretamente.
- Testa se o acúmulo de pontos funciona (memória do estado).
- Testa se a patente muda quando a aluna atinge os marcos (ex: "Iniciante" -> "Exploradora").
- Utiliza `@BeforeEach` para garantir o isolamento dos testes (cada teste começa com uma aluna "zerada").

### `AlunaTest.java` (Teste Unitário)
Foca exclusivamente na lógica interna da entidade.
- Verifica se a aluna nova nasce com 0 pontos.
- Testa os limites de borda da mudança de patente (ex: 49 pontos vs 50 pontos).


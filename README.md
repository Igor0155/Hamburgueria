# 🍔 Hamburgueria

Este projeto é uma implementação acadêmica e arquitetural de um sistema para uma Hamburgueria, desenvolvido com o objetivo de aplicar **24 Padrões de Projeto (Design Patterns do GoF)** em um único domínio de negócio.

O sistema simula as operações reais de uma hamburgueria, desde a configuração inicial, montagem do cardápio e atendimento ao cliente, até o processamento de pedidos, cálculo de frete, chat de suporte e fechamento de caixa.

---

## 🛠️ Tecnologias Utilizadas

- **Java** (JDK 11 ou superior)
- **JUnit 5** (Para testes unitários e validação dos padrões)
- **Maven** (Gerenciamento de dependências e build)

---

## 📁 Estrutura do Projeto

A arquitetura foi dividida logicamente para facilitar a compreensão dos padrões aplicados:

```text
src/main/java/hamburgueria/
 ├── ComponenteCardapio.java (Base para Composite e Decorator)
 ├── ComboFacade.java
 ├── (Outras classes estruturais do núcleo do cardápio)
 │
 ├── criacionais/
 │    ├── ConfiguracaoRestaurante.java
 │    ├── PedidoBuilder.java
 │    └── (Classes de Factory, Abstract Factory...)
 │
 ├── estruturais/
 │    └── (Classes do padrão Bridge para entregas)
 │
 └── comportamentais/
      ├── EstadoPedido.java
      ├── EstrategiaFrete.java
      └── (Classes de State, Strategy, Observer, Mediator...)
```

## 🧩 Padrões de Projeto Aplicados

### 1. Padrões de Criação

Gerenciam a forma como os objetos são instanciados no sistema, garantindo flexibilidade e reaproveitamento de código.

- **Abstract Factory**
  - **Onde foi usado:** `EmbalagemFactory` (`FabricaDelivery` e `FabricaSalao`)
  - **Objetivo:** Criar famílias de objetos que dependem entre si. Garante que um pedido para viagem receba uma "Caixa Térmica e Copo Selado", enquanto o salão recebe "Bandeja e Copo de Vidro".
- **Builder**
  - **Onde foi usado:** `PedidoBuilder`
  - **Objetivo:** Construir um objeto complexo (`Pedido`) passo a passo. Ele orquestra os dados do cliente, chama as fábricas de embalagem e pagamento, e adiciona os itens do cardápio de forma fluente.
- **Factory Method**
  - **Onde foi usado:** `PagamentoFactory`
  - **Objetivo:** Ocultar a complexidade de instanciar os diferentes meios de pagamento (`PagamentoPix`, `PagamentoCartao`), delegando a criação para uma fábrica baseada em uma String.
- **Prototype**
  - **Onde foi usado:** `HamburguerPersonalizado`
  - **Objetivo:** Permitir a clonagem de um pedido de hambúrguer complexo, criando cópias exatas em memória. Ideal para quando um cliente quer "um lanche igual ao do amigo, mas mudando apenas o pão", evitando instanciar tudo do zero.
- **Singleton**
  - **Onde foi usado:** `ConfiguracaoRestaurante`
  - **Objetivo:** Garantir que exista apenas uma única instância global no sistema contendo os dados do restaurante (como CNPJ e status Aberto/Fechado).

---

### 2. Padrões Estruturais

Cuidam da composição de classes e objetos, definindo como eles se conectam para formar estruturas maiores.

- **Adapter**
  - **Onde foi usado:** `TransporteTerceirizadoAdapter` e `LogisticaTerceirizadaAPI`
  - **Objetivo:** Permitir que o sistema utilize uma API externa de entregas cujos métodos e parâmetros são incompatíveis com a nossa interface interna (`Transporte`). O adaptador "traduz" as chamadas do nosso sistema, formatando os dados e acionando o método correto da biblioteca de terceiros.
- **Bridge**
  - **Onde foi usado:** `PedidoDelivery` (Abstração) e `Transporte` (Implementação)
  - **Objetivo:** Desacoplar a origem do pedido (ex: `PedidoApp`) da sua forma de transporte (`TransporteMoto`, `TransporteBicicleta`), permitindo que ambas as hierarquias evoluam de forma independente.
- **Composite**
  - **Onde foi usado:** `ComponenteCardapio`, `Categoria` e `Produto`
  - **Objetivo:** Permitir a criação de um menu em forma de árvore. Uma "Categoria" (Combo) pode conter "Produtos" individuais (Folhas) ou até mesmo outras subcategorias, e ambos são tratados da mesma forma pela interface base.
- **Decorator**
  - **Onde foi usado:** `HamburguerDecorator` (`AdicionalCheddar`, `AdicionalBacon`, etc.)
  - **Objetivo:** Adicionar responsabilidades e preços ao `HamburguerBase` de forma dinâmica (em tempo de execução), sem a necessidade de criar uma classe para cada combinação possível de lanche.
- **Facade (Fachada)**
  - **Onde foi usado:** `ComboFacade`
  - **Objetivo:** Fornecer uma interface simplificada (um único método) para o cliente. A fachada esconde a complexidade de instanciar os decoradores do hambúrguer e agrupá-los aos produtos da batata usando o Composite.
- **Flyweight**
  - **Onde foi usado:** `TipoIngrediente` e `IngredienteFactory`
  - **Objetivo:** Otimizar o uso de memória da aplicação. Informações repetitivas e pesadas dos ingredientes (como nome, fornecedor e tabela nutricional) são instanciadas uma única vez e compartilhadas, enquanto apenas os dados variáveis (como a quantidade em gramas daquele pedido específico) ficam nos objetos da ponta.
- **Proxy**
  - **Onde foi usado:** `RelatorioFinanceiroProxy` e `RelatorioFinanceiroReal`.
  - **Objetivo:** Atuar como um substituto com controle de acesso (Protection Proxy) para o relatório de faturamento da hamburgueria. O Proxy verifica se o funcionário logado possui o cargo de "Gerente" ou "Dono" antes de instanciar o relatório real e expor os dados financeiros, barrando o acesso de funcionários não autorizados.
---

### 3. Padrões Comportamentais

Definem como os objetos se comunicam, distribuem responsabilidades e controlam o fluxo de execução.

- **Chain of Responsibility**
  - **Onde foi usado:** `TratadorReclamacao` (`Atendente`, `Gerente`, `Dono`)
  - **Objetivo:** Passar uma solicitação de desconto por uma corrente de avaliadores. Se o cargo atual não tiver limite de alçada para aprovar o valor da reclamação, ele repassa automaticamente para o seu superior.
- **Command**
  - **Onde foi usado:** `ComandoPrepararLanche`, `TabletAtendente` (Invoker) e `Cozinha` (Receiver).
  - **Objetivo:** Encapsular a solicitação de preparo de um lanche como um objeto autônomo. Isso permitiu criar um histórico de comandos no tablet do atendente, viabilizando a implementação da operação de "Desfazer" (cancelar o preparo na cozinha) de forma totalmente desacoplada.
- **Interpreter**
  - **Onde foi usado:** `ExpressaoPromocao` (Interface), Expressões Terminais (`ExpressaoEstudante`, `ExpressaoDiaSemana`) e Não-Terminais (`ExpressaoAnd`, `ExpressaoOr`).
  - **Objetivo:** Avaliar e interpretar regras dinâmicas de cupons de desconto. O padrão permite montar árvores de regras booleanas (Ex: "Conceder desconto SE Cliente é VIP OU (Cliente é Estudante E Hoje é Terça-feira)"), cruzando essas regras lógicas com o contexto real do pedido.
- **Iterator**
  - **Onde foi usado:** `FilaPedidosCozinha` e `PedidoIterator`
  - **Objetivo:** Prover uma forma padronizada de percorrer sequencialmente os pedidos que estão aguardando preparo na cozinha, abstraindo a forma como essa lista é armazenada (Array, List, etc.) e garantindo segurança no acesso aos dados.
- **Mediator**
  - **Onde foi usado:** `ChatMediator` (`CentralChat`) e `UsuarioChat`
  - **Objetivo:** Centralizar a comunicação do chat do aplicativo. O Cliente e o Entregador não interagem diretamente um com o outro, mas sim através da Central de Chat, reduzindo o acoplamento.
- **Memento**
  - **Onde foi usado:** `CarrinhoCompras` (Originator) e `CarrinhoEstado` (Memento)
  - **Objetivo:** Capturar e armazenar o estado interno do carrinho de compras do cliente, permitindo a funcionalidade de "Desfazer" para restaurar o carrinho ao estado anterior.
- **Observer**
  - **Onde foi usado:** `MonitorObserver` (`PainelCliente`) e `PedidoFluxo`
  - **Objetivo:** Estabelecer uma relação de assinatura. Sempre que o pedido muda de Estado (State), ele notifica automaticamente o Painel do Cliente para atualizar a exibição.
- **State**
  - **Onde foi usado:** `EstadoPedido` (`Novo`, `Preparando`, `Pronto`, `Entregue`)
  - **Objetivo:** Alterar o comportamento do pedido conforme seu status de preparo na cozinha avança. O padrão impede transições ilegais (como tentar entregar um pedido que ainda é novo).
- **Strategy**
  - **Onde foi usado:** `EstrategiaFrete` (`FreteFixo`, `FretePorKm`, `FreteGratis`)
  - **Objetivo:** Permitir que a regra de cálculo do frete seja alterada em tempo de execução, dependendo da escolha do cliente ou de campanhas promocionais, sem o uso de estruturas condicionais (`if/else`) complexas.
- **Template Method**
  - **Onde foi usado:** `ProcessamentoCaixa` (`CaixaFisico`, `CaixaTotem`)
  - **Objetivo:** Definir o esqueleto do algoritmo de fechamento de caixa, forçando a ordem das operações (Contar valores -> Fechar no sistema -> Emitir comprovante), mas deixando as classes filhas implementarem os detalhes de como contar e emitir.
- **Visitor**
  - **Onde foi usado:** `VisitorNutricional` (`CalculadoraCalorias`) e `Ingrediente`
  - **Objetivo:** Separar o algoritmo de contagem de calorias das classes de ingredientes (`Pao`, `Carne`). Permite adicionar novas operações sobre esses objetos sem ter que modificá-los estruturalmente.

---

## 🚀 Como Executar os Testes

O projeto conta com classes de testes unitários que garantem o funcionamento individual e a integração de todos os padrões.

No terminal, na raiz do projeto (onde se encontra o arquivo `pom.xml`), execute:

```bash
mvn test
```

---

## 📈 Diagrama

<img width="7745" height="8588" alt="mermaid-diagram-2026-06-16-195510" src="https://github.com/user-attachments/assets/b6bd61bd-d1f5-4497-8f34-4f9beba41fbc" />


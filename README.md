# Desenvolvimento de Jogos 🎮

Este repositório contém os projetos desenvolvidos na disciplina de **Desenvolvimento de Jogos**, utilizando a framework **libGDX**. Os dois jogos implementados são:

- **Bow and Arrow**: Jogo simples de arco e flecha com mira e disparo.
- **Wall World**: Jogo de mineração inspirado no *Wall World* da Alawar, com elementos de exploração e coleta de recursos.

## Tecnologias utilizadas

- [LibGDX](https://libgdx.com/) - Framework para desenvolvimento multiplataforma de jogos em Java.
- Java 8+
- Gradle (para gerenciamento do projeto)
- IntelliJ IDEA - Ambiente de desenvolvimento

---

## 🎯 Bow and Arrow

Jogo 2D onde o jogador controla um arqueiro que deve estourar balões com flechas.

### Funcionalidades

- Sistema de movimento do arqueiro (cima/baixo)
- Mira com o mouse
- Detecção de colisão
- Animações básicas
- Spawn aleatório de balões
- Sistema de reciclagem de objetos (pooling)

### Controles

- W ou seta para cima: Mover para cima
- S ou seta para baixo: Mover para baixo
- ENTER: Atirar flecha

### Configuração no IntelliJ IDEA

- Importe o projeto como projeto Gradle
- Configure o JDK 8+
- Execute a task desktop:run ou a classe DesktopLauncher

---

## ⛏️ Wall World

Jogo de mineração com nave, inspirado em *Wall World*, onde o jogador explora cavernas em paredes e coleta recursos.

### Funcionalidades

- Movimentação da nave
- Sistema de mineração (destruição de blocos) com laser
- Coleta de recursos
- Spawn de inimigos
- Disparo de laser
- Dois mapas distintos
- Sistema de partículas para efeitos visuais
- Música e efeitos sonoros

### Controles

- `W` / `S` / `D` / `A`: Mover nave
- Botão esquerdo do mouse: Disparar laser
- N: Trocar de mapa

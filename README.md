## Descrição do Projeto

Este projeto em Java renderiza cubos rotacionando em um terminal utilizando caracteres ASCII. A implementação está organizada em três classes principais:
- `Main` :  Contém o loop principal que gerencia a animação contínua dos cubos. A classe também configura o buffer de caracteres e o buffer de profundidade (zBuffer) para criar a ilusão de profundidade.
- `CubeRenderer`: Esta classe é responsável pela renderização das superfícies dos cubos. Ela realiza cálculos tridimensionais utilizando transformações de rotação, aplicando funções trigonométricas (seno e cosseno) para simular a rotação dos cubos em torno dos eixos X, Y e Z.
- `Utilities`: Inclui funções auxiliares, como a função de pausa entre cada frame para controlar a taxa de atualização da animação.

## Detalhes dos Cálculos Trigonométricos
Para rotacionar os cubos no espaço tridimensional, o projeto utiliza as funções trigonométricas `sin` (seno) e `cos` (cosseno). A ideia central é transformar as coordenadas dos vértices dos cubos com base nos ângulos de rotação em torno dos três eixos:
- Rotação em torno do eixo X: Afeta as coordenadas Y e Z.
- Rotação em torno do eixo Y: Afeta as coordenadas X e Z.
- Rotação em torno do eixo Z: Afeta as coordenadas X e Y.

Por exemplo, para calcular a nova posição de um ponto `(i, j, k)` após a rotação, o projeto utiliza as seguintes equações:

- `X' = j * sin(A) * sin(B) * cos(C) - k * cos(A) * sin(B) * cos(C) + j * cos(A) * sin(C) + k * sin(A) * sin(C) + i * cos(B) * cos(C)`
- `Y' = j * cos(A) * cos(C) + k * sin(A) * cos(C) - j * sin(A) * sin(B) * sin(C) + k * cos(A) * sin(B) * sin(C) - i * cos(B) * sin(C)`
- `Z' = k * cos(A) * cos(B) - j * sin(A) * cos(B) + i * sin(B)`

Essas transformações são aplicadas a cada vértice dos cubos para calcular suas novas posições no espaço 3D, que então são projetadas na tela 2D (o terminal).

## Compilação e Execução
Para compilar e executar o projeto, utilize os seguintes comandos:

``` Java
 javac Main.java CubeRenderer.java Utilities.java
 java Main

```

## Considerações Finais
Este projeto é uma demonstração de conceitos básicos de gráficos 3D aplicados em um ambiente de texto, utilizando trigonometria para simular a rotação de objetos tridimensionais. Embora simples, ele ilustra como manipulações matemáticas podem criar uma visualização dinâmica no terminal.

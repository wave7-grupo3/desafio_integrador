# :ballot_box_with_check: Final Bootcamp Challenge

<p align="right">
  <img src="https://img.shields.io/badge/Project completed-black?style=for-the-badge">
</p>

<p align="left">
  <img src="https://img.shields.io/badge/Version: English-black?style=for-the-badge">
</p>

- TO DO


## Authors
- GitHub - [Amanda Zotelli](https://github.com/amdzotelli)
- GitHub - [Carolina Hakamada](https://github.com/carolhakamada)
- GitHub - [Gabriel Morais](https://github.com/gabrielmorais-meli)
- GitHub - [Ingrid Paulino](https://github.com/IngridPaulino)
- GitHub - [Mariana Saraiva](https://github.com/marianasaraivameli)
- GitHub - [Rosália Padoin](https://github.com/rosalia-oliveira)


</br>
<p align="left">
  <img src="https://img.shields.io/badge/Versão: Português-black?style=for-the-badge">
</p>

## API desafio_integrador
Desenvolvimento de uma API REST para um Marketplace que já vende produtos alimentícios, mas quer poder vender produtos que precisam de refrigeração; chamados produtos frescos. Implica novos desafios na forma de armazenar,
transportar e comercializar os produtos, uma vez que é feito de uma forma totalmente diferente.

A API foi desenvolvida para:

- Ser capaz de inserir um lote de produtos no armazém de distribuição para registrar essa existência no estoque.

- Ter as informações necessárias para entender em que setor deve ser armazenada a mercadoria para que fique em bom estado enquanto estiver no almoxarifado e para que se possa mostrar ao colaborador que vai procurar o produto (picking) onde está.

- Ser capaz de detectar se há produtos que estão prestes a expirar para tomar alguma medida a esse respeito (poder devolvê-los ao Vendedor, jogá-los fora ou realizar alguma ação comercial específica para liquidá-los).

- Para poder consultar o estoque, listar quais produtos estão em qual armazém e dado um produto específico, entender também em qual armazém ele está armazenado.

- Poder cadastrar o pedido de compra para que os colaboradores dentro do Fullfilment
possam montar o (s) pedido (s) para despachá-los.



### Habilidades

Implementa uma API REST no âmbito do slogan e aplicar os conteúdos trabalhados durante o BOOTCAMP MELI (Git, Java, Spring, Banco de Dados,
Qualidade e Segurança), que podem ser usados a partir de uma proposta, especificação de requisitos e documentação anexa.

MSC
- Estruturar a API conforme as boas práticas seguindo a arquitetura MSC e alguns princípios do SOLID:
    - Entity: Reponsável por modelar as entidades.
    - Repository: É a camada de comunicação com o banco de dados.
    - Service: Camada onde gerenciamos as regras de négocio.
    - Controller: Responsável por receber as requisições e enviar as respostas.
    - Advisor: Responsável por gerenciar as exceções da aplicação.

Tecnologias
- O desafio foi realizado utilizando Java 11 com Spring Boot, suas anotações de validação somando as dependências:
    - Spring Boot DevTools.
    - Spring Boot Web.
    - Spring Boot Validation.
    - Lombok.
    - JUnit.
    - Mockito.


_a fazer a partir daqui_
### O que foi desenvolvido
_Foi desenvolvida a rota `/property` para criar e listar todas as propriedades da plataforma de Imóveis conforme a preferência do cliente.
Ainda no escopo das propriedades, foi implementada a rota `/room/{id}`, responsável por buscar uma determinada propriedade e retornar o maior cômodo existente na mesma._


_As rotas utilizadas foram idealizadas para deixar a aplicação mais dinâmica e funcional para o público alvo._

### Validações

Entre as anotações de __validação__ do Spring Boot, que foram utilizadas na camada de Model de cada entidade, estão:

- __NotBlank__, para validar que um campo de String não pode estar vazio;


- __NotNull__, para validar um campo numérico que não pode estar nulo;


- __Size__, para determinar o tamanho mínimo ou máximo de um Objeto ou String;



### Testes

Os testes de unidades foram feitos utilizando o JUnit para as assertions e Mockito para fazer os Mocks.

A camada de Service foi testada, com cobertura de 100% de linhas, verificando os métodos de criação de propriedade, de retorno da lista de propriedades e de pesquisa do maior cômodo de uma propriedade.



### Documentação da API
A ferramenta escolhida para documentar a API foi o Postman e JavaDoc para o projeto.

:warning: Acesse a documentação do Postman [aqui](https://documenter.getpostman.com/view/23653402/2s847ESZzK).


## Como acessar o projeto
:warning: Acessar o repositório do desafio [aqui](https://github.com/wave7-grupo3/desafio_testing)

Abrir o terminal e digitar as informações na sequência:
- Realizar o clone do projeto:
    - SSH: `git clone git@github.com:wave7-grupo3/desafio_testing.git`
    - HTTPS: `git clone https://github.com/wave7-grupo3/desafio_testing.git`

- Ter o ambiente configurado para vizualização de projetos java.
- Gerar a documentação do JavaDocs:
    - Na barra superior da IDE IntelliJ, selecionar a aba `Tools`;
    - Em seguida, selecionar `generate JavaDoc`;


## Autores
- GitHub - [Amanda Zotelli](https://github.com/amdzotelli)
- GitHub - [Carolina Hakamada](https://github.com/carolhakamada)
- GitHub - [Gabriel Morais](https://github.com/gabrielmorais-meli)
- GitHub - [Ingrid Paulino](https://github.com/IngridPaulino)
- GitHub - [Mariana Saraiva](https://github.com/marianasaraivameli)
- GitHub - [Rosália Padoin](https://github.com/rosalia-oliveira)



# :ballot_box_with_check: Final Bootcamp Challenge

<p align="right">
  <img src="https://img.shields.io/badge/Project completed-black?style=for-the-badge" alt="badge complete project">
</p>

<p align="left">
  <img src="https://img.shields.io/badge/Version: English-black?style=for-the-badge" alt="badge version english">
</p>

## Integrator Challenge API
Development of a REST API for a Marketplace that already sells food products, but wants to be able to sell products that need refrigeration; called fresh products. It implies new challenges in how to store
transport and market the products, since it is done in a totally different way.

The API was developed to:

- Be able to enter a batch of products in the distribution warehouse to record that existence in the stock.

- Have the necessary information to understand in which sector the goods should be stored so that they are in good condition while in the warehouse and to be able to show the employee who is going to look for the product (picking) where it is.

- To be able to detect if there are products that are about to expire in order to take some action about it (to be able to return them to the Salesman, throw them away or perform some specific commercial action to liquidate them).

- To be able to query the stock, list which products are in which warehouse, and given a specific product, also understand in which warehouse it is stored.

- To be able to register the purchase order so that the collaborators within Fullfilment
  can assemble the order(s) to ship them.



### Skills

Implement a REST API within the framework of the slogan and apply the content worked on during BOOTCAMP MELI (Git, Java, Spring, Testing, Database,
Quality and Security), which can be used from a proposal, requirements specification and attached documentation.

MSC
- Structure the API according to good practices following the MSC architecture and some SOLID principles:
  - Entities: Responsible for modeling the entities.
  - Repository: This is the communication layer with the database.
  - Service: Layer where we manage the business rules.
  - Controller: Responsible for receiving the requests and sending the answers.
  - Advisor: Responsible for managing the application's exceptions.
  - DTO: Responsible for transporting data between different components of a system.

Technologies
- The challenge was performed using Java 11 with Spring Boot, its validation annotations adding dependencies:
  - Spring Boot DevTools.
  - Spring Boot Web.
  - Spring Boot Validation.
  - Lombok.
  - JUnit.
  - Mockito.


### What was developed
The routes implemented to contemplate these issues were:

- User stories are told from the **warehouse representative's** point of view based on their needs. Services are exposed from the **warehouse fullfilment**.
  - `/api/v1/fresh-products/inboundorder`: responsible for inserting the batch into the service warehouse to record the existence of stock.

- User stories are narrated from the point of view of the **buyer** based on
  their needs. The services are displayed from the **Marketplace** to be
  consumed by the buyer who requests them.
  - `/api/v1/fresh-products`: responsible for allowing the buyer to add the product to the shopping cart. Also in this route, we can query a product in stock in the warehouse to know its location in a sector and the different lots it is in.

- User stories are told from the **warehouse representative's** point of view based on their needs. Services are displayed from the **warehouse of care**.
  - `/api/v1/fresh-products/warehouse`: responsible for checking the location of a product in all warehouses to know the stock in each warehouse of that product.

- User stories are told from the point of view of the **warehouse representative** based on their needs. The services are exposed from the **warehouse of care**.
  - The user stories are told from the point of view of the **warehouse representative** based on his needs.

The routes used were designed to make the application more dynamic and functional for the target audience.

### Validations

Among Spring Boot's __validation__ annotations, which were used in the `Entities` layer of each entity, are:

- __NotBlank__, to validate that a String field cannot be empty;

- __NotNull__, to validate that a numeric field cannot be null;

- __Size__, to determine the minimum or maximum size of an Object or String;

- __DecimalMin__, to determine the minimum size of a number whose value is greater than or equal to that specified;

- __Email__, to determine if the email format is valid;


### Testing

Unit and integration testing was done using JUnit for the assertions and Mockito to do the Mocks.

The layers were tested, with at least 80% coverage of methods and lines, checking the consistency of the methods used for the development of the project.


### API Documentation
The tool chosen to document the API was Postman and JavaDoc for the project.

:warning: Access the Postman documentation [here](https://documenter.getpostman.com/view/23627905/2s8YmUKzJM).

### Requirement 6

#### Amanda Zotelli
Enter the individual information
:warning: Go to Postman's Individual [here]().

#### Carolina Hakamada
Enter your individual information
:warning: Go to Postman's Private [here]().

#### Gabriel Morais
Enter your individual information
:warning: Go to Postman's Private [here]().

#### Ingrid Paulino
Enter your individual information
:warning: Go to Postman's Private [here]().

#### Mariana Saraiva
Enter your individual info
:warning: Go to Postman's Private [here]().

#### Ros??lia Padoin
Enter your individual information
:warning: Go to Postman's Individual [here]().


## How to access the project
:warning: Access the challenge repository [here](https://github.com/wave7-grupo3/desafio_integrador)

Open the terminal and type the information in the sequence:
- Perform the project clone:
  - SSH: `git clone git@github.com:wave7-grupo3/desafio_integrador.git`
  - HTTPS: `git clone https://github.com/wave7-grupo3/desafio_integrador.git`

- Have the environment configured to view java projects.
- Generate JavaDocs documentation:
  - On the top bar of the IntelliJ IDE, select the `Tools` tab;
  - Then select `generate JavaDoc`;

Translated with www.DeepL.com/Translator (free version)


## Authors
- GitHub - [Amanda Zotelli](https://github.com/amdzotelli)
- GitHub - [Carolina Hakamada](https://github.com/carolhakamada)
- GitHub - [Gabriel Morais](https://github.com/gabrielmorais-meli)
- GitHub - [Ingrid Paulino](https://github.com/IngridPaulino)
- GitHub - [Mariana Saraiva](https://github.com/marianasaraivameli)
- GitHub - [Ros??lia Padoin](https://github.com/rosalia-oliveira)


</br>

<p align="left">
  <img src="https://img.shields.io/badge/Vers??o: Portugu??s-black?style=for-the-badge" alt="badge vers??o em portugu??s">
</p>

## API desafio_integrador
Desenvolvimento de uma API REST para um Marketplace que j?? vende produtos aliment??cios, mas quer poder vender produtos que precisam de refrigera????o; chamados produtos frescos. Implica novos desafios na forma de armazenar,
transportar e comercializar os produtos, uma vez que ?? feito de uma forma totalmente diferente.

A API foi desenvolvida para:

- Ser capaz de inserir um lote de produtos no armaz??m de distribui????o para registrar essa exist??ncia no estoque.

- Ter as informa????es necess??rias para entender em que setor deve ser armazenada a mercadoria para que fique em bom estado enquanto estiver no almoxarifado e para que se possa mostrar ao colaborador que vai procurar o produto (picking) onde est??.

- Ser capaz de detectar se h?? produtos que est??o prestes a expirar para tomar alguma medida a esse respeito (poder devolv??-los ao Vendedor, jog??-los fora ou realizar alguma a????o comercial espec??fica para liquid??-los).

- Para poder consultar o estoque, listar quais produtos est??o em qual armaz??m e dado um produto espec??fico, entender tamb??m em qual armaz??m ele est?? armazenado.

- Poder cadastrar o pedido de compra para que os colaboradores dentro do Fullfilment
possam montar o (s) pedido (s) para despach??-los.



### Habilidades

Implementa uma API REST no ??mbito do slogan e aplicar os conte??dos trabalhados durante o BOOTCAMP MELI (Git, Java, Spring, Testes, Banco de Dados,
Qualidade e Seguran??a), que podem ser usados a partir de uma proposta, especifica????o de requisitos e documenta????o anexa.

MSC
- Estruturar a API conforme as boas pr??ticas seguindo a arquitetura MSC e alguns princ??pios do SOLID:
    - Entities: Repons??vel por modelar as entidades.
    - Repository: ?? a camada de comunica????o com o banco de dados.
    - Service: Camada onde gerenciamos as regras de n??gocio.
    - Controller: Respons??vel por receber as requisi????es e enviar as respostas.
    - Advisor: Respons??vel por gerenciar as exce????es da aplica????o.
    - DTO: Respons??vel pelo transporte de dados entre diferentes componentes de um sistema.

Tecnologias
- O desafio foi realizado utilizando Java 11 com Spring Boot, suas anota????es de valida????o somando as depend??ncias:
    - Spring Boot DevTools.
    - Spring Boot Web.
    - Spring Boot Validation.
    - Lombok.
    - JUnit.
    - Mockito.


### O que foi desenvolvido 
As rotas implementadas para contemplar estas quest??es foram:

- As hist??rias do usu??rio s??o contadas do ponto de vista do **representante do armaz??m** com base em suas necessidades. Os servi??os s??o expostos a partir do **armaz??m de fullfilment**. 
  - `/api/v1/fresh-products/inboundorder`: respons??vel por inserir o lote no armaz??m do atendimento, para registrar a exist??ncia de estoque.

- As hist??rias de usu??rios s??o narradas do ponto de vista do **comprador** com base em
  suas necessidades. Os servi??os s??o expostos a partir do **Marketplace** para serem
  consumidos pelo comprador que os solicita.
  - `/api/v1/fresh-products`: respons??vel por permitir ao comprador que adicione o produto ao carrinho de compras. Ainda nesta rota, podemos consultar um produto em estoque no armaz??m para saber a sua localiza????o em um setor e os diferentes lotes onde se encontra.

- As hist??rias do usu??rio s??o contadas do ponto de vista do **representante do warehouse** com base em suas necessidades. Os servi??os s??o expostos a partir do **armaz??m de atendimento**.
  - `/api/v1/fresh-products/warehouse`: respons??vel por verificar a localiza????o de um produto em todos os armaz??ns para saber o estoque em cada armaz??m do referido produto.

- As hist??rias do usu??rio s??o contadas do ponto de vista do **representante do warehouse** com base em suas necessidades. Os servi??os s??o expostos a partir do **armaz??m de atendimento**.
  - `/api/v1/fresh-products/due-date/`: responss??vel por consultar os produtos em estoque que est??o pr??ximos a data de vencimento no almoxarifado, a fim de aplicar alguma a????o comercial com eles.

_As rotas utilizadas foram idealizadas para deixar a aplica????o mais din??mica e funcional para o p??blico alvo._

### Valida????es

Entre as anota????es de __valida????o__ do Spring Boot, que foram utilizadas na camada `Entities` de cada entidade, est??o:

- __NotBlank__, para validar que um campo de String n??o pode estar vazio;

- __NotNull__, para validar um campo num??rico que n??o pode estar nulo;

- __Size__, para determinar o tamanho m??nimo ou m??ximo de um Objeto ou String;

- __DecimalMin__, para determinar o tamanho m??nimo de um n??mero cujo valor seja maior ou igual ao especificado;

- __Email__, para determinar se o formato do e-mail ?? v??lido;


### Testes

Os testes de unidades e de integra????o foram feitos utilizando o JUnit para as assertions e Mockito para fazer os Mocks.

As camadas foram testadas, com cobertura de min??ma de 80% de m??todos e linhas, verificando a consist??ncia dos m??todos utilizados para o desenvolvimento do projeto.



### Documenta????o da API
A ferramenta escolhida para documentar a API foi o Postman e JavaDoc para o projeto.

:warning: Acesse a documenta????o do Postman [aqui](https://documenter.getpostman.com/view/23627905/2s8YmUKzJM).

### Requisito 6

#### Amanda Zotelli[]
Inserir as informa????es individuais
:warning: Acesse a Individual do Postman [aqui]().

#### Carolina Hakamada
Inserir as informa????es individuais
:warning: Acesse a Individual do Postman [aqui]().

#### Gabriel Morais
Inserir as informa????es individuais
:warning: Acesse a Individual do Postman [aqui]().

#### Ingrid Paulino
Inserir as informa????es individuais
:warning: Acesse a Individual do Postman [aqui]().

#### Mariana Saraiva
Inserir as informa????es individuais
:warning: Acesse a Individual do Postman [aqui]().

#### Ros??lia Padoin
Inserir as informa????es individuais
:warning: Acesse a Individual do Postman [aqui]().


## Como acessar o projeto
:warning: Acessar o reposit??rio do desafio [aqui](https://github.com/wave7-grupo3/desafio_integrador)

Abrir o terminal e digitar as informa????es na sequ??ncia:
- Realizar o clone do projeto:
    - SSH: `git clone git@github.com:wave7-grupo3/desafio_integrador.git`
    - HTTPS: `git clone https://github.com/wave7-grupo3/desafio_integrador.git`

- Ter o ambiente configurado para vizualiza????o de projetos java.
- Gerar a documenta????o do JavaDocs:
    - Na barra superior da IDE IntelliJ, selecionar a aba `Tools`;
    - Em seguida, selecionar `generate JavaDoc`;


## Autores
- GitHub - [Amanda Zotelli](https://github.com/amdzotelli)
- GitHub - [Carolina Hakamada](https://github.com/carolhakamada)
- GitHub - [Gabriel Morais](https://github.com/gabrielmorais-meli)
- GitHub - [Ingrid Paulino](https://github.com/IngridPaulino)
- GitHub - [Mariana Saraiva](https://github.com/marianasaraivameli)
- GitHub - [Ros??lia Padoin](https://github.com/rosalia-oliveira)



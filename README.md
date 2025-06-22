# SampaStore - E-commerce com Spring Boot e Java

Este projeto é uma aplicação de e-commerce completa, desenvolvida com Spring Boot e Java, focada em fornecer uma experiência de compra online eficiente e segura. A SampaStore oferece funcionalidades tanto para clientes (visualização e compra de produtos, gerenciamento de pedidos e conta) quanto para administradores e estoquistas (gerenciamento de usuários, produtos e pedidos).

## Conceito

A SampaStore tem como objetivo simular um ambiente de e-commerce real, permitindo a gestão de produtos, o processamento de pedidos e a interação de diferentes perfis de usuário (administrador, estoquista e cliente final). O foco principal é a construção de uma aplicação robusta, escalável e segura, utilizando as melhores práticas do desenvolvimento moderno com Spring Boot.

## Tecnologias Utilizadas

O projeto SampaStore foi construído com as seguintes tecnologias e ferramentas:

* **Linguagem de Programação:** Java 21
* **Framework:** Spring Boot 3.4.3
* **Controle de Dependências:** Maven
* **Banco de Dados:** H2 Database (em memória para testes e baseado em arquivo para desenvolvimento)
* **ORM (Object-Relational Mapping):** Spring Data JPA com Hibernate
* **Segurança:** Spring Security (autenticação baseada em formulário para diferentes papéis de usuário, como `ADMIN`, `ESTOQUISTA` e `CLIENTE`).
* **Templating Engine:** Thymeleaf
* **Validação de Dados:** Jakarta Bean Validation
* **Utilitários:** Lombok (para reduzir boilerplate code)
* **Testes:** JUnit 5, Mockito
* **Gerenciamento de Imagens:** Armazenamento de arquivos no sistema de arquivos local com paths relativos salvos no banco.
* **APIs Externas:** ViaCEP (para busca de endereços e cálculo de frete)

## Funcionalidades

### Para Administradores (ROLE_ADMIN)

* **Gerenciamento de Usuários:**
    * Listar todos os usuários cadastrados.
    * Pesquisar usuários por nome de usuário.
    * Visualizar e editar detalhes de usuários (nome, CPF, e-mail, grupo).
    * Alterar o status de usuários (ativo/inativo).
    * Cadastrar novos usuários (incluindo outros administradores e estoquistas).
* **Gerenciamento de Produtos:**
    * Listar, cadastrar, editar e remover produtos.
    * Gerenciar imagens de produtos (adicionar, remover, definir como principal, reordenar).
    * Inativar/Reativar produtos.
* **Gerenciamento de Pedidos:**
    * Visualizar todos os pedidos com seus detalhes.
    * Atualizar o status de um pedido (ex: de `AGUARDANDO_PAGAMENTO` para `EM_TRANSITO`).

### Para Estoquistas (ROLE_ESTOQUISTA)

* **Gerenciamento de Produtos:**
    * Listar e editar produtos.
    * Atualizar a quantidade em estoque dos produtos.
    * Não pode alterar nome, preço, descrição, avaliação ou imagens dos produtos.
* **Gerenciamento de Pedidos:**
    * Visualizar todos os pedidos com seus detalhes.
    * Atualizar o status de um pedido.

### Para Clientes (ROLE_CLIENTE)

* **Loja Virtual:**
    * Visualizar produtos ativos disponíveis para compra.
    * Filtrar produtos por nome.
    * Ver detalhes completos do produto, incluindo múltiplas imagens e avaliação.
* **Carrinho de Compras:**
    * Adicionar produtos ao carrinho.
    * Atualizar a quantidade de itens no carrinho.
    * Remover itens do carrinho.
    * Calcular o frete com base no CEP.
    * Visualizar o total do pedido (produtos + frete).
* **Finalização de Compra (Checkout):**
    * Selecionar endereço de entrega (pode adicionar novos endereços).
    * Escolher forma de pagamento (boleto ou cartão de crédito).
    * Fornecer dados de pagamento para cartão de crédito.
    * Revisar o resumo do pedido antes de confirmar.
* **Gerenciamento de Conta:**
    * Visualizar e editar informações pessoais (nome, data de nascimento, gênero).
    * Alterar senha.
    * Gerenciar endereços de entrega (adicionar, editar, definir como padrão).
* **Meus Pedidos:**
    * Visualizar o histórico de seus pedidos.
    * Ver os detalhes de cada pedido (itens, status, endereço de entrega, forma de pagamento).

## Configuração e Execução

### Pré-requisitos

* Java 21 ou superior
* Maven

### Como Rodar o Projeto

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/sampadev/rest-with-spring-boot-and-java-ecommerce-sampadev.git](https://github.com/sampadev/rest-with-spring-boot-and-java-ecommerce-sampadev.git)
    cd rest-with-spring-boot-and-java-ecommerce-sampadev
    ```
2.  **Compile o Projeto:**
    ```bash
    mvn clean install
    ```
3.  **Execute a Aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080` (a porta pode variar dependendo da configuração no `application.properties`).

### Acessos Padrão (Criados Automaticamente ao Iniciar)

* **Administrador:**
    * **E-mail:** `admin@sampastore.com`
    * **Senha:** `admin123`
* **Estoquista:**
    * **E-mail:** `estoquista@sampastore.com`
    * **Senha:** `estoque123`

### Acesso ao Console H2 Database

Durante o desenvolvimento, você pode acessar o console do H2 para visualizar o banco de dados interno:
`http://localhost:8080/h2-console` (verifique a porta no `application.properties`).
Credenciais:
* **JDBC URL:** `jdbc:h2:file:./data/sampastoredb` (conforme `application.properties`)
* **User Name:** `sa`
* **Password:** (deixe em branco)

## Estrutura do Projeto

* `br.com.sampa.sampastore.SampaStoreApplication.java`: Classe principal da aplicação.
* `br.com.sampa.sampastore.entity`: Contém as classes de modelo (entidades JPA).
* `br.com.sampa.sampastore.repository`: Interfaces para acesso a dados (Spring Data JPA).
* `br.com.sampa.sampastore.service`: Camada de lógica de negócios.
* `br.com.sampa.sampastore.controller`: Camada de controle (endpoints REST e web).
* `br.com.sampa.sampastore.enums`: Definições de enums para o sistema.
* `br.com.sampa.sampastore.security`: Classes de configuração de segurança.
* `br.com.sampa.sampastore.config`: Classes de configuração, incluindo inicialização de dados.
* `src/main/resources/templates`: Arquivos HTML (Thymeleaf) para as views.
* `src/main/resources/application.properties`: Configurações da aplicação.
* `src/test`: Classes de teste unitários e de integração.

## Contribuição

Sinta-se à vontade para abrir issues, enviar pull requests ou sugerir melhorias!
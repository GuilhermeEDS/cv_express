
# Projeto CV Express

## Visão Geral
CV Express é uma aplicação web baseada em Java, projetada para facilitar a gestão de candidaturas a vagas e envio de currículos. É construída usando Spring Boot e inclui funcionalidades para gerenciar candidatos, empresas e vagas de emprego.

## Estrutura do Projeto
O projeto segue uma estrutura padrão do Maven. Aqui está uma visão geral dos diretórios e arquivos principais:
- **.mvn/**: Arquivos do Maven Wrapper.
- **bin/**: Arquivos compilados e recursos.
- **src/**: Arquivos-fonte do aplicativo.
  - **main/**: Arquivos-fonte principais do aplicativo.
    - **java/**: Arquivos-fonte Java.
    - **resources/**: Recursos do aplicativo (por exemplo, arquivos de propriedades, templates HTML).
  - **test/**: Arquivos-fonte de testes.
- **pom.xml**: Arquivo do Maven Project Object Model.
- **Readme.md**: Arquivo README do projeto.

## Arquivos e Diretórios Principais

- **src/main/java/br/com/cv_express**: Contém os principais arquivos-fonte Java.
  - **controllers**: Contém controladores para lidar com solicitações HTTP.
  - **dtos**: Objetos de Transferência de Dados usados para transferir dados entre camadas.
  - **entities**: Entidades JPA que representam as tabelas do banco de dados.
  - **enumerations**: Enums usados no aplicativo.
  - **repositories**: Repositórios Spring Data JPA para acesso ao banco de dados.
  - **services**: Classes de serviço contendo lógica de negócio.

- **src/main/resources/templates**: Templates HTML Thymeleaf para renderização de páginas web.

- **application.properties**: Arquivo de propriedades de configuração para o Spring Boot.

## Pré-requisitos

- Java 22
- Maven 3.9.8 ou superior
- PostgreSQL 16.3 ou superior

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/GuilhermeEDS/cv_express.git
```

Entre no diretório do projeto

```bash
  cd cv_express
```

Configure o PostgreSQL

Crie um banco de dados no PostgreSQL e atualize o arquivo src/main/resources/application.properties com as informações do seu banco de dados:

```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco_de_dados
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha
```

Instale as dependências

```bash
  ./mvnw clean install
```

Execute a aplicação
```bash
  /mvnw spring-boot:run
```

Após isso sua aplicação estará rodando em: http://localhost:8080/
## Implementações adicionais

- Cadastro de Candidato e Empresa
- Cadastro de Cargo Desejado("Vaga")
- Sistema de Login


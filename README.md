# Agenda Bank Backend

Este projeto é uma API backend para o gerenciamento de transferências bancárias agendadas, desenvolvida em Java utilizando o framework Spring Boot. Abaixo estão as principais decisões arquiteturais, versões, ferramentas utilizadas e instruções para execução do projeto.

## Decisões Arquiteturais

- **Camadas Separadas:** O projeto segue o padrão de camadas, separando responsabilidades em Controller, Service, Repository, DTOs, Entities e Exception Handling.
- **Estratégia de Taxas:** O cálculo de taxas para transferências é implementado usando o padrão Strategy, facilitando a extensão e manutenção das regras de negócio.
- **Tratamento Global de Exceções:** Utiliza um handler global para capturar e tratar exceções de forma centralizada, garantindo respostas padronizadas para erros.
- **DTOs:** Utilização de Data Transfer Objects para abstrair entidades e garantir segurança e flexibilidade na comunicação entre camadas.
- **Persistência:** Utiliza Spring Data JPA para abstração do acesso a dados, facilitando integração com bancos relacionais.

## Versões e Ferramentas Utilizadas

- **Java:** 11
- **Spring Boot:** 2.x ou superior
- **Maven:** Gerenciador de dependências e build
- **Banco de Dados:** (Configuração padrão H2 ou outro relacional, conforme definido no `application.properties`)
- **JUnit:** Para testes automatizados

## Estrutura de Pastas

- `src/main/java/com/vitor/` - Código principal
  - `controller/` - Controllers REST
  - `service/` - Serviços e lógica de negócio
  - `service/fee/` - Estratégias de cálculo de taxas
  - `repository/` - Repositórios JPA
  - `entitys/` - Entidades do domínio
  - `dto/` - Data Transfer Objects
  - `exception/` - Tratamento de exceções
- `src/test/java/` - Testes automatizados

## Instruções para Subida do Projeto

1. **Pré-requisitos:**
   - Java 11 instalado
   - Maven instalado

2. **Configuração do Banco de Dados:**
   - Por padrão, o projeto pode utilizar H2 (memória) ou outro banco relacional configurado em `src/main/resources/application.properties`.

3. **Build do Projeto:**
   ```bash
   mvn clean install
   ```

4. **Execução da Aplicação:**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesso à API:**
   - A API estará disponível em `http://localhost:8080`.
   - Consulte a documentação dos endpoints no controller ou utilize ferramentas como Postman para testar.

6. **Testes:**
   ```bash
   mvn test
   ```

## Observações

- O projeto está preparado para fácil extensão de regras de taxas e novos tipos de transferências.
- Para ambiente de produção, configure variáveis sensíveis e banco de dados externo em `application.properties`.


### Testando a API com Postman, Insomnia ou outro
Você pode utilizar essas ferramentas para realizar chamadas à API e validar o funcionamento dos endpoints.

Requisição POST (Agendar Transferência)
- **URL**: `http://localhost:8080/api/transfers`
- **Método**: POST
- **Body** (JSON):
```json
{
  "sourceAccount": "1234567890",
  "destinationAccount": "0987654321",
  "amount": 100.00,
  "transferDate": "2025-09-10"
}
```
- **Headers**:
   - Content-Type: application/json

Requisição GET (Listar Transferências com Paginação)
- **URL**: `http://localhost:8080/api/transfers?page=1&size=10`
- **Método**: GET
- **Descrição**: Retorna as transferências agendadas, ordenadas pela data de agendamento (schedulingDate) de forma decrescente. Importante resaltar que os parâmetros são opcionais, padrão de `page = 1` e `size = 10`

Essas chamadas podem ser feitas facilmente copiando os exemplos acima para o Postman ou Insomnia, ajustando os dados conforme necessário para seus testes.


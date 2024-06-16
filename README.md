[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/9ONJnglb)
# Atividade de Gerenciamento de Eventos

Esta atividade consiste na implementação de um sistema de gerenciamento de eventos utilizando Spring Boot e JPA, com o suporte de Lombok para redução de código. O objetivo é criar uma aplicação que permita a criação e inscrição em eventos, seguindo os requisitos especificados abaixo.

Todos os códigos devem ser implementados nas camadas presentes em "app/src/main/java/com/poo/aula/demo/" e logo em seguida testados.

## Requisitos de Modelos

### Usuário

- **Atributos:**
  - `nome`: String, máximo de 100 caracteres, não pode ser nulo.
  - `email`: String, máximo de 60 caracteres, não pode ser nulo.
  - `senha`: String, máximo de 20 caracteres, não pode ser nulo.
  - `idade`: Integer, não pode ser nulo.
  - `eventosCriados`: Relacionamento One-to-Many com a entidade Evento.
  - `eventosInscritos`: Relacionamento Many-to-Many com a entidade Evento.

- **Anotações:**
  - `@Entity`: Define que a classe é uma entidade JPA.
  - `@Data`: Lombok para gerar getters, setters, equals, hashCode e toString automaticamente.
  - `@Id`, `@GeneratedValue`: Indicam a chave primária e a estratégia de geração de IDs.
  - `@Column`: Especifica os detalhes das colunas no banco de dados.
  - `@OneToMany`, `@ManyToMany`: Definem os relacionamentos entre entidades.

### Evento

- **Atributos:**
  - `nome`: String, máximo de 100 caracteres, não pode ser nulo.
  - `subtitulo`: String, máximo de 50 caracteres, não pode ser nulo.
  - `criador`: Relacionamento Many-to-One com a entidade Usuário.
  - `usuariosInscritos`: Relacionamento Many-to-Many com a entidade Usuário.

- **Anotações:**
  - `@Entity`: Define que a classe é uma entidade JPA.
  - `@Data`: Lombok para gerar getters, setters, equals, hashCode e toString automaticamente.
  - `@Id`, `@GeneratedValue`: Indicam a chave primária e a estratégia de geração de IDs.
  - `@Column`: Especifica os detalhes das colunas no banco de dados.
  - `@ManyToOne`, `@ManyToMany`: Definem os relacionamentos entre entidades.

## Repositórios

### UsuarioRepository

- **Métodos:**
  ```java
  Optional<Usuario> findByEmailAndSenha(String email, String senha);
- **Anotações:**
    - @Repository: Define que a interface é um repositório JPA.
    - Extende JpaRepository<Usuario, Long>.

### EventoRepository

- **Anotações:**
    - @Repository: Define que a interface é um repositório JPA.
    - Extende JpaRepository<Evento, Long>.


## Serviços

### UsuarioService

- **Métodos:**
```java
Optional<Usuario> login(String email, String senha);
Usuario addUsuario(Usuario usuario);
List<Usuario> getAllUsuarios();
Usuario updateUsuario(Long id, String nome);
```

- **Anotações:**
    - @Service: Define que a classe é um serviço.
    - @Autowired: Injeta dependências automaticamente.

### EventoService
- **Métodos:**
```java
Evento addEvento(Long usuarioId, Evento evento);
List<Evento> getAllEventos();
Evento updateEvento(Long id, String nome);
```

- **Anotações:**
    - @Service: Define que a classe é um serviço.
    - @Autowired: Injeta dependências automaticamente.

## Exceção

### UserNotAllowedException
- Descrição: Exceção personalizada lançada quando um usuário tenta criar um evento mas não possui idade suficiente.

## Controladores
### UsuarioController
- **Métodos:**
```java
void addUsuario(@RequestBody Usuario usuario);
List<Usuario> getAllUsuarios();
```
- **Anotações:**
    - @RestController: Define que a classe é um controlador REST.
    - @RequestMapping: Especifica a URL base do controlador.
    - @PostMapping: Mapeia solicitações POST.
    - @GetMapping: Mapeia solicitações GET.
    - @RequestBody: Indica que o parâmetro do método deve ser vinculado ao corpo da solicitação HTTP.

### EventoController
- **Métodos:**
```java
void addEvento(@RequestParam Long usuarioId, @RequestBody Evento evento);
List<Evento> getAllEventos();
```

- **Anotações:**

    - @RestController: Define que a classe é um controlador REST.
    - @RequestMapping: Especifica a URL base do controlador.
    - @PostMapping: Mapeia solicitações POST.
    - @GetMapping: Mapeia solicitações GET.
    - @RequestParam: Indica que o parâmetro do método deve ser vinculado a um parâmetro da solicitação HTTP.
    - @RequestBody: Indica que o parâmetro do método deve ser vinculado ao corpo da solicitação HTTP.
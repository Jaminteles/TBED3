# Documentação do Projeto TBED3

## Visão Geral

O projeto TBED3 é um sistema de arquivos simulado implementado em Java. Ele oferece uma estrutura hierárquica de diretórios e arquivos, permitindo operações básicas de manipulação como criar, deletar, renomear e buscar arquivos e diretórios. O projeto foi desenvolvido para a unidade 3 da disciplina Estruturas de Dados do curso de Sistemas de Informação - IFBA.

---

## Estrutura do Projeto

```
TBED3/
├── src/                         # Código-fonte principal
│   ├── Main.java                # Classe principal com interface de linha de comando
│   └── filesystem/              # Pacote com classes do sistema de arquivos
│       ├── Node.java            # Classe abstrata base para nós
│       ├── FileNode.java        # Representação de arquivos
│       ├── DirectoryNode.java   # Representação de diretórios
│       └── FileSystem.java      # Gerenciador do sistema de arquivos
├── test/                        # Testes do projeto
│   └── TestRunner.java          # Classe com testes automatizados
├── bin/                         # Arquivo binários compilados
├── README.md                    # Documentação geral
└── DOCUMENTATION.md             # Esta documentação
```

---

## Pacote `filesystem`

Contém as classes principais que implementam a estrutura de dados do sistema de arquivos.

### 1. **Node.java** - Classe Abstrata Base

#### Responsabilidades
- Define a interface base para todos os nós do sistema de arquivos (arquivos e diretórios)
- Gerencia propriedades comuns como nome e relação de parentesco

#### Atributos
| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `name` | String | Nome do nó |
| `parent` | DirectoryNode | Referência para o diretório pai (null se for raiz) |

#### Métodos Públicos

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `Node(String name)` | - | Construtor que inicializa o nó com um nome |
| `getName()` | String | Obtém o nome do nó |
| `setName(String newName)` | void | Define um novo nome para o nó |
| `getParent()` | DirectoryNode | Obtém o diretório pai |
| `setParent(DirectoryNode parent)` | void | Define o diretório pai |
| `getAbsolutePath()` | String | Retorna o caminho absoluto completo (ex: "/root/pasta/arquivo") |
| `isDirectory()` | boolean | **Abstrato** - Verifica se é um diretório |

#### Detalhes de Implementação
- Classe abstrata que força as subclasses a implementar `isDirectory()`
- O método `getAbsolutePath()` constrói recursivamente o caminho completo percorrendo os nós pais
- A relação de parentesco é mantida através do atributo `parent`

---

### 2. **FileNode.java** - Representação de Arquivos

#### Responsabilidades
- Representa um arquivo no sistema de arquivos
- Estende a classe `Node` para funcionar como nó folha

#### Atributos
| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `size` | int | Tamanho do arquivo em bytes |

#### Métodos Públicos

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `FileNode(String name, int size)` | - | Construtor que cria um arquivo com nome e tamanho |
| `getSize()` | int | Obtém o tamanho do arquivo em bytes |
| `isDirectory()` | boolean | Sempre retorna `false` (um arquivo não é diretório) |

#### Detalhes de Implementação
- Herda de `Node` todos os métodos de manipulação básica
- Não possui filhos (é um nó folha na árvore)
- O tamanho é imutável após a criação

---

### 3. **DirectoryNode.java** - Representação de Diretórios

#### Responsabilidades
- Representa um diretório no sistema de arquivos
- Gerencia uma coleção de nós filhos (arquivos e subdiretórios)

#### Atributos
| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `children` | List\<Node\> | Lista de nós filhos (arquivos e subdiretórios) |

#### Métodos Públicos

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `DirectoryNode(String name)` | - | Construtor que cria um diretório vazio com um nome |
| `getChildren()` | List\<Node\> | Obtém a lista de todos os filhos |
| `isDirectory()` | boolean | Sempre retorna `true` (um diretório é diretório) |
| `addChild(Node node)` | void | Adiciona um nó filho e define este diretório como seu pai |
| `removeChild(Node node)` | void | Remove um nó filho da lista |

#### Detalhes de Implementação
- Herda de `Node` todos os métodos básicos
- Mantém uma `ArrayList` de filhos para acesso rápido
- O método `addChild()` automaticamente define o parentesco do nó adicionado
- Permite estrutura hierárquica de diretórios aninhados

---

### 4. **FileSystem.java** - Gerenciador do Sistema de Arquivos

#### Responsabilidades
- Gerencia o sistema de arquivos completo
- Implementa operações de alto nível como criar, deletar, renomear, buscar e listar
- Fornece métodos de navegação pela hierarquia de diretórios

#### Atributos
| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `root` | DirectoryNode | Nó raiz do sistema de arquivos |

#### Métodos Públicos

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `FileSystem()` | - | Construtor que inicializa um sistema vazio com raiz |
| `getRoot()` | DirectoryNode | Obtém o nó raiz |
| `navigateTo(String path)` | DirectoryNode | Navega até um diretório específico usando seu caminho |
| `createDirectory(String path, String name)` | void | Cria um novo diretório dentro de outro diretório |
| `createFile(String path, String name, int size)` | void | Cria um novo arquivo dentro de um diretório |
| `rename(String path, String newName)` | boolean | Renomeia um arquivo ou diretório |
| `delete(String path)` | boolean | Remove um arquivo ou diretório |
| `listDirectory(String path)` | void | Lista o conteúdo (imprime) de um diretório |
| `findNode(String path)` | Node | Encontra um nó específico por seu caminho completo |
| `searchByName(String name)` | void | Busca recursiva por arquivos/diretórios com nome específico |
| `printTree()` | void | Imprime a árvore completa do sistema de arquivos |

#### Métodos Privados

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `searchRecursive(Node node, String name)` | void | Realiza busca recursiva a partir de um nó |
| `printRecursive(Node node, int depth)` | void | Realiza impressão recursiva com indentação por profundidade |

#### Detalhes de Implementação

**navigateTo(String path)**
- Divide o caminho em partes usando "/"
- Ignora partes vazias e "root"
- Navega descendendo pela hierarquia
- Retorna `null` se algum diretório intermediário não existir

**createDirectory() e createFile()**
- Navegam até o diretório pai especificado
- Criam novo nó e adicionam como filho
- Não fazem nada se o caminho pai for inválido

**rename() e delete()**
- Encontram o nó usando `findNode()`
- `rename()` apenas muda o nome
- `delete()` remove o nó de seu pai
- Ambos retornam `boolean` indicando sucesso

**findNode(String path)**
- Navega até a penúltima parte do caminho
- Procura o último componente entre os filhos
- Funciona tanto para arquivos quanto diretórios

**searchByName() e printTree()**
- Implementam padrão de travessia recursiva (DFS - Depth-First Search)
- `searchByName()` encontra todos os nós com um nome específico
- `printTree()` exibe a estrutura hierárquica com indentação visual

---

## Classe Main.java - Interface de Linha de Comando

#### Responsabilidades
- Fornece interface interativa para o usuário
- Processa comandos via terminal
- Gerencia a instância do `FileSystem`

#### Funcionalidade

A classe `Main` implementa um shell interativo que aceita os seguintes comandos:

| Comando | Sintaxe | Descrição |
|---------|---------|-----------|
| `mkdir` | `mkdir <path> <name>` | Cria um diretório |
| `touch` | `touch <path> <name> <size>` | Cria um arquivo com tamanho especificado |
| `ls` | `ls <path>` | Lista o conteúdo de um diretório |
| `rm` | `rm <path>` | Remove um arquivo ou diretório |
| `mv` | `mv <path> <newName>` | Renomeia um arquivo ou diretório |
| `find` | `find <name>` | Busca por arquivos/diretórios com um nome específico |
| `tree` | `tree` | Exibe a árvore completa do sistema de arquivos |
| `exit` | `exit` | Sai do programa |

#### Exemplos de Uso

```
Comando: mkdir /root documentos
Comando: touch /root/documentos arquivo.txt 1024
Comando: ls /root/documentos
[FILE] arquivo.txt
Comando: tree
[DIR] root
  [DIR] documentos
    [FILE] arquivo.txt
Comando: mv /root/documentos/arquivo.txt novo.txt
Comando: find novo.txt
/root/documentos/novo.txt
Comando: rm /root/documentos/novo.txt
Comando: exit
```

#### Detalhes de Implementação
- Usa `Scanner` para ler entrada do usuário
- Divide cada comando em partes usando espaço como delimitador
- Utiliza `switch-case` para processar cada comando
- Possui tratamento básico de erros com `try-catch`
- Não valida o número de argumentos (pode gerar exceções)

---

## Testes - TestRunner.java

#### Responsabilidades
- Testa as funcionalidades principais do sistema de arquivos
- Implementa framework de testes simples sem dependências externas
- Fornece relatório de testes passados e falhados

#### Atributos Estáticos
| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `passed` | int | Contador de testes que passaram |
| `failed` | int | Contador de testes que falharam |

#### Métodos de Utilidade

| Método | Descrição |
|--------|-----------|
| `ok()` | Incrementa o contador de testes passados |
| `fail(String msg)` | Incrementa falhas e exibe mensagem |
| `assertTrue(boolean cond, String msg)` | Verifica se condição é verdadeira |
| `assertEquals(Object exp, Object act, String msg)` | Verifica igualdade entre dois objetos |

#### Testes Implementados

**1. testCreateDirectoryAndFile()**
- Testa a criação de um diretório `/root/docs`
- Verifica se o diretório foi criado com sucesso
- Cria um arquivo `arquivo.txt` com tamanho 100 bytes
- Verifica se o arquivo existe e possui o nome correto

**2. testRenameAndDelete()**
- Cria um diretório `pasta` e arquivo `a.txt`
- Renomeia o arquivo para `b.txt` e verifica o retorno
- Deleta o arquivo renomeado e verifica se foi removido

**3. testSearchAndTree()**
- Cria estrutura aninhada: `/root/d1/d2/f.txt`
- Redireciona saída padrão para capturar resultado
- Busca pelo arquivo `f.txt`
- Verifica se o caminho completo é encontrado e exibido

#### Execução

O programa principal executa os três testes sequencialmente e exibe:
- Mensagens de teste iniciado
- Mensagens de falha (se houver)
- Resumo final com contagem de passados e falhados

Exemplo de saída:
```
--- testCreateDirectoryAndFile ---

--- testRenameAndDelete ---

--- testSearchAndTree ---

====== RESUMO ======
Passed: 6
Failed: 0
```

---

## Estrutura de Dados

### Árvore Hierárquica

O projeto utiliza uma **Árvore N-ária** (árvore onde cada nó pode ter múltiplos filhos):

```
        root (DirectoryNode)
       /    |    \
    dir1   dir2  file1
    /  \
 dir3  file2
 /
file3
```

**Características:**
- Cada nó conhece seu pai (para cálculo de caminho absoluto)
- Cada diretório mantém lista de filhos
- Relação bidirecional: pai ↔ filhos
- Profundidade ilimitada (estrutura recursiva)

### Percurso de Árvore (Traversal)

O projeto implementa:
- **DFS (Depth-First Search)** para `searchByName()` e `printTree()`
- **Linear Search** com Stream API para navegação e busca por nome

---

## Padrões de Projeto

### 1. **Composite Pattern**
- `Node` é o componente
- `DirectoryNode` é o composite (pode conter outros)
- `FileNode` é a folha (não pode conter filhos)

### 2. **Template Method**
- Classe abstrata `Node` define operações básicas
- Subclasses especializam o comportamento (`isDirectory()`)

### 3. **Visitor/Traversal**
- Métodos recursivos para percurso em profundidade
- Operações uniformes sobre toda a estrutura

---

## Fluxo de Execução Típico

```
1. Usuário inicia Main
2. FileSystem é criado com raiz vazia
3. Loop interativo aguarda comandos
4. Cada comando é processado:
   - mkdir: Cria DirectoryNode
   - touch: Cria FileNode
   - ls: Itera sobre children
   - find: Percorre recursivamente (DFS)
   - tree: Percorre recursivamente com indentação
   - rm: Remove nó do parent
   - mv: Renomeia nó
5. exit: Encerra programa
```

## Compilação e Execução

### Compilar
```bash
javac -d bin src/filesystem/*.java src/Main.java test/TestRunner.java
```

### Executar Main
```bash
java -cp bin Main
```

### Executar Testes
```bash
java -cp bin TestRunner
```

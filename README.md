# TBED3 - Sistema de Arquivos Simulado

## 📋 Descrição

TBED3 é um projeto educacional que implementa um **sistema de arquivos simulado** em Java. O projeto demonstra conceitos fundamentais de estruturas de dados, programação orientada a objetos e padrões de design através de uma estrutura hierárquica de diretórios e arquivos.

### Funcionalidades Principais
- ✅ Criar e gerenciar diretórios
- ✅ Criar e manipular arquivos com tamanho
- ✅ Renomear e deletar itens
- ✅ Navegar pela estrutura de diretórios
- ✅ Buscar arquivos por nome
- ✅ Visualizar a árvore completa do sistema

## 🗂️ Estrutura do Projeto

```
TBED3/
├── src/
│   ├── Main.java                  # Interface de linha de comando
│   └── filesystem/                # Pacote do sistema de arquivos
│       ├── Node.java             # Classe base abstrata
│       ├── FileNode.java         # Representação de arquivos
│       ├── DirectoryNode.java    # Representação de diretórios
│       └── FileSystem.java       # Gerenciador do sistema
├── test/
│   └── TestRunner.java           # Testes automatizados
├── bin/                           # Arquivos compilados
├── README.md                      # Este arquivo
└── DOCUMENTATION.md              # Documentação detalhada
```

## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior instalado
- Acesso ao terminal/prompt de comando

### 1️⃣ Compilar o Projeto

```bash
javac -d bin src/filesystem/*.java src/Main.java test/TestRunner.java
```

### 2️⃣ Executar os Testes

```bash
java -cp bin TestRunner
```

Isso executará todos os testes e exibirá um relatório com resultado.

### 3️⃣ Executar o Programa Principal

```bash
java -cp bin Main
```

Após executar, você verá um prompt interativo onde pode digitar comandos:

```
Comando: 
```

## 📝 Comandos Disponíveis

| Comando | Sintaxe | Descrição | Exemplo |
|---------|---------|-----------|---------|
| `mkdir` | `mkdir <path> <name>` | Criar diretório | `mkdir /root docs` |
| `touch` | `touch <path> <name> <size>` | Criar arquivo | `touch /root/docs file.txt 512` |
| `ls` | `ls <path>` | Listar conteúdo | `ls /root/docs` |
| `rm` | `rm <path>` | Remover item | `rm /root/docs/file.txt` |
| `mv` | `mv <path> <newName>` | Renomear | `mv /root/docs/file.txt novo.txt` |
| `find` | `find <name>` | Buscar por nome | `find file.txt` |
| `tree` | `tree` | Exibir árvore | `tree` |
| `exit` | `exit` | Sair | `exit` |

## 💡 Exemplo de Uso

```
Comando: mkdir /root documentos
Comando: touch /root/documentos relatorio.txt 2048
Comando: ls /root/documentos
[FILE] relatorio.txt
Comando: tree
[DIR] root
  [DIR] documentos
    [FILE] relatorio.txt
Comando: find relatorio.txt
/root/documentos/relatorio.txt
Comando: exit
```

## 📚 Para Mais Detalhes

Consulte o arquivo **[DOCUMENTATION.md](./DOCUMENTATION.md)** para:
- Documentação técnica completa de todas as classes
- Detalhes de implementação e padrões de design
- Estrutura de dados utilizada

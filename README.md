# 🐾 Sistema de Adoção de Pets

Trabalho final da disciplina **Laboratório de Programação II - Java**

Sistema de terminal desenvolvido em Java para gerenciar adoções de animais de estimação. Permite o cadastro de animais (cães, gatos e outros), adotantes e registro de adoções realizadas, com persistência de dados em arquivos `.txt`.


**Grupo:**
* Alexsander Amorim Borchardt
* Ester Bertolani
* Marcelo Henrique Fortaleza Mindas
* Natalia Pianca Martins
* Vanderson de Almeida Alves

**Disciplina:** Laboratório de Programação II  
**Instituição:** FAESA  
**Data:** Junho de 2025


## ✨ Funcionalidades Principais

### 1. **Cadastro de Animais** 🐕🐈
- Cadastro de **Cachorros** com atributos específicos (porte, adestramento, vermifugação)
- Cadastro de **Gatos** com informações de saúde (FIV, FeLV, comportamento)
- Cadastro de **Outros animais** com espécie customizável
- Validação de duplicatas por nome e tipo
- Prevenção de cadastros duplicados

### 2. **Cadastro de Adotantes** 👥
- Registro de pessoas interessadas em adotar
- Validação e unicidade de CPF (11 dígitos)
- Armazenamento de contato (telefone, endereço)

### 3. **Registro de Adoções** 📝
- Vinculação entre animal e adotante
- Registro de data da adoção
- Remoção automática de animais adotados da listagem de disponíveis

### 4. **Listagens e Consultas** 📊
- Listar todos os animais disponíveis
- Filtrar por espécie (Cães, Gatos, Outros)
- Filtrar por gênero (Macho/Fêmea)
- Listar animais castrados
- Consultar histórico de adoções realizadas

### 5. **Estatísticas** 📈
- Total de cachorros cadastrados
- Total de gatos cadastrados
- Total de outros animais cadastrados
- Média de idade dos animais disponíveis
- Percentual de animais adotados


## 🏗️ Estrutura do Projeto

```
src/
├── Aplicacao/
│   └── MenuSistemaAdocao.java          # Menu principal
├── Sistema/
│   ├── SistemaAdocao.java              # Orquestrador de operações
│   ├── ControleSistema.java            # Persistência em arquivos .txt
│   └── Listagem.java                   # Consultas e filtros
├── Classes/
│   ├── Animal.java                     # Classe base abstrata
│   ├── Cachorro.java                   # Extensão: Animal > Cachorro
│   ├── Gato.java                       # Extensão: Animal > Gato
│   ├── Outro.java                      # Extensão: Animal > Outro
│   ├── Adotante.java                   # Modelo de adotante
│   └── Adocao.java                     # Modelo de registro de adoção
└── Utilidade/
    └── TratamentoEntrada.java          # Validação e leitura de entrada

Arquivos de Dados:
├── animais.txt                         # Registro de animais
├── adotantes.txt                       # Registro de adotantes
└── adocoes.txt                         # Histórico de adoções
```


## 🔍 Arquitetura e Design 

### Herança Polimórfica
- Classe `Animal` como superclasse abstrata
- Subclasses `Cachorro`, `Gato` e `Outro` com atributos específicos
- Método `fromCSV()` estático em cada subclasse para desserialização

### Padrão de Separação de Responsabilidades
- **Aplicacao**: Interface com usuário
- **Sistema**: Lógica de negócio
- **Classes**: Modelos de dados
- **Utilidade**: Validação de entrada

### Persistência em Arquivo
- Formato `.txt` para armazenamento simples
- Carregamento automático ao inicializar operações
- Salvamento automático após operações de escrita

---

## 🚀 Como Compilar e Executar

### Pré-requisitos
- Java Development Kit (JDK) 8 ou superior
- Diretório de trabalho com a estrutura de pastas

### Compilação

```bash
# A partir da raiz do projeto
javac -d bin src/**/*.java
```

### Execução

```bash
# A partir da raiz do projeto
java -cp bin Aplicacao.MenuSistemaAdocao
```

Ou diretamente no IDE (Eclipse, IntelliJ, VS Code com extensão Java)

---

# 📱 ToDo List App - Jetpack Compose + Room

## 📖 Sobre o Projeto

Este projeto é uma aplicação de **Lista de Tarefas (ToDo List)** desenvolvida com **Android Nativo usando Kotlin**, com foco em persistência de dados e boas práticas de arquitetura.

O objetivo principal é praticar:

- Jetpack Compose (UI moderna)
- Room Database (SQLite)
- Arquitetura MVVM
- Gerenciamento de estado com ViewModel
- Persistência de preferências com SharedPreferences

---

## 🚀 Funcionalidades

- ✅ Criar novas tarefas  
- 📋 Listar tarefas salvas  
- ✔️ Marcar tarefas como concluídas  
- 🗑️ Excluir tarefas  
- 🌗 Alternar entre tema claro e escuro  
- 💾 Persistência de dados com Room  
- ⚙️ Salvamento de tema com SharedPreferences  

---

## 🏗️ Arquitetura

O projeto segue o padrão **MVVM (Model-View-ViewModel)**:

- **Model** → Room (Entity, DAO, Database)
- **ViewModel** → Lógica de negócio e estado
- **View (UI)** → Jetpack Compose

---

## 🛠️ Tecnologias Utilizadas

- Kotlin  
- Jetpack Compose  
- Room Database  
- ViewModel  
- StateFlow  
- SharedPreferences  
- Material 3  

---

## 📂 Estrutura do Projeto

app/
├── data/
│ ├── local/ # Room (Entity, DAO, Database)
│ └── repository/
├── ui/
│ ├── components/
│ ├── screens/
│ └── theme/
├── viewmodel/
└── MainActivity.kt


---

## ⚙️ Como Funciona

- As tarefas são armazenadas localmente usando **Room Database**
- A UI observa os dados com **StateFlow**
- O **ViewModel** faz a comunicação entre UI e dados
- O tema escolhido é salvo com **SharedPreferences**
- Ao abrir o app, as tarefas são carregadas automaticamente

---

## 🌗 Sistema de Tema

O app possui:

- Modo Claro ☀️  
- Modo Escuro 🌙  

A preferência do usuário é salva localmente.

---

## ▶️ Como Executar

1. Clone o repositório: https://github.com/lucascholzeh/ToDoListCompose-RoomDatabase.git

2. Abra no **Android Studio**

3. Execute em um emulador ou dispositivo físico

---

## 🎯 Objetivo de Aprendizado

Este projeto foi desenvolvido para:

- Entender persistência de dados no Android
- Aplicar boas práticas de Clean Code
- Utilizar arquitetura MVVM
- Trabalhar com Jetpack Compose

---

## 👨‍💻 Autor

Desenvolvido por **Lucas Scholze Hoffmann**

---

## 📌 Observações

Projeto acadêmico com foco em aprendizado de desenvolvimento Android moderno.

# Smart Treino — Android App

<p align="center">
  <img alt="Linguagem" src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin">
  <img alt="UI" src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white">
  <img alt="Backend" src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black">
  <img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen?style=for-the-badge&logo=android">
  <img alt="Architecture" src="https://img.shields.io/badge/Clean_Arch-MVVM-blue?style=for-the-badge">
</p>

**Smart Treino** é um aplicativo Android desenvolvido com **Kotlin** e **Jetpack Compose**, focado em oferecer uma experiência moderna, rápida e orientada a dados para usuários que desejam organizar seus treinos ou gerar rotinas personalizadas usando Inteligência Artificial (**Google Gemini**).

O projeto foi construído seguindo rigorosamente **Clean Architecture**, **MVVM** e princípios de **Offline-First**, utilizando Firebase para autenticação, sincronização e notificações.

## ✨ Features

- **🔐 Autenticação Robusta:**
  - Login híbrido: Email/Senha e Google Sign-In.
  - Logout persistente e tratamento de erros amigável.
- **🧠 Inteligência Artificial (Gemini):**
  - Geração de treinos automáticos consumindo a API do Google Gemini.
  - Prompts otimizados para criar rotinas personalizadas baseadas no perfil do usuário.
- **🏋️ Gestão de Treinos:**
  - Criação manual, edição e exclusão de exercícios.
  - Timer integrado para execução do treino.
- **🔄 Offline-First:**
  - Sincronização resiliente: Local (Room) ↔ Remoto (Firestore).
  - Gerenciamento de tarefas em background com WorkManager.
- **🔔 Notificações:**
  - Push Notifications via Firebase Cloud Messaging (FCM).
- **🎨 UI / UX Moderna:**
  - Design System Material 3.
  - Suporte total a Tema Claro e Escuro.
  - Internacionalização (PT / EN).

## 📸 Screenshots

| Login / Auth | Home & Dashboard | Detalhes do Treino | Geração com IA |
|:---:|:---:|:---:|:---:|
| <img src="https://via.placeholder.com/180x320?text=Login" width=180/> | <img src="https://via.placeholder.com/180x320?text=Home" width=180/> | <img src="https://via.placeholder.com/180x320?text=Details" width=180/> | <img src="https://via.placeholder.com/180x320?text=Gemini+AI" width=180/> |

## 🛠️ Tech Stack

- **Linguagem:** Kotlin
- **UI:** Jetpack Compose, Material 3
- **Arquitetura:** Clean Architecture + MVVM
- **Injeção de Dependência:** Hilt
- **Dados Locais:** Room Database
- **Dados Remotos:** Firebase Auth, Firestore, FCM
- **IA:** Google Gemini (via Ktor Client/Retrofit)
- **Navegação:** Navigation Compose
- **Background Jobs:** WorkManager
- **Assincronismo:** Coroutines + Flow
- **Build:** Gradle Kotlin DSL

## 🏗️ Architecture

O projeto segue os princípios da **Clean Architecture**, organizando o código em camadas para garantir desacoplamento, testabilidade e facilidade de manutenção.

```text
com.smarttreino
 ├── core/              # Configurações globais, DI (Hilt) e Utilitários
 │   ├── constants/     # Constantes (ex: Coleções do Firestore)
 │   ├── di/            # Módulos do Hilt (AppModule, AuthModule)
 │   └── util/          # Classes utilitárias e Exceções de Domínio
 │
 ├── data/              # Camada de Dados (Repository Pattern)
 │   ├── local/         # Persistência local (Room Database, DAOs, Entities)
 │   ├── remote/        # Fontes de dados externas
 │   │   ├── firebase/  # Integração com Auth e Firestore
 │   │   └── gemini/    # Integração com a API de IA
 │   └── repository/    # Implementação das interfaces de repositório
 │
 ├── domain/            # Camada de Domínio (Regras de Negócio Puras)
 │   ├── model/         # Modelos de dados agnósticos a framework
 │   ├── repository/    # Interfaces (contratos) dos repositórios
 │   └── usecase/       # Casos de uso (Lógica de negócios encapsulada)
 │
 ├── ui/                # Camada de Apresentação (Jetpack Compose)
 │   ├── navigation/    # Grafo de navegação e rotas
 │   ├── screens/       # Telas, ViewModels e UiStates (MVVM)
 │   └── theme/         # Design System (Cores, Tipografia, Tema)
 │
 └── worker/            # Tarefas em background (WorkManager)
```

### 🧩 Princípios Importantes
- **StateFlow:** Utilizado como única fonte de verdade para os estados da UI (ViewStates).
- **Unidirectional Data Flow (UDF):** Garante consistência, com eventos fluindo para cima (ViewModel) e estados fluindo para baixo (UI).
- **Stateless Components:** Composables são mantidos sem estado sempre que possível para maximizar a reutilização e facilitar testes.
- **UseCases:** As regras de negócio são centralizadas aqui, mantendo os ViewModels focados apenas em gerenciar o estado da tela.
- **Desacoplamento:** Os repositórios ocultam a complexidade de dados (Firebase/Room) do restante do app.

## 🚀 How to Run

### 1. Pré-requisitos
- **Android Studio:** Versão Giraffe, Iguana ou superior.
- **JDK:** Java 17 ou superior.

### 2. Configuração do Firebase
1. Crie um novo projeto no [Firebase Console](https://console.firebase.google.com).
2. No menu **Criação**, ative:
   - **Authentication:** Provedores **Google** e **Email/Senha**.
   - **Firestore Database:** Crie um banco de dados.
3. Baixe o arquivo `google-services.json` e coloque-o na pasta do módulo `app`:
   ```text
   SmartTreino/app/google-services.json
   ```

### 3. Configuração da API Gemini
Obtenha sua chave de API no [Google AI Studio](https://aistudio.google.com/). Em seguida, abra o arquivo `local.properties` na raiz do projeto e adicione:

```properties
GEMINI_API_KEY=SUA_CHAVE_AQUI
```

### 4. Executando o App
Sincronize o projeto e execute o comando abaixo no terminal, ou utilize o botão **Run** (▶️) do Android Studio:

```bash
./gradlew assembleDebug
```

## ✅ Tests

O projeto adota uma estratégia de testes para garantir qualidade e estabilidade.

- **🧪 Testes Unitários:** Focam na lógica de negócios (Use Cases), Repositórios e regras de validação.
- **📱 Testes Instrumentados:** Validam as telas em Compose, fluxo de navegação e persistência local com Room.

## 🔒 Privacy (LGPD)

O Smart Treino leva a privacidade a sério:

- **Dados Coletados:** Nome, Email, Histórico de treinos e execuções.
- **Segurança:**
  - Consentimento explícito no cadastro.
  - Tráfego criptografado (HTTPS / TLS).
  - Regras de segurança do Firestore (Security Rules) por usuário.
  - Funcionalidade planejada de reset e exclusão de dados.

---

## 📄 Documentação Legal

Para garantir transparência e conformidade com as diretrizes da LGPD:

- [📘 Termos de Uso](./TERMS_OF_USE.md)
- [🔒 Política de Privacidade](./PRIVACY_POLICY.md)

---


## 📜 License

MIT License

Copyright (c) 2025 André Brito Vita

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

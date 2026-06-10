# HabitFlow

Aplicativo Android para criação e acompanhamento de hábitos diários, com backend Node.js e banco de dados MongoDB Atlas.

---

## Funcionalidades

- Cadastro e login de usuários com autenticação JWT
- Criação, listagem, conclusão e exclusão de hábitos
- Persistência de dados em nuvem via MongoDB Atlas
- Suporte a tema claro e escuro
- Permissão de notificações para lembretes futuros

---

## ️ Tecnologias

### Android (Client)
- Java
- Retrofit 2 + Gson
- ViewModel + LiveData (MVVM)
- Material Design 3
- RecyclerView

### Backend
- Node.js + Express 5
- Mongoose + MongoDB Atlas
- JWT (jsonwebtoken)
- Bcrypt
- Dotenv

---

## Arquitetura

```
HabitFlow/
├── app/                        # Android app
│   └── src/main/java/com/example/habitflow/
│       ├── data/
│       │   ├── model/          # Habit, User
│       │   ├── network/        # Retrofit, ApiService
│       │   └── repository/     # HabitRepository, AuthRepository
│       ├── viewmodel/          # HabitViewModel, AuthViewModel
│       └── activities/         # Login, Register, Home, HabitList, etc.
│
└── backend/                    # API REST
    └── src/
        ├── config/             # Conexão MongoDB
        ├── controllers/        # authController, habitController
        ├── middlewares/        # authMiddleware (JWT)
        ├── models/             # User, Habit
        └── routes/             # authRoutes, habitRoutes
```

---

## Como rodar

### Backend

1. Acesse a pasta do backend:
```bash
cd backend
```

2. Instale as dependências:
```bash
npm install
```

3. Crie o arquivo `.env`:
```env
MONGO_URI=mongodb+srv://<usuario>:<senha>@<cluster>.mongodb.net/habitflow?retryWrites=true&w=majority
JWT_SECRET=sua_chave_secreta
PORT=6000
```

4. Inicie o servidor:
```bash
node server.js
```

### Android

1. Abra o projeto no Android Studio
2. Em `RetrofitClient.java`, configure a `BASE_URL`:
    - Emulador: `http://10.0.2.2:6000/`
    - Dispositivo físico: IP local da sua máquina
3. Execute o app em um emulador ou dispositivo com Android 7.0+ (API 24+)

---

## Permissões

| Permissão | Motivo |
|---|---|
| `INTERNET` | Comunicação com a API backend |
| `POST_NOTIFICATIONS` | Lembretes de hábitos diários (Android 13+) |

---

## Endpoints da API

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| POST | `/auth/register` | Cadastro de usuário | ❌ |
| POST | `/auth/login` | Login e geração de token | ❌ |
| GET | `/habits` | Lista hábitos do usuário | ✅ |
| POST | `/habits` | Cria novo hábito | ✅ |
| PUT | `/habits/:id` | Atualiza hábito | ✅ |
| DELETE | `/habits/:id` | Remove hábito | ✅ |

---

## Autores

Desenvolvido por **Filipe Rodrigues**, **Caíque Lima** e **Cauã Vieira**
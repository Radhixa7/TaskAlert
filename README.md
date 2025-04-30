# 📝 Task Alert

Aplikasi manajemen tugas pintar berbasis Android + Backend Ktor, dengan dukungan AI, notifikasi, dan penyimpanan database MongoDB.

---

## 📱 Fitur Aplikasi (Android - Frontend)

- Tambah, edit, hapus tugas
- Tandai tugas sebagai selesai
- Rekomendasi prioritas dengan AI *(Coming soon)*
- Smart Reminder berbasis waktu luang *(Coming soon)*
- UI modern dengan Jetpack Compose
- Mode offline dengan Room Database

---

## 🌐 Backend (Ktor + MongoDB)

- RESTful API menggunakan [Ktor](https://ktor.io/)
- Database NoSQL menggunakan [MongoDB](https://www.mongodb.com/)
- Endpoint JSON (GET, POST, PUT, DELETE)
- Siap deploy ke VPS / Railway / Render

---

## 🧱 Teknologi yang Digunakan

| Bagian      | Teknologi                |
|-------------|--------------------------|
| Frontend    | Android (Kotlin), Jetpack Compose, Room |
| Backend     | Kotlin Ktor, Koin, MongoDB, Exposed/MongoDriver |
| Database    | MongoDB (Atlas/Local)    |
| Tools       | Gradle, Git, Postman     |

---

## 🚀 Cara Menjalankan Proyek

### ▶️ Android (Frontend)
1. Buka folder `app/` di Android Studio
2. Pastikan emulator atau device aktif
3. Klik ▶️ untuk menjalankan

### 🌍 Ktor Backend
1. Masuk ke folder `backend/`
2. Jalankan perintah berikut:
   ```bash
   ./gradlew run

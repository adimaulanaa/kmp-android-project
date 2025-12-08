# 🗂️ Catatan Resource Android – Perlu `./gradlew clean` atau Tidak?

## 📌 Kapan Harus Menjalankan `./gradlew clean`?

### ✔ Wajib Clean jika:
- Menambah **file baru** ke dalam folder resource Android:
  - `android/app/src/main/res/drawable`
  - `android/app/src/main/res/mipmap`
  - atau folder resource lain seperti `drawable-xxhdpi`, `mipmap-hdpi`, dll.
- Mengganti **nama file resource**.
- Mendapatkan error seperti:
  - `resource not found`
  - `AAPT: error: ...`
  - Resource tidak muncul padahal sudah ditambahkan.

### ❗ Kenapa harus clean?
Karena Android melakukan **resource caching**, jadi saat ada file resource baru, Gradle tidak selalu langsung mengenalinya.  
`./gradlew clean` akan:
- Menghapus cache,
- Membangun ulang file R.java,
- Regenerate semua resource.

---

## ❌ Kapan Tidak Perlu `./gradlew clean`?
- Hanya **mengubah isi file resource** yang sudah ada (overwrite).
- Hanya mengedit:
  - Jetpack Compose UI,
  - Kode Kotlin,
  - Logic,
  - Layout XML,
  - Script Gradle.

---

## 🧠 Rumus Cepat
> **Tambah file baru = Clean**  
> **Edit file lama = Tidak perlu clean**

---

## 🔧 Perintah Clean
MacOS/Linux:
```sh
./gradlew clean

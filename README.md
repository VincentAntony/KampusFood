# KampusFood — Structural Design Patterns

Contoh implementasi Java:
- Adapter: `PaymentProcessor`, `OldPayAdapter`, `NewPayAdapter`
- Composite: `MenuComponent`, `MenuItem`, `MenuCategory`, `BundlePackage`
- Bridge: `Notification`, `MessageSender`, empat sender dan tiga jenis notifikasi
- Facade: `OrderFacade` mengoordinasikan stok, pembayaran, pengiriman, notifikasi

## Menjalankan

Memerlukan JDK 11 atau lebih baru.

Dari folder proyek:

```bash
javac -d out $(find src -name "*.java")
java -cp out kampusfood.Main
```

Di Windows Command Prompt, dapat dikompilasi dengan:

```bat
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
java -cp out kampusfood.Main
```

Semua gateway dan kanal di sini disimulasikan dengan output konsol. Ini bukan integrasi pembayaran atau notifikasi sungguhan.

## Jumlah kelas Bridge

Rancangan awal memiliki 12 kelas konkret hasil kombinasi 3 jenis notifikasi × 4 kanal.
Rancangan Bridge memiliki 7 kelas konkret: 3 jenis notifikasi + 4 sender kanal.
Antarmuka `MessageSender` dan kelas abstrak `Notification` tidak dihitung sebagai kelas konkret.

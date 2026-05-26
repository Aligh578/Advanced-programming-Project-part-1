# Chess Game - Phase 1

A console-based Chess game implemented in Java, featuring object-oriented design patterns, strict movement validation, and a clean command-line interface.

---

## 🛠️ Features Implemented
* **8x8 Chess Board Grid:** Full implementation of coordinates system with proper board rendering.
* **Piece Logic & Validation:** Individual movement mechanics for basic and complex pieces (`Pawn`, `Knight`, `Bishop`, `Rook`, `Queen`, `King`).
* **Game Loop Coordinator:** Smooth turn-taking system between `WHITE` and `BLACK` players, including input cleanup and safety checks.
* **Console-based UI:** Clear chess notation layout (`a1` to `h8`) optimized for standard terminals.

---

## 📂 Project Structure
Below is the architectural layout of the core components in this phase:

```text
Project-part-1/
├── .gitignore          # Filters compiled class files and IDE metadata
├── README.md           # Project documentation (This file)
└── src/
    ├── Main/
    │   ├── Main.java   # Application entry point with try-catch safety
    │   └── Game.java   # Game loop controller & input parsing logic
    ├── Board/
    │   └── Board.java  # 2D grid matrix and board initialization
    ├── Pieces/
    │   ├── Piece.java  # Base abstract class for all chess pieces
    │   ├── Pawn.java   # Forward/diagonal attack logic for Pawns
    │   ├── Knight.java # Cartesian-based L-shape movement
    │   ├── Bishop.java # Diagonal path clearance validator
    │   ├── Rook.java   # Horizontal & vertical path validator
    │   ├── Queen.java  # Combined diagonal and straight path checker
    │   └── King.java   # 1-step radius movement controller
    └── Utils/
        └── Color.java  # Enum holding WHITE and BLACK definitions


        # پروژه بازی شطرنج - فاز ۱

یک بازی شطرنج تحت کنسول (Command-Line) که با زبان جاوا و بر اساس اصول شی‌گرایی (OOP)، سیستم اعتبارسنجی دقیق حرکت مهره‌ها و ساختار کدنویسی تمیز پیاده‌سازی شده است.

---

## 🛠️ قابلیت‌های پیاده‌سازی شده
* **ماتریس ۸×۸ صفحه شطرنج:** پیاده‌سازی کامل سیستم مختصات استاندارد و رندر زیبای صفحه بازی در محیط کنسول.
* **منطق و اعتبارسنجی حرکت مهره‌ها:** محاسبات حرکتی دقیق برای تمامی مهره‌ها شامل (`Pawn`، `Knight`، `Bishop`، `Rook`، `Queen`، `King`).
* **مدیریت چرخه بازی (Game Loop):** کنترل نوبت بین بازیکنان سفید (`WHITE`) و سیاه (`BLACK`) همراه با پاک‌سازی ورودی‌های کاربر و مدیریت خطاهای احتمالی.
* **رابط کاربری کنسولی:** نمایش منظم صفحه شطرنج با فرمت استاندارد حروف a تا h و اعداد ۱ تا ۸.

---

## 📂 ساختار درختی پروژه
معماری و نحوه چیدمان کامپوننت‌های اصلی پروژه در این فاز به شرح زیر است:

```text
Project-part-1/
├── .gitignore          # فیلتر کردن فایل‌های کامپایل شده و تنظیمات IDE
├── README.md           # مستندات پروژه (همین فایل)
└── src/
    ├── Main/
    │   ├── Main.java   # نقطه ورود برنامه همراه با ساختار Try-Catch ایمن
    │   └── Game.java   # کنترل‌کننده اصلی چرخه بازی و پردازش ورودی‌ها
    ├── Board/
    │   └── Board.java  # ماتریس دو بعدی صفحه و چیدمان اولیه مهره‌ها
    ├── Pieces/
    │   ├── Piece.java  # کلاس انتزاعی (Abstract) پایه برای تمامی مهره‌ها
    │   ├── Pawn.java   # منطق حرکت رو به جلو و حمله قطری سرباز
    │   ├── Knight.java # اعتبارسنجی حرکت L شکل اسب بر اساس محاسبات دکارتی
    │   ├── Bishop.java # بررسی مسیرهای قطری و خالی بودن مسیر فیل
    │   ├── Rook.java   # بررسی مسیرهای مستقیم افقی و عمودی رخ
    │   ├── Queen.java  # ترکیب منطق حرکتی رخ و فیل برای وزیر
    │   └── King.java   # کنترل شعاع حرکتی ۱ قدمی شاه
    └── Utils/
        └── Color.java  # انام (Enum) تعریف‌کننده رنگ‌های سفید و سیاه
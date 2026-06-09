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



        ## 🎯 Phase 2: Advanced Rules, Game Logic, and Chess Clock

In this phase, all advanced chess rules compliant with FIDE standards, King safety detection mechanisms, endgame conditions, and an extra-credit feature (Chess Clock) were successfully integrated into the project.

### 🚀 Features Implemented in Phase 2

1. **Pawn Promotion:**
   - When a pawn reaches the furthest rank (rank 0 for White and rank 7 for Black), the game prompts a graphical menu allowing the player to promote the pawn to a **Queen, Rook, Bishop, or Knight**.

2. **En Passant Capture:**
   - The `Board` class now tracks the history of the last move. If an opponent moves their pawn two squares forward, an adjacent pawn can capture it diagonally on the very next turn.

3. **Castling:**
   - Implemented synchronous movement for both the King and the Rook (King-side and Queen-side castling). The King moves two squares toward the Rook, and the Rook hops over the King.
   - **Strict Castling Restrictions:** The system verifies that neither the King nor the chosen Rook has previously moved (`hasMoved`), the path between them is entirely empty, the King is not currently in check, and the King does not pass through or land on any squares attacked by the opponent.

4. **Check Detection & Self-Check Prevention:**
   - Added a move simulation method (`wouldMoveLeaveKingInCheck`) to the `Board` class. Before any move is finalized, the board simulates it temporarily. If the move exposes or leaves the player's own King in check, it is declared illegal, the move is blocked, and valid move highlights are restricted accordingly.

5. **Checkmate & Stalemate:**
   - At the end of every turn, the system evaluates whether the active player has any legal moves left on the entire board.
   - If no legal moves are available and the King is in check -> **Checkmate** (Declares winner and terminates the game).
   - If no legal moves are available and the King is NOT in check -> **Stalemate** (The game ends in a draw).

6. **⏱️ Chess Clock / Game Timer (Extra Credit Feature):**
   - Built an interactive, real-time game timer using `javax.swing.Timer` at the top of the GUI.
   - Each player is allocated 5 minutes (300 seconds). Upon changing turns, the previous player's clock pauses, and the active player's clock counts down second-by-second. If a player's time expires, the game stops, and the opponent is declared the winner.

---

### 🛠️ Code Architecture & Modified Methods

* **`Piece.java`**: Added the `hasMoved` state variable along with its respective getters and setters to track King and Rook movements.
* **`Board.java`**:
  - `isInCheck(Color)`: Evaluates if a specific King is currently under attack.
  - `isSquareAttacked(...)`: A radar mechanism checking if a specific square can be captured by any opponent pieces.
  - `wouldMoveLeaveKingInCheck(...)`: Simulates prospective moves to enforce king safety and block illegal actions.
* **`King.java` & `Pawn.java`**: Enhanced the `isValidMove` method to include complex conditional branches for Castling and En Passant logic.
* **`ChessGUI.java`**: Developed the timer UI label, managed promotion pop-up dialogues, synchronized clock pauses during checkmate/stalemate, and updated click-handling mechanics for combined piece movements.



## 🎯 فاز دوم: پیاده‌سازی قوانین پیشرفته، منطق بازی و تایمر (Phase 2)

در این فاز، تمام قوانین پیشرفته بازی شطرنج طبق استانداردهای فیده (FIDE)، سیستم‌های رادار تشخیص وضعیت شاه، مکانیزم‌های پایان بازی و یک ویژگی امتیازی (ساعت شطرنج) با موفقیت به پروژه اضافه شد.

### 🚀 قابلیت‌های اضافه شده در فاز دوم

1. **ترفیع پیاده (Pawn Promotion):**
   - هنگامی که سرباز به آخرین سطر بنشیند (سطر ۰ برای سفید و ۷ برای سیاه)، بازی متوقف شده و یک منوی گرافیکی به کاربر اجازه می‌دهد مهره خود را به **وزیر، رخ، فیل یا اسب** ارتقا دهد.

2. **حرکت ویژه آن‌پاسان (En Passant):**
   - کلاس `Board` مجهز به حافظه ثبت آخرین حرکت (`Last Move Memory`) شد. اگر حریف سرباز خود را دو خانه به جلو پرتاب کند، سرباز مجاور می‌تواند در نوبت بلافاصله بعد، آن را به صورت مورب شکار کند.

3. **قلعه رفتن (Castling):**
   - منطق حرکت هم‌زمان شاه و رخ (شاه‌قلعه و وزیرقلعه) پیاده‌سازی شد. شاه ۲ خانه به سمت رخ حرکت کرده و رخ به پشت شاه می‌پرد.
   - **شرط‌های سخت‌گیرانه قلعه:** عدم حرکت قبلی شاه و رخ (`hasMoved`)، خالی بودن مسیر بین آن‌ها، کیش نبودن شاه در لحظه شروع، و زیر ضرب نبودن خانه‌های مسیر عبور شاه به طور کامل بررسی می‌شوند.

4. **سیستم تشخیص کیش و جلوگیری از خودکشی شاه:**
   - متد شبیه‌ساز حرکات (`wouldMoveLeaveKingInCheck`) به بورد اضافه شد. قبل از قطعی شدن هر حرکت، بورد آن را در حافظه موقت خود تست می‌کند؛ اگر آن حرکت باعث کیش شدن شاه خودی شود (خودکشی یا رفع نکردن کیش)، حرکت غیرمجاز اعلام شده و مهره جابجا نمی‌شود. خانه‌های غیرمجاز نیز هایلایت نخواهند شد.

5. **کیش‌ومات (Checkmate) و پات (Stalemate):**
   - در پایان هر نوبت، سیستم بررسی می‌کند که آیا بازیکن فعلی هیچ حرکت قانونی در کل صفحه دارد یا خیر.
   - اگر حرکتی نداشت و شاهش کیش بود $\rightarrow$ **کیش‌ومات** (اعلان برنده و اتمام بازی).
   - اگر حرکتی نداشت و شاهش کیش نبود $\rightarrow$ **پات/مساوی** (اتمام بازی با نتیجه برابر).

6. **⏱️ ساعت شطرنج / تایمر بازی (بخش امتیازی - Extra Credit):**
   - یک تایمر هوشمند با استفاده از `javax.swing.Timer` در بالای صفحه گرافیکی طراحی شد.
   - هر بازیکن ۵ دقیقه زمان دارد. با تغییر نوبت، تایمر بازیکن قبلی متوقف شده و تایمر بازیکن جدید ثانیه‌به‌ثانیه کم می‌شود. به محض اتمام زمان هر بازیکن، بازی متوقف شده و حریف برنده اعلام می‌شود.

---

### 🛠️ ساختار کدهای تغییر یافته و متدها

* **`Piece.java`**: اضافه شدن متغیر `hasMoved` و متدهای Getter/Setter مربوطه برای رهگیری وضعیت مهره‌های شاه و رخ.
* **`Board.java`**:
  - `isInCheck(Color)`: بررسی وضعیت کیش بودن شاه.
  - `isSquareAttacked(...)`: رادار بررسی زیر ضرب بودن یک خانه خاص توسط مهره‌های حریف.
  - `wouldMoveLeaveKingInCheck(...)`: شبیه‌ساز حرکت برای جلوگیری از حرکات نامشروع.
* **`King.java` & `Pawn.java`**: بازنویسی و ارتقای متد `isValidMove` برای گنجاندن شروط پیچیده قلعه‌رفتن و آن‌پاسان.
* **`ChessGUI.java`**: طراحی گرافیکی لِیبل تایمر، پیاده‌سازی پاپ‌آپ‌های ارتقای پیاده، متوقف کردن تایمر در زمان مات/پات و مدیریت کلیک دوم برای حرکات ترکیبی.
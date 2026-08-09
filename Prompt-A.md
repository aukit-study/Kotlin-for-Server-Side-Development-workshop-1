# สรุปประวัติการใช้งาน Prompt (AI Log - Prompt-A)
**รายวิชา:** Kotlin for Server-Side Development  
**หัวข้อ:** Workshop #1 - พื้นฐานภาษา + Null Safety  
**โหมดการทำงาน:** TUTOR MODE (Session 1)

---

## รายการ Prompt ที่ผู้ใช้ (นักศึกษา) ป้อนให้กับ AI

### 1. Prompt กำหนดบทบาทและกติกาคลาสเรียน (Initial Tutor Setup)
* **เนื้อหา Prompt:** 
  > ตั้งค่าบทบาทเป็น "ติวเตอร์ผู้เชี่ยวชาญด้าน Kotlin สำหรับ Server-Side Development" ทำงานภายใต้กติกา Tutor Mode ของ Session 1 (ห้ามเขียนเฉลย ให้คำใบ้ทีละขั้นตอน อธิบาย Concept และวิจารณ์โค้ดทีละจุด)
* **จุดประสงค์:** กำหนดเงื่อนไขการช่วยเหลือของ AI ให้ถูกต้องตามกฎของคลาสเรียน

---

### 2. Prompt สอบถามแนวทางขั้นตอนที่ 3 (`when` expression)
* **เนื้อหา Prompt:** 
  > ส่ง snippet โค้ดส่วนเมนูและการรับค่า `val choice = readln()` เพื่อขอแนวทางการเขียน `when` expression
* **ผลลัพธ์/คำแนะนำที่ได้รับ:** AI อธิบายโครงสร้างของ `when` การจับคู่ข้อความ การเรียกใช้ฟังก์ชัน และการใช้ `break` ออกจากลูป `while (true)`

---

### 3. Prompt สอบถามแนวทางขั้นตอนที่ 5 (Null Safety ใน `convertCelsiusToFahrenheit`)
* **เนื้อหา Prompt:** 
  > ส่ง snippet ฟังก์ชัน `convertCelsiusToFahrenheit()` เพื่อขอแนวทางการใช้ `toDoubleOrNull()` และ Elvis operator (`?:`)
* **ผลลัพธ์/คำแนะนำที่ได้รับ:** AI อธิบายการทำงานของ `toDoubleOrNull()`, Elvis operator (`?:`) และการใช้ `return` เมื่อข้อมูลไม่ถูกต้อง

---

### 4. Prompt สอบถามแนวทางขั้นตอนที่ 5 (Null Safety ใน `convertKilometersToMiles`)
* **เนื้อหา Prompt:** 
  > ส่ง snippet ฟังก์ชัน `convertKilometersToMiles()` เพื่อยืนยันและขอคำแนะนำในการแปลงค่ากิโลเมตรเป็นไมล์
* **ผลลัพธ์/คำแนะนำที่ได้รับ:** AI แนะนำโครงสร้าง Null Safety ที่คล้ายกัน และเตือนความจำเรื่องการสร้างฟังก์ชันคำนวณแยก (ข้อ 4)

---

### 5. Prompt คำสั่งสร้างไฟล์สรุปประวัติ Prompt
* **เนื้อหา Prompt:** 
  > `สร้าวไฟล์Prompt-A เพื่อสรุป ว่าฉันใช้ prompt อะไรบ้าง`
* **ผลลัพธ์/คำแนะนำที่ได้รับ:** AI สร้างไฟล์ `Prompt-A.md` รวบรวมและสรุปประวัติ Prompt ทั้งหมดในการทำ Workshop #1


# รายงานการวิเคราะห์และถอดบทเรียนการใช้งาน AI (AI Log - Prompt-t.md)
**รายวิชา:** Kotlin for Server-Side Development  
**หัวข้อ:** Workshop #1 - พื้นฐานภาษา + Null Safety  
**โหมดการทำงาน:** TUTOR MODE (Session 1)

---

## 1. AI ตอบผิด / น่าสงสัยตรงไหน (AI Mistakes / Suspicious Points)

* **จุดที่ 1: ความสับสนระหว่าง Scope ของ `break` กับ `return` ภายในลูป `while (true)`**
  * **รายละเอียด:** ในช่วงที่ AI ให้คำใบ้สำหรับขั้นตอนที่ 3 การควบคุมเมนูด้วย `when` AI ได้เกริ่นว่าสามารถใช้ได้ทั้ง `break` หรือ `return` ซึ่งแม้จะทำให้โปรแกรมหยุดทำงานได้เหมือนกัน แต่ในมิติการทำงานเชิงลึก `break` ทำหน้าที่เพียงออกจากลูป `while` ในขณะที่ `return` จะเป็นการออกจากฟังก์ชัน `main()` ทันที การไม่เน้นย้ำความแตกต่างนี้อาจสร้างความสับสนเกี่ยวกับ Function Scope ได้
* **จุดที่ 2: ปัญหาแพ็กเกจนำเข้าในไฟล์ทดสอบ (`WorkshopTest.kt`)**
  * **รายละเอียด:** เมื่อทำการรัน Automated Test ผ่าน Gradle (`./gradlew test`) เกิดข้อผิดพลาดในขั้นตอนคอมไพล์ `Unresolved reference 'celsiusToFahrenheit'` เนื่องจากไฟล์ทดสอบไม่ได้ใส่คำสั่ง `import org.example.*` ไว้แต่แรก ทำให้ไฟล์เทสหาฟังก์ชันใน `src/main/kotlin/Workshop1.kt` ไม่เจอ

---

## 2. เราตัดสินใจ / แก้อย่างไร (Decisions & Fixes)

* **การแก้ไขจุดที่ 1 (การเลือกใช้ `return` ในเมนู `exit`):**
  * **การตัดสินใจ:** เลือกใช้ `return` ภายในบล็อกเคส `"exit"` ใน `when (choice)` เพราะโปรแกรมนี้มีโครงสร้างหลักอยู่ในฟังก์ชัน `main()` เพียงจุดเดียว การใช้ `return` เมื่อผู้ใช้เลือกออกจึงเป็นการจบการทำงานของโปรแกรมอย่างสมบูรณ์และกระชับ
* **การแก้ไขจุดที่ 2 (การแก้ปัญหา Test Compilation Error):**
  * **การตัดสินใจ:** ทำการเพิ่มคำสั่ง `import org.example.*` ไว้ที่ส่วนหัวของไฟล์ [`src/test/kotlin/WorkshopTest.kt`](file:///C:/Users/Aukit-dev/Downloads/Kotlin-for-Server/src/test/kotlin/WorkshopTest.kt) เพื่อเชื่อมโยง package จาก `Workshop1.kt` ให้ไฟล์เทสเรียกใช้ฟังก์ชันได้อย่างถูกต้อง ส่งผลให้การรัน `./gradlew test` สำเร็จเรียบร้อย (`BUILD SUCCESSFUL`)

---

## 3. สิ่งที่ได้เรียนรู้ (Learnings)

1. **การทำงานของ Kotlin Type System และ Elvis Operator (`?:`)**:
  * ได้เรียนรู้ว่าคอมไพเลอร์ Kotlin จะหาชนิดข้อมูลร่วม (Common Supertype) ระหว่างฝั่งซ้ายและฝั่งขวาของ `?:`
  * การใส่ `return` ในบล็อกฝั่งขวาของ `?:` จะได้ประเภทข้อมูลเป็น **`Nothing`** ทำให้เมื่อหา Supertype ร่วมกับ `Double` (จากฝั่งซ้าย) ผลลัพธ์จะได้รับการ Smart Cast เป็น **`Double`** (Non-null) อย่างปลอดภัย
  * หากลบ `return` ออก คำสั่ง `println()` จะคืนค่าเป็น **`Unit`** ซึ่งมี Common Supertype ร่วมกับ `Double` กลายเป็น **`Any`** ทำให้คอมไพเลอร์แจ้งข้อผิดพลาดเมื่อนำตัวแปรไปคำนวณคณิตศาสตร์
2. **การเขียนฟังก์ชันแบบ Single-Expression**:
  * ได้เรียนรู้การลดรูปฟังก์ชันที่มีเพียงบรรทัดเดียวให้สั้นลงโดยใช้เครื่องหมาย `=` (เช่น `fun celsiusToFahrenheit(celsius: Double): Double = celsius * 9.0 / 5.0 + 32`) เพิ่มความเป็น Idiomatic Kotlin
3. **หลักการ Immutability และ Null Safety**:
  * ตระหนักถึงความสำคัญของการใช้ `val` เหนือ `var` เพื่อป้องกันการเปลี่ยนค่าตัวแปรโดยไม่จำเป็น และการใช้ `toDoubleOrNull()` เพื่อจัดการกับข้อผิดพลาดจาก User Input โดยไม่ต้องพึ่งพา Exception Handling

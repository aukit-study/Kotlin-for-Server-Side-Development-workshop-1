import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.*

class WorkshopTest {
    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        val celsiusInput = 0.0
        val expectedFahrenheit = 32.0

        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "0°C should be 32°F")
    }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        val celsiusInput = -10.0
        val expectedFahrenheit = 14.0

        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "-10°C should be 14°F")
    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        // แก้ไขชื่อตัวแปรและฟังก์ชันที่เรียกใช้ให้ตรงกับบริบทของการแปลงหน่วยกิโลเมตรเป็นไมล์
        val kilometersInput = 1.0
        val expectedMiles = 0.621371

        val actualMiles = kilometersToMiles(kilometersInput)

        assertEquals(expectedMiles, actualMiles, 0.001, "1 km should be 0.621371 miles")
    }

    // --- Tests for Workshop #1: Unit Converter End ---


    // --- Tests for Workshop #2: Data Analysis Pipeline ---

    // Test case สำหรับคำนวณผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    @Test
    fun `test calculateTotalElectronicsPriceOver500`() {
        // Arrange: สร้างข้อมูลจำลอง (Mock Data) สำหรับการทดสอบ
        val mockProducts = listOf(
            Product("Laptop", 35000.0, "Electronics"),
            Product("Keyboard", 499.0, "Electronics"), // ไม่เข้าเงื่อนไข (ราคา <= 500)
            Product("T-shirt", 450.0, "Apparel"),      // ไม่เข้าเงื่อนไข (คนละหมวดหมู่)
            Product("Monitor", 7500.0, "Electronics")
        )
        // ผลรวมที่คาดหวังคือ Laptop (35000) + Monitor (7500) = 42500.0
        val expectedTotal = 42500.0

        // Act: เรียกใช้ฟังก์ชัน
        val actualTotal = calculateTotalElectronicsPriceOver500(mockProducts)

        // Assert: ตรวจสอบความถูกต้อง
        assertEquals(expectedTotal, actualTotal, 0.001, "Total electronics price over 500 should be 42500.0")
    }

    // Test case เช็คจำนวนสินค้าที่อยู่ในหมวด 'Electronics' และมีราคามากกว่า 500 บาท
    @Test
    fun `test countElectronicsOver500`() {
        // Arrange
        val mockProducts = listOf(
            Product("Laptop", 35000.0, "Electronics"),
            Product("Keyboard", 499.0, "Electronics"),
            Product("T-shirt", 450.0, "Apparel"),
            Product("Monitor", 7500.0, "Electronics")
        )
        // จำนวนที่คาดหวังคือ 2 ชิ้น (Laptop และ Monitor)
        val expectedCount = 2

        // Act: สมมุติว่าฟังก์ชันสำหรับการนับชื่อ countElectronicsOver500
        val actualCount = countElectronicsOver500(mockProducts)

        // Assert: กรณีคืนค่าเป็น Int จะใช้ assertEquals ที่ไม่ต้องระบุค่าความคลาดเคลื่อน (delta)
        assertEquals(expectedCount, actualCount, "Count of electronics over 500 should be 2")
    }


    // --- Tests for Workshop #2: Data Analysis Pipeline End ---
}
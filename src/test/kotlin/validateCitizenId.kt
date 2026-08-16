import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.example.CitizenId

class ValidateCitizenIdTest {

    // สร้าง Object จาก Class CitizenId เพื่อนำมาทดสอบ
    private val validator = CitizenId()

    @Test
    fun `test valid citizen id`() {
        // Arrange & Act & Assert
        // เลข 1234567890121 เป็นเลขสมมติที่คำนวณ Check Digit แล้วถูกต้อง (Check Digit = 1)
        assertTrue(validator.isValid("1234567890121"), "รหัสบัตรประชาชนนี้ควรผ่านการตรวจสอบ")
    }

    @Test
    fun `test invalid citizen id with wrong check digit`() {
        // เปลี่ยนหลักสุดท้ายเป็น 2 ซึ่งผิดสูตร
        assertFalse(validator.isValid("1234567890122"), "รหัสบัตรนี้เลข Check Digit ผิด ควร return false")
    }

    @Test
    fun `test citizen id is too short`() {
        // มีแค่ 12 หลัก
        assertFalse(validator.isValid("123456789012"), "รหัสบัตรสั้นเกินไป ควร return false")
    }

    @Test
    fun `test citizen id is too long`() {
        // มี 14 หลัก
        assertFalse(validator.isValid("12345678901213"), "รหัสบัตรยาวเกินไป ควร return false")
    }

    @Test
    fun `test citizen id contains characters`() {
        // มีตัวอักษรปนอยู่
        assertFalse(validator.isValid("12345A7890121"), "มีตัวอักษรผสมอยู่ ควร return false")
    }

    @Test
    fun `test citizen id is empty`() {
        // ค่าว่าง
        assertFalse(validator.isValid(""), "ค่าว่าง ควร return false")
    }

    // --- เพิ่มเติม Test Cases สำหรับ Edge Cases ตามหลัก TDD ---

    @Test
    fun `id with wrong checksum returns false`() {
        // หลักที่ 13 ต้องเป็น check digit ที่คำนวณจาก 12 หลักแรก
        // 110170018520 → check digit ที่ถูกต้องคือ 6
        assertFalse(validator.isValid("1101700185207"), "หลักสุดท้ายผิด (ใส่ 7 แต่ที่ถูกคือ 6) ควร return false")
        assertFalse(validator.isValid("1234567890129"), "หลักสุดท้ายผิด (ใส่ 9 แต่ที่ถูกคือ 1) ควร return false")

        // ใบที่ checksum ถูกต้อง ต้องยังผ่านอยู่
        assertTrue(validator.isValid("3509900547250"), "รหัสบัตรประชาชนที่ถูกต้องควรผ่านการตรวจสอบ")
        assertTrue(validator.isValid("1234567890121"), "รหัสบัตรประชาชนที่ถูกต้องควรผ่านการตรวจสอบ")
    }

    @Test
    fun `id with Thai number returns true`() {
        // กรณีใช้ตัวเลขไทย
        assertTrue(validator.isValid("๑๑๐๑๗๐๐๑๘๕๒๐๖"), "ระบบควรรองรับตัวเลขไทยและตรวจสอบผ่านได้")
    }
}
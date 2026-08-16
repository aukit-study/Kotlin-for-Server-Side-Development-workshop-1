package org.example

class CitizenId {

    fun isValid(citizenId: String): Boolean {
        // 1. แปลงเลขไทย (ถ้ามี) ให้กลายเป็นเลขอารบิก
        val normalizedId = citizenId.map { char ->
            when (char) {
                in '๐'..'๙' -> (char - '๐').toString()
                else -> char.toString()
            }
        }.joinToString("")

        // 2. ตรวจสอบความยาว และต้องเป็นตัวเลขทั้งหมด
        if (normalizedId.length != 13 || !normalizedId.all { it.isDigit() }) {
            return false
        }

        // 3. คำนวณ Checksum จาก 12 หลักแรก
        var sum = 0
        for (i in 0 until 12) {
            val digit = normalizedId[i].digitToInt()
            val weight = 13 - i
            sum += digit * weight
        }

        // 4. คำนวณหา Check Digit และเทียบกับหลักที่ 13
        val checkDigit = (11 - (sum % 11)) % 10
        val lastDigit = normalizedId[12].digitToInt()

        return checkDigit == lastDigit
    }
}
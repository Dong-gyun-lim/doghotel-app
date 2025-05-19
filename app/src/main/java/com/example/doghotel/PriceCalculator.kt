package com.example.doghotel

object PriceCalculator {
    fun computePrice(
        hotelName: String,
        dogWeight: Double,
        isWeekend: Boolean,
        isNeutered: Boolean
    ): Int {
        val rules = rulesFor(hotelName) ?: return 35000
        val rule = rules.firstOrNull { dogWeight <= it.maxWeight } ?: rules.last()
        var base = if (isWeekend) rule.weekendPrice else rule.weekdayPrice
        if (!isNeutered) base += 5000
        return base
    }

    private fun rulesFor(hotelName: String): List<PriceRule>? {
        return when (hotelName) {
            "냠냠 강아지 호텔" -> listOf(
                PriceRule(5.0, 20000, 25000),
                PriceRule(10.0, 30000, 35000),
                PriceRule(Double.MAX_VALUE, 40000, 45000)
            )
            "펑펑 강아지 호텔" -> listOf(
                PriceRule(5.0, 25000, 28000),
                PriceRule(10.0, 35000, 40000),
                PriceRule(Double.MAX_VALUE, 45000, 50000)
            )
            "룽룽 강아지 호텔" -> listOf(
                PriceRule(5.0, 22000, 27000),
                PriceRule(10.0, 30000, 35000),
                PriceRule(Double.MAX_VALUE, 38000, 43000)
            )
            "멍스타 펫텔" -> listOf(
                PriceRule(6.0, 25000, 30000),
                PriceRule(12.0, 35000, 40000),
                PriceRule(Double.MAX_VALUE, 45000, 50000)
            )
            "댕댕하우스" -> listOf(
                PriceRule(4.0, 24000, 29000),
                PriceRule(9.0, 33000, 38000),
                PriceRule(Double.MAX_VALUE, 42000, 47000)
            )
            "애견빌리지" -> listOf(
                PriceRule(3.0, 20000, 25000),
                PriceRule(6.0, 27000, 32000),
                PriceRule(12.0, 33000, 38000),
                PriceRule(Double.MAX_VALUE, 40000, 45000)
            )
            "멍푸드 호텔" -> listOf(
                PriceRule(6.0, 30000, 35000),
                PriceRule(10.0, 40000, 45000),
                PriceRule(Double.MAX_VALUE, 50000, 55000)
            )
            "강쥐랜드" -> listOf(
                PriceRule(8.0, 28000, 33000),
                PriceRule(Double.MAX_VALUE, 38000, 43000)
            )
            "소소한 멍라이프" -> listOf(
                PriceRule(4.0, 27000, 32000),
                PriceRule(10.0, 37000, 42000),
                PriceRule(Double.MAX_VALUE, 47000, 52000)
            )
            "위글위글 호텔" -> listOf(
                PriceRule(5.0, 26000, 31000),
                PriceRule(10.0, 36000, 41000),
                PriceRule(Double.MAX_VALUE, 46000, 51000)
            )
            else -> null
        }
    }
}

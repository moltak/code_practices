package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42578?language=kotlin
 * start 15:20
 * end 20:53
 */
class HashSpyTest {

    @Test fun test01() {
        val r = Solution().solution(
            arrayOf(
                arrayOf("yellow_hat", "headgear"),
                arrayOf("blue_sunglasses", "eyewear"),
                arrayOf("green_turban", "headgear")
            )
        )

        assertThat(r).isEqualTo(5)
    }

    @Test fun test02() {
        val r = Solution().solution(
            arrayOf(
                arrayOf("crow_mask", "face"),
                arrayOf("blue_sunglasses", "face"),
                arrayOf("smoky_makeup", "face")
            )
        )

        assertThat(r).isEqualTo(3)
    }

    @Test fun test03() {
        val r = Solution().solution(
            arrayOf(
                arrayOf("blue_sunglasses", "eyewear"),

                arrayOf("yellow_hat", "headgear"),
                arrayOf("green_turban", "headgear"),

                arrayOf("crow_mask", "face"),
                arrayOf("smoky_makeup", "face")
            )
        )

        assertThat(r).isEqualTo(17)
    }

    @Test fun test04() {
        val r = Solution().solution(
            arrayOf(
                arrayOf("yellow_hat", "headgear"),
                arrayOf("green_turban", "headgear"),
                arrayOf("green_turban2", "headgear"),

                arrayOf("blue_sunglasses", "eyewear"),
                arrayOf("blue_sunglasses2", "eyewear"),

                arrayOf("smoky_makeup", "face")
            )
        )

        assertThat(r).isEqualTo(23)
    }

    class Solution {
        fun solution(clothes: Array<Array<String>>): Int {
            val grouped = clothes.map { Pair(it[0], it[1]) }.groupBy { it.second }.map { Pair(it.key, it.value.size) }

            return grouped.map { it.second }.fold(1) { acc, i -> acc * (i + 1) } - 1
        }
    }
}
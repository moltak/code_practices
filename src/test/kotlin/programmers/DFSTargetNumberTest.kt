package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43165?language=kotlin
 * start 13:35
 * end 15:09
 */
class DFSTargetNumberTest {

    @Test fun test01() {
        val r = Solution().solution(intArrayOf(1, 1, 1, 1, 1), 3)

        assertThat(r).isEqualTo(5)
    }

    @Test fun test02() {
        val r = Solution().solution(intArrayOf(1, 2, 3), 4)

        assertThat(r).isEqualTo(1)
    }

    @Test fun test03() {
        val r = Solution().solution(intArrayOf(1, 2), 3)

        assertThat(r).isEqualTo(1)
    }

    class Solution {
        private val total = ArrayList<Int>()

        fun solution(numbers: IntArray, target: Int): Int {
            powerSet(numbers.toList(), 0)

            return total.count { it == target }
        }

        private fun powerSet(list: List<Int>, current: Int) {
            if (current == list.size) {
                total.add(list.reduce { acc, i -> acc + i })

                return
            }

            powerSet(list, current + 1)
            powerSet(list.mapIndexed { index, i -> if (index == current) -i else i }, current + 1)
        }
    }
}
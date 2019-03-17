package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SortingHIndexTest {
    @Test fun test01() {
        val r = Solution().solution(intArrayOf(3, 0, 6, 1, 5))

        assertThat(r).isEqualTo(3)
    }

    @Test fun test02() {
        val r = Solution().solution(intArrayOf(1, 2, 3, 3, 3, 3, 4, 4, 5, 6, 7, 7, 8, 8, 9, 9, 10, 10, 10))

        assertThat(r).isEqualTo(7)
    }

    @Test fun test03() {
        val r = Solution().solution(intArrayOf(3, 5, 15, 50, 10, 25, 20))

        assertThat(r).isEqualTo(5)
    }

    class Solution {
        fun solution(citations: IntArray): Int {
            val citationsList = citations.sortedByDescending { it }

            return citationsList.indexOf(citationsList.filterIndexed { index, citation -> citation < index + 1 }.first())
        }
    }
}
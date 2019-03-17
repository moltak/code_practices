package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42840?language=kotlin
 * start 14:45
 * end 15:02
 */
class SearchMathTest {

    @Test fun test01() {
        val r = Solution().solution(intArrayOf(1, 2, 3, 4, 5))

        assertThat(r[0]).isEqualTo(1)
        assertThat(r.size).isEqualTo(1)
    }

    @Test fun test02() {
        val r = Solution().solution(intArrayOf(1, 3, 2, 4, 2))

        assertThat(r[0]).isEqualTo(1)
        assertThat(r[1]).isEqualTo(2)
        assertThat(r[2]).isEqualTo(3)
    }

    class Solution {
        fun solution(answers: IntArray): IntArray {

            val studentA = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
            val studentB = listOf(2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5)
            val studentC = listOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

            val r = listOf(
                Pair(1, answers.filterIndexed { index, i -> studentA[index % studentA.size] == i }.count()),
                Pair(2, answers.filterIndexed { index, i -> studentB[index % studentB.size] == i }.count()),
                Pair(3, answers.filterIndexed { index, i -> studentC[index % studentC.size] == i }.count())
            )
                .sortedByDescending { it.second }

            return when {
                r[0].second == r[1].second && r[1].second == r[2].second -> intArrayOf(r[0].first, r[1].first, r[2].first)
                r[0].second == r[1].second -> intArrayOf(r[0].first, r[1].first)
                else -> intArrayOf(r[0].first)
            }
        }
    }
}
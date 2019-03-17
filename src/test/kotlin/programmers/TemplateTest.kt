package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42840?language=kotlin
 * start 14:45
 * end
 */
class TemplateTest {

    @Test fun test01() {
        val r = Solution().solution(intArrayOf(1, 2, 3, 4, 5))

        assertThat(r[0]).isEqualTo(1)
    }

    @Test fun test02() {
        val r = Solution().solution(intArrayOf(1, 3, 2, 4, 2))

        assertThat(r[0]).isEqualTo(1)
        assertThat(r[1]).isEqualTo(1)
        assertThat(r[2]).isEqualTo(1)
    }

    class Solution {
        fun solution(answers: IntArray): IntArray {
            var answer = intArrayOf()
            return answer
        }
    }
}
package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

// https://programmers.co.kr/learn/courses/30/lessons/42748?language=kotlin
// starting 13:55
// end 14:07
class SortingKNumberTest {

    @Test fun test01() {
        val r = Solution().solution(
            intArrayOf(1, 5, 2, 6, 3, 7, 4),
            arrayOf(
                intArrayOf(2, 5, 3),
                intArrayOf(4, 4, 1),
                intArrayOf(1, 7, 3)
            )
        )

        assertThat(r[0]).isEqualTo(5)
        assertThat(r[1]).isEqualTo(6)
        assertThat(r[2]).isEqualTo(3)
    }

    class Solution {
        fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
            return commands.map { command ->
                array.slice(IntRange(command[0] - 1, command[1] - 1)).sorted()[command[2] - 1]
            }
                .toIntArray()
        }
    }
}
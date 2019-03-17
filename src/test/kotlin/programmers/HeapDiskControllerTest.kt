package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42840?language=kotlin
 * start 16:30
 * end
 */
class HeapDiskControllerTest {

    @Test fun test01() {
        val r = Solution().solution(
            arrayOf(
                intArrayOf(0, 3),
                intArrayOf(1, 9),
                intArrayOf(2, 6)
            )
        )

        assertThat(r).isEqualTo(9)
    }

    @Test fun test02() {
        val r = Solution().solution(
            arrayOf(
                intArrayOf(0, 9),
                intArrayOf(0, 4),
                intArrayOf(0, 5),
                intArrayOf(0, 7),
                intArrayOf(0, 3)
            )
        )

        assertThat(r).isEqualTo(13)
    }

    class Solution {
        fun solution(jobs: Array<IntArray>): Int {

            val jobsList = jobs.toList().map { Pair(it[0], it[1]) }.sortedBy { it.second }

            val queue = ArrayList<Pair<Int, Int>>(jobsList.size)
            jobsList.forEach { queue.add(Pair(it.first, it.second)) }

            var time = 0

            val reduced = arrayListOf<Pair<Int, Int>>()

            while (queue.isNotEmpty()) {
                val value = queue.first()
                queue.removeAt(0)

                if (reduced.isEmpty()) {
                    reduced.add(Pair(value.first, value.second))
                } else {
                    reduced.add(Pair(value.first, reduced.last().first + reduced.last().second + value.second - value.first))
                }

                time += value.second
            }

            return reduced.fold(0) { acc, i -> acc + i.second }.div(jobs.size)
        }
    }
}
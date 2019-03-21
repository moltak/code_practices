package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43162?language=kotlin
 * start 15:14
 * end GG
 */
class DFSNetworkTest {

    @Test fun test01() {
        val r = Solution().solution(3, arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1)))

        assertThat(r).isEqualTo(2)
    }

    @Test fun test02() {
        val r = Solution().solution(3, arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1)))

        assertThat(r).isEqualTo(1)
    }

    @Test fun test03() {
        val r = Solution().solution(4, arrayOf(intArrayOf(1, 1, 0, 0), intArrayOf(1, 1, 0, 0), intArrayOf(0, 0, 1, 0), intArrayOf(0, 0, 0, 1)))

        assertThat(r).isEqualTo(3)
    }

    @Test fun test04() {
        val r = Solution().solution(4, arrayOf(intArrayOf(1, 0, 0, 0), intArrayOf(0, 1, 0, 0), intArrayOf(0, 0, 1, 0), intArrayOf(0, 0, 0, 1)))

        assertThat(r).isEqualTo(4)
    }

    class Solution {
        private val result = arrayListOf<MutableSet<Int>>()

        fun solution(n: Int, computers: Array<IntArray>): Int {
            dfs(computers.map { it.toList() }.toList(), n - 1)

            return result.size
        }

        private fun dfs(p: List<List<Int>>, n: Int) {

            for (i in IntRange(0, n)) {
                val temp = p[i]

                temp.forEachIndexed { j, value ->
                    if (value == 1) {
                        val set = result.find { set -> set.find { it == i } != null || set.find { it == j } != null }

                        if (set == null) {
                            val newSet = mutableSetOf<Int>()
                            newSet.add(i)
                            newSet.add(j)
                            result.add(newSet)
                        } else {
                            set.add(i)
                            set.add(j)
                        }
                    }
                }
            }
        }
    }
}
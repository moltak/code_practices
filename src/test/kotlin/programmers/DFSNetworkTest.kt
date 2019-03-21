package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43162?language=kotlin
 * start 15:14
 * end
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

    class Solution {
        private val r = arrayListOf<MutableSet<Int>>()
        private var count = 0

        fun solution(n: Int, computers: Array<IntArray>): Int {
            count = n

            dfu(computers, 0, 0)

            return r.size
        }

        private fun dfu(p: Array<IntArray>, i: Int, j: Int) {
            if (i == count - 1 && j == count - 1) return

            if (p[i][j] == 1) {
                val set = r.find { it.contains(i) || it.contains(j) }
                if (set == null) {
                    val newSet = mutableSetOf<Int>()
                    newSet.add(i)
                    newSet.add(j)
                    r.add(newSet)
                } else {
                    set.add(i)
                    set.add(j)
                }
            }

            dfu(p, i + 1, j)
            dfu(p, i, j + 1)
        }
    }
}
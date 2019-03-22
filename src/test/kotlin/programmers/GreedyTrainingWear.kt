package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42862?language=kotlin
 * start 08:46
 * end 09:46
 *  level 1이라고 했는데 너무 어려웠음 -_-
 *  설명글이 길면 대체로 어려운거 같다. 조건을 이해하기 어렵고 빈틈이 생기는데 메꾸기가 힘듬.
 */
class GreedyTrainingWear {

    @Test fun test01() {
        val r = Solution().solution(5, intArrayOf(2, 4), intArrayOf(1, 3, 5))

        assertThat(r).isEqualTo(5)
    }

    @Test fun test02() {
        val r = Solution().solution(5, intArrayOf(2, 4), intArrayOf(3))

        assertThat(r).isEqualTo(4)
    }

    @Test fun test03() {
        val r = Solution().solution(3, intArrayOf(3), intArrayOf(1))

        assertThat(r).isEqualTo(2)
    }

    @Test fun test04() {
        val r = Solution().solution(5, intArrayOf(2, 4), intArrayOf(2))

        assertThat(r).isEqualTo(4)
    }

    @Test fun test05() {
        val r = Solution().solution(5, intArrayOf(3, 4), intArrayOf(4, 5))

        assertThat(r).isEqualTo(4)
    }

    @Test fun test06() {
        val r = Solution().solution(6, intArrayOf(1, 3, 4, 5), intArrayOf(1, 4))

        assertThat(r).isEqualTo(4)
    }

    class Solution {
        fun solution(n: Int, loses: IntArray, reserve: IntArray): Int {
            val reserveList = arrayListOf<Int>()

            val filteredReserve = arrayListOf<Int>().apply { addAll(reserve.toList().filter { !loses.contains(it) }) }
            val filteredLoses = loses.toList().filter { !reserve.contains(it) }

            filteredLoses.forEach { lost ->
                val prev = filteredReserve.find { it == lost -1 }
                val next = filteredReserve.find { it == lost + 1 }

                val found = if (prev != null) {
                    prev
                } else {
                    next
                }

                found?.let {
                    reserveList.add(it)
                    filteredReserve.remove(it)
                }
            }

            return n - filteredLoses.size + reserveList.size
        }
    }
}
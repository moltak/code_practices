package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42628?language=kotlin
 * start 08:30
 * end 08:44
 */
class HeapDoubleQueueTest {

    @Test fun test01() {
        val r = Solution().solution(arrayOf("I 16", "D 1"))

        assertThat(r[0]).isEqualTo(0)
        assertThat(r[1]).isEqualTo(0)
    }

    @Test fun test02() {
        val r = Solution().solution(arrayOf("I 7", "I 5", "I -5", "D -1"))

        assertThat(r[0]).isEqualTo(7)
        assertThat(r[1]).isEqualTo(5)
    }

    @Test fun test03() {
        val r = Solution().solution(arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"))

        assertThat(r[0]).isEqualTo(333)
        assertThat(r[1]).isEqualTo(-45)
    }

    class Solution {
        fun solution(operations: Array<String>): IntArray {
            val queue = arrayListOf<Int>()

            operations
                .map { it.split(" ") }
                .map { Pair(it[0], it[1].toInt()) }
                .forEach { operation ->
                    when (operation.first) {
                        "I" -> { queue.add(operation.second) }
                        "D" -> {
                            val value = if (operation.second == 1) {
                                queue.max()
                            } else {
                                queue.min()
                            }

                            value?.let { queue.remove(it) }
                        }
                    }

                    Unit
                }

            return if (queue.isEmpty()) {
                intArrayOf(0, 0)
            } else {
                intArrayOf(queue.max()!!, queue.min()!!)
            }
        }
    }
}
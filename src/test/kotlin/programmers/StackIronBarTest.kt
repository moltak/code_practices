package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.lang.StringBuilder
import java.util.*

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42585?language=kotlin
 * start 21:
 * end 22:37
 */
class StackIronBarTest {

    @Test fun test01() {
        val r = Solution().solution("()(((()())(())()))(())")

        assertThat(r).isEqualTo(17)
    }

    @Test fun test02() {
        val r = Solution().solution("((()))")

        assertThat(r).isEqualTo(4)
    }

    class Solution {
        fun solution(s: String): Int {
            val stack = arrayListOf<Int>()
            val array = s.toCharArray()

            var sum = 0
            var index = 0

            while (index != s.lastIndex) {
                if (array[index] == '(' && array[index + 1] == ')') {
                    stack.map { it + 1 }
                        .let {
                            stack.clear()
                            stack.addAll(it)
                        }

                    index ++
                } else {
                    when (array[index]) {
                        '(' -> { stack.add(1) }
                        ')' -> {
                            sum += stack[stack.lastIndex]
                            stack.removeAt(stack.lastIndex)
                        }
                    }
                }

                index ++
            }

            return sum + stack.reduce { acc, i -> acc + i }
        }
    }
}
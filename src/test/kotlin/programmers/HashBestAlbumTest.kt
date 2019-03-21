package programmers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

// https://programmers.co.kr/learn/courses/30/lessons/42579?language=kotlin
class HashBestAlbumTest {
    @Test fun test01() {
        val r = Solution().solution(
            arrayOf("classic", "pop", "classic", "classic", "pop"),
            intArrayOf(500, 600, 150, 800, 2500)
        )

        assertThat(r[0]).isEqualTo(4)
        assertThat(r[1]).isEqualTo(1)
        assertThat(r[2]).isEqualTo(3)
        assertThat(r[3]).isEqualTo(0)
    }

    @Test fun test02() {
        val r = Solution().solution(
            arrayOf   ("classic", "pop", "classic", "classic", "pop", "jazz"),
            intArrayOf( 500,       600,   150,       800,       100,   2500)
        )

        assertThat(r[0]).isEqualTo(5)
        assertThat(r[1]).isEqualTo(3)
        assertThat(r[2]).isEqualTo(0)
        assertThat(r[3]).isEqualTo(1)
        assertThat(r[4]).isEqualTo(4)
    }

    @Test fun test03() {
        val r = Solution().solution(
            arrayOf   ("classic", "pop", "classic", "pop", "classic", "classic"),
            intArrayOf( 400,       600,   150,       2500,  500,       500)
        )

        assertThat(r[0]).isEqualTo(3)
        assertThat(r[1]).isEqualTo(1)
        assertThat(r[2]).isEqualTo(4)
        assertThat(r[3]).isEqualTo(5)
    }

    @Test fun test04() {
        val r = Solution().solution(
            arrayOf   ("classic", "pop", "jazz"),
            intArrayOf( 400,       600,   150)
        )

        assertThat(r[0]).isEqualTo(1)
        assertThat(r[1]).isEqualTo(0)
        assertThat(r[2]).isEqualTo(2)
    }

    @Test fun test05() {
        val r = Solution().solution(
            arrayOf   ("classic", "pop", "jazz"),
            intArrayOf( 400,       600,   150)
        )

        assertThat(r[0]).isEqualTo(1)
        assertThat(r[1]).isEqualTo(0)
        assertThat(r[2]).isEqualTo(2)
    }

    @Test fun test06() {
        val r = Solution().solution(
            arrayOf   ("classic", "jazz", "classic", "pop", "classic", "classic"),
            intArrayOf( 500,       600,    150,       2500,  500,       500)
        )

        assertThat(r[0]).isEqualTo(3)
        assertThat(r[1]).isEqualTo(0)
        assertThat(r[2]).isEqualTo(4)
        assertThat(r[3]).isEqualTo(1)
    }

    class Solution {
        fun solution(genres: Array<String>, plays: IntArray): IntArray {
            return genres.indices.groupBy { genres[it] }
                .toList()
                .sortedByDescending { it.second.sumBy { plays[it] } }
                .map { it.second.sortedByDescending { plays[it] }.take(2) }
                .flatten()
                .toIntArray()
        }

        fun solution2(genres: Array<String>, plays: IntArray): IntArray {
            val genresList = genres.toList()
            val playsList = plays.toList()

            val summed = genresList.zip(playsList)
                .fold(hashMapOf<String, Int>()) { acc, pair ->
                    if (acc.containsKey(pair.first)) {
                        acc[pair.first] = pair.second + acc[pair.first]!!
                    } else {
                        acc[pair.first] = pair.second
                    }

                    acc
                }
                .toList()
                .sortedByDescending { it.second }

            val zipped = genresList
                .zip(playsList)
                .mapIndexed { index, pair ->  Triple(pair.first, pair.second, index) }

            val answer = summed.flatMap { _sum -> zipped.filter { _zipped -> _zipped.first == _sum.first }
                .asSequence()
                .toList()
                .sortedByDescending { it.second }
                .map { it.third }
                .take(2)
            }

            return answer.toIntArray()
        }
    }
}
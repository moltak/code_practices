package rx

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

/**
 * https://stackoverflow.com/questions/54836801/how-to-keep-track-of-the-number-of-emits-in-flowable
 *
 * EventStream 에서 하나의 subscriber는 take 1 flowable 로 처리하고 나머지 하나의 subscriber 는 일반적인 작업을 하고 싶다고했음.
 * share operator 로 stream을 multicast 하면 될 것 같음.
 */
fun main() {
    val o = Flowable.fromArray(1, 2, 3, 4, 5)
        .map {
            println("heavy operation")
            it + it
        }
        .share() // publish the changes
        .subscribeOn(Schedulers.computation())

    o.take(1).subscribe { println("Special work: $it") } // take one

    o.subscribe { println("Normal work: $it") }

    o.test().await() // waiting to end
}
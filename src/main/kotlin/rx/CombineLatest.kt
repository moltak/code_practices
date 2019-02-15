package rx

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {

    Observable.combineLatest(
        Observable.just("o1"),
        Observable.just("o2"),
        BiFunction<String, String, String> { _, _ ->
            Thread.currentThread().name
        }
    )
        .subscribe {
            println("Without subscribeOn")
            println("in combineLatest: $it")
            println("in subscribe: ${Thread.currentThread().name}")
        }

    println()

    Observable.combineLatest(
        Observable.just("o1"),
        Observable.just("o2"),
        BiFunction<String, String, String> { _, _ ->
            Thread.currentThread().name
        }
    )
        .subscribeOn(Schedulers.io())
        .subscribe {
            println("With subscribeOn")
            println("in combineLatest: $it")
            println("in subscribe: ${Thread.currentThread().name}")

        }

    Thread.sleep(500)
}
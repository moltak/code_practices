package rx

import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers


fun main() {

    Observables.combineLatest(
        Observable.just("o1"),
        Observable.just("o2")
    ) { _, _ -> Thread.currentThread().name }
        .subscribe {
            println("Without subscribeOn")
            println("in combineLatest: $it")
            println("in subscribe: ${Thread.currentThread().name}")
        }

    println()

    Observables.combineLatest(
        Observable.just("o1"),
        Observable.just("o2")
    ) { _, _ -> Thread.currentThread().name }
        .subscribeOn(Schedulers.io())
        .subscribe {
            println("With subscribeOn")
            println("in combineLatest: $it")
            println("in subscribe: ${Thread.currentThread().name}")

        }

    Thread.sleep(100)
}
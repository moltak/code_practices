package rx

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


/**
 * Share operator 가 제대로 동작하지 않는다는 질문 https://stackoverflow.com/questions/54742299/why-is-rxjava2-share-operator-not-multicasting/54796121#54796121
 *
 * 문제는 thread 였음. 좀 헤멨는데 그래도 원인을 잘 밝혀내서 다행!
 */
fun main() {
    code1()
    code2()
}

fun code1() {
    val ob1 = Observable.fromArray(1,2,3,4,5).map {
        println("expensive operation")
        it * 2
    }.share()

    fun doMultiplyBy2() {
        val ob2 = ob1.share()

        ob2.flatMap { Observable.just(" 1st subscriber: $it;") }.subscribe{println(it)}

        ob2.flatMap { Observable.just(" 2nd subscriber: $it;") }.subscribe{println(it)}

        ob2.share()
    }

    doMultiplyBy2()
}

fun code2() {
    val ob1 = Observable.fromArray(1,2,3,4,5).map {
        println("expensive operation")
        it * 2
    }.subscribeOn(Schedulers.computation()).share()

    fun doMultiplyBy2() {
        ob1.flatMap { Observable.just(" 1st subscriber: $it;") }.subscribe{println(it)}

        ob1.flatMap { Observable.just(" 2nd subscriber: $it;") }.subscribe{println(it)}
    }

    doMultiplyBy2()

    Thread.sleep(1000)
}
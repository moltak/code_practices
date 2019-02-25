package rx

import io.reactivex.Observable

// https://duckduckgo.com/?q=java+stream+map&atb=v123-3__&ia=web

fun update(query: String): Observable<String> {
    return Observable.just(query)
}

fun main() {
    val o = IntRange(0, 10).map {
        update("QUERY EXECUTION $it")
    }

    Observable
        .fromIterable(o)
        .flatMap { it }
        .subscribe {
            println(it)
        }
}
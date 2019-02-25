package rx

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.TimeUnit

// https://pamartinezandres.com/rxjava-2-exponential-backoff-retry-only-when-internet-is-available-5a46188ab175

class RetryWhenTest {
    @Test fun `retry 3 times`() {
        val o = Observable.range(0, 10)
            .share()
            .doOnNext { if (it == 2) throw IllegalAccessError("ERROR!!!!!!") }
            .retryWhen { errors: Observable<Throwable> ->
                errors.zipWith(
                    Observable.range(1, 3),
                    BiFunction<Throwable, Int, Int> { error, retryCount ->
                        retryCount
                    }
                ).flatMap { retryCount: Int ->
                    Observable.timer(
                        Math.pow(2.toDouble(), retryCount.toDouble()).toLong(),
                        TimeUnit.SECONDS
                    )
                }
            }
            .subscribeOn(Schedulers.io())

        o.subscribe({ data ->
            println(data)
        }, { error ->
            error.printStackTrace()
        })

        o.test().await()
    }
}
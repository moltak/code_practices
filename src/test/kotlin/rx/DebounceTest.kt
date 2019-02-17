package rx

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class DebounceTest {
    private lateinit var searchSubject: PublishSubject<String>

    @Mock lateinit var apiService: ApiService

    @Mock lateinit var presenter: Presenter

    private lateinit var disposable: Disposable

    private lateinit var ioTestScheduler: TestScheduler
    private lateinit var uiTestScheduler: TestScheduler

    @Before fun setUp() {
        searchSubject = PublishSubject.create<String>()

        ioTestScheduler = TestScheduler()
        uiTestScheduler = TestScheduler()

        setupSearch(uiTestScheduler, ioTestScheduler)

        // important https://stackoverflow.com/a/53543257/1355048
        ioTestScheduler.triggerActions()
    }


    @Test fun searchBillsTest() {
        whenever(apiService.search("America")).thenReturn(Observable.just("MOCK RESULT"))


        searchSubject.onNext("America")


        ioTestScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        uiTestScheduler.triggerActions()


        verify(presenter).doSomething("MOCK RESULT")
    }


    private fun setupSearch(uiScheduler: Scheduler, ioScheduler: Scheduler) {
        disposable = searchSubject.debounce(1, TimeUnit.SECONDS, ioScheduler)
            .distinctUntilChanged()
            .switchMap { apiService.search(it) }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe { response ->
                presenter.doSomething(response)
            }
    }

    interface ApiService {
        fun search(query: String): Observable<String>
    }

    interface Presenter {
        fun doSomething(result: String)
    }
}
package rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultipleExecutes {
    public class DbClient {
        Observable<String> update(String someObject) {
            // replace what you want.
            // Observable.fromCallable() <- consider this
            return Observable.just(someObject);
        }
    }

    // client code to call update method
    private List<Observable<String>> processUpdate(Map<String, String> map) {
        DbClient dbClient = new DbClient();

        return map
                .entrySet()
                .stream()
                .map(entry -> dbClient.update(entry.getValue()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        List<Observable<String>> o = new MultipleExecutes().processUpdate(map);
        Observable
                .fromIterable(o)
                .flatMap(it -> it.subscribeOn(Schedulers.computation()))
                .subscribe(System.out::println);

        Thread.sleep(500);
    }
}


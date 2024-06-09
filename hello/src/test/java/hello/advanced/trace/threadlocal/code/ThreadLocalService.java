package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, threadLocal.get());
        threadLocal.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", threadLocal.get());
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

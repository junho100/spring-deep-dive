package hello.aop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(hello.aop.order.aop.AspectV3.class)
public class AopTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService: {}", org.springframework.aop.support.AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository: {}", org.springframework.aop.support.AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception(){
        assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

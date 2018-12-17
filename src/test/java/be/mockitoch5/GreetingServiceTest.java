package test.java.mockitoch5;

import main.java.mockitoch5.GreetingService;
import main.java.mockitoch5.Hello;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class GreetingServiceTest {
    private GreetingService greeting;
    private Hello helloMock;

    @BeforeEach
    public void beforeTest() {
        helloMock = mock(Hello.class);
        greeting = new GreetingService(helloMock);
    }

    @Test
    public void testGreet() {
        //prepare mock
        when(helloMock.sayHello("World").thenReturn("Hello World"));
        //execute test
        String answer = greeting.greet("World");
        assertEquals("Greeting message: Hello World",answer);
    }


}

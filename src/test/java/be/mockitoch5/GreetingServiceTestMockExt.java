package be.mockitoch5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        when(helloMock.sayHello("World")).thenReturn("Hello World");
        //execute test
        String answer = greeting.greet("World");
        assertEquals("Greeting message: Hello World",answer);
    }




}

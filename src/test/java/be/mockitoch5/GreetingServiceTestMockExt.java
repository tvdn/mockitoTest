package be.mockitoch5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GreetingServiceTestMockExt {

    @Mock
    private Hello helloMock;
    @InjectMocks
    private GreetingService greeting;

    @Test
    public void testGreet() {
        //prepare mock
        when(helloMock.sayHello("World")).thenReturn("Hello World");
        //execute test
        String answer = greeting.greet("World");
        assertEquals("Greeting message: Hello World",answer);
    }

    @Test
    public void testGreetNull() {
        //prepare mock
        when(helloMock.sayHello(null)).thenThrow(new NullPointerException());
        //execute test
        assertThrows(NullPointerException.class, () -> greeting.greet(null));
    }

    @Test
    public void testGreetAnswer(){
        //prepare mock
//        when(helloMock.sayHello("World")).then(new Answer<String>(){
//            public String answer(InvocationOnMock invocation) {
//                return "Hello " + invocation.getArgument(0);
//            }
//        });
        when(helloMock.sayHello("World")).then(inv -> "Hello " + inv.getArgument(0));
        //execute test
        String answer = greeting.greet("World");
        assertEquals("Greeting message: Hello World", answer);
    }

    @Test
    public void testGreetMultiple() {
        //prepare
        when(helloMock.sayHello("World")).thenReturn("Hello World", "Goodbye World");
        //exec
        String answer = greeting.greet("World");
        assertEquals("Greeting message: Hello World", answer);
        answer = greeting.greet("World");
        assertEquals("Greeting message: Goodbye World", answer);

    }

    @Test
    public void testGreetMatcher() {
        //prep
        when(helloMock.sayHello(ArgumentMatchers.any())).then(new Answer<String>() {
            public String answer(InvocationOnMock invocation) {
                String arg = invocation.getArgument(0);
                return "Hello " + arg;
            }
        });
        //exec
        String answer = greeting.greet("Homer");
        assertEquals("Greeting message: Hello Homer", answer);
    }

    @Test
    public void testGreetVoid() {
        //prep
        doThrow(NullPointerException.class).when(helloMock).sayHello(null);

        //exec
        //greeting.greet(null);
        assertThrows(NullPointerException.class, ()->greeting.greet(null));

    }




}

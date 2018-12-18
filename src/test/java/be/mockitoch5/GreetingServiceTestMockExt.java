package be.mockitoch5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    public void testGreetVerifyTime() {
        //prep
        when(helloMock.sayHello("World")).thenReturn("Hello World");
        //exec
        greeting.greet("World");
        //verify mock
        verify(helloMock, timeout(10)).sayHello("World");
    }

    @Test
    public void testGreetVerifyTimes() {
        //prep
        when(helloMock.sayHello("World")).thenReturn("Hello World");
        //exec
        greeting.greet("World");
        greeting.greet("World");
        greeting.greet("boo");
        //verify
        verify(helloMock,times(2)).sayHello("World");
        verify(helloMock,times(1)).sayHello("boo");
    }

    @Test
    public void testGreetVerifyOrder(){
        //prep
        when(helloMock.sayHello("World")).thenReturn("Hello World");
        //exec
        greeting.greet("World");
        greeting.greet("Mars");
        //veri
        InOrder inorder = inOrder(helloMock);
        inorder.verify(helloMock).sayHello("World");
        inorder.verify(helloMock).sayHello("Mars");
    }

    @Test
    public void testGreetVerifyArguments() {
      when(helloMock.sayHello(any())).then(invocationOnMock -> "Hello " + invocationOnMock.getArgument(0));
      greeting.greet("Moon");
      verify(helloMock).sayHello(any());
    }




}

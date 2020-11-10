package com.wang.mockito.lesson07;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @description: Arguments Matchers
 * @author: wei·man cui
 * @date: 2020/8/13 16:56
 */
@RunWith(MockitoJUnitRunner.class)
public class ArgumentsMatcherTest {

    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
        // 等价 when(list.get(eq(0))).thenReturn(100);
        when(list.get(0)).thenReturn(100);
        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }

    /*
    isA, any 的不同
    isA 必须是自类或当前类
    any 可以是同一个父类下
    eq 基本类型
    */
    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        // Mockito.when(foo.function(Mockito.isA(Parent.class))).thenReturn(100);
        when(foo.function(Mockito.isA(Child1.class))).thenReturn(100);
        int result = foo.function(new Child1());
        assertThat(result, equalTo(100));

        result = foo.function(new Child2());
        assertThat(result, equalTo(0));

        Mockito.reset(foo);

        when(foo.function(Mockito.any(Child1.class))).thenReturn(100);
        result = foo.function(new Child2());
        assertThat(result, equalTo(100));
    }

    static class Foo {
        int function(Parent p) {
            return p.work();
        }
    }

    interface Parent {
        int work();
    }

    class Child1 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child2 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }



    @Test
    public void argumentMatchers(){
        LinkedList mockedList = mock(LinkedList.class);
        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
//        when(mockedList.contains(argThat(isValid()))).thenReturn(true);

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        //argument matchers can also be written as Java 8 Lambdas
//        verify(mockedList).add(argThat(someString -> someString.length() > 5));
    }
}

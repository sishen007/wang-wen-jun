package com.wang.mockito.lesson06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @description: Mockito Spying
 * @author: wei·man cui
 * @date: 2020/8/13 15:46
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    /**
     * Spy：用于部分方法的 Mock(模拟),其他方法调取真实方法
     */
    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = Mockito.spy(realList);

        list.add("Mockito");
        list.add("PowerMock");
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(false));

        // Stubbing 语法 模拟 list 的返回内容
        Mockito.when(list.isEmpty()).thenReturn(true);
        Mockito.when(list.size()).thenReturn(0);

        // get方法真实方法,isEmpty() 和 size() 是假的方法
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));
    }

}

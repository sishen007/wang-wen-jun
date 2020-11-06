package com.wang.mockito.lesson03.mockmethod;

import com.wang.mockito.common.Account;
import com.wang.mockito.common.AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Matchers.anyString;

/**
 * @description: Rule方式 进行Mock
 * @author: wei·man cui
 * @date: 2020/8/13 11:26
 */
public class MockByRuleTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    //    @Mock(answer = Answers.CALLS_REAL_METHODS) // 调用真是方法
    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private AccountDao accountDao;

    @Test
    public void tetMock() {
        // AccountDao accountDao = Mockito.mock(AccountDao.class, Mockito.CALLS_REAL_METHODS);
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }

    @Test
    public void tetMockStub() {
        // 测试打桩
        Mockito.when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }


}

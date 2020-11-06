package com.wang.mockito.quickstart;

import com.wang.mockito.common.Account;
import com.wang.mockito.common.AccountDao;
import com.wang.mockito.common.AccountLoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Lesson02： Mockito快速入门 Demo
 *
 * AccountLoginController Mockito 单元测试
 *
 * @author wangyh2
 * @since 2020/11/6 10:37
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountLoginControllerTest {

    @Mock
    AccountDao accountDao;

    private HttpServletRequest request;

    private AccountLoginController accountLoginController;

    @Before
    public void setUp() {
        // 对象的两种mock方式(1,注解方式  2, 调用Mockito.mock方法进行生成mock对象)
//        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginController = new AccountLoginController(accountDao);
    }

    @Test
    public void login() {
        Account account = new Account();
        // Mockito.when == when  // 静态方法写法
        when(request.getParameter("userName")).thenReturn("Sun");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);
        // 断言使用 hamcrest
        assertThat(accountLoginController.login(request), equalTo("/index"));
    }

    @Test
    public void testLoginFailure() {
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
        assertThat(accountLoginController.login(request), equalTo("/login"));
    }

    @Test
    public void login505() {
        when(request.getParameter("userName")).thenReturn("Sun");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);
        assertThat(accountLoginController.login(request), equalTo("/505"));
    }
}








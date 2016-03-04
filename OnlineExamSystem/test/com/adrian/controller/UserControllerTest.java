package com.adrian.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adrian.command.BasePagination;
import com.adrian.common.*;
import com.adrian.model.User;
import com.adrian.service.UserService;
import com.adrian.type.RequestResultType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml", "classpath:config/springMVC.xml" })
public class UserControllerTest extends AbstractJUnit4SpringContextTests {
    @Resource(name = "userController")
    private UserController userController;
    private UserService userService;

    @BeforeClass
    public static void setUpClass() {
        HttpServletRequest request = new MockHttpServletRequest();
        RequestContext.setContextAttribute(Constants.REQUEST_CONTEXT_SESSION, request.getSession());
    }

    @Before
    public void setUp() {
        userService = EasyMock.createMock(UserService.class);
        userController.setUserService(userService);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGetCurrentUserInfo() {
        User testData = new User();
        userController.setSessionAttribute(Constants.SESSION_USER, testData);
        User user = userController.getCurrentUserInfo();
        assertNotNull(user);
        userController.setSessionAttribute(Constants.SESSION_USER, null);
    }

    @Test
    public void testCreateUserSuccess() {
        User user = new User();
        EasyMock.expect(userService.addUser(user)).andReturn(5);
        EasyMock.replay(userService);
        ResponseMessage message = userController.createUser(user, null);
        assertEquals(RequestResultType.SUCCESS, message.getCode());
        assertEquals(5, message.getMessage());
        EasyMock.verify(userService);
    }

    @Test
    public void testCreateUserFail() {
        User user = null;
        EasyMock.expect(userService.addUser(user)).andReturn(-1);
        EasyMock.replay(userService);
        ResponseMessage message = userController.createUser(user, null);
        assertEquals(RequestResultType.FAILED, message.getCode());
        EasyMock.verify(userService);
    }

    @Test
    public void testDeleteUsersSuccess() {
        int[] ids = new int[] { 1, 2, 3 };
        EasyMock.expect(userService.deleteUsers(ids)).andReturn(true);
        EasyMock.replay(userService);
        ResponseMessage message = userController.deleteUsers(ids);
        assertEquals(message.getCode(), RequestResultType.SUCCESS);
        EasyMock.verify(userService);
    }

    @Test
    public void testQueryUsersSuccess() {
        BasePagination pagination = new BasePagination();
        pagination.setOffset(0);
        pagination.setPerpageCount(5);
        pagination.setSortColumn("id");
        pagination.setSortMethod(0);
        EasyMock.expect(userService.getUsers(pagination)).andReturn(new ArrayList<User>());
        EasyMock.replay(userService);
        Map<String, Object> result = userController.queryUsers(pagination);
        assertNotNull(result.get("list"));
        EasyMock.verify(userService);
    }

    @Test
    public void testUpdateUserSuccess() {
        User user = new User();
        EasyMock.expect(userService.updateUser(user)).andReturn(true);
        EasyMock.replay(userService);
        ResponseMessage responseMessage = userController.updateUser(user, null);
        assertEquals(RequestResultType.SUCCESS, responseMessage.getCode());
        EasyMock.verify(userService);
    }

    @Test
    public void testUpdateUserFailed() {
        User user = new User();
        EasyMock.expect(userService.updateUser(user)).andReturn(false);
        EasyMock.replay(userService);
        ResponseMessage responseMessage = userController.updateUser(user, null);
        assertEquals(RequestResultType.FAILED, responseMessage.getCode());
        EasyMock.verify(userService);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        EasyMock.expect(userService.getUserById(1)).andReturn(user);
        EasyMock.replay(userService);
        User result = userController.getUserById(1);
        assertNotNull(result);
    }
}

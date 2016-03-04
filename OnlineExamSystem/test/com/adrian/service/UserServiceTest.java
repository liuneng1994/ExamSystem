package com.adrian.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adrian.command.BasePagination;
import com.adrian.model.User;
import com.adrian.type.Gender;
import com.adrian.type.RoleType;
import com.adrian.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml", "classpath:config/springMVC.xml" })
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        this.userService = (UserService) this.applicationContext.getBean("userService");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoginHappy() {
        String userName = "root";
        String password = StringUtil.EncoderByMd5("123456");
        User user = this.userService.login(userName, password);
        assertNotNull(user);
    }

    @Test
    public void testLoginWhenUserNameOrPasswordIsError() {
        String userName = "user";
        String password = StringUtil.EncoderByMd5("123456");
        User user = this.userService.login(userName, password);
        assertTrue(user == null);
    }

    @Test
    public void testLoginWhenUserNameIsNull() {
        String userName = null;
        String password = StringUtil.EncoderByMd5("123456");
        User user = this.userService.login(userName, password);
        assertTrue(user == null);
    }

    @Test
    public void testLoginWhenPasswordIsNull() {
        String userName = "root";
        String password = null;
        User user = this.userService.login(userName, password);
        assertTrue(user == null);
    }

    @Test
    public void testAddUserServiceSuccess() {
        User user = new User();
        user.setUserName("TESTSETSETSETSET");
        user.setChineseName("≤‚ ‘");
        user.setGender(Gender.MALE.getCode());
        user.setRoleType(RoleType.SYS_ADMIN.getCode());
        user.setPassword(StringUtil.EncoderByMd5("123456"));
        int result = userService.addUser(user);
        assertTrue(result != -1);
    }

    @Test
    public void testAddUserServiceRepeat() {
        User user = new User();
        user.setUserName("root");
        user.setChineseName("≤‚ ‘");
        user.setGender(Gender.MALE.getCode());
        user.setRoleType(RoleType.SYS_ADMIN.getCode());
        user.setPassword(StringUtil.EncoderByMd5("123456"));
        int result = userService.addUser(user);
        assertTrue(result == -1);
    }

    @Test
    public void testAddUserServiceWhenInfoIsNotCompleted() {
        User user = new User();
        user.setChineseName("≤‚ ‘");
        user.setRoleType(RoleType.SYS_ADMIN.getCode());
        user.setPassword(StringUtil.EncoderByMd5("123456"));
        int result = userService.addUser(user);
        assertTrue(result == -1);
    }

    @Test
    public void testAddUserServiceWhenUserIsNull() {
        User user = null;
        int result = userService.addUser(user);
        assertTrue(result == -1);
    }

    @Test
    public void testDeleteUsers() {
        User user = new User();
        user.setUserName("TESTSETSETSETSET");
        user.setChineseName("≤‚ ‘");
        user.setGender(Gender.MALE.getCode());
        user.setRoleType(RoleType.SYS_ADMIN.getCode());
        user.setPassword(StringUtil.EncoderByMd5("123456"));
        userService.addUser(user);
        userService.deleteUsers(new int[] { user.getId() });
        User result = userService.login(user.getUserName(), user.getPassword());
        assertTrue(result == null);
    }

    @Test
    public void testGetUsers() {
        BasePagination pagination = new BasePagination();
        pagination.setOffset(0);
        pagination.setPerpageCount(5);
        pagination.setSortColumn("id");
        pagination.setSortMethod(0);
        List<User> list = userService.getUsers(pagination);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetUsersWhenPaginationIllegal() {
        BasePagination pagination = new BasePagination();
        pagination.setOffset(0);
        pagination.setPerpageCount(5);
        pagination.setSortMethod(0);
        List<User> list = userService.getUsers(pagination);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testUpdateUserWhenUserIsLegal() {
        User user = new User();
        user.setUserName("@TEST");
        user.setPassword(StringUtil.EncoderByMd5("123456"));
        user.setChineseName("≤‚ ‘");
        user.setGender(Gender.MALE.getCode());
        user.setRoleType(RoleType.SYS_ADMIN.getCode());

        int id = userService.addUser(user);
        user.setPassword(StringUtil.EncoderByMd5("123"));
        user.setId(id);
        userService.updateUser(user);

        User result = userService.login("@TEST", StringUtil.EncoderByMd5("123"));
        assertNotNull(result);
    }

    @Test(expected = IllegalStateException.class)
    public void testUpdateUserWhenUserIsIllegal() {
        User user = new User();
        user.setPassword(StringUtil.EncoderByMd5("123456"));
        user.setChineseName("≤‚ ‘");
        user.setGender(Gender.MALE.getCode());
        user.setRoleType(RoleType.SYS_ADMIN.getCode());

        userService.addUser(user);
        user.setPassword(StringUtil.EncoderByMd5("123"));
        userService.updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWhenUserIsNull() {
        User user = null;
        userService.updateUser(user);
    }

    @Test
    public void testGetUserById() {
        User result = userService.getUserById(1);
        assertNotNull(result);
    }
}

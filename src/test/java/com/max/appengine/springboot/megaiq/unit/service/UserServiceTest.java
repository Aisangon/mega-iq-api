/*
 * Copyright 2018 mega-iq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.max.appengine.springboot.megaiq.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.max.appengine.springboot.megaiq.Application;
import com.max.appengine.springboot.megaiq.model.User;
import com.max.appengine.springboot.megaiq.model.enums.Locale;
import com.max.appengine.springboot.megaiq.repository.UserReporitory;
import com.max.appengine.springboot.megaiq.service.UserService;
import com.max.appengine.springboot.megaiq.unit.AbstractUnitTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest extends AbstractUnitTest {
  private static final String USER_PASSWORD = "test";
  private static final String USER_PASSWORD_HASH = "098f6bcd4621d373cade4e832627b4f6";

  @Autowired
  private UserReporitory userReporitory;

  private UserService userService;

  private User testUserPublic;

  @Before
  public void doSetup() {
    this.userService = new UserService(userReporitory, null);

    testUserPublic = new User("test" + UUID.randomUUID() + "@test.email", "test", "url", "pic",
        "city", 40, 150, true,
        USER_PASSWORD_HASH, "ip", 0, Locale.EN);
    testUserPublic = userReporitory.save(testUserPublic);

    Date dateNow = new Date();
    Date dateExpire = new Date(dateNow.getTime() + (1000 * 60 * 60 * 24 * 7));
//    testTokenAccess =
//        new UserToken(testUserPublic.getId(), UserTokenType.ACCESS, UUID.randomUUID().toString(),
//            dateNow, dateExpire);
//    userTokenReporitory.save(testTokenAccess);
//
//    testTokenForget =
//        new UserToken(testUserPublic.getId(), UserTokenType.FORGET, UUID.randomUUID().toString(),
//            dateNow, dateExpire);
//    userTokenReporitory.save(testTokenForget);
  }

//  @After
//  public void doFinish() {
//    userTokenReporitory.delete(testTokenAccess);
//    userTokenReporitory.delete(testTokenForget);
//    userReporitory.delete(testUserPublic);
//  }
//
//  @Test
//  public void testUserServiceBasis() {
//    Optional<User> userResult = this.userService.getUserById(testUserPublic.getId());
//    assertTrue(userResult.isPresent());
//    assertEquals(testUserPublic, userResult.get());
//
//    userResult = this.userService.getUserByToken(testTokenAccess.getValue(), UserTokenType.ACCESS);
//    assertTrue(userResult.isPresent());
//    assertEquals(testUserPublic, userResult.get());
//
//    userResult = this.userService.getUserByToken(testTokenForget.getValue(), UserTokenType.ACCESS);
//    assertFalse(userResult.isPresent());
//  }
//
//  @Test
//  public void testUserAuth() {
//    Optional<User> userResult =
//        this.userService.authUserLogin(testUserPublic.getEmail(), USER_PASSWORD);
//    assertTrue(userResult.isPresent());
//    assertEquals(testUserPublic, userResult.get());
//    assertEquals(testTokenAccess, userResult.get().getUserTokenByType(UserTokenType.ACCESS));
//    assertEquals(testTokenForget, userResult.get().getUserTokenByType(UserTokenType.FORGET));
//
//    userResult = this.userService.authUserLogin(testUserPublic.getEmail(), USER_PASSWORD + "123");
//    assertFalse(userResult.isPresent());
//  }
//
//  @Test
//  public void testUserAuthUser2() {
//    User testUser2 = new User("test2@test.email", "test", "url", "pic", "city", 40, 150, true,
//        USER_PASSWORD_HASH, "ip", 0, Locale.EN);
//    userReporitory.save(testUser2);
//
//    Optional<User> userResult = this.userService.authUserLogin(testUser2.getEmail(), USER_PASSWORD);
//    assertTrue(userResult.isPresent());
//    assertEquals(testUser2, userResult.get());
//    assertEquals(testUser2.getId(),
//        userResult.get().getUserTokenByType(UserTokenType.ACCESS).get().getUserId());
//    assertNull(userResult.get().getUserTokenByType(UserTokenType.FORGET));
//
//    userTokenReporitory.delete(userResult.get().getUserToken().get());
//    userReporitory.delete(testUser2);
//  }
//  
//  @Test
//  public void testUserRegister() {
//    User user = new User();
//    user.setEmail("test@sometestemail.com");
//    
//    Optional<User> userResult = this.userService.addUser(user);
//    assertTrue(userResult.isPresent());
//    user.setTokenList(userResult.get().getTokenList());
//    assertEquals(user, userResult.get());
//  }
}

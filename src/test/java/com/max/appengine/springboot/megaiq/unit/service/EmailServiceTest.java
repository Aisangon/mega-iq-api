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

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.max.appengine.springboot.megaiq.Application;
import com.max.appengine.springboot.megaiq.model.QuestionGroupsResult;
import com.max.appengine.springboot.megaiq.model.QuestionUser;
import com.max.appengine.springboot.megaiq.model.TestResult;
import com.max.appengine.springboot.megaiq.model.User;
import com.max.appengine.springboot.megaiq.model.enums.IqTestStatus;
import com.max.appengine.springboot.megaiq.model.enums.IqTestType;
import com.max.appengine.springboot.megaiq.model.enums.Locale;
import com.max.appengine.springboot.megaiq.service.EmailService;
import com.max.appengine.springboot.megaiq.unit.AbstractUnitTest;
import mockit.Mock;
import mockit.MockUp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmailServiceTest extends AbstractUnitTest {
  private User user;

  private EmailService emailService;

  @Before
  public void doSetup() {
    user = generateUser();

    new MockUp<EmailService>() {
      @Mock
      protected boolean sendEmail(String to, String subject, String content) {
        return true;
      }
    };

    emailService = new EmailService();
  }

  @Test
  public void testEmailNewUserRegistration() {
    boolean result = this.emailService.sendEmailRegistration(user);
    assertTrue(result);
  }

  @Test
  public void testEmailRegistrationWithVerify() {
    boolean result = this.emailService.sendEmailRegistrationWithVerify(user, "http://mega-iq.com");
    assertTrue(result);
  }

  @Test
  public void testEmailVerify() {
    boolean result = this.emailService.sendEmailVerify(user, "http://mega-iq.com");
    assertTrue(result);
  }

  @Test
  public void testEmailForget() {
    boolean result = this.emailService.sendEmailForget(user, "http://mega-iq.com");
    assertTrue(result);
  }

  @Test
  public void testEmailDirectLogin() {
    boolean result = this.emailService.sendEmailDirectLogin(user);
    assertTrue(result);
  }

  @Test
  public void testSendTestResult() {
    UUID code = UUID.randomUUID();
    TestResult testUserResultFinished = new TestResult(1, code, "/iqtest/result/" + code,
        user.getId(), IqTestType.MEGA_IQ, IqTestStatus.FINISHED, new Date(), new Date(), new Date(),
        150, new QuestionGroupsResult(1, 1, 1, 1, 1), Locale.EN);

    testUserResultFinished.setQuestionSet(new ArrayList<QuestionUser>());
    
    boolean result = this.emailService.sendTestResult(user, testUserResultFinished);
    assertTrue(result);
  }
}

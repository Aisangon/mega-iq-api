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

package com.max.appengine.springboot.megaiq.service;

import com.max.appengine.springboot.megaiq.model.QuestionGroupsResult;
import com.max.appengine.springboot.megaiq.model.enums.IqQuestionGroup;
import com.max.appengine.springboot.megaiq.model.enums.IqTestStatus;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.max.appengine.springboot.megaiq.model.Question;
import com.max.appengine.springboot.megaiq.model.QuestionUser;
import com.max.appengine.springboot.megaiq.model.TestResult;
import com.max.appengine.springboot.megaiq.model.User;
import com.max.appengine.springboot.megaiq.model.enums.IqTestType;
import com.max.appengine.springboot.megaiq.model.enums.Locale;
import com.max.appengine.springboot.megaiq.repository.QuestionUserRepository;
import com.max.appengine.springboot.megaiq.repository.TestResultReporitory;
import com.max.appengine.springboot.megaiq.repository.UserReporitory;

@Service
public class TestResultService {
  public static final Integer RESULT_EXPIRE_MINUTES = 24 * 60;

  public static final Integer RESULT_EXPIRE_MINUTES_STANDART = 60;

  public static final Integer RESULT_EXPIRE_MINUTES_MEGA_IQ = 2 * 60;

  private static final Logger log = LoggerFactory.getLogger(TestResultService.class);

  private final UserReporitory userReporitory;

  private final TestResultReporitory testResultReporitory;

  private final QuestionUserRepository questionUserRepository;

  @Autowired
  public TestResultService(UserReporitory userReporitory, TestResultReporitory testResultReporitory,
      QuestionUserRepository questionUserRepository) {
    this.userReporitory = userReporitory;
    this.testResultReporitory = testResultReporitory;
    this.questionUserRepository = questionUserRepository;
  }

  public long getResultCount() {
    return testResultReporitory.count();
  }

  public void saveUserResults(User user) {
    testResultReporitory.saveAll(user.getTestResultList());
  }

  public Optional<TestResult> getTestResultById(Integer testId) {
    Optional<TestResult> testResult = testResultReporitory.findById(testId);

    if (testResult.isPresent()) {
      TestResult testResultDetails = loadTestDetails(testResult.get());

      return Optional.of(testResultDetails);
    }

    return testResult;
  }

  public Optional<TestResult> getTestResultByCode(UUID code, Locale locale) {
    Optional<TestResult> testResult = testResultReporitory.findByCodeAndLocale(code, locale);

    if (testResult.isPresent()) {
      TestResult testResultDetails = loadTestDetails(testResult.get());

      return Optional.of(testResultDetails);
    }

    return testResult;
  }

  public TestResult submitUserAnswer(TestResult testResult, Integer questionId,
      Integer answerUser) {

    try {
      testResult.getQuestionSet().get(questionId - 1).setAnswerUser(answerUser);
      testResult.getQuestionSet().get(questionId - 1).setUpdateDate(new Date());
    } catch (IndexOutOfBoundsException e) {
      return testResult;
    }

    questionUserRepository.saveAll(testResult.getQuestionSet());

    testResult.setUpdateDate(new Date());

    TestResult testResultDb = testResultReporitory.save(testResult);
    testResultDb.setQuestionSet(testResult.getQuestionSet());
    testResultDb.setUser(testResult.getUser());
    return testResultDb;
  }

  public Optional<TestResult> submitFinish(TestResult testResult) {
    boolean allDone = true;
    for (QuestionUser question : testResult.getQuestionSet()) {
      if (question.getAnswerUser() == null) {
        allDone = false;
      }
    }

    if (!allDone) {
      return Optional.empty();
    }
    
    if (testResult.getType().equals(IqTestType.STANDART_IQ)
        || testResult.getType().equals(IqTestType.MEGA_IQ)) {
      QuestionGroupsResult questionGroupsResult = new QuestionGroupsResult(testResult.getId(), 1, 1, 1, 1);
      QuestionGroupsResult questionGroupsCorrect = new QuestionGroupsResult(testResult.getId(), 1, 1, 1, 1);
      Integer points = 80;

      Integer pointsTotal = 0, pointsCorrect = 0;
      for (QuestionUser question : testResult.getQuestionSet()) {
        boolean isCorrect = question.getAnswerCorrect().equals(question.getAnswerUser());
        if (isCorrect) {
          pointsCorrect += question.getPoints();
        }
        pointsTotal += question.getPoints();
        
        for (IqQuestionGroup type : question.getGroups()) {
          switch (type) {
            case MATH:
              if (isCorrect) {
                questionGroupsCorrect.setMath(questionGroupsResult.getMath() + question.getPoints());
              }
              questionGroupsResult.setMath(questionGroupsResult.getMath() + question.getPoints());
              break;
            case GRAMMAR:
              if (isCorrect) {
                questionGroupsCorrect.setGrammar(questionGroupsResult.getGrammar() + question.getPoints());
              }
              questionGroupsResult.setGrammar(questionGroupsResult.getGrammar() + question.getPoints());
              break;
            case HORIZONS:
              if (isCorrect) {
                questionGroupsCorrect.setHorizons(questionGroupsResult.getHorizons() + question.getPoints());
              }
              questionGroupsResult.setHorizons(questionGroupsResult.getHorizons() + question.getPoints());
              break;
            case LOGIC:
              if (isCorrect) {
                questionGroupsCorrect.setLogic(questionGroupsResult.getLogic() + question.getPoints());
              }
              questionGroupsResult.setLogic(questionGroupsResult.getLogic() + question.getPoints());
              break;
          }
        }
        
      }
      points = Math.round(pointsCorrect / pointsTotal * 90);
      
      questionGroupsResult.setMath(questionGroupsCorrect.getMath() / questionGroupsResult.getMath() * 100);
      questionGroupsResult.setGrammar(questionGroupsCorrect.getGrammar() / questionGroupsResult.getGrammar() * 100);
      questionGroupsResult.setHorizons(questionGroupsCorrect.getHorizons() / questionGroupsResult.getHorizons() * 100);
      questionGroupsResult.setLogic(questionGroupsCorrect.getLogic() / questionGroupsResult.getLogic() * 100);
      
      testResult.setGroupsGraph(questionGroupsResult);
      testResult.setPoints(points);
    }

    testResult.setStatus(IqTestStatus.FINISHED);
    testResult.setFinishDate(new Date());
    testResult.setUpdateDate(new Date());

    TestResult testResultDb = testResultReporitory.save(testResult);
    testResultDb.setQuestionSet(testResult.getQuestionSet());
    testResultDb.setUser(testResult.getUser());
    return Optional.of(testResultDb);
  }

  public Optional<TestResult> startUserTest(User user, IqTestType testType,
      List<Question> questions, Locale locale) {
    TestResult testResult = new TestResult(user.getId(), testType, locale);
    testResult.newQuestionSet(questions);
    testResult.setUser(user);
    TestResult testResultDb = testResultReporitory.save(testResult);
    log.info("New test created = {}", testResult);

    for (QuestionUser question : testResult.getQuestionSet()) {
      question.setTestId(testResultDb.getId());
    }
    questionUserRepository.saveAll(testResult.getQuestionSet());

    return getTestResultById(testResultDb.getId());
  }

  public List<TestResult> findByUserId(Integer userId, Locale locale) {
    return testResultReporitory.findByUserIdAndLocaleOrderByCreateDateDesc(userId, locale);
  }

  public TestResult loadQuestions(TestResult testResult) {
    List<QuestionUser> questions =
        questionUserRepository.findByTestIdOrderByIdDesc(testResult.getId());

    if (!questions.isEmpty()) {
      testResult.setQuestionSet(questions);
    }
    return testResult;
  }

  public void expireTestResults() {
    expireByType(RESULT_EXPIRE_MINUTES, IqTestType.GRAMMAR);
    expireByType(RESULT_EXPIRE_MINUTES, IqTestType.MATH);
    expireByType(RESULT_EXPIRE_MINUTES, IqTestType.PRACTICE_IQ);
    expireByType(RESULT_EXPIRE_MINUTES_STANDART, IqTestType.STANDART_IQ);
    expireByType(RESULT_EXPIRE_MINUTES_MEGA_IQ, IqTestType.MEGA_IQ);
  }

  public void deleteTestResult(TestResult testResult) {
    testResultReporitory.delete(testResult);
  }

  private void expireByType(Integer minutes, IqTestType type) {
    Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.MINUTE, -1 * minutes);

    List<TestResult> testResultsList = testResultReporitory
        .findByCreateDateBeforeAndTypeAndStatus(c.getTime(), type, IqTestStatus.ACTIVE);
    if (!testResultsList.isEmpty()) {
      for (TestResult testResult : testResultsList) {
        testResult.setStatus(IqTestStatus.EXPIRED);
        testResult.setUpdateDate(new Date());
        testResultReporitory.save(testResult);
      }
    }
  }

  private TestResult loadTestDetails(TestResult testResult) {
    Optional<User> user = userReporitory.findById(testResult.getUserId());
    if (user.isPresent()) {
      testResult.setUser(user.get());
    }

    return loadQuestions(testResult);
  }

}

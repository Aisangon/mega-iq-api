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

package com.max.appengine.springboot.megaiq.unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.max.appengine.springboot.megaiq.model.Answer;
import com.max.appengine.springboot.megaiq.model.Question;
import com.max.appengine.springboot.megaiq.model.enums.IqQuestionGroup;
import com.max.appengine.springboot.megaiq.model.enums.Locale;
import com.max.appengine.springboot.megaiq.repository.AnswerReporitory;
import com.max.appengine.springboot.megaiq.repository.QuestionReporitory;

public abstract class AbstractUnitTest {

  protected static final Logger log = LoggerFactory.getLogger(AbstractUnitTest.class);

  @Rule
  public TestName name = new TestName();

  @Before
  public void printTestStart() {
    log.debug("UT {}.{}() started.", name.getClass(), name.getMethodName());
  }

  @After
  public void printTestEnd() {
    log.debug("UT {}.{}() Ends.", name.getClass(), name.getMethodName());
  }

  public static String asJsonString(final Object obj) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void generateQuestionsAndAnswers(QuestionReporitory questionReporitory,
      AnswerReporitory answerReporitory, Integer maxQuestions, Integer maxAnswers, Locale locale) {
    // TODO: fix this procedure to speed-up execution

    log.info("Will generate maxQuestions={}, maxAnswers={} for locale= {}", maxQuestions,
        maxAnswers, locale);

    int answerId = 1;
    int questionId = 1;
    if (locale.equals(Locale.DE)) {
      answerId = 100;
      questionId = 100;
    } else if (locale.equals(Locale.RU)) {
      answerId = 1000;
      questionId = 1000;
    }
    
    List<IqQuestionGroup> groups = Arrays.asList(IqQuestionGroup.values());
    ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<Answer> answers = new ArrayList<Answer>();

    for (int i = 1; i <= maxQuestions; i++) {
      // GENERATE_ANSWERS_LIMIT answers for each question
      // first answer is correct

      ArrayList<IqQuestionGroup> iqGroups = new ArrayList<IqQuestionGroup>();
      int iqGroupIndex = questionId % groups.size();
      iqGroups.add(groups.get(iqGroupIndex));
      iqGroupIndex = (questionId + 1) % groups.size();
      iqGroups.add(groups.get(iqGroupIndex));

      int questionPoint = questionId % maxAnswers + 1;

      questions.add(new Question(questionId, "pic", questionPoint, answerId,
          "test." + locale + " q" + i, "info", iqGroups, new Date(), new Date(), locale));

      for (int j = 1; j <= maxAnswers; j++) {
        answers.add(new Answer(answerId++, "test." + locale + " q" + i + "a" + j, questionId,
            new Date(), new Date(), locale));
      }

      questionId++;
    }

    log.info("Answers generated: {}", answers.size());
    log.info("Question generated: {}", questions.size());

    questionReporitory.saveAll(questions);
    answerReporitory.saveAll(answers);
  }

}

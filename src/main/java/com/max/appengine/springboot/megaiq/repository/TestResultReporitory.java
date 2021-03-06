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

package com.max.appengine.springboot.megaiq.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.max.appengine.springboot.megaiq.model.TestResult;
import com.max.appengine.springboot.megaiq.model.enums.IqTestStatus;
import com.max.appengine.springboot.megaiq.model.enums.IqTestType;
import com.max.appengine.springboot.megaiq.model.enums.Locale;

public interface TestResultReporitory extends JpaRepository<TestResult, Integer> {

  Optional<TestResult> findByCodeAndLocale(UUID code, Locale locale);

  List<TestResult> findTop8ByUserIdAndLocaleAndStatusOrderByCreateDateDesc(Integer userId,
      Locale locale, IqTestStatus status, Pageable pageable);

  List<TestResult> findTop8ByUserIdAndLocaleOrderByCreateDateDesc(Integer userId, Locale locale,
      Pageable pageable);

  List<TestResult> findByCreateDateBeforeAndTypeAndStatus(Date createDate, IqTestType type,
      IqTestStatus status);

  List<TestResult> findByLocaleAndStatusOrderByCreateDateDesc(Locale locale, IqTestStatus status,
      Pageable pageable);

  
  List<TestResult> findByUserIdAndStatus(Integer userId, IqTestStatus status);

  @Query("select userId, sum(points) as score from TestResult as t where t.locale = ?1 and status = 'FINISHED' group by userId order by score desc")
  List<Object[]> findTopUserIds(Locale locale,  Pageable pageable);
}

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

package com.max.appengine.springboot.megaiq.model;

import java.util.List;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.max.appengine.springboot.megaiq.model.enums.Locale;
import com.max.appengine.springboot.megaiq.model.enums.UserTokenType;
import com.max.appengine.springboot.megaiq.repository.UserTokenReporitory;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "locale"), @Index(columnList = "isPublic"),
    @Index(columnList = "iq")})
public class User extends AbstractUser {

  @Transient
  private List<TestResult> testResultList;

  @Transient
  private List<UserToken> tokenList;

  public UserToken generateToken(UserTokenReporitory tokenReporitory, UserTokenType type) {
    UserToken tokenNew = new UserToken(this.getId(), type);
    this.getTokenList().add(tokenNew);

    return tokenReporitory.save(tokenNew);
  }
  
  public List<TestResult> getTestResultList() {
    return testResultList;
  }

  public void setTestResultList(List<TestResult> testResultList) {
    this.testResultList = testResultList;
  }

  public Optional<UserToken> getUserToken() {
    return getUserTokenByType(UserTokenType.ACCESS);
  }

  public List<UserToken> getTokenList() {
    return tokenList;
  }

  public void setTokenList(List<UserToken> tokenList) {
    this.tokenList = tokenList;
  }

  public Optional<UserToken> getUserTokenByType(UserTokenType type) {
    if (this.tokenList == null || this.tokenList.isEmpty()) {
      return Optional.empty();
    }

    for (UserToken token : this.tokenList) {
      if (token.getType().equals(type)) {
        return Optional.of(token);
      }
    }

    return Optional.empty();
  }

  public boolean checkToken(UserTokenType type) {
    Optional<UserToken> token = getUserTokenByType(type);
    return token.isPresent();
  }

  public User() {
    super();
  }

  public User(String email, String name, String url, String pic, String location,
      Integer age, Integer iq, Boolean isPublic, String password, String ip, Integer geoId,
      Locale locale) {
    super();

    this.setEmail(email);
    this.setName(name);
    this.setUrl(url);
    this.setPic(pic);
    this.setLocation(location);
    this.setAge(age);
    this.setIq(iq);
    this.setIsPublic(isPublic);
    this.setPassword(password);
    this.setIp(ip);
    this.setGeoId(geoId);
    this.setLocale(locale);
  }

  @Override
  public String toString() {
    return "User [id=" + getId() + ", email=" + getEmail() + ", name=" + getName() + ", url="
        + getUrl() + ", pic=" + getPic() + ", location=" + getLocation() + ", age=" + getAge() + ", iq="
        + getIq() + ", isPublic=" + getIsPublic() + ", password=" + getPassword() + ", ip="
        + getIp() + ", geoId=" + getGeoId() + ", locale=" + getLocale() + "] Tokens="
        + getTokenList();
  }
}

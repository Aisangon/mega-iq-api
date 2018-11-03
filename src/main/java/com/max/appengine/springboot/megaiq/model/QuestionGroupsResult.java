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

import java.io.Serializable;

public class QuestionGroupsResult implements Serializable {
  private Integer math;
  private Integer logic;
  private Integer grammar;
  private Integer horizons;

  public Integer getMath() {
    return math;
  }

  public void setMath(Integer math) {
    this.math = math;
  }

  public Integer getLogic() {
    return logic;
  }

  public void setLogic(Integer logic) {
    this.logic = logic;
  }

  public Integer getGrammar() {
    return grammar;
  }

  public void setGrammar(Integer grammar) {
    this.grammar = grammar;
  }

  public Integer getHorizons() {
    return horizons;
  }

  public void setHorizons(Integer horizons) {
    this.horizons = horizons;
  }

  public QuestionGroupsResult(Integer math, Integer logic, Integer grammar, Integer horizons) {
    super();
    this.math = math;
    this.logic = logic;
    this.grammar = grammar;
    this.horizons = horizons;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((grammar == null) ? 0 : grammar.hashCode());
    result = prime * result + ((horizons == null) ? 0 : horizons.hashCode());
    result = prime * result + ((logic == null) ? 0 : logic.hashCode());
    result = prime * result + ((math == null) ? 0 : math.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    QuestionGroupsResult other = (QuestionGroupsResult) obj;
    if (grammar == null) {
      if (other.grammar != null)
        return false;
    } else if (!grammar.equals(other.grammar))
      return false;
    if (horizons == null) {
      if (other.horizons != null)
        return false;
    } else if (!horizons.equals(other.horizons))
      return false;
    if (logic == null) {
      if (other.logic != null)
        return false;
    } else if (!logic.equals(other.logic))
      return false;
    if (math == null) {
      if (other.math != null)
        return false;
    } else if (!math.equals(other.math))
      return false;
    return true;
  }
}

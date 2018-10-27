/*
 * Copyright 2018 mega-iq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.max.appengine.springboot.megaiq.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractIntegrationTest {

	private static final Logger log = LoggerFactory.getLogger(AbstractIntegrationTest.class);

	@Rule
	public TestName name = new TestName();

	@Before
	public void printTestStart() {
		log.debug("IT {}.{}() started.", name.getClass(), name.getMethodName());
	}

	@After
	public void printTestEnd() {
		log.debug("IT {}.{}() Ends.", name.getClass(), name.getMethodName());
	}
}
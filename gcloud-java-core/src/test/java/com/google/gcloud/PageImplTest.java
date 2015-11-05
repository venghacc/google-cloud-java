/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gcloud;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.Collections;

public class PageImplTest {

  @Test
  public void testPage() throws Exception {
    ImmutableList<String> values = ImmutableList.of("1", "2");
    final PageImpl<String> nextResult =
        new PageImpl<>(null, "c", Collections.<String>emptyList());
    PageImpl.NextPageFetcher<String> fetcher = new PageImpl.NextPageFetcher<String>() {

      @Override
      public PageImpl<String> nextPage() {
        return nextResult;
      }
    };
    PageImpl<String> result = new PageImpl<>(fetcher, "c", values);
    assertEquals(nextResult, result.nextPage());
    assertEquals("c", result.nextPageCursor());
    assertEquals(values, ImmutableList.copyOf(result.values().iterator()));
  }
}
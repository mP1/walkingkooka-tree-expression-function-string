/*
 * Copyright 2022 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.expression.function.string;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

public final class StringExpressionFunctionStringStringSubstringBeforeTest extends StringExpressionFunctionStringStringTestCase<StringExpressionFunctionStringStringSubstringBefore<ExpressionEvaluationContext>> {

    @Test
    public void testMissing() {
        this.applyAndCheck2(parameters("abcdef", "z"), "");
    }

    @Test
    public void testMissingWrongCase() {
        this.applyAndCheck2(parameters("abcdef", "A"), "");
    }

    @Test
    public void testPresent() {
        this.applyAndCheck2(parameters("abc", "c"), "ab");
    }

    @Test
    public void testPresent2() {
        this.applyAndCheck2(parameters("abcdef", "de"), "abc");
    }

    @Test
    public void testPresent3() {
        this.applyAndCheck2(parameters("abcdef", "bcde"), "a");
    }

    @Test
    public void testPresentFirst() {
        this.applyAndCheck2(parameters("abcd", "a"), "");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "substring-before");
    }

    @Override
    public StringExpressionFunctionStringStringSubstringBefore<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionStringStringSubstringBefore.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<StringExpressionFunctionStringStringSubstringBefore<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionStringStringSubstringBefore.class);
    }
}

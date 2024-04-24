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

public final class StringExpressionFunctionStringStringSubstringAfterTest extends StringExpressionFunctionStringStringTestCase<StringExpressionFunctionStringStringSubstringAfter<ExpressionEvaluationContext>> {

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
        this.applyAndCheck2(parameters("abc", "a"), "bc");
    }

    @Test
    public void testPresent2() {
        this.applyAndCheck2(parameters("abcde", "bc"), "de");
    }

    @Test
    public void testPresentLast() {
        this.applyAndCheck2(parameters("abcd", "d"), "");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "substringAfter");
    }

    @Override
    public StringExpressionFunctionStringStringSubstringAfter<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionStringStringSubstringAfter.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<StringExpressionFunctionStringStringSubstringAfter<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionStringStringSubstringAfter.class);
    }
}

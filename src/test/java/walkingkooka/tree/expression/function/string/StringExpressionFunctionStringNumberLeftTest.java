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

public final class StringExpressionFunctionStringNumberLeftTest extends StringExpressionFunctionStringNumberTestCase<StringExpressionFunctionStringNumberLeft<ExpressionEvaluationContext>> {

    @Test
    public void testString() {
        this.applyAndCheck3("abc123", "a");
    }

    @Test
    public void testStringWithNegative() {
        this.applyAndCheck3(" abc123", -1, "");
    }

    @Test
    public void testStringWithZero() {
        this.applyAndCheck3(" abc123", 0, "");
    }

    @Test
    public void testStringWithOne() {
        this.applyAndCheck3("abc123", 1, "a");
    }

    @Test
    public void testStringWithTwo() {
        this.applyAndCheck3("abc123", 2, "ab");
    }

    @Test
    public void testStringWithMax() {
        this.applyAndCheck3("abc123", 6, "abc123");
    }

    @Test
    public void testStringWithTooMany() {
        this.applyAndCheck3("abc123", 7, "abc123");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "left");
    }

    @Override
    public StringExpressionFunctionStringNumberLeft<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionStringNumberLeft.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<StringExpressionFunctionStringNumberLeft<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionStringNumberLeft.class);
    }
}

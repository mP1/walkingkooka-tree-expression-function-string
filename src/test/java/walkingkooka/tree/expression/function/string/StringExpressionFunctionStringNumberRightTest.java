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

public final class StringExpressionFunctionStringNumberRightTest extends StringExpressionFunctionStringNumberTestCase<StringExpressionFunctionStringNumberRight<ExpressionEvaluationContext>> {

    @Test
    public void testString() {
        this.applyAndCheck3("abcXYZ", "Z");
    }

    @Test
    public void testStringWithNegative() {
        this.applyAndCheck3(" abcXYZ", -1, "");
    }

    @Test
    public void testStringWithZero() {
        this.applyAndCheck3(" abcXYZ", 0, "");
    }

    @Test
    public void testStringWithOne() {
        this.applyAndCheck3("abcXYZ", 1, "Z");
    }

    @Test
    public void testStringWithTwo() {
        this.applyAndCheck3("abcXYZ", 2, "YZ");
    }

    @Test
    public void testStringWithFour() {
        this.applyAndCheck3("abcXYZ", 4, "cXYZ");
    }

    @Test
    public void testStringWithMax() {
        this.applyAndCheck3("abcXYZ", 6, "abcXYZ");
    }

    @Test
    public void testStringWithTooMany() {
        this.applyAndCheck3("abcXYZ", 7, "abcXYZ");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "right");
    }

    @Override
    public StringExpressionFunctionStringNumberRight<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionStringNumberRight.instance();
    }

    @Override
    public Class<StringExpressionFunctionStringNumberRight<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionStringNumberRight.class);
    }
}

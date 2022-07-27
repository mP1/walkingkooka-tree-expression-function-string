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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionTTest extends StringExpressionFunctionTestCase<StringExpressionFunctionT<ExpressionEvaluationContext>> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public void testBooleanTrue() {
        this.tAndCheck(
                true,
                ""
        );
    }

    @Test
    public void testBooleanFalse() {
        this.tAndCheck(
                false,
                ""
        );
    }

    @Test
    public void testNumber() {
        this.tAndCheck(
                KIND.create(123),
                ""
        );
    }

    @Test
    public void testEmptyString() {
        this.tAndCheck(
                ""
        );
    }

    @Test
    public void testNonEmptyString() {
        this.tAndCheck(
                "abc"
        );
    }

    private void tAndCheck(final String value) {
        this.tAndCheck(
                value,
                value
        );
    }

    private void tAndCheck(final Object value,
                           final String expected) {
        this.applyAndCheck2(
                Lists.of(value),
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "t"
        );
    }

    @Override
    public StringExpressionFunctionT<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionT.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<StringExpressionFunctionT<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionT.class);
    }
}

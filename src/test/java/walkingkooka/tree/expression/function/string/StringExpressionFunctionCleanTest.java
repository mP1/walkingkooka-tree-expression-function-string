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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionCleanTest extends StringExpressionFunctionTestCase<StringExpressionFunctionClean<ExpressionEvaluationContext>> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public void testAlreadyClean() {
        final String text = "abc123";

        assertSame(
            text,
            this.apply2(text)
        );
    }

    @Test
    public void testCleaned() {
        this.applyAndCheck2(
            parameters("\0abc\t123\nxyz "), "abc123xyz ");
    }

    @Test
    public void testString() {
        this.applyAndCheck2(parameters("abc123"), "abc123");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "clean");
    }

    @Override
    public StringExpressionFunctionClean<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionClean.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<StringExpressionFunctionClean<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionClean.class);
    }
}

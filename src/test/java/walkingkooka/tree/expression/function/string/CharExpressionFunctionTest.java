/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CharExpressionFunctionTest extends StringExpressionFunctionTestCase<CharExpressionFunction<ExpressionFunctionContext>, Character> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public final void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public final void testThreeParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2", "c3"));
    }

    @Test
    public final void testFourParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2", "c3", "d4"));
    }

    @Test
    public final void testNegativeValueFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2(-1));
    }

    @Test
    public final void testInvalidPositiveValueFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2(Character.MAX_VALUE + 1));
    }

    @Test
    public void testNumberFormattedString() {
        this.applyAndCheck3("65", 'A');
    }

    @Test
    public void testAsciiLetter() {
        this.applyAndCheck3((int) 'a', 'a');
    }

    private void applyAndCheck3(final Object value,
                                final char expected) {
        this.applyAndCheck2(parameters(value), expected);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "char");
    }

    @Override
    public CharExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return CharExpressionFunction.instance();
    }

    @Override
    public Class<CharExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(CharExpressionFunction.class);
    }
}

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

public final class UnicodeExpressionFunctionTest extends UnaryStringExpressionFunctionTestCase<UnicodeExpressionFunction<ExpressionFunctionContext>, Number> {

    @Test
    public void testEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2(""));
    }

    @Test
    public void testString() {
        this.applyAndCheck3("a", 'a');
    }

    @Test
    public void testString2() {
        this.applyAndCheck3("xyz", 'x');
    }

    @Test
    public void testNumber() {
        this.applyAndCheck3(123, '1');
    }

    @Test
    public void testBoolean() {
        this.applyAndCheck3(true, 't');
    }

    private void applyAndCheck3(final Object string,
                                final char expected) {
        this.applyAndCheck2(parameters(string), (int) expected);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "unicode");
    }

    @Override
    public UnicodeExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return UnicodeExpressionFunction.instance();
    }

    @Override
    public Class<UnicodeExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(UnicodeExpressionFunction.class);
    }
}

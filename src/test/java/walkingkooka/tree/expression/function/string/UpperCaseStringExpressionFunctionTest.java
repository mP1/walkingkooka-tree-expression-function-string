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
import walkingkooka.Either;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

import java.util.Locale;

public final class UpperCaseStringExpressionFunctionTest extends UnaryStringExpressionFunctionTestCase<UpperCaseStringExpressionFunction, String> {

    @Test
    public void testBoolean() {
        this.applyAndCheck2(parameters(true), "TRUE");
    }

    @Test
    public void testNumber() {
        this.applyAndCheck2(parameters(123), "123");
    }

    @Test
    public void testString() {
        this.applyAndCheck2(parameters("abc123\u00DF"), "ABC123SS");
    }

    @Test
    public void testStringUpperCased() {
        this.applyAndCheck2(parameters("ABC123\u00DF"), "ABC123SS");
    }

    @Test
    public void testStringMixed() {
        this.applyAndCheck2(parameters("abcDEF"), "ABCDEF");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "upper-case");
    }

    @Override
    public UpperCaseStringExpressionFunction createBiFunction() {
        return UpperCaseStringExpressionFunction.INSTANCE;
    }

    @Override
    public Class<UpperCaseStringExpressionFunction> type() {
        return UpperCaseStringExpressionFunction.class;
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return Cast.to(Either.left(value.toString()));
            }

            @Override
            public Locale locale() {
                return Locale.forLanguageTag("DE");
            }
        };
    }
}

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
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class NumberExpressionFunctionUnicodeTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionUnicode<ExpressionFunctionContext>> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testCharacter() {
        this.applyAndCheck(
                Lists.of('a'),
                this.createContext(),
                KIND.create((int) 'a')
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "unicode");
    }

    @Override
    public NumberExpressionFunctionUnicode<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionUnicode.instance();
    }

    @Override
    public Class<NumberExpressionFunctionUnicode<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionUnicode.class);
    }
}

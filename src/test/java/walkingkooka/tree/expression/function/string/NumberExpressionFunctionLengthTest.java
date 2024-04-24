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

public final class NumberExpressionFunctionLengthTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionLength<ExpressionEvaluationContext>> {

    @Test
    public void testEmptyString() {
        this.applyAndCheck(
                Lists.of(""),
                this.createContext(),
                KIND.zero()
        );
    }

    @Test
    public void testString() {
        final String text = "abc123";

        this.applyAndCheck(
                Lists.of(text),
                this.createContext(),
                KIND.create(text.length())
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "stringLength");
    }

    @Override
    public NumberExpressionFunctionLength<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionLength.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<NumberExpressionFunctionLength<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionLength.class);
    }
}

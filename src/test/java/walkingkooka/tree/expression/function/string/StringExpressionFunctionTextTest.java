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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionTextTest extends StringExpressionFunctionTestCase<StringExpressionFunctionText<ExpressionEvaluationContext>> {

    @Test
    public void testApplyZeroParametersFails() {
        assertThrows(
            IllegalArgumentException.class,
            this::apply2
        );
    }

    @Test
    public void testApplyTwoParametersFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.apply2("a1", "b2")
        );
    }

    @Test
    public void testApplyString() {
        this.applyAndCheck2(
            parameters("abc123"),
            "abc123"
        );
    }


    @Override
    public StringExpressionFunctionText<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionText.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "text");
    }

    // class............................................................................................................

    @Override
    public Class<StringExpressionFunctionText<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionText.class);
    }
}

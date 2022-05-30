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

public final class StringExpressionFunctionRepeatTest extends StringExpressionFunctionTestCase<StringExpressionFunctionRepeat<ExpressionEvaluationContext>> {

    @Test
    public void testZero() {
        this.applyAndCheck3("xyz", 0, "");
    }

    @Test
    public void testOne() {
        this.applyAndCheck3("xyz", 1, "xyz");
    }

    @Test
    public void testTwo() {
        this.applyAndCheck3("abc", 3, "abcabcabc");
    }

    private void applyAndCheck3(final String text, final int count, final String result) {
        this.applyAndCheck2(
                this.parameters(text, KIND.create(count)),
                result
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "repeat-string");
    }

    @Override
    public StringExpressionFunctionRepeat<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionRepeat.instance();
    }

    @Override
    public Class<StringExpressionFunctionRepeat<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionRepeat.class);
    }
}

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

public final class BooleanExpressionFunctionIsTextIsNonTextTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionIsTextIsNonText<ExpressionEvaluationContext>> {

    @Test
    public void testIsNonTextNull() {
        this.isNonTextAndCheck(null, true);
    }

    @Test
    public void testIsNonTextBoolean() {
        this.isNonTextAndCheck(true, true);
    }

    @Test
    public void testIsNonTextEmptyString() {
        this.isNonTextAndCheck("", false);
    }

    @Test
    public void testIsNonTextString() {
        this.isNonTextAndCheck("hello", false);
    }

    private void isNonTextAndCheck(final Object value,
                                   final boolean expected) {
        this.applyAndCheck(
            BooleanExpressionFunctionIsTextIsNonText.isNonText(),
            value,
            expected
        );
    }

    @Test
    public void testIsTextNull() {
        this.isTextAndCheck(null, false);
    }

    @Test
    public void testIsTextBoolean() {
        this.isTextAndCheck(true, false);
    }

    @Test
    public void testIsTextEmptyString() {
        this.isTextAndCheck("", true);
    }

    @Test
    public void testIsTextString() {
        this.isTextAndCheck("hello", true);
    }

    private void isTextAndCheck(final Object value,
                                final boolean expected) {
        this.applyAndCheck(
            BooleanExpressionFunctionIsTextIsNonText.isText(),
            value,
            expected
        );
    }

    private void applyAndCheck(final BooleanExpressionFunctionIsTextIsNonText<ExpressionEvaluationContext> function,
                               final Object value,
                               final boolean expected) {
        this.applyAndCheck2(
            function,
            Lists.of(
                value
            ),
            expected
        );
    }

    // toString........................................................................................................

    @Test
    public void testIsNonTextToString() {
        this.toStringAndCheck(
            BooleanExpressionFunctionIsTextIsNonText.isNonText(),
            "isNonText"
        );
    }

    @Test
    public void testIsTextToString() {
        this.toStringAndCheck(
            BooleanExpressionFunctionIsTextIsNonText.isText(),
            "isText"
        );
    }

    @Override
    public BooleanExpressionFunctionIsTextIsNonText<ExpressionEvaluationContext> createBiFunction() {
        return BooleanExpressionFunctionIsTextIsNonText.isText();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<BooleanExpressionFunctionIsTextIsNonText<ExpressionEvaluationContext>> type() {
        return Cast.to(BooleanExpressionFunctionIsTextIsNonText.class);
    }
}

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

public final class BooleanExpressionFunctionEqualsTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionEquals<ExpressionEvaluationContext>> {

    // caseSensitive....................................................................................................

    @Test
    public void testCaseInsensitiveTrue() {
        this.caseInsensitiveAndCheck("hello", "hello", true);
    }

    @Test
    public void testCaseInsensitiveDifferentCaseFalse() {
        this.caseInsensitiveAndCheck("hello", "HELLO", true);
    }

    @Test
    public void testCaseInsensitiveFalse() {
        this.caseInsensitiveAndCheck("hello", "goodbye", false);
    }

    private void caseInsensitiveAndCheck(final String text1,
                                         final String text2,
                                         final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionEquals.caseInsensitive(),
                text1,
                text2,
                expected
        );
    }

    // caseSensitive....................................................................................................

    @Test
    public void testCaseSensitiveTrue() {
        this.caseSensitiveAndCheck("hello", "hello", true);
    }

    @Test
    public void testCaseSensitiveDifferentCaseFalse() {
        this.caseSensitiveAndCheck("hello", "HELLO", false);
    }

    @Test
    public void testCaseSensitiveFalse() {
        this.caseSensitiveAndCheck("hello", "goodbye", false);
    }

    private void caseSensitiveAndCheck(final String text1,
                                       final String text2,
                                       final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionEquals.caseSensitive(),
                text1,
                text2,
                expected
        );
    }

    private void applyAndCheck(final BooleanExpressionFunctionEquals<ExpressionEvaluationContext> function,
                               final String text,
                               final String second,
                               final boolean expected) {
        this.applyAndCheck2(
                function,
                Lists.of(
                        text,
                        second
                ),
                expected
        );
    }

    // toString........................................................................................................

    @Test
    public void testCaseInensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionEquals.caseInsensitive(),
                "string-equals-case-insensitive"
        );
    }

    @Test
    public void testCaseSensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionEquals.caseSensitive(),
                "string-equals-case-sensitive"
        );
    }

    @Override
    public BooleanExpressionFunctionEquals<ExpressionEvaluationContext> createBiFunction() {
        return BooleanExpressionFunctionEquals.caseInsensitive();
    }

    @Override
    public int minimumParameterCount() {
        return 2;
    }

    @Override
    public Class<BooleanExpressionFunctionEquals<ExpressionEvaluationContext>> type() {
        return Cast.to(BooleanExpressionFunctionEquals.class);
    }
}

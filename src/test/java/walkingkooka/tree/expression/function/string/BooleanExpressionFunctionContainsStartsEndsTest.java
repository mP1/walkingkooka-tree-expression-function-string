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

public final class BooleanExpressionFunctionContainsStartsEndsTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionContainsStartsEnds<ExpressionEvaluationContext>> {

    @Test
    public void testContainsCaseSensitiveTrue() {
        this.containsCaseSensitiveAndCheck("hello", "el", true);
    }

    @Test
    public void testContainsCaseSensitiveDifferentCaseFalse() {
        this.containsCaseSensitiveAndCheck("hello", "EL", false);
    }

    @Test
    public void testContainsCaseSensitiveFalse() {
        this.containsCaseSensitiveAndCheck("hello", "goodbye", false);
    }

    private void containsCaseSensitiveAndCheck(final String text,
                                               final String contains,
                                               final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.containsCaseSensitive(),
                text,
                contains,
                expected
        );
    }

    @Test
    public void testContainsCaseInsensitiveFalse2() {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.containsCaseInsensitive(),
                "hello",
                "ELL",
                true
        );
    }

    @Test
    public void testEndsWithCaseSensitiveTrue() {
        this.endsWithCaseSensitiveAndCheck("hello", "lo", true);
    }

    @Test
    public void testEndsWithCaseSensitiveDifferentCaseFalse() {
        this.endsWithCaseSensitiveAndCheck("hello", "LO", false);
    }

    @Test
    public void testEndsWithCaseSensitiveFalse() {
        this.endsWithCaseSensitiveAndCheck("hello", "goodbye", false);
    }

    private void endsWithCaseSensitiveAndCheck(final String text,
                                               final String contains,
                                               final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.endsWithCaseSensitive(),
                text,
                contains,
                expected
        );
    }

    @Test
    public void testEndsWithCaseInsensitiveFalse() {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.endsWithCaseInsensitive(),
                "hello",
                "LLo",
                true
        );
    }

    @Test
    public void testStartsWithCaseSensitiveTrue() {
        this.startsWithCaseSensitiveAndCheck("hello", "he", true);
    }

    @Test
    public void testStartsWithCaseSensitiveDifferentCaseFalse() {
        this.startsWithCaseSensitiveAndCheck("hello", "HE", false);
    }

    @Test
    public void testStartsWithCaseSensitiveFalse() {
        this.startsWithCaseSensitiveAndCheck("hello", "goodbye", false);
    }

    private void startsWithCaseSensitiveAndCheck(final String text,
                                                 final String contains,
                                                 final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.startsWithCaseSensitive(),
                text,
                contains,
                expected
        );
    }

    @Test
    public void testStartsWithCaseInsensitive() {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.startsWithCaseInsensitive(),
                "hello",
                "HE",
                true
        );
    }

    private void applyAndCheck(final BooleanExpressionFunctionContainsStartsEnds<ExpressionEvaluationContext> function,
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
    public void testContainsCaseInsensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.containsCaseInsensitive(),
                "contains-case-insensitive"
        );
    }

    @Test
    public void testContainsCaseSensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.containsCaseSensitive(),
                "contains-case-sensitive"
        );
    }

    @Test
    public void testEndsWithCaseInsensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.endsWithCaseInsensitive(),
                "ends-with-case-insensitive"
        );
    }

    @Test
    public void testEndsWithCaseSensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.endsWithCaseSensitive(),
                "ends-with-case-sensitive"
        );
    }

    @Test
    public void testStartsWithCaseInsensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.startsWithCaseInsensitive(),
                "starts-with-case-insensitive"
        );
    }

    @Test
    public void testStartsWithCaseSensitiveToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.startsWithCaseSensitive(),
                "starts-with-case-sensitive"
        );
    }

    @Override
    public BooleanExpressionFunctionContainsStartsEnds<ExpressionEvaluationContext> createBiFunction() {
        return BooleanExpressionFunctionContainsStartsEnds.containsCaseSensitive();
    }

    @Override
    public Class<BooleanExpressionFunctionContainsStartsEnds<ExpressionEvaluationContext>> type() {
        return Cast.to(BooleanExpressionFunctionContainsStartsEnds.class);
    }
}

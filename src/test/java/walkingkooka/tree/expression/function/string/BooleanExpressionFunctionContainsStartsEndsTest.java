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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class BooleanExpressionFunctionContainsStartsEndsTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionContainsStartsEnds<ExpressionFunctionContext>> {

    @Test
    public void testContainsTrue() {
        this.containsAndCheck("hello", "el", true);
    }

    @Test
    public void testContainsCaseSensitiveFalse() {
        this.containsAndCheck("hello", "EL", false);
    }

    @Test
    public void testContainsFalse() {
        this.containsAndCheck("hello", "goodbye", false);
    }

    private void containsAndCheck(final String text,
                                  final String contains,
                                  final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.contains(),
                text,
                contains,
                expected
        );
    }

    @Test
    public void testEndsWithTrue() {
        this.endsWithAndCheck("hello", "lo", true);
    }

    @Test
    public void testEndsWithCaseSensitiveFalse() {
        this.endsWithAndCheck("hello", "LO", false);
    }

    @Test
    public void testEndsWithFalse() {
        this.endsWithAndCheck("hello", "goodbye", false);
    }

    private void endsWithAndCheck(final String text,
                                  final String contains,
                                  final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.endsWith(),
                text,
                contains,
                expected
        );
    }

    @Test
    public void testStartsWithTrue() {
        this.startsWithAndCheck("hello", "he", true);
    }

    @Test
    public void testStartsWithCaseSensitiveFalse() {
        this.startsWithAndCheck("hello", "HE", false);
    }

    @Test
    public void testStartsWithFalse() {
        this.startsWithAndCheck("hello", "goodbye", false);
    }

    private void startsWithAndCheck(final String text,
                                    final String contains,
                                    final boolean expected) {
        this.applyAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.startsWith(),
                text,
                contains,
                expected
        );
    }

    private void applyAndCheck(final BooleanExpressionFunctionContainsStartsEnds<ExpressionFunctionContext> function,
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
    public void testContainsToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.contains(),
                "contains"
        );
    }

    @Test
    public void testEndsWithToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.endsWith(),
                "ends-with"
        );
    }

    @Test
    public void testStartsWithToString() {
        this.toStringAndCheck(
                BooleanExpressionFunctionContainsStartsEnds.startsWith(),
                "starts-with"
        );
    }

    @Override
    public BooleanExpressionFunctionContainsStartsEnds<ExpressionFunctionContext> createBiFunction() {
        return BooleanExpressionFunctionContainsStartsEnds.contains();
    }

    @Override
    public Class<BooleanExpressionFunctionContainsStartsEnds<ExpressionFunctionContext>> type() {
        return Cast.to(BooleanExpressionFunctionContainsStartsEnds.class);
    }
}

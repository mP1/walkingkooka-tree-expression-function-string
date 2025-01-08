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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class NumberExpressionFunctionSearchOrFindTestCase<F extends NumberExpressionFunctionSearchOrFind<ExpressionEvaluationContext>> extends NumberExpressionFunctionTestCase<F> {

    NumberExpressionFunctionSearchOrFindTestCase() {
        super();
    }

    @Test
    public final void testStartPosNegativeFails() {
        this.startPosFails("A", "A", -1);
    }

    @Test
    public final void testStartPosZeroFails() {
        this.startPosFails("A", "A", 0);
    }

    @Test
    public final void testStartPosOutOfBoundsFails() {
        this.startPosFails("A", "A", 2);
    }

    private void startPosFails(final String find,
                               final String within,
                               final int startPos) {
        assertThrows(
            StringIndexOutOfBoundsException.class,
            () -> this.createBiFunction()
                .apply(
                    Lists.of(
                        find,
                        within,
                        KIND.create(startPos)
                    ),
                    this.createContext()
                )
        );
    }

    @Test
    public final void testNotFound() {
        this.searchOrFindAndCheck(
            "abc",
            "xyz",
            NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public final void testNotFoundStartPos() {
        this.searchOrFindAndCheck(
            "x",
            "xyz",
            2,
            NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public final void testNotFoundStartPos2() {
        this.searchOrFindAndCheck(
            "a",
            "abcde",
            2,
            NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public final void testFound() {
        this.searchOrFindAndCheck(
            "a",
            "abcde",
            1
        );
    }

    @Test
    public final void testFound2() {
        this.searchOrFindAndCheck(
            "c",
            "abcde",
            3
        );
    }

    @Test
    public final void testFoundStartPos() {
        this.searchOrFindAndCheck(
            "e",
            "abcde",
            5
        );
    }

    @Test
    public final void testFoundStartPos2() {
        this.searchOrFindAndCheck(
            "e",
            "abcde",
            2,
            5
        );
    }

    final void searchOrFindAndCheck(final String find,
                                    final String within,
                                    final int expected) {
        this.applyAndCheck2(
            Lists.of(
                find,
                within
            ),
            KIND.create(expected)
        );
    }

    final void searchOrFindAndCheck(final String find,
                                    final String within,
                                    final int startPos,
                                    final int expected) {
        this.applyAndCheck2(
            Lists.of(
                find,
                within,
                KIND.create(startPos)
            ),
            KIND.create(expected)
        );
    }

    final void searchOrFindAndCheck(final F function,
                                    final String find,
                                    final String within,
                                    final int startPos,
                                    final int expected) {
        this.applyAndCheck2(
            function,
            Lists.of(
                find,
                within,
                KIND.create(startPos)
            ),
            KIND.create(expected)
        );
    }

    abstract F createBiFunctionCaseInsensitive();
}

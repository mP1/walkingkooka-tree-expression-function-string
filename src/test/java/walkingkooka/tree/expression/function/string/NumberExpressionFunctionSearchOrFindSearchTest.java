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

public final class NumberExpressionFunctionSearchOrFindSearchTest extends NumberExpressionFunctionSearchOrFindTestCase<NumberExpressionFunctionSearchOrFindSearch<ExpressionEvaluationContext>> {

    @Test
    public void testPatternFound() {
        this.searchOrFindAndCheck(
                "?bc",
                "ABCDE",
                1
        );
    }

    @Test
    public void testPatternFound2() {
        this.searchOrFindAndCheck(
                "?cd",
                "ABCDE",
                2
        );
    }

    @Test
    public void testPatternFoundStartPos() {
        this.searchOrFindAndCheck(
                "?cd",
                "ABCDE",
                2,
                2
        );
    }

    @Test
    public void testPatternFoundStartPos2() {
        this.searchOrFindAndCheck(
                "?de",
                "ABCDEF",
                2,
                3
        );
    }

    @Test
    public void testPatternFoundStar() {
        this.searchOrFindAndCheck(
                "*.txt",
                "/dir/file.txt",
                1
        );
    }

    @Test
    public void testPatternFoundStarStartPos() {
        this.searchOrFindAndCheck(
                "*.txt",
                "/dir/file.txt",
                2,
                2
        );
    }

    @Test
    public void testFoundCaseDifferent() {
        this.searchOrFindAndCheck(
                "abc",
                "ABCDE",
                1
        );
    }

    @Test
    public void testFoundCaseDifferent2() {
        this.searchOrFindAndCheck(
                "c",
                "ABCDE",
                3
        );
    }

    @Test
    public void testFoundCaseDifferent3() {
        this.searchOrFindAndCheck(
                "bc",
                "ABCDE",
                2
        );
    }

    @Test
    public void testFoundCaseDifferentStartPos() {
        this.searchOrFindAndCheck(
                "cd",
                "ABCDE",
                2,
                3
        );
    }

    @Test
    public void testFoundCaseDifferentStartPos2() {
        this.searchOrFindAndCheck(
                "e",
                "ABCDE",
                2,
                5
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToStringFindCaseInsensitive() {
        this.toStringAndCheck(
                NumberExpressionFunctionSearchOrFindSearch.caseInsensitive(),
                "search-case-insensitive"
        );
    }

    @Test
    public void testToStringFindCaseSensitive() {
        this.toStringAndCheck(
                NumberExpressionFunctionSearchOrFindSearch.caseSensitive(),
                "search-case-sensitive"
        );
    }

    @Override
    public NumberExpressionFunctionSearchOrFindSearch<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionSearchOrFindSearch.caseSensitive();
    }

    @Override
    NumberExpressionFunctionSearchOrFindSearch<ExpressionEvaluationContext> createBiFunctionCaseInsensitive() {
        return NumberExpressionFunctionSearchOrFindSearch.caseInsensitive();
    }

    @Override
    public Class<NumberExpressionFunctionSearchOrFindSearch<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionSearchOrFindSearch.class);
    }
}

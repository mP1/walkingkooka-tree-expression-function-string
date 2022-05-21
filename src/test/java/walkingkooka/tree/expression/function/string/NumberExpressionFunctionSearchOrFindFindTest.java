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

public final class NumberExpressionFunctionSearchOrFindFindTest extends NumberExpressionFunctionSearchOrFindTestCase<NumberExpressionFunctionSearchOrFindFind<ExpressionEvaluationContext>> {

    @Test
    public void testCaseSensitiveNotFoundCaseDifferent() {
        this.searchOrFindAndCheck(
                "abc",
                "ABCDE",
                NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public void testCaseSensitiveNotFoundCaseDifferent2() {
        this.searchOrFindAndCheck(
                "c",
                "ABCDE",
                NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public void testCaseSensitiveNotFoundCaseDifferent3() {
        this.searchOrFindAndCheck(
                "bc",
                "ABCDE",
                NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public void testCaseSensitiveNotFoundCaseDifferentStartPos() {
        this.searchOrFindAndCheck(
                "cd",
                "ABCDE",
                2,
                NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public void testCaseSensitiveNotFoundCaseDifferentStartPos2() {
        this.searchOrFindAndCheck(
                "e",
                "ABCDE",
                2,
                NumberExpressionFunction.NOT_FOUND_INDEX
        );
    }

    @Test
    public void testCaseInsensitiveNotFoundCaseDifferentStartPos() {
        this.searchOrFindAndCheck(
                this.createBiFunctionCaseInsensitive(),
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
                NumberExpressionFunctionSearchOrFindFind.caseInsensitive(),
                "find-case-insensitive"
        );
    }

    @Test
    public void testToStringFindCaseSensitive() {
        this.toStringAndCheck(
                NumberExpressionFunctionSearchOrFindFind.caseSensitive(),
                "find-case-sensitive"
        );
    }

    @Override
    public NumberExpressionFunctionSearchOrFindFind<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionSearchOrFindFind.caseSensitive();
    }

    @Override
    NumberExpressionFunctionSearchOrFindFind<ExpressionEvaluationContext> createBiFunctionCaseInsensitive() {
        return NumberExpressionFunctionSearchOrFindFind.caseInsensitive();
    }

    @Override
    public Class<NumberExpressionFunctionSearchOrFindFind<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionSearchOrFindFind.class);
    }
}

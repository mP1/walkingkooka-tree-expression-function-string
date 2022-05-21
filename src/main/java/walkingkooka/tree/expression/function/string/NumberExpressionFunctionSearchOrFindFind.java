
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

import walkingkooka.Cast;
import walkingkooka.text.CaseSensitivity;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

/**
 * A function that performs a search, matching the semantics of Excel's SEARCH except that it returns -1 when the match
 * is unsuccessful.
 * <br>
 * https://exceljet.net/excel-functions/excel-search-function
 */
final class NumberExpressionFunctionSearchOrFindFind<C extends ExpressionEvaluationContext> extends NumberExpressionFunctionSearchOrFind<C> {

    /**
     * FIND case sensitive function getter
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionSearchOrFindFind<C> caseSensitive() {
        return Cast.to(CASE_SENSITIVE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionSearchOrFindFind<?> CASE_SENSITIVE = new NumberExpressionFunctionSearchOrFindFind<>(
            "find-case-sensitive",
            CaseSensitivity.SENSITIVE
    );

    /**
     * FIND case insensitive function getter
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionSearchOrFindFind<C> caseInsensitive() {
        return Cast.to(CASE_INSENSITIVE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionSearchOrFindFind<?> CASE_INSENSITIVE = new NumberExpressionFunctionSearchOrFindFind<>(
            "find-case-insensitive",
            CaseSensitivity.INSENSITIVE
    );

    /**
     * Private ctor
     */
    private NumberExpressionFunctionSearchOrFindFind(final String name,
                                                     final CaseSensitivity caseSensitivity) {
        super(name, caseSensitivity);
    }

    @Override
    int apply(final String find,
              final String within,
              final int startPos) {
        return this.caseSensitivity.indexOf(
                within,
                find,
                startPos
        );
    }
}

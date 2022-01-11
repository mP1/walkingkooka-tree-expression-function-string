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

/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.text.CharSequences;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Finds one string within another returning the starting index, starting count at 1.
 * <br>
 * https://exceljet.net/excel-functions/excel-find-function
 */
final class NumberExpressionFunctionFind<C extends ExpressionFunctionContext> extends NumberExpressionFunction<C> {

    /**
     * FIND getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionFind<C> findCaseSensitive() {
        return Cast.to(FIND_CASE_SENSITIVE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionFind<?> FIND_CASE_SENSITIVE = new NumberExpressionFunctionFind<>(
            "find-case-sensitive",
            CaseSensitivity.SENSITIVE
    );

    /**
     * FIND case-insensitive getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionFind<C> findCaseInsensitive() {
        return Cast.to(FIND_CASE_INSENSITIVE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionFind<?> FIND_CASE_INSENSITIVE = new NumberExpressionFunctionFind<>(
            "find-case-insensitive",
            CaseSensitivity.INSENSITIVE
    );

    /**
     * Private ctor
     */
    private NumberExpressionFunctionFind(final String name,
                                         final CaseSensitivity caseSensitivity) {
        super(name);
        this.caseSensitivity = caseSensitivity;
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        final int count = parameters.size();

        final String find;
        final String within;
        final int start;

        switch (count) {
            case 2:
                find = FIND_TEXT.getOrFail(parameters, 0);
                within = WITHIN.getOrFail(parameters, 1);
                start = BIAS;
                break;
            case 3:
                find = FIND_TEXT.getOrFail(parameters, 0);
                within = WITHIN.getOrFail(parameters, 1);
                start = START_POS.getOrFail(parameters, 2)
                        .intValue();
                final int textLength = within.length();
                if (start < BIAS || start > textLength) {
                    throw new IllegalArgumentException("Invalid start " + start + " < 1 or > " + textLength);
                }
                break;
            default:
                throw new IllegalArgumentException("Expected 2 or 3 parameters but got " + count);
        }

        final int found =
                find.length() == 0 ?
                        start - BIAS :
                        this.caseSensitivity.indexOf(within, find, start - BIAS);

        if (found == -1) {
            throw new IllegalStateException(
                    "Text " +
                            CharSequences.quoteAndEscape(find) +
                            " not found within " +
                            CharSequences.quoteIfChars(within)
            );
        }

        return context.expressionNumberKind()
                .create(found + BIAS);
    }

    private final CaseSensitivity caseSensitivity;

    private final static int BIAS = 1;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<String> FIND_TEXT = ExpressionFunctionParameterName.with("find-text")
            .required(String.class);

    private final static ExpressionFunctionParameter<String> WITHIN = ExpressionFunctionParameterName.with("within")
            .required(String.class);

    private final static ExpressionFunctionParameter<ExpressionNumber> START_POS = ExpressionFunctionParameterName.with("start-pos")
            .optional(ExpressionNumber.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            FIND_TEXT,
            WITHIN,
            START_POS
    );
}

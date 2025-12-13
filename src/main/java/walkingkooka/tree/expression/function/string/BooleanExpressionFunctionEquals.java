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
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Only returns true if both parameters are strings, with two functions where case matters and case insensitive.
 * <br>
 * https://exceljet.net/excel-functions/excel-exact-function
 */
final class BooleanExpressionFunctionEquals<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionEquals<C> caseInsensitive() {
        return Cast.to(EQUALS_CASE_INSENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionEquals<?> EQUALS_CASE_INSENSITIVE = new BooleanExpressionFunctionEquals<>(
        "equalsCaseInsensitive",
        CaseSensitivity.INSENSITIVE
    );

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionEquals<C> caseSensitive() {
        return Cast.to(EQUALS_CASE_SENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionEquals<?> EQUALS_CASE_SENSITIVE = new BooleanExpressionFunctionEquals<>(
        "equalsCaseSensitive",
        CaseSensitivity.SENSITIVE
    );

    private BooleanExpressionFunctionEquals(final String name,
                                            final CaseSensitivity caseSensitivity) {
        super(name);
        this.caseSensitivity = caseSensitivity;
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        return this.caseSensitivity.equals(
            TEXT1.getOrFail(parameters, 0, context),
            TEXT2.getOrFail(parameters, 1, context)
        );
    }

    private final static ExpressionFunctionParameter<String> TEXT1 = ExpressionFunctionParameterName.with("text1")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<String> TEXT2 = ExpressionFunctionParameterName.with("text2")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final CaseSensitivity caseSensitivity;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
        TEXT1,
        TEXT2
    );
}

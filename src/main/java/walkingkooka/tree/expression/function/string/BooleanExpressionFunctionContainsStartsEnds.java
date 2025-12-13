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
import java.util.function.BiFunction;

final class BooleanExpressionFunctionContainsStartsEnds<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    // first to avoid nulls
    private final static ExpressionFunctionParameter<String> TEXT = ExpressionFunctionParameter.TEXT
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    /**
     * CONTAINS case-insensitive getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionContainsStartsEnds<C> containsCaseInsensitive() {
        return Cast.to(CONTAINS_CASE_INSENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> CONTAINS_CASE_INSENSITIVE = new BooleanExpressionFunctionContainsStartsEnds<>(
        "containsCaseInsensitive",
        "containsCaseInsensitive",
        (text, ends) -> CaseSensitivity.INSENSITIVE.contains(text, ends)
    );

    /**
     * CONTAINS case-sensitive getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionContainsStartsEnds<C> containsCaseSensitive() {
        return Cast.to(CONTAINS_CASE_SENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> CONTAINS_CASE_SENSITIVE = new BooleanExpressionFunctionContainsStartsEnds<>(
        "containsCaseSensitive",
        "containsCaseSensitive",
        (text, ends) -> CaseSensitivity.SENSITIVE.contains(text, ends)
    );

    /**
     * STARTSWITH case-insensitive getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionContainsStartsEnds<C> startsWithCaseInsensitive() {
        return Cast.to(STARTSWITH_CASE_INSENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> STARTSWITH_CASE_INSENSITIVE = new BooleanExpressionFunctionContainsStartsEnds<>(
        "startsWithCaseInsensitive",
        "startsWithCaseInsensitive",
        (text, starts) -> CaseSensitivity.INSENSITIVE.startsWith(text, starts)
    );

    /**
     * STARTSWITH case-sensitive getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionContainsStartsEnds<C> startsWithCaseSensitive() {
        return Cast.to(STARTSWITH_CASE_SENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> STARTSWITH_CASE_SENSITIVE = new BooleanExpressionFunctionContainsStartsEnds<>(
        "startsWithCaseSensitive",
        "startsWithCaseSensitive",
        (text, starts) -> CaseSensitivity.SENSITIVE.startsWith(text, starts)
    );

    /**
     * ENDSWITH case-insensitive getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionContainsStartsEnds<C> endsWithCaseInsensitive() {
        return Cast.to(ENDSWITH_CASE_INSENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> ENDSWITH_CASE_INSENSITIVE = new BooleanExpressionFunctionContainsStartsEnds<>(
        "endsWithCaseInsensitive",
        "endsWithCaseInsensitive",
        (text, ends) -> CaseSensitivity.INSENSITIVE.endsWith(text, ends)
    );

    /**
     * ENDSWITH case-sensitive getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionContainsStartsEnds<C> endsWithCaseSensitive() {
        return Cast.to(ENDSWITH_CASE_SENSITIVE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> ENDSWITH_CASE_SENSITIVE = new BooleanExpressionFunctionContainsStartsEnds<>(
        "endsWithCaseSensitive",
        "endsWithCaseSensitive",
        (text, ends) -> CaseSensitivity.SENSITIVE.endsWith(text, ends)
    );

    private BooleanExpressionFunctionContainsStartsEnds(final String name,
                                                        final String secondParameterName,
                                                        final BiFunction<String, String, Boolean> predicate) {
        super(name);
        this.secondParameter = ExpressionFunctionParameterName.with(secondParameterName)
            .required(String.class)
            .setKinds(TEXT.kinds());
        this.parameters = ExpressionFunctionParameter.list(
            TEXT,
            this.secondParameter
        );
        this.predicate = predicate;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return this.parameters;
    }

    final ExpressionFunctionParameter<String> secondParameter;
    final List<ExpressionFunctionParameter<?>> parameters;

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        final String first = TEXT.getOrFail(parameters, 0, context);
        final String second = this.secondParameter.getOrFail(parameters, 1, context);

        return this.predicate.apply(first, second);
    }

    private final BiFunction<String, String, Boolean> predicate;
}

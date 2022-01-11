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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;
import java.util.function.BiFunction;

final class BooleanExpressionFunctionContainsStartsEnds<C extends ExpressionFunctionContext> extends BooleanExpressionFunction<C> {

    // first to avoid nulls
    private final static ExpressionFunctionParameter<String> TEXT = ExpressionFunctionParameter.TEXT;

    /**
     * CONTAINS getter.
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionContainsStartsEnds<C> contains() {
        return Cast.to(CONTAINS);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> CONTAINS = new BooleanExpressionFunctionContainsStartsEnds<>(
            "contains",
            "contains",
            (text, contains) -> text.contains(contains)
    );

    /**
     * STARTSWITH getter.
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionContainsStartsEnds<C> startsWith() {
        return Cast.to(STARTSWITH);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> STARTSWITH = new BooleanExpressionFunctionContainsStartsEnds<>(
            "starts-with",
            "starts-with",
            (text, starts) -> text.startsWith(starts)
    );

    /**
     * ENDSWITH getter.
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionContainsStartsEnds<C> endsWith() {
        return Cast.to(ENDSWITH);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionContainsStartsEnds<?> ENDSWITH = new BooleanExpressionFunctionContainsStartsEnds<>(
            "ends-with",
            "ends-with",
            (text, ends) -> text.endsWith(ends)
    );

    private BooleanExpressionFunctionContainsStartsEnds(final String name,
                                                        final String secondParameterName,
                                                        final BiFunction<String, String, Boolean> predicate) {
        super(name);
        this.secondParameter = ExpressionFunctionParameterName.with(secondParameterName)
                .required(String.class);
        this.parameters = ExpressionFunctionParameter.list(
                TEXT,
                this.secondParameter
        );
        this.predicate = predicate;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return this.parameters;
    }

    final ExpressionFunctionParameter<String> secondParameter;
    final List<ExpressionFunctionParameter<?>> parameters;

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        final String first = TEXT.getOrFail(parameters, 0);
        final String second = this.secondParameter.getOrFail(parameters, 1);

        return this.predicate.apply(first, second);
    }

    private final BiFunction<String, String, Boolean> predicate;
}

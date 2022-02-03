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

/**
 * A {@link walkingkooka.tree.expression.function.ExpressionFunction} that converts the given value into a {@link String} and if successful returns true, or false in other cases.
 */
final class StringExpressionFunctionText<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionText<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionText<?> INSTANCE = new StringExpressionFunctionText<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionText() {
        super("text");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        return context.convertOrFail(
                VALUE.getOrFail(parameters, 0),
                String.class
        );
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<String> VALUE = ExpressionFunctionParameterName.with("value")
            .required(String.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(VALUE);
}

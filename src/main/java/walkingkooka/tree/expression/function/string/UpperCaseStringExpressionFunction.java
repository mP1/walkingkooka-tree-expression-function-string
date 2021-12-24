/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

/**
 * Performs a lower case converting the value to a {@link String} using the current {@link java.util.Locale}.
 */
final class UpperCaseStringExpressionFunction<C extends ExpressionFunctionContext> extends UnaryStringExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> UpperCaseStringExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final UpperCaseStringExpressionFunction<?> INSTANCE = new UpperCaseStringExpressionFunction<>();

    /**
     * Private ctor
     */
    private UpperCaseStringExpressionFunction() {
        super();
    }

    @Override
    String applyString(final String value,
                       final ExpressionFunctionContext context) {
        return value.toUpperCase(context.locale());
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("upper-case");
}

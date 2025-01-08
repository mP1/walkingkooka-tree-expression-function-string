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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;

import java.util.List;

/**
 * Returns the length of the provided string.
 * <a href="https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/string-length"></a>
 * Unlike the Mozilla documentation, if the argument is missing an exception is thrown.
 */
final class NumberExpressionFunctionLength<C extends ExpressionEvaluationContext> extends NumberExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionLength<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionLength<?> INSTANCE = new NumberExpressionFunctionLength<>();

    /**
     * Private ctor
     */
    private NumberExpressionFunctionLength() {
        super("stringLength");
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkParameterCount(parameters);
        return context.expressionNumberKind()
            .create(
                TEXT.getOrFail(parameters, 0).length()
            );
    }

    private final static ExpressionFunctionParameter<String> TEXT = ExpressionFunctionParameter.TEXT
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(TEXT);
}

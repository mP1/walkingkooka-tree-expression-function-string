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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * Returns the unicode of the provided {@link Character}
 * <a href="https://support.google.com/docs/answer/9149523?hl=en&ref_topic=3105625"></a>
 */
final class NumberExpressionFunctionUnicode<C extends ExpressionFunctionContext> extends NumberExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> NumberExpressionFunctionUnicode<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionUnicode<?> INSTANCE = new NumberExpressionFunctionUnicode<>();

    /**
     * Private ctor
     */
    private NumberExpressionFunctionUnicode() {
        super("unicode");
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkOnlyRequiredParameters(parameters);

        final Character character = CHARACTER.getOrFail(parameters, 0);

        return context.expressionNumberKind()
                .create((int) character);
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Character> CHARACTER = ExpressionFunctionParameter.CHARACTER;

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(CHARACTER);
}

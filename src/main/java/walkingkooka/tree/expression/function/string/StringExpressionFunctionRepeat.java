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
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * https://support.google.com/docs/answer/3094134?hl=en
 * <p>
 * Returns specified text repeated a number of times.
 */
final class StringExpressionFunctionRepeat<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionRepeat<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionRepeat<?> INSTANCE = new StringExpressionFunctionRepeat<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionRepeat() {
        super("repeatString");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final String string = TEXT.getOrFail(parameters, 0);
        final int count = COUNT.getOrFail(parameters, 1).intValue();

        final StringBuilder b = new StringBuilder(string.length() * count);

        for (int i = 0; i < count; i++) {
            b.append(string);
        }

        return b.toString();
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> COUNT = ExpressionFunctionParameterName.with("count")
            .required(ExpressionNumber.class)
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEXT,
            COUNT
    );
}

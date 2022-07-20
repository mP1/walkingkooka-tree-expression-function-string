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
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A function that concats all the Strings given to it.
 */
final class StringExpressionFunctionConcat<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionConcat<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionConcat<?> INSTANCE = new StringExpressionFunctionConcat<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionConcat() {
        super("concat");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        final int count = parameters.size();
        if (count < 1) {
            throw new IllegalArgumentException("Missing text");
        }

        return parameters.stream()
                .map(p -> (String) p)
                .collect(Collectors.joining());
    }

    private final static ExpressionFunctionParameter<String> TEXT = ExpressionFunctionParameterName.TEXT.variable(String.class)
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_FLATTEN_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(TEXT);
}

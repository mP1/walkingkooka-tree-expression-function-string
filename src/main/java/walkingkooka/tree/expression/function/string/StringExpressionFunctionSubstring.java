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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that returns a substring of another string.<br>
 * <a href="https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/substring"></a>
 */
final class StringExpressionFunctionSubstring<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {

    /**
     * Factory that returns a matching {@link StringExpressionFunctionSubstring}
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionSubstring<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionSubstring<?> INSTANCE = new StringExpressionFunctionSubstring<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionSubstring() {
        super("substring");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final String string = TEXT.getOrFail(parameters, 0);
        final int offset = OFFSET.getOrFail(parameters, 1).intValue();
        final int length = LENGTH.get(parameters, 2)
                .orElse(context.expressionNumberKind().create(string.length() - offset + INDEX_BIAS))
                .intValue();

        final int zeroOffset = offset - INDEX_BIAS;

        return string.substring(zeroOffset, length + zeroOffset);
    }

    private final static int INDEX_BIAS = 1;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> OFFSET = ExpressionFunctionParameterName.with("offset")
            .required(ExpressionNumber.class);

    private final static ExpressionFunctionParameter<ExpressionNumber> LENGTH = ExpressionFunctionParameterName.with("length")
            .optional(ExpressionNumber.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEXT,
            OFFSET,
            LENGTH
    );
}

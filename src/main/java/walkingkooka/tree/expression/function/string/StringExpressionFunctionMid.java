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
 * The excel mid function.
 * <a href="https://support.google.com/docs/answer/3094129?hl=en&ref_topic=3105625>MID</a>
 */
final class StringExpressionFunctionMid<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionMid<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionMid<?> INSTANCE = new StringExpressionFunctionMid<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionMid() {
        super("mid");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final String string = TEXT.getOrFail(parameters, 0, context);
        final int start = START.getOrFail(parameters, 1, context).intValue() - 1;
        final int length = LENGTH.getOrFail(parameters, 2, context).intValue();

        final int stringLength = string.length();

        return start >= stringLength || start + length <= 0 ?
            "" : // return empty if start or end is out of bounds
            string.substring(Math.max(0, start), Math.min(start + length, stringLength)); // ensure start & length are within bounds
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> START = ExpressionFunctionParameterName.with("start")
        .required(ExpressionNumber.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<ExpressionNumber> LENGTH = ExpressionFunctionParameterName.with("length")
        .required(ExpressionNumber.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
        TEXT,
        START,
        LENGTH
    );
}

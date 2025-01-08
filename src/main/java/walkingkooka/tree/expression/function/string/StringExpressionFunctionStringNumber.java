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

import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;
import java.util.Optional;

/**
 * A function that requires a {@link String} and an optional {@link Number} returning a {@link String} result.
 */
abstract class StringExpressionFunctionStringNumber<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Package private ctor
     */
    StringExpressionFunctionStringNumber(final String name) {
        super(name);
    }

    @Override
    public final String apply(final List<Object> parameters,
                              final C context) {
        this.checkParameterCount(parameters);


        return this.applyStringInteger(
            TEXT.getOrFail(parameters, 0),
            LENGTH.get(parameters, 1)
                .orElseGet(() -> Optional.of(
                        context.expressionNumberKind()
                            .one()
                    )
                ).get()
                .intValue()
        );
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> LENGTH = ExpressionFunctionParameterName.with("length")
        .optional(ExpressionNumber.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    abstract String applyStringInteger(final String string,
                                       final int number);

    @Override
    public final List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
        TEXT,
        LENGTH
    );
}

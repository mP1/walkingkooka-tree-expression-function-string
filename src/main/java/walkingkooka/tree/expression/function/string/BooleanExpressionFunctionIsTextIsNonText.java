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

import java.util.List;
import java.util.function.Predicate;

// https://support.google.com/docs/answer/3093297?hl=en&ref_topic=3105471
// https://support.google.com/docs/answer/3093295?hl=en&ref_topic=3105471
final class BooleanExpressionFunctionIsTextIsNonText<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    /**
     * ISNONTEXT getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionIsTextIsNonText<C> isNonText() {
        return Cast.to(ISNONTEXT);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionIsTextIsNonText<?> ISNONTEXT = new BooleanExpressionFunctionIsTextIsNonText<>(
            "isNonText",
            (value) -> !(value instanceof String) && !"".equals(value)
    );

    /**
     * ISTEXT getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionIsTextIsNonText<C> isText() {
        return Cast.to(IS);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionIsTextIsNonText<?> IS = new BooleanExpressionFunctionIsTextIsNonText<>(
            "isText",
            (value) -> value instanceof String
    );

    private BooleanExpressionFunctionIsTextIsNonText(final String name,
                                                     final Predicate<Object> predicate) {
        super(name);
        this.predicate = predicate;
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        return this.predicate.test(VALUE.getOrFail(parameters, 0));
    }

    @Override
    public final List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Object> VALUE = ExpressionFunctionParameter.VALUE;

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            VALUE
    );

    private final Predicate<Object> predicate;
}

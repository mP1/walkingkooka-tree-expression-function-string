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
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * A function that requires 2 string parameters and returns a {@link String} result.
 */
abstract class StringExpressionFunctionStringString<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Package private ctor
     */
    StringExpressionFunctionStringString(final String name) {
        super(name);
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        return this.applyStringString(
                TEXT.getOrFail(parameters, 0),
                this.secondParameter().getOrFail(parameters, 1),
                context);
    }

    abstract ExpressionFunctionParameter<String> secondParameter();

    abstract String applyStringString(final String first,
                                      final String second,
                                      final ExpressionEvaluationContext context);
}

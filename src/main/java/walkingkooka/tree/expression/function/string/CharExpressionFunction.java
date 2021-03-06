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

import java.util.List;

/**
 * Converts the only parameter to a character.
 * <a href="https://support.google.com/docs/answer/3094120?hl=en&ref_topic=3105625">CHAR</a>
 */
final class CharExpressionFunction<C extends ExpressionFunctionContext> extends StringExpressionFunction<Character, C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> CharExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final CharExpressionFunction INSTANCE = new CharExpressionFunction();

    /**
     * Private ctor
     */
    private CharExpressionFunction() {
        super();
    }

    @Override
    public Character apply(final List<Object> parameters,
                           final C context) {
        this.checkParameterCount(parameters, 1);

        final int value = this.integer(parameters, 0, context);
        if (value < Character.MIN_VALUE || value > Character.MAX_VALUE) {
            throw new IllegalArgumentException("Invalid character value " + value);
        }
        return Character.valueOf((char) value);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("char");
}

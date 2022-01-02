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
 * Converts the only parameter to a {@link Character} with a character.
 * <a href="https://support.google.com/docs/answer/3094120?hl=en&ref_topic=3105625">CHAR</a>
 */
final class CharacterExpressionFunctionChar<C extends ExpressionFunctionContext> extends CharacterExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> CharacterExpressionFunctionChar<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final CharacterExpressionFunctionChar<?> INSTANCE = new CharacterExpressionFunctionChar<>();

    /**
     * Private ctor
     */
    private CharacterExpressionFunctionChar() {
        super("char");
    }

    @Override
    public Character apply(final List<Object> parameters,
                           final C context) {
        this.checkOnlyRequiredParameters(parameters);

        final int value = NUMBER.getOrFail(parameters, 0)
                .intValue();
        if (value < Character.MIN_VALUE || value > Character.MAX_VALUE) {
            throw new IllegalArgumentException("Invalid character value " + value);
        }
        return (char) value;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    final static ExpressionFunctionParameter<ExpressionNumber> NUMBER = ExpressionFunctionParameter.NUMBER;

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(NUMBER);
}

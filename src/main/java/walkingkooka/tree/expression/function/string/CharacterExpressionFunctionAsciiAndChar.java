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

import java.util.List;

/**
 * <a href="https://support.google.com/docs/answer/9061514?hl=en">ASC</a>
 * <br>
 * <a href="https://support.google.com/docs/answer/3094120?hl=en&ref_topic=3105625">CHAR</a>
 */
final class CharacterExpressionFunctionAsciiAndChar<C extends ExpressionEvaluationContext> extends CharacterExpressionFunction<C> {

    /**
     * Char Instance getter.
     */
    static <C extends ExpressionEvaluationContext> CharacterExpressionFunctionAsciiAndChar<C> ascii() {
        return Cast.to(ASCII);
    }

    /**
     * CHAR Singleton
     */
    private static final CharacterExpressionFunctionAsciiAndChar<?> ASCII = new CharacterExpressionFunctionAsciiAndChar<>("ascii", 255);

    /**
     * Char Instance getter.
     */
    static <C extends ExpressionEvaluationContext> CharacterExpressionFunctionAsciiAndChar<C> character() {
        return Cast.to(CHAR);
    }

    /**
     * CHAR Singleton
     */
    private static final CharacterExpressionFunctionAsciiAndChar<?> CHAR = new CharacterExpressionFunctionAsciiAndChar<>("char", Character.MAX_VALUE);

    /**
     * Private ctor
     */
    private CharacterExpressionFunctionAsciiAndChar(final String name, final int max) {
        super(name);
        this.max = max;
    }

    @Override
    public Character apply(final List<Object> parameters,
                           final C context) {
        this.checkParameterCount(parameters);

        final int value = NUMBER.getOrFail(parameters, 0)
            .intValue();
        final int max = this.max;
        if (value < Character.MIN_VALUE || value > max) {
            throw new IllegalArgumentException("Invalid character value " + value + " expected between 0 and " + max);
        }
        return (char) value;
    }

    private final int max;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    final static ExpressionFunctionParameter<ExpressionNumber> NUMBER = ExpressionFunctionParameter.NUMBER
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(NUMBER);
}

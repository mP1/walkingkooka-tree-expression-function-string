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

/**
 * Performs a left for a given {@link String}. Defaults to 1 if the number or second parameter is absent and uses 0 if the 2nd parameter is negative.
 * <a href="https://support.google.com/docs/answer/3094079?hl=en">LEFT</a>
 */
final class StringExpressionFunctionStringNumberLeft<C extends ExpressionEvaluationContext> extends StringExpressionFunctionStringNumber<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionStringNumberLeft<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionStringNumberLeft<?> INSTANCE = new StringExpressionFunctionStringNumberLeft<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionStringNumberLeft() {
        super("left");
    }

    @Override
    String applyStringInteger(final String string,
                              final int length) {
        return string.substring(0, Math.max(0, Math.min(length, string.length())));
    }
}

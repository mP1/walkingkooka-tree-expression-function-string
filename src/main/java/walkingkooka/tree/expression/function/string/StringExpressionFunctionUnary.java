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

/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.text.CharSequences;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;
import java.util.function.BiFunction;

/**
 * A {@link ExpressionFunction} that handles a single {@link String} parameter.
 */
final class StringExpressionFunctionUnary<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {

    /**
     * LOWERCASE Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionUnary<C> lowerCase() {
        return Cast.to(LOWERCASE);
    }

    private static final StringExpressionFunctionUnary<?> LOWERCASE = new StringExpressionFunctionUnary<>(
            "lower-case",
            (s, c) -> s.toLowerCase(c.locale())
    );

    /**
     * NORMALIZESPACE Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionUnary<C> normalizeSpace() {
        return Cast.to(NORMALIZESPACE);
    }

    private static final StringExpressionFunctionUnary<?> NORMALIZESPACE = new StringExpressionFunctionUnary<>(
            "normalize-space",
            StringExpressionFunctionUnary::normalizeSpace
    );

    private static String normalizeSpace(final String value,
                                         final ExpressionFunctionContext context) {
        final String trimmed = value.trim();

        final StringBuilder b = new StringBuilder();
        final int length = trimmed.length();
        boolean wasWhitespace = false;

        for (int i = 0; i < length; i++) {
            char c = trimmed.charAt(i);
            final boolean whitespace = Character.isWhitespace(c);
            if (whitespace) {
                if (wasWhitespace) {
                    continue;
                }
                c = ' ';
            }
            wasWhitespace = whitespace;
            b.append(c);
        }

        return b.toString();
    }

    /**
     * TRIMLEFT Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionUnary<C> trimLeft() {
        return Cast.to(TRIMLEFT);
    }

    private static final StringExpressionFunctionUnary<?> TRIMLEFT = new StringExpressionFunctionUnary<>(
            "trim-left",
            (s, c) -> CharSequences.trimLeft(s).toString()
    );

    /**
     * TRIMRIGHT Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionUnary<C> trimRight() {
        return Cast.to(TRIMRIGHT);
    }

    private static final StringExpressionFunctionUnary<?> TRIMRIGHT = new StringExpressionFunctionUnary<>(
            "trim-right",
            (s, c) -> CharSequences.trimRight(s).toString()
    );

    /**
     * UPPERCASE Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionUnary<C> upperCase() {
        return Cast.to(UPPERCASE);
    }

    private static final StringExpressionFunctionUnary<?> UPPERCASE = new StringExpressionFunctionUnary<>(
            "upper-case",
            (s, c) -> s.toUpperCase(c.locale())
    );

    /**
     * Private ctor
     */
    private StringExpressionFunctionUnary(final String name,
                                          final BiFunction<String, C, String> function) {
        super(name);
        this.function = function;
    }

    @Override
    public final String apply(final List<Object> parameters,
                              final C context) {
        this.checkParameterCount(parameters);

        return this.function.apply(
                TEXT.getOrFail(parameters, 0),
                context
        );
    }

    private final BiFunction<String, C, String> function;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(TEXT);
}

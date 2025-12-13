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
import walkingkooka.text.CharSequences;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

/**
 * A {@link ExpressionFunction} that handles a single {@link String} parameter.
 */
final class StringExpressionFunctionUnary<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * LOWERCASE Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> lowerCase() {
        return Cast.to(LOWERCASE);
    }

    private static final StringExpressionFunctionUnary<?> LOWERCASE = new StringExpressionFunctionUnary<>(
        "lowerCase",
        (s, c) -> s.toLowerCase(c.locale())
    );

    /**
     * NORMALIZESPACE Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> normalizeSpace() {
        return Cast.to(NORMALIZESPACE);
    }

    private static final StringExpressionFunctionUnary<?> NORMALIZESPACE = new StringExpressionFunctionUnary<>(
        "normalizeSpace",
        StringExpressionFunctionUnary::normalizeSpace
    );

    private static String normalizeSpace(final String value,
                                         final ExpressionEvaluationContext context) {
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
     * PROPER Instance getter.
     * <br>
     * https://github.com/mP1/walkingkooka-tree-expression-function-string/issues/71
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> proper() {
        return Cast.to(PROPER);
    }

    private static final StringExpressionFunctionUnary<?> PROPER = new StringExpressionFunctionUnary<>(
        "proper",
        (s, c) -> proper(s, c.locale())
    );

    // https://exceljet.net/excel-functions/excel-proper-function
    //
    // The first letter in any word is capitalized, all other letters are lowerCased.
    // All other characters are copied over as is.
    //
    // TODO
    // If a numeric value is given to PROPER, number formatting is removed. For example, if cell A1 contains the date
    // June 26, 2021, date formatting will be lost and PROPER will return a date serial number as text:
    private static String proper(final String text,
                                 final Locale locale) {
        final StringBuilder b = new StringBuilder();
        final int length = text.length();

        boolean makeUpper = true;

        for (int i = 0; i < length; i++) {
            final char c = text.charAt(i);
            if (Character.isLetter(c)) {
                final String s = String.valueOf(c);
                b.append(makeUpper ? s.toUpperCase(locale) : s.toLowerCase(locale));

                makeUpper = false;
                continue;
            }

            b.append(c);
            makeUpper = true;
        }

        return b.toString();
    }

    /**
     * SPACETRIM Instance getter.
     * <br>
     * This follows the same semantics as EXCEL trim, removing only spaces left/right and multiple spaces.
     * <br>
     * https://exceljet.net/excel-functions/excel-trim-function
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> spaceTrim() {
        return Cast.to(SPACETRIM);
    }

    private static final StringExpressionFunctionUnary<?> SPACETRIM = new StringExpressionFunctionUnary<>(
        "spaceTrim",
        StringExpressionFunctionUnary::spaceTrim
    );

    private static String spaceTrim(final String string, final ExpressionEvaluationContext context) {
        final StringBuilder b = new StringBuilder();

        int start = 0;
        boolean space = false;

        final int length = string.length();

        int i = 0;
        do {
            if (i == length) {
                // dont save the last run of spaces
                break;
            }

            final char c = string.charAt(i);
            if (' ' == c) {
                if (!space) {
                    start = i;
                }
                space = true;
                i++;
                continue;
            }

            if (space && start > 0) {
                b.append(' ');
            }

            space = false;

            b.append(c);
            i++;
        } while (true);

        return b.length() == length ?
            string :
            b.toString();
    }

    /**
     * TRIM Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> trim() {
        return Cast.to(TRIM);
    }

    private static final StringExpressionFunctionUnary<?> TRIM = new StringExpressionFunctionUnary<>(
        "trim",
        (s, c) -> CharSequences.trim(s).toString()
    );

    /**
     * TRIMLEFT Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> trimLeft() {
        return Cast.to(TRIMLEFT);
    }

    private static final StringExpressionFunctionUnary<?> TRIMLEFT = new StringExpressionFunctionUnary<>(
        "trimLeft",
        (s, c) -> CharSequences.trimLeft(s).toString()
    );

    /**
     * TRIMRIGHT Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> trimRight() {
        return Cast.to(TRIMRIGHT);
    }

    private static final StringExpressionFunctionUnary<?> TRIMRIGHT = new StringExpressionFunctionUnary<>(
        "trimRight",
        (s, c) -> CharSequences.trimRight(s).toString()
    );

    /**
     * UPPERCASE Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionUnary<C> upperCase() {
        return Cast.to(UPPERCASE);
    }

    private static final StringExpressionFunctionUnary<?> UPPERCASE = new StringExpressionFunctionUnary<>(
        "upperCase",
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
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(TEXT);
}

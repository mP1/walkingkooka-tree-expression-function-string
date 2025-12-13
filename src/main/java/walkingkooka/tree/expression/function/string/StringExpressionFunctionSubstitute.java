
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
 * A function that supports replacing some text by another a fixed or all number of times.
 */
final class StringExpressionFunctionSubstitute<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionSubstitute<C> instance() {
        return Cast.to(SUBSTITUTE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionSubstitute<?> SUBSTITUTE = new StringExpressionFunctionSubstitute<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionSubstitute() {
        super("substitute");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final String text = TEXT.getOrFail(parameters, 0, context);
        final String oldText = OLD_TEXT.getOrFail(parameters, 1, context);
        final String newText = NEW_TEXT.getOrFail(parameters, 2, context);

        String result = text;

        final int oldTextLength = oldText.length();

        if (oldTextLength > 0 && !oldText.equals(newText)) {
            int instance = instanceParameter(parameters, context);

            final StringBuilder b = new StringBuilder();
            int i = 0;
            int from = 0;
            boolean changed = false;
            int instanceCount = 1; // BIAS start counting at 1

            final int textLength = text.length();

            do {
                final int found = text.indexOf(oldText, i);
                if (-1 == found) {
                    if (changed) {
                        // simply append the remainder of text just searched
                        b.append(text, from, textLength);
                    }

                    // else never found simply return $text
                    break;
                }

                if (0 == instance || instance == instanceCount) {
                    b.append(text, from, found);
                    b.append(newText);
                    changed = true;

                    from = found + oldTextLength;
                }
                i = found + oldTextLength;
                instanceCount++;

            } while (i < textLength);

            if (changed) {
                result = b.toString();
            }
        }

        return result;
    }

    private static <C extends ExpressionEvaluationContext> int instanceParameter(final List<Object> parameters,
                                                                                 final C context) {
        final ExpressionNumber expressionNumber = INSTANCE.get(
            parameters,
            3,
            context
        ).orElse(null);

        int instance = 0;
        if (null != expressionNumber) {
            instance = expressionNumber.intValue();
            if (instance < 1) {
                throw new IllegalArgumentException("Invalid instance < 1 got " + expressionNumber);
            }
        }
        return instance;
    }

    private final static ExpressionFunctionParameter<String> OLD_TEXT = ExpressionFunctionParameterName.with("old-text")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<String> NEW_TEXT = ExpressionFunctionParameterName.with("new-text")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<ExpressionNumber> INSTANCE = ExpressionFunctionParameterName.with("instance")
        .optional(ExpressionNumber.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
        TEXT,
        OLD_TEXT,
        NEW_TEXT,
        INSTANCE
    );
}

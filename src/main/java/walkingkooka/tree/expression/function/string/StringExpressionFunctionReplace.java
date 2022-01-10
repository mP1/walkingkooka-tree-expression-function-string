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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that replaces a part of a text with a new text returning the result.
 * <br>
 * - The startPos starts at 1
 * - all string bounds are clipped.
 * <br>
 * https://exceljet.net/excel-functions/excel-replace-function
 */
final class StringExpressionFunctionReplace<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionReplace<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionReplace<?> INSTANCE = new StringExpressionFunctionReplace<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionReplace() {
        super("replace");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkOnlyRequiredParameters(parameters);

        final String oldText = OLD_TEXT.getOrFail(parameters, 0);

        final int startPos = START_POS.getOrFail(parameters, 1)
                .intValue(); // BIAS_ONE
        if (startPos < 1) {
            throw new IllegalArgumentException("Invalid start pos " + startPos + " < 1");
        }

        final int charCount = CHAR_COUNT.getOrFail(parameters, 2).intValue();
        if (charCount < 0) {
            throw new IllegalArgumentException("Invalid charCount " + charCount + " < 0");
        }

        final String newText = OLD_TEXT.getOrFail(parameters, 3);

        final int oldTextLength = oldText.length();

        final StringBuilder answer = new StringBuilder();

        answer.append(
                oldText,
                0,
                Math.min(startPos - 1, oldTextLength)
        );

        answer.append(newText);

        answer.append(
                oldText,
                Math.min(startPos - 1 + charCount, oldTextLength),
                oldTextLength
        );

        return answer.toString();
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<String> OLD_TEXT = ExpressionFunctionParameterName.with("old-text")
            .setType(String.class);

    private final static ExpressionFunctionParameter<ExpressionNumber> START_POS = ExpressionFunctionParameterName.with("start-pos")
            .setType(ExpressionNumber.class);

    private final static ExpressionFunctionParameter<ExpressionNumber> CHAR_COUNT = ExpressionFunctionParameterName.with("character-count")
            .setType(ExpressionNumber.class);

    private final static ExpressionFunctionParameter<String> NEW_TEXT = ExpressionFunctionParameterName.with("new-text")
            .setType(String.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            OLD_TEXT,
            START_POS,
            CHAR_COUNT,
            NEW_TEXT
    );
}

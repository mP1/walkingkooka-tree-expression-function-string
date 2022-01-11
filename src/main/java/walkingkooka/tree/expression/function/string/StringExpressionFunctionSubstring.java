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
 * A function that returns a substring of another string.<br>
 * <a href="https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/substring"></a>
 */
final class StringExpressionFunctionSubstring<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {

    /**
     * Factory that returns a matching {@link StringExpressionFunctionSubstring}
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionSubstring<C> with(final int indexBase) {
        StringExpressionFunctionSubstring<C> result;
        switch (indexBase) {
            case 0:
                result = Cast.to(ZERO);
                break;
            case 1:
                result = Cast.to(ONE);
                break;
            default:
                throw new IllegalArgumentException("Invalid indexBase " + indexBase + " expected either 0 or 1");
        }
        return result;
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionSubstring<?> ZERO = new StringExpressionFunctionSubstring<>(0);

    /**
     * Singleton
     */
    private static final StringExpressionFunctionSubstring<?> ONE = new StringExpressionFunctionSubstring<>(1);

    /**
     * Private ctor
     */
    private StringExpressionFunctionSubstring(final int indexBase) {
        super("substring");
        this.indexBase = indexBase;
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        final int parameterCount = parameters.size();

        final String string;
        final int offset;
        final int length;

        switch (parameterCount) {
            case 2:
                string = TEXT.getOrFail(parameters, 0);
                offset = OFFSET.getOrFail(parameters, 1).intValue();
                length = string.length() - offset + this.indexBase;
                break;
            case 3:
                string = TEXT.getOrFail(parameters, 0);
                offset = OFFSET.getOrFail(parameters, 1).intValue();
                length = LENGTH.getOrFail(parameters, 2).intValue();
                break;
            default:
                throw new IllegalArgumentException("Expected 2 or 3 parameters (String, offset, [length]) but got " + parameterCount);
        }

        final int zeroOffset = offset - this.indexBase;

        return string.substring(zeroOffset, length + zeroOffset);
    }

    private final int indexBase;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> OFFSET = ExpressionFunctionParameterName.with("offset")
            .required(ExpressionNumber.class);

    private final static ExpressionFunctionParameter<ExpressionNumber> LENGTH = ExpressionFunctionParameterName.with("length")
            .optional(ExpressionNumber.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEXT,
            OFFSET,
            LENGTH
    );
}

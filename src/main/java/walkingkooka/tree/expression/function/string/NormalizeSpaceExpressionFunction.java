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
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

/**
 * Returns a string after removing any leading or trailing spaces and normalizes multiple sequences of spaces into a single space.<br>
 * <a href="https://developer.mozilla.org/en-US/docs/Web/XPath/Functions/normalize-space></a>
 * <br>
 * <pre>
 * The string to be normalized. If omitted, string used will be the same as the context node converted to a string.
 * </pre>
 * Unlike the mention in the mozilla document, if the argument is missing, an exception will be thrown.
 */
final class NormalizeSpaceExpressionFunction<C extends ExpressionFunctionContext> extends UnaryStringExpressionFunction<String, C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> NormalizeSpaceExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NormalizeSpaceExpressionFunction INSTANCE = new NormalizeSpaceExpressionFunction();

    /**
     * Private ctor
     */
    private NormalizeSpaceExpressionFunction() {
        super();
    }

    @Override
    String applyString(final String value,
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

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("normalize-space");
}

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

import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

/**
 * Returns the unicode of the first character in the provided {@link String}
 * <a href="https://support.google.com/docs/answer/9149523?hl=en&ref_topic=3105625"></a>
 */
final class UnicodeExpressionFunction extends UnaryStringExpressionFunction<Number> {
    /**
     * Singleton
     */
    static final UnicodeExpressionFunction INSTANCE = new UnicodeExpressionFunction();

    /**
     * Private ctor
     */
    private UnicodeExpressionFunction() {
        super();
    }

    @Override
    Number applyString(final String string,
                       final ExpressionFunctionContext context) {
        if (string.length() == 0) {
            throw new IllegalArgumentException("Unicode requires a string with at least 1 character");
        }
        return (int) string.charAt(0);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("unicode");
}

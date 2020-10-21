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
 * Performs a right for a given {@link String}. Defaults to 1 if the number or second parameter is absent and uses 0 if the 2nd parameter is negative.
 * <a href="https://support.google.com/docs/answer/3094087?hl=en&ref_topic=3105625">RIGHT</a>
 */
final class RightStringExpressionFunction extends StringOptionalNumberStringExpressionFunction {

    /**
     * Singleton
     */
    static final RightStringExpressionFunction INSTANCE = new RightStringExpressionFunction();

    /**
     * Private ctor
     */
    private RightStringExpressionFunction() {
        super();
    }

    @Override
    String applyStringInteger(final String string,
                              final int length,
                              final ExpressionFunctionContext context) {
        final int stringLength = string.length();

        return string.substring(Math.max(0, Math.min(stringLength - length, stringLength)), stringLength);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("right");
}

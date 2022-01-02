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
import walkingkooka.text.CharSequences;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

/**
 * Performs a trim right after converting the value to a {@link String}.
 */
final class StringExpressionFunctionUnaryTrimRight<C extends ExpressionFunctionContext> extends StringExpressionFunctionUnary<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionUnaryTrimRight<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionUnaryTrimRight<?> INSTANCE = new StringExpressionFunctionUnaryTrimRight<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionUnaryTrimRight() {
        super("trim-right");
    }

    @Override
    String applyString(final String value,
                       final ExpressionFunctionContext context) {
        return CharSequences.trimRight(value).toString();
    }
}

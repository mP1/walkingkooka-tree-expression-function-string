
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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * A function that cleans (removes unprintable control characters from the given {@link String}.
 */
final class StringExpressionFunctionClean<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionClean<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionClean<?> INSTANCE = new StringExpressionFunctionClean<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionClean() {
        super("clean");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkOnlyRequiredParameters(parameters);

        final String text = TEXT.getOrFail(parameters, 0);

        final StringBuilder clean = new StringBuilder();

        for (final char c : text.toCharArray()) {
            if (c >= 32) {
                clean.append(c);
            }
        }

        return clean.length() == text.length() ?
                text :
                clean.toString();
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(TEXT);
}

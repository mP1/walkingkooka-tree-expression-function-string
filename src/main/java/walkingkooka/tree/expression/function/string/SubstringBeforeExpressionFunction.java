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
 * A function that returns the part of string1 up before the first occurrence of string2
 */
final class SubstringBeforeExpressionFunction<C extends ExpressionFunctionContext> extends StringStringStringExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> SubstringBeforeExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final SubstringBeforeExpressionFunction<?> INSTANCE = new SubstringBeforeExpressionFunction<>();

    /**
     * Private ctor
     */
    private SubstringBeforeExpressionFunction() {
        super();
    }

    @Override
    String applyStringString(final String string,
                             final String find,
                             final ExpressionFunctionContext context) {
        final int offset = string.indexOf(find);

        return -1 != offset ?
                string.substring(0, offset) :
                "";
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("substring-before");
}

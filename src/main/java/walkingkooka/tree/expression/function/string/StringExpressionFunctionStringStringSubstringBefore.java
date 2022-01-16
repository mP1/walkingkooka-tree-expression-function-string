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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that returns the part of string1 up before the first occurrence of string2
 */
final class StringExpressionFunctionStringStringSubstringBefore<C extends ExpressionFunctionContext> extends StringExpressionFunctionStringString<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> StringExpressionFunctionStringStringSubstringBefore<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionStringStringSubstringBefore<?> INSTANCE = new StringExpressionFunctionStringStringSubstringBefore<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionStringStringSubstringBefore() {
        super("substring-before");
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
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    @Override
    ExpressionFunctionParameter<String> secondParameter() {
        return BEFORE;
    }

    private final static ExpressionFunctionParameter<String> BEFORE = ExpressionFunctionParameterName.with("before")
            .required(String.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEXT,
            BEFORE
    );
}

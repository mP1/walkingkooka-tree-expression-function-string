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

import walkingkooka.Cast;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Returns the unicode of the first character in the provided {@link String}
 * <a href="https://support.google.com/docs/answer/9149523?hl=en&ref_topic=3105625"></a>
 */
final class UnicodeExpressionFunction<C extends ExpressionFunctionContext> implements ExpressionFunction<ExpressionNumber, C> {
    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> UnicodeExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final UnicodeExpressionFunction<?> INSTANCE = new UnicodeExpressionFunction<>();

    /**
     * Private ctor
     */
    private UnicodeExpressionFunction() {
        super();
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        this.checkOnlyRequiredParameters(parameters);

        final String string = TEXT.getOrFail(parameters, 0);
        if (string.length() == 0) {
            throw new IllegalArgumentException("Unicode requires a string with at least 1 character");
        }

        return context.expressionNumberKind()
                .create((int) string.charAt(0));
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("unicode");

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<String> TEXT = ExpressionFunctionParameterName.with("text")
            .setType(String.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(TEXT);

    @Override
    public boolean lsLastParameterVariable() {
        return false;
    }

    @Override
    public boolean resolveReferences() {
        return false;
    }

    @Override
    public boolean isPure(final ExpressionPurityContext context) {
        return true;
    }

    @Override
    public String toString() {
        return this.name().toString();
    }
}

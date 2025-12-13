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
import walkingkooka.text.CharSequences;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A concat function with a few extra parameters, that matches the semantics of EXCELs TEXTJOIN.
 * <br>
 * This assumes that any references have been expanded inline and not replaced by a List&lt;String&gt;
 * <br>
 * https://exceljet.net/excel-functions/excel-textjoin-function
 */
final class StringExpressionFunctionTextJoin<C extends ExpressionEvaluationContext> extends StringExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> StringExpressionFunctionTextJoin<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final StringExpressionFunctionTextJoin<?> INSTANCE = new StringExpressionFunctionTextJoin<>();

    /**
     * Private ctor
     */
    private StringExpressionFunctionTextJoin() {
        super("textJoin");
    }

    @Override
    public String apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final String delimiter = DELIMITER.getOrFail(parameters, 0, context);
        final boolean ignoreEmpty = IGNORE_EMPTY.getOrFail(parameters, 1, context);
        final String text = TEXT.getOrFail(parameters, 2, context);
        final List<String> moreText = MORE_TEXT.getVariable(parameters, 3);

        return Stream.concat(
                Stream.of(text),
                moreText.stream()
            )
            .filter(s -> !CharSequences.isNullOrEmpty(s) || !ignoreEmpty)
            .map(s -> null == s ? "" : s)
            .collect(Collectors.joining(delimiter));
    }

    private final static ExpressionFunctionParameter<String> DELIMITER = ExpressionFunctionParameterName.with("delimiter")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<Boolean> IGNORE_EMPTY = ExpressionFunctionParameterName.with("ignore-empty")
        .required(Boolean.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<String> MORE_TEXT = ExpressionFunctionParameterName.with("more-text")
        .variable(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_FLATTEN_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
        DELIMITER,
        IGNORE_EMPTY,
        TEXT,
        MORE_TEXT
    );
}


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

import walkingkooka.text.CaseSensitivity;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Abstract class that captures the common code for both the search and find functions.
 */
abstract class NumberExpressionFunctionSearchOrFind<C extends ExpressionEvaluationContext> extends NumberExpressionFunction<C> {

    /**
     * Package private ctor
     */
    NumberExpressionFunctionSearchOrFind(final String name,
                                         final CaseSensitivity caseSensitivity) {
        super(name);
        this.caseSensitivity = caseSensitivity;
    }

    @Override
    public final ExpressionNumber apply(final List<Object> parameters,
                                        final C context) {
        this.checkParameterCount(parameters);

        final ExpressionNumberKind kind = context.expressionNumberKind();

        final String find = FIND.getOrFail(parameters, 0);
        final String within = WITHIN.getOrFail(parameters, 1);
        final int startPos = START_POS.get(parameters, 2)
            .orElse(kind.one())
            .intValue(); // base 0

        final int length = within.length();

        // check here necessary because CaseSensitivity.startsWith doesn't throw if startPos is OOB.
        if (startPos < 1 || startPos > length) {
            throw new StringIndexOutOfBoundsException("StartPos " + startPos + " not within 1 and " + length);
        }

        final int result = this.apply(
            find,
            within,
            startPos - INDEX_BIAS
        );

        return kind.create(
            result == -1 ?
                NOT_FOUND_INDEX :
                INDEX_BIAS + result
        );
    }

    final CaseSensitivity caseSensitivity;

    /**
     * Perform the search or find, with the startPos and result both zero bias numbers.
     */
    abstract int apply(final String find,
                       final String within,
                       final int startPos);

    private final static ExpressionFunctionParameter<String> FIND = ExpressionFunctionParameterName.with("find")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<String> WITHIN = ExpressionFunctionParameterName.with("within")
        .required(String.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<ExpressionNumber> START_POS = ExpressionFunctionParameterName.with("start-pos")
        .optional(ExpressionNumber.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
        FIND,
        WITHIN,
        START_POS
    );
}

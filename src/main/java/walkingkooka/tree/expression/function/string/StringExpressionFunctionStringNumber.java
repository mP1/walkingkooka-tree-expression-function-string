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

import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that requires a {@link String} and an optional {@link Number} returning a {@link String} result.
 */
abstract class StringExpressionFunctionStringNumber<C extends ExpressionFunctionContext> extends StringExpressionFunction<C> {

    /**
     * Package private ctor
     */
    StringExpressionFunctionStringNumber() {
        super();
    }

    @Override
    public final String apply(final List<Object> parameters,
                              final C context) {
        String result;

        final int count = parameters.size();
        switch (count) {
            case 1:
                result = this.applyStringInteger(
                        TEXT.getOrFail(parameters, 0),
                        1
                );
                break;
            case 2:
                result = this.applyStringInteger(
                        TEXT.getOrFail(parameters, 0),
                        LENGTH.getOrFail(parameters, 1).intValue()
                );
                break;
            default:
                throw new IllegalArgumentException("Expected 1 or 2 parameters but got " + count + "=" + parameters);
        }

        return result;
    }

    abstract String applyStringInteger(final String string,
                                       final int number);

    @Override
    public final List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> LENGTH = ExpressionFunctionParameterName.with("length")
            .setType(ExpressionNumber.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEXT,
            LENGTH
    );
}

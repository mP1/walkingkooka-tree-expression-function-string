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

import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.List;

/**
 * A function that requires a {@link String} and an optional {@link Number} returning a {@link String} result.
 */
abstract class StringOptionalNumberStringExpressionFunction<C extends ExpressionFunctionContext> extends StringExpressionFunction<String, C> {

    /**
     * Package private ctor
     */
    StringOptionalNumberStringExpressionFunction() {
        super();
    }

    @Override
    public final String apply(final List<Object> parameters,
                              final C context) {
        String result;

        final int count = parameters.size();
        switch (count) {
            case 1:
                result = this.applyStringInteger(this.stringParameter0(parameters, context), 1, context);
                break;
            case 2:
                result = this.applyStringInteger(this.stringParameter0(parameters, context),
                        this.numberParameter1(parameters, context),
                        context);
                break;
            default:
                throw new IllegalArgumentException("Expected 1 or 2 parameters but got " + count + "=" + parameters);
        }

        return result;
    }

    private String stringParameter0(final List<Object> parameters,
                                    final ExpressionFunctionContext context) {
        return this.string(parameters, 0, context);
    }

    private int numberParameter1(final List<Object> parameters,
                                 final ExpressionFunctionContext context) {
        return this.integer(parameters, 1, context);
    }

    abstract String applyStringInteger(final String string,
                                       final int number,
                                       final ExpressionFunctionContext context);
}

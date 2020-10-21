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

import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.List;

/**
 * The excel mid function.
 * <a href="https://support.google.com/docs/answer/3094129?hl=en&ref_topic=3105625>MID</a>
 */
final class MidStringExpressionFunction extends StringExpressionFunction<String> {

    /**
     * Singleton
     */
    static final MidStringExpressionFunction INSTANCE = new MidStringExpressionFunction();

    /**
     * Private ctor
     */
    private MidStringExpressionFunction() {
        super();
    }

    @Override
    public String apply(final List<Object> parameters,
                        final ExpressionFunctionContext context) {
        this.checkParameterCount(parameters, 3);

        final String string = this.string(parameters, 0, context);
        final int start = this.integer(parameters, 1, context);
        final int length = this.integer(parameters, 2, context);

        final int stringLength = string.length();

        return start >= stringLength || start + length <= 0 ?
                "" : // return empty if start or end is out of bounds
                string.substring(Math.max(0, start), Math.min(start + length, stringLength)); // ensure start & length are within bounds
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("mid");
}

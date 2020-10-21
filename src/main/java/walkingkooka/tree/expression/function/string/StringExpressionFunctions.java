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

import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.function.ExpressionFunction;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class StringExpressionFunctions implements PublicStaticHelper {

    /**
     * {@see ConcatExpressionFunction}
     */
    public static ExpressionFunction<String> concat() {
        return ConcatExpressionFunction.INSTANCE;
    }

    /**
     * {@see ContainsExpressionFunction}
     */
    public static ExpressionFunction<Boolean> contains() {
        return ContainsExpressionFunction.INSTANCE;
    }

    /**
     * {@see EndsWithExpressionFunction}
     */
    public static ExpressionFunction<Boolean> endsWith() {
        return EndsWithExpressionFunction.INSTANCE;
    }

    /**
     * {@see LeftStringExpressionFunction}
     */
    public static ExpressionFunction<String> left() {
        return LeftStringExpressionFunction.INSTANCE;
    }

    /**
     * {@see LowerCaseStringExpressionFunction}
     */
    public static ExpressionFunction<String> lowerCase() {
        return LowerCaseStringExpressionFunction.INSTANCE;
    }

    /**
     * {@see NormalizeSpaceExpressionFunction}
     */
    public static ExpressionFunction<String> normalizeSpace() {
        return NormalizeSpaceExpressionFunction.INSTANCE;
    }

    /**
     * {@see RightStringExpressionFunction}
     */
    public static ExpressionFunction<String> right() {
        return RightStringExpressionFunction.INSTANCE;
    }

    /**
     * {@see StartsWithExpressionFunction}
     */
    public static ExpressionFunction<Boolean> startsWith() {
        return StartsWithExpressionFunction.INSTANCE;
    }

    /**
     * {@see StringLengthExpressionFunction}
     */
    public static ExpressionFunction<Number> stringLength() {
        return StringLengthExpressionFunction.INSTANCE;
    }

    /**
     * {@see SubstringExpressionFunction}
     */
    public static ExpressionFunction<String> substring(final int indexBias) {
        return SubstringExpressionFunction.with(indexBias);
    }

    /**
     * {@see SubstringAfterExpressionFunction}
     */
    public static ExpressionFunction<String> substringAfter() {
        return SubstringAfterExpressionFunction.INSTANCE;
    }

    /**
     * {@see SubstringBeforeExpressionFunction}
     */
    public static ExpressionFunction<String> substringBefore() {
        return SubstringBeforeExpressionFunction.INSTANCE;
    }

    /**
     * {@see TextExpressionFunction}
     */
    public static ExpressionFunction<String> text() {
        return TextExpressionFunction.INSTANCE;
    }

    /**
     * {@see TrimLeftStringExpressionFunction}
     */
    public static ExpressionFunction<String> trimLeft() {
        return TrimLeftStringExpressionFunction.INSTANCE;
    }

    /**
     * {@see TrimRightStringExpressionFunction}
     */
    public static ExpressionFunction<String> trimRight() {
        return TrimRightStringExpressionFunction.INSTANCE;
    }

    /**
     * {@see UpperCaseStringExpressionFunction}
     */
    public static ExpressionFunction<String> upperCase() {
        return UpperCaseStringExpressionFunction.INSTANCE;
    }

    /**
     * Stops creation
     */
    private StringExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}

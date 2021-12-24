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

import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.function.Consumer;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class StringExpressionFunctions implements PublicStaticHelper {

    /**
     * Visit all {@link ExpressionFunction functions}.
     */
    public static void visit(final int indexBias,
                             final Consumer<ExpressionFunction<?, ?>> consumer) {
        Lists.of(character(),
                concat(),
                contains(),
                endsWith(),
                left(),
                lowerCase(),
                mid(),
                normalizeSpace(),
                right(),
                startsWith(),
                stringLength(),
                substring(indexBias),
                substringAfter(),
                substringBefore(),
                text(),
                trimLeft(),
                trimRight(),
                unicode(),
                upperCase())
                .forEach(consumer);
    }

    /**
     * {@see CharExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> character() {
        return CharExpressionFunction.instance();
    }

    /**
     * {@see ConcatExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> concat() {
        return ConcatExpressionFunction.instance();
    }

    /**
     * {@see ContainsExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> contains() {
        return ContainsExpressionFunction.instance();
    }

    /**
     * {@see EndsWithExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> endsWith() {
        return EndsWithExpressionFunction.instance();
    }

    /**
     * {@see LeftStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> left() {
        return LeftStringExpressionFunction.instance();
    }

    /**
     * {@see LowerCaseStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> lowerCase() {
        return LowerCaseStringExpressionFunction.instance();
    }

    /**
     * {@see MidStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> mid() {
        return MidStringExpressionFunction.instance();
    }

    /**
     * {@see NormalizeSpaceExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> normalizeSpace() {
        return NormalizeSpaceExpressionFunction.instance();
    }

    /**
     * {@see RightStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> right() {
        return RightStringExpressionFunction.instance();
    }

    /**
     * {@see StartsWithExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> startsWith() {
        return StartsWithExpressionFunction.instance();
    }

    /**
     * {@see StringLengthExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> stringLength() {
        return StringLengthExpressionFunction.instance();
    }

    /**
     * {@see SubstringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> substring(final int indexBias) {
        return SubstringExpressionFunction.with(indexBias);
    }

    /**
     * {@see SubstringAfterExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> substringAfter() {
        return SubstringAfterExpressionFunction.instance();
    }

    /**
     * {@see SubstringBeforeExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> substringBefore() {
        return SubstringBeforeExpressionFunction.instance();
    }

    /**
     * {@see TextExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> text() {
        return TextExpressionFunction.instance();
    }

    /**
     * {@see TrimLeftStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> trimLeft() {
        return TrimLeftStringExpressionFunction.instance();
    }

    /**
     * {@see TrimRightStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> trimRight() {
        return TrimRightStringExpressionFunction.instance();
    }

    /**
     * {@see UnicodeExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> unicode() {
        return UnicodeExpressionFunction.instance();
    }

    /**
     * {@see UpperCaseStringExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> upperCase() {
        return UpperCaseStringExpressionFunction.instance();
    }

    /**
     * Stops creation
     */
    private StringExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}

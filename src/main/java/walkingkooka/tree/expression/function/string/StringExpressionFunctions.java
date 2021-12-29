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
     * {@see StringExpressionFunctionChar}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> character() {
        return StringExpressionFunctionChar.instance();
    }

    /**
     * {@see StringExpressionFunctionConcat}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> concat() {
        return StringExpressionFunctionConcat.instance();
    }

    /**
     * {@see BooleanExpressionFunctionContains}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> contains() {
        return BooleanExpressionFunctionContains.instance();
    }

    /**
     * {@see BooleanExpressionFunctionEndsWith}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> endsWith() {
        return BooleanExpressionFunctionEndsWith.instance();
    }

    /**
     * {@see StringExpressionFunctionStringNumberLeft}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> left() {
        return StringExpressionFunctionStringNumberLeft.instance();
    }

    /**
     * {@see StringExpressionFunctionUnaryLowerCase}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> lowerCase() {
        return StringExpressionFunctionUnaryLowerCase.instance();
    }

    /**
     * {@see StringExpressionFunctionMid}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> mid() {
        return StringExpressionFunctionMid.instance();
    }

    /**
     * {@see StringExpressionFunctionUnaryNormalizeSpace}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> normalizeSpace() {
        return StringExpressionFunctionUnaryNormalizeSpace.instance();
    }

    /**
     * {@see StringExpressionFunctionStringNumberRight}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> right() {
        return StringExpressionFunctionStringNumberRight.instance();
    }

    /**
     * {@see BooleanExpressionFunctionStartsWith}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> startsWith() {
        return BooleanExpressionFunctionStartsWith.instance();
    }

    /**
     * {@see StringLengthExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> stringLength() {
        return StringLengthExpressionFunction.instance();
    }

    /**
     * {@see StringExpressionFunctionSubstring}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> substring(final int indexBias) {
        return StringExpressionFunctionSubstring.with(indexBias);
    }

    /**
     * {@see StringExpressionFunctionStringStringSubstringAfter}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> substringAfter() {
        return StringExpressionFunctionStringStringSubstringAfter.instance();
    }

    /**
     * {@see StringExpressionFunctionStringStringSubstringBefore}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> substringBefore() {
        return StringExpressionFunctionStringStringSubstringBefore.instance();
    }

    /**
     * {@see StringExpressionFunctionText}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> text() {
        return StringExpressionFunctionText.instance();
    }

    /**
     * {@see StringExpressionFunctionUnaryTrimLeft}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> trimLeft() {
        return StringExpressionFunctionUnaryTrimLeft.instance();
    }

    /**
     * {@see StringExpressionFunctionUnaryTrimRight}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> trimRight() {
        return StringExpressionFunctionUnaryTrimRight.instance();
    }

    /**
     * {@see UnicodeExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> unicode() {
        return UnicodeExpressionFunction.instance();
    }

    /**
     * {@see StringExpressionFunctionUnaryUpperCase}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> upperCase() {
        return StringExpressionFunctionUnaryUpperCase.instance();
    }

    /**
     * Stops creation
     */
    private StringExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}

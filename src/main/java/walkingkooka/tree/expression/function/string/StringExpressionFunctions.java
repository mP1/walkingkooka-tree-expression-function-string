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
                        ascii(),
                        character(),
                        clean(),
                        concat(),
                        contains(),
                        endsWith(),
                        equalsCaseInsensitive(),
                        equalsCaseSensitive(),
                        isNonText(),
                        isText(),
                        left(),
                        lowerCase(),
                        mid(),
                        normalizeSpace(),
                        repeat(),
                        replace(),
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
     * {@see CharacterExpressionFunctionAsciiAndChar#ascii}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Character, C> ascii() {
        return CharacterExpressionFunctionAsciiAndChar.ascii();
    }

    /**
     * {@see CharacterExpressionFunctionAsciiAndChar#character}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Character, C> character() {
        return CharacterExpressionFunctionAsciiAndChar.character();
    }

    /**
     * {@see CharacterExpressionFunctionClean}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> clean() {
        return StringExpressionFunctionClean.instance();
    }

    /**
     * {@see StringExpressionFunctionConcat}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> concat() {
        return StringExpressionFunctionConcat.instance();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#contains}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> contains() {
        return BooleanExpressionFunctionContainsStartsEnds.contains();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#endsWith}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> endsWith() {
        return BooleanExpressionFunctionContainsStartsEnds.endsWith();
    }

    /**
     * {@see BooleanExpressionFunctionEquals.caseInsensitive}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> equalsCaseInsensitive() {
        return BooleanExpressionFunctionEquals.caseInsensitive();
    }

    /**
     * {@see BooleanExpressionFunctionEquals.caseSensitive}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> equalsCaseSensitive() {
        return BooleanExpressionFunctionEquals.caseSensitive();
    }

    /**
     * {@see BooleanExpressionFunctionIsTextIsNonText#isNonText}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> isNonText() {
        return BooleanExpressionFunctionIsTextIsNonText.isNonText();
    }

    /**
     * {@see BooleanExpressionFunctionIsTextIsNonText#isText}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> isText() {
        return BooleanExpressionFunctionIsTextIsNonText.isText();
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
     * {@see StringExpressionFunctionRepeat}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> repeat() {
        return StringExpressionFunctionRepeat.instance();
    }

    /**
     * {@see StringExpressionFunctionReplace}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> replace() {
        return StringExpressionFunctionReplace.instance();
    }

    /**
     * {@see StringExpressionFunctionStringNumberRight}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<String, C> right() {
        return StringExpressionFunctionStringNumberRight.instance();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#startsWith}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> startsWith() {
        return BooleanExpressionFunctionContainsStartsEnds.startsWith();
    }

    /**
     * {@see NumberExpressionFunctionLength}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> stringLength() {
        return NumberExpressionFunctionLength.instance();
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
     * {@see NumberExpressionFunctionUnicode}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> unicode() {
        return NumberExpressionFunctionUnicode.instance();
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

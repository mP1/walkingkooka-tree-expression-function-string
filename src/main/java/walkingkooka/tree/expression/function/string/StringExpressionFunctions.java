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

import walkingkooka.collect.set.Sets;
import walkingkooka.net.Url;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.provider.ExpressionFunctionProvider;
import walkingkooka.tree.expression.function.provider.ExpressionFunctionProviders;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class StringExpressionFunctions implements PublicStaticHelper {

    /**
     * An {@link ExpressionFunctionProvider} with all the functions in this project.
     */
    public static ExpressionFunctionProvider expressionFunctionProvider() {
        return ExpressionFunctionProviders.basic(
                Url.parseAbsolute("https://github.com/mP1/walkingkooka-tree-expression-function-string/"),
                Sets.of(
                        ascii(),
                        character(),
                        clean(),
                        concat(),
                        containsCaseInsensitive(),
                        containsCaseSensitive(),
                        endsWithCaseInsensitive(),
                        endsWithCaseSensitive(),
                        equalsCaseInsensitive(),
                        equalsCaseSensitive(),
                        findCaseSensitive(),
                        findCaseInsensitive(),
                        isNonText(),
                        isText(),
                        left(),
                        lowerCase(),
                        mid(),
                        normalizeSpace(),
                        proper(),
                        repeat(),
                        replace(),
                        right(),
                        searchCaseInsensitive(),
                        searchCaseSensitive(),
                        spaceTrim(),
                        startsWithCaseInsensitive(),
                        startsWithCaseSensitive(),
                        stringLength(),
                        substitute(),
                        substring(),
                        substringAfter(),
                        substringBefore(),
                        t(),
                        text(),
                        textJoin(),
                        trim(),
                        trimLeft(),
                        trimRight(),
                        unicode(),
                        upperCase(),
                        value()
                )
        );
    }

    /**
     * {@see CharacterExpressionFunctionAsciiAndChar#ascii}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Character, C> ascii() {
        return CharacterExpressionFunctionAsciiAndChar.ascii();
    }

    /**
     * {@see CharacterExpressionFunctionAsciiAndChar#character}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Character, C> character() {
        return CharacterExpressionFunctionAsciiAndChar.character();
    }

    /**
     * {@see CharacterExpressionFunctionClean}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> clean() {
        return StringExpressionFunctionClean.instance();
    }

    /**
     * {@see StringExpressionFunctionConcat}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> concat() {
        return StringExpressionFunctionConcat.instance();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#containsCaseInsensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> containsCaseInsensitive() {
        return BooleanExpressionFunctionContainsStartsEnds.containsCaseInsensitive();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#containsCaseSensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> containsCaseSensitive() {
        return BooleanExpressionFunctionContainsStartsEnds.containsCaseSensitive();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#endsWithCaseInsensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> endsWithCaseInsensitive() {
        return BooleanExpressionFunctionContainsStartsEnds.endsWithCaseInsensitive();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#endsWithCaseSensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> endsWithCaseSensitive() {
        return BooleanExpressionFunctionContainsStartsEnds.endsWithCaseSensitive();
    }

    /**
     * {@see BooleanExpressionFunctionEquals.caseInsensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> equalsCaseInsensitive() {
        return BooleanExpressionFunctionEquals.caseInsensitive();
    }

    /**
     * {@see BooleanExpressionFunctionEquals.caseSensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> equalsCaseSensitive() {
        return BooleanExpressionFunctionEquals.caseSensitive();
    }

    /**
     * {@see NumberExpressionFunctionSearchOrFindFind#caseSensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> findCaseSensitive() {
        return NumberExpressionFunctionSearchOrFindFind.caseSensitive();
    }

    /**
     * {@see NumberExpressionFunctionSearchOrFindFind#caseInsensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> findCaseInsensitive() {
        return NumberExpressionFunctionSearchOrFindFind.caseInsensitive();
    }

    /**
     * {@see BooleanExpressionFunctionIsTextIsNonText#isNonText}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isNonText() {
        return BooleanExpressionFunctionIsTextIsNonText.isNonText();
    }

    /**
     * {@see BooleanExpressionFunctionIsTextIsNonText#isText}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isText() {
        return BooleanExpressionFunctionIsTextIsNonText.isText();
    }

    /**
     * {@see StringExpressionFunctionStringNumberLeft}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> left() {
        return StringExpressionFunctionStringNumberLeft.instance();
    }

    /**
     * {@see StringExpressionFunctionUnary#lowerCase}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> lowerCase() {
        return StringExpressionFunctionUnary.lowerCase();
    }

    /**
     * {@see StringExpressionFunctionMid}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> mid() {
        return StringExpressionFunctionMid.instance();
    }

    /**
     * {@see StringExpressionFunctionUnary#normalizeSpace}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> normalizeSpace() {
        return StringExpressionFunctionUnary.normalizeSpace();
    }

    /**
     * {@see StringExpressionFunctionUnary#proper}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> proper() {
        return StringExpressionFunctionUnary.proper();
    }

    /**
     * {@see StringExpressionFunctionRepeat}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> repeat() {
        return StringExpressionFunctionRepeat.instance();
    }

    /**
     * {@see StringExpressionFunctionReplace}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> replace() {
        return StringExpressionFunctionReplace.instance();
    }

    /**
     * {@see StringExpressionFunctionStringNumberRight}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> right() {
        return StringExpressionFunctionStringNumberRight.instance();
    }

    /**
     * {@see NumberExpressionFunctionSearchOrFindSearch#caseInsensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> searchCaseInsensitive() {
        return NumberExpressionFunctionSearchOrFindSearch.caseInsensitive();
    }

    /**
     * {@see NumberExpressionFunctionSearchOrFindSearch#caseSensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> searchCaseSensitive() {
        return NumberExpressionFunctionSearchOrFindSearch.caseSensitive();
    }

    /**
     * {@see StringExpressionFunctionUnary#spaceTrim}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> spaceTrim() {
        return StringExpressionFunctionUnary.spaceTrim();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#startsWithCaseInsensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> startsWithCaseInsensitive() {
        return BooleanExpressionFunctionContainsStartsEnds.startsWithCaseInsensitive();
    }

    /**
     * {@see BooleanExpressionFunctionContainsStartsEnds#startsWithCaseSensitive}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> startsWithCaseSensitive() {
        return BooleanExpressionFunctionContainsStartsEnds.startsWithCaseSensitive();
    }

    /**
     * {@see NumberExpressionFunctionLength}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> stringLength() {
        return NumberExpressionFunctionLength.instance();
    }

    /**
     * {@see StringExpressionFunctionSubstitute}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> substitute() {
        return StringExpressionFunctionSubstitute.instance();
    }

    /**
     * {@see StringExpressionFunctionSubstring}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> substring() {
        return StringExpressionFunctionSubstring.instance();
    }

    /**
     * {@see StringExpressionFunctionStringStringSubstringAfter}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> substringAfter() {
        return StringExpressionFunctionStringStringSubstringAfter.instance();
    }

    /**
     * {@see StringExpressionFunctionStringStringSubstringBefore}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> substringBefore() {
        return StringExpressionFunctionStringStringSubstringBefore.instance();
    }

    /**
     * {@see StringExpressionFunctionT}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> t() {
        return StringExpressionFunctionT.instance();
    }

    /**
     * {@see StringExpressionFunctionText}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> text() {
        return StringExpressionFunctionText.instance();
    }

    /**
     * {@see StringExpressionFunctionTextJoin}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> textJoin() {
        return StringExpressionFunctionTextJoin.instance();
    }

    /**
     * {@see StringExpressionFunctionUnary#trim}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> trim() {
        return StringExpressionFunctionUnary.trim();
    }

    /**
     * {@see StringExpressionFunctionUnary#trimLeft}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> trimLeft() {
        return StringExpressionFunctionUnary.trimLeft();
    }

    /**
     * {@see StringExpressionFunctionUnary#trimRight}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> trimRight() {
        return StringExpressionFunctionUnary.trimRight();
    }

    /**
     * {@see NumberExpressionFunctionUnicode}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> unicode() {
        return NumberExpressionFunctionUnicode.instance();
    }

    /**
     * {@see StringExpressionFunctionUnary#upperCase}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<String, C> upperCase() {
        return StringExpressionFunctionUnary.upperCase();
    }

    /**
     * {@see NumberExpressionFunctionValue}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<ExpressionNumber, C> value() {
        return NumberExpressionFunctionValue.instance();
    }

    /**
     * Stops creation
     */
    private StringExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}

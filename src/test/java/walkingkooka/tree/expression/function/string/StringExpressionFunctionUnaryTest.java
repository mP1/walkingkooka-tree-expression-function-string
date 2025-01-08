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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

public final class StringExpressionFunctionUnaryTest extends StringExpressionFunctionTestCase<StringExpressionFunctionUnary<ExpressionEvaluationContext>> {

    // lowerCase........................................................................................................

    @Test
    public void testLowerCase() {
        this.lowerCaseAndCheck("abc123", "abc123");
    }

    @Test
    public void testLowerCaseUpperCased() {
        this.lowerCaseAndCheck("ABC123", "abc123");
    }

    @Test
    public void testLowerCaseMixed() {
        this.lowerCaseAndCheck("abcDEF", "abcdef");
    }

    private void lowerCaseAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.lowerCase(),
            input,
            expected
        );
    }

    // normalizeSpace........................................................................................................

    @Test
    public void testNormalizeSpaceUnnecessary() {
        this.normalizeSpaceAndCheck("a", "a");
    }

    @Test
    public void testNormalizeSpaceUnnecessary2() {
        this.normalizeSpaceAndCheck("abc", "abc");
    }

    @Test
    public void testNormalizeSpaceUnnecessary3() {
        this.normalizeSpaceAndCheck("a b c", "a b c");
    }

    @Test
    public void testNormalizeSpaceLeadingWhitespace() {
        this.normalizeSpaceAndCheck("  a 1", "a 1");
    }

    @Test
    public void testNormalizeSpaceLeadingWhitespace2() {
        this.normalizeSpaceAndCheck("\n\r  a 1", "a 1");
    }

    @Test
    public void testNormalizeSpaceTrailingWhitespace() {
        this.normalizeSpaceAndCheck("a 1  ", "a 1");
    }

    @Test
    public void testNormalizeSpaceTrailingWhitespace2() {
        this.normalizeSpaceAndCheck("a 1 \n\r ", "a 1");
    }

    @Test
    public void testNormalizeSpaceWithoutLeadingOrTrailingWhitespace() {
        this.normalizeSpaceAndCheck("a 1", "a 1");
    }

    @Test
    public void testNormalizeSpaceWithLeadingOrTrailingWhitespace() {
        this.normalizeSpaceAndCheck(" a 1  ", "a 1");
    }

    @Test
    public void testNormalizeSpaceWithLeadingOrTrailingWhitespace2() {
        this.normalizeSpaceAndCheck(" a    1  ", "a 1");
    }

    @Test
    public void testNormalizeSpaceIgnoresNonSpaceWhitespace() {
        this.normalizeSpaceAndCheck("a\nb", "a b");
    }

    @Test
    public void testNormalizeSpaceIgnoresNonSpaceWhitespace2() {
        this.normalizeSpaceAndCheck("a\rb", "a b");
    }

    @Test
    public void testNormalizeSpaceIgnoresNonSpaceWhitespace3() {
        this.normalizeSpaceAndCheck("a\r\n  b", "a b");
    }

    private void normalizeSpaceAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.normalizeSpace(),
            input,
            expected
        );
    }

    // proper.........................................................................................................

    @Test
    public void testProperEmpty() {
        this.properAndCheck("");
    }

    @Test
    public void testProperSpace() {
        this.properAndCheck(" ");
    }

    @Test
    public void testProperAllSpaces() {
        this.properAndCheck("   ");
    }

    @Test
    public void testProperAllNumbers() {
        this.properAndCheck("123456");
    }

    @Test
    public void testProperAllNumbersAndWhitespaces() {
        this.properAndCheck("123 45\t6");
    }

    @Test
    public void testProperAllSymbols() {
        this.properAndCheck("!./");
    }

    @Test
    public void testProperSymbolNumbersWhitespace() {
        this.properAndCheck("! 1 2 3");
    }

    @Test
    public void testLetters() {
        this.properAndCheck("abc", "Abc");
    }

    @Test
    public void testLetters2() {
        this.properAndCheck("ABC", "Abc");
    }

    @Test
    public void testLetters3() {
        this.properAndCheck("AbC", "Abc");
    }

    @Test
    public void testLettersAndNumbers() {
        this.properAndCheck("ABC123", "Abc123");
    }

    @Test
    public void testLettersWhitespaceLetters() {
        this.properAndCheck("ABC DEF", "Abc Def");
    }

    @Test
    public void testLettersDashLetters() {
        this.properAndCheck("ABC-DEF", "Abc-Def");
    }

    @Test
    public void testLettersDashLetters2() {
        this.properAndCheck("ABC-DEF-567-GHI", "Abc-Def-567-Ghi");
    }

    @Test
    public void testSentence() {
        this.properAndCheck("The cat and the hat.", "The Cat And The Hat.");
    }

    private void properAndCheck(final String text) {
        this.properAndCheck(text, text);
    }

    private void properAndCheck(final String input,
                                final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.proper(),
            input,
            expected
        );
    }

    // spaceTrim.........................................................................................................

    @Test
    public void testSpaceTrimEmpty() {
        this.spaceTrimAndCheck("", "");
    }

    @Test
    public void testSpaceTrimAllSpace() {
        this.spaceTrimAndCheck(" ", "");
    }

    @Test
    public void testSpaceTrimAllSpaces() {
        this.spaceTrimAndCheck("   ", "");
    }

    @Test
    public void testSpaceTrimLeft() {
        this.spaceTrimAndCheck(" abc123", "abc123");
    }

    @Test
    public void testSpaceTrimLeft2() {
        this.spaceTrimAndCheck("  abc123", "abc123");
    }

    @Test
    public void testSpaceTrimRight() {
        this.spaceTrimAndCheck(" abc123 ", "abc123");
    }

    @Test
    public void testSpaceTrimRight2() {
        this.spaceTrimAndCheck("abc123  ", "abc123");
    }

    @Test
    public void testSpaceTrimMid() {
        this.spaceTrimAndCheck("abc 123", "abc 123");
    }

    @Test
    public void testSpaceTrimMid2() {
        this.spaceTrimAndCheck("abc  123", "abc 123");
    }

    @Test
    public void testSpaceTrimMid3() {
        this.spaceTrimAndCheck("abc  1  23", "abc 1 23");
    }

    @Test
    public void testSpaceTrimLeftMidRight() {
        this.spaceTrimAndCheck("  abc  1  23 ", "abc 1 23");
    }

    @Test
    public void testSpaceTrimIgnoresOtherWhitespace() {
        this.spaceTrimAndCheck("\nabc\tdef\rg", "\nabc\tdef\rg");
    }

    @Test
    public void testSpaceTrimIgnoresOtherWhitespace2() {
        this.spaceTrimAndCheck(" \nabc \t  def\r g ", "\nabc \t def\r g");
    }

    private void spaceTrimAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.spaceTrim(),
            input,
            expected
        );
    }

    // trim.........................................................................................................

    @Test
    public void testTrim() {
        this.trimAndCheck("abc123", "abc123");
    }

    @Test
    public void testTrimWhitespace() {
        this.trimAndCheck(" \n\tabc123", "abc123");
    }

    @Test
    public void testTrimRightWhitespace() {
        this.trimAndCheck("abc123\n \r", "abc123");
    }

    private void trimAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.trim(),
            input,
            expected
        );
    }

    // trimLeft.........................................................................................................

    @Test
    public void testTrimLeft() {
        this.trimLeftAndCheck("abc123", "abc123");
    }

    @Test
    public void testTrimLeftLeftWhitespace() {
        this.trimLeftAndCheck(" \n\tabc123", "abc123");
    }

    @Test
    public void testTrimLeftRightWhitespace() {
        this.trimLeftAndCheck("abc123 ", "abc123 ");
    }

    private void trimLeftAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.trimLeft(),
            input,
            expected
        );
    }

    // trimRight........................................................................................................

    @Test
    public void testTrimRight() {
        this.trimRightAndCheck("abc123", "abc123");
    }

    @Test
    public void testTrimRightRightWhitespace() {
        this.trimRightAndCheck("abc123 \t\n", "abc123");
    }

    @Test
    public void testTrimRightLeftWhitespace() {
        this.trimRightAndCheck(" abc123", " abc123");
    }

    private void trimRightAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.trimRight(),
            input,
            expected
        );
    }

    // upperCase........................................................................................................

    @Test
    public void testUpperCase() {
        this.upperCaseAndCheck("abc123\u00DF", "ABC123SS");
    }

    @Test
    public void testUpperCaseUpperCased() {
        this.upperCaseAndCheck("ABC123\u00DF", "ABC123SS");
    }

    @Test
    public void testUpperCaseMixed() {
        this.upperCaseAndCheck("abcDEF", "ABCDEF");
    }

    private void upperCaseAndCheck(final String input, final String expected) {
        this.unaryAndCheck(
            StringExpressionFunctionUnary.upperCase(),
            input,
            expected
        );
    }

    private void unaryAndCheck(final StringExpressionFunctionUnary<ExpressionEvaluationContext> function,
                               final String input,
                               final String expected) {
        this.applyAndCheck(
            function,
            this.parameters(input),
            this.createContext(),
            expected
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToStringLowerCase() {
        this.toStringAndCheck(
            StringExpressionFunctionUnary.lowerCase(),
            "lowerCase"
        );
    }

    @Test
    public void testToStringNormalizeSpace() {
        this.toStringAndCheck(
            StringExpressionFunctionUnary.normalizeSpace(),
            "normalizeSpace"
        );
    }

    @Test
    public void testToStringTrim() {
        this.toStringAndCheck(
            StringExpressionFunctionUnary.trim(),
            "trim"
        );
    }

    @Test
    public void testToStringTrimLeft() {
        this.toStringAndCheck(
            StringExpressionFunctionUnary.trimLeft(),
            "trimLeft"
        );
    }

    @Test
    public void testToStringTrimRight() {
        this.toStringAndCheck(
            StringExpressionFunctionUnary.trimRight(),
            "trimRight"
        );
    }

    @Test
    public void testToStringUpperCase() {
        this.toStringAndCheck(
            StringExpressionFunctionUnary.upperCase(),
            "upperCase"
        );
    }

    @Override
    public StringExpressionFunctionUnary<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionUnary.lowerCase();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<StringExpressionFunctionUnary<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionUnary.class);
    }
}

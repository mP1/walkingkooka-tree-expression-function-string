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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class StringExpressionFunctionUnaryTest extends StringExpressionFunctionTestCase<StringExpressionFunctionUnary<ExpressionFunctionContext>, String> {

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

    private void unaryAndCheck(final StringExpressionFunctionUnary<ExpressionFunctionContext> function,
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
                "lower-case"
        );
    }

    @Test
    public void testToStringNormalizeSpace() {
        this.toStringAndCheck(
                StringExpressionFunctionUnary.normalizeSpace(),
                "normalize-space"
        );
    }

    @Test
    public void testToStringTrimLeft() {
        this.toStringAndCheck(
                StringExpressionFunctionUnary.trimLeft(),
                "trim-left"
        );
    }

    @Test
    public void testToStringTrimRight() {
        this.toStringAndCheck(
                StringExpressionFunctionUnary.trimRight(),
                "trim-right"
        );
    }

    @Test
    public void testToStringUpperCase() {
        this.toStringAndCheck(
                StringExpressionFunctionUnary.upperCase(),
                "upper-case"
        );
    }

    @Override
    public StringExpressionFunctionUnary<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionUnary.lowerCase();
    }

    @Override
    public Class<StringExpressionFunctionUnary<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionUnary.class);
    }
}

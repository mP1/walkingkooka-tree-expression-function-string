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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionMidTest extends StringExpressionFunctionTestCase<StringExpressionFunctionMid<ExpressionFunctionContext>, String> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testOneParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1"));
    }

    @Test
    public void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public void testFourParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2", "c3", "d4"));
    }

    @Test
    public void testStringStartAndEndWithinBounds() {
        this.applyAndCheck3("abc", 0, 0, "");
    }

    @Test
    public void testStringStartAndEndWithinBounds2() {
        this.applyAndCheck3("abc", 1, 0, "");
    }

    @Test
    public void testStringStartAndEndWithinBounds3() {
        this.applyAndCheck3("abc", 1, 1, "a");
    }

    @Test
    public void testStringStartAndEndWithinBounds4() {
        this.applyAndCheck3("abc", 1, 2, "ab");
    }

    @Test
    public void testStringStartAndEndWithinBounds5() {
        this.applyAndCheck3("abc", 2, 1, "b");
    }

    @Test
    public void testStringStartAndEndWithinBounds6() {
        this.applyAndCheck3("abc", 2, 2, "bc");
    }

    @Test
    public void testStringLengthPastEnd() {
        this.applyAndCheck3("abc", 2, 3, "bc");
    }

    @Test
    public void testStringStartAndEndWithinBounds7() {
        this.applyAndCheck3("abcde", 5, 1, "e");
    }

    @Test
    public void testStringStartAndEndWithinBounds8() {
        this.applyAndCheck3("abcde", 4, 2, "de");
    }

    @Test
    public void testStringInvalidStart() {
        this.applyAndCheck3("abcde", -2, 2, "");
    }

    @Test
    public void testStringInvalidStart2() {
        this.applyAndCheck3("abcde", -2, 4, "a");
    }

    @Test
    public void testStringInvalidStart3() {
        this.applyAndCheck3("abcde", -2, 5, "ab");
    }

    private void applyAndCheck3(final String text, final int start, final int length, final String result) {
        this.applyAndCheck2(
                this.parameters(text, KIND.create(start), KIND.create(length)),
                result
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "mid");
    }

    @Override
    public StringExpressionFunctionMid<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionMid.instance();
    }

    @Override
    public Class<StringExpressionFunctionMid<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionMid.class);
    }
}

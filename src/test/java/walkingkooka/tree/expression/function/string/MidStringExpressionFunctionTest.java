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

package walkingkooka.tree.expression.function.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class MidStringExpressionFunctionTest extends StringExpressionFunctionTestCase<MidStringExpressionFunction, String> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public final void testOneParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1"));
    }

    @Test
    public final void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public final void testFourParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2", "c3", "d4"));
    }

    @Test
    public void testStringStartAndEndWithinBounds() {
        this.applyAndCheck2(parameters("abc", 0, 0), "");
    }

    @Test
    public void testStringStartAndEndWithinBounds2() {
        this.applyAndCheck2(parameters("abc", 1, 0), "");
    }

    @Test
    public void testStringStartAndEndWithinBounds3() {
        this.applyAndCheck2(parameters("abc", 0, 1), "a");
    }

    @Test
    public void testStringStartAndEndWithinBounds4() {
        this.applyAndCheck2(parameters("abc", 0, 2), "ab");
    }

    @Test
    public void testStringStartAndEndWithinBounds5() {
        this.applyAndCheck2(parameters("abc", 1, 1), "b");
    }

    @Test
    public void testStringStartAndEndWithinBounds6() {
        this.applyAndCheck2(parameters("abc", 1, 2), "bc");
    }

    @Test
    public void testStringLengthPastEnd() {
        this.applyAndCheck2(parameters("abc", 1, 3), "bc");
    }

    @Test
    public void testStringStartAndEndWithinBounds7() {
        this.applyAndCheck2(parameters("abcde", 4, 1), "e");
    }

    @Test
    public void testStringStartAndEndWithinBounds8() {
        this.applyAndCheck2(parameters("abcde", 3, 2), "de");
    }

    @Test
    public void testStringInvalidStart() {
        this.applyAndCheck2(parameters("abcde", -2, 2), "");
    }

    @Test
    public void testStringInvalidStart2() {
        this.applyAndCheck2(parameters("abcde", -2, 3), "a");
    }

    @Test
    public void testStringInvalidStart3() {
        this.applyAndCheck2(parameters("abcde", -2, 4), "ab");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "mid");
    }

    @Override
    public MidStringExpressionFunction createBiFunction() {
        return MidStringExpressionFunction.INSTANCE;
    }

    @Override
    public Class<MidStringExpressionFunction> type() {
        return MidStringExpressionFunction.class;
    }
}

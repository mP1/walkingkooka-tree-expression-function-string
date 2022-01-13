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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionSubstringTest extends StringExpressionFunctionTestCase<StringExpressionFunctionSubstring<ExpressionFunctionContext>, String> {

    @Test
    public void testSubstringOutOfRange() {
        assertThrows2(StringIndexOutOfBoundsException.class, "abcdef", -2, 2);
    }

    @Test
    public void testSubstringOutOfRange2() {
        assertThrows2(StringIndexOutOfBoundsException.class, "abcdef", 2, -1);
    }

    @Test
    public void testSubstringOutOfRange3() {
        assertThrows2(StringIndexOutOfBoundsException.class, "abcdef", 1, 99);
    }

    private void assertThrows2(final Class<? extends Throwable> thrown,
                               final String text,
                               final int start,
                               final int end) {
        assertThrows(
                thrown,
                () -> {
                    this.apply2(text, KIND.create(start), KIND.create(end));
                }
        );
    }

    @Test
    public void testSubstring() {
        this.applyAndCheck3("abcdef", 3, 0, "");
    }

    @Test
    public void testSubstring2() {
        this.applyAndCheck3("abcdef", 4, 1, "d");
    }

    @Test
    public void testSubstring3() {
        this.applyAndCheck3("abcdef", 3, 2, "cd");
    }

    @Test
    public void testSubstringLengthMissing() {
        this.applyAndCheck3("abcdef", 2, "bcdef");
    }

    @Test
    public void testSubstringLengthMissing2() {
        this.applyAndCheck3("abc", 3, "c");
    }

    private void applyAndCheck3(final String text, final int start, final String result) {
        this.applyAndCheck2(
                this.parameters(text, KIND.create(start)),
                result
        );
    }

    private void applyAndCheck3(final String text, final int start, final int length, final String result) {
        this.applyAndCheck2(
                this.parameters(text, KIND.create(start), KIND.create(length)),
                result
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "substring");
    }

    @Override
    public StringExpressionFunctionSubstring<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionSubstring.instance();
    }

    @Override
    public Class<StringExpressionFunctionSubstring<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionSubstring.class);
    }
}

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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionReplaceTest extends StringExpressionFunctionTestCase<StringExpressionFunctionReplace<ExpressionFunctionContext>, String> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testFirstChar() {
        this.replaceAndCheck(
                "c:\\documents",
                1,
                1,
                "d",
                "d:\\documents"
        );
    }

    // REPLACE("XYZ123",4,3,"456")
    @Test
    public void testFirst3Chars() {
        this.replaceAndCheck(
                "xyz123",
                4,
                3,
                "456",
                "xyz456"
        );
    }

    // =REPLACE("XYZ",1,1,"") // returns "YZ"
    @Test
    public void testFirstChar2() {
        this.replaceAndCheck(
                "xyz",
                1,
                1,
                "",
                "yz"
        );
    }

    // =REPLACE("abcde", 3, 0, "XYZ")
    @Test
    public void testReplace_abcde_3_0_XYZ() {
        this.replaceAndCheck(
                "abcde",
                3,
                0,
                "XYZ",
                "abXYZcde"
        );
    }

    // =REPLACE("abcde", 3, 1, "XYZ")
    @Test
    public void testReplace_abcde_3_1_XYZ() {
        this.replaceAndCheck(
                "abcde",
                3,
                1,
                "XYZ",
                "abXYZde"
        );
    }

    // =REPLACE("abcde", 3, 2, "XYZ")
    @Test
    public void testReplace_abcde_3_2_XYZ() {
        this.replaceAndCheck(
                "abcde",
                3,
                2,
                "XYZ",
                "abXYZe"
        );
    }

    // =REPLACE("abcde", 3, 3, "XYZ")
    @Test
    public void testReplace_abcde_3_3_XYZ() {
        this.replaceAndCheck(
                "abcde",
                3,
                3,
                "XYZ",
                "abXYZ"
        );
    }

    // =REPLACE("abcde", 3, 4, "XYZ")
    @Test
    public void testReplace_abcde_3_4_XYZ() {
        this.replaceAndCheck(
                "abcde",
                3,
                4,
                "XYZ",
                "abXYZ"
        );
    }

    private void replaceAndCheck(final String oldText,
                                 final int startPos,
                                 final int charCount,
                                 final String newText,
                                 final String expected) {
        this.applyAndCheck2(
                Lists.of(
                        oldText,
                        KIND.create(startPos),
                        KIND.create(charCount),
                        newText
                ),
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "replace");
    }

    @Override
    public StringExpressionFunctionReplace<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionReplace.instance();
    }

    @Override
    public Class<StringExpressionFunctionReplace<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionReplace.class);
    }
}

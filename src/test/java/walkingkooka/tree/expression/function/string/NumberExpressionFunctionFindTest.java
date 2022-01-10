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

public final class NumberExpressionFunctionFindTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionFind<ExpressionFunctionContext>> {

    // find.............................................................................................................

    @Test
    public void testFindFails() {
        this.findAndFail(
                "A",
                "BC"
        );
    }

    @Test
    public void testFindCaseSensitiveFails() {
        this.findAndFail(
                "A",
                "abc"
        );
    }

    private void findAndFail(final String find,
                             final String within) {
        assertThrows(
                IllegalStateException.class,
                () -> {
                    this.apply2(
                            find,
                            within
                    );
                }
        );
    }

    @Test
    public void testFind() {
        this.findAndCheck(
                "A",
                "Abc",
                1
        );
    }

    @Test
    public void testFind2() {
        this.findAndCheck(
                "xyz",
                "ABCxyz",
                4
        );
    }

    private void findAndCheck(final String find,
                              final String within,
                              final int expected) {
        this.applyAndCheck2(
                Lists.of(
                        find,
                        within
                ),
                KIND.create(expected)
        );
    }

    @Test
    public void testFindStartPosFails() {
        this.findAndFail(
                "A",
                "BC",
                1
        );
    }

    @Test
    public void testFindStartPosFails2() {
        this.findAndFail(
                "A",
                "ABC",
                2
        );
    }

    @Test
    public void testFindStartPosCaseSensitiveFails2() {
        this.findAndFail(
                "A",
                "Aabc",
                2
        );
    }

    @Test
    public void testFindStartPosCaseSensitiveFails() {
        this.findAndFail(
                "A",
                "abc",
                1
        );
    }

    private void findAndFail(final String find,
                             final String within,
                             final int startPos) {
        assertThrows(
                IllegalStateException.class,
                () -> {
                    this.apply2(
                            find,
                            within,
                            KIND.create(startPos)
                    );
                }
        );
    }

    @Test
    public void testFindStartPos() {
        this.findAndCheck(
                "a",
                "abc",
                1,
                1
        );
    }

    @Test
    public void testFindStartPos2() {
        this.findAndCheck(
                "abc",
                "Xabc",
                2,
                2
        );
    }

    @Test
    public void testFindStartPos3() {
        this.findAndCheck(
                "abc",
                "XYZabc123",
                2,
                4
        );
    }

    @Test
    public void testFindStartPos4() {
        this.findAndCheck(
                "abc",
                "XYZabc123abc",
                5,
                10
        );
    }

    private void findAndCheck(final String find,
                              final String within,
                              final int startPos,
                              final int expected) {
        this.applyAndCheck2(
                Lists.of(
                        find,
                        within,
                        KIND.create(startPos)
                ),
                KIND.create(expected)
        );
    }

    // findCaseInsensitive.............................................................................................

    @Test
    public void testFindCaseInsensitiveFails() {
        this.findCaseInsensitiveAndFail(
                "A",
                "BC"
        );
    }

    private void findCaseInsensitiveAndFail(final String find,
                                            final String within) {
        assertThrows(
                IllegalStateException.class,
                () -> {
                    this.applyCaseInsensitive(
                            find,
                            within
                    );
                }
        );
    }

    @Test
    public void testFindCaseInsensitive() {
        this.findCaseInsensitiveAndCheck(
                "A",
                "Abc",
                1
        );
    }

    @Test
    public void testFindCaseInsensitive2() {
        this.findCaseInsensitiveAndCheck(
                "xyz",
                "ABCxyz",
                4
        );
    }

    private void findCaseInsensitiveAndCheck(final String find,
                                             final String within,
                                             final int expected) {
        this.applyAndCheck2(
                NumberExpressionFunctionFind.findCaseInsensitive(),
                Lists.of(
                        find,
                        within
                ),
                KIND.create(expected)
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPosFails() {
        this.findCaseInsensitiveAndFail(
                "A",
                "BC",
                1
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPosFails2() {
        this.findCaseInsensitiveAndFail(
                "A",
                "ABC",
                2
        );
    }

    private void findCaseInsensitiveAndFail(final String find,
                                            final String within,
                                            final int startPos) {
        assertThrows(
                IllegalStateException.class,
                () ->
                        this.applyCaseInsensitive(
                                find,
                                within,
                                KIND.create(startPos)
                        )
        );
    }

    private void applyCaseInsensitive(final Object... parameters) {
        NumberExpressionFunctionFind.findCaseInsensitive()
                .apply(
                        Lists.of(parameters),
                        this.createContext()

                );
    }

    @Test
    public void testFindCaseInsensitiveStartPos() {
        this.findCaseInsensitiveAndCheck(
                "a",
                "abc",
                1,
                1
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos2() {
        this.findCaseInsensitiveAndCheck(
                "A",
                "abc",
                1,
                1
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos3() {
        this.findCaseInsensitiveAndCheck(
                "abc",
                "Xabc",
                2,
                2
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos4() {
        this.findCaseInsensitiveAndCheck(
                "ABC",
                "Xabc",
                2,
                2
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos5() {
        this.findCaseInsensitiveAndCheck(
                "abc",
                "XYZabc123",
                2,
                4
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos6() {
        this.findCaseInsensitiveAndCheck(
                "ABC",
                "XYZabc123",
                2,
                4
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos7() {
        this.findCaseInsensitiveAndCheck(
                "abc",
                "XYZabc123abc",
                5,
                10
        );
    }

    @Test
    public void testFindCaseInsensitiveStartPos8() {
        this.findCaseInsensitiveAndCheck(
                "ABC",
                "XYZabc123abc",
                5,
                10
        );
    }

    private void findCaseInsensitiveAndCheck(final String find,
                                             final String within,
                                             final int startPos,
                                             final int expected) {
        this.applyAndCheck2(
                NumberExpressionFunctionFind.findCaseInsensitive(),
                Lists.of(
                        find,
                        within,
                        KIND.create(startPos)
                ),
                KIND.create(expected)
        );
    }

    // toString.........................................................................................................

    @Test
    public void testFindCaseSensitiveToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "find-case-sensitive"
        );
    }

    @Test
    public void testFindCaseInsensitiveToString() {
        this.toStringAndCheck(
                NumberExpressionFunctionFind.findCaseInsensitive(),
                "find-case-insensitive"
        );
    }

    @Override
    public NumberExpressionFunctionFind<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionFind.findCaseSensitive();
    }

    @Override
    public Class<NumberExpressionFunctionFind<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionFind.class);
    }
}

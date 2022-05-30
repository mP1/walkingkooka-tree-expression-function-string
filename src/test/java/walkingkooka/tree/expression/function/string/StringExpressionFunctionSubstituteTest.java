
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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class StringExpressionFunctionSubstituteTest extends StringExpressionFunctionTestCase<StringExpressionFunctionSubstitute<ExpressionEvaluationContext>> {

    @Test
    public void testInstance0Fails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    this.apply2(
                            "text",
                            "oldText",
                            "newText",
                            KIND.zero()
                    );
                }
        );
    }

    @Test
    public void testInstanceNegativeFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    this.apply2(
                            "text",
                            "oldText",
                            "newText",
                            KIND.create(-1)
                    );
                }
        );
    }

    @Test
    public void testEmptyOldText() {
        this.substituteAndCheck(
                "abc",
                "",
                "123",
                "abc"
        );
    }

    @Test
    public void testEmptyOldTextInstance() {
        this.substituteAndCheck(
                "abc",
                "",
                "123",
                1,
                "abc"
        );
    }

    @Test
    public void testOldSameNew() {
        this.substituteAndCheck(
                "abc",
                "b",
                "b",
                "abc"
        );
    }

    @Test
    public void testOldSameNewInstance() {
        this.substituteAndCheck(
                "abc",
                "b",
                "b",
                1,
                "abc"
        );
    }

    @Test
    public void testReplacedStart() {
        this.substituteAndCheck(
                "abc mno xyz",
                "a",
                "1",
                "1bc mno xyz"
        );
    }

    @Test
    public void testReplacedStart2() {
        this.substituteAndCheck(
                "abc mno xyz",
                "abc",
                "123",
                "123 mno xyz"
        );
    }

    @Test
    public void testReplacedMiddle() {
        this.substituteAndCheck(
                "abc mno xyz",
                "m",
                "1",
                "abc 1no xyz"
        );
    }

    @Test
    public void testReplacedMiddle2() {
        this.substituteAndCheck(
                "abc mno xyz",
                "mno",
                "123",
                "abc 123 xyz"
        );
    }

    @Test
    public void testReplacedLast() {
        this.substituteAndCheck(
                "abc mno xyz",
                "z",
                "1",
                "abc mno xy1"
        );
    }

    @Test
    public void testReplacedLast2() {
        this.substituteAndCheck(
                "abc mno xyz",
                "xyz",
                "123",
                "abc mno 123"
        );
    }

    @Test
    public void testStartAndMiddleReplaces() {
        this.substituteAndCheck(
                "abc abc xyz",
                "abc",
                "123",
                "123 123 xyz"
        );
    }

    @Test
    public void testStartAndEndReplaces() {
        this.substituteAndCheck(
                "abc mno abc",
                "abc",
                "123",
                "123 mno 123"
        );
    }

    @Test
    public void testMiddleAndEndReplaces() {
        this.substituteAndCheck(
                "abc mno mno",
                "mno",
                "123",
                "abc 123 123"
        );
    }

    @Test
    public void testOldAndNewDifferentLength() {
        this.substituteAndCheck(
                "abc mno xyz",
                "mno",
                "*",
                "abc * xyz"
        );
    }

    @Test
    public void testEmptyNewText() {
        this.substituteAndCheck(
                "abc mno xyz",
                "mno",
                "",
                "abc  xyz"
        );
    }

    @Test
    public void testManyReplaces() {
        this.substituteAndCheck(
                "abc m m m xyz",
                "m",
                "123",
                "abc 123 123 123 xyz"
        );
    }

    @Test
    public void testOnlyReplaceFirst() {
        this.substituteAndCheck(
                "abc m m m xyz",
                "m",
                "1",
                1,
                "abc 1 m m xyz"
        );
    }

    @Test
    public void testOnlyReplaceSecond() {
        this.substituteAndCheck(
                "abc m m m xyz",
                "m",
                "1",
                2,
                "abc m 1 m xyz"
        );
    }

    @Test
    public void testOnlyReplaceLast() {
        this.substituteAndCheck(
                "abc m m m xyz",
                "m",
                "1",
                3,
                "abc m m 1 xyz"
        );
    }

    private void substituteAndCheck(final String text,
                                    final String oldText,
                                    final String newText,
                                    final String expected) {
        this.applyAndCheck2(
                Lists.of(
                        text,
                        oldText,
                        newText
                ),
                expected
        );
    }

    private void substituteAndCheck(final String text,
                                    final String oldText,
                                    final String newText,
                                    final int instance,
                                    final String expected) {
        this.applyAndCheck2(
                Lists.of(
                        text,
                        oldText,
                        newText,
                        KIND.create(instance)
                ),
                expected
        );
    }

    // toString........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "substitute"
        );
    }

    @Override
    public StringExpressionFunctionSubstitute<ExpressionEvaluationContext> createBiFunction() {
        return StringExpressionFunctionSubstitute.instance();
    }

    @Override
    public Class<StringExpressionFunctionSubstitute<ExpressionEvaluationContext>> type() {
        return Cast.to(StringExpressionFunctionSubstitute.class);
    }
}

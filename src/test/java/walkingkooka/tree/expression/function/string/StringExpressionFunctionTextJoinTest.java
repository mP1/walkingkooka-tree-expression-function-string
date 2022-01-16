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

public final class StringExpressionFunctionTextJoinTest extends StringExpressionFunctionTestCase<StringExpressionFunctionTextJoin<ExpressionFunctionContext>, String> {

    private static final boolean DONT_IGNORE_EMPTY = false;

    private static final boolean IGNORE_EMPTY = true;

    @Test
    public void testSkipEmptyText1NotEmpty() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "hello"
                ),
                "hello"
        );
    }

    @Test
    public void testSkipEmptyTextNull() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        IGNORE_EMPTY,
                        null
                ),
                ""
        );
    }

    @Test
    public void testSkipEmptyText1Empty() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        ""
                ),
                ""
        );
    }

    @Test
    public void testDontSkip() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "a1",
                        "b2"
                ),
                "a1,b2"
        );
    }

    @Test
    public void testDontSkipIncludesNullText() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "a1",
                        null,
                        "c3"
                ),
                "a1,,c3"
        );
    }

    @Test
    public void testDontSkipIncludesNullText2() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "a1",
                        "b2",
                        null
                ),
                "a1,b2,"
        );
    }

    @Test
    public void testDontSkipIncludesEmptyText() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "a1",
                        "",
                        "c3"
                ),
                "a1,,c3"
        );
    }

    @Test
    public void testDontSkipIncludesEmptyText2() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "a1",
                        "",
                        "c3",
                        ""
                ),
                "a1,,c3,"
        );
    }

    @Test
    public void testDontSkipMixed() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        DONT_IGNORE_EMPTY,
                        "a1",
                        "",
                        "c3",
                        null,
                        "e5"
                ),
                "a1,,c3,,e5"
        );
    }

    @Test
    public void testSkipMixed() {
        this.applyAndCheck2(
                Lists.of(
                        ",",
                        IGNORE_EMPTY,
                        "a1",
                        "",
                        "c3",
                        null,
                        "e5"
                ),
                "a1,c3,e5"
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createBiFunction(),
                "text-join"
        );
    }

    @Override
    public StringExpressionFunctionTextJoin<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionTextJoin.instance();
    }

    @Override
    public Class<StringExpressionFunctionTextJoin<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionTextJoin.class);
    }
}

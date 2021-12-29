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

public final class StringExpressionFunctionUnaryTrimRightTest extends StringExpressionFunctionUnaryTestCase<StringExpressionFunctionUnaryTrimRight<ExpressionFunctionContext>> {

    @Test
    public void testString() {
        this.applyAndCheck2(parameters("abc123"), "abc123");
    }

    @Test
    public void testStringLeftWhitespace() {
        this.applyAndCheck2(parameters(" abc123"), " abc123");
    }

    @Test
    public void testStringRightWhitespace() {
        this.applyAndCheck2(parameters("abc123 "), "abc123");
    }

    @Test
    public void testStringRightWhitespace2() {
        this.applyAndCheck2(parameters("abc123 \t"), "abc123");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "trim-right");
    }

    @Override
    public StringExpressionFunctionUnaryTrimRight<ExpressionFunctionContext> createBiFunction() {
        return StringExpressionFunctionUnaryTrimRight.instance();
    }

    @Override
    public Class<StringExpressionFunctionUnaryTrimRight<ExpressionFunctionContext>> type() {
        return Cast.to(StringExpressionFunctionUnaryTrimRight.class);
    }
}

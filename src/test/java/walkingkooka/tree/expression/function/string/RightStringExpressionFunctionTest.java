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

public final class RightStringExpressionFunctionTest extends StringOptionalNumberStringExpressionFunctionTestCase<RightStringExpressionFunction<ExpressionFunctionContext>> {

    @Test
    public void testBoolean() {
        this.applyAndCheck2(parameters(true), "e");
    }

    @Test
    public void testNumber() {
        this.applyAndCheck2(parameters(123), "3");
    }

    @Test
    public void testString() {
        this.applyAndCheck2(parameters("abcXYZ"), "Z");
    }

    @Test
    public void testStringWithNegative() {
        this.applyAndCheck2(parameters(" abcXYZ", -1), "");
    }

    @Test
    public void testStringWithZero() {
        this.applyAndCheck2(parameters(" abcXYZ", 0), "");
    }

    @Test
    public void testStringWithOne() {
        this.applyAndCheck2(parameters("abcXYZ", 1), "Z");
    }

    @Test
    public void testStringWithTwo() {
        this.applyAndCheck2(parameters("abcXYZ", 2), "YZ");
    }

    @Test
    public void testStringWithFour() {
        this.applyAndCheck2(parameters("abcXYZ", 4), "cXYZ");
    }

    @Test
    public void testStringWithMax() {
        this.applyAndCheck2(parameters("abcXYZ", 6), "abcXYZ");
    }

    @Test
    public void testStringWithTooMany() {
        this.applyAndCheck2(parameters("abcXYZ", 7), "abcXYZ");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "right");
    }

    @Override
    public RightStringExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return RightStringExpressionFunction.instance();
    }

    @Override
    public Class<RightStringExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(RightStringExpressionFunction.class);
    }
}

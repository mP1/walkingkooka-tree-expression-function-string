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

public final class StartsWithExpressionFunctionTest extends StringStringBooleanExpressionFunctionTestCase<StartsWithExpressionFunction<ExpressionFunctionContext>> {

    @Test
    public void testMissing() {
        this.applyAndCheck2(parameters("abc", "d"), false);
    }

    @Test
    public void testMissing2() {
        this.applyAndCheck2(parameters("abcd", "abx"), false);
    }

    @Test
    public void testMissingButEndsWith() {
        this.applyAndCheck2(parameters("abc", "bc"), false);
    }

    @Test
    public void testPresent() {
        this.applyAndCheck2(parameters("abc", "a"), true);
    }

    @Test
    public void testPresent2() {
        this.applyAndCheck2(parameters("abcd", "abc"), true);
    }

    @Test
    public void testPresentDifferentCase() {
        this.applyAndCheck2(parameters("abc", "A"), false);
    }

    @Test
    public void testContainsEmpty() {
        this.applyAndCheck2(parameters("abc", ""), true);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "starts-with");
    }

    @Override
    public StartsWithExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return StartsWithExpressionFunction.instance();
    }

    @Override
    public Class<StartsWithExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(StartsWithExpressionFunction.class);
    }
}

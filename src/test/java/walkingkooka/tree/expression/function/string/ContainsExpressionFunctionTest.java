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

public final class ContainsExpressionFunctionTest extends StringStringBooleanExpressionFunctionTestCase<ContainsExpressionFunction<ExpressionFunctionContext>> {

    @Test
    public void testContains() {
        this.applyAndCheck2(parameters("xyz", "x"), true);
    }

    @Test
    public void testContains2() {
        this.applyAndCheck2(parameters("xyz", "z"), true);
    }

    @Test
    public void testContains3() {
        this.applyAndCheck2(parameters(123, 1), true);
    }

    @Test
    public void testMissing() {
        this.applyAndCheck2(parameters("xyz", "a"), false);
    }

    @Test
    public void testMissing2() {
        this.applyAndCheck2(parameters(123, 4), false);
    }

    @Test
    public void testMissingCaseSensitive() {
        this.applyAndCheck2(parameters("xyz", "Z"), false);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "contains");
    }

    @Override
    public ContainsExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return ContainsExpressionFunction.instance();
    }

    @Override
    public Class<ContainsExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(ContainsExpressionFunction.class);
    }
}

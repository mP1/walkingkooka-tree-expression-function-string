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

package walkingkooka.tree.expression.function.string.sample;

import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.string.StringExpressionFunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Sample {
    public static void main(final String[] args) {
        final String string1 = "abc";
        final String string2 = "123";

        final Object result = StringExpressionFunctions.concat()
                .apply(Lists.of(string1, string2),
                        new FakeExpressionEvaluationContext() {

                            public <T> Either<T, String> convert(final Object value,
                                                                 final Class<T> target) {
                                assertEquals(String.class, target);
                                return this.successfulConversion(
                                        value,
                                        target
                                );
                            }
                        });
        assertEquals(string1 + string2, result);
    }
}

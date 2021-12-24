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
import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

public final class StringLengthExpressionFunctionTest implements ExpressionFunctionTesting<StringLengthExpressionFunction<ExpressionFunctionContext>, ExpressionNumber, ExpressionFunctionContext>,
        ClassTesting2<StringLengthExpressionFunction<ExpressionFunctionContext>> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testEmptyString() {
        this.applyAndCheck(
                Lists.of(""),
                this.createContext(),
                KIND.create(0)
        );
    }

    @Test
    public void testString() {
        final String text = "abc123";

        this.applyAndCheck(
                Lists.of(text),
                this.createContext(),
                KIND.create(text.length())
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "string-length");
    }

    @Override
    public StringLengthExpressionFunction<ExpressionFunctionContext> createBiFunction() {
        return StringLengthExpressionFunction.instance();
    }


    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return KIND;
            }
        };
    }

    @Override
    public Class<StringLengthExpressionFunction<ExpressionFunctionContext>> type() {
        return Cast.to(StringLengthExpressionFunction.class);
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}

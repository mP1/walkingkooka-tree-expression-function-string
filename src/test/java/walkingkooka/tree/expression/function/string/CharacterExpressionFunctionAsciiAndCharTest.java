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
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CharacterExpressionFunctionAsciiAndCharTest extends CharacterExpressionFunctionTestCase<CharacterExpressionFunctionAsciiAndChar<ExpressionFunctionContext>> {

    @Test
    public void testCharZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testCharTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2"));
    }

    @Test
    public void testCharThreeParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2", "c3"));
    }

    @Test
    public void testCharFourParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", "b2", "c3", "d4"));
    }

    @Test
    public void testAsciiNegativeFails() {
        this.applyFails(
                CharacterExpressionFunctionAsciiAndChar.ascii(),
                ExpressionNumberKind.DEFAULT.create(-1)
        );
    }

    @Test
    public void testCharCharacterNegativeFails() {
        this.applyFails(
                CharacterExpressionFunctionAsciiAndChar.character(),
                ExpressionNumberKind.DEFAULT.create(-1)
        );
    }

    @Test
    public void testAscii256Fails() {
        this.applyFails(
                CharacterExpressionFunctionAsciiAndChar.ascii(),
                ExpressionNumberKind.DEFAULT.create(256)
        );
    }

    @Test
    public void testCharCharacterMaxPlus1Fails() {
        this.applyFails(
                CharacterExpressionFunctionAsciiAndChar.character(),
                ExpressionNumberKind.DEFAULT.create(Character.MAX_VALUE + 1)
        );
    }

    private void applyFails(final CharacterExpressionFunctionAsciiAndChar<ExpressionFunctionContext> function,
                            final Object... parameters) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    function.apply(
                            Lists.of(parameters),
                            this.createContext()
                    );
                }
        );
    }

    @Test
    public void testAsciiAsciiLetter() {
        this.applyAndCheck3(
                CharacterExpressionFunctionAsciiAndChar.ascii(),
                (int) 'a',
                'a'
        );
    }

    @Test
    public void testCharAsciiLetter() {
        this.applyAndCheck3(
                CharacterExpressionFunctionAsciiAndChar.character(),
                (int) 'a',
                'a'
        );
    }

    @Test
    public void testCharNonAscii() {
        this.applyAndCheck3(
                CharacterExpressionFunctionAsciiAndChar.character(),
                2000,
                (char) 2000
        );
    }

    private void applyAndCheck3(final CharacterExpressionFunctionAsciiAndChar<ExpressionFunctionContext> function,
                                final Number value,
                                final Character expected) {
        this.applyAndCheck2(
                function,
                parameters(
                        ExpressionNumberKind.DEFAULT.create(value)
                ),
                expected
        );
    }

    @Test
    public void testAsciiToString() {
        this.toStringAndCheck(CharacterExpressionFunctionAsciiAndChar.ascii(), "ascii");
    }

    @Test
    public void testCharToString() {
        this.toStringAndCheck(CharacterExpressionFunctionAsciiAndChar.character(), "char");
    }

    @Override
    public CharacterExpressionFunctionAsciiAndChar<ExpressionFunctionContext> createBiFunction() {
        return CharacterExpressionFunctionAsciiAndChar.ascii();
    }

    @Override
    public Class<CharacterExpressionFunctionAsciiAndChar<ExpressionFunctionContext>> type() {
        return Cast.to(CharacterExpressionFunctionAsciiAndChar.class);
    }
}

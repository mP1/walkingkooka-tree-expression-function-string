package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.j2cl.locale.LocaleAware;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.string.StringExpressionFunctions;

@LocaleAware
public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testParseExpression() {
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
        assertEquals(
                string1 + string2,
                result
        );
    }
}

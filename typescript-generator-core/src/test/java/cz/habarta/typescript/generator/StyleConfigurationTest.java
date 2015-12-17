package cz.habarta.typescript.generator;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StyleConfigurationTest {

    @Test
    public void testOutputWithCustomStyle() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Settings settings = new Settings();
        settings.addTypeNamePrefix = "I";
        settings.sortDeclarations = true;
        settings.noFileComment = true;

        String expected = ""    +
""                              + settings.newline +
"    export interface IA {"     + settings.newline +
"        b: IB;"                + settings.newline +
"        x: number;"            + settings.newline +
"    }"                         + settings.newline +
""                              + settings.newline +
"    export interface IB {"     + settings.newline +
"        s: string;"            + settings.newline +
"    }"                         + settings.newline +
"";
        new TypeScriptGenerator(settings).generateEmbeddableTypeScript(Input.from(A.class), output, true, 1);

        assertEquals(expected, new String(output.toByteArray()));
    }

    public static class A {
        public int getX() {
            return -1;
        }
        public B getB() {
            return null;
        }
    }

    public static class B {
        public String getS() {
            return null;
        }
    }

    @Test
    public void testTypeNameCustomizations() {
        final Settings settings = new Settings();
        settings.removeTypeNamePrefix = "Json";
        settings.removeTypeNameSuffix = "Class";
        settings.addTypeNamePrefix = "I";
        settings.addTypeNameSuffix = "JSON";

        final TsType tsType = new TypeScriptGenerator(settings).getModelCompiler().typeFromJava(JsonTestClass.class);
        assertEquals("ITestJSON", tsType.toString());
    }

    private static class JsonTestClass {
    }

}

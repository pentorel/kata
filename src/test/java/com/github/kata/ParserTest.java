package com.github.kata;

import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void testParseSchema() {
        Args arg = new Args("l,p#,d*", "");
        Map<Character, Parser> parserMap = arg.getParserMap();
        assertEquals(BooleanParser.class, parserMap.get('l').getClass());
        assertEquals(IntegerParser.class, parserMap.get('p').getClass());
        assertEquals(StringParser.class, parserMap.get('d').getClass());
    }

    @Test
    public void testParse() {
        Args arg = new Args("l,p#,d*", "-l -p 8080 -d test");
        assertEquals(8080, arg.getInt('p'));
        assertEquals("test", arg.getString('d'));
        assertEquals(true, arg.getBoolean('l'));
    }
}

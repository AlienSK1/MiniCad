package is.Interpreter.Parsing;





import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

//white box statement-coveage
class AnalizzatoreLessicaleTest {
    static List<String> texts;
    static int ris;

    @BeforeAll
    public static void init(){
        texts=new ArrayList<>();
        texts.add("29");
        texts.add("(");
        texts.add(")");
        texts.add(",");
        texts.add("new");
        texts.add("del");
        texts.add("ls");
        texts.add("area");
        texts.add("perimeter");
        texts.add("mv");
        texts.add("mvoff");
        texts.add("grp");
        texts.add("ungrp");
        texts.add("?");
        texts.add("\"");
        texts.add("\u001a");
    }

    @Before
    public void setup(){
        ris=-1;
    }
    @RepeatedTest(15)
    public void testNextSimbolo(RepetitionInfo info){
        AnalizzatoreLessicale lexer = new AnalizzatoreLessicale(new StringReader(texts.get(info.getCurrentRepetition()-1)));
        ris = lexer.nextSimbolo().ordinal();
        assertNotEquals(-1, ris);
    }
}
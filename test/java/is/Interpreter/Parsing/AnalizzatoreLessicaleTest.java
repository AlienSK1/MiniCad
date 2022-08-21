package is.Interpreter.Parsing;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class AnalizzatoreLessicaleTest {

    @Test
    public void nextSimboloWorks(){
        String s= "mv";
        AnalizzatoreLessicale lexer = new AnalizzatoreLessicale(new StringReader(s));
        assertEquals(Simbolo.MOVE,lexer.nextSimbolo());
    }
    @Test
    public void nextSimboloFail(){
        String s= "^";
        AnalizzatoreLessicale lexer = new AnalizzatoreLessicale(new StringReader(s));
        assertEquals(Simbolo.CHAR_INVALIDO,lexer.nextSimbolo());
    }
}
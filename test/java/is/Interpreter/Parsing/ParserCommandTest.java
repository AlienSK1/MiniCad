package is.Interpreter.Parsing;

import is.Interpreter.Cmd;
import is.Interpreter.Create;
import is.Interpreter.TerminalElement.Floating;
import is.Interpreter.TerminalElement.Pos;
import is.Interpreter.typeConstraint.Circle;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ParserCommandTest {

    @Test
    public void CorrectParsing(){
        String command= "new circle (29) (299,299)";
        ParserCommand parser= new ParserCommand(new StringReader(command));
        Cmd atteso = new Cmd(new Create(new Circle(new Floating("29")),new Pos(new Floating("299"), new Floating("299"))));
        assertEquals( atteso, parser.getCommand());
    }

    @Test
    public void WrongParsing(){
        String command="mv img (200,200) ()))";
        assertThrows(SyntaxException.class,()->{ new ParserCommand(new StringReader(command));});
    }


}
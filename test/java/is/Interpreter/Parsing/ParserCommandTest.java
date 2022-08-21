package is.Interpreter.Parsing;

import is.Interpreter.Cmd;
import is.Interpreter.Create;
import is.Interpreter.TerminalElement.Floating;
import is.Interpreter.TerminalElement.Pos;
import is.Interpreter.typeConstraint.Circle;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ParserCommandTest {

    @Test
    public void CorrectParsing(){
        ParserCommand parser= new ParserCommand(new StringReader("new circle (29) (299,299)"));
        Cmd atteso = new Cmd(new Create(new Circle(new Floating("29")),new Pos(new Floating("299"), new Floating("299"))));
        assertEquals( atteso, parser.getCommand());
    }
}
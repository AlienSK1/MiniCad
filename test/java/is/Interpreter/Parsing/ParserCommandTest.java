package is.Interpreter.Parsing;

import is.Interpreter.Cmd;
import is.Interpreter.Create;
import is.Interpreter.TerminalElement.Floating;
import is.Interpreter.TerminalElement.Pos;
import is.Interpreter.typeConstraint.Circle;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ParserCommandTest {
    static List<String> correctCommand;
    static List<String> wrongCommand;
    @BeforeAll
    public static void init(){
        correctCommand= new LinkedList<>();
        correctCommand.add("new circle (29) (299,299)");
        correctCommand.add("new img (\"/home/fabrizio/Scaricati/Save_webP/pippo.png\") (200,200)");
        correctCommand.add("ls all");
        correctCommand.add("perimeter circle");
        correctCommand.add("del 0");
        wrongCommand= new LinkedList<>();
        wrongCommand.add("(.../");
        wrongCommand.add("del 0");
        wrongCommand.add("new square (100) (20,20)");
        wrongCommand.add("move 0 (200,200)");
        wrongCommand.add("");
        wrongCommand.add("list circle");
    }
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

    @RepeatedTest(5)
    public void CommandExecutionWorks(RepetitionInfo info){
        String command= correctCommand.get(info.getCurrentRepetition()-1);
        ParserCommand parser = new ParserCommand(new StringReader(command));
        assertDoesNotThrow(()->parser.getCommand().interpret());
    }

    @RepeatedTest(5)
    public void CommandExecutionFail(RepetitionInfo info){
        String command = wrongCommand.get(info.getCurrentRepetition()-1);
        ParserCommand parser = new ParserCommand(new StringReader(command));
        assertThrows(Exception.class,()-> parser.getCommand().interpret());
    }


}
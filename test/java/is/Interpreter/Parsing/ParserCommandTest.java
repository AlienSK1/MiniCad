package is.Interpreter.Parsing;

import is.Interpreter.Cmd;
import is.Interpreter.Create;
import is.Interpreter.TerminalExpression.Floating;
import is.Interpreter.NonTerminalExpressions.Pos;
import is.Interpreter.typeConstraint.Circle;
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
        correctCommand.add("mv 0 (100,100)");
        correctCommand.add("mvoff 1 (100,100)");
        correctCommand.add("grp 0,1");
        correctCommand.add("ungrp 2");
        correctCommand.add("ls all");
        correctCommand.add("perimeter circle");
        correctCommand.add("area all");
        correctCommand.add("scale 1 2.5");
        correctCommand.add("del 0");
        wrongCommand= new LinkedList<>();
        wrongCommand.add("(.../");
        wrongCommand.add("new circle (100) (29,29) mv 0");
        wrongCommand.add("new square (100) (20,20)");
        wrongCommand.add("mv 0 (200,200)");
        wrongCommand.add("");
        wrongCommand.add("list circle");
        wrongCommand.add("del 0");
    }

    @RepeatedTest(11)
    public void CommandExecutionWorks(RepetitionInfo info){
        String command= correctCommand.get(info.getCurrentRepetition()-1);
        ParserCommand parser = new ParserCommand(new StringReader(command));
        assertDoesNotThrow(()->parser.getCommand().interpret());
    }

    @RepeatedTest(6)
    public void CommandExecutionFail(RepetitionInfo info){
        String command = wrongCommand.get(info.getCurrentRepetition()-1);
        assertThrows("Operazione fallita.",Exception.class,()-> new ParserCommand(new StringReader(command)).getCommand().interpret());
    }

}
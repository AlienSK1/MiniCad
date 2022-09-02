package is.Interpreter.Parsing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ParserCommandTest {
    static List<String> commands;
    @BeforeAll
    public static void init(){
        commands= new LinkedList<>();
        commands.add("new circle (29) (299,299)");
        commands.add("new img (\"/home/fabrizio/Scaricati/Save_webP/pippo.png\") (200,200)");
        commands.add("mv 0 (100,100)");
        commands.add("mvoff 1 (100,100)");
        commands.add("grp 0,1");
        commands.add("ungrp 2");
        commands.add("ls all");
        commands.add("perimeter circle");
        commands.add("area all");
        commands.add("scale 1 2.5");
        commands.add("del 0");
        commands.add("(.../");
        commands.add("new circle (100) (29,29) mv 0");
        commands.add("new square (100) (20,20)");
        commands.add("mv 0 (200,200)");
        commands.add("");
        commands.add("list circle");
        commands.add("del 0");
    }

    @RepeatedTest(17)
    public void CommandExecutionWorks(RepetitionInfo info){

        String command= commands.get(info.getCurrentRepetition()-1);
        if(info.getCurrentRepetition()<12){
            ParserCommand parser = new ParserCommand(new StringReader(command));
            assertDoesNotThrow(()->parser.getCommand().interpret());
        }
        else{

            assertThrows("Operazione fallita.",Exception.class,()-> new ParserCommand(new StringReader(command)).getCommand().interpret());
        }
    }

}
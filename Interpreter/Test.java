package is.Interpreter;

import is.Interpreter.Parsing.ParserCommand;
import is.Interpreter.TerminalElement.Floating;
import is.Interpreter.TerminalElement.Pos;
import is.Interpreter.typeConstraint.Circle;

import java.io.StringReader;

public class Test {
    public static void main(String[] args){

        Cmd atteso = new Cmd(new Create(new Circle(new Floating("29")),new Pos(new Floating("299"), new Floating("299"))));
        System.out.println(atteso.equals(new Cmd(new Create(new Circle(new Floating("29")),new Pos(new Floating("299"), new Floating("299"))))));
    }
}

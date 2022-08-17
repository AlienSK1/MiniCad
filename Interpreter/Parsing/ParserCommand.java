package is.Interpreter.Parsing;

import is.Interpreter.*;
import is.Interpreter.NonTerminalExpressions.All;
import is.Interpreter.NonTerminalExpressions.Groups;
import is.Interpreter.TerminalElement.Floating;
import is.Interpreter.TerminalElement.Id;
import is.Interpreter.TerminalElement.ListId;
import is.Interpreter.TerminalElement.Pos;
import is.Interpreter.typeConstraint.Circle;
import is.Interpreter.typeConstraint.Img;
import is.Interpreter.typeConstraint.Rectangle;

import java.io.Reader;

public class ParserCommand {

    private AnalizzatoreLessicale lexer;
    private Simbolo simbolo;
    private Cmd command;

    public ParserCommand(Reader in){
        this.lexer=new AnalizzatoreLessicale(in);
        this.command=command();
        atteso(Simbolo.EOF);
    }

    private Cmd command(){
        Expression specificCommand= specificCommand();
        return new Cmd(specificCommand);
    }

    private Expression specificCommand(){
        Expression ris=null;
        Simbolo s = lexer.nextSimbolo();
        Expression typeConstr,pos,id,subj,listId,factor;
        switch (s){
            case CREATE:
                typeConstr=typeConstr();
                pos= pos();
                ris= new Create(typeConstr,pos);
                break;
            case REMOVE:
                id = id();
                ris= new Remove(id);
                break;
            case LIST:
                subj = subject();
                break;
            case MOVE:
                id=id();
                pos=pos();
                ris = new Move(false,id,pos);
                break;
            case MOVEOFF:
                id=id();
                pos=pos();
                ris = new Move(true,id,pos);
                break;
            case GROUP:
                listId=listId();
                ris=new Group(listId);
                break;
            case UNGROUP:
                id= id();
                ris= new Ungroup(id);
                break;
            case SCALE:
                id=id();
                factor= factor();
                ris= new Scale(id,factor);
                break;
            case PERIMETER:
                subj = subject();
                if(subj instanceof Groups){
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero o una parola chiave(ALl) o un tipo");
                }
                ris= new Perimeter(subj);
                break;
            case AREA:
                subj = subject();
                if(subj instanceof Groups){
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero o una parola chiave(ALl) o un tipo");
                }
                ris= new Area(subj);
            dafault: throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un comando");
        }
        return ris;
    }
    private Expression typeConstr(){
        Expression ris= null;
        Simbolo s= lexer.nextSimbolo();
        switch (s){
            case CIRCLE:
                Expression radious=radious();
                ris= new Circle(radious);
                break;
            case RECTANGLE:
                Expression dim = pos();
                ris= new Rectangle(dim);
                break;
            case IMAGE:
                String path= path();
                ris= new Img(path);
                break;
            default: throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un tipo");
        }
        return ris;
    }

    private Expression id(){
        Simbolo s = lexer.nextSimbolo();
        atteso(Simbolo.PAROLA);
        return new Id(lexer.getString());
    }
    private Expression pos(){
        Expression leftValue=null;
        Expression rightValue=null;
        Simbolo s=lexer.nextSimbolo();
        atteso(Simbolo.TONDA_APERTA);
        if(lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            leftValue= new Floating(lexer.getString());
            simbolo=lexer.nextSimbolo();
        }
        else{
            throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
        }
        atteso(Simbolo.COMMA);
        if(lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            rightValue= new Floating(lexer.getString());
            simbolo=lexer.nextSimbolo();
        }
        else{
            throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
        }
        atteso(Simbolo.TONDA_CHIUSA);
        return new Pos(leftValue,rightValue);
    }

    private String path(){
        Simbolo s = lexer.nextSimbolo();
        atteso(Simbolo.TONDA_APERTA);
        atteso(Simbolo.STRINGA_QUOTATA);
        String path= lexer.getString();
        atteso(Simbolo.PAROLA);
        atteso(Simbolo.STRINGA_QUOTATA);
        atteso(Simbolo.TONDA_CHIUSA);
        return path;
    }
    private Expression factor(){
        Expression ris=null;
        Simbolo s=lexer.nextSimbolo();
        if(s==Simbolo.PAROLA && lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            ris= new Floating(lexer.getString());
        }
        else {
            throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
        }
        return ris;
    }

    private Expression radious(){
        Simbolo s= lexer.nextSimbolo();
        atteso(Simbolo.TONDA_APERTA);
        String ris= lexer.getString();
        atteso(Simbolo.PAROLA);
        atteso(Simbolo.TONDA_CHIUSA);
        return new Floating(ris);
    }

    private Expression subject(){
        Expression ris=null;
        Simbolo s= lexer.nextSimbolo();
        switch (s){
            case PAROLA:
                if(lexer.getString().matches("-?\\d+(\\.\\d+)?")){
                    ris= new Id(lexer.getString());
                }
                else {
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
                }
                break;
            case ALL:
                ris= new All();
                break;
            case GROUPS:
                ris= new Groups();
                break;
            default:
                throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero oppure una parola chiave");
        }
        return ris;
    }

    private Expression listId(){
        ListId ris=null;
        Simbolo s = lexer.nextSimbolo();
        while(s!= Simbolo.EOF){
            if(s == Simbolo.PAROLA && lexer.getString().matches("-?\\d+(\\.\\d+)?")){
                if(ris==null){
                    ris= new ListId(new Id(lexer.getString()));
                }
                else{
                    ris.addId(new Id(lexer.getString()));
                }
                s=lexer.nextSimbolo();
            }
            else{
                throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
            }
        }
        return ris;
    }

    private void atteso(Simbolo s) {
        if (simbolo != s) {
            String msg = " trovato " + simbolo + " mentre si attendeva " + s;
            throw new SyntaxException(msg);
        }
        simbolo = lexer.nextSimbolo();
    }
}

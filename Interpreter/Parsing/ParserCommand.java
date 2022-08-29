package is.Interpreter.Parsing;

import is.Interpreter.*;
import is.Interpreter.NonTerminalExpressions.All;
import is.Interpreter.NonTerminalExpressions.Groups;
import is.Interpreter.TerminalExpression.Floating;
import is.Interpreter.TerminalExpression.Id;
import is.Interpreter.NonTerminalExpressions.ListId;
import is.Interpreter.NonTerminalExpressions.Pos;
import is.Interpreter.TerminalExpression.Path;
import is.Interpreter.typeConstraint.Circle;
import is.Interpreter.typeConstraint.Img;
import is.Interpreter.typeConstraint.Rectangle;

import java.io.Reader;

public class ParserCommand {

    private final AnalizzatoreLessicale lexer;
    private Simbolo simbolo;
    private final Cmd command;

    public ParserCommand(Reader in){
        this.lexer=new AnalizzatoreLessicale(in);
        this.command=command();
    }

    private Cmd command(){
        return new Cmd(specificCommand());
    }

    private Expression specificCommand(){
        simbolo = lexer.nextSimbolo();
        Expression typeConstr,pos,id,subj,listId,factor;
        switch (simbolo){
            case CREATE:
                typeConstr=typeConstr();
                pos= pos();
                return new Create(typeConstr,pos);
            case REMOVE:
                id = id();
                return new Remove(id);
            case LIST:
                subj = subject();
                return new List(subj);
            case MOVE:
                id=id();
                pos=pos();
                return new Move(false,id,pos);
            case MOVEOFF:
                id=id();
                pos=pos();
                return new Move(true,id,pos);
            case GROUP:
                listId=listId();
                return new Group(listId);
            case UNGROUP:
                id= id();
                return new Ungroup(id);
            case SCALE:
                id=id();
                factor= factor();
                return new Scale(id,factor);
            case PERIMETER:
                subj = subject();
                if(subj instanceof Groups){
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero o una parola chiave(ALl) o un tipo");
                }
                return new Perimeter(subj);
            case AREA:
                subj = subject();
                if(subj instanceof Groups){
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero o una parola chiave(ALl) o un tipo");
                }
                return new Area(subj);
                default: throw new SyntaxException("trovato " + simbolo + " mentre si attendeva un comando");
        }
    }
    private Expression typeConstr(){
        Expression ris= null;
        simbolo= lexer.nextSimbolo();
        switch (simbolo){
            case CIRCLE:
                Expression radious=radious();
                ris= new Circle(radious);
                break;
            case RECTANGLE:
                Expression dim = pos();
                ris= new Rectangle(dim);
                break;
            case IMAGE:
                Expression path= path();
                ris= new Img(path);
                break;
            default: throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un tipo");
        }
        return ris;
    }

    private Expression id(){
        simbolo=lexer.nextSimbolo();
        if(simbolo!= Simbolo.PAROLA || !lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            throw new SyntaxException("trovato "+lexer.getString()+" mentre si attendeva un numero");
        }
        return new Id(lexer.getString());
    }
    private Expression pos(){
        Expression leftValue=null;
        Expression rightValue=null;
        atteso(Simbolo.TONDA_APERTA);
        atteso(Simbolo.PAROLA);
        if(lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            leftValue= new Floating(lexer.getString());
        }
        else{
            throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
        }
        atteso(Simbolo.COMMA);
        atteso(Simbolo.PAROLA);
        if(lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            rightValue= new Floating(lexer.getString());
        }
        else{
            throw new SyntaxException("trovato "+lexer.getString()+" mentre si attendeva un numero");
        }
        atteso(Simbolo.TONDA_CHIUSA);
        return new Pos(leftValue,rightValue);
    }

    private Expression path(){
        atteso(Simbolo.TONDA_APERTA);
        atteso(Simbolo.STRINGA_QUOTATA);
        atteso(Simbolo.PAROLA);
        Path path= new Path(lexer.getString());
        atteso(Simbolo.STRINGA_QUOTATA);
        atteso(Simbolo.TONDA_CHIUSA);
        return path;
    }
    private Expression factor(){
        Expression ris=null;
        simbolo=lexer.nextSimbolo();
        if(simbolo==Simbolo.PAROLA && lexer.getString().matches("-?\\d+(\\.\\d+)?")){
            ris= new Floating(lexer.getString());
        }
        else {
            throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
        }
        return ris;
    }

    private Expression radious(){
        atteso(Simbolo.TONDA_APERTA);
        atteso(Simbolo.PAROLA);
        String ris= lexer.getString();
        atteso(Simbolo.TONDA_CHIUSA);
        return new Floating(ris);
    }

    private Expression subject(){
        Expression ris=null;
        simbolo= lexer.nextSimbolo();
        switch (simbolo){
            case PAROLA:
                if(lexer.getString().matches("-?\\d+(\\.\\d+)?")){
                    ris= new Id(lexer.getString());
                }
                else {
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
                }
                break;
            case CIRCLE:
                ris= new Circle(new Floating("0"));
                break;
            case RECTANGLE:
                ris= new Rectangle(new Pos(new Floating("0"), new Floating("0")));
                break;
            case IMAGE:
                ris = new Img(new Path(""));
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
        simbolo = lexer.nextSimbolo();
        while(simbolo!= Simbolo.EOF){
            if(simbolo == Simbolo.PAROLA && lexer.getString().matches("-?\\d+(\\.\\d+)?")){
                if(ris==null){
                    ris= new ListId(new Id(lexer.getString()));
                }
                else{
                    ris.addId(new Id(lexer.getString()));
                }

                simbolo=lexer.nextSimbolo();
                if(simbolo!=Simbolo.COMMA && simbolo!=Simbolo.EOF){
                    throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un EOF o una virgola");
                }
                else if( simbolo==Simbolo.COMMA){
                    simbolo=lexer.nextSimbolo();
                }
            }
            else{
                throw new SyntaxException("trovato "+simbolo+" mentre si attendeva un numero");
            }
        }
        return ris;
    }

    private void atteso(Simbolo s) {
        simbolo = lexer.nextSimbolo();
        if (simbolo != s) {
            String msg = " trovato " + simbolo + " mentre si attendeva " + s;
            throw new SyntaxException(msg);
        }
    }
    public Cmd getCommand(){
        return this.command;
    }
}

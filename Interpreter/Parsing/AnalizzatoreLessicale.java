package is.Interpreter.Parsing;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class AnalizzatoreLessicale {
    private StreamTokenizer st;
    private Simbolo simbolo;

    public AnalizzatoreLessicale(Reader in){
        this.st=new StreamTokenizer(in);
        st.eolIsSignificant(false);
        st.resetSyntax();
        st.eolIsSignificant(false);
        st.wordChars('a', 'z');
        st.wordChars('A', 'Z');
        st.wordChars('0', '9');
        st.wordChars('.', '.');
        st.wordChars('/','/');
        st.wordChars('_','_');
        st.whitespaceChars('\u0000', ' ');
        st.ordinaryChar('(');
        st.ordinaryChar(')');
        st.ordinaryChar(',');
        st.ordinaryChar('"');
    }

    public String getString() {
        return st.sval;
    }

    public Simbolo nextSimbolo(){
        try {
            switch (st.nextToken()) {
                case StreamTokenizer.TT_EOF:
                    simbolo = Simbolo.EOF;
                    break;
                case StreamTokenizer.TT_WORD:
                    if (st.sval.equalsIgnoreCase("new"))
                        simbolo = Simbolo.CREATE;
                    else if (st.sval.equalsIgnoreCase("del"))
                        simbolo = Simbolo.REMOVE;
                    else if (st.sval.equalsIgnoreCase("mv"))
                        simbolo = Simbolo.MOVE;
                    else if (st.sval.equalsIgnoreCase("mvoff"))
                        simbolo = Simbolo.MOVEOFF;
                    else if (st.sval.equalsIgnoreCase("ls"))
                        simbolo = Simbolo.LIST;
                    else if(st.sval.equalsIgnoreCase("scale"))
                        simbolo=Simbolo.SCALE;
                    else if(st.sval.equalsIgnoreCase("grp"))
                        simbolo=Simbolo.GROUP;
                    else if(st.sval.equalsIgnoreCase("ungrp"))
                        simbolo=Simbolo.UNGROUP;
                    else if(st.sval.equalsIgnoreCase("perimeter"))
                        simbolo=Simbolo.PERIMETER;
                    else if(st.sval.equalsIgnoreCase("area"))
                        simbolo=Simbolo.AREA;
                    else if(st.sval.equalsIgnoreCase("circle"))
                        simbolo=Simbolo.CIRCLE;
                    else if(st.sval.equalsIgnoreCase("rectangle"))
                        simbolo=Simbolo.RECTANGLE;
                    else if(st.sval.equalsIgnoreCase("img"))
                        simbolo=Simbolo.IMAGE;
                    else if(st.sval.equalsIgnoreCase("all"))
                        simbolo=Simbolo.ALL;
                    else if(st.sval.equalsIgnoreCase("groups"))
                        simbolo=Simbolo.GROUPS;
                    else
                        // parola normale - nome o numero
                        simbolo = Simbolo.PAROLA;
                    break;
                case '"':
                    simbolo = Simbolo.STRINGA_QUOTATA;
                    break;
                case '(':
                    simbolo = Simbolo.TONDA_APERTA;
                    break;
                case ')':
                    simbolo = Simbolo.TONDA_CHIUSA;
                    break;
                case ',':
                    simbolo=Simbolo.COMMA;
                    break;
                default:
                    simbolo = Simbolo.CHAR_INVALIDO;
            }
        } catch (IOException e) {
            simbolo = Simbolo.EOF;
        }
        return simbolo;
    }


}

package is.Interpreter.NonTerminalExpressions;

import is.Interpreter.Expression;
import is.Interpreter.TerminalExpression.Id;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListId implements Expression{
    List<Expression> ids;

    public ListId(Expression id){
        this.ids= new LinkedList<>();
        this.ids.add(id);
    }

    public void addId(Id id){
        this.ids.add(id);
    }

    @Override
    public String interpret() {
        StringBuilder sb = new StringBuilder();
        Iterator<Expression> it = ids.listIterator();
        Expression e = it.next();
        while(it.hasNext()){
            sb.append(e.interpret());
            sb.append(",");
            e=it.next();
        }
        sb.append(e);
        return sb.toString();
    }

    @Override
    public boolean equals(Object e) {
        if(e==null) return false;
        if(e==this) return true;
        if(!(e instanceof ListId)) return false;
        ListId li= (ListId) e;
        return this.ids.equals(li.ids);

    }
}

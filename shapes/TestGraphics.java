package is.shapes;

import is.Interpreter.Cmd;
import is.Interpreter.Parsing.ParserCommand;
import is.command.HistoryCommandHandler;
import is.shapes.Singleton.GraphicObjectHolder;
import is.shapes.model.CircleObject;
import is.shapes.model.Group;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.Reader;
import java.io.StringReader;

public class TestGraphics {

    public static void main(String[] args){
        JFrame frame= new JFrame("MiniCad");
        JToolBar toolbar = new JToolBar();
        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");
        HistoryCommandHandler handler= GraphicObjectHolder.getInstance().getHistory();

        undoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));
        redoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.REDO));
        toolbar.add(undoButt);
        toolbar.add(redoButt);

        GraphicObjectPanel panel = GraphicObjectHolder.getInstance().getPanel();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.installView(RectangleObject.class, new RectangleObjectView());
        panel.installView(CircleObject.class, new CircleObjectView());
        panel.installView(ImageObject.class, new ImageObjectView());
        panel.installView(Group.class, new GroupObjectView());

        TextField field= new TextField();
        field.addActionListener(evt->{
            System.out.println(field.getText());
            Reader in = new StringReader(field.getText());
            Cmd cmd =new ParserCommand(in).getCommand();
            field.setText(cmd.interpret());
        });
        field.setText("Inserisci comando");

        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(field, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

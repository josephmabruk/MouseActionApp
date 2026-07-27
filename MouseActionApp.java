import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseActionApp extends JFrame implements MouseListener {

    JLabel label;
    JComboBox<String> colors;

    public MouseActionApp() {

        setTitle("Mouse Event Application");
        setSize(500,300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Move or Click the Mouse Here");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.addMouseListener(this);

        colors = new JComboBox<>();
        colors.addItem("Black");
        colors.addItem("Red");
        colors.addItem("Blue");
        colors.addItem("Green");
        colors.addItem("Orange");

        add(label);
        add(colors);

        setVisible(true);
    }

    public void changeColor() {
        String c = (String) colors.getSelectedItem();

        if(c.equals("Red"))
            label.setForeground(Color.RED);
        else if(c.equals("Blue"))
            label.setForeground(Color.BLUE);
        else if(c.equals("Green"))
            label.setForeground(Color.GREEN);
        else if(c.equals("Orange"))
            label.setForeground(Color.ORANGE);
        else
            label.setForeground(Color.BLACK);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        label.setText("Mouse Clicked");
        changeColor();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        label.setText("Mouse Pressed");
        changeColor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label.setText("Mouse Released");
        changeColor();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setText("Mouse Entered");
        changeColor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setText("Mouse Exited");
        changeColor();
    }

    public static void main(String[] args) {
        new MouseActionApp();
    }
} 
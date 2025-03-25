package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import roundedComponents.RoundButton;
import roundedComponents.RoundButtonImage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CalculatorPanel extends JPanel {

    private RoundButton equals;
    private RoundButtonImage erase;
    
    private RoundButton one, two, three, four, five, six, seven, eight, nine, zero;
    private RoundButton sum, minus, mult, div, comma, percent;

    private static List<RoundButton> list = new ArrayList<>();
    private Main main;

    /**
     * Create the panel.
     */
    public CalculatorPanel(Main main) {
        setBackground(Color.WHITE);
        setLayout(null);
        
        this.main = main;

        equals = new RoundButton("=", 10, 10);
        equals.setForeground(Color.WHITE);
        equals.setFont(new Font("Inter 28pt ExtraBold", Font.PLAIN, 20));
        equals.setBackground(new Color(0, 122, 255));
        equals.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        equals.setBounds(354, 9, 55, 75);
        add(equals);

        erase = new RoundButtonImage("", 10, 10);
        erase.setBackground(new Color(255, 166, 0));
        erase.setBounds(354, 93, 55, 75);
        erase.setIcon(new ImageIcon(getClass().getResource("/eraser.png")));
        erase.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(erase);

        one = new RoundButton("1", 10, 10);
        two = new RoundButton("2", 10, 10);
        three = new RoundButton("3", 10, 10);
        four = new RoundButton("4", 10, 10);
        five = new RoundButton("5", 10, 10);
        six = new RoundButton("6", 10, 10);
        seven = new RoundButton("7", 10, 10);
        eight = new RoundButton("8", 10, 10);
        nine = new RoundButton("9", 10, 10);
        zero = new RoundButton("0", 10, 10);
        sum = new RoundButton("+", 10, 10);
        minus = new RoundButton("-", 10, 10);
        mult = new RoundButton("*", 10, 10); // Cambio "X" por "*"
        div = new RoundButton("/", 10, 10);
        comma = new RoundButton(".", 10, 10); // Cambio "," por "."
        percent = new RoundButton("%", 10, 10);

        one.setBounds(10, 7, 75, 35);
        two.setBounds(95, 7, 75, 35);
        three.setBounds(180, 7, 75, 35);
        sum.setBounds(265, 7, 75, 35);

        four.setBounds(10, 49, 75, 35);
        five.setBounds(95, 49, 75, 35);
        six.setBounds(180, 49, 75, 35);
        minus.setBounds(265, 49, 75, 35);

        seven.setBounds(10, 91, 75, 35);
        eight.setBounds(95, 91, 75, 35);
        nine.setBounds(180, 91, 75, 35);
        mult.setBounds(265, 91, 75, 35);

        percent.setBounds(10, 133, 75, 35);
        zero.setBounds(95, 133, 75, 35);
        comma.setBounds(180, 133, 75, 35);
        div.setBounds(265, 133, 75, 35);

        add(one);
        add(two);
        add(three);
        add(sum);
        add(four);
        add(five);
        add(six);
        add(minus);
        add(seven);
        add(eight);
        add(nine);
        add(mult);
        add(percent);
        add(zero);
        add(comma);
        add(div);

        addButtons();
        configurations();
        addListeners();
    }

    private void addListeners() {
        for (RoundButton btn : list) {
            btn.addActionListener(e -> appendToResult(btn.getText()));
        }
        equals.addActionListener(e -> calculateResult());
        erase.addActionListener(e -> eraseLastCharacter());
    }

    private void appendToResult(String text) {
        JTextField resultField = main.getResult();
        String currentText = resultField.getText();

        // Si el campo contiene "0.00", reemplazarlo con el número presionado
        if (currentText.equals("0,00") || currentText.equals("0.0")) {
        	resultField.setText("");
            resultField.setText(text);
        } else {
            resultField.setText(currentText + text);
        }
    }


    private void eraseLastCharacter() {
        JTextField resultField = main.getResult();
        String text = resultField.getText();
        if (!text.isEmpty()) {
            resultField.setText(text.substring(0, text.length() - 1));
        }
    }

    private void calculateResult() {
        JTextField resultField = main.getResult();
        String expression = resultField.getText();

        try {
            // Remplazar multiplicación y comas
            expression = expression.replace("X", "*").replace(",", ".");

            // Manejo de porcentaje
            expression = expression.replaceAll("(\\d+(?:\\.\\d+)?)%", "($1/100.0)");

            // Validar que la expresión no termine en un operador
            if (expression.matches(".*[+\\-*/.]$")) {
                resultField.setText("Error");
                return;
            }

            // Evaluar la expresión manualmente
            double result = evaluateExpression(expression);
            resultField.setText((result == (int) result) ? String.valueOf((int) result) : String.valueOf(result));

        } catch (Exception e) {
            resultField.setText("Error");
        }
    }

    
    private double evaluateExpression(String expression) throws Exception {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        StringBuilder numBuffer = new StringBuilder();

        // Separar números y operadores
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                numBuffer.append(c);
            } else if ("+-*/".indexOf(c) != -1) {
                numbers.add(Double.parseDouble(numBuffer.toString()));
                numBuffer.setLength(0);
                operators.add(c);
            }
        }
        numbers.add(Double.parseDouble(numBuffer.toString())); // Último número

        // Resolver multiplicaciones y divisiones primero
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '*' || operators.get(i) == '/') {
                double left = numbers.remove(i);
                double right = numbers.remove(i);
                char op = operators.remove(i);

                double res = (op == '*') ? (left * right) : (left / right);
                numbers.add(i, res);
                i--; // Ajustar índice
            }
        }

        // Resolver sumas y restas
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            double num = numbers.get(i + 1);
            result = (op == '+') ? (result + num) : (result - num);
        }

        return result;
    }



    private String handlePercentage(String expression) {
        // Reemplazamos cada número con % por su equivalente dividido entre 100
        expression = expression.replaceAll("(\\d+)%", "($1/100.0)");
        return expression;
    }

    private double eval(String expression) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        return (double) engine.eval(expression);
    }

    public void configurations() {
        for (RoundButton rb : list) {
            rb.setCustomBorderColor(new Color(166, 166, 166));
            rb.setBackground(new Color(217, 217, 217));
            rb.setFont(new Font("Inter 24pt ExtraBold", Font.PLAIN, 20));
            rb.setForeground(Color.BLACK);
            rb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    public void addButtons() {
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list.add(six);
        list.add(seven);
        list.add(eight);
        list.add(nine);
        list.add(zero);
        list.add(comma);
        list.add(percent);
        list.add(sum);
        list.add(minus);
        list.add(mult);
        list.add(div);
    }
}

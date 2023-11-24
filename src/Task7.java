import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Task7 {
    private JFrame frame;
    private DrawingCanvas canvas;
    private JButton lineButton;
    private JButton rectangleButton;
    private JButton ellipseButton;

    private List<Shape> shapes;
    private Shape currentShape;
    private Point startPoint;
    private Point endPoint;

    public Task7() {
        frame = new JFrame("Drawing Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        canvas = new DrawingCanvas();
        canvas.addMouseListener(new CanvasMouseListener());

        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        ellipseButton = new JButton("Ellipse");

        lineButton.addActionListener(new ButtonListener(ShapeType.LINE));
        rectangleButton.addActionListener(new ButtonListener(ShapeType.RECTANGLE));
        ellipseButton.addActionListener(new ButtonListener(ShapeType.ELLIPSE));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(lineButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(ellipseButton);

        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        shapes = new ArrayList<>();
        currentShape = null;
        startPoint = null;
        endPoint = null;

        frame.setVisible(true);
    }

    class DrawingCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Shape shape : shapes) {
                shape.draw(g);
            }
            if (currentShape != null) {
                currentShape.draw(g);
            }
        }
    }

    class ButtonListener implements ActionListener {
        private ShapeType shapeType;

        public ButtonListener(ShapeType shapeType) {
            this.shapeType = shapeType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentShape = shapeType.createShape();
        }
    }

    class CanvasMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            endPoint = e.getPoint();
            if (currentShape != null && startPoint != null) {
                currentShape.setStartPoint(startPoint);
                currentShape.setEndPoint(endPoint);
                shapes.add(currentShape);
                currentShape = null;
                startPoint = null;
                endPoint = null;
                canvas.repaint();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task7());
    }
}

enum ShapeType {
    LINE {
        @Override
        public Shape createShape() {
            return new Line();
        }
    },
    RECTANGLE {
        @Override
        public Shape createShape() {
            return new Rectangle();
        }
    },
    ELLIPSE {
        @Override
        public Shape createShape() {
            return new Ellipse();
        }
    };

    public abstract Shape createShape();
}

interface Shape {
    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void draw(Graphics g);
}

class Line implements Shape {
    private Point startPoint;
    private Point endPoint;

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics g) {
        if (startPoint != null && endPoint != null) {
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}

class Rectangle implements Shape {
    private Point startPoint;
    private Point endPoint;

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics g) {
        if (startPoint != null && endPoint != null) {
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            g.drawRect(x, y, width, height);
        }
    }
}

class Ellipse implements Shape {
    private Point startPoint;
    private Point endPoint;

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics g) {
        if (startPoint != null && endPoint != null) {
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            g.drawOval(x, y, width, height);
        }
    }
}

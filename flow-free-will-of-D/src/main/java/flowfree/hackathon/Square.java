package flowfree.hackathon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;

import javax.swing.JComponent;

public class Square extends JComponent {

	private static final long serialVersionUID = 1L;

	private Color color = Color.BLACK;
	private Piece piece1;
	private Piece piece2;
	private Gui wholeBoard;
	private int row;
	private int col;
	private Color lineColor;

	public Square(Gui gui, int row, int col) {
		setLayout(new BorderLayout());
		this.wholeBoard = gui;
		this.row = row;
		this.col = col;
	}

	public void setPiece1(Piece piece1) {
		if (!(this.piece1 instanceof Dot)) {
			this.piece1 = piece1;
		}
		if (this.piece1 instanceof Dot) {
			this.piece2 = piece1;
		}
		if (piece1 == null) {
			this.piece1 = null;
		}
	}

	public void setPiece2(Piece piece2) {
		// if ((this.piece1.getColor().equals(piece2.getColor()))
		// || ((this.piece1 instanceof Dot) &&
		// this.piece1.getColor().equals(piece2.getColor()))) {
		this.piece2 = piece2;
		// }

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(this.color);
		if (piece1 != null){
			Color lighterShade = lighterShade(piece1.getColor());
			g.setColor(lighterShade);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		g.drawRect(0, 0, getWidth(), getHeight());
		if (piece1 != null) {
			piece1.draw(g, getWidth(), getHeight());
		}
		if (piece2 != null) {
			piece2.draw(g, getWidth(), getHeight());
		}
	}
	
	private Color lighterShade(Color color){
		Color lighter;
		double r = color.getRed() + (255-color.getRed()) * .5;
		double g = color.getGreen() + (255-color.getGreen()) * .5;
		double b = color.getBlue() + (255-color.getBlue()) *  .5;
		lighter = new Color((int)r,(int)g,(int)b);
		return lighter;
	}



	public void addExitLine(Point exit) {
	}

	public void pushPath() {
		wholeBoard.pushPath(this);
	}

	public Stack<Square> getPath() {
		return wholeBoard.getPath();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void clearPathStack() {
		this.wholeBoard.clearPathStack();
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public Piece getPiece1() {
		return piece1;
	}

	public Piece getPiece2() {
		return piece2;
	}
}

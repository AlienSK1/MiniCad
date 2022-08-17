package is.shapes.model;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public class CircleObject extends AbstractGraphicObject {

	private Point2D position;

	private double radius;

	public CircleObject(Point2D pos, double r, int id) {
		super(id);
		if (r <= 0)
			throw new IllegalArgumentException();
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;

	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Point2D getPosition() {
		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		radius *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension();
		d.setSize(2 * radius, 2 * radius);

		return d;
	}

	@Override
	public boolean contains(Point2D p) {
		return (position.distance(p) <= radius);

	}

	@Override
	public CircleObject clone() {
		CircleObject cloned = (CircleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Circle";
	}

	@Override
	public String objectProperties(){
		return "[type:"+ this.getType()+"\nradious:"+this.radius+"\nposition:<"+ this.position.toString()+">]\n";
	}

	@Override
	public double perimeter(){
		return this.radius*2* Math.PI;
	}

	public double area(){
		return Math.pow(this.radius, 2)*Math.PI*2;
	}

	public double getRadius() {
		return radius;
	}
}

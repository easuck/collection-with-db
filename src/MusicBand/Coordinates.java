package MusicBand;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {

    @XmlElement(required = true)
    private double x;

    @XmlElement(required = true)
    private float y;

    public Coordinates(double x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(){};

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

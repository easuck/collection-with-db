package MusicBand;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "bestAlbum")
@XmlAccessorType(XmlAccessType.FIELD)

public class Album {

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private long length;

    public Album(String name, long length) {
        this.name = name;
        this.length = length;
    }

    public Album() {};

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.isEmpty()) throw new Exception();
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) throws Exception{
        if (length <= 0) throw new Exception();
        this.length = length;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}

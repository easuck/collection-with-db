package MusicBand;

import AdaptersAndComparators.ZonedDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;

@XmlRootElement(name = "musicBand")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicBand {

    @XmlElement
    private Long id;
    private static long idCounter = 0;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private Coordinates coordinates;

    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime creationDate;

    @XmlElement(required = true)
    private Integer numberOfParticipants;

    @XmlElement(required = true)
    private Integer singlesCount;

    @XmlElement(required = true)
    private MusicGenre genre;

    @XmlElement(required = true)
    private Album bestAlbum;

    public MusicBand(String name, Coordinates coordinates, Integer numberOfParticipants,
                     Integer singlesCount, MusicGenre genre, Album bestAlbum) {
        id = idCounter++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = ZonedDateTime.now();
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.genre = genre;
        this.bestAlbum = bestAlbum;
    }

    public MusicBand(){
        id = idCounter++;
        creationDate = ZonedDateTime.now();
    };

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception{
        if (name == null || name.isEmpty()) throw new Exception();
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws Exception {
        if (coordinates == null) throw new Exception();
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) throws Exception{
        if (numberOfParticipants <= 0) throw new Exception();
        this.numberOfParticipants = numberOfParticipants;
    }

    public Integer getSinglesCount() {
        return singlesCount;
    }

    public void setSinglesCount(Integer singlesCount) throws Exception{
        if (singlesCount == null || singlesCount <= 0) throw new Exception();
        this.singlesCount = singlesCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) throws Exception{
        if (genre == null) throw new Exception();
        this.genre = genre;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    public void setBestAlbum(Album bestAlbum) throws Exception{
        if (bestAlbum == null) throw new Exception();
        this.bestAlbum = bestAlbum;
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", genre=" + genre +
                ", bestAlbum=" + bestAlbum +
                '}';
    }
}

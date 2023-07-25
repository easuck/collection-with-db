package MusicBand;

public enum MusicGenre {
    RAP("рэп"),
    HIP_HOP("хип-хоп"),
    POP("поп");

    private String title;

    MusicGenre(String title){
        this.title = title;
    }

    public String getTitle() { return title; }

    @Override
    public String toString() {
        return title;
    }
}

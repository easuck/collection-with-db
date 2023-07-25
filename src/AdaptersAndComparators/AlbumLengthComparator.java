package AdaptersAndComparators;

import MusicBand.MusicBand;

import java.util.Comparator;

public class AlbumLengthComparator implements Comparator<MusicBand> {

    @Override
    public int compare(MusicBand musicBand1, MusicBand musicBand2) {
        if (musicBand1.getBestAlbum().getLength() == musicBand2.getBestAlbum().getLength()){
            return 0;
        }
        else if (musicBand1.getBestAlbum().getLength() > musicBand2.getBestAlbum().getLength()){
            return -1;
        }
        else return 1;
    }
}

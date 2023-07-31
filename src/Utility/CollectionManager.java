package Utility;

import AdaptersAndComparators.AlbumLengthComparator;
import DAO.MusicBandDAO;
import DAO.UserDAO;
import Exceptions.NoKeyReferenceException;
import MusicBand.MusicBand;
import MusicBand.Album;
import javax.xml.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement(name = "musicBands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionManager {

    @XmlElement(name = "musicBand")
    private HashMap<Integer, MusicBand> musicBands = new HashMap<>();
    @XmlTransient
    private ConsoleManager consoleManager = new ConsoleManager();
    private MusicBandDAO musicBandDAO;

    public CollectionManager(MusicBandDAO musicBandDAO){
        this.musicBandDAO = musicBandDAO;
    }
    public CollectionManager(){};

    public HashMap<Integer, MusicBand> getMusicBands() {
        return musicBands;
    }

    public void setMusicBands(HashMap<Integer, MusicBand> musicBands) {
        this.musicBands = musicBands;
    }

    public int getCollectionSize(){
        return musicBands.size();
    }

    public MusicBand getFirstElement(){
        return musicBands.get(0);
    }

    public void showElements(){
        consoleManager.println("Elements of the collection:");
        for (Map.Entry entry : musicBands.entrySet()){
            consoleManager.println(entry);
        }
    }

    public void insertElementByKey(int key, MusicBand musicBand) {
        musicBands.put(key, musicBand);
    }

    public boolean removeElementByKey(int key) throws NoKeyReferenceException {
        if (!musicBands.containsKey(key)) throw new NoKeyReferenceException();
        if(musicBands.get(key).getUserId() == UserDAO.getUserId()){
            musicBands.remove(key);
            return true;
        }
        return false;
    }

    public void clearCollection(){
        for(Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            if (entry.getValue().getUserId() == UserDAO.getUserId()){
                musicBands.remove(entry.getKey());
            }
        }
    }

    public boolean updateById(Long id, UserActionsOnElement userActionsOnElement) {
        for (Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            if ((entry.getValue().getId() == id) &&
            entry.getValue().getUserId() == UserDAO.getUserId()){
                userActionsOnElement.setElementOfCollection(entry.getValue());
                consoleManager.println("element was updated");
                return  true;
            }
        }
        consoleManager.println(("no your elements with such id in collection"));
        return false;
    }

    public void removeGreater(int length){
        int count = 0;
        for (Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            if (entry.getValue().getBestAlbum().getLength() > length &&
                    entry.getValue().getUserId() == UserDAO.getUserId()) {
                musicBands.remove(entry.getKey());
                count++;
            }
        }
        consoleManager.println(count + " element(s) was/were removed from collection");
    }

    public void removeLower(int length){
        int count = 0;
        for (Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            if (entry.getValue().getBestAlbum().getLength() < length &&
                    entry.getValue().getUserId() == UserDAO.getUserId()) {
                musicBands.remove(entry.getKey());
                count++;
            }
        }
        consoleManager.println(count + " element(s) was/were removed from collection");
    }

    public int replaceIfGreater(int length) {
        try{
            for(Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
                if (entry.getValue().getBestAlbum().getLength() < length &&
                        entry.getValue().getUserId() == UserDAO.getUserId()){
                    musicBands.get(entry.getKey()).getBestAlbum().setLength(length);
                    return entry.getKey();
                }
            }
        }
        catch(Exception e){
            consoleManager.println("something wrong with length idk");
        }
        return -1;
    }

    public void removeFirstByGenre(String genre){
        boolean flag = false;
        for (Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            if (entry.getValue().getGenre().getTitle().equals(genre) &&
                    entry.getValue().getUserId() == UserDAO.getUserId()){
                musicBands.remove(entry.getKey());
                flag = true;
                break;
            }
        }
        if (flag){
            consoleManager.println("element was successfully removed");
        }
        else{
            consoleManager.println("no elements with such genre");
        }
    }

    public void filterGreater(int numberOfParticipants){
        musicBands.entrySet()
                .stream()
                .filter(e -> e.getValue().getNumberOfParticipants() > numberOfParticipants)
                .forEach(System.out::println);
    }

    public void printBestAlbumAscending(){
        List<Album> listOfLength = new ArrayList<>();
        for (Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            listOfLength.add(entry.getValue().getBestAlbum());
        }
        consoleManager.println("album length of each element in ascending order: ");
        listOfLength
                .stream()
                .sorted(Comparator.comparing(Album::getLength))
                .forEach(System.out::println);
    }

    public void addDataFromDatabase(){
        List<MusicBand> musicBandsList = musicBandDAO.getDataFromDatabase();
        int count = 0;
        for(MusicBand musicBand : musicBandsList){
            musicBands.put(count, musicBand);
            count++;
        }
    }

    public MusicBand getElementById(long id){
        for (Map.Entry<Integer, MusicBand> entry : musicBands.entrySet()){
            if(entry.getValue().getId() == id){
                return entry.getValue();
            }
        }
        return null;
    }
}

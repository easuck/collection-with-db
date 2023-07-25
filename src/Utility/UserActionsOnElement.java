package Utility;

import MusicBand.Album;
import MusicBand.Coordinates;
import MusicBand.MusicBand;
import MusicBand.MusicGenre;

public class UserActionsOnElement {

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;

    public UserActionsOnElement(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        consoleManager = new ConsoleManager();
    }

    public String setName(){
        String name;
        while(true){
            try{
                consoleManager.print("enter name: ");
                name = consoleManager.readString().trim();
                if (name.isEmpty()) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("name can't be empty, please re-enter");
            }
        }
        return name;
    }

    public Coordinates setCoordinates(){
        Double x;
        Float y;
        while(true){
            try{
                consoleManager.print("enter x coordinate: ");
                x = consoleManager.readDouble();
                if (!(x instanceof Double)) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("x must be type of double, please re-enter");
            }
        }

        while(true){
            try{
                consoleManager.print("enter y coordinate: ");
                y = consoleManager.readFloat();
                if (!(y instanceof Float)) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("y must be type of float, please re-enter");
            }
        }
        return new Coordinates(x, y);
    }

    public int setNumberOfParticipants(){
        Integer participants;
        while(true){
            try{
                consoleManager.print("enter number of participants: ");
                participants = consoleManager.readInt();
                if (!(participants instanceof Integer) || participants == null) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("it must be a number, please re-enter");
            }
        }
        return participants;
    }

    public int setSinglesCount(){
        Integer singles;
        while(true){
            try{
                consoleManager.print("enter number of singles: ");
                singles = consoleManager.readInt();
                if (!(singles instanceof Integer) || singles == null) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("it must be a number, please re-enter");
            }
        }
        return singles;
    }

    public MusicGenre setMusicGenre(){
        consoleManager.println("1.HIP-HOP\n2.RAP\n3.POP");
        Integer genreNumber;
        while(true){
            try{
                consoleManager.print("enter a number of genre you need: ");
                genreNumber = consoleManager.readInt();
                if (genreNumber < 1 || genreNumber > 3 || !(genreNumber instanceof Integer)) throw new Exception();
                if (genreNumber == 1) return MusicGenre.HIP_HOP;
                if (genreNumber == 2) return MusicGenre.RAP;
                if (genreNumber == 3) return MusicGenre.POP;
            }
            catch(Exception e){
                consoleManager.println("wrong input, try again");
            }
        }
    }

    public Album setAlbum(){
        String name;
        Long length;
        while(true){
            try{
                consoleManager.print("enter the name of the album: ");
                name = consoleManager.readString().trim();
                if (name.isEmpty()) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("name can't be empty, please re-enter");
            }
        }

        while(true){
            try{
                consoleManager.print("enter amount of songs in the album: ");
                length = consoleManager.readLong();
                if (!(length instanceof Long)) throw new Exception();
                break;
            }
            catch(Exception e){
                consoleManager.println("it must be a number, please re-enter");
            }
        }
        return new Album(name, length);
    }


    public void setElementOfCollection(MusicBand musicBand) {
        try{
            musicBand.setName(setName());
            musicBand.setCoordinates(setCoordinates());
            musicBand.setNumberOfParticipants(setNumberOfParticipants());
            musicBand.setSinglesCount(setSinglesCount());
            musicBand.setGenre(setMusicGenre());
            musicBand.setBestAlbum(setAlbum());
        }
        catch(Exception e){
            consoleManager.println("incorrect values");
        }
    }


}

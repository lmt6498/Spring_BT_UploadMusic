package services;

import models.Music;

import java.util.ArrayList;

public class MusicServices {
    public ArrayList<Music> list = new ArrayList<>();
    public MusicServices(){

    }
    public void save(Music music){
        list.add(music);
    }
}

package com.example.baitapcuoiky;

public class ListSong {
    private String song;
    private String Singer;
    private int Hinhanh;
    private int file;
    public ListSong(String song, String singer, int hinhanh, int file) {
        this.song = song;
        Singer = singer;
        Hinhanh = hinhanh;
        this.file = file;
    }
    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public int getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        Hinhanh = hinhanh;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}

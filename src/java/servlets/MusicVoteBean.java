/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.Serializable;

/**
 *
 * @author Suhas
 */
public class MusicVoteBean implements Serializable {
    
    private String musicType;
    private int numVotes;

    
    public MusicVoteBean() {
        
    }
    
    public String getMusicType() {
        return musicType;
    }
    

    
    public int getNumVotes() {
        return numVotes;
    }
    

    public void setMusicType(String music) {
        this.musicType = music;
    }
    
    public void setNumVotes(int votes) {
        this.numVotes = votes;
    }
    
    


}

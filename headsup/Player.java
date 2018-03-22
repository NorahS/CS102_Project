package n.headsup;

import java.util.ArrayList;

/**
 * Created by Norah on 4/2/16 AD.
 */
public class Player {
    private String videoName;
    private ArrayList<String> played = new ArrayList<>();
    private ArrayList<String> state = new ArrayList<>();
    private boolean both;
    private int score=0;

    Player(){
        this.videoName =setVideoName();
    }
    Player(Player p ){
        this.videoName = p.getVideoName();
        this.played = p.getPlayed();
        this.state = p.getState();
        this.score =p.getScore();
        this.both= p.getBoth();
    }
    public String getVideoName(){
        return videoName;
    }
    public ArrayList<String> getPlayed(){
        return played;
    }
    public ArrayList<String> getState(){
        return  state;
    }
    public int getScore(){
        return score;
    }

    public void addP(String n){
        played.add(n);
    }
    public void addS(String n){
        state.add(n);
    }
    public String setVideoName(){
        //random name
        String randomName="";
        int len = (int)(Math.random()*10);
        for(int i=0; i!=len;i++){
            randomName += (char)((int)(Math.random()*28)+'a');
        }
        return randomName;
    }
    public  void setScore(){
        score+=1;
    }
    public void setBoth(boolean b){
        both = b;
    }
    public boolean getBoth(){
        return both;
    }
}

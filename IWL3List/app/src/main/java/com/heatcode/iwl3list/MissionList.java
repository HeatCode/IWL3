package com.heatcode.iwl3list;

/**
 * Created by HeatherGuin on 11/25/2015.
 */
public class MissionList {
  private String mLocation;
  private String mAddress;
  private int mScore;


   public MissionList (String location, String address, int score){

       mLocation = location;
       mAddress = address;
       mScore = score;
   }


    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}

package asa.scps.nyu.edu.nycmaps;

import com.google.android.gms.maps.GoogleMap;

// this class is used to store settings for the google maps between the two activities
public class MapSettings {

    public static Integer mapType = GoogleMap.MAP_TYPE_NORMAL;
    public static Float cameraTilt = 0f;

    public static Float getCameraTilt() {
        return cameraTilt;
    }

    public static void setCameraTilt(Float cameraTilt) {
        if (cameraTilt < 0 || cameraTilt > 90)  {
            throw new IllegalArgumentException("Camera tilt must be a number between 0 and 90 degrees");
        } else {
            MapSettings.cameraTilt = cameraTilt;
        }
    }

    public static Integer getMapType() {
        return mapType;
    }

    public static void setMapType(String mapName) {
        if (mapName.equals("Normal")) {
            mapType = GoogleMap.MAP_TYPE_NORMAL;
        } else if (mapName.equals("Hybrid")) {
            mapType = GoogleMap.MAP_TYPE_HYBRID;
        } else if (mapName.equals("Satellite")) {
            mapType = GoogleMap.MAP_TYPE_SATELLITE;
        } else if (mapName.equals("Terrain")) {
            mapType = GoogleMap.MAP_TYPE_TERRAIN;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

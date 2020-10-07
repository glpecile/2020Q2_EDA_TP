package reader;


import model.BusStop;
import model.Coord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BusStopReader {
    private final String filePath = "src/main/resources/paradas-de-colectivo.csv";
    private final List<String[]> busStopData;
    private final List<BusStop> busStops = new ArrayList<>();
    private Double maxLat=(-1)*Double.MAX_VALUE, maxLng=(-1)*Double.MAX_VALUE, minLat=Double.MAX_VALUE, minLng=Double.MAX_VALUE;

    public BusStopReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busStopData = resources.gettingFileReady();
    }

    public List<BusStop> getBusStops() {
        busStopData.forEach(b -> {
            double busLng = Double.parseDouble(b[4]);
            double busLat = Double.parseDouble(b[3]);
            if (Double.compare(maxLat,busLat)<0) {
                maxLat=busLat;
            }
            if (Double.compare(maxLng,busLng)<0) {
                maxLng=busLng;
            }
            if (Double.compare(minLat,busLat)>0) {
                minLat=busLat;
            }
            if (Double.compare(minLng,busLng)>0) {
                minLng=busLng;
            }
            busStops.add(new BusStop(busLng, busLat, Integer.parseInt(b[5]), b[8]));
        });
        System.out.println("Tamaño busStops: " + busStops.size());
        return busStops;
    }
    public Coord getTopRight() {
        return new Coord(maxLng, maxLat);
    }
    public Coord getBottomLeft() {
        return new Coord(minLng, minLat);
    }
}

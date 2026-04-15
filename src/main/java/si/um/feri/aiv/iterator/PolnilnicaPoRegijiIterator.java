package si.um.feri.aiv.iterator;

import si.um.feri.aiv.vao.ChargingStation;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PolnilnicaPoRegijiIterator implements Iterator<ChargingStation> {
    private List<ChargingStation> stations;
    private int index = 0;
    private String region;

    public PolnilnicaPoRegijiIterator(List<ChargingStation> stations, String region) {
        this.stations = stations;
        this.region = region;
    }

    @Override
    public boolean hasNext() {
        while (index < stations.size()) {
            if (stations.get(index).getRegion() != null && stations.get(index).getRegion().equalsIgnoreCase(region)) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public ChargingStation next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return stations.get(index++);
    }
}


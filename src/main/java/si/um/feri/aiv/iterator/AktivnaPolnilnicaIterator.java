package si.um.feri.aiv.iterator;

import si.um.feri.aiv.vao.ChargingStation;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AktivnaPolnilnicaIterator implements Iterator<ChargingStation> {
    private List<ChargingStation> stations;
    private int index = 0;

    public AktivnaPolnilnicaIterator(List<ChargingStation> stations) {
        this.stations = stations;
    }

    @Override
    public boolean hasNext() {
        while (index < stations.size()) {
            if (stations.get(index).isActive()) {
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


package si.um.feri.aiv.iterator;

import si.um.feri.aiv.vao.ChargingStation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class VsePolnilnicePoAbecediIterator implements Iterator<ChargingStation> {
    private List<ChargingStation> sortedStations;
    private int index = 0;

    public VsePolnilnicePoAbecediIterator(List<ChargingStation> stations) {
        this.sortedStations = new ArrayList<>(stations);
        // Sort by location, then by ID as secondary sort key for stability
        this.sortedStations.sort(Comparator.comparing(ChargingStation::getLocation)
                .thenComparing(ChargingStation::getId));
    }

    @Override
    public boolean hasNext() {
        return index < sortedStations.size();
    }

    @Override
    public ChargingStation next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return sortedStations.get(index++);
    }
}


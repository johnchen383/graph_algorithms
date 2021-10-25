package comparators;

import java.util.Comparator;

import models.arcs.Arc;

public class ArcWeightComparator implements Comparator<Arc>{
    @Override
    public int compare(Arc arc1, Arc arc2) {
        return arc1.getWeight() - arc2.getWeight();
    }
}

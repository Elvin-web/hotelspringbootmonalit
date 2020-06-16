package az.elvin.hotel.dao;

import az.elvin.hotel.model.CanvasjsChartData;

import java.util.List;
import java.util.Map;

public class CanvasjsChartDaoImpl implements CanvasjsChartDao{
    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return CanvasjsChartData.getCanvasjsDataList();
    }
}

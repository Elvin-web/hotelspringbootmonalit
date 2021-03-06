package az.elvin.hotel.business;

import az.elvin.hotel.dao.CanvasjsChartDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class CanvasjsChartServiceImpl implements CanvasjsChartService {
    @Autowired
    private CanvasjsChartDao canvasjsChartDao;

    public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
        this.canvasjsChartDao = canvasjsChartDao;
    }

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return canvasjsChartDao.getCanvasjsChartData();
    }

}

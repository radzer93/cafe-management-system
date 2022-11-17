package org.radoslawzerek.cafemanagementsystem.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.radoslawzerek.cafemanagementsystem.dao.BillDao;
import org.radoslawzerek.cafemanagementsystem.dao.CategoryDao;
import org.radoslawzerek.cafemanagementsystem.dao.ProductDao;
import org.radoslawzerek.cafemanagementsystem.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CategoryDao categoryDao;

    private final ProductDao productDao;

    private final BillDao billDao;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("category", categoryDao.count());
            map.put("product", productDao.count());
            map.put("bill", billDao.count());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

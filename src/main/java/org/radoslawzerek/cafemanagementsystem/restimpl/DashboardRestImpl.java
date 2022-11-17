package org.radoslawzerek.cafemanagementsystem.restimpl;

import lombok.RequiredArgsConstructor;
import org.radoslawzerek.cafemanagementsystem.rest.DashboardRest;
import org.radoslawzerek.cafemanagementsystem.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DashboardRestImpl implements DashboardRest {

    private final DashboardService dashboardService;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        try {
            return dashboardService.getCount();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

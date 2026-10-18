package org.dromara.system.controller;

import org.dromara.common.core.domain.R;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 职业健康监测
 */
@RestController
@RequestMapping("/zyjk/monitoring")
public class ZyjkMonitoringController {
    
    @GetMapping("/list")
    public R<List<Map<String, Object>>> list() {
        return R.ok(new ArrayList<>());
    }
    
    @GetMapping("/{id}")
    public R<Map<String, Object>> get(@PathVariable("id") String id) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("name", "职业健康监测");
        return R.ok(data);
    }
}

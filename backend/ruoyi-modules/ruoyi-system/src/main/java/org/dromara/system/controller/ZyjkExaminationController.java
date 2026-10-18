package org.dromara.system.controller;

import org.dromara.common.core.domain.R;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 职业健康检查
 */
@RestController
@RequestMapping("/zyjk/examination")
public class ZyjkExaminationController {
    
    @GetMapping("/list")
    public R<List<Map<String, Object>>> list() {
        return R.ok(new ArrayList<>());
    }
    
    @GetMapping("/{id}")
    public R<Map<String, Object>> get(@PathVariable("id") String id) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("name", "职业健康检查");
        return R.ok(data);
    }
}

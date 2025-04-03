package edu.dp.sau.osuzdaliev.controller;
import edu.dp.sau.osuzdaliev.model.Blender;
import edu.dp.sau.osuzdaliev.service.BlenderScraperService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/blenders")
public class BlenderController {
    private final BlenderScraperService blenderScraperService;
    public BlenderController(BlenderScraperService blenderScraperService) {
        this.blenderScraperService = blenderScraperService;
    }
    @GetMapping
    public List<Blender> getBlenders(@RequestParam(value = "brand", required = false) String brand) {
        if (brand == null || brand.isEmpty()) {
            return List.of();
        }
        return blenderScraperService.getBlenders(brand);
    }
}




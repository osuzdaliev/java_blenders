package edu.dp.sau.osuzdaliev.controller;
import edu.dp.sau.osuzdaliev.model.Blender;
import edu.dp.sau.osuzdaliev.service.BlenderScraperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Controller
public class BlenderViewController {
    private final BlenderScraperService blenderScraperService;
    public BlenderViewController(BlenderScraperService blenderScraperService) {
        this.blenderScraperService = blenderScraperService;
    }
    @GetMapping("/blenders")
    public String showBlendersPage(@RequestParam(value = "brand", required = false) String brand, Model model) {
        if (brand != null && !brand.isEmpty()) {
            List<Blender> blenders = blenderScraperService.getBlenders(brand);
            model.addAttribute("blenders", blenders);
        }
        return "blenders";
    }
}





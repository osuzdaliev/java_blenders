package edu.dp.sau.osuzdaliev.controller;
import edu.dp.sau.osuzdaliev.service.BlenderScraperService;
import edu.dp.sau.osuzdaliev.service.ExcelReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/reports")
public class ExcelReportController {
    private final ExcelReportService excelReportService;
    private final BlenderScraperService blenderScraperService;
    public ExcelReportController(ExcelReportService excelReportService, BlenderScraperService blenderScraperService) {
        this.excelReportService = excelReportService;
        this.blenderScraperService = blenderScraperService;
    }
    @GetMapping("/blenders/{brand}")
    public byte[] downloadBlenderReport(@PathVariable String brand) {
        return excelReportService.generateBlenderReport(blenderScraperService.getBlenders(brand));
    }
}


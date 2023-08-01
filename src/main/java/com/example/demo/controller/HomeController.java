package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataService;
import com.example.demo.services.CovidDataRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
@Configuration
public class HomeController {

    @Autowired
    @Qualifier("coronaVirusDataService")
    private CoronaVirusDataService crnService;

    @Autowired
    private CovidDataRepository repository;

    @GetMapping("/")
    //@Transactional
    //@ResponseBody 
    public String home(Model model) {
        List<LocationStates> allStates = crnService.getAllStates();
        int totalReportedCases = allStates.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum();
        int totalNewCases = allStates.stream().mapToInt(stat -> stat.getDiffFromPrevay()).sum();

        model.addAttribute("locationStats", allStates);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        repository.saveAll(allStates); // Use saveAll instead of save for a list of entities
        return "home";
    }

    @RequestMapping(path = "/collectionChartData", produces = {"application/json"})
    @ResponseBody
    public List<LocationStates> collectChartData(Model model) {
        System.out.println("Here View Chart Data...");
        List<LocationStates> allstates = crnService.getAllStates();
        int totalDeathReported = allstates.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum();
        model.addAttribute("LocationStates", allstates);
        model.addAttribute("totalDeathsReported", totalDeathReported);
        return allstates;
    }

    @RequestMapping(path = "/collectionChartData/country/{name}", produces = {"application/json"})
    @ResponseBody
    public Optional<LocationStates> collectChartDataByCountryID(@PathVariable("name") String countryName, Model model) {
        System.out.println("Here View Chart Data by Country ID...");
        Optional<LocationStates> locationStates = Optional.empty();
        return locationStates;
    }

    @RequestMapping(path = "/collectionChartData/top/{count}", produces = {"application/json"})
    @ResponseBody
    public List<LocationStates> collectChartDataByCountryTop(@PathVariable("count") int count, Model model) {
        System.out.println("Here View Chart Data by Country Name...");
        List<LocationStates> locationStates = repository.findTopNByLatestTotalDeaths(count);
        return locationStates;
    }

    @RequestMapping(value = "/viewChart", method = RequestMethod.GET)
    public ModelAndView viewChart() {
        return new ModelAndView("ViewChart").addObject("myURL", new String("http://localhost:8081/collectionChartData"));
    }

    @GetMapping("/viewChart/{id}")
    public String viewChartByID(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("myURL", "http://localhost:8081/collectionChartData/country/" + id);
        return "viewChart";
    }

    @GetMapping("/viewChartByCountryName")
    public String viewChartByCountryName(@RequestParam String name, Model model) {
        model.addAttribute("myURL", "http://localhost:8081/collectionChartData/country/" + name);
        return "viewChart";
    }
}

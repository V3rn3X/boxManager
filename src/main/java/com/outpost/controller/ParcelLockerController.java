package com.outpost.controller;

import com.outpost.domain.ParcelLocker;
import com.outpost.service.ParcelLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ParcelLockerController {

    private final ParcelLockerService service;

    // display list of parcel loc
    @GetMapping("/parcellockerlist")
    public String parcelLockerListPage(Model model) {
        model.addAttribute("parcelLockerList", service.getAllParcelLocker());
        return "parcelLockerListPage";
    }

    @GetMapping("/addParcelLocker")
    public String saveNewParcelLocker(Model model) {
        ParcelLocker parcelLocker = new ParcelLocker();
        model.addAttribute("parcelLocker", parcelLocker);
        return "addParcelLocker";
    }

    @PostMapping("saveParcelLocker")
    public String saveParcelLocker(@Valid @ModelAttribute("parcelLocker") ParcelLocker parcelLocker, BindingResult result) {
        if (result.hasErrors()) {
            return "addParcelLocker";
        }
        service.addParcelLocker(parcelLocker);
        return "redirect:/parcellockerlist";
    }

    @GetMapping("/updateParcelLocker/{id}")
    public String updateParcelLocker(@PathVariable(value = "id") String id, Model model) {
        ParcelLocker parcelLocker = service.getParcelLockerById(id);
        model.addAttribute("parcelLocker", parcelLocker);
        return "updateParcelLocker";
    }

    @PostMapping("updateParcelLocker")
    public String updateParcelLocker(@Valid @ModelAttribute("parcelLocker") ParcelLocker parcelLocker, BindingResult result) {
        if (result.hasErrors()) {
            return "updateParcelLocker";
        }
        service.addParcelLocker(parcelLocker);
        return "redirect:/parcellockerlist";
    }

    @GetMapping("/deleteParcelLocker/{id}")
    public String deleteParcelLocker(@PathVariable(value = "id") String id) {
        service.deleteParcelLockerById(id);
        return "redirect:/parcellockerlist";
    }
}

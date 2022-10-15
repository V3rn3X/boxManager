package com.outpost.box;

import com.outpost.parcelLocker.ParcelLocker;
import com.outpost.parcelLocker.ParcelLockerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoxController {

    private final BoxService service;
    private final ParcelLockerService parcelLockerService;

    @GetMapping("/boxlist")
    public String boxListPage(Model model){
        model.addAttribute("boxList", service.getAllBox());
        return "boxListPage";
    }

    @GetMapping("/addBox")
    public String saveNewBox(final Model model) {
        Box box = new Box();
        List<ParcelLocker> senderParcelLockerList = parcelLockerService.getAllParcelLocker();
        List<ParcelLocker> recipientParcelLockerList = parcelLockerService.getAllParcelLocker();
        model.addAttribute("box", box);
        model.addAttribute("senderParcelLockerList", senderParcelLockerList);
        model.addAttribute("recipientParcelLockerList", recipientParcelLockerList);
        log.info("przekierowanie na addBox");
        return "addBox";
    }

    @PostMapping("saveBox")
    public String saveBox(@Valid @ModelAttribute("box") Box box, BindingResult result) {
        log.info("error pińsyt");
        if (result.hasErrors()) {
            log.info("error pińsyt one");
            return "addBox";
        }
        service.addBox(box);
        return "redirect:/boxlist";
    }




}

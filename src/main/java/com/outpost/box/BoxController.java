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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
        List<ParcelLocker> parcelLockerList = parcelLockerService.getAllParcelLocker();
        model.addAttribute("box", box);
        model.addAttribute("senderParcelLockerList", parcelLockerList);
        model.addAttribute("recipientParcelLockerList", parcelLockerList);
        log.info("przekierowanie na addBox");
        return "addBox";
    }

    @PostMapping("/saveBox")
    public String saveBox(@Valid @ModelAttribute("box") Box box, BindingResult result, Model model) {
        log.info("error pińsyt");
        if (result.hasErrors()) {
            log.info("error pińsyt one");
            List<ParcelLocker> parcelLockerList = parcelLockerService.getAllParcelLocker();
            model.addAttribute("senderParcelLockerList", parcelLockerList);
            model.addAttribute("recipientParcelLockerList", parcelLockerList);
            return "addBox";
        }
        service.addBox(box);
        return "redirect:/boxlist";
    }

    @GetMapping("/updateBox/{id}")
    public String updateBox(@PathVariable(value = "id") UUID id, Model model) {
        Box box = service.getBoxById(id);
        List<ParcelLocker> parcelLockerList = parcelLockerService.getAllParcelLocker();
        model.addAttribute("senderParcelLockerList", parcelLockerList);
        model.addAttribute("recipientParcelLockerList", parcelLockerList);
        model.addAttribute("box", box);
        return "updateBox";
    }

    @PostMapping("updateBox")
    public String updateBox(@Valid @ModelAttribute("box") Box box, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ParcelLocker> parcelLockerList = parcelLockerService.getAllParcelLocker();
            model.addAttribute("senderParcelLockerList", parcelLockerList);
            model.addAttribute("recipientParcelLockerList", parcelLockerList);
            return "updateBox";
        }
        service.addBox(box);
        return "redirect:/boxlist";
    }

    @GetMapping("/deleteBox/{id}")
    public String deleteBox(@PathVariable(value = "id") UUID id) {
        service.deleteBoxById(id);
        return "redirect:/boxlist";
    }

    @GetMapping("/editStatusBox/{id}")
    public String editStatusBox(@PathVariable(value = "id") UUID id, Model model) {
        Box box = service.getBoxById(id);
        List<ParcelLocker> parcelLockerList = parcelLockerService.getAllParcelLocker();
        model.addAttribute("senderParcelLockerList", parcelLockerList);
        model.addAttribute("recipientParcelLockerList", parcelLockerList);
        model.addAttribute("box", box);
        return "editStatus";
    }

    @PostMapping("editStatusBox")
    public String editStatusBox(@ModelAttribute("box") Box box, Model model) {
        List<ParcelLocker> parcelLockerList = parcelLockerService.getAllParcelLocker();
        Status current = box.getStatus();
        current = current.getNext();
        log.info(String.valueOf(current));
        box.setStatus(current);
        service.addBox(box);

        if(current.equals(Status.PLACED_IN_SENDER_PARCEL_LOCKER)) {
            ParcelLocker updateParcelLocker = parcelLockerList.get(parcelLockerList.indexOf(box.getSenderParcelLocker()));
            changeStatusParcelLocker(box, updateParcelLocker);
        }

        if(current.equals(Status.JOURNEY_TO_CENTRAL)) {
            ParcelLocker updateParcelLocker = parcelLockerList.get(parcelLockerList.indexOf(box.getSenderParcelLocker()));
            changeStatusParcelLocker1(box, updateParcelLocker);
        }

        if(current.equals(Status.PLACED_IN_RECIPIENT_PARCEL_LOCKER)) {
            ParcelLocker updateParcelLocker = parcelLockerList.get(parcelLockerList.indexOf(box.getRecipientParcelLocker()));
            changeStatusParcelLocker(box, updateParcelLocker);
        }

        if(current.equals(Status.DELIVERED)) {
            ParcelLocker updateParcelLocker = parcelLockerList.get(parcelLockerList.indexOf(box.getRecipientParcelLocker()));
            changeStatusParcelLocker1(box, updateParcelLocker);
        }

        model.addAttribute("senderParcelLockerList", parcelLockerList);
        model.addAttribute("recipientParcelLockerList", parcelLockerList);
        model.addAttribute("box", box);
        return "editStatus";
    }



    private void changeStatusParcelLocker1(@ModelAttribute("box") Box box, ParcelLocker updateParcelLocker) {
        if (box.getSizeBox().equals(SizeBox.SMALL)) {
            updateParcelLocker.setSmallBoxStatus(updateParcelLocker.getSmallBoxStatus() - 1);
        } else if (box.getSizeBox().equals(SizeBox.MEDIUM)) {
            updateParcelLocker.setMediumBoxStatus(updateParcelLocker.getMediumBoxStatus() - 1);
        } else if (box.getSizeBox().equals(SizeBox.BIG)) {
            updateParcelLocker.setBigBoxStatus(updateParcelLocker.getBigBoxStatus() - 1);
        }
        parcelLockerService.addParcelLocker(updateParcelLocker);
    }

    private void changeStatusParcelLocker(@ModelAttribute("box") Box box, ParcelLocker updateParcelLocker) {
        if (box.getSizeBox().equals(SizeBox.SMALL)) {
            updateParcelLocker.setSmallBoxStatus(updateParcelLocker.getSmallBoxStatus() + 1);
        } else if (box.getSizeBox().equals(SizeBox.MEDIUM)) {
            updateParcelLocker.setMediumBoxStatus(updateParcelLocker.getMediumBoxStatus() + 1);
        } else if (box.getSizeBox().equals(SizeBox.BIG)) {
            updateParcelLocker.setBigBoxStatus(updateParcelLocker.getBigBoxStatus() + 1);
        }
        parcelLockerService.addParcelLocker(updateParcelLocker);
    }


}

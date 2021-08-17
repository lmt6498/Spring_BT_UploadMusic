package controllers;

import models.Music;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.MusicServices;

import java.io.File;
import java.io.IOException;

@Controller
public class MusicController {
    MusicServices musicServices = new MusicServices();

    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("list",musicServices.list);
        return modelAndView;
    }

    @PostMapping("create")
    public String create(@ModelAttribute Music music, @RequestParam MultipartFile uppMusic){
        String nameMusic = uppMusic.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppMusic.getBytes(), new File("D:\\JavaProject\\Spring_UploadMusic\\src\\main\\webapp\\music/" + nameMusic));
            String urlImg = "/music/" + nameMusic;
            music.setLink(urlImg);
        } catch (IOException e) {
            System.err.println("chưa uppload file");
        }
        musicServices.save(music);
        return "redirect:/home";
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("list", new Music());

        return modelAndView;
    }
}

package controllers;

import models.Music;
import models.MusicForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.MusicServices;

import java.io.File;
import java.util.List;

public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;

    private final MusicServices musicServices = new MusicServices();

    @GetMapping("/home")
    public ModelAndView index(){
        List<Music> list = musicServices.findAll();
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("musics",list);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(@ModelAttribute("musicForm") MusicForm form){
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute("musicForm") MusicForm form){
        MultipartFile multipartFile = form.getLink();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(form.getLink().getBytes(),new File(fileUpload,fileName));
        } catch (Exception e){
            e.printStackTrace();
        }
        Music music = new Music(form.getId(),form.getName(),form.getSinger(),fileName);
        musicServices.save(music);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }
}

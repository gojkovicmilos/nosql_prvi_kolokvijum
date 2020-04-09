package com.nosqlbaze.prvikolokvijum.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nosqlbaze.prvikolokvijum.model.FileModel;
import com.nosqlbaze.prvikolokvijum.services.FileService;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files/{id}")
    public String getFile(@PathVariable String id, Model model) throws IllegalStateException, IOException {
        FileModel file = fileService.getFile(id);
        model.addAttribute("title", file.getTitle());
        model.addAttribute("url", "/files/stream/" + id);
        return "files";
    }

    @GetMapping("/files/stream/{id}")
    public void streamFile(@PathVariable String id, HttpServletResponse response) throws IllegalStateException, IOException {
        FileModel file = fileService.getFile(id);
        FileCopyUtils.copy(file.getStream(), response.getOutputStream());
    }

    @GetMapping("/files/upload")
    public String uploadFile(Model model) {
        model.addAttribute("message", "hello");
        return "uploadFile";
    }

    @PostMapping("/files/add")
    public String addFile(@RequestParam("title") String title, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        String id = fileService.addFile(title, file);
        return "redirect:/files/" + id;
    }
}
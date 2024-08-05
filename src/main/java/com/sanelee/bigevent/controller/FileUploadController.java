package com.sanelee.bigevent.controller;

import com.sanelee.bigevent.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //保证文件名字是唯一的
        String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("D:\\Document\\files\\"+fileName));
        return Result.success("D:\\Document\\files\\"+fileName);
    }
}

package top.mxzero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.mxzero.common.exception.ServiceNotFoundException;
import top.mxzero.common.model.Image;
import top.mxzero.common.service.ImageService;
import top.mxzero.common.util.FileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {
    @Value("${file.image.path.prefix}")
    private String prefix;

    @Autowired
    private ImageService imageService;

    @PostMapping("/images/upload")
    public Object upload(MultipartFile file) throws IOException {
        String filename = FileUtil.generateFilename(file.getOriginalFilename());
        file.transferTo(new File(prefix + filename));

        Image image = new Image();
        image.setUrl(filename);
        imageService.save(image);

        return filename;
    }

    @GetMapping("/images/list")
    public Object listImages(){
        return imageService.list();
    }

    @RequestMapping(value = "/static/images/{filename}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public BufferedImage readImage(@PathVariable("filename") String filename) throws IOException {

        File file = new File(prefix + filename);
        if(!file.exists()){
            throw new ServiceNotFoundException();
        }

        return ImageIO.read(file);
    }

}

package top.cuizilin.website.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.cuizilin.website.utils.Constants;
import top.cuizilin.website.utils.OSSUtils;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.ImagePost;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/oss")
public class OSSController {

    @PostMapping("/add")
    public String addImage(MultipartFile file) throws IOException {
        String url = OSSUtils.saveImg(file.getBytes());
        return JSON.toJSONString(new Result().setCode(Constants.successCode)
                .setMessage(Constants.succeedMessage)
                .setData(url));
    }

}

package cn.calendo.tcmdistribution.controller.poi;

import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/2/22 17:10
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/poi/get")
public class GetPresHisExcel {

    @Autowired
    private IPresInfoService presInfoService;

    @Value("${constants.presHisLocation}")
    private String presHisLocation;

    /**
     * 数据报表输出
     *
     * @return org.springframework.http.ResponseEntity
     * @author Calendo
     * @date 2023/2/22 18:09
     */
    @PostMapping("/preshis_excel")
    public ResponseEntity getPresInfoExcel() throws IOException {

        //生成文件到自己服务器
        presInfoService.getPresHisExcel();

        FileSystemResource file = new FileSystemResource(presHisLocation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}

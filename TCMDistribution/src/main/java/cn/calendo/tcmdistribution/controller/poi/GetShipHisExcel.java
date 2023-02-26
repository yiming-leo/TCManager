package cn.calendo.tcmdistribution.controller.poi;

import cn.calendo.tcmdistribution.service.IShipInfoService;
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
public class GetShipHisExcel {

    @Autowired
    private IShipInfoService shipInfoService;

    @Value("${constants.shipHisLocation}")
    private String shipHisLocation;

    /**
     * 数据报表输出
     *
     * @return org.springframework.http.ResponseEntity
     * @author Calendo
     * @date 2023/2/22 18:09
     */
    @PostMapping("/shiphis_excel")
    public ResponseEntity getShipInfoExcel() throws IOException {

        //生成文件到自己服务器
        shipInfoService.getShipHisExcel();

        FileSystemResource file = new FileSystemResource(shipHisLocation);
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers", "*");
        headers.add("Access-Control-Allow-Methods","*");
        headers.add("Access-Control-Allow-Credentials","true");
        headers.add("Access-Control-Max-Age","3600");

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

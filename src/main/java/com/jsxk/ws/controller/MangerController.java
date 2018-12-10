package com.jsxk.ws.controller;


import com.alibaba.druid.wall.violation.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.Bo.VoidesQuery;
import com.jsxk.ws.model.Po.VoidesCatalog;
import com.jsxk.ws.model.Voides;
import com.jsxk.ws.model.couster;
import com.jsxk.ws.service.VoidesService;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/voides")
public class MangerController {

    @Autowired
    VoidesService voidesService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVoides(@RequestBody Voides voides) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "添加错误");

        resultJson.put("state", false);
        try {

            boolean result = voidesService.addVoides(voides);

            if (result) {

                resultJson.put("message", "添加成功");
                resultJson.put("state", true);

                return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

            }

        } catch (Exception ex) {

            log.error("新增错误{}", ex);
        }
        return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editVoides(@RequestBody Voides voides) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "编辑错误");

        resultJson.put("state", false);

        try {

            boolean result = voidesService.editVoides(voides);

            if (result) {
                resultJson.put("message", "编辑成功");

                resultJson.put("state", true);

                return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

            }

        } catch (Exception ex) {

            log.error("编辑错误{}", ex);

            resultJson.put("message", ex.getMessage());


        }
        return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

    }


    @RequestMapping("/dele")
    public String deleVoides(@RequestParam("id") int id) {


        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "添加错误");

        resultJson.put("state", false);

        try {
            boolean reult = voidesService.deletVoides(id);

            if (reult) {
                resultJson.put("message", "删除成功");

                resultJson.put("state", true);

                return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);
            }

        } catch (Exception ex) {

            log.error("删除错误{}", ex);
        }
        return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

    }


    @RequestMapping(value = "getVoidesById")
    public String getVoidesById(@RequestParam("id") int id) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        VoidesCatalog voides = voidesService.getVoidesCatalogById(id);

        resultJson.putPOJO("video", voides);

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }

    @RequestMapping(value = "/getVoidesList", method = RequestMethod.POST)
    public String getVoidesList(@RequestBody VoidesQuery voidesQuery) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Page page = PageHelper.startPage(voidesQuery.getPagenum(), 20);
            List<Voides> voides = voidesService.getVoidesList(voidesQuery);
            resultJson.putPOJO("data", voides);
            resultJson.put("pageNum", page.getPageNum());
            resultJson.put("pageSize", page.getPageSize());


        } catch (Exception ex) {

            log.error("查询错误{}", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    // @RequestMapping(value = "/getVoidesList", method = RequestMethod.POST)
    public String getvoielist(@RequestBody VoidesQuery voidesQuery) {


        return "hellworld";
    }


    @RequestMapping("/editwximages")
    public String editurl(@RequestParam("url") String url) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "添加错误");

        resultJson.put("state", false);

        try {

            Boolean result = voidesService.editcouster(url) > 0;

            if (result) {
                resultJson.put("message", "添加成功");

                resultJson.put("state", true);
            }

        } catch (Exception ex) {

            log.error("更新错误", ex);
        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("getwximages")
    public String getCoustor() {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        couster couster = voidesService.getcouster();

        resultJson.putPOJO("data", couster);
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


}

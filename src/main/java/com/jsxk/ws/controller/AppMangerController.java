package com.jsxk.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.AppManager;
import com.jsxk.ws.model.Po.AppMangers;
import com.jsxk.ws.service.AppManagerService;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appManger")
public class AppMangerController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    AppManagerService appManagerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addManager(@RequestBody AppManager appManager) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "添加错误");

        resultJson.put("state", false);
        try {

            boolean result = appManagerService.addAppManager(appManager);

            if (result) {

                resultJson.put("message", "添加成功");

                resultJson.put("state", true);

            }

        } catch (Exception ex) {
            log.error("新增应用错误{}", ex);
            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/getAppList")
    public String getAppList(@RequestParam("pagenum") int pagenum) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {
            Page page = PageHelper.startPage(pagenum, 20);
            List<AppMangers> appMangers = appManagerService.getAppManagerList();
            resultJson.putPOJO("data", appMangers);
            resultJson.put("pageNum", page.getPageNum());
            resultJson.put("pageSize", page.getPageSize());

        } catch (Exception ex) {
            log.error("查询应用列表错误{}", ex);
            resultJson.putPOJO("data", null);
            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    @RequestMapping("/editApp")
    public String editApp(@RequestBody AppManager appManager) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        resultJson.put("state", false);
        resultJson.put("message", "编辑错误");
        try {

            boolean result = appManagerService.editAppManager(appManager);
            if (result) {

                resultJson.put("message", "添加成功");

                resultJson.put("state", true);
            }

        } catch (Exception ex) {

            log.error("编辑错误{}", ex);

            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);


        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


    @RequestMapping("/deleApp")
    public String deletApp(@RequestParam("id") int id) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        resultJson.put("state", false);
        resultJson.put("message", "删除错误");
        try {

            boolean result = appManagerService.deletAppManager(id);
            if (result) {
                resultJson.put("message", "删除成功");

                resultJson.put("state", true);
            }

        } catch (Exception ex) {

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


}

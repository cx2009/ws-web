package com.jsxk.ws.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.Bo.OrderQuery;
import com.jsxk.ws.model.Order;
import com.jsxk.ws.service.OrderService;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("/order")
@RestController
@Slf4j
public class OrderController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getOrderList(@RequestBody OrderQuery orderQuery) {


        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Page page = PageHelper.startPage(orderQuery.getPageNum(), orderQuery.getLimit());

            List<Order> orders = orderService.getOrderListByQuery(orderQuery);

            resultJson.putPOJO("data", orders);

            resultJson.put("pageNum", page.getPageNum());
            resultJson.put("pageSize", page.getPageSize());


        } catch (Exception ex)

        {


            log.error("查询错误{}", ex);


        }


        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }







}

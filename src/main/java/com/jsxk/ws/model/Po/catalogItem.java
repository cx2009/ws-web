package com.jsxk.ws.model.Po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
public class catalogItem {


    private int id;

    private String name;

    private List<catalogItem> children;


}

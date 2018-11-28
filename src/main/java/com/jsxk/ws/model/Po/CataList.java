package com.jsxk.ws.model.Po;

import com.jsxk.ws.model.Catalogue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CataList {

    private int id;


    private String name;


    private List<catalogItem> children;
}

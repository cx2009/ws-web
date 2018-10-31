package com.jsxk.ws.model.Po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppMangerList {

    private int catalogId;

    private String catalogName;

    private List<AppManager> appMangers;


}

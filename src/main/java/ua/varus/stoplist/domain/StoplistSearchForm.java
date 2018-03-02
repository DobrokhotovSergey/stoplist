package ua.varus.stoplist.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoplistSearchForm {

    private String inn;
    private String fio;
    private Date birtdate;

}

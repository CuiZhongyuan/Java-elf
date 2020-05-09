package com.interfaceproject.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
@Data //引入lombok
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

    @Override
    public String toString(){
        return (
                        "id:"+id+","+
                        "userName:"+userName+","+
                        "passWord:"+passWord+","+
                        "realName:"+realName+"}"
                );
    }

}

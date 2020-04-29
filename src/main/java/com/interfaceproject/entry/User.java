package com.interfaceproject.entry;

import lombok.Data;
import lombok.extern.java.Log;

@Log
@Data //引入lombok
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

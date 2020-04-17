package com.pc.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_permission
 * @author 
 */
@Data
public class Permission implements Serializable {
    private Integer id;

    private String permissionName;

    private Date createName;

    private static final long serialVersionUID = 1L;
}
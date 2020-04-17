package com.pc.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * t_user_role
 * @author 
 */
@Data
public class UserRole implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}
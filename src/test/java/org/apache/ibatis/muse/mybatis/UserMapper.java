/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package org.apache.ibatis.muse.mybatis;

public interface UserMapper {
    public UserDomain selectById(int id);
}

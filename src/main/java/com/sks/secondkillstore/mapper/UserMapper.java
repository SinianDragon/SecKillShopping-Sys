package com.sks.secondkillstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sks.secondkillstore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HQD
 * @since 2024-04-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

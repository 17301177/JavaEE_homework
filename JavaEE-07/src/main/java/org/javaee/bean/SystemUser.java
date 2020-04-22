package org.javaee.bean;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author 郭佳华 17301093
 */
@Data
@Configuration
public class SystemUser {
    String id;
    String password;
    String role;
}

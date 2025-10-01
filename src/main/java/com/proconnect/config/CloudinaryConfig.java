package com.proconnect.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dl7oki7ao",
                "api_key", "356796322666593",
                "api_secret", "CRBI_Yv7MxsnyenHnzKyrOQ1E-g"
        ));
    }
}

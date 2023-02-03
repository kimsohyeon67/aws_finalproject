package dbsecure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration // 내가 설정한 클래스 파일
@EnableEncryptableProperties // 현재 정보를 application.properties 파일에서 사용 가능
public class DBConfig {
	@Bean("jasyptEncryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		
		config.setPassword(System.getenv("DB_PASSWORD"));
		
		// 알고리즘
		config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        System.out.println("====DBConfig 출력====");
        System.out.println(System.getenv("DB_PASSWORD"));
        System.out.println(encryptor.decrypt("L992ngF51DCGpJE4WZeuJ/3A93q/ptMrN0kaC+EaHko="));
        System.out.println(encryptor.decrypt("491zRZcMB/UzT4C9+6LNEY3vgMW+vYZ9iVWDfq/mzVjAayTkcmYcSRSKr9ahDXwlvOEsmeUUncKy/NTKzYMh5k45qHILzeAxlWzoWwIBQEboMZN2Svx2ze25p4q+7EEeoDY42dLBaPg="));
        System.out.println(encryptor.decrypt("w1W3k704TwX3pg9V1pOBp74kfIcKGIWU"));
        System.out.println(encryptor.decrypt("8c6SXgU9FDFahzgNOuj28S4dc+JppEZi"));
        
        return encryptor;
	}

}

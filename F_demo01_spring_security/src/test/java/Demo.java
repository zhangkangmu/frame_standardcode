import com.hong.utils.MD5Util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by zhangyuhong
 * Date:2020/4/12
 */
public class Demo {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String zhangyuhong = encoder.encode("123");
        System.out.println(zhangyuhong);
        boolean matches = encoder.matches("123", zhangyuhong);
        System.out.println(matches);
        System.out.println("===========MD5==================");
        String md5 = MD5Util.md5("张宇洪");
        System.out.println(md5);
    }
}

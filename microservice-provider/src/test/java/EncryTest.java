import com.mindai.util.EncryptUtil;

/**
 * @Description:
 * @Author: Huang Xiang
 * @Date: 2016骞�12鏈�11鏃�
 */
public class EncryTest {
    public static void main(String[] args) {
        String telEncrypt= EncryptUtil.encryptAESBASE64("13570954891","MjMxYWZiOGJlNzU4");
        String nameEncrypt=EncryptUtil.encryptAESBASE64("吴伟杰","MjMxYWZiOGJlNzU4");
        String cardEncrypt=EncryptUtil.encryptAESBASE64("6222020111122220001","MjMxYWZiOGJlNzU4");
        String creditEncrypt=EncryptUtil.encryptAESBASE64("340881199004087010","MjMxYWZiOGJlNzU4");
        System.out.println("tel="+telEncrypt);
        System.out.println("name="+nameEncrypt);
        System.out.println("bankAccount="+cardEncrypt);
        System.out.println("creditNum="+creditEncrypt);
        String telDecrypt=EncryptUtil.decryptAESBASE64(telEncrypt,"MjMxYWZiOGJlNzU4");
        String nameDecrypt=EncryptUtil.decryptAESBASE64(nameEncrypt,"MjMxYWZiOGJlNzU4");
        String cardDecrypt=EncryptUtil.decryptAESBASE64(cardEncrypt,"MjMxYWZiOGJlNzU4");
        String credDecrypt=EncryptUtil.decryptAESBASE64(creditEncrypt,"MjMxYWZiOGJlNzU4");
        System.out.println("tel="+telDecrypt);
        System.out.println("name="+nameDecrypt);
        System.out.println("bankAccount="+cardDecrypt);
        System.out.println("creditNum="+credDecrypt);
    }

}

package com.mindai.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * 
 * @author Maginar
 *
 */
public class EncryptUtil {
	
	
	/******************BASE64转码***************/
	/**
	 * BASE64解密
	 */
	public static byte[] decryptBASE64(String data)  {
		return Base64.decodeBase64(data);
	}

	/**
	 * BASE64加密
	 */
	public static String encryptBASE64(byte[] data){
		return	  new String(Base64.encodeBase64(data));
	}
	
	
	/******************不可逆加密***************/
	/**
	 * md5加密，默认以utf8取，加密后以base64转码
	 * 此方法同DigestUtils.md5(data)，此处为解析DigestUtils，其他方法将直接使用DigestUtils
	 * @param data
	 * @return
	 */
	public static String encryptMD5(String data) {
		MessageDigest md5=null;
	       try {
	    	   md5=MessageDigest.getInstance("MD5");
	    	   md5.update(StringUtils.getBytesUtf8(data));
	    	  
	    	} catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	       return encryptBASE64(md5.digest());
	}
	/**
	 * sha加密后再用base64转码
	 * @param data
	 * @return
	 */
	public static String encryptSHABASE64(String data){
		return encryptBASE64(DigestUtils.sha(data));
	}
	
	
	/******************对称加密***************/
	
	
	  /**
     * DES 加密后再用base64转码
     * 加密使用DES/ECB/PKCS5Padding
     * 如果需要使用CBC分组模式可用注释区代码
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * 
     */
	public static String encryptDESBASE64(String data, String key) {
		if (data==null||"".equals(data.trim()))
			return null;
		if (key == null||key.length()!=8) {
			return null;
		}
		try {

			//ECB模式 也可只是DES，因为默认是ECB分组，PKCS5Padding填充
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			
			//要使用SecureRandom也可以如下
//			DESKeySpec keySpec = new DESKeySpec(key.getBytes());
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			Key secretKey = keyFactory.generateSecret(keySpec);
//			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, secretKey,new SecureRandom());			
			
			//下面这个是CBC模式
//			DESKeySpec keySpec = new DESKeySpec(key.getBytes());
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			Key secretKey = keyFactory.generateSecret(keySpec);
			//CBC的处理方式是先用初始向量IV对第一组加密，再用第一组的密文作为密钥对第二组加密，然后依次完成整个加密操作
			//CBC模式才可以用IV向量，ECB模式是不能用的，会报ECB mode cannot use IV
			//API描述是：具有 IV 的缓冲区。复制该缓冲区始于且包含 offset 的前 len 个字节来防止后续修改。
//			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//			AlgorithmParameterSpec paramSpec = new IvParameterSpec("12345678".getBytes());  
//			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			
			byte[] bytes = cipher.doFinal(StringUtils.getBytesUtf8(data));
			return new String(encryptBASE64((bytes)));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	
	
	/**
	 * DES 先用base64后解密
	 * 加密使用DES/CBC/PKCS5Padding
	 * 如果需要使用ECB分组模式可用注释区代码
	 *
	 * @param data 待加密字符串
	 * @param key  加密私钥，长度不能够小于8位
	 * @return 加密后的字节数组，一般结合Base64编码使用
	 * 
	 */
	public static String decryptDESBASE64(String data, String key) {
		if (data==null||"".equals(data.trim()))
			return null;
		if (key == null||key.length()!=8) {
			return null;
		}
		try {
			//ECB
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			
			//下面这个是CBC模式
//			DESKeySpec keySpec = new DESKeySpec(key.getBytes());
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			Key secretKey = keyFactory.generateSecret(keySpec);
//			
//			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//			AlgorithmParameterSpec paramSpec = new IvParameterSpec("12345678".getBytes());  
//			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			
		
			
			byte[] bytes = cipher.doFinal(decryptBASE64(data));
			return StringUtils.newStringUtf8(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
	/**
	 * 
	 * AES加密后使用BASE64做转码
	 * 这里只能16字节密钥， 如果要使用24，32，需要配置local_policy.jar 和US_export_policy.jar
	 * @param data
	 * @param key
	 * @return
	 */
	public static String encryptAESBASE64(String data, String key) {
		//ey为16，如果要使用24，32，需要配置local_policy.jar 和US_export_policy.jar
		if (key == null||key.length()!=16) {
			return null;
		}
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String iv = key;
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivspec);
			byte[] bytes = cipher.doFinal(data.getBytes("utf-8"));
			return new String(encryptBASE64((bytes))); 
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * AES解密
	 * @param data
	 * @param key
	 * @return
	 */
	public static String decryptAESBASE64(String data, String key) {
		//key为16，如果要使用24，32，需要配置local_policy.jar 和US_export_policy.jar
		if (key == null||key.length()!=16) {
			return null;
		}
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String iv = key;
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivspec);
			byte[] bytes = cipher.doFinal(Base64.decodeBase64(data));
			return StringUtils.newStringUtf8(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	   /**
     * PBE 加密
     * 这个上面没有说，因为他并没有DES和AES流行，但是也有可取之处，因此这里也加上了
     * 只不过是把密钥概念转变成了“口令 + 盐”的方式而已，至于加解密的原理，还是用到常用的对称加密算法，比如 DES 和 AES 等，因此说，没有什么新东西
     * 
     * PBE算法没有密钥的概念，把口令当做密钥了。因为密钥长短影响算法安全性，还不方便记忆，这里我们直接换成我们自己常用的口令就大大不同了，便于我们的记忆。
     * 但是单纯的口令很容易被字典法给穷举出来，所以我们这里给口令加了点“盐”
     * 这个盐和口令组合，想破解就难了。同时我们将盐和口令合并后用消息摘要算法进行迭代很多次来构建密钥初始化向量的基本材料，使破译更加难了
     * 常用的算法有
     *     PBEWithSHAAndDES
     *     PBEWithSHAAndBlowfish
     *     PBEWithSHAAnd128BitRC4
     *     PBEWithSHAAndIDEA-CBC
     *     PBEWithSHAAnd3-KeyTripleDES-CBC
     *
     * @param data 需要加密的字节数组 
     * @param key  密钥 
     * @param salt 盐 必须只能是8byte
     * @param  itratCount 摘要算法进行迭代次数,增加破译难度
     * @return 
     */  
    public static byte[] encryptPBE(byte[] data, String key, byte[] salt,int itratCount) {  
    	byte[] bytes =null;
    	try {
    		SecretKey secretKey=initPBEkey(key);
             PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, itratCount);  
             //使用PBEWithMD5AndDES算法获取Cipher实例 
             Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES"); //secretKey.getAlgorithm()
             //设置为加密模式
             cipher.init(Cipher.ENCRYPT_MODE, secretKey,parameterSpec);  
             bytes = cipher.doFinal(data);  
    	} catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return bytes;  
    }  
    /**
     * PBE 加密,默认uft8，加密后b64转码
     */
    public static String encryptPBEBASE64(String data, String key){  
    	byte[] salt =  { (byte) 0xE5, (byte) 0xBB, (byte) 0x96, (byte) 0xE9,
    			(byte) 0x87, (byte) 0x91, (byte) 0xE6, (byte) 0x0B4 };

    	byte[] out=encryptPBE( StringUtils.getBytesUtf8(data),  key, salt,20);
    	return encryptBASE64(out);  
    }  

    /** 
     * PBE 解密 
     * @param data 需要解密的字节数组 
     * @param key  密钥 
     * @param salt 盐 
     * @return 
     */  
    public static byte[] decryptPBE(byte[] data, String key, byte[] salt,int itratCount) {  
        byte[] bytes = null;  
        try {  
            // 获取密钥  
        	SecretKey secretKey=initPBEkey(key);
        	 PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, itratCount);  
        	//使用PBEWithMD5AndDES算法获取Cipher实例 
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");  
            //设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);  
            bytes = cipher.doFinal(data);  
    	} catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return bytes;  
    }  
    
    /**
     * PBE 先进行b64解码,解密后utf8取
     */
    public static String decryptPBEBASE64(String data, String key){  
    	byte[] salt =  { (byte) 0xE5, (byte) 0xBB, (byte) 0x96, (byte) 0xE9,
    			(byte) 0x87, (byte) 0x91, (byte) 0xE6, (byte) 0x0B4 };
    	byte[] out=decryptPBE( decryptBASE64(data),  key, salt,20);
    	return StringUtils.newStringUtf8(out);  
    }   
    
	
    /**
     * 初始化PBE密钥
     * @param key
     * @return
     * @throws Exception
     */
	private static SecretKey initPBEkey(String key) throws Exception {
		// 初始化生成密钥
		PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
		SecretKeyFactory factory = SecretKeyFactory
				.getInstance("PBEWITHMD5andDES");
		SecretKey secretKey = factory.generateSecret(keySpec);
		return secretKey;
	}
	

	
	
	
	
	
	
	/******************不对称加密***************/	

    private static final String RSA_PUBLIC_KEY = "RSAPublicKey";   //生成公钥放入map中的key
    private static final String RSA_PRIVATE_KEY = "RSAPrivateKey"; //生成私钥放入map中的key
    private static final String RSA_KEY_ALGORITHM = "RSA"; //加密算法
    public static final String RSA_SIGNATURE_ALGORITHM = "MD5withRSA";//签名加密算法
	
    /** *//** 
     * 初始化密钥,使用时先生成密钥，然后再调用getRSAPrivateKey和getRSAPublicKey分别获取公钥和私钥
     *   
     * @return 
     * @throws Exception 
     */ 
    public static Map<String, Object> initRSAKey() throws Exception {   
        KeyPairGenerator keyPairGen = KeyPairGenerator   
                .getInstance(RSA_KEY_ALGORITHM);   
        keyPairGen.initialize(1024);   
        KeyPair keyPair = keyPairGen.generateKeyPair();   
        // 公钥   
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();   
        // 私钥   
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   

        Map<String, Object> keyMap = new HashMap<String, Object>(2);   

        keyMap.put(RSA_PUBLIC_KEY, publicKey);   
        keyMap.put(RSA_PRIVATE_KEY, privateKey);   
        return keyMap;   
    }   
    /** *//** 
     * 取得私钥 
     */ 
    public static String getRSAPrivateKey(Map<String, Object> keyMap)   
            throws Exception {   
        Key key = (Key) keyMap.get(RSA_PRIVATE_KEY);   

        return encryptBASE64(key.getEncoded());   
    }   

    /** 
     * 取得公钥 
     */ 
    public static String getRSAPublicKey(Map<String, Object> keyMap)   
            throws Exception {   
        Key key = (Key) keyMap.get(RSA_PUBLIC_KEY);   

        return encryptBASE64(key.getEncoded());   
    }   
	
    /**  
     * 加密 
     * 用公钥加密 
     */ 
    public static String encryptRSAByPublicKey(String data, String key)   
            throws Exception {   
        // 对公钥转码
        byte[] keyBytes = decryptBASE64(key);   
        // 取得公钥   
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);   
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);   
        Key publicKey = keyFactory.generatePublic(x509KeySpec);   
        
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

        // 模长  
        int key_len = ((RSAKey) publicKey).getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        String mi = "";  
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += bcd2Str(cipher.doFinal(s.getBytes()));  
        }  
        return mi;
    }   
    
    /**  
     * 加密 
     * 用私钥加密 
     */ 
    public static String encryptRSAByPrivateKey(String data, String key)   
            throws Exception {   
        // 对密钥转码   
        byte[] keyBytes = decryptBASE64(key);   
        // 取得私钥   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);   
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);   
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);   

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());   
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);   
        
        // 模长  
        int key_len = ((RSAKey) privateKey).getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        String mi = "";  
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += bcd2Str(cipher.doFinal(s.getBytes()));  
        }  
        return mi;
    }   
	
    /** 
     * 解密 
     * 用私钥解密 
     */  
    public static String decryptRSAByPrivateKey(String data, String key)  
            throws Exception {  
        // 对密钥转码  
        byte[] keyBytes = decryptBASE64(key);  
  
        // 取得私钥  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);  
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        
        //模长  
        int key_len = ((RSAKey) privateKey).getModulus().bitLength() / 8;  
        byte[] bytes = data.getBytes();  
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
        //如果密文长度大于模长则要分组解密  
        String ming = "";  
        byte[][] arrays = splitArray(bcd, key_len);  
        for(byte[] arr : arrays){  
            ming += new String(cipher.doFinal(arr));  
        }  
        return ming;  
    }  
  
    /** 
     * 解密 
     * 用公钥解密 
     */  
    public static String decryptRSAByPublicKey(String data, String key)  
            throws Exception {  
        // 对密钥转码  
        byte[] keyBytes = decryptBASE64(key);  
  
        // 取得公钥  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);  
        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
  
        // 对数据解密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);  
        
        //模长  
        int key_len = ((RSAKey) publicKey).getModulus().bitLength() / 8;  
        byte[] bytes = data.getBytes();  
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
        //如果密文长度大于模长则要分组解密  
        String ming = "";  
        byte[][] arrays = splitArray(bcd, key_len);  
        for(byte[] arr : arrays){  
            ming += new String(cipher.doFinal(arr));  
        }  
        return ming;  
    }  
    
    /** 
     * 用私钥对信息生成数字签名 
     * @param data  加密数据 
     * @param privateKey  私钥 
     * @return 
     * @throws Exception 
     */  
    public static String sign(String data, String privateKey) throws Exception {  
        // 解密由base64编码的私钥  
        byte[] keyBytes = decryptBASE64(privateKey);  
        // 构造PKCS8EncodedKeySpec对象  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        // RSA_KEY_ALGORITHM指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);  
        // 取私钥匙对象  
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  
        // 用私钥对信息生成数字签名  
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);  
        signature.initSign(priKey);  
        signature.update(data.getBytes());  
        return encryptBASE64(signature.sign());  
    }  
    /** 
     * 校验数字签名 
     * @param data  加密数据 
     * @param publicKey  公钥 
     * @param sign  数字签名 
     * @return 校验成功返回true 失败返回false 
     * @throws Exception 
     */  
    public static boolean verify(String data, String publicKey, String sign)  
            throws Exception {  
        // 解密由base64编码的公钥  
        byte[] keyBytes = decryptBASE64(publicKey);  
        // 构造X509EncodedKeySpec对象  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);  
        // 取公钥匙对象  
        PublicKey pubKey = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(RSA_SIGNATURE_ALGORITHM);  
        signature.initVerify(pubKey);  
        signature.update(data.getBytes());  
        // 验证签名是否正常  
        return signature.verify(decryptBASE64(sign));  
    }  
    /** 
     * 拆分字符串 
     */  
    private static String[] splitString(String string, int len) {  
        int x = string.length() / len;  
        int y = string.length() % len;  
        int z = 0;  
        if (y != 0) {  
            z = 1;  
        }  
        String[] strings = new String[x + z];  
        String str = "";  
        for (int i=0; i<x+z; i++) {  
            if (i==x+z-1 && y!=0) {  
                str = string.substring(i*len, i*len+y);  
            }else{  
                str = string.substring(i*len, i*len+len);  
            }  
            strings[i] = str;  
        }  
        return strings;  
    }  
    /** 
     *拆分数组  
     */  
    private static byte[][] splitArray(byte[] data,int len){  
        int x = data.length / len;  
        int y = data.length % len;  
        int z = 0;  
        if(y!=0){  
            z = 1;  
        }  
        byte[][] arrays = new byte[x+z][];  
        byte[] arr;  
        for(int i=0; i<x+z; i++){  
            arr = new byte[len];  
            if(i==x+z-1 && y!=0){  
                System.arraycopy(data, i*len, arr, 0, y);  
            }else{  
                System.arraycopy(data, i*len, arr, 0, len);  
            }  
            arrays[i] = arr;  
        }  
        return arrays;  
    }  
    /** 
     * BCD转字符串 
     */  
    private static String bcd2Str(byte[] bytes) {  
        char temp[] = new char[bytes.length * 2], val;  
  
        for (int i = 0; i < bytes.length; i++) {  
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
  
            val = (char) (bytes[i] & 0x0f);  
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
        }  
        return new String(temp);  
    }  
    /** 
     * ASCII码转BCD码 
     *  
     */  
    private static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
        byte[] bcd = new byte[asc_len / 2];  
        int j = 0;  
        for (int i = 0; i < (asc_len + 1) / 2; i++) {  
            bcd[i] = asc_to_bcd(ascii[j++]);  
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
        }  
        return bcd;  
    }  
    private static byte asc_to_bcd(byte asc) {  
        byte bcd;  
  
        if ((asc >= '0') && (asc <= '9'))  
            bcd = (byte) (asc - '0');  
        else if ((asc >= 'A') && (asc <= 'F'))  
            bcd = (byte) (asc - 'A' + 10);  
        else if ((asc >= 'a') && (asc <= 'f'))  
            bcd = (byte) (asc - 'a' + 10);  
        else  
            bcd = (byte) (asc - 48);  
        return bcd;  
    }  
	
	
	
	
	
}

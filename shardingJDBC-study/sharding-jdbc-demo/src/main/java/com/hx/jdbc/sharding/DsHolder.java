package com.hx.jdbc.sharding;

/**
 *
 */
public class DsHolder {
    private static boolean useBackupDs = false;//是否使用备用数据源

    public static boolean isUseBackupDs() {
        return useBackupDs;
    }

    public static synchronized void setUseBackupDs(boolean useBackupDs) {
        DsHolder.useBackupDs = useBackupDs;
    }
}

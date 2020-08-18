package com.aubga.redis.distributeLock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LuaScript {
    /**
     * 加锁脚本 lock.lua
     * */
    public static String LOCK_SCRIPT = "";

    /**
     * 解锁脚本 unlock.lua
     * */
    public static String UN_LOCK_SCRIPT = "";

    public static void init(){
        try {
            initLockScript();
            initUnLockScript();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initLockScript() throws IOException {
        String filePath = Objects.requireNonNull(LuaScript.class.getClassLoader().getResource("lock.lua")).getPath();
        LOCK_SCRIPT = readFile(filePath);
    }

    private static void initUnLockScript() throws IOException {
        String filePath = Objects.requireNonNull(LuaScript.class.getClassLoader().getResource("unlock.lua")).getPath();
        UN_LOCK_SCRIPT = readFile(filePath);
    }

    private static String readFile(String filePath) throws IOException {
        try (
                FileReader reader = new FileReader(filePath);
                BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }

            return stringBuilder.toString();
        }
    }
}
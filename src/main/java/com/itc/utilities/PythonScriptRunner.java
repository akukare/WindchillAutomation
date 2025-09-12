package com.itc.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonScriptRunner {

    /**
     * Runs a Python script using the system's Python interpreter.
     * @param scriptPath Full path to the Python script (.py)
     */
    public static void run(String scriptPath) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            	LogUtil.info("[Python] " + line);
            }

            int exitCode = process.waitFor();
            LogUtil.info("Python script exited with code: " + exitCode);
        } catch (Exception e) {
        	LogUtil.info("Error running Python script: " + e.getMessage());
        }
    }
}
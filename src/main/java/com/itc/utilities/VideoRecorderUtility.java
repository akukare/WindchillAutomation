package com.itc.utilities;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.util.List;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorderUtility {
	private static ScreenRecorder screenRecorder;
    private static String videoName;
    private static File recordedFile;

    public static void startRecording(String testName) throws Exception {
        videoName = testName;
        File file = new File("test-videos");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(0, 0,
                screenSize.width, screenSize.height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                null, null, file, testName);
        screenRecorder.start();
    }

    public static File stopRecording() throws Exception {
        if (screenRecorder != null) {
            screenRecorder.stop();
            List<File> files = screenRecorder.getCreatedMovieFiles();
            if (files != null && !files.isEmpty()) {
                recordedFile = files.get(0);
            }
            screenRecorder = null;
        }
        return recordedFile;
    }

    public static File getRecordedFile() {
        return recordedFile;
    }

    public static void deleteRecording() {
        if (recordedFile != null && recordedFile.exists()) {
            recordedFile.delete();
            recordedFile = null;
        }
    }
}

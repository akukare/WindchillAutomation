package com.itc.utilities;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SpecializedScreenRecorder extends ScreenRecorder {

    private final String name;

    public SpecializedScreenRecorder(GraphicsConfiguration cfg,
                                     Rectangle captureArea,
                                     Format fileFormat,
                                     Format screenFormat,
                                     Format mouseFormat,
                                     Format audioFormat,
                                     File movieFolder,
                                     String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        }

        return new File(movieFolder,
                name + "-" + System.currentTimeMillis() + "." +
                Registry.getInstance().getExtension(fileFormat));
    }
}

/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.util.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.apache.tika.utils.StringUtils;

/**
 * Detecting file type
 */
public class FileTypeDetector {

    TikaConfig tika;

    public FileTypeDetector() throws TikaException, IOException {
         tika = new TikaConfig();
    }

    /**
     * Note: this method is deprecated in Tika Core 2.4.1
     * @param file          File to detect
     * @param name          Optional file name to include as a hint for the detection strategy
     * @return
     * @throws IOException
     */
    @Deprecated
    public MediaType detectMediaType(File file, String name) throws IOException {
        Metadata metadata = new Metadata();
        // Set optional name as an additional 'hint'
        if (!StringUtils.isBlank(name)) {
            metadata.set(TikaCoreProperties.RESOURCE_NAME_KEY, name);
        }
        return tika.getDetector().detect(TikaInputStream.get(file, metadata), metadata);
    }

    /**
     * Return media type for an input stream
     * @param inputStream   Input stream from file
     * @param name          Optional file name to include as a hint for the detection strategy
     * @return
     * @throws IOException
     */
    public MediaType detectMediaType(InputStream inputStream, String name) throws IOException {
        Metadata metadata = new Metadata();
        // Set optional name as an additional 'hint'
        if (!StringUtils.isBlank(name)) {
            metadata.set(TikaCoreProperties.RESOURCE_NAME_KEY, name);
        }
        return tika.getDetector().detect(TikaInputStream.get(inputStream), metadata);
    }

}

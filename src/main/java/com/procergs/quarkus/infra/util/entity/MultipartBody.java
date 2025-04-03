package com.procergs.quarkus.infra.util.entity;

import java.io.InputStream;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class MultipartBody {

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

}

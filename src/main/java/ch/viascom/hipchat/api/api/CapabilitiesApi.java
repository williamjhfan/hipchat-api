package ch.viascom.hipchat.api.api;

import ch.viascom.groundwork.foxhttp.annotation.types.GET;
import ch.viascom.groundwork.foxhttp.annotation.types.Header;
import ch.viascom.groundwork.foxhttp.annotation.types.Path;
import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.hipchat.api.models.Capabilities;

@Path("{host}")
@Header(name = "Content-Type", value = "application/json")
public interface CapabilitiesApi {

    @GET("/capabilities")
    Capabilities getCapabilities() throws FoxHttpException;

}

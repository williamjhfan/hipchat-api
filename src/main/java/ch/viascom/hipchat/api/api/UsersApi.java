package ch.viascom.hipchat.api.api;

import ch.viascom.groundwork.foxhttp.FoxHttpResponse;
import ch.viascom.groundwork.foxhttp.annotation.types.*;
import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.hipchat.api.models.Message;
import ch.viascom.hipchat.api.models.PrivateMessage;
import ch.viascom.hipchat.api.models.User;
import ch.viascom.hipchat.api.request.models.*;
import ch.viascom.hipchat.api.response.GetAllUsersResponse;
import ch.viascom.hipchat.api.response.GetAutoJoinRoomsResponse;
import ch.viascom.hipchat.api.response.ViewPrivatechatHistoryResponse;
import ch.viascom.hipchat.api.response.ViewRecentPrivatechatHistoryResponse;

import java.io.InputStream;

@Path("{host}")
@Header(name = "Content-Type", value = "application/json")
public interface UsersApi {

    @GET("/user")
    GetAllUsersResponse getAllUsers(@QueryObject(parseSerializedName = true) GetAllUsers getAllUsers) throws FoxHttpException;

    @POST("/user")
    FoxHttpResponse createUser(@Body CreateUser createUser) throws FoxHttpException;

    @GET("/user/{id}")
    User viewUser(@Path("id") String idOrEmail) throws FoxHttpException;

    @PUT("/user/{id}")
    @SkipResponseBody(true)
    FoxHttpResponse updateUser(@Path("id") String idOrEmail,
                               @Body UpdateUser updateUser) throws FoxHttpException;

    @DELETE("/user/{id}")
    @SkipResponseBody(true)
    FoxHttpResponse deleteUser(@Path("id") String idOrEmail) throws FoxHttpException;

    @POST("/user/{id}/message")
    @SkipResponseBody(true)
    FoxHttpResponse sendPrivateMessage(@Path("id") String idOrEmail,
                                       @Body PrivateMessage privateMessage) throws FoxHttpException;

    @GET("/user/{id}/photo/{size}")
    InputStream getPhoto(@Path("id") String idOrEmail,
                         @Path("size") String size) throws FoxHttpException;

    @PUT("/user/{id}/photo")
    @SkipResponseBody(true)
    FoxHttpResponse updatePhoto(@Path("id") String idOrEmail,
                                @Body UpdateUserPhoto photo) throws FoxHttpException;

    @DELETE("/user/{id}/photo")
    @SkipResponseBody(true)
    FoxHttpResponse deletePhoto(@Path("id") String idOrEmail) throws FoxHttpException;

    @GET("/user/{id}/preference/auto-join")
    GetAutoJoinRoomsResponse getAutoJoinRooms(@Path("id") String idOrEmail,
                                              @Query("start-index") String startIndex,
                                              @Query("max-results") String maxResults) throws FoxHttpException;

    @GET("/user/{id}/history/{message_id}")
    Message getPrivatechatMessage(@Path("id") String idOrEmail,
                                  @Path("message_id") String messageId,
                                  @Query("timezone") String timezone,
                                  @Query("include_deleted") String includeDeleted) throws FoxHttpException;

    @GET("/user/{id}/history")
    ViewPrivatechatHistoryResponse viewPrivatechatHistory(@Path("id") String idOrEmail,
                                                          @QueryObject(parseSerializedName = true) ViewPrivatechatHistory viewPrivatechatHistory) throws FoxHttpException;

    @GET("/user/{id}/history/latest")
    ViewRecentPrivatechatHistoryResponse viewRecentPrivatechatHistory(@Path("id") String idOrEmail,
                                                                      @QueryObject(parseSerializedName = true) ViewRecentPrivatechatHistory viewRecentPrivatechatHistory) throws FoxHttpException;
}

package ch.viascom.hipchat.api;

import ch.viascom.groundwork.foxhttp.exception.FoxHttpException;
import ch.viascom.hipchat.api.api.RoomsApi;
import ch.viascom.hipchat.api.models.Card;
import ch.viascom.hipchat.api.models.Message;
import ch.viascom.hipchat.api.models.Notification;
import ch.viascom.hipchat.api.models.card.*;
import ch.viascom.hipchat.api.models.message.MessageColor;
import ch.viascom.hipchat.api.models.room.RoomRole;
import ch.viascom.hipchat.api.models.room.RoomStatistics;
import ch.viascom.hipchat.api.request.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by patrickboesch on 11.04.16.
 */
public class RoomApiTest {

    private static final Logger log = LogManager.getLogger(RoomApiTest.class);
    private static final String clientToken = "";
    private static final String integrationToken = "";

    private static String testBotUserId = "";

    @Test
    public void sendRoomNotificationTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(integrationToken);
        hipChat.roomsAPI().sendRoomNotification("2640607", new Notification(null, null, MessageColor.RED, null, true, "Hello World", null));
    }

    @Test
    public void sendRoomMessageTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(clientToken);
        hipChat.roomsAPI().sendRoomMessage("2640607", new MessageRequestBody("Hello World"));
    }


    @Test
    public void replyToMessageTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(clientToken);
        hipChat.roomsAPI().replyToMessage("2640607", new ReplyMessage( "Reply", "95e30c9d-b143-4475-9f98-b8b54ef6ed73")); // Fill out last parameter with parentId
    }

    @Test
    public void setTopicTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(integrationToken);
        hipChat.roomsAPI().setTopic("2640607", new SetTopic("New fancy topic for our test room"));
    }

    @Test
    public void getAllParticipantsTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(integrationToken);
        log.debug(hipChat.roomsAPI().getAllParticipants("2640607", "0", "100", "true"));
    }


    @Test
    public void addMemberTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(clientToken);
        hipChat.roomsAPI().addMember("2640607",
                testBotUserId,
                new AddMember(new ArrayList<>(Collections.singletonList(RoomRole.ROOM_MEMBER))));
    }

    @Test
    public void viewRoomHistoryTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(clientToken);
        ViewRoomHistory viewRoomHistory = new ViewRoomHistory(0, 100);
        viewRoomHistory.setReverse(false);
        ArrayList<Message> messages = hipChat.roomsAPI().viewRoomHistory("2640607", viewRoomHistory).getItems();

        System.out.println(messages);
    }

    @Test
    public void getRoomStatisticsTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(clientToken);
        RoomStatistics statistics = hipChat.roomsAPI().getRoomStatistics("TestRoom");

        System.out.println(statistics);

        assertThat(statistics.getLastActive()).isNotEmpty();
    }

    @Test
    public void sendRoomNotificationCardTest() throws FoxHttpException {
        HipChat hipChat = new HipChat(integrationToken);
        RoomsApi roomsApi = hipChat.roomsAPI();

        SendNotification notification = new SendNotification();
        notification.setMessage("Hi from (puzzle) HipChat-API v2");
        notification.setColor(MessageColor.RED);
        notification.setNotify(true);

        Card card = new Card();
        card.setTitle("API Card");

        CardIcon icon = new CardIcon();
        icon.setUrl("https://dujrsrsgsd3nh.cloudfront.net/img/emoticons/22438/fire-1366382911.png");
        card.setIcon(icon);
        card.setStyle(CardStyle.APPLICATION);
        card.setUrl("http://github.com");
        card.setId("3985698273957");
        card.setFormat(CardFormat.COMPACT);

        CardAttribute cardAttribute = new CardAttribute();

        cardAttribute.setLabel("Version");

        CardAttributeValue cardAttributeValue = new CardAttributeValue();

        cardAttributeValue.setLabel("1.0");
        CardIcon icon2 = new CardIcon();
        icon2.setUrl("https://dujrsrsgsd3nh.cloudfront.net/img/emoticons/22438/puzzle-1366382992.png");
        cardAttributeValue.setIcon(icon2);

        cardAttribute.setValue(cardAttributeValue);

        card.getAttributes().add(cardAttribute);

        CardActivity cardActivity = new CardActivity();

        cardActivity.setHtml("API test Card from hipchat-api");
        cardActivity.setIcon(icon);

        card.setActivity(cardActivity);

        notification.setCard(card);

        roomsApi.sendRoomNotification("2640607", notification);
    }
}

package main.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetAddress;

@Getter
@Setter
@NoArgsConstructor
public class UrlsListResponse {

    private Long id;
    private String sourceUrl;
    private String destinationUrl;
    private String fullStringForFront;

    public UrlsListResponse(Long id, String sourceUrl, String destinationUrl) {
        this.id = id;
        this.sourceUrl = sourceUrl;
        this.destinationUrl = destinationUrl;
        this.fullStringForFront = InetAddress.getLoopbackAddress().getHostName() + "/r/" + destinationUrl;
    }
}
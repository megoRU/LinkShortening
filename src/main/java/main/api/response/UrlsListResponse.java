package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlsListResponse {

    private Long id;
    private String sourceUrl;
    private String destinationUrl;
}

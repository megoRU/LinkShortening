package main.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {

    private Long id;
    private String text;
    private String destinationUrl;
    private String sourceUrl;
}

package AdaptersAndComparators;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {
    @Override
    public ZonedDateTime unmarshal(String date) {
        return ZonedDateTime.parse(date);
    }

    @Override
    public String marshal(ZonedDateTime date) throws Exception {
        return date.toString();
    }
}

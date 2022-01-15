package com.sbrf.reboot.post_office;

import com.sbrf.reboot.post_office.parcels.BookPost;
import com.sbrf.reboot.post_office.parcels.Letter;
import com.sbrf.reboot.post_office.parcels.Parcel;
import com.sbrf.reboot.post_office.parcels.PostalPackage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrierServiceTest {

    @Test
    void getSummarizeCarriageCost() {
        Letter letter = new Letter();
        BookPost bookPost = new BookPost();
        PostalPackage postalPackage = new PostalPackage();
        Parcel otherParcel = new Parcel() {};

        CarrierService<Parcel> carrierService = new CarrierService<>(new ArrayList<Parcel>() {{
            add(letter);
            add(bookPost);
            add(postalPackage);
            add(otherParcel);
        }});

        assertEquals(new BigDecimal(7100), carrierService.getSummarizeCarriageCost());
    }
}

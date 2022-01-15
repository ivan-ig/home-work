package com.sbrf.reboot.post_office;

import com.sbrf.reboot.post_office.parcels.BookPost;
import com.sbrf.reboot.post_office.parcels.Letter;
import com.sbrf.reboot.post_office.parcels.Parcel;
import com.sbrf.reboot.post_office.parcels.PostalPackage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrierTest {

    @Test
    void getLetterShipmentCost() {
        Letter letter = new Letter();
        Carrier<Parcel> letterCarrier = new Carrier<>(letter);

        assertEquals(new BigDecimal(100), letterCarrier.getShipmentCost());
    }

    @Test
    void getBookPostShipmentCost() {
        BookPost bookPost = new BookPost();
        Carrier<Parcel> bookPostCarrier = new Carrier<>(bookPost);

        assertEquals(new BigDecimal(500), bookPostCarrier.getShipmentCost());
    }

    @Test
    void getPostalPackageShipmentCost() {
        PostalPackage postalPackage = new PostalPackage();
        Carrier<Parcel> postalPackageCarrier = new Carrier<>(postalPackage);

        assertEquals(new BigDecimal(1500), postalPackageCarrier.getShipmentCost());
    }

    @Test
    void getOtherParcelShipmentCost() {
        Parcel parcel = new Parcel(){};
        Carrier<Parcel> other = new Carrier<>(parcel);

        assertEquals(new BigDecimal(5000), other.getShipmentCost());
    }
}

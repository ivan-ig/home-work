package com.sbrf.reboot.atm.cassettes;

import java.util.List;

public class Cassette <E> {

    private final List<E> banknotes;

    public Cassette(List<E> banknotes) {
        this.banknotes = banknotes;
    }

    public int getCountBanknotes() {
        return banknotes.size();
    }
}

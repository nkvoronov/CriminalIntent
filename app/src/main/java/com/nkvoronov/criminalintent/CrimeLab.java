package com.nkvoronov.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCremeLab;
    private List<Crime> mCrimes;

    public CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
    }

    public static CrimeLab get(Context context) {
        if (sCremeLab == null) {
            sCremeLab = new CrimeLab(context);
        }
        return sCremeLab;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public void delCrime(Crime c) {
        mCrimes.remove(c);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}

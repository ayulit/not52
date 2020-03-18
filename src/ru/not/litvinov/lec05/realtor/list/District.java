package ru.not.litvinov.lec05.realtor.list;

public enum District {
    AVTOZAVOD("Avtozavod"),
    SORMOVO("Sormovo"),
    KANAVINO("Kanavino"),
    MOSCOVSKY("Moskovsky"),
    PRIOKSKI("Priokski"),
    NIZHEGORODSKY("Nizhegorodski"),
    SOVETSKY("Sovetky");

    private String district;

    District(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public int getIndex() {
        return ordinal() + 1;
    }
}
